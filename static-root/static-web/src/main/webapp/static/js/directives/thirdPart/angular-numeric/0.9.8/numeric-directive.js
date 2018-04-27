/**
 * Numeric directive.
 * Version: 0.9.8
 * 
 * Numeric only input. Limits input to:
 * - max value: maximum input value. Default undefined (no max).
 * - min value: minimum input value. Default undefined (no min).
 * - decimals: number of decimals. Default 2.
 * - formatting: apply thousand separator formatting. Default true.
 */
(function () {
    'use strict';

    angular
        .module('purplefox.numeric', [])
        .directive('numeric', numeric);

    numeric.$inject = ['$locale'];

    function numeric($locale) {
        var directive = {
            link: link,
            require: 'ngModel',
            restrict: 'A'
        };
        return directive;


        function link(scope, el, attrs, ngModelCtrl) {
            var decimalSeparator = $locale.NUMBER_FORMATS.DECIMAL_SEP;
            var groupSeparator = $locale.NUMBER_FORMATS.GROUP_SEP;

            var NUMBER_REGEXP = "^\\s*(\\-|\\+)?(\\d+|(\\d*(\\.\\d*)))\\s*$";
            var regex = new RegExp(NUMBER_REGEXP);

            var formatting = true;
            var maxInputLength = 18;            
            var max = 99999999999999;                            
            var min = 0;                            
            var decimals = 4;                   
            var lastValidValue;                 

            ngModelCtrl.$parsers.push(parseViewValue);
            ngModelCtrl.$parsers.push(minValidator);
            ngModelCtrl.$parsers.push(maxValidator);
            ngModelCtrl.$formatters.push(formatViewValue);

            el.bind('blur', onBlur);        
            el.bind('focus', onFocus);      

            scope.$watch(attrs.min, onMinChanged);
            scope.$watch(attrs.max, onMaxChanged);
            scope.$watch(attrs.decimals, onDecimalsChanged);
            scope.$watch(attrs.formatting, onFormattingChanged);

            if (decimals > -1) {
                ngModelCtrl.$parsers.push(function (value) {
                    return (value) ? round(value) : value;
                });
                ngModelCtrl.$formatters.push(function (value) {
                    return (value) ? formatPrecision(value) : value;
                });
            }

            function onMinChanged(value) {
                if (!angular.isUndefined(value)) {
                    min = parseFloat(value);
                    lastValidValue = minValidator(ngModelCtrl.$modelValue);
                    ngModelCtrl.$setViewValue(formatPrecision(lastValidValue));
                    ngModelCtrl.$render();
                }
            }

            function onMaxChanged(value) {
                if (!angular.isUndefined(value)) {
                    max = parseFloat(value);
                    maxInputLength = calculateMaxLength(max);
                    lastValidValue = maxValidator(ngModelCtrl.$modelValue);
                    ngModelCtrl.$setViewValue(formatPrecision(lastValidValue));
                    ngModelCtrl.$render();
                }
            }

            function onDecimalsChanged(value) {
                if (!angular.isUndefined(value)) {
                    decimals = parseFloat(value);
                    maxInputLength = calculateMaxLength(max);
                    if (lastValidValue !== undefined) {
                        ngModelCtrl.$setViewValue(formatPrecision(lastValidValue));
                        ngModelCtrl.$render();
                    }
                }
            }

            function onFormattingChanged(value) {
                if (!angular.isUndefined(value)) {
                    formatting = (value !== false);
                    ngModelCtrl.$setViewValue(formatPrecision(lastValidValue));
                    ngModelCtrl.$render();
                }
            }

            /**
             * Round the value to the closest decimal.
             */
            function round(value) {
                var d = Math.pow(10, decimals);
                return Math.round(value * d) / d;
            }

            /**
             * Format a number with the thousand group separator.
             */
            function numberWithCommas(value) {
                if (formatting) {
                    var parts = value.toString().split(decimalSeparator);
                    parts[0] = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, groupSeparator);
                    return parts.join(decimalSeparator);
                }
                else {
                    return value;
                }
            }

            /**
             * Format a value with thousand group separator and correct decimal char.
             */
            function formatPrecision(value) {
                if (!(value || value === 0)) {
                    return '';
                }
                var formattedValue = parseFloat(value).toFixed(decimals);
                formattedValue = formattedValue.replace('.', decimalSeparator);
                return numberWithCommas(formattedValue);
            }

            function formatViewValue(value) {
                return ngModelCtrl.$isEmpty(value) ? '' : '' + value;
            }

            /**
             * Parse the view value.
             */
            function parseViewValue(value) {
                if (angular.isUndefined(value)) {
                    value = '';
                }
                value = value.toString().replace(decimalSeparator, '.');

                if (value.indexOf('.') === 0) {
                    value = '0' + value;
                }

                if (value.indexOf('-') === 0) {
                    if (min >= 0) {
                        value = null;
                        ngModelCtrl.$setViewValue(formatViewValue(lastValidValue));
                        ngModelCtrl.$render();
                    }
                    else if (value === '-') {
                        value = '';
                    }
                }

                var empty = ngModelCtrl.$isEmpty(value);
                if (empty) {
                    lastValidValue = '';
                } 
                else {
                    if (regex.test(value) && (value.length <= maxInputLength)) {
                        if (value > max) {
                            lastValidValue = max - 1;
                            ngModelCtrl.$setViewValue(formatViewValue(lastValidValue));
                            ngModelCtrl.$render();
                            return lastValidValue;
                        }
                        else if (value < min) {
                            lastValidValue = min;
                        }
                        else {
                            lastValidValue = (value === '') ? null : parseFloat(value);
                        }
                    }
                    else {
                        ngModelCtrl.$setViewValue(formatViewValue(lastValidValue));
                        ngModelCtrl.$render();
                    }
                }

                return lastValidValue;
            }

            /**
             * Calculate the maximum input length in characters.
             * If no maximum the input will be limited to 16; the maximum ECMA script int.
             */
            function calculateMaxLength(value) {
                var length = 18;
                if (!angular.isUndefined(value)) {
                    length = Math.floor(value).toString().length;
                }
                if (decimals > 0) {
                    length += decimals + 1; 
                }
                if (min < 0) {
                    length++;
                }
                return length;
            }

            /**
             * Minimum value validator.
             */
            function minValidator(value) {
                if (!angular.isUndefined(min)) {
                    if (!ngModelCtrl.$isEmpty(value) && (value < min)) {
                        return min;
                    } else {
                        return value;
                    }
                }
                else {
                    return value;
                }
            }

            /**
             * Maximum value validator.
             */
            function maxValidator(value) {
                if (!angular.isUndefined(max)) {
                    if (!ngModelCtrl.$isEmpty(value) && (value > max)) {
                        return max;
                    } else {
                        return value;
                    }
                }
                else {
                    return value;
                }
            }


            /**
             * Function for handeling the blur (leave) event on the control.
             */
            function onBlur() {
                var value = ngModelCtrl.$modelValue;
                if (!angular.isUndefined(value)) {
                    ngModelCtrl.$viewValue = formatPrecision(value);
                    ngModelCtrl.$render();
                }
            }

            
            /**
             * Function for handeling the focus (enter) event on the control.
             * On focus show the value without the group separators.
             */
            function onFocus() {
                var value = ngModelCtrl.$modelValue;
                if (!angular.isUndefined(value)) {
                    ngModelCtrl.$viewValue = value.toString().replace(".", decimalSeparator);
                    ngModelCtrl.$render();
                }
            }
        }
    }

})();

angular.module('oi.select')

.provider('oiSelect', function() {
    return {
        options: {
            debounce:       500,
            searchFilter:   'oiSelectCloseIcon',
            dropdownFilter: 'oiSelectHighlight',
            listFilter:     'oiSelectAscSort',
            groupFilter:    'oiSelectGroup',
            editItem:       false,
            newItem:        false,
            closeList:      true,
            saveTrigger:    'enter tab blur',
            minlength:      0
        },
        version: {
            full: '0.2.21',
            major: 0,
            minor: 2,
            dot: 21
        },
        $get: function() {
            return {
                options: this.options,
                version: this.version
            };
        }
    };
})

.factory('oiSelectEscape', function() {
    var rEscapableCharacters = /[-\/\\^$*+?.()|[\]{}]/g;  
    var sEscapeMatch = '\\$&';

    return function(string) {
        return String(string).replace(rEscapableCharacters, sEscapeMatch);
    };
})

.factory('oiSelectEditItem', function() {
    return function(removedItem, lastQuery, getLabel, itemIsCorrected) {
        return itemIsCorrected ? '' : getLabel(removedItem);
    };
})

.factory('oiUtils', ['$document', '$timeout', function($document, $timeout) {
    /**
     * Check to see if a DOM element is a descendant of another DOM element.
     *
     * @param {DOM element} container
     * @param {DOM element} contained
     * @param {string} class name of element in container
     * @returns {boolean}
     */
    function contains(container, contained, className) {
        var current = contained;

        while (current && current.ownerDocument && current.nodeType !== 11) {
            if (className) {
                if (current === container) {
                    return false;
                }
                if (current.className.indexOf(className) >= 0) { 
                    return true;
                }
            } else {
                if (current === container) {
                    return true;
                }
            }
            current = current.parentNode;
        }

        return false;
    }

    /**
     * Simulate focus/blur events of the inner input element to the outer element
     *
     * @param {element} outer element
     * @param {element} inner input element
     * @returns {function} deregistration function for listeners.
     */
    function bindFocusBlur(element, inputElement) {
        var isFocused, isMousedown, isBlur;

        $document[0].addEventListener('click', clickHandler, true);
        element[0].addEventListener('mousedown', mousedownHandler, true);
        element[0].addEventListener('blur', blurHandler, true);
        inputElement.on('focus', focusHandler);

        function blurHandler(event) {
            if (event && event.target.nodeName !== 'INPUT') return; 

            isBlur = false;
            isFocused = false;

            if (isMousedown) {
                isBlur = true;
                return;
            }

            $timeout(function () {
                element.triggerHandler('blur'); 
            });
        }

        function focusHandler() {
            if (!isFocused) {
                isFocused = true;

                $timeout(function () {
                    element.triggerHandler('focus'); 
                });
            }
        }

        function mousedownHandler() {
            isMousedown = true;
        }

        function clickHandler(event) {
            isMousedown = false;

            var activeElement = event.target;
            var isSelectElement = contains(element[0], activeElement);

            if (isBlur && !isSelectElement) {
                blurHandler();
            }

            if (isSelectElement && activeElement.nodeName !== 'INPUT') {
                $timeout(function () {
                    inputElement[0].focus();
                });
            }

            if (!isSelectElement && isFocused) {
                isFocused = false;
            }
        }

        return function () {
            $document[0].removeEventListener('click', clickHandler, true);
            element[0].removeEventListener('mousedown', mousedownHandler, true);
            element[0].removeEventListener('blur', blurHandler, true);
            inputElement.off('focus', focusHandler);
        }
    }

    /**
     * Sets the selected item in the dropdown menu
     * of available options.
     *
     * @param {object} list
     * @param {object} item
     */
    function scrollActiveOption(list, item) {
        var y, height_menu, height_item, scroll, scroll_top, scroll_bottom;

        if (item) {
            height_menu = list.offsetHeight;
            height_item = getWidthOrHeight(item, 'height', 'margin'); 
            scroll = list.scrollTop || 0;
            y = getOffset(item).top - getOffset(list).top + scroll;
            scroll_top = y;
            scroll_bottom = y - height_menu + height_item;

            if (y + height_item > height_menu + scroll) {
                list.scrollTop = scroll_bottom;
            } else if (y < scroll) {
                list.scrollTop = scroll_top;
            }
        }
    }

    var core_pnum = /[+-]?(?:\d*\.|)\d+(?:[eE][+-]?\d+|)/.source;
    var rnumnonpx = new RegExp("^(" + core_pnum + ")(?!px)[a-z%]+$", "i");

    function augmentWidthOrHeight(elem, name, extra, isBorderBox, styles) {
        var i = extra === (isBorderBox ? 'border' : 'content') ?
                4 :
                name === 'width' ? 1 : 0,

            val = 0,
            cssExpand = ['Top', 'Right', 'Bottom', 'Left'];

        function getStyleValue(name) {
            return parseFloat(styles[name]);
        }

        for (; i < 4; i += 2) {
            if (extra === 'margin') {
                val += getStyleValue(extra + cssExpand[i]);
            }

            if (isBorderBox) {
                if (extra === 'content') {
                    val -= getStyleValue('padding' + cssExpand[i]);
                }

                if (extra !== 'margin') {
                    val -= getStyleValue('border' + cssExpand[i] + 'Width');
                }
            } else {
                val += getStyleValue('padding' + cssExpand[i]);

                if (extra !== 'padding') {
                    val += getStyleValue('border' + cssExpand[i] + 'Width');
                }
            }
        }

        return val;
    }

    function getOffset(elem) {
        var docElem, win,
            box = elem.getBoundingClientRect(),
            doc = elem && elem.ownerDocument;

        if (!doc) {
            return;
        }

        docElem = doc.documentElement;
        win = getWindow(doc);

        return {
            top: box.top + win.pageYOffset - docElem.clientTop,
            left: box.left + win.pageXOffset - docElem.clientLeft
        };
    }

    function getWindow(elem) {
        return elem != null && elem === elem.window ? elem : elem.nodeType === 9 && elem.defaultView;
    }

    function getWidthOrHeight(elem, name, extra) {

        var valueIsBorderBox = true,
            val = name === 'width' ? elem.offsetWidth : elem.offsetHeight,
            styles = window.getComputedStyle(elem, null),

            isBorderBox = false; 

        if (val <= 0 || val == null) {
            val = styles[name];

            if (val < 0 || val == null) {
                val = elem.style[name];
            }

            if (rnumnonpx.test(val)) {
                return val;
            }

            val = parseFloat(val) || 0;
        }

        return val + augmentWidthOrHeight(elem, name, extra || ( isBorderBox ? "border" : "content" ), valueIsBorderBox, styles);
    }

    function groupsIsEmpty(groups) {
        for (var k in groups) {
            if (groups.hasOwnProperty(k) && groups[k].length) {
                return false;
            }
        }
        return true;
    }

    function intersection(xArr, yArr, xFilter, yFilter, invert) {
        var i, j, n, filteredX, filteredY, out = invert ? [].concat(xArr) : [];

        for (i = 0, n = xArr.length; i < xArr.length; i++) {
            filteredX = xFilter ? xFilter(xArr[i]) : xArr[i];

            for (j = 0; j < yArr.length; j++) {
                filteredY = yFilter ? yFilter(yArr[j]) : yArr[j];

                if (angular.equals(filteredX, filteredY, xArr, yArr, i, j)) {
                    invert ? out.splice(i + out.length - n, 1) : out.push(yArr[j]);
                    break;
                }
            }
        }
        return out;
    }

    function getValue(valueName, item, scope, getter) {
        var locals = {};

        valueName.split('.').reduce(function (previousValue, currentItem, index, arr) {
            return previousValue[currentItem] = index < arr.length - 1 ? {} : item;
        }, locals);

        return getter(scope, locals);
    }

    return {
        contains: contains,
        bindFocusBlur: bindFocusBlur,
        scrollActiveOption: scrollActiveOption,
        groupsIsEmpty: groupsIsEmpty,
        getValue: getValue,
        intersection: intersection
    }
}]);

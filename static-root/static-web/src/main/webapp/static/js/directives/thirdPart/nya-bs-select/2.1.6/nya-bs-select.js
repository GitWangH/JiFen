nyaBsSelect.directive('nyaBsSelect', ['$parse', '$document', '$timeout', '$compile', 'nyaBsConfig', function ($parse, $document, $timeout, $compile, nyaBsConfig) {

  var DEFAULT_NONE_SELECTION = '';

  var DROPDOWN_TOGGLE = '<button class="btn btn-default dropdown-toggle" type="button">' +
    '<span class="pull-left filter-option"></span>' +
    '<span class="pull-left special-title"></span>' +
    '&nbsp;' +
    '<span class="caret"></span>' +
    '</button>';

  var DROPDOWN_CONTAINER = '<div class="dropdown-menu open"></div>';

  var SEARCH_BOX = '<div class="bs-searchbox">' +
    '<input type="text" class="form-control">' +
    '</div>';

  var DROPDOWN_MENU = '<ul class="dropdown-menu inner"></ul>';

  var NO_SEARCH_RESULT = '<li class="no-search-result"><span>没有找到匹配条件的项</span></li>';

  var ACTIONS_BOX = '<div class="bs-actionsbox">' +
    '<div class="btn-group btn-group-sm btn-block">' +
    '<button class="actions-btn bs-select-all btn btn-default">SELECT ALL</button>' +
    '<button class="actions-btn bs-deselect-all btn btn-default">DESELECT ALL</button>' +
    '</div>' +
    '</div>';

  return {
    restrict: 'ECA',
    require: ['ngModel', 'nyaBsSelect'],
    controller: 'nyaBsSelectCtrl',
    compile: function nyaBsSelectCompile (tElement, tAttrs){
      tElement.addClass('btn-group');
      /**
       * get the default text when nothing is selected. can be template
       * @param scope, if provided, will try to compile template with given scope, will not attempt to compile the pure text.
       * @returns {*}
       */
      var getDefaultNoneSelectionContent = function(scope) {
        var content;

        if(tAttrs.titleTpl) {
          content = jqLite(tAttrs.titleTpl);
        } else if(tAttrs.title) {
          content = document.createTextNode(tAttrs.title);
        } else if(localizedText.defaultNoneSelectionTpl){
          content = jqLite(localizedText.defaultNoneSelectionTpl);
        } else if(localizedText.defaultNoneSelection) {
          content = document.createTextNode(localizedText.defaultNoneSelection);
        } else {
          content = document.createTextNode(DEFAULT_NONE_SELECTION);
        }

        if(scope && (tAttrs.titleTpl || localizedText.defaultNoneSelectionTpl)) {
          return $compile(content)(scope);
        }

        return content;
      };

      var options = tElement.children(),
        dropdownToggle = jqLite(DROPDOWN_TOGGLE),
        dropdownContainer = jqLite(DROPDOWN_CONTAINER),
        dropdownMenu = jqLite(DROPDOWN_MENU),
        searchBox,
        noSearchResult,
        actionsBox,
        classList,
        length,
        index,
        liElement,
        localizedText = nyaBsConfig,
        isMultiple = typeof tAttrs.multiple !== 'undefined',
        nyaBsOptionValue;

      classList = getClassList(tElement[0]);
      classList.forEach(function(className) {
        if(/btn-(?:primary|info|success|warning|danger|inverse)/.test(className)) {
          tElement.removeClass(className);
          dropdownToggle.removeClass('btn-default');
          dropdownToggle.addClass(className);
        }

        if(/btn-(?:lg|sm|xs)/.test(className)) {
          tElement.removeClass(className);
          dropdownToggle.addClass(className);
        }

        if(className === 'form-control') {
          dropdownToggle.addClass(className);
        }
      });

      dropdownMenu.append(options);

      length = options.length;
      for(index = 0; index < length; index++) {
        liElement = options.eq(index);
        if(liElement.hasClass('nya-bs-option') || liElement.attr('nya-bs-option')) {
          liElement.find('a').attr('tabindex', '0');
          nyaBsOptionValue = liElement.attr('value');
          if(angular.isString(nyaBsOptionValue) && nyaBsOptionValue !== '') {
            liElement.attr('data-value', nyaBsOptionValue);
            liElement.removeAttr('value');
          }
        }
      }

      if(tAttrs.liveSearch === 'true') {
        searchBox = jqLite(SEARCH_BOX);

        if(tAttrs.noSearchTitle) {
            NO_SEARCH_RESULT = NO_SEARCH_RESULT.replace('没有找到匹配条件的项', tAttrs.noSearchTitle);
        } else if (tAttrs.noSearchTitleTpl) {
            NO_SEARCH_RESULT = NO_SEARCH_RESULT.replace('没有找到匹配条件的项', tAttrs.noSearchTitleTpl);
        }else {
          if(localizedText.noSearchResultTpl) {
            NO_SEARCH_RESULT = NO_SEARCH_RESULT.replace('没有找到匹配条件的项', localizedText.noSearchResultTpl);
          } else if(localizedText.noSearchResult) {
            NO_SEARCH_RESULT = NO_SEARCH_RESULT.replace('没有找到匹配条件的项', localizedText.noSearchResult);
          }
        }

        noSearchResult = jqLite(NO_SEARCH_RESULT);
        dropdownContainer.append(searchBox);
        dropdownMenu.append(noSearchResult);
      }

      if (tAttrs.actionsBox === 'true' && isMultiple) {
        if (localizedText.selectAllTpl) {
          ACTIONS_BOX = ACTIONS_BOX.replace('SELECT ALL', localizedText.selectAllTpl);
        } else if (localizedText.selectAll) {
          ACTIONS_BOX = ACTIONS_BOX.replace('SELECT ALL', localizedText.selectAll);
        }

        if (localizedText.deselectAllTpl) {
          ACTIONS_BOX = ACTIONS_BOX.replace('DESELECT ALL', localizedText.deselectAllTpl);
        } else if (localizedText.selectAll) {
          ACTIONS_BOX = ACTIONS_BOX.replace('DESELECT ALL', localizedText.deselectAll);
        }

        actionsBox = jqLite(ACTIONS_BOX);
        dropdownContainer.append(actionsBox);
      }

      jqLite(dropdownToggle[0].querySelector('.special-title')).append(getDefaultNoneSelectionContent());

      dropdownContainer.append(dropdownMenu);

      tElement.append(dropdownToggle);
      tElement.append(dropdownContainer);

      return function nyaBsSelectLink ($scope, $element, $attrs, ctrls) {
        var ngCtrl = ctrls[0],
          nyaBsSelectCtrl = ctrls[1],
          liHeight,
          isDisabled = false,
          previousTabIndex,
          valueExpFn,
          valueExpGetter = $parse(nyaBsSelectCtrl.valueExp),
          isMultiple = typeof $attrs.multiple !== 'undefined';

        var dropdownToggle = jqLite($element[0].querySelector('.dropdown-toggle')),
          dropdownContainer = dropdownToggle.next(),
          dropdownMenu = jqLite(dropdownContainer[0].querySelector('.dropdown-menu.inner')),
          searchBox = jqLite(dropdownContainer[0].querySelector('.bs-searchbox')),
          noSearchResult = jqLite(dropdownMenu[0].querySelector('.no-search-result')),
          actionsBox = jqLite(dropdownContainer[0].querySelector('.bs-actionsbox'));
        if(nyaBsSelectCtrl.valueExp) {
          valueExpFn = function(scope, locals) {
            return valueExpGetter(scope, locals);
          };
        }

        nyaBsSelectCtrl.setId($element.attr('id'));

        if (isMultiple) {
          nyaBsSelectCtrl.isMultiple = true;

          ngCtrl.$isEmpty = function(value) {
            return !value || value.length === 0;
          };
        }
        if(typeof $attrs.disabled !== 'undefined') {
          $scope.$watch($attrs.disabled, function(disabled){
            if(disabled) {
              dropdownToggle.addClass('disabled');
              dropdownToggle.attr('disabled', 'disabled');
              previousTabIndex = dropdownToggle.attr('tabindex');
              dropdownToggle.attr('tabindex', '-1');
              isDisabled = true;
            } else {
              dropdownToggle.removeClass('disabled');
              dropdownToggle.removeAttr('disabled');
              if(previousTabIndex) {
                dropdownToggle.attr('tabindex', previousTabIndex);
              } else {
                dropdownToggle.removeAttr('tabindex');
              }
              isDisabled = false;
            }
          });
        }

        /**
         * Do some check on modelValue. remove no existing value
         * @param values
         * @param deepWatched
         */
        nyaBsSelectCtrl.onCollectionChange = function (values, deepWatched) {
          var valuesForSelect = [],
            index,
            modelValueChanged = false,
            modelValue = deepCopy(ngCtrl.$modelValue);

          if(!modelValue) {
            return;
          }

          /**
           * Behavior change, since 2.1.0, we don't want to reset model to null or empty array when options' collection is not prepared.
           */
          if(Array.isArray(values) && values.length > 0) {
            if(valueExpFn) {
              for(index = 0; index < values.length; index++) {
                valuesForSelect.push(valueExpFn($scope, values[index]));
              }
            } else {
              for(index = 0; index < values.length; index++) {
                if(nyaBsSelectCtrl.valueIdentifier) {
                  valuesForSelect.push(values[index][nyaBsSelectCtrl.valueIdentifier]);
                } else if(nyaBsSelectCtrl.keyIdentifier) {
                  valuesForSelect.push(values[index][nyaBsSelectCtrl.keyIdentifier]);
                }
              }

            }

            if(isMultiple) {
              for(index = 0; index < modelValue.length; index++) {
                if(!contains(valuesForSelect, modelValue[index])) {
                  modelValueChanged = true;
                  modelValue.splice(index, 1);
                  index--;
                }
              }

              if(modelValueChanged) {

                ngCtrl.$setViewValue(modelValue);

                updateButtonContent();
              }

            } else {
              if(!contains(valuesForSelect, modelValue)) {
                modelValue = valuesForSelect[0];

                ngCtrl.$setViewValue(modelValue);

                updateButtonContent();
              }
            }

          }

          /**
           * if we set deep-watch="true" on nyaBsOption directive,
           * we need to refresh dropdown button content whenever a change happened in collection.
           */
          if(deepWatched) {
            updateButtonContent();
          }

        };


        dropdownMenu.on('click', function menuEventHandler (event) {
          if(isDisabled) {
            return;
          }
          if(jqLite(event.target).hasClass('dropdown-header')) {
            return;
          }
          var nyaBsOptionNode = filterTarget(event.target, dropdownMenu[0], 'nya-bs-option'),
            nyaBsOption;

          if(nyaBsOptionNode !== null) {
            nyaBsOption = jqLite(nyaBsOptionNode);
            if(nyaBsOption.hasClass('disabled')) {
              return;
            }
            selectOption(nyaBsOption);
          }
        });

        var outClick = function(event) {
          if(filterTarget(event.target, $element.parent()[0], $element[0]) === null) {
            if($element.hasClass('open')) {
              $element.triggerHandler('blur');
            }
            $element.removeClass('open');
          }
        };
        $document.on('click', outClick);
        dropdownToggle.on('blur', function() {
          if(!$element.hasClass('open')) {
            $element.triggerHandler('blur');
          }
        });
        dropdownToggle.on('click', function() {
          var nyaBsOptionNode;
          $element.toggleClass('open');
          if($element.hasClass('open') && typeof liHeight === 'undefined') {
            calcMenuSize();
          }
          if($attrs.liveSearch === 'true' && $element.hasClass('open')) {
            searchBox.children().eq(0)[0].focus();
            nyaBsOptionNode = findFocus(true);
            if(nyaBsOptionNode) {
              dropdownMenu.children().removeClass('active');
              jqLite(nyaBsOptionNode).addClass('active');
            }
          } else if($element.hasClass('open')) {
            nyaBsOptionNode = findFocus(true);
            if(nyaBsOptionNode) {
              setFocus(nyaBsOptionNode);
            }
          }
        });

        if ($attrs.actionsBox === 'true' && isMultiple) {
          actionsBox.find('button').eq(0).on('click', function () {
            setAllOptions(true);
          });
          actionsBox.find('button').eq(1).on('click', function () {
            setAllOptions(false);
          });
        }


        if($attrs.liveSearch === 'true') {
          searchBox.children().on('input', function(){
        	console.log('输入的值改变了');
            var searchKeyword = searchBox.children().val(),
              found = 0,
              options = dropdownMenu.children(),
              length = options.length,
              index,
              option,
              nyaBsOptionNode;

            if(searchKeyword) {
            	console.log('searchKeyword');
            	console.log(searchKeyword);
              for(index = 0; index < length; index++) {
                option = options.eq(index);
                if(option.hasClass('nya-bs-option')) {
                  if(!hasKeyword(option.find('a'), searchKeyword)) {
                    option.addClass('not-match');
                  } else {
                    option.removeClass('not-match');
                    found++;
                  }
                }
              }

              if(found === 0) {
                noSearchResult.addClass('show');
              } else {
                noSearchResult.removeClass('show');
              }
            } else {
              for(index = 0; index < length; index++) {
                option = options.eq(index);
                if(option.hasClass('nya-bs-option')) {
                  option.removeClass('not-match');
                }
              }
              noSearchResult.removeClass('show');
            }

            nyaBsOptionNode = findFocus(true);

            if(nyaBsOptionNode) {
              options.removeClass('active');
              jqLite(nyaBsOptionNode).addClass('active');
            }

          });
        }



        ngCtrl.$render = function() {
          var modelValue = ngCtrl.$modelValue,
            index,
            bsOptionElements = dropdownMenu.children(),
            length = bsOptionElements.length,
            value;
          if(typeof modelValue === 'undefined') {
            for(index = 0; index < length; index++) {
              if(bsOptionElements.eq(index).hasClass('nya-bs-option')) {
                bsOptionElements.eq(index).removeClass('selected');
              }
            }
          } else {
            for(index = 0; index < length; index++) {
              if(bsOptionElements.eq(index).hasClass('nya-bs-option')) {

                value = getOptionValue(bsOptionElements.eq(index));
                if(isMultiple) {
                  if(contains(modelValue, value)) {
                    bsOptionElements.eq(index).addClass('selected');
                  } else {
                    bsOptionElements.eq(index).removeClass('selected');
                  }
                } else {
                  if(deepEquals(modelValue, value)) {
                    bsOptionElements.eq(index).addClass('selected');
                  } else {
                    bsOptionElements.eq(index).removeClass('selected');
                  }
                }

              }
            }
          }
          updateButtonContent();
        };

        $element.on('keydown', function(event){
        	console.log(1112222);
          var keyCode = event.keyCode;

          if(keyCode !== 27 && keyCode !== 13 && keyCode !== 38 && keyCode !== 40) {
            return;
          }

          event.preventDefault();
          if(isDisabled) {
            event.stopPropagation();
            return;
          }
          var toggleButton = filterTarget(event.target, $element[0], dropdownToggle[0]),
            menuContainer,
            searchBoxContainer,
            liElement,
            nyaBsOptionNode;

          if($attrs.liveSearch === 'true') {
        	  console.log($element[0]);
        	  console.log(searchBox[0]);
            searchBoxContainer = filterTarget(event.target, $element[0], searchBox[0]);
          } else {
        	  console.log(112);
        	  console.log($element[0]);
        	  console.log(searchBox[0]);
            menuContainer = filterTarget(event.target, $element[0], dropdownContainer[0])
          }

          if(toggleButton) {
            if((keyCode === 13 || keyCode === 38 || keyCode === 40) && !$element.hasClass('open')) {

              event.stopPropagation();

              $element.addClass('open');

              if(typeof liHeight === 'undefined') {
                calcMenuSize();
              }

              if($attrs.liveSearch === 'true') {
                searchBox.children().eq(0)[0].focus();
                nyaBsOptionNode = findFocus(true);
                if(nyaBsOptionNode) {
                  dropdownMenu.children().removeClass('active');
                  jqLite(nyaBsOptionNode).addClass('active');
                }
              } else {
                nyaBsOptionNode = findFocus(true);
                if(nyaBsOptionNode) {
                  setFocus(nyaBsOptionNode);
                }
              }
            }

          } else if(menuContainer) {

            if(keyCode === 27) {
              dropdownToggle[0].focus();
              if($element.hasClass('open')) {
                $element.triggerHandler('blur');
              }
              $element.removeClass('open');
              event.stopPropagation();

            } else if(keyCode === 38) {
              event.stopPropagation();
              nyaBsOptionNode = findNextFocus(event.target.parentNode, 'previousSibling');
              if(nyaBsOptionNode) {
                setFocus(nyaBsOptionNode);
              } else {
                nyaBsOptionNode = findFocus(false);
                if(nyaBsOptionNode) {
                  setFocus(nyaBsOptionNode);
                }
              }
            } else if(keyCode === 40) {
              event.stopPropagation();
              nyaBsOptionNode = findNextFocus(event.target.parentNode, 'nextSibling');
              if(nyaBsOptionNode) {
                setFocus(nyaBsOptionNode);
              } else {
                nyaBsOptionNode = findFocus(true);
                if(nyaBsOptionNode) {
                  setFocus(nyaBsOptionNode);
                }
              }
            } else if(keyCode === 13) {
              event.stopPropagation();
              liElement = jqLite(event.target.parentNode);
              if(liElement.hasClass('nya-bs-option')) {
                selectOption(liElement);
                if(!isMultiple) {
                  dropdownToggle[0].focus();
                }
              }
            }
          } else if(searchBoxContainer) {
            if(keyCode === 27) {
              dropdownToggle[0].focus();
              $element.removeClass('open');
              event.stopPropagation();
            } else if(keyCode === 38) {
              event.stopPropagation();

              liElement = findActive();
              if(liElement) {
                nyaBsOptionNode = findNextFocus(liElement[0], 'previousSibling');
                if(nyaBsOptionNode) {
                  liElement.removeClass('active');
                  jqLite(nyaBsOptionNode).addClass('active');
                } else {
                  nyaBsOptionNode = findFocus(false);
                  if(nyaBsOptionNode) {
                    liElement.removeClass('active');
                    jqLite(nyaBsOptionNode).addClass('active');
                  }
                }
              }

            } else if(keyCode === 40) {
              event.stopPropagation();

              liElement = findActive();
              if(liElement) {
                nyaBsOptionNode = findNextFocus(liElement[0], 'nextSibling');
                if(nyaBsOptionNode) {
                  liElement.removeClass('active');
                  jqLite(nyaBsOptionNode).addClass('active');
                } else {
                  nyaBsOptionNode = findFocus(true);
                  if(nyaBsOptionNode) {
                    liElement.removeClass('active');
                    jqLite(nyaBsOptionNode).addClass('active');
                  }
                }
              }
            } else if(keyCode === 13) {
              liElement = findActive();
              if(liElement) {
                selectOption(liElement);
                if(!isMultiple) {
                  dropdownToggle[0].focus();
                }
              }
            }
          }
        });

        function findActive() {
          var list = dropdownMenu.children(),
            i, liElement,
            length = list.length;
          for(i = 0; i < length; i++) {
            liElement = list.eq(i);
            if(liElement.hasClass('active') && liElement.hasClass('nya-bs-option') && !liElement.hasClass('not-match')) {
              return liElement;
            }
          }
          return null;
        }

        /**
         * setFocus on a nya-bs-option element. it actually set focus on its child anchor element.
         * @param elem a nya-bs-option element.
         */
        function setFocus(elem) {
          var childList = elem.childNodes,
            length = childList.length,
            child;
          for(var i = 0; i < length; i++) {
            child = childList[i];
            if(child.nodeType === 1 && child.tagName.toLowerCase() === 'a') {
              child.focus();
              break;
            }
          }
        }

        function findFocus(fromFirst) {
          var firstLiElement;
          if(fromFirst) {
            firstLiElement = dropdownMenu.children().eq(0);
          } else {
            firstLiElement = dropdownMenu.children().eq(dropdownMenu.children().length - 1);
          }

          for(var i = 0; i < dropdownMenu.children().length; i++) {
            var childElement = dropdownMenu.children().eq(i);
            if (!childElement.hasClass('not-match') && childElement.hasClass('selected')) {
              return dropdownMenu.children().eq(i)[0];
            }
          }

          if(firstLiElement.hasClass('nya-bs-option') && !firstLiElement.hasClass('disabled') && !firstLiElement.hasClass('not-match')) {
            return firstLiElement[0];
          } else {
            if(fromFirst) {
              return findNextFocus(firstLiElement[0], 'nextSibling');
            } else {
              return findNextFocus(firstLiElement[0], 'previousSibling');
            }
          }
        }

        /**
         * find next focusable element on direction
         * @param from the element traversed from
         * @param direction can be 'nextSibling' or 'previousSibling'
         * @returns the element if found, otherwise return null.
         */
        function findNextFocus(from, direction) {
          if(from && !hasClass(from, 'nya-bs-option')) {
            return;
          }
          var next = from;
          while ((next = sibling(next, direction)) && next.nodeType) {
            if(hasClass(next,'nya-bs-option') && !hasClass(next, 'disabled') && !hasClass(next, 'not-match')) {
              return next
            }
          }
          return null;
        }

        /**
         *
         */
        function setAllOptions(selectAll) {
          if (!isMultiple || isDisabled)
            return;

          var liElements,
            wv,
            viewValue;

          liElements = dropdownMenu[0].querySelectorAll('.nya-bs-option');
          if (liElements.length > 0) {
            wv = ngCtrl.$viewValue;

            viewValue = Array.isArray(wv) ? deepCopy(wv) : [];

            for (var i = 0; i < liElements.length; i++) {
              var nyaBsOption = jqLite(liElements[i]);
              if (nyaBsOption.hasClass('disabled'))
                continue;

              var value, index;

              value = getOptionValue(nyaBsOption);

              if (typeof value !== 'undefined') {
                index = indexOf(viewValue, value);
                if (selectAll && index == -1) {
                  viewValue.push(value);
                  nyaBsOption.addClass('selected');
                } else if (!selectAll && index != -1) {
                  viewValue.splice(index, 1);
                  nyaBsOption.removeClass('selected');
                }
              }
            }

            ngCtrl.$setViewValue(viewValue);
            $scope.$digest();

            updateButtonContent();
          }
        }

        /**
         * select an option represented by nyaBsOption argument. Get the option's value and update model.
         * if isMultiple = true, doesn't close dropdown menu. otherwise close the menu.
         * @param nyaBsOption the jqLite wrapped `nya-bs-option` element.
         */
        function selectOption(nyaBsOption) {
          var value,
            viewValue,
            wv = ngCtrl.$viewValue,
            index;

          value = getOptionValue(nyaBsOption);

          if(typeof value !== 'undefined') {
            if(isMultiple) {
              viewValue = Array.isArray(wv) ? deepCopy(wv) : [];
              index = indexOf(viewValue, value);
              if(index === -1) {
                viewValue.push(value);
                nyaBsOption.addClass('selected');

              } else {
                viewValue.splice(index, 1);
                nyaBsOption.removeClass('selected');

              }

            } else {
              dropdownMenu.children().removeClass('selected');
              viewValue = value;
              nyaBsOption.addClass('selected');

            }
          }
          ngCtrl.$setViewValue(viewValue);
          $scope.$digest();

          if(!isMultiple) {
            if($element.hasClass('open')) {
              $element.triggerHandler('blur');
            }
            $element.removeClass('open');
            dropdownToggle[0].focus();
          }
          updateButtonContent();
        }

        /**
         * get a value of current nyaBsOption. according to different setting.
         * - if `nya-bs-option` directive is used to populate options and a `value` attribute is specified. use expression of the attribute value.
         * - if `nya-bs-option` directive is used to populate options and no other settings, use the valueIdentifier or keyIdentifier to retrieve value from scope of current nyaBsOption.
         * - if `nya-bs-option` class is used on static options. use literal value of the `value` attribute.
         * @param nyaBsOption a jqLite wrapped `nya-bs-option` element
         */
        function getOptionValue(nyaBsOption) {
          var scopeOfOption;
          if(valueExpFn) {
            scopeOfOption = nyaBsOption.data('isolateScope');
            return valueExpFn(scopeOfOption);
          } else {
            if(nyaBsSelectCtrl.valueIdentifier || nyaBsSelectCtrl.keyIdentifier) {
              scopeOfOption = nyaBsOption.data('isolateScope');
              return scopeOfOption[nyaBsSelectCtrl.valueIdentifier] || scopeOfOption[nyaBsSelectCtrl.keyIdentifier];
            } else {
              return nyaBsOption.attr('data-value');
            }
          }

        }

        function getOptionText(nyaBsOption) {
          var item = nyaBsOption.find('a');
          if(item.children().length === 0 || item.children().eq(0).hasClass('check-mark')) {
            return item[0].firstChild.cloneNode(false);
          } else {
            return item.children().eq(0)[0].cloneNode(true);
          }
        }

        function updateButtonContent() {
          var viewValue = ngCtrl.$viewValue || '';
          $element.triggerHandler('change');
          var filterOption = jqLite(dropdownToggle[0].querySelector('.filter-option'));
          var specialTitle = jqLite(dropdownToggle[0].querySelector('.special-title'));
          if(typeof viewValue === 'undefined') {
            /**
             * Select empty option when model is undefined.
             */
            dropdownToggle.addClass('show-special-title');
            filterOption.empty();
            return;
          }
          if(isMultiple && viewValue.length === 0) {
            dropdownToggle.addClass('show-special-title');
            filterOption.empty();
          } else {
            dropdownToggle.removeClass('show-special-title');
            $timeout(function() {
              var bsOptionElements = dropdownMenu.children(),
                value,
                nyaBsOption,
                index,
                length = bsOptionElements.length || viewValue.length,
                optionTitle,
                selection = [],
                optionScopes = [],
                match,
                count,
                clone;
              
              
              if(isMultiple && $attrs.selectedTextFormat === 'count') {
                count = 1;
              } else if(isMultiple && $attrs.selectedTextFormat && (match = $attrs.selectedTextFormat.match(/\s*count\s*>\s*(\d+)\s*/))) {
                count = parseInt(match[1], 10);
              }

              if((typeof count !== 'undefined') && viewValue.length > count) {
                filterOption.empty();
                if(localizedText.numberItemSelectedTpl) {
                  filterOption.append(jqLite(localizedText.numberItemSelectedTpl.replace('%d', viewValue.length)));
                } else if(localizedText.numberItemSelected) {
                  filterOption.append(document.createTextNode(localizedText.numberItemSelected.replace('%d', viewValue.length)));
                } else {
                  filterOption.append(document.createTextNode(viewValue.length + ' items selected'));
                }
                return;
              }

              for(index = 0; index < length; index++) {
                nyaBsOption = bsOptionElements.eq(index);
                if(nyaBsOption.hasClass('nya-bs-option')) {
                  value = getOptionValue(nyaBsOption);
                  if(isMultiple) {
                    if(Array.isArray(viewValue) && contains(viewValue, value)) {
                      optionTitle = nyaBsOption.attr('title');
                      if(optionTitle) {
                        selection.push(document.createTextNode(optionTitle));
                      } else {
                        selection.push(getOptionText(nyaBsOption));
                        optionScopes.push(nyaBsOption.data('isolateScope'))
                      }

                    }
                  } else {
                    if(deepEquals(viewValue, value)) {
                      optionTitle = nyaBsOption.attr('title');
                      if(optionTitle) {
                        selection.push(document.createTextNode(optionTitle));
                      } else {
                        selection.push(getOptionText(nyaBsOption));
                        optionScopes.push(nyaBsOption.data('isolateScope'))
                      }
                    }
                  }

                }
              }

              if(selection.length === 0) {
                filterOption.empty();
                dropdownToggle.addClass('show-special-title');
              } else if(selection.length === 1) {
                dropdownToggle.removeClass('show-special-title');
                filterOption.empty();
                clone = $compile (selection[0])(optionScopes[0]);
                filterOption.append(clone);
              } else {
                dropdownToggle.removeClass('show-special-title');
                filterOption.empty();
                for(index = 0; index < selection.length; index++) {
                  clone = $compile (selection[index])(optionScopes[index]);
                  filterOption.append(clone);
                  if(index < selection.length -1) {
                    filterOption.append(document.createTextNode(', '));
                  }
                }
              }

            });
          }

        }

        function calcMenuSize(){

          var liElements = dropdownMenu.find('li'),
            length = liElements.length,
            liElement,
            i;
          for(i = 0; i < length; i++) {
            liElement = liElements.eq(i);
            if(liElement.hasClass('nya-bs-option') || liElement.attr('nya-bs-option')) {
              liHeight = liElement[0].clientHeight;
              break;
            }
          }

          if(/\d+/.test($attrs.size)) {
            var dropdownSize = parseInt($attrs.size, 10);
            dropdownMenu.css('max-height', (dropdownSize * liHeight) + 'px');
            dropdownMenu.css('overflow-y', 'auto');
          }

        }

        $scope.$on('$destroy', function() {
          dropdownMenu.off();
          dropdownToggle.off();
          if (searchBox.off) searchBox.off();
          $document.off('click', outClick);
        });

      };
    }
  };
}]);

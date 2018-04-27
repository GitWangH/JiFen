nyaBsSelect.directive('nyaBsOption', ['$parse', function($parse){

  var BS_OPTION_REGEX = /^\s*(?:([\$\w][\$\w]*)|(?:\(\s*([\$\w][\$\w]*)\s*,\s*([\$\w][\$\w]*)\s*\)))\s+in\s+([\s\S]+?)(?:\s+group\s+by\s+([\s\S]+?))?(?:\s+track\s+by\s+([\s\S]+?))?\s*$/;

  return {
    restrict: 'A',
    transclude: 'element',
    priority: 1000,
    terminal: true,
    require: ['^nyaBsSelect', '^ngModel'],
    compile: function nyaBsOptionCompile (tElement, tAttrs) {

      var expression = tAttrs.nyaBsOption;
      var nyaBsOptionEndComment = document.createComment(' end nyaBsOption: ' + expression + ' ');
      var match = expression.match(BS_OPTION_REGEX);

      if(!match) {
        throw new Error('invalid expression');
      }

      var valueExp = tAttrs.value,
        valueExpGetter = valueExp ? $parse(valueExp) : null;

      var valueIdentifier = match[3] || match[1],
        keyIdentifier = match[2],
        collectionExp = match[4],
        groupByExpGetter = match[5] ? $parse(match[5]) : null,
        trackByExp = match[6];

      var trackByIdArrayFn,
        trackByIdObjFn,
        trackByIdExpFn,
        trackByExpGetter;
      var hashFnLocals = {$id: hashKey};
      var groupByFn, locals = {};

      if(trackByExp) {
        trackByExpGetter = $parse(trackByExp);
      } else {
        trackByIdArrayFn = function(key, value) {
          return hashKey(value);
        };
        trackByIdObjFn = function(key) {
          return key;
        };
      }
      return function nyaBsOptionLink($scope, $element, $attr, ctrls, $transclude) {

        var nyaBsSelectCtrl = ctrls[0],
          ngCtrl = ctrls[1],
          valueExpFn,
          deepWatched,
          valueExpLocals = {};

        if(trackByExpGetter) {
          trackByIdExpFn = function(key, value, index) {
            if (keyIdentifier) {
              hashFnLocals[keyIdentifier] = key;
            }
            hashFnLocals[valueIdentifier] = value;
            hashFnLocals.$index = index;
            return trackByExpGetter($scope, hashFnLocals);
          };
        }

        if(groupByExpGetter) {
          groupByFn = function(key, value) {
            if(keyIdentifier) {
              locals[keyIdentifier] = key;
            }
            locals[valueIdentifier] = value;
            return groupByExpGetter($scope, locals);
          }
        }

        if(keyIdentifier) {
          nyaBsSelectCtrl.keyIdentifier = keyIdentifier;
        }
        if(valueIdentifier) {
          nyaBsSelectCtrl.valueIdentifier = valueIdentifier;
        }

        if(valueExpGetter) {
          nyaBsSelectCtrl.valueExp = valueExp;
          valueExpFn = function(key, value) {
            if(keyIdentifier) {
              valueExpLocals[keyIdentifier] = key;
            }
            valueExpLocals[valueIdentifier] = value;
            return valueExpGetter($scope, valueExpLocals);
          }

        }


        var lastBlockMap = createMap();

        if($attr.deepWatch === 'true') {
          deepWatched = true;
          $scope.$watch(collectionExp, nyaBsOptionAction, true);
        } else {
          deepWatched = false;
          $scope.$watchCollection(collectionExp, nyaBsOptionAction);
        }

        function nyaBsOptionAction(collection) {
          var index,

            previousNode = $element[0],     

            key, value,
            trackById,
            trackByIdFn,
            collectionKeys,
            collectionLength,
            nextBlockMap = createMap(),
            nextBlockOrder,
            block,
            groupName,
            nextNode,
            group,
            lastGroup,

            removedClone, 

            values = [],
            valueObj; 

          if(groupByFn) {
            group = [];
          }

          if(isArrayLike(collection)) {
            collectionKeys = collection;
            trackByIdFn = trackByIdExpFn || trackByIdArrayFn;
          } else {
            trackByIdFn = trackByIdExpFn || trackByIdObjFn;
            collectionKeys = [];
            for (var itemKey in collection) {
              if (collection.hasOwnProperty(itemKey) && itemKey.charAt(0) != '$') {
                collectionKeys.push(itemKey);
              }
            }
            collectionKeys.sort();
          }
          collectionLength = collectionKeys.length;
          nextBlockOrder = new Array(collectionLength);

          for(index = 0; index < collectionLength; index++) {
            key = (collection === collectionKeys) ? index : collectionKeys[index];
            value = collection[key];
            trackById = trackByIdFn(key, value, index);

            valueObj = {};
            if(keyIdentifier) {
              valueObj[keyIdentifier] = key;
            }

            valueObj[valueIdentifier] = value;
            values.push(valueObj);

            if(groupByFn) {
              groupName = groupByFn(key, value);
              if(group.indexOf(groupName) === -1 && groupName) {
                group.push(groupName);
              }
            }

            if(lastBlockMap[trackById]) {
              block = lastBlockMap[trackById];
              delete lastBlockMap[trackById];

              if(groupByFn) {
                block.group = groupName;
              }
              block.key = key;
              block.value = value;

              nextBlockMap[trackById] = block;
              nextBlockOrder[index] = block;
            } else if(nextBlockMap[trackById]) {
              nextBlockOrder.forEach(function(block) {
                if(block && block.scope) {
                  lastBlockMap[block.id] = block;
                }
              });
              throw new Error("Duplicates in a select are not allowed. Use 'track by' expression to specify unique keys.");
            } else {
              nextBlockOrder[index] = {id: trackById, scope: undefined, clone: undefined, key: key, value: value};
              nextBlockMap[trackById] = true;
              if(groupName) {
                nextBlockOrder[index].group = groupName;
              }
            }
          }

          if(group && group.length > 0) {

            nextBlockOrder = sortByGroup(nextBlockOrder, group, 'group');
          }

          for( var blockKey in lastBlockMap) {
            block = lastBlockMap[blockKey];
            removedClone = getBlockNodes(block.clone);
            removedClone.removeData('isolateScope');
            removedClone.remove();
            block.scope.$destroy();
          }

          for(index = 0; index < collectionLength; index++) {
            block = nextBlockOrder[index];
            if(block.scope) {

              nextNode = previousNode;
              if(getBlockStart(block) != nextNode) {
                jqLite(previousNode).after(block.clone);
              }
              previousNode = getBlockEnd(block);

              updateScope(block.scope, index, valueIdentifier, block.value, keyIdentifier, block.key, collectionLength, block.group);
            } else {
              $transclude(function nyaBsOptionTransclude(clone, scope) {
                setElementIsolateScope(clone, scope);

                block.scope = scope;

                var endNode = nyaBsOptionEndComment.cloneNode(false);
                clone[clone.length++] = endNode;

                jqLite(previousNode).after(clone);

                clone.addClass('nya-bs-option');

                if(valueExpFn) {
                  value = valueExpFn(block.key, block.value);
                } else {
                  value = block.value || block.key;
                }

                if(nyaBsSelectCtrl.isMultiple) {
                  if(Array.isArray(ngCtrl.$modelValue) && contains(ngCtrl.$modelValue, value)) {
                    clone.addClass('selected');
                  }
                } else {
                  if(deepEquals(value, ngCtrl.$modelValue)) {
                    clone.addClass('selected');
                  }
                }

                previousNode = endNode;
                block.clone = clone;
                nextBlockMap[block.id] = block;
                updateScope(block.scope, index, valueIdentifier, block.value, keyIdentifier, block.key, collectionLength, block.group);
              });

            }

            if(group) {
              if(!lastGroup || lastGroup !== block.group) {
                block.clone.addClass('first-in-group');
              } else {
                block.clone.removeClass('first-in-group');
              }

              lastGroup = block.group;

              block.clone.addClass('group-item');
            }
          }

          lastBlockMap = nextBlockMap;

          nyaBsSelectCtrl.onCollectionChange(values, deepWatched);
        }
      };
    }
  }
}]);

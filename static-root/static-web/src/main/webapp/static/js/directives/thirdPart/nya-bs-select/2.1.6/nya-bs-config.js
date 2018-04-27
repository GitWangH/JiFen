/**
 * A service for configuration. the configuration is shared globally.
 * Testing ci build --jpmckearin
 */
nyaBsSelect.provider('nyaBsConfig', function() {

  var locale = null;

  var defaultText = {
    'en-us': {
      defaultNoneSelection: '',
      noSearchResult: '没有找到匹配条件的项',
      numberItemSelected: '%d items selected',
      selectAll: '全选',
      deselectAll: '全不选'
    }
  };

  var interfaceText = deepCopy(defaultText);

  /**
   * Merge with default localized text.
   * @param localeId a string formatted as languageId-countryId
   * @param obj localized text object.
   */
  this.setLocalizedText = function(localeId, obj) {
    if(!localeId) {
      throw new Error('localeId must be a string formatted as languageId-countryId');
    }
    if(!interfaceText[localeId]) {
      interfaceText[localeId] = {};
    }
    interfaceText[localeId] = extend(interfaceText[localeId], obj);
  };

  /**
   * Force to use a special locale id. if localeId is null. reset to user-agent locale.
   * @param localeId a string formatted as languageId-countryId
   */
  this.useLocale = function(localeId) {
    locale = localeId;
  };

  /**
   * get the localized text according current locale or forced locale
   * @returns localizedText
   */
  this.$get = ['$locale', function($locale){
    var localizedText;
    if(locale) {
      localizedText = interfaceText[locale];
    } else {
      localizedText = interfaceText[$locale.id];
    }
    if(!localizedText) {
      localizedText = defaultText['en-us'];
    }
    return localizedText;
  }];

});

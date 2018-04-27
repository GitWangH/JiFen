
angular.module('huatek.services').service('i18n', function () {
    var self = this;
    this.setLanguage = function (language) {
        $.i18n.properties({
            name: 'messages',
            path: 'i18n/',
            mode: 'map',
            language: language,
            callback: function () {
                self.language = language;
            }
        });
    };
    this.setLanguage('zh_CN');
});
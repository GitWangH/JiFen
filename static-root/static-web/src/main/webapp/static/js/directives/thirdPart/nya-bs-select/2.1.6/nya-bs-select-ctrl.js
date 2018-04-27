
nyaBsSelect.controller('nyaBsSelectCtrl', function(){

  var self = this;

  self.keyIdentifier = null;
  self.valueIdentifier = null;

  self.isMultiple = false;

  self.onCollectionChange = function(){};

  self.setId = function(id) {
    self.id = id || 'id#' + Math.floor(Math.random() * 10000);
  };

});
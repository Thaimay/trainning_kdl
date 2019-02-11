'use strict';

angular
  .module('app.core')
  .controller('TopListController', function(TopListService, $scope) {

    this.initTop = function() {
      TopListService.settingDatePicker();
    };
});

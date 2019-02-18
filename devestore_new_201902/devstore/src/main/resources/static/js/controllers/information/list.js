'use strict';

angular
  .module('app.core')
  .controller('ImportantInformationController', function(ImportantInformationService, $scope) {

    this._getInformationId = function(target) {
      return $(target).data("information-id");
    }

    this.toRead = function ($event) {
      var targetId = this._getInformationId($event.currentTarget);
      ImportantInformationService.toRead(targetId, $event.currentTarget);
    };

    this.toNice = function ($event) {
      var targetId = this._getInformationId($event.target);
      ImportantInformationService.toNice(targetId, $event.target);
    };

      this.toDelete = function ($event) {
          var targetId = this._getInformationId($event.target);
          ImportantInformationService.toDelete(targetId, $event.target);
      };
  });

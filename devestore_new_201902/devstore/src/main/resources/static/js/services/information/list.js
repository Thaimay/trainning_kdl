'use strict';

/*
 * Contains a service to communicate with API
 */
angular
    .module('app.services')
    .factory('ImportantInformationService', importantInformationService);

function importantInformationService($http, BaseService) {
    var api = angular.copy(BaseService);
    var BASE_PATH = "sp/important/notice/";

    function toRead(id, target) {
      var $target = $(target);
      
      BaseService.post(BASE_PATH + "read/" + id);
      setTimeout(function() {
        location.reload();
      }, 300);
    };

    function toNice(id, target) {
      var $target = $(target);
      if (!$target.hasClass("isNice")) {
        BaseService.post(BASE_PATH + "nice/" + id);
        $target.addClass("isNice");
        setTimeout(function() {
          location.reload();
        }, 300);
      }
    };


    function toDelete(id, target) {
        var $target = $(target);
        BaseService.post(BASE_PATH + "delete/" + id);
        setTimeout(function() {
            location.reload();
        }, 300);
    };


    return {
    	toRead: toRead,
        toNice: toNice,
        toDelete: toDelete,
    };
};

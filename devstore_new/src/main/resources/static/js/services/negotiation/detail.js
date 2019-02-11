'use strict';
/*
 * Contains a service to communicate with API
 */
angular
  .module('app.services')
  .factory('NegotiationDetailService', negotiationDetailService);

function negotiationDetailService ($http, BaseService) {
  var api = angular.copy(BaseService);

  function init (id, $scope) {
    api.post('pc/negotiation/comment/list/' + id, {}).then(function (data) {
        $scope.commentList = data;
    });
  };

  function initRelatedNegotiation(id, $scope, $timeout) {
	  api.post('pc/negotiation/detail/find', { id: id, isInit : true }).then(function(data) {
		  $scope.relatedData = data;
		  $timeout(setCollapse, 1000);
	  });
  }

  function initRelatedProjectNegotiation(id, $scope, $timeout) {
	  api.post('pc/negotiation/detail/project', { negotiationIds: [id] }).then(function(data) {
		  $scope.relatedProjectData = data;
		  $timeout(setCollapse, 1000);
	  });
  }

  function setCollapse() {
      if ($('.j-dropdown').length) {
          let $dropdown = $('.j-dropdown');
          let $target;

          $dropdown.find('.j-dropdown-header').off('click');

          $dropdown.find('.j-dropdown-header').on({
              'click': function (event) {
                  $target = $(event.currentTarget);

                  if ($target.hasClass('isOpen')) {
                      handleDropDownState($target, false);
                  } else {
                      handleDropDownState($target, true);
                  }
              }
          })

          $('.j-dropdown-header.isOpen').next('.j-dropdown-body').show();

          let handleDropDownState = function ($target, doOpen) {
              if (doOpen) {
                  $target.addClass('isOpen');
                  $target.next('.j-dropdown-body').slideDown();
              } else {
                  $target.removeClass('isOpen');
                  $target.next('.j-dropdown-body').slideUp();
              }
          }
      }
  };

  function findRelatedNegotiation(data, $scope) {
	  api.post('pc/negotiation/detail/find', data).then(function(data) {
		  $scope.relatedData = data;
	  });
  }

  function addComment (comment, $scope) {
    api.post('pc/negotiation/comment/add', comment).then(function (data) {
      $scope.commentList = data;
    });
    $('.comment-text-input').val('');
  };

  function deleteComment (id, $scope) {
    api.post('pc/negotiation/comment/delete/' + id, {}).then(function (data) {
      $scope.commentList = data;
    });
  }

  return {
    init: init,
    addComment: addComment,
    deleteComment: deleteComment,
    initRelatedNegotiation: initRelatedNegotiation,
    findRelatedNegotiation: findRelatedNegotiation,
    initRelatedProjectNegotiation: initRelatedProjectNegotiation,
  };
};

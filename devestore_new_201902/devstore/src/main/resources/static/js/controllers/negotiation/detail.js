'use strict';
angular
  .module('app.core')
  .controller('NegotiationDetailController', function (BaseService, NegotiationDetailService, $scope, $timeout) {
    this.init = function () {
      NegotiationDetailService.init($('.negotiation-id').val(), $scope);
      NegotiationDetailService.initRelatedNegotiation($('.negotiation-id').val(), $scope, $timeout);
      NegotiationDetailService.initRelatedProjectNegotiation($('.negotiation-id').val(), $scope, $timeout);
    };

    this.sendComment = function () {
      var data = {
        id: $('.negotiation-id').val(),
        content: $('.comment-text-input').val()
      };

      if (data.content != '') {
        NegotiationDetailService.addComment(data, $scope);
      }
    };

    this.back = function () {
    	var data_search = JSON.parse(localStorage.getItem("negotiaton_search_for_next"));
    	if (data_search !== null) {
        	localStorage.setItem("negotiaton_search_for_next", JSON.stringify(data_search));
		}

    	// back to list or project
    	let url = window.location.href;
		if (url.indexOf('#') > -1 && url.indexOf('&') > -1) {
			let screen = url.substring(url.lastIndexOf('&') + 1);
			window.location.replace(createUrl('/pc/' + screen));
		} else {
			window.location.replace(createUrl('/pc/negotiation/list'));
		}
	}
    this.findRelation = function() {
        var corporationIds = [];
        var businessCardIds = [];
        var businessCardFree = "";
        var buildingIds = [];
        var brandIds = [];
        var latest2Year = true;

        $(".negotiatoin-related-corporation").each(function() {
            if ($(this).prop("checked")) {
            	corporationIds.push($(this).val());
            }
        });
        
        $(".negotiatoin-related-business-card").each(function() {
            if ($(this).prop("checked")) {
            	businessCardIds.push($(this).val());
            }
        });
        
        if ($("#negotiatoin-related-business-card-free").prop("checked")) {
        	businessCardFree = $("#negotiatoin-related-business-card-free").val();
        }
        
        $(".negotiatoin-related-building").each(function() {
            if ($(this).prop("checked")) {
                buildingIds.push($(this).val());
            }
        });
        
        $(".negotiatoin-related-brand").each(function() {
            if ($(this).prop("checked")) {
            	brandIds.push($(this).val());
            }
        });
        
        latest2Year = !$("#negotiatoin-related-latest-2-year").prop("checked")

        NegotiationDetailService.findRelatedNegotiation({
            id: $('.negotiation-id').val(),
            corporationIds: corporationIds,
            interviewBusinessCardIds: businessCardIds,
            interviewBusinessCardFree: businessCardFree,
            buildingIds: buildingIds,
            brandIds: brandIds,
            latest2Year: latest2Year,
    	}, $scope);
    }

    this.deleteComment = function (id) {
      NegotiationDetailService.deleteComment(id, $scope);
    };
  });

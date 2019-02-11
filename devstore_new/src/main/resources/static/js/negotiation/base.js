'use strict';

/*
 * Contains a service to communicate with API
 */
angular
    .module('app.services')
    .factory('NegotiationFormBase', negotiationFormBase);

function negotiationFormBase($http, BaseService) {
    var self = this;
    var datetime = {};

    function findAccount(suggest) {
        _makeFindRequest('sp/negotiation/find/account', suggest);
    }

    function findCorporation(suggest) {
        _makeFindRequest('sp/negotiation/find/corporation', suggest);
    }

    function findBrand(suggest) {
        _makeFindRequest('sp/negotiation/find/brand', suggest);
    }

    function findPartner(suggest) {
        _makeFindRequest('sp/negotiation/find/partner', suggest);
    }

    function findBuilding(suggest) {
        _makeFindRequest('sp/negotiation/find/building', suggest);
    }

    function findShop(suggest) {
        _makeFindRequest('sp/negotiation/find/shop', suggest);
    }

    function findBuilding(suggest) {
        _makeFindRequest("sp/negotiation/find/building", suggest);
    }

    function _makeFindRequest(url, suggest) {
        return BaseService.postSuggest(url, suggest).then(function(data) {
            suggest.setData(data);
            return data;
        });
    }
    
    function fileDeleteEvent() {
    	$(document).on("click", ".file-btn-close", function(e) {
    		var div = $(this).closest(".input-area");
    		div.remove();
    		
    		$($(".file-list").find(".input-area").get().reverse()).each(function(index, element) {
    			$(element).attr("index", index);
    			$(element).find(".file-id").attr("name", "fileInformation[" + index + "].id");
    			$(element).find(".file-division").each(function(i, radio) {
    				$(radio).attr("name", "fileInformation[" + index + "].division");
    			});
    			$(element).find(".file-comment").attr("name", "fileInformation[" + index + "].comment");
    		});
    	});
    }

    function toDate(datetimeValue) {
        if (datetimeValue) {
            return datetimeValue.split(" ")[0];
        } else {
            return "";
        }
    }
    function toTime(datetimeValue) {
        if (datetimeValue) {
            return datetimeValue.split(" ")[1];
        } else {
            return "";
        }
    }
    
    function createValidMessage(){
    	var message = [];
    	$(".tab-pane" ).each(function() {
    	if($(this).find('label.error').length > 0){
	    	message.push($('ul > li > a[href="#'+$(this).attr('id')+'"]').html() + 'タブの項目に誤りがあります');
	    	}
    	});

    	if(message.length > 0){
    		alert(message.join('\n'));
    	}
    }

    return {
    	datetime: datetime,
        toDate: toDate,
        toTime: toTime,
        fileDeleteEvent: fileDeleteEvent,
        createValidMessage: createValidMessage
    };
};

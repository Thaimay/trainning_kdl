'use strict';

/*
 * Contains a service to communicate with API
 */
angular
  .module('app.services')
  .factory('NegotiationSearchService', negotiationSearchService);

function negotiationSearchService ($http, BaseService) {
  var api = angular.copy(BaseService);
  var BASE_PATH = 'pc/negotiation/';

  function findProject(value) {
	  return BaseService.suggestPost(BASE_PATH + 'find/project', value );
  }

  function findCorporation (value) {
    return BaseService.suggestPost(BASE_PATH + 'find/corporation', { text: value });
  };

  function findAccount (value) {
    return BaseService.suggestPost(BASE_PATH + 'find/account', { text: value });
  };

  function findPartner (value) {
    return BaseService.suggestPost(BASE_PATH + 'find/partner', { text: value });
  };

  function findBuilding (value) {
    return BaseService.suggestPost(BASE_PATH + 'find/building', { text: value });
  };

  function findBrand (value) {
    return BaseService.suggestPost(BASE_PATH + 'find/brand', { text: value });
  }
  
  function findShop (value) {
    return BaseService.suggestPost(BASE_PATH + 'find/shop', { text: value });
  }

  function submitForm () {
    var _form = document.getElementById('negotiation-form');
    
    var isScheduled = false;
    var isImplemented = true;
    var type = $('input[type=radio][name=negotiation-type]:checked').val();
    console.log(type);
    if(type === "Scheduled"){
    	isScheduled = true;
    	isImplemented = false;
    }else if(type === "Implemented"){
    	isScheduled = false;
    	isImplemented = true;
    }else if(type === "All"){
    	isScheduled = false;
    	isImplemented = false;
    }
    
    $('<input>').attr({
        type: 'hidden',
        name: 'isScheduled',
        value: isScheduled
      }).appendTo(_form);
    
    $('<input>').attr({
        type: 'hidden',
        name: 'isImplemented',
        value: isImplemented
      }).appendTo(_form);
    
    _form.submit();
  }

  function movePage (target) {
    var pageNum = $(target).data('j-pager-num');
    $('#numberOfPage').val(pageNum);

    this.submitForm();
  }

  function resetPageNumber () {
    $('#numberOfPage').val(0);
  }

  return {
    findProject: findProject,
    findCorporation: findCorporation,
    findAccount: findAccount,
    findPartner: findPartner,
    findBuilding: findBuilding,
    findBrand: findBrand,
    findShop: findShop,
    submitForm: submitForm,
    movePage: movePage,
    resetPageNumber: resetPageNumber
  };
};

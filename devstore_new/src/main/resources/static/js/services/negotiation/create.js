'use strict';

/*
 * Contains a service to communicate with API
 */
angular
  .module('app.services')
  .factory('NegotiationSearchService', negotiationSearchService);

function negotiationSearchService ($http, BaseService) {
  function submitForm () {
    var _form = document.getElementById('negotiation-form');
    _form.submit();
  }

  return {
    submitForm: submitForm
  };
};

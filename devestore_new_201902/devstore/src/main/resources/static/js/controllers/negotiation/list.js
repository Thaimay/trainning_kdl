'use strict';

angular
  .module('app.core')
  .controller('NegotiationListController', function (NegotiationSearchService, $scope) {
    var self = this;
    this.$targetForm = $('#negotiation-form');
    let suggestCorporation, suggestVisitor, suggestInterview, suggestBuilding, suggestShop, suggestUpdateAccount;

	this.initSuggest = function () {
		this.createPagingKeyword();
		this.initPagingPagination();
		suggestCorporation = this.createSuggest($('.j-form-meeting-corporation'), 'corporationIds', NegotiationSearchService.findCorporation);
		suggestVisitor = this.createSuggest($('.j-form-visitor'), 'accountIds', NegotiationSearchService.findAccount);
		suggestInterview = this.createSuggest($('.j-form-staff-interview'), 'interviewIds', NegotiationSearchService.findPartner);
		suggestBuilding = this.createSuggest($('.j-form-negotiation-building'), 'buildingIds', NegotiationSearchService.findBuilding);
		suggestShop = this.createSuggest($('.j-form-negotiation-shop'), 'shopIds', NegotiationSearchService.findShop);
		suggestUpdateAccount = this.createSuggest($('.j-form-negotiation-update-account'), 'updateAccountIds', NegotiationSearchService.findAccount);
		var data_search = JSON.parse(localStorage.getItem("negotiaton_search_for_next"));
      if (data_search != null) {
    	  $("#keyWordSearch").val(data_search.fillText);
    	  if(data_search.isOpened){
    		  $('input[name="isOpened"]').prop('checked', true);
    	  }
    	  if(data_search.isOwn){
    		  $('input[name="isOwn"]').prop('checked', true);
    	  }
    	  if(data_search.isReadLater){
    		  $('input[name="isReadLater"]').prop('checked', true);
    	  }
    	  var radioCheck = $('input[name="negotiation-type"]');
    	  radioCheck.each(function(){
              if($(this).val() == 'Scheduled'){
            	  if(data_search.isScheduled){
            		  $('input[name="negotiation-type"][value="Scheduled"]').prop('checked', true);
            	  }
              } else if($(this).val() == 'Implemented'){
            	  if(data_search.isImplemented){
            		  $('input[name="negotiation-type"][value="Implemented"]').prop('checked', true);
            	  }
              } else {
            	  if(!data_search.isScheduled && !data_search.isImplemented){
            		  $('input[name="negotiation-type"][value="All"]').prop('checked', true);
            	  }
              }
          });

		  $("#form-subject").val(data_search.title);
		  $("#form-implementation-date").val(data_search.implementationStartDate);
		  $("#form-implementation-end-date").val(data_search.implementationEndDate);
		  suggestBuilding.setSelection(data_search.buildingData);
		  suggestBuilding.setValue(data_search.buildingIds);
		  suggestCorporation.setSelection(data_search.corporationData);
		  suggestCorporation.setValue(data_search.corporationIds);
		  suggestInterview.setSelection(data_search.interviewData);
		  suggestInterview.setValue(data_search.interviewIds);
		  suggestVisitor.setSelection(data_search.accountData);
		  suggestVisitor.setValue(data_search.accountIds);
		  suggestShop.setSelection(data_search.shopData);
		  suggestShop.setValue(data_search.shopIds);
		  suggestUpdateAccount.setSelection(data_search.updateAccountData);
		  suggestUpdateAccount.setValue(data_search.updateAccountIds);

		  $(suggestBuilding).blur();
		  $(suggestCorporation).blur();
		  $(suggestInterview).blur();
		  $(suggestVisitor).blur();
		  $(suggestShop).blur();
		  $(suggestUpdateAccount).blur();

		  var radioDivision = $("input[name='division']");
		  radioDivision.each(function () {
			  if($(this).val() == data_search.division){
				  $('input[name="division"][value="' + data_search.division + '"]').prop('checked', true);}
		  });
		  $("#negotiation-search-order").val(data_search.orderByOption);
		  if (data_search.numberOfPage === undefined) {
			  $("#numberOfPage").val(0);
		  } else {
			  $("#numberOfPage").val(data_search.numberOfPage);
		  }

		  NegotiationSearchService.submitForm();
    	  localStorage.removeItem("negotiaton_search_for_next");
      }
	  if(this.isExistsSearchParameter()){
    	  $("#j-negotiation-tabs").find("a[href='#search']").css("background-color", "#effdff");
	  }
    };

    this.createSuggest = function (target, name, method) {
      return magicSuggest.create(target, {
        name: name,
        allowFreeEntries : false,
        request: function (value, successHandler) {
          method(value).then(function (data) {
            successHandler(data);
          });
        }
      });
    };

    this.onCheck = function () {
      NegotiationSearchService.resetPageNumber();
      NegotiationSearchService.submitForm();
    };

    this.onPagerClick = function ($event) {
      NegotiationSearchService.movePage($event.target);
    };

    this.searchAdvance = function (next_search) {

    	next_search.title = $("#form-subject").val();
    	next_search.implementationStartDate = $("#form-implementation-date").val();
    	next_search.implementationEndDate = $("#form-implementation-end-date").val();
    	next_search.buildingIds = suggestBuilding.getValue();
    	next_search.buildingData = suggestBuilding.getSelection();
    	next_search.shopData = suggestShop.getSelection();
    	next_search.shopIds = suggestShop.getValue();
    	next_search.corporationData = suggestCorporation.getSelection();
    	next_search.corporationIds = suggestCorporation.getValue();
    	next_search.interviewData = suggestInterview.getSelection();
    	next_search.interviewIds = suggestInterview.getValue();
    	next_search.accountData = suggestVisitor.getSelection();
    	next_search.accountIds = suggestVisitor.getValue();
    	next_search.updateAccountData = suggestUpdateAccount.getSelection();
    	next_search.updateAccountIds = suggestUpdateAccount.getValue();
    	next_search.division = $("input[name='division']:checked").val();
    	next_search.orderByOption = $("#negotiation-search-order").val();
	};
    this.onSubmit = function () {
    	var next_search = JSON.parse(localStorage.getItem("negotiaton_search_for_next"));
        if(next_search == null){
        	next_search = {};
        }
    	this.searchAdvance(next_search);
    	localStorage.setItem("negotiaton_search_for_next", JSON.stringify(next_search));
    	NegotiationSearchService.resetPageNumber();
    	NegotiationSearchService.submitForm();
    };

    this.searchFullText = function(e){
      if (e.keyCode === 13) {
          NegotiationSearchService.resetPageNumber();
          NegotiationSearchService.submitForm();
      }
    }

    this.toDetail = function () {
        var next_search = JSON.parse(localStorage.getItem("negotiaton_search_for_next"));
        if(next_search == null){
        	next_search = {};
        }
        var radioSearch = $("input[name='negotiation-type']:checked").val();
        if (radioSearch == 'Scheduled') {
        	next_search.isScheduled = true;
		} else if(radioSearch == 'Implemented'){
			next_search.isImplemented = true;
		} else {
			next_search.isScheduled = false;
        	next_search.isImplemented = false;
		}

        $('.negotiation-list input[name="negotiation-search-onCheck"]:checked').each(function(){
        	console.log(this.value)
            if($(this).val() == 'isOpened'){
            	next_search.search_opened = true;
            } else if($(this).val() == 'isOwn'){
                next_search.search_own = true;
            } else {
                next_search.search_readLater = true;
            }
        });
        var inputElements = document.getElementsByClassName('negotiation-search-onCheck');
        for(var i=0; inputElements[i]; ++i){
        	if(inputElements[i].name == 'isOpened' && inputElements[i].checked){
				next_search.isOpened = true;
        	} else if(inputElements[i].name == 'isOwn' && inputElements[i].checked){
        		next_search.isOwn = true;
        	} else if(inputElements[i].name == 'isReadLater' && inputElements[i].checked){
        		next_search.isReadLater = true;
        	}
        }
        next_search.fillText  = $("#keyWordSearch").val();
        var numPage = $(".pagination").find(".active").text().trim();
        if (numPage > 1) {
        	next_search.numberOfPage = numPage - 1;
		} else {
			next_search.numberOfPage = 0;	
		}

        this.searchAdvance(next_search);
    	localStorage.setItem("negotiaton_search_for_next", JSON.stringify(next_search));
	}
    this.clear = function() {
    	$("#search").find('input[type=text]').val('');
    	$("#search").find('input[type=checkbox]').prop('checked', false);
    	$("#search").find('input[type=radio]').prop('checked', false);
    	$("#negotiation-search-order").val("UPDATE_DATETIME_DESC");
    	suggestCorporation.clear();
    	suggestVisitor.clear();
    	suggestInterview.clear();
    	suggestBuilding.clear();
    	suggestShop.clear();
    	suggestUpdateAccount.clear();
    }

    this.isExistsSearchParameter = function() {
    	var result = false;
    	$("#search").find('input[type=text]').each(function(){
    		if($(this).val() !== ""){
    			result = true;
    		}
    	});
    	$("#search").find('input[type=checkbox]').each(function(){
    		if($(this).prop('checked') !== false && $(this).attr('id') !== 'negotiatoin-search-default'){
    			result = true;
    		}
    	});
    	if(suggestCorporation.getValue().length > 0){
    		result = true;
    	}
    	if(suggestVisitor.getValue().length > 0){
    		result = true;
    	}
    	if(suggestInterview.getValue().length > 0){
    		result = true;
    	}
    	if(suggestBuilding.getValue().length > 0){
    		result = true;
    	}
    	if(suggestShop.getValue().length > 0){
    		result = true;
    	}
    	if(suggestUpdateAccount.getValue().length > 0){
    		result = true;
    	}
    	if ($("#negotiation-search-order").val() !== "UPDATE_DATETIME_DESC") {
    		result = true;
		}
    	return result;
    }
    this.createPagingKeyword = function (){
    	var pagination = $('#paginationKeyword');
    	var pageCount = $('#pageCountKeyword').val();
    	var currentPage = $('#numberOfPage').val();
    	var maxDisplayPage = 8;
    	var startPageIndex = 0;
    	var endPageIndex = 0;

    	pagination.html("");

    	if(currentPage == 0){
    		pagination.append('<li id="previousKeyword" class="previousPage disabled" value="previous"><a aria-label="Previous"><span aria-hidden="true">«</span></a></li>')
    	}else{
    		pagination.append('<li id="previousKeyword" class="previousPage" value="previous"><a aria-label="Previous"><span aria-hidden="true">«</span></a></li>')
    	}

    	if(pageCount > maxDisplayPage - 1){
    		if(currentPage < parseInt(maxDisplayPage/2)){
    			startPageIndex = 0;
    		} else {
    			startPageIndex = currentPage - parseInt(maxDisplayPage/2);
    		}
    		if((startPageIndex + maxDisplayPage - 1) > pageCount) {
    			startPageIndex = pageCount - maxDisplayPage + 1;
    		}
    		endPageIndex = startPageIndex + maxDisplayPage - 1;
    	}else{
    		startPageIndex = 0;
    		endPageIndex = pageCount;
    	}

    	for (var i = startPageIndex; i <= endPageIndex; i++) {
    		if(i == currentPage){
    			pagination.append('<li class="pagingKeyword active" value="' + i + '"><a>'+ ( i + 1 ) +'</a></li>');
    		}else{
    			pagination.append('<li class="pagingKeyword" value="' + i + '"><a>'+ ( i + 1 ) +'</a></li>');
    		}
    	}

    	if(currentPage == pageCount){
    		pagination.append('<li id="nextKeyword" class="nextPage disabled" value="next"><a aria-label="Next"><span aria-hidden="true">»</span></a></li>')
    	}else{
    		pagination.append('<li id="nextKeyword" class="nextPage" value="next"><a aria-label="Next"><span aria-hidden="true">»</span></a></li>')
    	}
    }
    this.initPagingPagination = function () {
    	$('.pagingKeyword').click(function() {
    		$('.pagingKeyword').removeClass('active');
    		$(this).addClass('active');
    		$('#numberOfPage').val($(this).val());
    		NegotiationSearchService.submitForm();
    	});

    	$('#nextKeyword').click(function() {
    		if($(this).hasClass("disabled")) {
    			return;
    		}
    		$('#numberOfPage').val(parseInt($('#numberOfPage').val()) + 1);
    		NegotiationSearchService.submitForm();
    	});

    	$('#previousKeyword').click(function() {
    		if($(this).hasClass("disabled")) {
    			return;
    		}
    		$('#numberOfPage').val(parseInt($('#numberOfPage').val()) - 1);
    		NegotiationSearchService.submitForm();
    	});
	}
  });

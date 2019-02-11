var makePostRequest = function (url, data) {
	return $.ajax({
        url: createUrl(url),
        type: 'POST',
        contentType : "application/json",
        data: data,
    });
}
if (typeof magicSuggestCreate !== 'function') {
	    var magicSuggestCreate = function (domName, value, data, allowFree) {
	      return $(domName).magicSuggest({
	        placeholder: '',
	        value: value,
					data: data,
					noSuggestionText: "候補がありません",
	        allowFreeEntries: allowFree,
				})
	    };
	};
	
function createSuggest(control, data, value, allowFree)
{
	if(value == undefined) {
		value = [];
	}
	if(allowFree == undefined) {
		allowFree = true;
	}
	var $target = $("#project-form");
	return magicSuggestCreate(
      $target.find(control),
      value,
      data,
      allowFree
    );
};
var createDatePicker = function ($target, selectEvent) {
	var option = {
		dateFormat : dateFormat,
		buttonImageOnly: true,
	     buttonImage: "",
	     buttonText: "",
	     showOn: "both",
	     showButtonPanel: true,
	     closeText: "閉じる",
	     onSelect: function() {
	         var value = $(this).datepicker({ dateFormat: 'yy-mm-dd' }).val();
	         $(this).val(value);
	         $(this).change();
	     },
	     monthNames: [ "1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月" ],
	     monthNamesShort: [ "1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月" ],
	     dayNames: ["日曜日","月曜日","火曜日","水曜日","木曜日","金曜日","土曜日"],
	     dayNamesShort: ["日","月","火","水","木","金","土"],
	     dayNamesMin: ["日","月","火","水","木","金","土"],
	     showMonthAfterYear: true,
	     changeMonth: true,
	     changeYear: true,
	}
	if(selectEvent != undefined){
		option.onSelect = selectEvent;
	}
	$target.datepicker(option);

}

function doFindKeyword(){
	var form = $('#project-form');
	form.append('<input type="hidden" name="keyWord" value="' + $('#keyWordSearch').val() + '" />');
	form.append('<input type="hidden" name="isOwn" value="' + ($('#chkOwner').prop('checked') ? true : false) + '" />');
	form.append('<input type="hidden" name="isReadLater" value="' + ($('#chkReadLater').prop('checked') ? true : false) + '" />');
	form.append('<input type="hidden" name="isMonBefore" value="' + ($('#chkMonBefore').prop('checked') ? true : false) + '" />');
	form.append('<input type="hidden" name="isRunOnly" value="' + ($('#chkRunOnly').prop('checked') ? true : false) + '" />');
	form.append('<input type="hidden" name="isNewShop" value="' + ($('#chkNewShop').prop('checked') ? true : false) + '" />');
	form.append('<input type="hidden" name="isExistsShop" value="' + ($('#chkExistsShop').prop('checked') ? true : false) + '" />');
	form.append('<input type="hidden" name="isOtherShop" value="' + ($('#chkOtherShop').prop('checked') ? true : false) + '" />');
	if ($('input[name=landingIds]:checked').length > 0) {
		$('input[name=landingIds]:checked').each(function(index, e){
			form.append('<input type="hidden" name="landingIds" value="' + $(this).val() + '" />');
		});
	} else {
		form.append('<input type="hidden" name="landingIds" value="" />');
	}
	form.append('<input type="hidden" name="isLandingNothing" value="' + ($('#isLandingNothing').prop('checked') ? true : false) + '" />');

	form.append('<input type="hidden" name="isBasicNotPrint" value="' + ($('#chkBasicPrint').prop('checked') ? true : false) + '" />');
	form.append('<input type="hidden" name="isCorporationNotPrint" value="' + ($('#chkCorporationPrint').prop('checked') ? true : false) + '" />');
	form.append('<input type="hidden" name="isLandingNotPrint" value="' + ($('#chkLandingPrint').prop('checked') ? true : false) + '" />');
	form.append('<input type="hidden" name="isCurrentContractNotPrint" value="' + ($('#chkCurrentContractPrint').prop('checked') ? true : false) + '" />');
	form.append('<input type="hidden" name="isProgressContractNotPrint" value="' + ($('#chkProgressContractPrint').prop('checked') ? true : false) + '" />');
	form.append('<input type="hidden" name="isPlanNotPrint" value="' + ($('#chkPlanPrint').prop('checked') ? true : false) + '" />');
	form.append('<input type="hidden" name="isCurrentSectionNotPrint" value="' + ($('#chkCurrentSectionPrint').prop('checked') ? true : false) + '" />');
	form.append('<input type="hidden" name="isProgressSectionNotPrint" value="' + ($('#chkProgressSectionPrint').prop('checked') ? true : false) + '" />');
	form.append('<input type="hidden" name="isStatusNotPrint" value="' + ($('#chkStatusPrint').prop('checked') ? true : false) + '" />');

	form.append('<input type="hidden" name="numberOfPage" value="' + $('#currentPageKeyword').val() + '" />');
	
	// company
	$.each(iShopCompanieIds, function(){
		form.append('<input type="hidden" name="iShopCompanieIds" value="' + this + '" />');
	});
	
	// brand
	$.each(iBrandIds, function(){
		form.append('<input type="hidden" name="iBrandIds" value="' + this + '" />');
	});
	
	// area
	$.each(iAreaIds, function(){
		form.append('<input type="hidden" name="iAreaIds" value="' + this + '" />');
	});
	
	// prefecture
	$.each(iPrefectureIds, function(){
		form.append('<input type="hidden" name="iPrefectureIds" value="' + this + '" />');
	});
	
	// block
	$.each(iBlockIds, function(){
		form.append('<input type="hidden" name="iBlockIds" value="' + this + '" />');
	});

	getDataSuggesOtherAccountStore(form);
    
	// set form to local storage
    localStorage.setItem("project_search_form", JSON.stringify($("#project-form").serializeArray()));
	
	form.submit();
}

function doFindAdvanced(){
	var form = $('#project-form');
	form.append('<input type="hidden" name="advancedSearch" value="true" />');
	form.append('<input type="hidden" name="numberOfPage" value="' + $('#currentPageAdvanced').val() + '" />');
	
	form.submit();
}

function createPagingKeyword(){
	var pagination = $('#paginationKeyword');
	var pageCount = $('#pageCountKeyword').val();
	var currentPage = $('#currentPageKeyword').val();
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

	for (i = startPageIndex; i <= endPageIndex; i++) {
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

function createPagingAdvanced(){
	var pagination = $('#paginationAdvanced');
	var pageCount = $('#pageCountAdvanced').val();
	var currentPage = $('#currentPageAdvanced').val();
	var maxDisplayPage = 8;
	var startPageIndex = 0;
	var endPageIndex = 0;

	pagination.html("");

	if(currentPage == 0){
		pagination.append('<li id="previousAdvanced" class="previousPage disabled" value="previous"><a aria-label="Previous"><span aria-hidden="true">«</span></a></li>')
	}else{
		pagination.append('<li id="previousAdvanced" class="previousPage" value="previous"><a aria-label="Previous"><span aria-hidden="true">«</span></a></li>')
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

	for (i = startPageIndex; i <= endPageIndex; i++) {
		if(i == currentPage){
			pagination.append('<li class="pagingAdvanced active" value="' + i + '"><a>'+ ( i + 1 ) +'</a></li>');
		}else{
			pagination.append('<li class="pagingAdvanced" value="' + i + '"><a>'+ ( i + 1 ) +'</a></li>');
		}
	}

	if(currentPage == pageCount){
		pagination.append('<li id="nextAdvanced" class="nextPage disabled" value="next"><a aria-label="Next"><span aria-hidden="true">»</span></a></li>')
	}else{
		pagination.append('<li id="nextAdvanced" class="nextPage" value="next"><a aria-label="Next"><span aria-hidden="true">»</span></a></li>')
	}
}
$(function() {
	$(window).keydown(function(event){
	    if(event.keyCode === 13 && !$('#keyWordSearch').is(':focus')) {
	    	$(this).prop("disabled", true);
	      event.preventDefault();
	      return false;
	    }
	});

	if(isExistsSearchParameter()){
  	  $("#j-project-tabs").find("a[href='#search']").css("background-color", "#effdff");
	}
	
	createPagingKeyword();
	//createPagingAdvanced();

	$('#keyWordSearch').keydown(function(e) {
		if(e.which == 13) {
			$(this).prop("disabled", true);
			$('#currentPageKeyword').val('0');
			doFindKeyword();
		}
	});

	$('#chkOwner').click(function() {
		$(this).prop("disabled", true);
		$('#currentPageKeyword').val('0');
		doFindKeyword();
	});
	
	$("#print-update-button").click(function(e) {
		$(this).prop("disabled", true);
		e.preventDefault();
		doFindKeyword();
	});

	$('#chkReadLater').click(function() {
		$(this).prop("disabled", true);
		$('#currentPageKeyword').val('0');
		doFindKeyword();
	});

	$('#chkMonBefore').click(function() {
		$(this).prop("disabled", true);
		$('#currentPageKeyword').val('0');
		doFindKeyword();
	});

	$('#chkRunOnly').click(function() {
		$(this).prop("disabled", true);
		$('#currentPageKeyword').val('0');
		doFindKeyword();
	});

	$('#project-find-checkbox').click(function() {
		$('#currentPageKeyword').val('0');
		doFindKeyword();
	});

	$('.pagingKeyword').click(function() {
		$(this).prop("disabled", true);
		$('.pagingKeyword').removeClass('active');
		$(this).addClass('active');
		$('#currentPageKeyword').val($(this).val());
		doFindKeyword();
	});

	$('#nextKeyword').click(function() {
		$(this).prop("disabled", true);
		if($(this).hasClass("disabled")) {
			return;
		}
		$('#currentPageKeyword').val(parseInt($('#currentPageKeyword').val()) + 1);
		doFindKeyword();
	});

	$('#previousKeyword').click(function() {
		$(this).prop("disabled", true);
		if($(this).hasClass("disabled")) {
			return;
		}
		$('#currentPageKeyword').val(parseInt($('#currentPageKeyword').val()) - 1);
		doFindKeyword();
	});

	$('#j-search').click(function(e) {
		$(this).prop("disabled", true);
		$('#currentPageKeyword').val('0');
		doFindKeyword();
	});

	var isImeMode = false;
	$(suggestOtherAccountStore).on({
		'keydown': function (keydownEvent, ms, event) {
			if (event.keyCode === 229) {
				isImeMode = true
			} else {
				isImeMode = false
			}
		},
		'keyup': function(e, m, v) {
			if (event.keyCode == 38 || event.keyCode == 40) { return; }

			let value = toZenkaku(this.getRawValue());
			if (!value) { return; }

			if (!isImeMode || (isImeMode && event.keyCode === 13)) {
				$('#' + suggestOtherAccountStore.container[0].id + ' input[type=text]').val(value);
				let suggest = this;
				$.ajax({
					url: createUrl("/sp/project/find/account"),
					data : JSON.stringify({ "text": value}),
					cache : false,
					contentType: "application/json",
					processData : false,
					method : 'POST',
					type : 'POST', // For jQuery < 1.9
					success : function(response) {
						suggest.setData(response);
					}
				});
			}
		},
		'blur': function (e) {
			isImeMode = false
		}
	});
	
	datepickerUtils.createDatePicker($('input#j-form-update-from'));
	datepickerUtils.createDatePicker($('input#j-form-update-to'));
	
	$('#j-clear').click(function(e) {
    	$("#search").find('input[type=text]').val('');
    	$("#search").find('input[type=number]').val('');
    	$("#search").find('input[type=checkbox]').prop('checked', false);
    	$("#search").find('input[type=radio]').prop('checked', false);
    	$("#search").find('select').val('');
    	
    	suggestOtherAccountStore.clear();
    	
    	$("#label-brand-search").text('未設定');
    	$("#label-block-search").text('未設定');
    	
    	iShopCompanieIds = [];
    	iBrandIds = [];
    	iAreaIds = [];
    	iPrefectureIds = [];
    	iBlockIds = [];
    	
    	$('#project-search-order-by-option').val('ORDER_BY_1');
    	$('#project-search-order-by-desc').val('false');
    });
	
	// modal
	$("#modal-brand-search").on('show.bs.modal', function (eventModel) {
		$('#modal-brand-search').find('input[class=project-search-company]').each(function(){
			let value = parseInt($(this).val(), 10);
			if(iShopCompanieIds.indexOf(value) !== -1){
				$(this).prop("checked", true);
			} else{
				$(this).prop("checked", false);
			}
		});
		
		$('#modal-brand-search').find('input[class=project-search-brand]').each(function(){
			let value = parseInt($(this).val(), 10);
			if(iBrandIds.indexOf(value) !== -1){
				$(this).prop("checked", true);
			} else{
				$(this).prop("checked", false);
			}
		});
		
		$('#modal-brand-search').find('.j-dropdown-header').each(function(){
	        let target = $(this);
	        if(target.find('input:checked').length > 0){
	        	if(!target.hasClass("isOpen")){
	        		target.addClass('isOpen');
				}
	        } else{
	        	if(target.hasClass("isOpen")){
	        		target.removeClass('isOpen');
				}
	        }
	    });
		
		$('.j-dropdown-header').not('.isOpen').next('.j-dropdown-body').hide();
		$('.j-dropdown-header.isOpen').next('.j-dropdown-body').show();
	});
	
	$("#btn-select-brand").on("click", function(){
		// company
		iShopCompanieIds = [];
		$('#modal-brand-search').find('input[class=project-search-company]:checked').each(function(){
			iShopCompanieIds.push(parseInt($(this).val(), 10));
		});
		
		// brand
		iBrandIds = [];
		$('#modal-brand-search').find('input[class=project-search-brand]:checked').each(function(){
			iBrandIds.push(parseInt($(this).val(), 10));
		});
		
		getBrandLabel();
	});
	
	
	$("#modal-block-search").on('show.bs.modal', function (eventModel) {
		$('#modal-block-search').find('input[class=project-search-area]').each(function(){
			let value = parseInt($(this).val(), 10);
			if(iAreaIds.indexOf(value) !== -1){
				$(this).prop("checked", true);
			} else{
				$(this).prop("checked", false);
			}
		});
		
		$('#modal-block-search').find('input[class=project-search-prefectures]').each(function(){
			let value = parseInt($(this).val(), 10);
			if(iPrefectureIds.indexOf(value) !== -1){
				$(this).prop("checked", true);
			} else{
				$(this).prop("checked", false);
			}
		});
		
		$('#modal-block-search').find('input[class=project-search-block]').each(function(){
			let value = parseInt($(this).val(), 10);
			if(iBlockIds.indexOf(value) !== -1){
				$(this).prop("checked", true);
			} else{
				$(this).prop("checked", false);
			}
		});
		
		$('#modal-block-search').find('.j-dropdown-header').each(function(){
	        let target = $(this);
	        if(target.find('input:checked').length > 0){
	        	if(!target.hasClass("isOpen")){
	        		target.addClass('isOpen');
				}
	        } else{
	        	if(target.hasClass("isOpen")){
	        		target.removeClass('isOpen');
				}
	        }
	    });
		
		$('.j-dropdown-header').not('.isOpen').next('.j-dropdown-body').hide();
		$('.j-dropdown-header.isOpen').next('.j-dropdown-body').show();
	});
	
	$("#btn-select-block").on("click", function(){
		// area
		iAreaIds = [];
		$('#modal-block-search').find('input[class=project-search-area]:checked').each(function(){
			iAreaIds.push(parseInt($(this).val(), 10));
		});
		
		// prefectures
		iPrefectureIds = [];
		$('#modal-block-search').find('input[class=project-search-prefectures]:checked').each(function(){
			iPrefectureIds.push(parseInt($(this).val(), 10));
		});
		
		// block
		iBlockIds = [];
		$('#modal-block-search').find('input[class=project-search-block]:checked').each(function(){
			iBlockIds.push(parseInt($(this).val(), 10));
		});
		
		getBlockLabel();
	});
})

function getDataSuggesOtherAccountStore(form){
	$.each(suggestOtherAccountStore.getSelection(), function(index,obj){
		form.append('<input type="hidden" name="otherAccountStoreIds" value="' + obj.id + '" />');
	});
	form.append('<input type="hidden" name="jsonCurrentOtherAccountStore" value="[]" />');
	form.find('input[name="jsonCurrentOtherAccountStore"]').val(JSON.stringify(suggestOtherAccountStore.getSelection()));
}

function isExistsSearchParameter() {
	var result = false;
	$("#search").find('input[type=text]').each(function(){
		if($(this).val() !== ""){
			result = true;
		}
	});
	$("#search").find('input[type=number]').each(function(){
		if($(this).val() !== ""){
			result = true;
		}
	});
	$("#search").find('input[type=checkbox]').each(function(){
		if($(this).prop('checked') !== false){
			result = true;
		}
	});
	$("#search").find('input[type=radio]').each(function(){
		if($(this).prop('checked') !== false){
			result = true;
		}
	});
	$("#search").find('select').each(function(){
		let id = $(this).attr('id');
		let value = $(this).val()
		
		if(id !== undefined && id !== null){
			if(id === 'project-search-order-by-option'){
				if(value !== undefined && value !== null && value !== "ORDER_BY_1"){
					result = true
				}
			}else if(id === 'project-search-order-by-desc'){
				if(value !== undefined && value !== null && value !== "false"){
					result = true
				}
			}else{
				if(value !== undefined && value !== null && value !== ""){
					result = true
				}
			}
		} else{
			if(value !== undefined && value !== null && value !== ""){
				result = true
			}
		}
	});
	if(suggestOtherAccountStore.getValue().length > 0){
		result = true;
	}
	if(iShopCompanieIds.length > 0){
		result = true;
	}
	if(iBrandIds.length > 0){
		result = true;
	}
	if(iAreaIds.length > 0){
		result = true;
	}
	if(iPrefectureIds.length > 0){
		result = true;
	}
	if(iBlockIds.length > 0){
		result = true;
	}
	return result;
}

function modalCheckChange(element){
	// set checked parent
	parentCheckChange(element);
	
	// set checked children
	childrenCheckChange(element);
}

function parentCheckChange(element){
	let parentId = $(element).attr('parent');
	let checked = $(element).prop('checked');
	
	if(parentId !== undefined && parentId.length > 0){
		if(checked){
			$('#' + parentId).prop('checked', true);
		} else{
			if($.find('input[parent='+parentId+']:checked').length <= 0){
				$('#' + parentId).prop('checked', false);
			}
		}
		parentCheckChange('#' + parentId);
	}
}

function childrenCheckChange(element){
	let id = $(element).attr('id');
	let checked = $(element).prop('checked');
	
	$.each($.find('input[parent='+id+']'), function(){
		$(this).prop('checked', checked);
		childrenCheckChange(this);
	});
}

function getBlockLabel(){
	let label = "未設定";
	let blockChecked = $('#modal-block-search').find('input[class=project-search-block]:checked');
		
	if(blockChecked.length > 0){
		let id = $(blockChecked[0]).attr('id');
		label = $($('#modal-block-search').find('span[for='+id+']')[0]).text();
		
		if(blockChecked.length > 1){
			label += " 他";
		}
	}
	
	$("#label-block-search").text(label);
}

function getBrandLabel(){
	let label = "未設定";
	let brandChecked = $('#modal-brand-search').find('input[class=project-search-brand]:checked');
		
	if(brandChecked.length > 0){
		let id = $(brandChecked[0]).attr('id');
		label = $($('#modal-brand-search').find('span[for='+id+']')[0]).text();
		
		if(brandChecked.length > 1){
			label += " 他";
		}
	}
	
	$("#label-brand-search").text(label);
}

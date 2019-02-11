if (typeof magicSuggestCreate !== 'function') {
	    var magicSuggestCreate = function (domName, value, data, allowFree) {
	      return $(domName).magicSuggest({
	        placeholder: '',
	        value: value,
	        data: data,
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
	var $target = $("#store-form");
	return magicSuggestCreate(
      $target.find(control),
      value,
      data,
      allowFree
    );
};

function doFindKeyword(){
	var form = $('#store-form');
	form.html("");
	form.append('<input type="hidden" name="keyWord" />');
	$("input[name='keyWord']").val($('#keyWordSearch').val());
	form.append('<input type="hidden" name="accountId" value="' + ($('#chkOwner').prop('checked') ? accountId : 0) + '" />');
	form.append('<input type="hidden" name="numberOfPage" value="' + $('#currentPageKeyword').val() + '" />');
	form.submit();
}

function doFindAdvanced(){
	var form = $('#store-form');
	form.append('<input type="hidden" name="advancedSearch" value="true" />');
	form.append('<input type="hidden" name="numberOfPage" value="' + $('#currentPageAdvanced').val() + '" />');
	getDataSuggestShop(form);
	getDataSuggestShopCompany(form);
	getDataSuggestArea(form);
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

function getDataSuggestShop(form){
	$.each(suggestShop.getSelection(), function(index,obj){
		
		form.append('<input type="hidden" name="shopIds" value="' + obj.id + '" />');
	});
	form.append('<input type="hidden" name="jsonCurrentShop" value="[]" />');
	form.find('input[name="jsonCurrentShop"]').val(JSON.stringify(suggestShop.getSelection()));
}

function getDataSuggestShopCompany(form){
	$.each(suggestShopCompany.getSelection(), function(index,obj){
		
		form.append('<input type="hidden" name="iShopCompanyIds" value="' + obj.id + '" />');
	});
	form.append('<input type="hidden" name="jsonCurrentShopCompany" value="[]" />');
	form.find('input[name="jsonCurrentShopCompany"]').val(JSON.stringify(suggestShopCompany.getSelection()));
}

function getDataSuggestArea(form){
	$.each(suggestArea.getSelection(), function(index,obj){
		
		form.append('<input type="hidden" name="iAreaIds" value="' + obj.id + '" />');
	});
	form.append('<input type="hidden" name="jsonCurrentArea" value="[]" />');
	form.find('input[name="jsonCurrentArea"]').val(JSON.stringify(suggestArea.getSelection()));
}

$(function() {

	createPagingKeyword();
	createPagingAdvanced();
	
	$(window).keydown(function(event){
	    if(event.keyCode == 13 && !$('#keyWordSearch').is(':focus')) {
	    	$(this).prop("disabled", true);
	      event.preventDefault();
	      return false;
	    }
	});

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

	$('#j-search').click(function() {
		$(this).prop("disabled", true);
		$('#currentPageAdvanced').val('0');
		doFindAdvanced();
	});
	
	$('.pagingAdvanced').click(function() {
		$(this).prop("disabled", true);
		$('.pagingAdvanced').removeClass('active');
		$(this).addClass('active');
		$('#currentPageAdvanced').val($(this).val());
		doFindAdvanced();
	});

	$('#nextAdvanced').click(function() {
		$(this).prop("disabled", true);
		if($(this).hasClass("disabled")) {
			return;
		}
		$('#currentPageAdvanced').val(parseInt($('#currentPageAdvanced').val()) + 1);
		doFindAdvanced();
	});

	$('#previousAdvanced').click(function() {
		$(this).prop("disabled", true);
		if($(this).hasClass("disabled")) {
			return;
		}
		$('#currentPageAdvanced').val(parseInt($('#currentPageAdvanced').val()) - 1);
		doFindAdvanced();
	});

	var createSuggest = function (target, url) {
		var isImeMode = false;
		$(target).on({
			'keydown': function (keydownEvent, ms, event) {
				if (event.keyCode === 229) {
					isImeMode = true
				} else {
					isImeMode = false
				}
			},
			'keyup': function (keyupEvent, ms, event) {
				if (event.keyCode == 38 || event.keyCode == 40) { return; }

				let value = toZenkaku(this.getRawValue());
				if (!value) { return; }

				if (!isImeMode || (isImeMode && event.keyCode === 13)) {
					$('#' + target.container[0].id + ' input[type=text]').val(value);
					let suggest = this;
					$.ajax({
						url: createUrl(url),
						data: JSON.stringify({ "text": value }),
						cache: false,
						contentType: "application/json",
						processData: false,
						method: 'POST',
						type: 'POST', // For jQuery < 1.9
						success: function (response) {
							suggest.setData(response);
						}
					});
				}
			},
			'blur': function (e) {
				isImeMode = false
			}
		});
	}

	createSuggest(suggestShop, "/sp/building/find/shop")
	createSuggest(suggestShopCompany, "/sp/building/find/i_shop_company")
	createSuggest(suggestArea, "/sp/building/find/i_shop_company")
		
	$("#radioBuilding").change(function() {
		if ($(this).prop("checked")) {
			window.location.replace(createUrl("/pc/building/list"));
		}
    });
})
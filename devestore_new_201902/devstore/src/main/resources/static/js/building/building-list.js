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
	var $target = $("#building-form");
	return magicSuggestCreate(
      $target.find(control),
      value,
      data,
      allowFree
    );
};

function getDataSuggestCorporation(form){
	$.each(suggestCorporation.getSelection(), function(index,obj){
		
		form.append('<input type="hidden" name="iCorporationIds" value="' + obj.id + '" />');
	});
	form.append('<input type="hidden" name="jsonCurrentCorporation" value="[]" />');
	form.find('input[name="jsonCurrentCorporation"]').val(JSON.stringify(suggestCorporation.getSelection()));
}

function getDataSuggestSalesChannel(form){
	$.each(suggestSalesChannel.getSelection(), function(index,obj){
		
		form.append('<input type="hidden" name="iSalesChannelIds" value="' + obj.id + '" />');
	});
	form.append('<input type="hidden" name="jsonCurrentSalesChannel" value="[]" />');
	form.find('input[name="jsonCurrentSalesChannel"]').val(JSON.stringify(suggestSalesChannel.getSelection()));
}

function getDataSuggestSalesChannel2(form){
	$.each(suggestSalesChannel2.getSelection(), function(index,obj){
		
		form.append('<input type="hidden" name="salesChannelCd2s" value="' + obj.id + '" />');
	});
	form.append('<input type="hidden" name="jsonCurrentSalesChannel2" value="[]" />');
	form.find('input[name="jsonCurrentSalesChannel2"]').val(JSON.stringify(suggestSalesChannel2.getSelection()));
}

function getDataSuggestBlock(form){
	$.each(suggestBlock.getSelection(), function(index,obj){
		
		form.append('<input type="hidden" name="iBlockIds" value="' + obj.id + '" />');
	});
	form.append('<input type="hidden" name="jsonCurrentBlock" value="[]" />');
	form.find('input[name="jsonCurrentBlock"]').val(JSON.stringify(suggestBlock.getSelection()));
}

function getDataSuggestArea(form){
	$.each(suggestArea.getSelection(), function(index,obj){
		
		form.append('<input type="hidden" name="iAreaIds" value="' + obj.id + '" />');
	});
	form.append('<input type="hidden" name="jsonCurrentArea" value="[]" />');
	form.find('input[name="jsonCurrentArea"]').val(JSON.stringify(suggestArea.getSelection()));
}

function doFindKeyword(){
	var form = $('#building-form');
	form.append('<input type="hidden" name="keyWord" /> ');
	$("input[name='keyWord']").val($('#keyWordSearch').val());
	form.append('<input type="hidden" name="accountId" value="' + ($('#chkOwner').prop('checked') ? accountId : 0) + '" />');
	form.append('<input type="hidden" name="numberOfPage" value="' + $('#currentPageKeyword').val() + '" />');
	getDataSuggestCorporation(form);
	getDataSuggestSalesChannel(form);
	getDataSuggestSalesChannel2(form);
	getDataSuggestBlock(form);
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

$(function() {
	createPagingKeyword();
	
	$(window).keydown(function(event){
		$(this).prop("disabled", true);
	    if(event.keyCode === 13 && !$('#keyWordSearch').is(':focus')) {
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
		$('#currentPageKeyword').val('0');
		doFindKeyword();
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
				if ([37, 38, 39, 40].indexOf(event.keyCode) >= 0) {
					event.preventDefault()
					return;
				}

				let value = this.getRawValue();
				if (!value) {
					this.setData([]);
					$('#' + target.container[0].id + ' input[type=text]').val('');
					event.preventDefault()
					return;
				}

				if (!isImeMode || (isImeMode && event.keyCode === 13)) {
					let suggest = this;
					$('#' + target.container[0].id + ' input[type=text]').val(value);
					$.ajax({
						url: createUrl(url),
						type: "POST",
						data: JSON.stringify({
							"text": value
						}),
						contentType: "application/json",
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

	createSuggest(suggestCorporation, "/sp/building/find/corporation");
	createSuggest(suggestSalesChannel, "/sp/building/find/i_sales_channel");
	createSuggest(suggestSalesChannel2, "/sp/building/find/i_sales_channel");
	createSuggest(suggestBlock, "/sp/building/find/i_block");
	createSuggest(suggestArea, "/sp/building/find/i_area");
	
	$("#radioStore").change(function() {
		if ($(this).prop("checked")) {
			window.location.replace(createUrl("/pc/store/list"));
		}
    });
})
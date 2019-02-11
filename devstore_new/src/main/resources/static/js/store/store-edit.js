function deleteParent(child){
	var item = child.closest(".media-list-item");
	if(item.hasClass("present-item")){
		item.addClass('hidden');
	}else if(item.hasClass("new-item")){
		item.remove();
	}
}
let suggestSalesAgencyTarget, suggestParticipatingStoreCorporation;
function getDataSuggetSalesAgencyTarget(data) {
	// SalesAgencyTarget
	$.each(suggestSalesAgencyTarget.getSelection(), function(index,obj){
		data.append('iSalesAgencyTargetId', obj.id);
	});
}

function getDataSuggetParticipatingStoreCorporation(data) {
	// ParticipatingStoreCorporation
	$.each(suggestParticipatingStoreCorporation.getSelection(), function(index,obj){
		data.append('participatingStoreCorporationId', obj.id);
	});
}

$(function() {
	var indexImage = 0;
	
	if ($('.j-implementation-date').length) {
		datepickerUtils.createDatePicker($('.j-implementation-date').find('input'));
	}
	
	$('#store-update-form').validate(validation);
	$('#j-update').click(function() {
		$("label.error").remove();
		let $this = this;
		$($this).addClass("disabled");
		if(!$('#store-update-form').valid()) {
			$($this).removeClass("disabled");
			createValidMessage();
			return;
		}
		let form = $('#store-update-form');
		// Get list object Images / Docs / Keyman / Active / History Meeting
		getListDataForRequest(form);
		// Create data pass
		var data = new FormData(form);
		// Append Data form
		$.each(form.serializeArray(),function(index,obj) {
			data.append(obj.name, obj.value);
		});
		$.each($('input[control-name="files"]'),function(index,obj) {
			data.append("files",$(this).prop("files")[0]);
		});
		getDataSuggetSalesAgencyTarget(data);
		getDataSuggetParticipatingStoreCorporation(data);
		jQuery.ajax({
		    url: createUrl("/pc/store/update"),
		    data: data,
		    cache: false,
		    contentType: false,
		    processData: false,
		    method: 'POST',
		    type: 'POST', // For jQuery < 1.9
		    success: function(response){
		    	window.location.replace(createUrl("/pc/store/detail/" + response));
		    },
			error: function() {
		    	alert("更新に失敗しました。");
		    }
		});
	});
	
	// Add Image
	$('#addImage').click(function(){
		indexImage++;
		let htmltext = '<div class="media-list-item has-trash new-item">';
		htmltext += '<div class="row">';
		htmltext += '<input class="object-property" type="hidden" propertyName="id" value="0">';
		htmltext += '<div class="col-md-2">';
		htmltext += '<div class="img">';
		htmltext += '<img class="media-object img-rounded">';
		htmltext += '</div>';
		htmltext += '</div>';
		htmltext += '<div class="col-md-10">';
		htmltext += '<div class="form-group">';
		htmltext += '<div class="row">';
		htmltext += '<div class="col-md-2">';
		htmltext += '<label>ファイル</label>';
		htmltext += '</div>';
		htmltext += '<div class="col-md-9">';
		htmltext += '<div class="input-group">';
		htmltext += '<input type="text" class="form-control" input-file="openImageFile' + indexImage +'" placeholder="">';
		htmltext += '<span class="input-group-btn">';
		htmltext += '<label class="btn btn-primary" for="openImageFile' + indexImage +'">選択</label>';
		htmltext += '<input type="file" control-name="files" class="openImageFile" style="display:none" id="openImageFile' + indexImage +'" name="openImageFile' + indexImage +'" accept=".png, .gif, .jpg, .jpeg">';
		htmltext += '</div>';
		htmltext += '</div>';
		htmltext += '</div>';
		htmltext += '</div>';
		htmltext += '<div class="form-group">';
		htmltext += '<div class="row">';
		htmltext += '<div class="col-md-2">';
		htmltext += '<label>サイズ</label>';
		htmltext += '</div>';
		htmltext += '<div class="col-md-9">';
		htmltext += '<label class="sizefile"></label>';
		htmltext += '</div>';
		htmltext += '<div class="col-md-1">';
		htmltext += '<div class="trash j-delete-item" onclick="deleteParent($(this))">';
		htmltext += '<i class="fa fa-trash"></i>';
		htmltext += '</div>';
		htmltext += '</div>';
		htmltext += '</div>';
		htmltext += '</div>';
		htmltext += '<div class="form-group">';
		htmltext += '<div class="row">';
		htmltext += '<div class="col-md-2">';
		htmltext += '<label>コメント</label>';
		htmltext += '</div>';
		htmltext += '<div class="col-md-9">';
		htmltext += '<input class="form-control" placeholder="コメント" propertyName="comment" >';
		htmltext += '</div>';
		htmltext += '</div>';
		htmltext += '</div>';
		htmltext += '<div class="form-group">';
		htmltext += '<div class="row">';
		htmltext += '<div class="col-md-2">';
		htmltext += '</div>';
		htmltext += '<div class="col-md-9 radio">';
		htmltext += '<label><input type="checkbox" class="isDefaultImage" propertyName="isDefaultImage" value="false">一覧表示に使用</label>';
		htmltext += '</div>';
		htmltext += '</div>';
		htmltext += '</div>';
		htmltext += '</div>';
		htmltext += '</div>';
		htmltext += '</div>';
		$('#image > .tab-content-body > .media-list').prepend(htmltext);
		$('#openImageFile' + indexImage).rules("add", {
			hasfile: true,
		});
		resetDefaultImage();
	});
	
	$("#image").on("change",".openImageFile",function(){
		let parent = $(this).closest(".media-list-item");
		let files = $(this).prop("files");
		let fileExtension = ['image/jpeg', 'image/png', 'image/gif'];
		if(files.length > 0) {
			if ($.inArray(this.files[0].type, fileExtension) == -1) {
				alert("画像の拡張子はjpg、pngまたはgifでアップロードしてください");
			} else {
				parent.find('input[input-file="' + $(this).attr("id") + '"]').val(files[0].name);
				parent.find('.sizefile').html(Math.round(files[0].size/1024) + 'KB');
				var reader = new FileReader();
				reader.readAsDataURL(files[0]);
				reader.onload = function () {
					let dataUrl = reader.result;
					parent.find('.media-object').attr('src', dataUrl);
				};
			}
		}
		else {
			parent.find('input[input-file="' + $(this).attr("id") + '"]').val('');
			parent.find('.sizefile').html('');
			parent.find('.media-object').attr('src','');
		}
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
			'keyup': function(e, m, v) {
				let value = this.getRawValue();
				
				if (event.keyCode == 38 || event.keyCode == 40) { return; }
				if (!value) { return; }

				if (!isImeMode || (isImeMode && event.keyCode === 13)) {
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

	suggestSalesAgencyTarget = $('#j-form-sales-agency-target').magicSuggest({
		data: (iShop.shop != null && iShop.shop.iSalesAgencyTarget != null) ? [{
			id: iShop.shop.iSalesAgencyTargetId,
			name: iShop.shop.iSalesAgencyTarget.salesAgencyTargetName
		}] : [],
		value: (iShop.shop != null && iShop.shop.iSalesAgencyTarget != null) ? [iShop.shop.iSalesAgencyTargetId] : [],
		noSuggestionText: "候補がありません",
		maxSelection: 1,
		maxSelectionRenderer: function () {
			return "";
		},
		allowFreeEntries: false
	});

	createSuggest(suggestSalesAgencyTarget, '/sp/building/find/i_sales_agency_target')
	
	suggestParticipatingStoreCorporation = $('#j-form-participating-store-corporation').magicSuggest({
		data : (iShop.shop != null && iShop.shop.participatingStoreCorporation) ? [ {
			id : iShop.shop.participatingStoreCorporationId,
			name : iShop.shop.participatingStoreCorporation.corporationName
		} ] : [],
		value : (iShop.shop != null && iShop.shop.participatingStoreCorporation) ? [ iShop.shop.participatingStoreCorporationId ] : [],
		noSuggestionText: "候補がありません",
		maxSelection : 1,
		maxSelectionRenderer: function () {
			return "";
		},
		allowFreeEntries : false
	});

	createSuggest(suggestParticipatingStoreCorporation, '/sp/building/find/participating_store_corporation')
	
	$(document).on(
			'change',
			'input[type=radio][name=isDefaultImage]',
			function() {
				$('input[type=radio][name=isDefaultImage]').val('false');
				$(this).val('true');
			});
	$('a[href]:not([href*="#"],[target="_blank"])').click(function(e) {
		if (!confirm('他のページに移動します。 内容が消えますがよろしいでしょうか？')) {
			return false;
		}
	});
	
	$("#store_edit_rent_start_date").change(function(){
		let startDate = $(this).val();
		let endDate = $("#store_edit_rent_end_date").val();
		let numOfYear = $("#store_edit_rent_year_view").val();
		if(endDate !== undefined && endDate){
			calcRentYear();
		} else if(numOfYear !== undefined && numOfYear){
			endDate = calcRentEndDate(startDate, numOfYear);
			$("#store_edit_rent_end_date").val(endDate);
			$("#store_edit_rent_year_value").val($(this).val());
		}
	});

	$("#store_edit_rent_end_date").change(function(){
		calcRentYear();
	});
	
	$("#store_edit_rent_year_view").change(function(){
		let endDate = calcRentEndDate($("#store_edit_rent_start_date").val(), $(this).val());
		$("#store_edit_rent_end_date").val(endDate);
		$("#store_edit_rent_year_value").val($(this).val());
	});
});

function calcRentYear(){
	let numOfYear = calcYear($("#store_edit_rent_start_date").val(),$("#store_edit_rent_end_date").val());
	if(numOfYear === +numOfYear && numOfYear !== (numOfYear|0)){
		$("#store_edit_rent_year_view").val(Math.round(numOfYear * 100) / 100);
		$("#store_edit_rent_year_value").val(numOfYear);
	} else{
		$("#store_edit_rent_year_view").val('');
		$("#store_edit_rent_year_value").val('');
	}
}

function calcRentEndDate(startDate, numOfYear) {
    if (!startDate) {
      return "";
    }
    if (isNaN(numOfYear) || !numOfYear || numOfYear < 0) {
      return "";
    }
    
    let diffDate = numOfYear * 365;
    let endDate = new Date(startDate);
    endDate.setDate(endDate.getDate() + diffDate);
    result = endDate.toISOString().slice(0, 10);
    return result;
}

function calcYear(startDate, endDate) {
    var result = 0;
    var diff = new Date(endDate) - new Date(startDate);
    if (isNaN(diff)) {
        result = "";
    } else if (diff > 0) {
        var diffDate = new Date(diff);
        let diffDay = Math.ceil(diff / (1000 * 3600 * 24));
        result = diffDay / 365;
    }
	return result;
}

function getListDataForRequest(form){
	form.find('.media-list').each(function() {
		var objectName = $(this).attr('objectName');
		$(this).find('.media-list-item.has-trash').each(function( index ) {
			form.append('<input type="hidden" name="' + objectName + '[' + index + '].isDeleted" value="' + $(this).hasClass("hidden") + '" />');
			$(this).find('[propertyName]').each(function() {
				form.append('<input type="hidden" name="' + objectName + '[' + index + '].' + $(this).attr('propertyName') + '" value="' + $(this).val() + '" />');
			});
		});
	});
}

function createValidMessage(){
	var message = [];
	$( ".tab-pane" ).each(function() {
		if($(this).find('label.error').length > 0){
			message.push($('ul > li > a[href="#'+$(this).attr('id')+'"]').html() + 'タブの項目に誤りがあります');
		}
	});
	
	if(message.length > 0){
		alert(message.join('\n'));
	}
}

function resetDefaultImage(){
	$("input[class*='isDefaultImage']").change(function() {
	    if (this.checked) {
	    	$("input:checkbox[class=isDefaultImage]").removeAttr("checked");
	        $("input:checkbox[class=isDefaultImage]").val("false");
	        this.checked = true;
	        $(this).val("true");
	    } else {
	    	$(this).val("false");
	    }
	});
}
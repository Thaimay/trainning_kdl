let indexFile = 0;
let indexImage = 0;
let indexMeeting = 0;
let indexActivation = 0;
let indexBuildingSale = 0;
let createBuildingDatePicker = function($target, dateFormat) {
	if (dateFormat == undefined) {
		dateFormat = "yy-mm-dd";
	}
	$target.datepicker({
		dateFormat : dateFormat
	});
};

let suggestCorporationGrId, suggestCorporation, suggestiPrefecturesId, suggestSalesChannel, suggestSalesChannel2, suggestiBlockId, suggestiAreaId, suggestBuildingGroupId, suggestTenant, suggestStoreDevelopment, suggestBranchSales, suggestHrLeader, suggestKeyman, suggestPmCorporation;
$(function() {
	if ($('.j-implementation-date').length) {
		createBuildingDatePicker($('.j-implementation-date').find('input'));
	}
	if ($("#building-form").length) {
		$('.salesChannel').hide();
		let salesChannelInit = $('option:selected', $("#salesChannel")).attr(
				'text');
		$('.' + salesChannelInit).show();
		let corporationGrIdInit = $("#corporationGroup").val();
		let corporationInit = $("#corporationTemp").find(
				"option[corporation_gr_id=" + corporationGrIdInit + "]")
				.clone();
		// Create magicSuggest
		suggestCorporationGrId = $('#j-form-corporationGrId').magicSuggest({
			maxSelection : 1,
			allowFreeEntries : false,
			maxSelectionRenderer: function () {
				return "";
			}
		});
		suggestCorporation = $('#j-form-corporation').magicSuggest({
			maxSelection : 1,
			allowFreeEntries : false,
			maxSelectionRenderer: function () {
				return "";
			}
		});
		suggestiPrefecturesId = $('#j-form-iPrefecturesId').magicSuggest({
			maxSelection : 1,
			allowFreeEntries : false,
			maxSelectionRenderer: function () {
				return "";
			}
		});
		suggestSalesChannel = $('#j-form-salesChannel').magicSuggest({
			maxSelection : 1,
			allowFreeEntries : false,
			maxSelectionRenderer: function () {
				return "";
			}
		});
		suggestSalesChannel2 = $('#j-form-salesChannelCd2').magicSuggest({
			maxSelection : 1,
			allowFreeEntries : false,
			maxSelectionRenderer: function () {
				return "";
			}
		});
		suggestiBlockId = $('#j-form-iBlockId').magicSuggest({
			maxSelection : 1,
			allowFreeEntries : false,
			maxSelectionRenderer: function () {
				return "";
			}
		});
		suggestiAreaId = $('#j-form-iAreaId').magicSuggest({
			maxSelection : 1,
			allowFreeEntries : false,
			maxSelectionRenderer: function () {
				return "";
			}
		});
		suggestBuildingGroupId = $('#j-form-buildingGroupId').magicSuggest({
			maxSelection : 1,
			allowFreeEntries : false,
			maxSelectionRenderer: function () {
				return "";
			}
		});

		suggestTenant = $('#j-form-tenant').magicSuggest();
		suggestStoreDevelopment = $('#j-form-store-development').magicSuggest({
			allowFreeEntries : false
		});
		suggestBranchSales = $('#j-form-branch-sales').magicSuggest({
			allowFreeEntries : false
		});
		suggestHrLeader = $('#j-form-hr-leader').magicSuggest({
			allowFreeEntries : false
		});
		suggestKeyman = $('#j-form-keyman').magicSuggest({
			allowFreeEntries : false
		});
		suggestPmCorporation = $('#j-form-pmCorporation').magicSuggest({
			allowFreeEntries : false
		});
		$("#building-form").validate(validation);
		$("#add-building-sales-form").validate(validationAddBuildingSales);
	}

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

	createSuggest(suggestCorporationGrId, '/sp/building/find/i_corporation_group');
	createSuggest(suggestPmCorporation, '/sp/building/find/corporation');
	createSuggest(suggestiPrefecturesId, '/sp/building/find/i_prefectures')

	var isImeMode = false;
	$(suggestCorporation).on({
		'keydown': function (keydownEvent, ms, event) {
			if (event.keyCode === 229) {
				isImeMode = true
			} else {
				isImeMode = false
			}
			console.log("event.keyCode", event.keyCode);
		},
		'keyup': function (keyupEvent, ms, event) {
			let corporationGrId = suggestCorporationGrId.getSelection();
			if (event.keyCode == 38 || event.keyCode == 40) { return; }

			let value = this.getRawValue();
			console.log('isImeMode', isImeMode, "event.keyCode", event.keyCode);
			
			if (!isImeMode || (isImeMode && event.keyCode === 13)) {
				if (value && corporationGrId.length > 0) {
					let suggest = this;
					$.ajax({
						url : createUrl('/sp/building/find/corporation'),
						type : "POST",
						data : JSON.stringify({
							"text" : value
						}),
						contentType : "application/json",
						success : function(response) {
							suggest.setData(response.filter(function(obj) {
								return obj.idParent === corporationGrId[0].id;
							}));
						}
					});
				}
			}
		},
		'blur': function (e) {
			isImeMode = false
		}
	});

	createSuggest(suggestSalesChannel, '/sp/building/find/i_sales_channel');
	createSuggest(suggestSalesChannel2, '/sp/building/find/i_sales_channel');
	createSuggest(suggestiBlockId, '/sp/building/find/i_block');
	createSuggest(suggestiAreaId, '/sp/building/find/i_area');
	createSuggest(suggestBuildingGroupId, "/sp/building/find/building_parent");
	createSuggest(suggestTenant, "/sp/building/find/tenant");
	createSuggest(suggestStoreDevelopment, "/sp/building/find/account");
	createSuggest(suggestBranchSales, "/sp/building/find/account");
	createSuggest(suggestHrLeader, "/sp/building/find/account");
	createSuggest(suggestKeyman, "/sp/building/find/i_business_card");
	
	$(suggestCorporationGrId).on('selectionchange', function(e, m, v) {
		suggestCorporation.clear();
	});
	$(suggestSalesChannel).on("selectionchange", function() {
		let text = this.getSelection();
		$('.salesChannel').hide();
		if (text.length > 0) {
			//$('.' + text[0].name).show();
		}
	});

	// Add keyman
	$("#addKeyman")
		.click(
			function () {
				var selectionKeyman = suggestKeyman.getSelection();
				if (selectionKeyman.length === 0) {
					return;
				}
				$
					.each(
						selectionKeyman,
						function (index, keyman) {
							let selector = $("#keyman-id"
								+ keyman.id);
							if (selector.length === 0) {
								let htmltext = '<li class="media-list-item has-trash" objectName="buildingKeymanDto">';
								htmltext += '<div class="keyman-info">';
								htmltext += '<header class="keyman-info-header">';
								htmltext += '<div class="form-group">';
								htmltext += '<div class="row">';
								htmltext += '<div class="col-md-9">';
								htmltext += '<div class="inline-list">';
								htmltext += '<div id="j-star-keyman-container'
									+ keyman.id + '">';
								htmltext += '<input class="rating" type="radio" name="keyman-start'
									+ keyman.id
									+ '" value="1" style="display: none;">';
								htmltext += '<input class="rating" type="radio" name="keyman-start'
									+ keyman.id
									+ '" value="2" style="display: none;"> ';
								htmltext += '<input class="rating" type="radio" name="keyman-start'
									+ keyman.id
									+ '" value="3" style="display: none;">';
								htmltext += '</div>';
								htmltext += '<input propertyName="priority" type="hidden" value="0">';
								htmltext += '</div>';
								htmltext += '</div>';
								htmltext += '<div class="col-md-2 keyman-category">';
								htmltext += '<select class="form-control" propertyName="role">';
								htmltext += '<option value="HQ Leasing" selected>本部リーシング</option>';
								htmltext += '<option value="Building Leasing">館リーシング</option>';
								htmltext += '<option value="Building Promotion">館販促</option>';
								htmltext += '<option value="Interior Management">内装管理</option>';
								htmltext += '</select>';
								htmltext += '</div>';
								htmltext += '</div>';
								htmltext += '</div>';
								htmltext += '</header>';
								htmltext += '<div class="keyman-info-body">';
								htmltext += '<div class="form-group">';
								htmltext += '<div class="row">';
								htmltext += '<div class="col-md-2">';
								htmltext += '<label for="#form-keyman-name">名前</label>';
								htmltext += '</div>';
								htmltext += '<div class="col-md-9">';
								htmltext += '<label class="form-control">'
									+ keyman.name
									+ '</label>';
								htmltext += '<input type="hidden" propertyName="keyman.businessCardId" id="keyman-id'
									+ keyman.id
									+ '" class="keyman-id" value="'
									+ keyman.id + '">';
								htmltext += '<input type="hidden" value="'
									+ keyman.keymanId
									+ '" propertyName="keyman.id">';
								htmltext += '</div>';
								htmltext += '</div>';
								htmltext += '</div>';
								htmltext += '<div class="form-group">';
								htmltext += '<div class="row">';
								htmltext += '<div class="col-md-2">';
								htmltext += '<label>部署</label>';
								htmltext += '</div>';
								htmltext += '<div class="col-md-9">';
								htmltext += '<label class="form-control">'
									+ keyman.departmentName
									+ '</label>';
								htmltext += '</div>';
								htmltext += '</div>';
								htmltext += '</div>';
								htmltext += '<div class="form-group">';
								htmltext += '<div class="row">';
								htmltext += '<div class="col-md-2">';
								htmltext += '<label>役職</label>';
								htmltext += '</div>';
								htmltext += '<div class="col-md-9">';
								htmltext += '<label class="form-control">'
									+ keyman.positionName
									+ '</label>';
								htmltext += '</div>';
								htmltext += '</div>';
								htmltext += '</div>';
								htmltext += '<div class="form-group">';
								htmltext += '<div class="row">';
								htmltext += '<div class="col-md-2">';
								htmltext += '<label>メモ</label>';
								htmltext += '</div>';
								htmltext += '<div class="col-md-9">';
								htmltext += '<input class="form-control j-search-keyman" propertyName="note" type="text" value="" placeholder="メモ">';
								htmltext += '</div>';
								htmltext += '<div class="col-md-1">';
								htmltext += '<div class="trash j-delete-item">';
								htmltext += '<i class="fa fa-trash"></i>';
								htmltext += '</div>';
								htmltext += '</div>';
								htmltext += '</div>';
								htmltext += '</div>';
								htmltext += '<div class="form-group">';
								htmltext += '<div class="row">';
								htmltext += '<div class="col-md-2">';
								htmltext += '<label>法人</label>';
								htmltext += '</div>';
								htmltext += '<div class="col-md-9">';
								htmltext += '<input class="form-control" type="text" placeholder="法人" id="j-form-keyman-corporation'
									+ keyman.id + '">';
								htmltext += '<input class="form-control" propertyName="keyman.corporationId" type="hidden" id="form-keyman-corporation'
									+ keyman.id
									+ '" name="name-form-keyman-corporation'
									+ keyman.id + '">';
								htmltext += '</div>';
								htmltext += '</div>';
								htmltext += '</div>';
								htmltext += '</div>';
								htmltext += '</div>';
								htmltext += '</li>';
								$('#keymanList').append(
									htmltext);
								if ($('#j-star-keyman-container'
									+ keyman.id).length) {
									$(
										'#j-star-keyman-container'
										+ keyman.id)
										.rating(
											function (
												vote,
												event) {
												let element = event.toElement;
												let parent = $(
													element)
													.closest(
														".media-list-item");
												parent
													.find(
														'input[propertyName="priority"]')
													.val(
														vote);
											});
								}
								;
								let suggestCorporationKeyman = $(
									'#j-form-keyman-corporation'
									+ keyman.id)
									.magicSuggest(
										{
											maxSelection: 1,
											allowFreeEntries: false,
											maxSelectionRenderer: function () {
												return "";
											},
											data: (keyman.corporationId != null) ? [{
												id: keyman.corporationId,
												name: keyman.corporationName
											}]
												: [],
											value: (keyman.corporationId != null) ? [keyman.corporationId]
												: []
										});
								createSuggest(suggestCorporationKeyman, "/sp/building/find/corporation");
								
								$(suggestCorporationKeyman)
									.on(
										'selectionchange',
										function () {
											let value = this
												.getSelection();
											if (value.length > 0) {
												$(
													'#form-keyman-corporation'
													+ keyman.id)
													.val(
														value[0].id);
											} else {
												$(
													'#form-keyman-corporation'
													+ keyman.id)
													.val(
														'');
											}
										});
								if (keyman.corporationId != null) {
									$(
										'#form-keyman-corporation'
										+ keyman.id)
										.val(
											keyman.corporationId);
								}
							}
						})
				suggestKeyman.clear();
			});
	// Add activate
	$('#addActivate')
			.click(
					function() {
						indexActivation++;
						let htmltext = '<div class="media-list-item has-trash" objectName="activationDto">';
						htmltext += '<div class="row">';
						htmltext += '<div class="col-md-12">';
						htmltext += '<div class="form-group">';
						htmltext += '<div class="row">';
						htmltext += '<div class="col-md-2">';
						htmltext += '<label>フロア</label>';
						htmltext += '</div>';
						htmltext += '<div class="col-md-9">';
						htmltext += '<input class="form-control" name="j-active-floor'
								+ indexActivation
								+ '" id="j-active-floor'
								+ indexActivation
								+ '" propertyName="floor" type="text" placeholder="フロア">';
						htmltext += '</div>';
						htmltext += '</div>';
						htmltext += '</div>';
						htmltext += '<div class="form-group">';
						htmltext += '<div class="row">';
						htmltext += '<div class="col-md-2">';
						htmltext += '<label>活性化予定日</label>';
						htmltext += '</div>';
						htmltext += '<div class="col-md-9 j-implementation-date">';
						htmltext += '<input class="form-control" name="j-active-expectedDay'
								+ indexActivation
								+ '" id="j-active-expectedDay'
								+ indexActivation
								+ '" propertyName="expectedDay" type="text" placeholder="活性化予定日">';
						htmltext += '</div>';
						htmltext += '<div class="col-md-1">';
						htmltext += '<div class="trash j-delete-item">';
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
						htmltext += '<textarea class="form-control" propertyName="content" rows="3" name="j-active-content'
							+ indexActivation
							+ '" id="j-active-content'
							+ indexActivation + '" placeholder="コメント"></textarea>';
						htmltext += '</div>';
						htmltext += '</div>';
						htmltext += '</div>';
						htmltext += '</div>';
						htmltext += '</div>';
						htmltext += '</div>';
						$('#activate > .tab-content-body > .media-list')
								.append(htmltext);
						if ($('.j-implementation-date').length) {
							createBuildingDatePicker($('.j-implementation-date').find(
									'input'));
						}
						$('#j-active-expectedDay' + indexActivation).rules(
								"add", {
									required : true,
									dateISO : true,
									messages : {
										required : "活性化予定日を入力してください。",
										dateISO : "日付の形式を正しく入力してください。",
									}
								});
						$('#j-active-floor' + indexActivation).rules("add", {
							required : true,
							messages : {
								required : "フロアを入力してください。",
							}
						});
						$('#j-active-content' + indexActivation).rules("add", {
							required : true,
							messages : {
								required : "コメントを入力してください。",
							}
						});
					});
	// Add Related Document
	$('#addrelatedDocument')
			.click(
					function() {
						indexFile++;
						let htmltext = '<div class="media-list-item has-trash" objectName="buildingFileDto">';
						htmltext += '<div class="row">';
						htmltext += '<div class="col-md-12">';
						htmltext += '<div class="form-group">';
						htmltext += '<div class="row">';
						htmltext += '<div class="col-md-2">';
						htmltext += '<label>ファイル</label>';
						htmltext += '</div>';
						htmltext += '<div class="col-md-9">';
						htmltext += '<div class="input-group">';
						htmltext += '<input type="text" propertyName="displayName" class="form-control" input-file="openRelatedDoc'
								+ indexFile + '" placeholder="ファイル">';
						htmltext += '<span class="input-group-btn">';
						htmltext += '<label class="btn btn-primary" for="openRelatedDoc'
								+ indexFile + '" >選択</label>';
						htmltext += '<input type="file" control-name="docs" class="openRelatedDoc" style="display:none" name="openRelatedDoc'
								+ indexFile
								+ '" id="openRelatedDoc'
								+ indexFile
								+ '" accept=".pdf, .xls, .xlsx, .doc, .docx">';
						htmltext += '</span>';
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
						htmltext += '<div class="trash j-delete-item">';
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
						htmltext += '<input class="form-control" placeholder="コメント" propertyName="comment">';
						htmltext += '</div>';
						htmltext += '</div>';
						htmltext += '</div>';
						htmltext += '<div class="form-group">';
						htmltext += '<div class="row">';
						htmltext += '<div class="col-md-2">';
						htmltext += '<label>区分</label>';
						htmltext += '</div>';
						htmltext += '<div class="col-md-9">';
						htmltext += '<div class="btn-group" data-toggle="buttons">';
						htmltext += '<label class="btn btn-primary active">';
						htmltext += '<input type="radio" checked name="divisionFile'
								+ indexFile
								+ '" name-control="division" value="BUILDING" autocomplete="off">';
						htmltext += '<span class="fa fa-check">';
						htmltext += '</span>';
						htmltext += '<lable>館</lable>';
						htmltext += '</label>';
						htmltext += '<label class="btn btn-primary">';
						htmltext += '<input type="radio" name="divisionFile'
								+ indexFile
								+ '" name-control="division" value="PROJECT" autocomplete="off">';
						htmltext += '<span class="fa fa-check"></span>';
						htmltext += '<lable>DB進捗</lable>';
						htmltext += '</label>';
						htmltext += '<input propertyName="division" type="hidden" value="BUILDING">';
						htmltext += '<input propertyName="id" type="hidden" value="0">';
						htmltext += '</div>';
						htmltext += '</div>';
						htmltext += '</div>';
						htmltext += '</div>';
						htmltext += '</div>';
						htmltext += '</div>';
						htmltext += '</div>';
						$('#related-document > .tab-content-body > .media-list')
								.append(htmltext);
						$('#openRelatedDoc' + indexFile).rules("add", {
							hasfile : true
						});
					});
	$("#related-document").on(
			"change",
			".openRelatedDoc",
			function() {
				let parent = $(this).closest(".media-list-item");
				let files = $(this).prop("files");
				let fileExtension = ['application/pdf', 'application/vnd.openxmlformats-officedocument.wordprocessingml.document', 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet', 'application/vnd.openxmlformats-officedocument.presentationml.presentation'];
				if (files.length > 0) {
					if ($.inArray(this.files[0].type, fileExtension) == -1) {
						alert("画像の拡張子はpdf、xlsx、docx、またはpptxでアップロードしてください");
					} else {
						parent.find(
								'input[input-file="' + $(this).attr("id") + '"]')
								.val(files[0].name);
						parent.find('.sizefile').html(
								Math.round(files[0].size / 1024) + 'KB');
					}
				} else {
					parent.find(
							'input[input-file="' + $(this).attr("id") + '"]')
							.val('');
					parent.find('.sizefile').html('');
				}
			});
	// Add Related Document
	$('#addImage')
			.click(
					function() {
						indexImage++;
						let htmltext = '<div class="media-list-item has-trash" objectName="buildingImageDto">';
						htmltext += '<div class="row">';
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
						htmltext += '<input type="text" class="form-control" input-file="openImageFile'
								+ indexImage + '" placeholder="" readonly>';
						htmltext += '<span class="input-group-btn">';
						htmltext += '<label class="btn btn-primary" for="openImageFile'
								+ indexImage + '" >選択</label>';
						htmltext += '<input type="file" control-name="imgs" class="openImageFile" style="display:none" id="openImageFile'
								+ indexImage
								+ '" name="openImageFile'
								+ indexImage
								+ '" accept=".png, .gif, .jpg, .jpeg">';
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
						htmltext += '<div class="trash j-delete-item">';
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
						htmltext += '<input class="form-control" placeholder="コメント" propertyName="comment">';
						htmltext += '</div>';
						htmltext += '</div>';
						htmltext += '</div>';
						htmltext += '<div class="form-group">';
						htmltext += '<div class="row">';
						htmltext += '<div class="col-md-2">';
						htmltext += '<label>区分</label>';
						htmltext += '</div>';
						htmltext += '<div class="col-md-9">';
						htmltext += '<div class="btn-group" data-toggle="buttons">';
						htmltext += '<label class="btn btn-primary active">';
						htmltext += '<input type="radio" name="divisionImage'
								+ indexImage
								+ '" name-control="division" value="BUILDING" autocomplete="off" checked>';
						htmltext += '<span class="fa fa-check"></span>';
						htmltext += '<lable>館</lable>';
						htmltext += '</label>';
						htmltext += '<label class="btn btn-primary">';
						htmltext += '<input type="radio" name="divisionImage'
								+ indexImage
								+ '" name-control="division" value="PROJECT" autocomplete="off" >';
						htmltext += '<span class="fa fa-check"></span>';
						htmltext += '<lable>DB進捗</lable>';
						htmltext += '</label>';
						htmltext += '<input propertyName="division" type="hidden" value="BUILDING">';
						htmltext += '<input propertyName="id" type="hidden" value="0">';
						htmltext += '</div>';
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
						$('#image > .tab-content-body > .media-list').append(
								htmltext);
						$('#openImageFile' + indexImage).rules("add", {
							hasfile : true,
						});
						resetDefaultImage();
					});
	$("#image").on(
			"change",
			".openImageFile",
			function() {
				let parent = $(this).closest(".media-list-item");
				let files = $(this).prop("files");
				let fileExtension = ['image/jpeg', 'image/png', 'image/gif'];
				if (files.length > 0) {
					if ($.inArray(this.files[0].type, fileExtension) == -1) {
						alert("画像の拡張子はjpg、pngまたはgifでアップロードしてください");
					} else {
						parent.find(
								'input[input-file="' + $(this).attr("id") + '"]')
								.val(files[0].name);
						parent.find('.sizefile').html(
								Math.round(files[0].size / 1024) + 'KB');
						var reader = new FileReader();
						reader.readAsDataURL(files[0]);
						reader.onload = function() {
							let dataUrl = reader.result;
							parent.find('.media-object').attr('src', dataUrl);
						};
					}
				} else {
					parent.find(
							'input[input-file="' + $(this).attr("id") + '"]')
							.val('');
					parent.find('.sizefile').html('');
					parent.find('.media-object').attr('src', '');
				}
			});
	$('#addMeetingHistory')
			.click(
					function() {
						indexMeeting++;
						let htmltext = '<div class="media-list-item has-trash" objectName="buildingMeetingDto">';
						htmltext += '<div class="row">';
						htmltext += '<div class="col-md-12">';
						htmltext += '<div class="form-group">';
						htmltext += '<div class="row">';
						htmltext += '<label class="col-sm-2 control-label">総会名</label>';
						htmltext += '<div class="col-sm-9">';
						htmltext += '<input class="form-control" type="text" placeholder="総会名" propertyName="name">';
						htmltext += '</div>';
						htmltext += '</div>';
						htmltext += '</div>';
						htmltext += '<div class="form-group">';
						htmltext += '<div class="row">';
						htmltext += '<label class="col-sm-2 control-label">総会実施日</label>';
						htmltext += '<div class="col-sm-9 j-implementation-date">';
						htmltext += '<input class="form-control" id="j-meeting-date'
								+ indexMeeting
								+ '" name="j-meeting-date'
								+ indexMeeting
								+ '" type="text" placeholder="総会実施日" propertyName="date">';
						htmltext += '</div>';
						htmltext += '<div class="col-md-1">';
						htmltext += '<div class="trash j-delete-item">';
						htmltext += '<i class="fa fa-trash"></i>';
						htmltext += '</div>';
						htmltext += '</div>';
						htmltext += '</div>';
						htmltext += '</div>';
						htmltext += '<div class="form-group">';
						htmltext += '<div class="row">';
						htmltext += '<label class="col-sm-2 control-label">出席者</label>';
						htmltext += '<div class="col-sm-9">';
						htmltext += '<input class="form-control" type="text" placeholder="出席者" propertyName="attendee">';
						htmltext += '</div>';
						htmltext += '</div>';
						htmltext += '</div>';
						htmltext += '</div>';
						htmltext += '</div>';
						htmltext += '</div>';
						$('#general-meeting > .tab-content-body > .media-list')
								.append(htmltext);
						if ($('.j-implementation-date').length) {
							createBuildingDatePicker($('.j-implementation-date').find(
									'input'), 'yy-mm-dd 00:00');
						}
						$('#j-meeting-date' + indexMeeting).rules("add", {
							inspDateTime : true,
							messages : {
								inspDateTime : "日付の形式を正しく入力してください。",
							}
						});
					});
	$(document)
			.on(
					'click',
					'.j-add-item-building-sales',
					function() {
						indexBuildingSale++;
						let parent = $(this).closest(".media-list-item");
						let year = parent.attr("bulding-sales-year").substr(0,
								4)
								+ '-'
								+ parent.attr("bulding-sales-year")
										.substr(4, 2) + '-01';
						let type = parent.attr("bulding-sales-type");

						let htmltext = '<tbody class="new-item" objectName="buildingSalesDto">';
						htmltext += '<tr>';
						htmltext += '<th class="category" rowspan="3"><input propertyName="division" name="j-bsales-division-new"'
								+ indexBuildingSale
								+ ' type="text" placeholder="区分" /></th>';
						htmltext += '<td>売上</td>';
						htmltext += '<td class="ta-r"><input propertyName="sales" name="j-bsales-sales-new"'
								+ indexBuildingSale + ' type="number" /></td>';
						htmltext += '<th class="text-right" style="width:40px;max-width:40px;" rowspan="3"><div class="trash j-delete-item-building-sales" style="position: relative;margin-top: 0px;"><i class="fa fa-trash"></div></th>';
						htmltext += '<tr>';
						htmltext += '<td>面積</td>';
						htmltext += '<td class="ta-r"><input propertyName="area" name="j-bsales-area-new"'
								+ indexBuildingSale + ' type="number" /></td>';
						htmltext += '</tr>';
						htmltext += '<tr>';
						htmltext += '<td>月坪</td>';
						htmltext += '<td class="ta-r"><input propertyName="monthBasis" name="j-bsales-monthBasis-new"'
								+ indexBuildingSale + ' type="number" /></td>';
						htmltext += '</tr>';
						htmltext += '<input type=hidden propertyName="id" value="0">';
						htmltext += '<input type=hidden propertyName="financialMonth" value="'
								+ year + '">';
						htmltext += '<input type=hidden propertyName="type" value="'
								+ type + '">';
						htmltext += '</tbody>';
						$(this).closest("tbody").before(htmltext);
					});
	$(document).on(
			'click',
			'.j-delete-item-building-sales',
			function() {
				let parent = $(this).closest("tbody");
				if (parent.hasClass("present-item")) {
					$(parent).addClass("hidden");
				} else if (parent.hasClass("new-item")) {
					$(parent).remove();
				}
				parent = $(this).closest(".media-list-item").find("tbody").not(
						".addBuildingSalesChild,.hidden");
				if (parent.length === 0) {
					$(this).closest(".media-list-item").addClass("hidden");
				}
			});
	$(document).on('click', '.j-delete-item', function() {
		let parent = $(this).closest(".media-list-item");
		$(parent).remove();
	});
	$(document).on(
			'change',
			'input[type=radio][name-control=division]',
			function() {
				let parent = $(this).closest(".media-list-item");
				parent.find('input[type=hidden][propertyName="division"]').val(
						this.value);
			});
	$('input[type=radio][name=isBuildingGroup]').change(function() {
		if (this.value === "true") {
			$("#trbuildingGroupId").hide();
			$("#buildingGroupId").val(0);
		} else {
			$("#trbuildingGroupId").show();
		}
	});
	$('#add-building-sales')
			.click(
					function() {
						$('#j-form-building-sales-division-error').remove();
						let form = $('#add-building-sales-form');
						if (!form.valid()) {
							return;
						}
						let fmonth = form.find('input[name="add-f-month"]')
								.val();
						let type = form.find('select[name="add-type"]').val();
						let typeName = form.find('select[name="add-type"]').children("option").filter(":selected").text();
						let division = form.find('select[name="add-division"]').val();
						let divisionName = form.find('select[name="add-division"]').children("option").filter(":selected").text();
						let sale = form.find('input[name="add-sales"]').val();
						let area = form.find('input[name="add-area"]').val();
						let monthBasis = form.find(
								'input[name="add-month-basis"]').val();
						let valid = $('#buildingSales').find(
								'li.media-list-item[bulding-sales-year="'
										+ fmonth + '"][bulding-sales-type="'
										+ type + '"]').find(
								'input[type="hidden"][propertyname="division"][value="'
										+ division + '"]');

						if (valid.length > 0
								&& !valid.closest('tbody').hasClass("hidden")) {
							$('#j-form-building-sales-division')
									.after(
											'<label id="j-form-building-sales-division-error" class="error" for="j-form-building-sales-division">館売上が既に存在します。</label>');
							return;
						}
						indexBuildingSale++;
						let liType = $('li.media-list-item[bulding-sales-type="'
								+ type + '"]');
						if (liType.length > 0) {
							let yearSales = $('li.media-list-item[bulding-sales-year="'
									+ fmonth
									+ '"][bulding-sales-type="'
									+ type
									+ '"]');
							if (yearSales.length > 0) {
								$(yearSales).removeClass("hidden");
								$(yearSales).find('table').append(
										createHtmlBuildingSalesChild(fmonth,
												type, typeName, division,
												divisionName, sale, area,
												monthBasis));
							} else {
								let year = [];
								$
										.each(
												$('li.media-list-item[bulding-sales-type="'
														+ type + '"]'),
												function() {
													year
															.push($(this)
																	.attr(
																			"bulding-sales-year"));
												})
								if (year.length === 0) {
									$('#buildingSales').append(
											createHtmlBuildingSalesItem(fmonth,
													type, typeName, division,
													divisionName, sale, area,
													monthBasis));
								} else {
									year.push(fmonth);
									year = year.sort().reverse();
									let indexYear = year.indexOf(fmonth);
									if (indexYear === 0) {
										$(
												'#buildingSales > li.media-list-item.hide')
												.after(
														createHtmlBuildingSalesItem(
																fmonth, type,
																typeName,
																division,
																divisionName,
																sale, area,
																monthBasis));
									} else {
										let yearPre = year[indexYear - 1];
										$(
												'li.media-list-item[bulding-sales-type="'
														+ type
														+ '"][bulding-sales-year="'
														+ yearPre + '"]')
												.after(
														createHtmlBuildingSalesItem(
																fmonth, type,
																typeName,
																division,
																divisionName,
																sale, area,
																monthBasis));
									}
								}
							}
						} else {
							$('#buildingSales').append(
									createHtmlBuildingSalesItem(fmonth, type,
											typeName, division, divisionName,
											sale, area, monthBasis));
						}

						form.find('input[name="add-f-month"]').val('');						
						form.find('select[name="add-type"]').val(form.find('select[name="add-type"]').children("option").filter(":first").val());
						form.find('select[name="add-division"]').val(form.find('select[name="add-division"]').children("option").filter(":first").val());						
						form.find('input[name="add-sales"]').val('');
						form.find('input[name="add-area"]').val('');
						form.find('input[name="add-month-basis"]').val('');
						$('#addSalesGroup').modal('hide');
						$('li.media-list-item[bulding-sales-type]').hide();
						$(
								'li.media-list-item[bulding-sales-type="'
										+ $(
												'input[type=radio][name=sales-type]:checked')
												.val() + '"]').show();
						
						if ($('.j-dropdown').length) {
						    var $dropdown = $('.j-dropdown');

						    $dropdown.find('.j-dropdown-header').on({
						      'click': function(event) {
						        $target = $(event.currentTarget);

						        if ($target.hasClass('isOpen')) {
						          handleDropDownState($target, false);
						        } else {
						          handleDropDownState($target, true);
						        }
						      }
						    })
						    
						    $('.j-dropdown-header.isOpen').next('.j-dropdown-body').show();

						    var handleDropDownState = function ($target, doOpen) {
						      if (doOpen) {
						        $target.addClass('isOpen');
						        $target.next('.j-dropdown-body').slideDown();
						      } else {
						        $target.removeClass('isOpen');
						        $target.next('.j-dropdown-body').slideUp();
						      }
						    }
						  }
					});
	$(document).on(
			'click',
			'#j-detail-create',
			function() {
				// Clone form data
				$("label.error").remove();
				let form = $('#building-form');
				let $this = this;
				$($this).addClass("disabled");
				if (!form.valid()) {
					$($this).removeClass("disabled");
					checkValidSuggest();
					createValidMessage();
					return;
				}
				if(!checkValidSuggest()) {
					$($this).removeClass("disabled");
					createValidMessage();
					return;
				}
				// Create data pass
				var data = new FormData();
				getDataSuggestCorporation(data);
				getDataSuggestiPrefecturesId(data);
				getDataSuggestSalesChannel(data);
				getDataSuggestSalesChannel2(data);
				getDataSuggestiBlockId(data);
				getDataSuggestiAreaId(data);
				getDataSuggestBuildingGroupId(data);
				// Append Data form
				$.each(form.serializeArray(), function(index, obj) {
					data.append(obj.name, obj.value);
				});
				// Get list object Images / Docs / Keyman / Active / History
				// Meeting
				getListDataForRequest(data);
				$.each(form.find('input[control-name="imgs"]'), function(index,
						obj) {
					data.append("imgs", $(this).prop("files")[0]);
				});
				$.each(form.find('input[control-name="docs"]'), function(index,
						obj) {
					data.append("docs", $(this).prop("files")[0]);
				});
				getDataSuggestTennant(data);
				getDataSuggestPersonalDevelop(data);
				getDataSuggetPmCorporation(data);
				jQuery.ajax({
					url: createUrl("/pc/building/create"),
					data : data,
					cache : false,
					contentType : false,
					processData : false,
					method : 'POST',
					type : 'POST', // For jQuery < 1.9
					success : function(response) {
						window.location.replace(createUrl("/pc/building/list"));
					},
					error : function() {
						alert("作成に失敗しました。");
						$($this).removeClass("disabled");
					}
				});
			});
	$(document).on(
			'change',
			'input[type=radio][name=sales-type]',
			function() {
				$('li.media-list-item[bulding-sales-type]').hide();
				$(
						'.media-list-item[bulding-sales-type="' + $(this).val()
								+ '"]').show();
			});
	$(document).on(
			'change',
			'input[type=radio][name=isDefaultImage]',
			function() {
				$('input[type=radio][name=isDefaultImage]').val('false');

				if($(this).attr('checked') == undefined){
					$(this).attr('checked', true);
					$(this).val('true')
				}else{
					$(this).attr('checked', false);
				}
			});
	$('a[href]:not([href*="#"],[target="_blank"])').click(function(e) {
		if (!confirm('他のページに移動します。 内容が消えますがよろしいでしょうか？')) {
			return false;
		}
	});
});
function createHtmlBuildingSalesItem(fmonth, type, typeName, division,
		divisionName, sale, area, monthBasis) {
	let textBuildingSale = '<li class="media-list-item has-trash new-item" bulding-sales-year="'
			+ fmonth + '" bulding-sales-type="' + type + '" >';
	textBuildingSale += '<div class="building-basic-info j-dropdown">';
	textBuildingSale += '<header class="building-basic-info-header j-dropdown-header isOpen">';
	textBuildingSale += '<h3 class="title">' + fmonth + '年度</h3>';
	textBuildingSale += '</header>';
	textBuildingSale += '<div class="building-basic-info-body j-dropdown-body" style="display: block;">';
	textBuildingSale += '<div class="j-dropdown-body-inner">';
	textBuildingSale += '<table class="table">';
	textBuildingSale += createHtmlBuildingSalesChild(fmonth, type, typeName,
			division, divisionName, sale, area, monthBasis);
	textBuildingSale += '</table>';
	textBuildingSale += '</div>';
	textBuildingSale += '</div>';
	textBuildingSale += '</div>';
	textBuildingSale += '</li>';
	return textBuildingSale;
}
function createHtmlBuildingSalesChild(year, type, typeName, division,
		divisionName, sale, area, monthBasis) {
	let htmltext = '<tbody class="new-item" objectName="buildingSalesDto">';
	htmltext += '<tr>';
	htmltext += '<td style="width:300px;min-width:300px;max-width:300px;"><label>'
			+ typeName + '</label></td>';
	htmltext += '<td>売上</td>';
	htmltext += '<td class="ta-r"><input propertyName="sales" name="j-bsales-sales-new"'
			+ indexBuildingSale + ' type="number" value="' + sale + '" /></td>';
	htmltext += '</tr>';
	htmltext += '<tr>';
	htmltext += '<td style="width:300px;min-width:300px;max-width:300px;"><label>'
			+ divisionName + '</label></td>';
	htmltext += '<td>面積</td>';
	htmltext += '<td class="ta-r"><input propertyName="area" name="j-bsales-area-new"'
			+ indexBuildingSale + ' type="number" value="' + area + '" /></td>';
	htmltext += '</tr>';
	htmltext += '<tr>';
	htmltext += '<td style="width:300px;min-width:300px;max-width:300px;"><label><div class="trash j-delete-item-building-sales" style="position: relative;margin-top: 0px;"><i class="fa fa-trash"></div></label></td>';
	htmltext += '<td>月坪</td>';
	htmltext += '<td class="ta-r"><input propertyName="monthBasis" name="j-bsales-monthBasis-new"'
			+ indexBuildingSale
			+ ' type="number" value="'
			+ monthBasis
			+ '"/></td>';
	htmltext += '</tr>';
	htmltext += '<input type="hidden" propertyName="id" value="0">';
	htmltext += '<input type="hidden" propertyName="financialMonth" value="'
			+ year + '-01-01">';
	htmltext += '<input type="hidden" propertyName="type" value="' + type
			+ '">';
	htmltext += '<input type="hidden" propertyName="division" name="j-bsales-division-new'
			+ indexBuildingSale + '" value="' + division + '" />';
	htmltext += '</tbody>';
	return htmltext;
}

function getListDataForRequest(data) {
	let lstDto = [];
	$('[objectName]').each(function() {
		var objectName = $(this).attr('objectName');
		lstDto.push(objectName);
	});
	lstDto = $.unique(lstDto);
	$.each(lstDto, function(index, obj) {
		$('[objectName="' + obj + '"]').each(
				function(index) {
					data.append(obj + '[' + index + '].isDeleted', $(this)
							.hasClass("hidden"));
					$(this).find('[propertyName]').each(
							function() {
								data.append(obj + '[' + index + '].'
										+ $(this).attr('propertyName'), $(this)
										.val());
							});
				});
	});
}

function checkValidSuggest(){
	let result = true;
	let value = suggestCorporation.getValue();
	if (value.length <= 0) {
		$('#j-form-corporation')
				.after(
						'<label id="iCorporationId-error" class="error" for="corporation">法人を入力してください。</label>');
		result = false;
	}
	value = suggestiPrefecturesId.getValue();
	if (value.length <= 0) {
		$('#j-form-iPrefecturesId')
				.after(
						'<label id="iPrefecturesId-error" class="error" for="iPrefecturesId">都道府県を入力してください。</label>');
		result = false;
	}
	value = suggestSalesChannel.getValue();
	if (value.length <= 0) {
		$('#j-form-salesChannel')
				.after(
						'<label id="iSalesChannelId-error" class="error" for="salesChannel">販売チャンネルを入力してください。</label>');
		result = false;
	}
	value = suggestSalesChannel2.getValue();
	if (value.length <= 0) {
		$('#j-form-salesChannelCd2')
				.after(
						'<label id="salesChannelCd2-error" class="error" for="salesChannelCd2">SC分類を入力してください。</label>');
		result = false;
	}
	value = suggestiBlockId.getValue();
	if (value.length <= 0) {
		$('#j-form-iBlockId')
				.after(
						'<label id="iBlockId-error" class="error" for="iBlockId">ブロックを入力してください。</label>');
		result = false;
	}
	value = suggestiAreaId.getValue();
	if (value.length <= 0) {
		$('#j-form-iAreaId')
				.after(
						'<label id="iAreaId-error" class="error" for="iAreaId">エリアを入力してください。</label>');
		result = false;
	}
	return result;
}

function getDataSuggestCorporation(data) {
	let value = suggestCorporation.getValue();
	if (value.length > 0) {
		data.append("iCorporationId", value[0]);
	}
}

function getDataSuggestiPrefecturesId(data) {
	let value = suggestiPrefecturesId.getValue();
	if (value.length > 0) {
		data.append("iPrefecturesId", value[0]);
	}
}

function getDataSuggestSalesChannel(data) {
	let value = suggestSalesChannel.getValue();
	if (value.length > 0) {
		data.append("iSalesChannelId", value[0]);
	}
}

function getDataSuggestSalesChannel2(data) {
	let value = suggestSalesChannel2.getValue();
	if (value.length > 0) {
		data.append("salesChannelCd2", value[0]);
	}
}

function getDataSuggestiBlockId(data) {
	let value = suggestiBlockId.getValue();
	if (value.length > 0) {
		data.append("iBlockId", value[0]);
	}
}

function getDataSuggestiAreaId(data) {
	let value = suggestiAreaId.getValue();
	if (value.length > 0) {
		data.append("iAreaId", value[0]);
	}
}

function getDataSuggestBuildingGroupId(data) {
	let value = suggestBuildingGroupId.getValue();
	if (value.length > 0) {
		data.append("buildingGroupId", value[0]);
	} else {
		data.append("buildingGroupId", '');
	}
}

function getDataSuggestTennant(data) {
	// Building tenant
	$.each(suggestTenant.getSelection(), function(index, obj) {

		if (obj.idParent == undefined) {
			data.append('buildingTenantDto[' + index + '].tenant.id', 0);
		} else {
			data.append('buildingTenantDto[' + index + '].tenant.id', obj.id);
		}
		data.append('buildingTenantDto[' + index + '].tenant.name', obj.name);
	});
}

function getDataSuggestPersonalDevelop(data) {
	let indexBPD = 0;
	// Building Personal Develop StoreDevelopment
	$.each(suggestStoreDevelopment.getSelection(), function(index, obj) {
		data.append('buildingPersonalDevelopDto[' + indexBPD + '].accountId',
				obj.id);
		data.append('buildingPersonalDevelopDto[' + indexBPD + '].category',
				'storeDeveloper');
		indexBPD++;
	});
	// Building Personal Develop Branch Sales
	$.each(suggestBranchSales.getSelection(), function(index, obj) {
		data.append('buildingPersonalDevelopDto[' + indexBPD + '].accountId',
				obj.id);
		data.append('buildingPersonalDevelopDto[' + indexBPD + '].category',
				'branchsSales');
		indexBPD++;
	});
	// Building Personal Develop Hr Leader
	$.each(suggestHrLeader.getSelection(), function(index, obj) {
		data.append('buildingPersonalDevelopDto[' + indexBPD + '].accountId',
				obj.id);
		data.append('buildingPersonalDevelopDto[' + indexBPD + '].category',
				'humanResourceLeader');
		indexBPD++;
	});
}

function getDataSuggetPmCorporation(data) {
	// Pm Corporation
	$.each(suggestPmCorporation.getSelection(), function(index, obj) {
		data.append('pmCorporationDto[' + index + '].corporationId', obj.id);
		data
				.append('pmCorporationDto[' + index + '].corporationName',
						obj.name);
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
var dateFormat = "yy-mm-dd";
var dateTimeFormat = "yy-mm-dd 00:00";
var indexFile = 0;
var indexImage = 0;
var indexMeeting = 0;
var indexActivation = 0;
var indexBuildingSale = 0;
let suggestCorporationGrId, suggestCorporation, suggestiPrefecturesId, suggestSalesChannel, suggestSalesChannel2, suggestiBlockId, suggestiAreaId, suggestBuildingGroupId, suggestTenant, suggestStoreDevelopment, suggestBranchSales, suggestHrLeader, suggestKeyman, suggestPmCorporation;
var createDatePicker = function($target) {
    $target.datepicker({
	     dateFormat:'yy-mm-dd',
	     buttonImageOnly: true,
	     buttonImage: "",
	     buttonText: "",
	     showOn: "both",
	     showButtonPanel: true,
	     closeText: "閉じる",
	     onSelect: function() {
	         var value = $(this).datepicker({ dateFormat: 'yy-mm-dd' }).val();
	         $(this).val(value);
	     },
	     monthNames: [ "1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月" ],
	     monthNamesShort: [ "1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月" ],
	     dayNames: ["日曜日","月曜日","火曜日","水曜日","木曜日","金曜日","土曜日"],
	     dayNamesShort: ["日","月","火","水","木","金","土"],
	     dayNamesMin: ["日","月","火","水","木","金","土"],
	     showMonthAfterYear: true,
	     changeMonth: true,
	     changeYear: true,
	});
};
var createDateTimePicker = function($target) {
	$target.datepicker({
		dateFormat : dateTimeFormat
	});
}
$(function() {
	$('.media-list-item[bulding-sales-type]').hide();
	let buildingSaleType = $('input[type="radio"][name="sales-type"]').val();
	$('.media-list-item[bulding-sales-type="' + buildingSaleType + '"]').show();

	if ($('.j-implementation-date').length) {
		createDatePicker($('.j-implementation-date').find('input'));
	}
	if ($('.j-implementation-date-time').length) {
		createDateTimePicker($('.j-implementation-date-time').find('input'));
	}
	if ($("#building-form").length) {
		$('.salesChannel').hide();
		let salesChannelInit = $('option:selected', $("#salesChannel")).attr(
				'text');
		$('.' + salesChannelInit).show();
		if ($('.j-star-keyman-init').length) {
			$('.j-star-keyman-init').rating(function(vote, event) {
				let element = event.toElement;
				let parent = $(element).closest(".media-list-item");
				parent.find('input[propertyName="priority"]').val(vote);
			});
		}
		;
		$("#building-form").validate(validation);
		$('#add-building-sales-form').validate(validationAddBuildingSales);
		$.each($('[j-active="expectedDay"]'), function() {
			$(this).rules("add", {
				required : true,
				dateISO : true,
				messages : {
					required : "活性化予定日を入力してください。",
					dateISO : "日付の形式を正しく入力してください。",
				}
			});
		});
		$.each($('[j-active="floor"]'), function() {
			$(this).rules("add", {
				required : true,
				messages : {
					required : "フロアを入力してください。",
				}
			});
		});
		$.each($('[j-active="content"]'), function() {
			$(this).rules("add", {
				required : true,
				messages : {
					required : "コメントを入力してください。",
				}
			});
		});

		$.each($('[j-meeting="date"]'), function() {
			$(this).rules("add", {
				inspDateTime : true,
				messages : {
					inspDateTime : "日付の形式を正しく入力してください。",
				}
			});
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

		// Create magicSuggest
		suggestCorporationGrId = $('#j-form-corporationGrId')
				.magicSuggest(
						{
							maxSelection : 1,
							allowFreeEntries : false,
							maxSelectionRenderer: function () {
								return "";
							},
							data : (building.iCorporation != null && building.iCorporation.iCorporationGroup != null) ? [ {
								"id" : building.iCorporation.iCorporationGroup.id,
								"name" : building.iCorporation.iCorporationGroup.corporationGpName
							} ]
									: [],
							value : (building.iCorporation != null && building.iCorporation.iCorporationGroup != null) ? [ building.iCorporation.iCorporationGroup.id ]
									: []
						});
		suggestCorporation = $('#j-form-corporation')
				.magicSuggest(
						{
							maxSelection : 1,
							allowFreeEntries : false,
							maxSelectionRenderer: function () {
								return "";
							},
							data : (building.iCorporation != null) ? [ {
								"id" : building.iCorporation.id,
								"name" : building.iCorporation.corporationName
							} ] : [],
							value : (building.iCorporation != null) ? [ building.iCorporation.id ]
									: []
						});
		suggestiPrefecturesId = $('#j-form-iPrefecturesId')
				.magicSuggest(
						{
							maxSelection : 1,
							allowFreeEntries : false,
							maxSelectionRenderer: function () {
								return "";
							},
							data : (building.iPrefectures != null) ? [ {
								"id" : building.iPrefectures.id,
								"name" : building.iPrefectures.prefecturesName
							} ] : [],
							value : (building.iPrefectures != null) ? [ building.iPrefectures.id ]
									: []
						});
		suggestSalesChannel = $('#j-form-salesChannel')
				.magicSuggest(
						{
							maxSelection : 1,
							allowFreeEntries : false,
							maxSelectionRenderer: function () {
								return "";
							},
							data : (building.iSalesChannel != null) ? [ {
								"id" : building.iSalesChannel.id,
								"name" : building.iSalesChannel.salesChannelName
							} ]
									: [],
							value : (building.iSalesChannel != null) ? [ building.iSalesChannel.id ]
									: []
						});
		suggestSalesChannel2 = $('#j-form-salesChannelCd2')
				.magicSuggest(
						{
							maxSelection : 1,
							allowFreeEntries : false,
							maxSelectionRenderer: function () {
								return "";
							},
							data : (building.iSalesChannel2 != null) ? [ {
								"id" : building.iSalesChannel2.id,
								"name" : building.iSalesChannel2.salesChannelName
							} ]
									: [],
							value : (building.iSalesChannel2 != null) ? [ building.iSalesChannel2.id ]
									: []
						});
		suggestiBlockId = $('#j-form-iBlockId').magicSuggest({
			maxSelection : 1,
			allowFreeEntries : false,
			maxSelectionRenderer: function () {
				return "";
			},
			data : (building.iBlock != null) ? [ {
				"id" : building.iBlock.id,
				"name" : building.iBlock.blockName
			} ] : [],
			value : (building.iBlock != null) ? [ building.iBlock.id ] : []
		});
		suggestiAreaId = $('#j-form-iAreaId').magicSuggest({
			maxSelection : 1,
			allowFreeEntries : false,
			maxSelectionRenderer: function () {
				return "";
			},
			data : (building.iArea != null) ? [ {
				"id" : building.iArea.id,
				"name" : building.iArea.areaName
			} ] : [],
			value : (building.iArea != null) ? [ building.iArea.id ] : []
		});
		suggestBuildingGroupId = $('#j-form-buildingGroupId').magicSuggest({
			maxSelection : 1,
			maxSelectionRenderer: function () {
				return "";
			},
			allowFreeEntries : false
		});
		if (!building.isBuildingGroup && building.buildingParent != undefined) {
			suggestBuildingGroupId.setData([ {
				id : building.buildingParent.id,
				name : building.buildingParent.name
			} ]);
			suggestBuildingGroupId.setValue([ building.buildingParent.id ]);
		}

		suggestTenant = $('#j-form-tenant').magicSuggest(
				{
					data : (building.buildingTenants != null) ? Array
							.from(building.buildingTenants.map(function(obj) {
								return (obj.tenant != null) ? {
									id : obj.tenant.id,
									name : obj.tenant.name,
									idParent : 1
								} : undefined;
							})) : [],
					value : (building.buildingTenants != null) ? Array
							.from(building.buildingTenants.map(function(obj) {
								return (obj.tenant != null) ? obj.tenant.id
										: undefined;
							})) : []
				});
		suggestStoreDevelopment = $('#j-form-store-development')
				.magicSuggest(
						{
							allowFreeEntries : false,
							data : (building.buildingPersonalDevelops != null) ? Array
									.from(building.buildingPersonalDevelops
											.filter(
													function(obj) {
														return obj.category === "storeDeveloper";
													}).map(function(obj) {
												return {
													id : obj.accountId,
													name : obj.name
												}
											}))
									: [],
							value : (building.buildingPersonalDevelops != null) ? Array
									.from(building.buildingPersonalDevelops
											.filter(
													function(obj) {
														return obj.category === "storeDeveloper";
													}).map(function(obj) {
												return obj.accountId;
											}))
									: []
						});
		suggestBranchSales = $('#j-form-branch-sales').magicSuggest(
				{
					allowFreeEntries : false,
					data : (building.buildingPersonalDevelops != null) ? Array
							.from(building.buildingPersonalDevelops.filter(
									function(obj) {
										return obj.category === "branchsSales";
									}).map(function(obj) {
								return {
									id : obj.accountId,
									name : obj.name
								}
							})) : [],
					value : (building.buildingPersonalDevelops != null) ? Array
							.from(building.buildingPersonalDevelops.filter(
									function(obj) {
										return obj.category === "branchsSales";
									}).map(function(obj) {
								return obj.accountId;
							})) : []
				});
		suggestHrLeader = $('#j-form-hr-leader')
				.magicSuggest(
						{
							allowFreeEntries : false,
							data : (building.buildingPersonalDevelops != null) ? Array
									.from(building.buildingPersonalDevelops
											.filter(
													function(obj) {
														return obj.category === "humanResourceLeader";
													}).map(function(obj) {
												return {
													id : obj.accountId,
													name : obj.name
												}
											}))
									: [],
							value : (building.buildingPersonalDevelops != null) ? Array
									.from(building.buildingPersonalDevelops
											.filter(
													function(obj) {
														return obj.category === "humanResourceLeader";
													}).map(function(obj) {
												return obj.accountId;
											}))
									: []
						});
		suggestKeyman = $('#j-form-keyman').magicSuggest({
			allowFreeEntries : false
		});
		suggestPmCorporation = $('#j-form-pmCorporation')
				.magicSuggest(
						{
							allowFreeEntries : false,
							data : (building.pmCorporations != null) ? Array
									.from(building.pmCorporations
											.map(function(obj) {
												return (obj.corporationName.length > 0) ? {
													id : obj.corporationId,
													name : obj.corporationName
												}
														: undefined;
											}))
									: [],
							value : (building.pmCorporations != null) ? Array
									.from(building.pmCorporations
											.map(function(obj) {
												return (obj.corporationName.length > 0) ? obj.corporationId
														: undefined;
											}))
									: []
						});
		$
				.each(
						building.buildingKeymans,
						function(index, obj) {
							let suggestCorporationKeyman = $(
									'#j-form-keyman-corporation-exits' + obj.id)
									.magicSuggest(
											{
												maxSelection : 1,
												maxSelectionRenderer: function () {
													return "";
												},
												allowFreeEntries : false,
												data : (obj.keyman != null && obj.keyman.iCorporation != null) ? [ {
													id : obj.keyman.corporationId,
													name : obj.keyman.iCorporation.corporationName
												} ]
														: [],
												value : (obj.keyman != null && obj.keyman.iCorporation != null) ? [ obj.keyman.corporationId ]
														: []
											});
							createSuggest(suggestCorporationKeyman, "/sp/building/find/corporation")
							$(suggestCorporationKeyman).on(
									'selectionchange',
									function() {
										let value = this.getSelection();
										if (value.length > 0) {
											$(
													'#id-form-keyman-corporation-exits'
															+ obj.id).val(
													value[0].id);
										} else {
											$(
													'#id-form-keyman-corporation-exits'
															+ obj.id).val('');
										}
									});
						})
	}
	createSuggest(suggestCorporationGrId, "/sp/building/find/i_corporation_group");
	
	
	$(suggestCorporation).on({
		'keydown': function (keydownEvent, ms, event) {
			if (event.keyCode === 229) {
				isImeMode = true
			} else {
				isImeMode = false
			}
		},
		'keyup': function (keyupEvent, ms, event) {
			if (event.keyCode == 38 || event.keyCode == 40) { return; }

			let corporationGrId = suggestCorporationGrId.getSelection();
			let value = this.getRawValue();

			if (!isImeMode || (isImeMode && event.keyCode === 13)) {
				if (value && corporationGrId.length > 0) {
					let suggest = this;
					$.ajax({
						url: createUrl("/sp/building/find/corporation"),
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

	createSuggest(suggestPmCorporation, "/sp/building/find/corporation");
	createSuggest(suggestiPrefecturesId, "/sp/building/find/i_prefectures");
	createSuggest(suggestSalesChannel, "/sp/building/find/i_sales_channel");
	createSuggest(suggestSalesChannel2, "/sp/building/find/i_sales_channel");
	createSuggest(suggestiBlockId, "/sp/building/find/i_block");
	createSuggest(suggestiAreaId, "/sp/building/find/i_area");
	createSuggest(suggestBuildingGroupId, "/sp/building/find/building_parent");
	createSuggest(suggestTenant, "/sp/building/find/tenant");
	createSuggest(suggestStoreDevelopment, "/sp/building/find/account");
	createSuggest(suggestBranchSales, "/sp/building/find/account");
	createSuggest(suggestHrLeader, "/sp/building/find/account");
	createSuggest(suggestKeyman, "/sp/building/find/i_business_card");
	
	$(suggestCorporationGrId).on('selectionchange', function(e, m, v) {
		suggestCorporation.clear();
	});
	$(document).on(
			'click',
			'#j-update',
			function() {
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
					url: createUrl("/pc/building/update"),
					data : data,
					cache : false,
					contentType : false,
					processData : false,
					method : 'POST',
					type : 'POST', // For jQuery < 1.9
					success : function(response) {
						window.location.replace(createUrl("/pc/building/detail/"
								+ response));
					},
					error : function() {
						alert("更新に失敗しました。");
						$($this).removeClass("disabled");
					}
				});
			});
	// Add keyman
	$("#addKeyman")
			.click(
					function() {
						var selectionKeyman = suggestKeyman.getSelection();
						if (selectionKeyman.length === 0) {
							return;
						}
						$
								.each(
										selectionKeyman,
										function(index, keyman) {
											let selector = $('[businessCardId="'
													+ keyman.id + '"]');
											if (selector.length === 0) {
												let htmltext = '<li class="media-list-item has-trash new-item" objectName="buildingKeymanDto">';
												htmltext += '<div class="keyman-info">';
												htmltext += '<header class="keyman-info-header">';
												htmltext += '<div class="form-group">';
												htmltext += '<div class="row">';
												htmltext += '<div class="col-md-9">';
												htmltext += '<div class="inline-list">';
												htmltext += '<div class="stars">';
												htmltext += '<div id="j-star-keyman-container-new'
														+ keyman.id + '">';
												htmltext += '<input class="rating" type="radio" value="1" style="display: none;">';
												htmltext += '<input class="rating" type="radio" value="2" style="display: none;"> ';
												htmltext += '<input class="rating" type="radio" value="3" style="display: none;">';
												htmltext += '</div>';
												htmltext += '<input propertyName="priority" type="hidden" value="0">';
												htmltext += '</div>';
												htmltext += '</div>';
												htmltext += '</div>';
												htmltext += '<div class="col-md-2">';
												htmltext += '<select class="form-control building-keyman-role" propertyName="role">';
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
												htmltext += '<label>名前</label>';
												htmltext += '</div>';
												htmltext += '<div class="col-md-9">';
												htmltext += '<label class="form-control">'
														+ keyman.name
														+ '</label>';
												htmltext += '<input type="hidden" propertyName="keyman.businessCardId" businessCardId="'
														+ keyman.id
														+ '" value="'
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
												htmltext += '<input class="form-control building-keyman-note" propertyName="note" type="text" value="" placeholder="メモ">';
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
												if ($('#j-star-keyman-container-new'
														+ keyman.id).length) {
													$(
															'#j-star-keyman-container-new'
																	+ keyman.id)
															.rating(
																	function(
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
												let corporationInit = $(
														"#corporationTemp")
														.find("option").clone();
												$(
														'#keyman-corporation'
																+ keyman.id)
														.append(corporationInit);
												let suggestCorporationKeyman = $(
														'#j-form-keyman-corporation'
																+ keyman.id)
														.magicSuggest(
																{
																	maxSelection : 1,
																	allowFreeEntries : false,
																	maxSelectionRenderer: function () {
																		return "";
																	},
																	data : (keyman.corporationId != null) ? [ {
																		id : keyman.corporationId,
																		name : keyman.corporationName
																	} ]
																			: [],
																	value : (keyman.corporationId != null) ? [ keyman.corporationId ]
																			: []
																});
												createSuggest(suggestCorporationKeyman, "/sp/building/find/corporation");
												$(suggestCorporationKeyman)
														.on(
																'selectionchange',
																function() {
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
											} else {
												selector.closest(
														".media-list-item")
														.removeClass("hidden");
											}
										})
						suggestKeyman.clear();
					});
	// Add activation

	$('#addActivation')
			.click(
					function() {
						indexActivation++;
						let htmltext = '<div class="media-list-item has-trash new-item" objectName="activationDto">';
						htmltext += '<div class="row">';
						htmltext += '<div class="col-md-12">';
						htmltext += '<div class="form-group">';
						htmltext += '<div class="row">';
						htmltext += '<div class="col-md-2">';
						htmltext += '<label>フロア</label>';
						htmltext += '</div>';
						htmltext += '<div class="col-md-9">';
						htmltext += '<input class="form-control" type="text" name="j-active-floor'
								+ indexActivation
								+ '" id="j-active-floor'
								+ indexActivation
								+ '" propertyName="floor" placeholder="フロア">';
						htmltext += '</div>';
						htmltext += '</div>';
						htmltext += '</div>';
						htmltext += '<div class="form-group">';
						htmltext += '<div class="row">';
						htmltext += '<input class="object-property" type="hidden" propertyName="id" value="0">';
						htmltext += '<div class="col-md-2">';
						htmltext += '<label>活性化予定日</label>';
						htmltext += '</div>';
						htmltext += '<div class="col-md-9 j-implementation-date">';
						htmltext += '<input class="form-control" type="text" propertyName="expectedDay" name="j-active-expectedDay'
								+ indexActivation
								+ '" id="j-active-expectedDay'
								+ indexActivation + '" placeholder="活性化予定日">';
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
						$('#activation > .tab-content-body > .media-list')
								.append(htmltext);
						if ($('.j-implementation-date').length) {
							createDatePicker($('.j-implementation-date').find(
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
						let htmltext = '<div class="media-list-item has-trash new-item" objectName="buildingFileDto">';
						htmltext += '<div class="row">';
						htmltext += '<div class="col-md-12">';
						htmltext += '<div class="form-group">';
						htmltext += '<div class="row">';
						htmltext += '<div class="col-md-2">';
						htmltext += '<label>ファイル</label>';
						htmltext += '</div>';
						htmltext += '<div class="col-md-9">';
						htmltext += '<div class="input-group">';
						htmltext += '<input type="text" class="form-control" propertyName="displayName" input-file="openRelatedDoc'
								+ indexFile + '" placeholder="">';
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
						htmltext += '<input class="form-control" propertyName="comment">';
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
	// Add Image
	$('#addImage')
			.click(
					function() {
						indexImage++;
						let htmltext = '<div class="media-list-item has-trash new-item" objectName="buildingImageDto">';
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
						htmltext += '<input type="text" class="form-control" input-file="openImageFile'
								+ indexImage + '" placeholder="">';
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
						$('#image > .tab-content-body > .media-list').prepend(
								htmltext);
						resetDefaultImage();
						$('#openImageFile' + indexImage).rules("add", {
							hasfile : true,
						});
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
						let htmltext = '<div class="media-list-item has-trash new-item" objectName="buildingMeetingDto">';
						htmltext += '<div class="row">';
						htmltext += '<div class="col-md-12">';
						htmltext += '<div class="form-group">';
						htmltext += '<div class="row">';
						htmltext += '<input class="object-property" type="hidden" propertyName="id" value="0">';
						htmltext += '<label class="col-sm-2 control-label">総会名</label>';
						htmltext += '<div class="col-sm-9">';
						htmltext += '<input class="form-control" propertyName="name" type="text" placeholder="総会名">';
						htmltext += '</div>';
						htmltext += '</div>';
						htmltext += '</div>';
						htmltext += '<div class="form-group">';
						htmltext += '<div class="row">';
						htmltext += '<label class="col-sm-2 control-label">総会実施日</label>';
						htmltext += '<div class="col-sm-9 j-implementation-date-time">';
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
						htmltext += '<input class="form-control" propertyName="attendee" type="text" placeholder="出席者">';
						htmltext += '</div>';
						htmltext += '</div>';
						htmltext += '</div>';
						htmltext += '</div>';
						htmltext += '</div>';
						htmltext += '</div>';
						$('#general-meeting > .tab-content-body > .media-list')
								.append(htmltext);
						if ($('.j-implementation-date-time').length) {
							createDateTimePicker($(
									'.j-implementation-date-time')
									.find('input'));
						}
						$('#j-meeting-date' + indexMeeting).rules("add", {
							inspDateTime : true,
							messages : {
								inspDateTime : "日付の形式を正しく入力してください。",
							}
						});
					});
	$(document).on('click', '.j-delete-item', function() {
		let parent = $(this).closest(".media-list-item");
		if (parent.hasClass("present-item")) {
			$(parent).addClass("hidden");
			$.each($(parent).find("input"), function(index, obj) {
				$(obj).rules('remove');
			});
		} else if (parent.hasClass("new-item")) {
			$(parent).remove();
		}
	});
	$(document).on(
			'click',
			'.j-delete-item-building-sales',
			function() {
				let parent = $(this).closest("tbody");
				let liparent = $(this).closest(".media-list-item");
				if (parent.hasClass("present-item")) {
					$(parent).addClass("hidden");
				} else if (parent.hasClass("new-item")) {
					$(parent).remove();
				}
				parent = $(this).closest(".media-list-item").find("tbody").not(
						".hidden");
				if (parent.length === 0) {
					$(liparent).addClass("hidden");
				}
			});
	$(document).on(
			'change',
			'input[type=radio][name-control=division]',
			function() {
				let parent = $(this).closest(".media-list-item");
				parent.find('input[type=hidden][propertyName="division"]').val(
						this.value);
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
						let valid = $('#buildingSales').find('li.media-list-item[bulding-sales-year="'
								+ fmonth
								+ '"][bulding-sales-type="' + type + '"]').find('input[type="hidden"][propertyname="division"][value="' + division +'"]');

						if(valid.length > 0 && !valid.closest('tbody').hasClass("hidden")) {
							$('#j-form-building-sales-division').after('<label id="j-form-building-sales-division-error" class="error" for="j-form-building-sales-division">館売上が既に存在します。</label>');
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
										$('#buildingSales > li.media-list-item.hide')
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
								'li.media-list-item[bulding-sales-type="' + $('input[type=radio][name=sales-type]:checked').val()
										+ '"]').show();

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
				$(this).val('true');
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
					if (!$(this).hasClass("hidden")) {
						$(this).find('[propertyName]').each(
								function() {
									data.append(obj + '[' + index + '].'
											+ $(this).attr('propertyName'), $(
											this).val());
								});
					} else {
						$(this).find('[propertyName=id]').each(
								function() {
									data.append(obj + '[' + index + '].'
											+ $(this).attr('propertyName'), $(
											this).val());
								});
					}
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
	if ($(suggestBuildingGroupId).length === 0) {
		return;
	}
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

		if (obj.createdDatetime == undefined) {
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
function buildingSales(buildingSaless) {
	let fMonths = Array.from(Array.from(buildingSaless.map(function(s) {
		let date = new Date(s.financialMonth);
		return date.getFullYear().toString();
	})).filter(function(value, index, self) {
		return self.indexOf(value) === index;
	})).sort().reverse();
	let textBuildingSale = '';
	$
			.each(
					fMonths,
					function(index, obj) {
						let data = buildingSaless.filter(function(bds) {
							let date = new Date(bds.financialMonth);
							return date.getFullYear().toString() === obj;
						});
						var type = Array.from(Array.from(data.map(function(f) {
							return f.type;
						})).filter(function(value, index, self) {
							return self.indexOf(value) === index;
						})).sort();
						$
								.each(
										type,
										function(index, t) {
											textBuildingSale += '<li class="media-list-item has-trash present-item" bulding-sales-year="'
													+ obj
													+ '" bulding-sales-type="'
													+ t + '" >';
											textBuildingSale += '<div class="building-basic-info j-dropdown">';
											textBuildingSale += '<header class="building-basic-info-header j-dropdown-header isOpen">';
											textBuildingSale += '<h3 class="title">'
													+ obj + '年度</h3>';
											textBuildingSale += '</header>';
											textBuildingSale += '<div class="building-basic-info-body j-dropdown-body" style="display: block;">';
											textBuildingSale += '<div class="j-dropdown-body-inner">';
											textBuildingSale += '<table class="table">';

											var buildsales = data
													.filter(function(dt) {
														return dt.type === t;
													});
											$
													.each(
															buildsales,
															function(index,
																	buildsale) {
																textBuildingSale += '<tbody class="present-item" objectName="buildingSalesDto">';
																textBuildingSale += '<tr>';
																textBuildingSale += '<td style="width:300px;min-width:300px;max-width:300px;"><label>'
																		+ buildsale.mBuildingSalesTypes.value
																		+ '</label></td>';
																textBuildingSale += '<td>売上</td>';
																textBuildingSale += '<td class="ta-r"><input propertyName="sales" name="j-bsales-sales"'
																		+ buildsale.id
																		+ ' type="number" value="'
																		+ buildsale.sales
																		+ '" /></td>';
																textBuildingSale += '</tr>';
																textBuildingSale += '<tr>';
																textBuildingSale += '<td style="width:300px;min-width:300px;max-width:300px;"><label>'
																		+ buildsale.mBuildingSalesClassifications.value
																		+ '</label></td>';
																textBuildingSale += '<td>面積</td>';
																textBuildingSale += '<td class="ta-r"><input propertyName="area" name="j-bsales-area"'
																		+ buildsale.id
																		+ ' type="number" value="'
																		+ buildsale.area
																		+ '" /></td>';
																textBuildingSale += '</tr>';
																textBuildingSale += '<tr>';
																textBuildingSale += '<td style="width:300px;min-width:300px;max-width:300px;"><label><div class="trash j-delete-item-building-sales" style="position: relative;margin-top: 0px;"><i class="fa fa-trash"></div></label></td>';
																textBuildingSale += '<td>月坪</td>';
																textBuildingSale += '<td class="ta-r"><input propertyName="monthBasis" name="j-bsales-monthBasis"'
																		+ buildsale.id
																		+ ' type="number" value="'
																		+ buildsale.monthBasis
																		+ '" /></td>';
																textBuildingSale += '</tr>';
																textBuildingSale += '<input type="hidden" propertyName="id" value="'
																		+ buildsale.id
																		+ '">';
																textBuildingSale += '<input type="hidden" propertyName="financialMonth" value="'
																		+ buildsale.financialMonth
																		+ '">';
																textBuildingSale += '<input type="hidden" propertyName="type" value="'
																		+ buildsale.type
																		+ '">';
																textBuildingSale += '<input type="hidden" propertyName="division" name="j-bsales-division'
																		+ buildsale.id
																		+ '" value="'
																		+ buildsale.division
																		+ '" />';
																textBuildingSale += '</tbody>';
															});
											textBuildingSale += '</table>';
											textBuildingSale += '</div>';
											textBuildingSale += '</div>';
											textBuildingSale += '</div>';
											textBuildingSale += '</li>';
										});
					});
	return textBuildingSale;
};

function createValidMessage(){
	var message = [];
	$(".tab-pane").each(function() {
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
var dateFormat = "yy-mm-dd";
var dateTimeFormat = "yy-mm-dd 00:00";
var indexFile = 0;
var indexOpinionFile = 0;
var indexTask = 0;
var refBuilding = false;
var refShop = false;
var refBuildingData = {};
var refShopData = {};
var projectData = {};
var listBrandIncome = [];
var requiredShop = false;
var permitType = [{type:"image/jpeg",division:"image",extension:"jpg"}, {type:"image/png",division:"image",extension:"png"}, {type:"image/gif",division:"image",extension:"gif"},
    {type:"application/pdf",division:"/wsd/img/pdf.jpg",extension:"pdf"}, {type:"application/vnd.openxmlformats-officedocument.wordprocessingml.document",division:"/wsd/img/docx.jpg",extension:"docx"},
    {type:"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",division:"/wsd/img/xlsx.jpg",extension:"xlsx"},
    {type:"application/vnd.openxmlformats-officedocument.presentationml.presentation",division:"/wsd/img/pptx.jpg",extension:"pptx"},
    {type:"video/mp4",division:"video",extension:"mp4"}, {type:"video/quicktime",division:"video",extension:"mov"}];

let suggestBuilding, suggestBlock, suggestArea, suggestShopName, suggestBrand, suggestCorporationGroup,
	suggestCorporation, suggestPrefectures, suggestAccountStore, suggestAccountSales, suggestAccountSection,
	suggestAccountFc, suggestAccountOther, suggestAccountLeader, projectSaleAgencyTargetProgressSuggest,
	projectParticipatingStoreCorporationProgressSuggest, suggestOtherProjectTeam, suggestProgressContractTargetName;
var projectPlanCurrent = {};
var projectPlanProgress = {};
var projectPlanDto = [];
var participatingStoreCorporation = {};
var projectSectionProgressNegotiation = {};
var salesAgencyContract = {};
var salesAgencyTarget = {};
let currentContract = {};
var createProjectTitle = {"building":"", "brand":"", "landing":""};

var createDateTimePicker = function($target) {
	$target.datepicker({
		dateFormat : dateTimeFormat,
		buttonImageOnly: true,
	     buttonImage: "",
	     buttonText: "",
	     showOn: "both",
	     showButtonPanel: true,
	     closeText: "閉じる",
	     onSelect: function() {
	         var value = $(this).datepicker({ dateFormat: 'yy-mm-dd 00:00' }).val();
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
	});
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
					type : "POST",
					data : JSON.stringify({
						"text" : value
					}),
					contentType : "application/json",
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
}
var createDatePicker = function ($target, defaultValue, selectEvent) {
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
	var datepicker = $target.datepicker(option);
	if(defaultValue != undefined){
		datepicker.datepicker("setDate", defaultValue);
	}
}
var makePostRequest = function (url, data) {
	return $.ajax({
        url: createUrl(url),
        type: 'POST',
        contentType : "application/json",
        data: data,
        timeout: 30000
    });
}
var showFormBusinessCard = function (id) {
	makePostRequest("/sp/project/refBrandData/" + id, {}).then(function (data) {
        if (data !== "") {
        	$("#j-form-business-card").show();
            $("#j-form-business-card").html(data.listIBusiness.name);
        }
	});
}
var processSuggestShopNameData = function (id) {

	makePostRequest("/sp/project/refShopData/" + id, {}).then(function (data) {
        if (data !== "") {
        	refShopData = data;
    		setDataFromShop(true);
    		
    		if(currentContract.contractTargetName !== undefined && currentContract.contractTargetName !== null){
    			suggestProgressContractTargetName.clear();
    			suggestProgressContractTargetName.setValue([currentContract.contractTargetName]);
    		}
    		
            if(data.listBrandIncome.length > 0){
            	listBrandIncome = data.listBrandIncome;
                if(data.listBrandIncome.length == 1) {
                	$("#j-form-brand-label-span").html(data.listBrandIncome[0].name);
                	$("#j-form-brand-label-span").show();
                	$("#j-form-brand-label-option").hide();
                	showFormBusinessCard(data.listBrandIncome[0].id);
                	project.brandId = data.listBrandIncome[0].id;
                	createProjectTitle.brand = data.listBrandIncome[0].name;
                } else if(data.listBrandIncome.length > 1){
                	$("#j-form-brand-label-option").empty();
                	data.listBrandIncome.forEach(function (element) {
                		$("#j-form-brand-label-option").append('<option value="'+ element.id +'"> '+element.name +'</option>');
					});
                	$("#j-form-brand-label-option").show();
                    $("#j-form-brand-label-span").hide();

                    showFormBusinessCard(data.listBrandIncome[0].id);
                    $("#j-form-brand-label-option").on("change", function () {
                    	let idBrand = $("#j-form-brand-label-option").val();
                    	showFormBusinessCard(idBrand);
                    	createProjectTitle.brand = $("#j-form-brand-label-option option:selected").val();
                    	makeProjectTitle();
					})
                }
                $("#project_edit_brand").hide();
            } else {
            	listBrandIncome = [];
                $("#j-form-brand-label-option").hide();
                $("#j-form-brand-label-span").hide();
                $("#j-form-business-card").hide();

                if(suggestBrand.getValue().length > 0){
                    createProjectTitle.brand = $("#project_edit_brand .ms-sel-item").text();
                } else {
                	createProjectTitle.brand = "";
                }
            }
            if(data.listSalesAgency !== null){
            	$("#j-form-management-form").empty();
            	$("#j-form-management-form").text(data.listSalesAgency.name);
            	$("#j-form-management-form").show();
            } else {
            	$("#j-form-management-form").empty();
            	$("#j-form-management-form").hide();
            }
			if(data.salesAgencyTarget != undefined && data.salesAgencyTarget != null){
                salesAgencyTarget = data.salesAgencyTarget;
                $("#project-edit-i-sales-agency-target-curent-label").html(salesAgencyTarget.name);
            } else {
            	salesAgencyTarget = {};
            }
            if(data.salesAgencyContract != undefined && data.salesAgencyContract != null){
                salesAgencyContract.startDate = data.salesAgencyContract.startDateString;
                salesAgencyContract.endDate = data.salesAgencyContract.endDateString;
                salesAgencyContract.salesAgencyTargetId = data.salesAgencyContract.salesAgencyTargetId;
//                $("#project-edit-i-sales-agency-contract-startdate-curent-label").val(salesAgencyContract.startDate);
//                $("#project-edit-i-sales-agency-contract-enddate-curent-label").val(salesAgencyContract.endDate);
                
                let numberOfYear = calcYear(salesAgencyContract.startDate, salesAgencyContract.endDate);
                
                salesAgencyContract.numberOfYear = "";
                if(numberOfYear !== ""){
                	salesAgencyContract.numberOfYear = roundNumber(numberOfYear, 2);
                }
                
                let dateNumOfYear = "";
                if(salesAgencyContract.startDate != '' || salesAgencyContract.endDate != ''){
                	dateNumOfYear += salesAgencyContract.startDate + "～";
                	
                	if(salesAgencyContract.endDate !== '9999-12-31'){
                		dateNumOfYear += salesAgencyContract.endDate;
                	}
                	
                	if(salesAgencyContract.numberOfYear !== ""){
                		dateNumOfYear += "（" + salesAgencyContract.numberOfYear + "年）";
                	}
    			}
                $("#project-edit-i-sales-agency-contract-curent-date-number-of-year-label").html(dateNumOfYear);
            } else {
            	salesAgencyContract = {};
            }

//            if(data.salesAgencyCondition != null && data.salesAgencyCondition.startDate != null && data.salesAgencyCondition.endDate != null){
//                let endDate = data.salesAgencyCondition.endDateString;
//                let startDate = data.salesAgencyCondition.startDateString;
//                var result = 0;
//                var diff = new Date(endDate) -new Date(startDate);
//                if (isNaN(diff)) {
//                    result = "";
//                } else if (diff > 0) {
//                    var diffDate = new Date(diff);
//                    result = Math.abs(diffDate.getUTCFullYear() - 1970);
//                }
//                $("#number-of-year-agency-contract-current").val(result);
//                if(salesAgencyContract.startDate != '' && salesAgencyContract.endDate != undefined && 
//                		salesAgencyContract.endDate != '' && salesAgencyContract.endDate != undefined ){
//                	$("#project-edit-i-sales-agency-contract-curent-date-number-of-year-label").html(salesAgencyContract.startDate + "～" + salesAgencyContract.endDate + "（" + result + "年）");
//                }
//            }
            if(data.participatingStoreCorporation != undefined && data.participatingStoreCorporation != null){
                participatingStoreCorporation = data.participatingStoreCorporation;
                $("#project-edit-participating-store-corporation-curent-label").html(participatingStoreCorporation.name);
            } else {
            	participatingStoreCorporation = {};
            }
            projectData.brandId = undefined;
        } else {
			suggestBrand.clear();
        	project.brandId = null;
        	listBrandIncome = [];
        	createProjectTitle.brand = "";
        }
        makeProjectTitle();
    });
}
var setDataFromBuilding = function () {
	if(refBuilding){
		$("#project_edit_corporation_group").hide();
		$("#project_edit_corporation").hide();
		$("#project_edit_area").hide();
		$("#project_edit_block").hide();
		$("#project_edit_prefectures").hide();
		$("#project_edit_sales_channel").hide();

		$("#project_edit_corporation_group_label").html(refBuildingData.corporationGroupName);
		$("#project_edit_corporation_group_label").show();
		$("#project_edit_corporation_label").html(refBuildingData.corporationName);
		$("#project_edit_corporation_label").show();
		$("#project_edit_area_label").html(refBuildingData.areaName);
		$("#project_edit_area_label").show();
		$("#project_edit_block_label").html(refBuildingData.blockName);
		$("#project_edit_block_label").show();
		$("#project_edit_prefectures_label").html(refBuildingData.prefecturesName);
		$("#project_edit_prefectures_label").show();
		$("#project_edit_sales_channel_label").html(refBuildingData.salesChannelName);
		$("#project_edit_sales_channel_label").show();
		$("#project-edit-building-adopt-difficulty").html(refBuildingData.building.adoptDifficulty);
		$("#project-edit-building-adopt-difficulty").show();
		
		if(refBuildingData.storeDeveloperList !== undefined && refBuildingData.storeDeveloperList !== null) {
			suggestAccountStore.addToSelection(Array
					.from(refBuildingData.storeDeveloperList
							.map(function(obj) {
								return {
									id : obj.id,
									name : obj.name
								}
							})));
		}

		if(refBuildingData.branchsSalesList !== undefined && refBuildingData.branchsSalesList !== null) {
			suggestAccountSales.addToSelection(Array
					.from(refBuildingData.branchsSalesList
							.map(function(obj) {
								return {
									id : obj.id,
									name : obj.name
								}
							})));
		}

		if(refBuildingData.humanResourceLeaderList !== undefined && refBuildingData.humanResourceLeaderList !== null) {
			suggestAccountLeader.addToSelection(Array
					.from(refBuildingData.humanResourceLeaderList
							.map(function(obj) {
								return {
									id : obj.id,
									name : obj.name
								}
							})));
		}
		createProjectTitle.building = refBuildingData.buildingName;
	} else {
		$("#project_edit_corporation_group_label").hide();
		$("#project_edit_corporation_label").hide();
		$("#project_edit_area_label").hide();
		$("#project_edit_block_label").hide();
		$("#project_edit_prefectures_label").hide();
		$("#project_edit_sales_channel_label").hide();
		$("#project-edit-building-adopt-difficulty").hide();

		$("#project_edit_corporation_group").show();
		$("#project_edit_corporation_group").blur();
		$("#project_edit_corporation").show();
		$("#project_edit_corporation").blur();
		$("#project_edit_area").show();
		$("#project_edit_area").blur();
		$("#project_edit_block").show();
		$("#project_edit_block").blur();
		$("#project_edit_prefectures").show();
		$("#project_edit_prefectures").blur();
		$("#project_edit_sales_channel").show();
		$("#project_edit_sales_channel").blur();
		createProjectTitle.building = "";
	}
	makeProjectTitle();
}

var setDataFromShop = function(autoRefBuildingData) {
	if(refShop){
		if(refShopData.buildingId !== null){
			$("#project_edit_building").hide();

			$("#project_edit_building_label").html(refShopData.buildingName);
			$("#project_edit_building_label").show();

			if(autoRefBuildingData){
				refBuilding = true;
				makePostRequest("/sp/project/refBuildingData/" + refShopData.buildingId, {}).then(function (data) {
		    		refBuildingData = data;
		    		setDataFromBuilding();
		        });
			}
		}
		if(refShopData.rentContract !== undefined && refShopData.rentContract !== null) {
            if(refShopData.rentContract.iContractType !== undefined && refShopData.rentContract.iContractType !== null){
    			currentContract.form = refShopData.rentContract.iContractType.contractTypeName;
            }
            
            currentContract.contractTargetName = refShopData.rentContract.contractTargetName;
            
            let startDateString = "";
            if(refShopData.rentContract.startDateString !== undefined && refShopData.rentContract.startDateString !== null){
            	startDateString = refShopData.rentContract.startDateString.replace(/\//g,"-");
            	currentContract.contractStartDate = startDateString;
            }
            
            let endDateString = "";
            if(refShopData.rentContract.endDateString !== undefined && refShopData.rentContract.endDateString !== null){
            	endDateString = refShopData.rentContract.endDateString.replace(/\//g,"-");
            	currentContract.contractEndDate = endDateString;
            }
            
            let numberOfYear = calcYear(startDateString, endDateString);
            
            currentContract.contractNumberOfYear = "";
            if(numberOfYear !== ""){
            	currentContract.contractNumberOfYear = roundNumber(numberOfYear, 2);
            }
            
            let dateNumOfYear = "";
            if(startDateString !== "" || endDateString !== ""){
            	dateNumOfYear += startDateString + "～";
            	
            	if(endDateString !== "9999-12-31"){
            		dateNumOfYear += endDateString;
            	}
            	
            	if(currentContract.contractNumberOfYear !== ""){
            		dateNumOfYear += "（" + currentContract.contractNumberOfYear + "年）";
            	}
			}
            
            currentContract.autoUpdate = refShopData.rentContract.autoUpdate;
            
            $("#project_edit_current_contract_date_number_of_year").text(dateNumOfYear);
            $("#project_edit_current_contract_date_number_of_year_area").show();
            
			if(refShopData.isDisplayEconomy){
				if(suggestShopName != undefined){
					$("#project_edit_current_contract_display_economy_label").html('<a href="/wsd/pc/store/detail/' + suggestShopName.getValue()[0] + '">経済条件</a>');
					$("#project_edit_current_contract_display_economy_area").show();
				}
			}
        }
		
		currentContract.rentStartDate = refShopData.rentStartDate;
		currentContract.rentEndDate = refShopData.rentEndDate;
		currentContract.rentYear = refShopData.rentYear;
		currentContract.operationForm = refShopData.operationForm;

		$("#project_edit_current_section_progress_section_label").html(refShopData.section);
		$("#project_edit_current_section_progress_section_area").show();
		$("#project_edit_current_section_progress_frontage_label").html(refShopData.frontage);
		$("#project_edit_current_section_progress_frontage_area").show();
		$("#project_edit_current_section_progress_floor_label").html(refShopData.floorNum);
		$("#project_edit_current_section_progress_floor_area").show();
		$("#project_edit_current_section_progress_contract_tsubo_label").html(refShopData.contractTsubo);
		$("#project_edit_current_section_progress_contract_tsubo_area").show();
		$("#project_edit_current_section_progress_business_hours_label").html(refShopData.businessHours);
		$("#project_edit_current_section_progress_business_hours_area").show();

		$("#project-edit-i-sales-agency-target-curent-area").show();
    	$("#project-edit-participating-store-corporation-curent-area").show();
    	$("#project-edit-current-plan-group-area").show();
    	$("#project_edit_shop_name_temporary").hide();
    	
    	$("#j-form-operation-form").hide();
        $("#j-form-operation-form-label").text(refShopData.operationForm);
        $("#j-form-operation-form-label").show();
		displayIDContractTsubo();
		operationFormChange();
	} else {
		$("#project_edit_shop_name_temporary").show();
		$("#project-edit-i-sales-agency-target-curent-area").hide();
    	$("#project-edit-participating-store-corporation-curent-area").hide();
    	$("#project-edit-current-plan-group-area").hide();

		$("#project_edit_current_section_progress_section_area").hide();
		$("#project_edit_current_section_progress_frontage_area").hide();
		$("#project_edit_current_section_progress_floor_area").hide();
		$("#project_edit_current_section_progress_contract_tsubo_area").hide();
		$("#project_edit_current_section_progress_business_hours_area").hide();
		
		$("#project_edit_current_contract_date_number_of_year_area").hide();

		$("#project_edit_building").show();
		$("#project_edit_building").blur();

		$("#project_edit_building_label").hide();
		$("#project_edit_current_section_progress_area").hide();
		$("#j-form-operation-form").show();
        $("#j-form-operation-form-label").hide();
		displayIDContractTsubo();
		operationFormChange();
	}
}
$(function() {
	if(project.implementationScheduleDatetime != null && projectData.implementationScheduleDatetime != ''){
        $("#project_edit_implementation_schedule_datetime_year").val(project.implementationScheduleDatetime.split("-")[0]);
        $("#project_edit_implementation_schedule_datetime_month").val(project.implementationScheduleDatetime.split("-")[1]);
        $("#project_edit_implementation_schedule_datetime_day").val(project.implementationScheduleDatetime.split("-")[2]);
    }
	makePostRequest("/sp/project/store/require/" + project.projectCategoryId, {}).then(function (data) {
		requiredShop = data;
    });
	project.listProjectPlan.forEach(function (element){
        if ( element.category == "CURRENT"){
            projectPlanCurrent = element;
        } else if ( element.category == "PROGRESS"){
            projectPlanProgress = element;
        }
    });
	project.files.forEach(function (element){
		$('#project-file-exist-output-flag-' + element.id).on("change", function(){
    		let outputNumberTargetId = $(this).attr("id").replace("output-flag", "output-number");
    		if($(this).val() === 'T'){
    			$("#" + outputNumberTargetId).show();
    		} else{
    			$("#" + outputNumberTargetId).hide();
    		}
    	});
	});
	if ($('.j-implementation-date').length) {
		datepickerUtils.createDatePicker($('.j-implementation-date').find('input'));
	}
	if ($('.j-implementation-time').length) {
		datepickerUtils.createTimePicker($('.j-implementation-time').find('input'), "");
	}
	if ($('.j-implementation-date-time').length) {
		createDateTimePicker($('.j-implementation-date-time').find('input'));
	}
	if (project.progressContract !== null) {
        if(project.progressContract.rentStartDate != null && project.progressContract.rentStartDate != undefined && project.progressContract.rentStartDate != ''){
            var rentStartDate = project.progressContract.rentStartDate;
        }
        if(project.progressContract.rentEndDate != null && project.progressContract.rentEndDate != undefined && project.progressContract.rentEndDate != ''){
            var rentEndDate = project.progressContract.rentEndDate;
        }
        if(project.progressContract.rentYear != null && project.progressContract.rentYear != undefined){
            $("#project_edit_rent_contract_number_of_year_progress_hidden").val(project.progressContract.rentYear);
        }
    }
	if ($("#project-form").length) {
		$("#project-form").validate(validation);
		if(project.externalReleaseConfirm){
			$("#j-form-external-release-confirm-label").html("外部公開日確定");
		} else {
			$("#j-form-external-release-confirm-label").html("外部公開日未確定");
		}
		$("#j-form-external-release-confirm").on("change", function () {
			let check = this.checked;
			if(check){
				$("#j-form-external-release-confirm-label").html("外部公開日確定");
			} else {
				$("#j-form-external-release-confirm-label").html("外部公開日未確定");
			}
		});

		suggestBlock = $('#project_edit_block').magicSuggest({
			maxSelection : 1,
			allowFreeEntries : false,
			noSuggestionText: "候補がありません",
			maxSelectionRenderer : function() {
				return "";
			},
			data : (project.block != null) ? [ {
				"id" : project.block.id,
				"name" : project.block.blockName
			} ] : [],
			value : (project.block != null) ? [ project.block.id ] : []
		});
		suggestArea = $('#project_edit_area').magicSuggest({
			maxSelection : 1,
			allowFreeEntries : false,
			noSuggestionText: "候補がありません",
			maxSelectionRenderer : function() {
				return "";
			},
			data : (project.area != null) ? [ {
				"id" : project.area.id,
				"name" : project.area.areaName
			} ] : [],
			value : (project.area != null) ? [ project.area.id ] : []
		});
		suggestBuilding = $('#project_edit_building')
			.magicSuggest(
					{
						maxSelection : 1,
						allowFreeEntries : false,
						noSuggestionText: "候補がありません",
						maxSelectionRenderer : function() {
							return "";
						},
						data : (project.building != null) ? [ {
							"id" : project.building.id,
							"name" : project.building.name
						} ] : [],
						value : (project.building != null) ? [ project.building.id ] : []
					});
		suggestShopName = $('#project_edit_shop_name')
			.magicSuggest(
					{
						maxSelection : 1,
						allowFreeEntries : false,
						noSuggestionText: "候補がありません",
						maxSelectionRenderer : function() {
							return "";
						},
						data : (project.shop != null) ? [ {
							"id" : project.shop.id,
							"name" : project.shopCd + ' ' + project.shop.shopNameZenkaku,
						} ] : [],
						value : (project.shop != null) ? [project.shop.id] : []
					});
		suggestBrand = $('#project_edit_brand')
			.magicSuggest(
					{
						maxSelection : 1,
						allowFreeEntries : false,
						noSuggestionText: "候補がありません",
						maxSelectionRenderer : function() {
							return "";
						},
						data : (project.brand != null) ? [ {
							"id" : project.brand.id,
							"name" : project.brand.brandIncomeUnitName
						} ] : [],
						value : (project.brand != null) ? [ project.brand.id ] : []
					});
		suggestCorporationGroup = $('#project_edit_corporation_group')
			.magicSuggest(
					{
						maxSelection : 1,
						allowFreeEntries : false,
						noSuggestionText: "候補がありません",
						maxSelectionRenderer : function() {
							return "";
						},
						data : (project.corporationGroup != null) ? [ {
							"id" : project.corporationGroup.id,
							"name" : project.corporationGroup.corporationGpName
						} ] : [],
						value : (project.corporationGroup != null) ? [ project.corporationGroup.id ] : []
					});
		suggestCorporation = $('#project_edit_corporation')
			.magicSuggest(
					{
						maxSelection : 1,
						allowFreeEntries : false,
						noSuggestionText: "候補がありません",
						maxSelectionRenderer : function() {
							return "";
						},
						data : (project.corporation != null) ? [ {
							"id" : project.corporation.id,
							"name" : project.corporation.corporationName
						} ] : [],
						value : (project.corporation != null) ? [ project.corporation.id ] : []
					});
		suggestProgressContractTargetName = $('#project_edit_progress_contract_target_name')
			.magicSuggest(
					{
						maxSelection : 1,
						allowFreeEntries : false,
						noSuggestionText: "候補がありません",
						maxSelectionRenderer : function() {
							return "";
						},
						data : (project.progressContract != null && project.progressContract.contractTargetName != null) ? [ {
							"id" : project.progressContract.contractTargetName,
							"name" : project.progressContract.contractTargetName
						} ] : [],
						value : (project.progressContract != null && project.progressContract.contractTargetName != null) ? [ project.progressContract.contractTargetName ] : []
					});
		suggestPrefectures = $('#project_edit_prefectures')
			.magicSuggest(
					{
						maxSelection : 1,
						allowFreeEntries : false,
						noSuggestionText: "候補がありません",
						maxSelectionRenderer : function() {
							return "";
						},
						data : (project.prefectures != null) ? [ {
							"id" : project.prefectures.id,
							"name" : project.prefectures.prefecturesName
						} ] : [],
						value : (project.prefectures != null) ? [ project.prefectures.id ] : []
					});

		suggestAccountStore = $('#project_edit_other_account_store')
			.magicSuggest(
					{
						allowFreeEntries : false,
						noSuggestionText: "候補がありません",
						data : (project.storeAccounts != null) ? Array
								.from(project.storeAccounts
										.map(function(obj) {
											return {
												id : obj.id,
												name : obj.name
											}
										}))
								: [],
						value : (project.storeAccounts != null) ? Array
								.from(project.storeAccounts
										.map(function(obj) {
											return obj.id;
										}))
								: []
					});
		suggestAccountSales = $('#project_edit_other_account_sales')
			.magicSuggest(
					{
						allowFreeEntries : false,
						noSuggestionText: "候補がありません",
						data : (project.salesAccounts != null) ? Array
								.from(project.salesAccounts
										.map(function(obj) {
											return {
												id : obj.id,
												name : obj.name
											}
										}))
								: [],
						value : (project.salesAccounts != null) ? Array
								.from(project.salesAccounts
										.map(function(obj) {
											return obj.id;
										}))
								: []
					});
		suggestAccountSales = $('#project_edit_other_account_sales')
			.magicSuggest(
					{
						allowFreeEntries : false,
						noSuggestionText: "候補がありません",
						data : (project.salesAccounts != null) ? Array
								.from(project.salesAccounts
										.map(function(obj) {
											return {
												id : obj.id,
												name : obj.name
											}
										}))
								: [],
						value : (project.salesAccounts != null) ? Array
								.from(project.salesAccounts
										.map(function(obj) {
											return obj.id;
										}))
								: []
					});
		suggestAccountSection = $('#project_edit_other_account_section')
			.magicSuggest(
					{
						allowFreeEntries : false,
						noSuggestionText: "候補がありません",
						data : (project.sectionAccounts != null) ? Array
								.from(project.sectionAccounts
										.map(function(obj) {
											return {
												id : obj.id,
												name : obj.name
											}
										}))
								: [],
						value : (project.sectionAccounts != null) ? Array
								.from(project.sectionAccounts
										.map(function(obj) {
											return obj.id;
										}))
								: []
					});
		suggestAccountFc = $('#project_edit_other_account_fc')
			.magicSuggest(
					{
						allowFreeEntries : false,
						noSuggestionText: "候補がありません",
						data : (project.fcAccounts != null) ? Array
								.from(project.fcAccounts
										.map(function(obj) {
											return {
												id : obj.id,
												name : obj.name
											}
										}))
								: [],
						value : (project.fcAccounts != null) ? Array
								.from(project.fcAccounts
										.map(function(obj) {
											return obj.id;
										}))
								: []
					});
		suggestAccountOther = $('#project_edit_other_account_other')
			.magicSuggest(
					{
						allowFreeEntries : false,
						noSuggestionText: "候補がありません",
						data : (project.otherAccounts != null) ? Array
								.from(project.otherAccounts
										.map(function(obj) {
											return {
												id : obj.id,
												name : obj.name
											}
										}))
								: [],
						value : (project.otherAccounts != null) ? Array
								.from(project.otherAccounts
										.map(function(obj) {
											return obj.id;
										}))
								: []
					});
		suggestAccountLeader = $('#project_edit_other_account_leader')
			.magicSuggest(
					{
						allowFreeEntries : false,
						noSuggestionText: "候補がありません",
						data : (project.leaderAccounts != null) ? Array
								.from(project.leaderAccounts
										.map(function(obj) {
											return {
												id : obj.id,
												name : obj.name
											}
										}))
								: [],
						value : (project.leaderAccounts != null) ? Array
								.from(project.leaderAccounts
										.map(function(obj) {
											return obj.id;
										}))
								: []
					});
			if (projectPlanProgress.salesAgencyProgress != undefined && projectPlanProgress.salesAgencyProgress.name != undefined && projectPlanProgress.salesAgencyProgress.name != ""){
	            var agencyProgresstName = projectPlanProgress.salesAgencyProgress.name;
	        }
			setTimeout(function(){
				projectSaleAgencyTargetProgressSuggest = $('#project-edit-i-sales-agency-target-progress')
				.magicSuggest(
						{
							noSuggestionText: "候補がありません",
							maxSelection : 1,
							allowFreeEntries : false,
							maxSelectionRenderer : function() {
								return "";
							},
							placeholder: "",
							data : (agencyProgresstName != null) ? [ {
								"id" : 0,
								"name" : agencyProgresstName
							} ] : [],
							value : (agencyProgresstName != null) ? [ 0 ] : []
						});
			}, 1000);

			if (projectPlanProgress.participatingStoreProgress != undefined && projectPlanProgress.participatingStoreProgress.name != undefined && projectPlanProgress.participatingStoreProgress.name != ""){
	            var storeProgressName = projectPlanProgress.participatingStoreProgress.name;
	        }
			setTimeout(function(){
				projectParticipatingStoreCorporationProgressSuggest = $('#project-edit-participating-store-corporation-progress')
				.magicSuggest(
						{
							noSuggestionText: "候補がありません",
							maxSelection : 1,
							allowFreeEntries : false,
							maxSelectionRenderer : function() {
								return "";
							},
							placeholder: "",
							data : (storeProgressName != null) ? [ {
								"id" : 0,
								"name" : storeProgressName
							} ] : [],
							value : (storeProgressName != null) ? [ 0 ] : []
						});
			}, 1000);

		$.each(
				project.projectTasks,
					function(index, task) {
						$('#j-form-task-period-exsist' + task.id).rules("add", {
							required: true,
							dateISO : true,
							messages : {
								required: "期限を選択してください。",
								dateISO : "日付の形式を正しく入力してください。",
							}
						});
						let suggestTaskAccount = $(
								'#project_edit_task_account_exist' + task.id)
									.magicSuggest(
											{
												noSuggestionText: "候補がありません",
												allowFreeEntries : false,
												data : (task.account != null) ? Array
														.from(task.account
															.map(function(obj) {
																return {
																	id : obj.id,
																	name : obj.name
																}
															}))
												: [],
												value : (task.account != null) ? Array
														.from(task.account
																.map(function(obj) {
																	return obj.id;
																}))
													: [],
											});
						createSuggest(suggestTaskAccount, "/sp/project/find/account");
						$(suggestTaskAccount).on(
								'selectionchange',
								function() {
									let selection = this.getSelection();
									let html = "";

									$.each(selection, function(index){
										html += '<input type="hidden" propertyName="account['+index+'].accountId" value="'+this.id+'">';
									});

									$('#project_edit_id_task_account_exist' + task.id).html(html);
								});
				})
			operationFormChange();
			$("#j-form-operation-form").on("change", function () {
				operationFormChange();
			});
	}

	refBuildingData.corporation = {};
	refBuildingData.building = {};
	refBuilding = project.building !== null;
	refShop = project.shop !== null;

	if(refBuilding){
		refBuildingData.buildingName = project.building.name;
		refBuildingData.building.adoptDifficulty = project.building.adoptDifficulty;

        if (project.corporationGroup !== null) {
            refBuildingData.corporationGroupName = project.corporationGroup.corporationGpName;
            refBuildingData.corporation.corporationGpId = project.corporationGpId;
        }
        if (project.corporation !== null) {
            refBuildingData.corporationName = project.corporation.corporationName;
            refBuildingData.building.iCorporationId = project.corporationId;
        }
        if (project.area !== null) {
            refBuildingData.areaName = project.area.areaName;
            refBuildingData.building.iAreaId = project.iAreaId;
        }
        if (project.block !== null) {
            refBuildingData.blockName = project.block.blockName;
            refBuildingData.building.iBlockId = project.iBlockId;
        }
        if (project.prefectures !== null) {
            refBuildingData.prefecturesName = project.prefectures.prefecturesName;
            refBuildingData.building.iPrefecturesId = project.iPrefecturesId;
        }
		if (project.salesChannel !== null) {
			refBuildingData.building.iSalesChannelId = project.iSalesChannelId;
            refBuildingData.salesChannelName = project.salesChannel.salesChannelName;
        }
	}
	setDataFromBuilding();

	if(refShop){
        refShopData.shopName = project.shopName;
        refShopData.shopCd = project.shopCd;
        if (project.building !== null) {
            refShopData.buildingId = project.building.id;
            refShopData.buildingName = project.building.name;
        }
        if (project.currentContract !== null) {
        	refShopData.rentContract = {};
        	refShopData.rentContract.contractTargetName = project.currentContract.contractTargetName;
        	refShopData.rentContract.startDateString = project.currentContract.contractStartDate;
        	refShopData.rentContract.endDateString = project.currentContract.contractEndDate;
        	refShopData.rentContract.term = project.currentContract.term;
        	refShopData.rentContract.autoUpdate = project.currentContract.autoUpdate;
        	
        	refShopData.rentStartDate = project.currentContract.rentStartDate;
        	refShopData.rentEndDate = project.currentContract.rentEndDate;
        	refShopData.rentYear = project.currentContract.rentYear;

        	refShopData.rentContract.iContractType = {};
        	refShopData.rentContract.iContractType.contractTypeName = project.currentContract.form;
        	
        	refShopData.operationForm = project.currentContract.operationForm;
        }
        if (project.currentSectionProgress !== null) {
        	refShopData.section = project.currentSectionProgress.section;
        	refShopData.frontage = project.currentSectionProgress.frontage;
        	refShopData.floorNum = project.currentSectionProgress.floor;
        	refShopData.contractTsubo = project.currentSectionProgress.contractTsubo;
        	refShopData.businessHours = project.currentSectionProgress.businessHours;
        	refShopData.buildingExpectedValue = project.currentSectionProgress.buildingExpectedValue;
        }
        if (project.listProjectPlan !== null ) {
        	project.listProjectPlan.forEach(function (element){
                if ( element.category == "CURRENT"){
                    salesAgencyTarget = element.salesAgencyCurrent;
                    salesAgencyContract.startDate = element.startDate;
                    salesAgencyContract.endDate = element.endDate;
                    salesAgencyContract.salesAgencyTargetId = element.salesAgencyTargetId;
                    salesAgencyContract.numberOfYear = element.numberOfYear;
                    participatingStoreCorporation = element.participatingStoreCurrent;

                    $("#project-edit-i-sales-agency-target-curent-label").html(salesAgencyTarget.name);
                    
                    let startDateString = element.startDate !== undefined && element.startDate !== null? element.startDate : "";
                    let endDateString = element.endDate !== undefined && element.endDate !== null? element.endDate : "";
                    let numberOfYear = element.numberOfYear;
                    let dateNumOfYear = "";
                    if(startDateString !== "" || endDateString !== ""){
                    	dateNumOfYear += startDateString + "～";
                    	
                    	if(endDateString !== "9999-12-31"){
                    		dateNumOfYear += endDateString;
                    	}
                    	
                    	if(numberOfYear !== undefined && numberOfYear !== null && numberOfYear !== ""){
                    		dateNumOfYear += "（" + roundNumber(numberOfYear, 2) + "年）";
                    	}
        			}
                    $("#project-edit-i-sales-agency-contract-curent-date-number-of-year-label").html(dateNumOfYear);
                    
                    $("#project-edit-participating-store-corporation-curent-label").html(participatingStoreCorporation.name);
                }
            });
        }
        refShopData.isDisplayEconomy = project.shop.isDisplayEconomy;
	}
	setDataFromShop(false);

	if(project.brandId != null){
		showFormBusinessCard(project.brandId);
		createProjectTitle.brand = project.brand.brandIncomeUnitName;
		makeProjectTitle();
	}

	createSuggest(suggestBuilding, "/sp/project/find/building");
	createSuggest(suggestShopName, "/sp/project/find/shop");
	createSuggest(suggestBrand, "/sp/project/find/ibrandincomeunit");
	createSuggest(suggestBlock, "/sp/project/find/iblock");
	createSuggest(suggestArea, "/sp/project/find/iarea");
	createSuggest(suggestCorporationGroup, "/sp/project/find/i_corporation_group");
	createSuggest(suggestCorporation, "/sp/project/find/icorporation");
	createSuggest(suggestPrefectures, "/sp/project/find/iprefectures");
	createSuggest(suggestAccountStore, "/sp/project/find/account");
	createSuggest(suggestAccountSales, "/sp/project/find/account");
	createSuggest(suggestAccountSection, "/sp/project/find/account");
	createSuggest(suggestAccountSection, "/sp/project/find/account");
	createSuggest(suggestAccountFc, "/sp/project/find/account");
	createSuggest(suggestAccountOther, "/sp/project/find/account");
	createSuggest(suggestAccountLeader, "/sp/project/find/account");
	createSuggest(projectSaleAgencyTargetProgressSuggest, "/sp/project/find/salesagencytarget");
	createSuggest(projectParticipatingStoreCorporationProgressSuggest, "/sp/project/find/participatingstorecorporation");
	createSuggest(suggestProgressContractTargetName, "/sp/project/find/icorporation");

	setTimeout(function() {
		$('input.ms-inv').width('100%');
	}, 1000);

	$(suggestBuilding).on( 'selectionchange', function() {
		var selectedData = this.getSelection();
		if (selectedData.length > 0) {
    		refBuilding = true;
        	makePostRequest("/sp/project/refBuildingData/" + selectedData[0].id, {}).then(function (data) {
        		refBuildingData = data;
        		setDataFromBuilding();
            });
    	} else {
    		refBuilding = false;
    		setDataFromBuilding();
    	}
	});

	$(suggestShopName).on( 'selectionchange', function() {
		var selectedData = this.getSelection();
		if (selectedData.length > 0) {
    		projectData.shopName = selectedData[0].rawName;
    		projectData.shopCd = selectedData[0].shopCd;
    		refShop = true;
            let id = selectedData[0].id;
            if(id){
            	processSuggestShopNameData(selectedData[0].id);
			}
			if(refShopData.rentContract.iContractType.contractTypeName!= undefined){
				$("#project-contract-progress-edit-form").val(refShopData.rentContract.iContractType.contractTypeName);
				currentContract.form = refShopData.rentContract.iContractType.contractTypeName;
			}
            $("#project_edit_shop_name_temporary").hide();
    	} else {
    		$("#project_edit_shop_name_temporary").show();
    		$("#j-form-management-form").empty();
    		$("#j-form-management-form").hide();
            $("#j-form-brand-label-option").hide();
            $("#j-form-brand-label-span").hide();
            $("#project_edit_brand").show();
            if(suggestBrand.getValue().length <= 0){
            	$("#j-form-business-card").hide();
            } else {
            	showFormBusinessCard(suggestBrand.getValue()[0]);
            }

            refShop = false;
			if(suggestBuilding.getValue().length > 0){
				refBuilding = true;
	        	makePostRequest("/sp/project/refBuildingData/" + suggestBuilding.getValue()[0], {}).then(function (data) {
	        		refShopData.buildingId = data.building.id;
	        		refBuildingData = data;
	        		setDataFromBuilding();
	            });
			} else {
				refBuilding = false;
			}
			setDataFromShop(false);

            $("#number-of-year-agency-contract-current").val("");
            participatingStoreCorporation = {};
            salesAgencyContract = {};
            salesAgencyTarget = {};
        }
	});

	$(suggestBrand).on( 'selectionchange', function() {
		var selectedData = this.getSelection();
		if (selectedData.length > 0) {
			projectData.brandId = selectedData[0].id;
            showFormBusinessCard(projectData.brandId);
            createProjectTitle.brand = selectedData[0].name;
    	} else {
    		project.brandId = null;
    		projectData.brandId = undefined;
    		$("#j-form-business-card").hide();
    		createProjectTitle.brand = "";
    	}
		makeProjectTitle();
	});

	$("#project_edit_landing_category").on("change", function(){
		if ($("#project_edit_landing_category").val() != "") {
            createProjectTitle.landing = $("#project_edit_landing_category option:selected").text();
            makeProjectTitle();
        }
	});

	$(document).on(
			'click',
			'#j-update',
			function() {
				$("label.error").remove();

				let form = $('#project-form');
				var implementationScheduleDatetimeYear = $("#project_edit_implementation_schedule_datetime_year").val();
				var implementationScheduleDatetimeMonth = $("#project_edit_implementation_schedule_datetime_month").val();
				var implementationScheduleDatetimeDay =  $("#project_edit_implementation_schedule_datetime_day").val();
				validateImplementationScheduleDate(implementationScheduleDatetimeYear, implementationScheduleDatetimeMonth, implementationScheduleDatetimeDay);
				let $this = this;
				$($this).addClass("disabled");
				if (!form.valid()) {
					$($this).removeClass("disabled");
					checkValidSuggest();
					createValidMessage();
					return;
				}
				if (!checkValidSuggest()) {
					$($this).removeClass("disabled");
					createValidMessage();
					return;
				}
				if ($("#j-form-operation-form").val() !== 'FC') {
					suggestAccountFc.clear();
				}
				// Create data
				var data = new FormData();
				if(refShop){
					getDataSuggestShop(data);
					if(projectData.shopCd != undefined){
						data.append("shopCd", projectData.shopCd);
					}
					if(projectData.shopName != undefined){
						data.append("shopName", projectData.shopName);
					} else {
						data.append("shopName", refShopData.shopName);
						data.append("shopCd", refShopData.shopCd);
					}
					if(listBrandIncome.length > 1){
						let idBrand = $("#j-form-brand-label-option").val();
						data.append("brandId", idBrand);
					} else if(projectData.brandId !== undefined){
						data.append("brandId", projectData.brandId);
					} else if(project.brandId !== null){
						data.append("brandId", project.brandId);
					} else {
						data.append("brandId", "");
					}

					if(refShopData.buildingId !== null && refShopData.buildingId !== undefined){
						data.append("buildingId", refShopData.buildingId);
					}else{
						getDataSuggestBuilding(data);
					}
					
					if(!checkProperties(currentContract)){
						if(currentContract.form !== undefined && currentContract.form !== null && currentContract.form.length > 0){
							data.append("projectContractProgressDto[0].form", currentContract.form);
						}
						if(currentContract.contractTargetName !== undefined && currentContract.contractTargetName !== null && currentContract.contractTargetName.length > 0){
							data.append("projectContractProgressDto[0].contractTargetName", currentContract.contractTargetName);
						}
						if(currentContract.contractStartDate !== undefined && currentContract.contractStartDate !== null && currentContract.contractStartDate.length > 0){
							data.append("projectContractProgressDto[0].contractStartDate", currentContract.contractStartDate);
						}
						if(currentContract.contractEndDate !== undefined && currentContract.contractEndDate !== null && currentContract.contractEndDate.length > 0){
							data.append("projectContractProgressDto[0].contractEndDate", currentContract.contractEndDate);
						}
						if(currentContract.contractNumberOfYear !== undefined && currentContract.contractNumberOfYear !== null){
							data.append("projectContractProgressDto[0].contractNumberOfYear", currentContract.contractNumberOfYear);
						}
						if(currentContract.autoUpdate !== undefined && currentContract.autoUpdate !== null){
							data.append("projectContractProgressDto[0].autoUpdate", currentContract.autoUpdate);
						}
						if(currentContract.rentStartDate !== undefined && currentContract.rentStartDate !== null && currentContract.rentStartDate.length > 0){
							data.append("projectContractProgressDto[0].rentStartDate", currentContract.rentStartDate);
						}
						if(currentContract.rentEndDate !== undefined && currentContract.rentEndDate !== null && currentContract.rentEndDate.length > 0){
							data.append("projectContractProgressDto[0].rentEndDate", currentContract.rentEndDate);
						}
						if(currentContract.rentYear !== undefined && currentContract.rentYear !== null){
							data.append("projectContractProgressDto[0].rentYear", currentContract.rentYear);
						}
						if(currentContract.operationForm !== undefined && currentContract.operationForm !== null){
							data.append("projectContractProgressDto[0].operationForm", currentContract.operationForm);
						}
                    }
					
//					if(!checkProperties(salesAgencyTarget)){
//                        projectPlanCurrent.salesAgencyTargetId = salesAgencyTarget.id;
//                    }
                    if (!checkProperties(salesAgencyContract)){
                        let endDate = salesAgencyContract.endDate;
                        let startDate = salesAgencyContract.startDate;
                        projectPlanCurrent.startDate = (new Date(startDate) instanceof Date) ? startDate : null;
                        projectPlanCurrent.endDate = (new Date(endDate) instanceof Date) ? endDate : null;
                        projectPlanCurrent.numberOfYear = salesAgencyContract.numberOfYear;
                        projectPlanCurrent.salesAgencyTargetId = salesAgencyContract.salesAgencyTargetId;
                    }
                    if(!checkProperties(participatingStoreCorporation)){
                        projectPlanCurrent.participatingStoreCorporationId = participatingStoreCorporation.id;
                    }

                    if(refShopData.section !== undefined && refShopData.section !== null){
                    	data.append("projectSectionProgressDto[0].section", refShopData.section);
                    }
                    if(refShopData.frontage !== undefined && refShopData.frontage !== null){
                    	data.append("projectSectionProgressDto[0].frontage", refShopData.frontage);
                    }
                    if(refShopData.floorNum !== undefined && refShopData.floorNum !== null){
                    	data.append("projectSectionProgressDto[0].floor", refShopData.floorNum);
                    }
                    if(refShopData.contractTsubo !== undefined && refShopData.contractTsubo !== null){
                    	data.append("projectSectionProgressDto[0].contractTsubo", refShopData.contractTsubo);
                    }
                    if(refShopData.businessHours !== undefined && refShopData.businessHours !== null){
                    	data.append("projectSectionProgressDto[0].businessHours", refShopData.businessHours);
                    }
                    if(refShopData.buildingExpectedValue !== undefined && refShopData.buildingExpectedValue !== null){
                    	data.append("projectSectionProgressDto[0].buildingExpectedValue", refShopData.buildingExpectedValue);
                    }
				} else{
					getDataSuggestBuilding(data);
					getDataSuggestBrand(data);
//					projectPlanCurrent.startDate = $("#project-edit-i-sales-agency-contract-startdate-curent-label").val();
//                  projectPlanCurrent.endDate = $("#project-edit-i-sales-agency-contract-enddate-curent-label").val();
					
					let operationFormValue = $("#j-form-operation-form").val();
					if(operationFormValue !== undefined && operationFormValue !== null){
						data.append("projectContractProgressDto[0].operationForm", operationFormValue);
					}
				}
				
				let buildingExpectedValue = $("#project_edit_progress_negotiation_building_expected_value").val();
				if(buildingExpectedValue !== undefined 
					&& buildingExpectedValue !== null 
					&& buildingExpectedValue.length > 0 
					&& !isNaN(buildingExpectedValue)){
					data.append("projectSectionProgressDto[1].buildingExpectedValue", buildingExpectedValue * 1000);
				}

				if (refShop && !checkProperties(projectPlanCurrent)) {
	                projectPlanCurrent.category = "CURRENT";
	                projectPlanDto.push(projectPlanCurrent);
	            }
				if(projectSaleAgencyTargetProgressSuggest.getValue() > 0){
					projectPlanProgress.salesAgencyTargetId = projectSaleAgencyTargetProgressSuggest.getValue()[0];
				}
				if(projectParticipatingStoreCorporationProgressSuggest.getValue() > 0){
					projectPlanProgress.participatingStoreCorporationId = projectParticipatingStoreCorporationProgressSuggest.getValue()[0];
				}
				projectPlanProgress.startDate = $("#project-edit-i-sales-agency-contract-startdate-progress").val();
	            projectPlanProgress.endDate = $("#project-edit-i-sales-agency-contract-enddate-progress").val();
	            projectPlanProgress.numberOfYear = $("#project_edit_i_sales_agency_contract_number_of_year_progress_hidden").val();
	            if (!checkProperties(projectPlanProgress)) {
	                projectPlanProgress.category = "PROGRESS";
	                projectPlanDto.push(projectPlanProgress);
	            }

				if(refBuilding){
					if(refBuildingData.corporation !== null && refBuildingData.corporation.corporationGpId != undefined) {
						data.append("corporationGpId", refBuildingData.corporation.corporationGpId);
					}

					if(refBuildingData.building !== null) {
						if (refBuildingData.building.iCorporationId !== undefined) {
							data.append("corporationId", refBuildingData.building.iCorporationId);
						}
						if (refBuildingData.building.iBlockId !== undefined) {
							data.append("iBlockId", refBuildingData.building.iBlockId);
						}
						if (refBuildingData.building.iAreaId !== undefined) {
							data.append("iAreaId", refBuildingData.building.iAreaId);
						}
						if (refBuildingData.building.iPrefecturesId !== undefined) {
							data.append("iPrefecturesId", refBuildingData.building.iPrefecturesId);
						}
						if (refBuildingData.building.iSalesChannelId !== undefined) {
							data.append("iSalesChannelId", refBuildingData.building.iSalesChannelId);
						}
						if (refBuildingData.building.adoptDifficulty !== undefined) {
							data.append("adoptDifficulty", refBuildingData.building.adoptDifficulty);
						}
					}
				} else{
					getDataSuggestCorporationGroup(data);
					getDataSuggestCorporation(data);
					getDataSuggestBlock(data);
					getDataSuggestArea(data);
					getDataSuggestPrefectures(data);
					if($("#project_edit_sales_channel").val() != undefined){
						data.append("iSalesChannelId", $("#project_edit_sales_channel").val());
					}
				}
				getDataSuggestProgressContractTargetName(data);

				var statusIds = [];
				var statusIdList = $(".schedule-status-id").each(function(index, obj) {
					statusIds.push($(obj).val());
				});

				$(".schedule-datepicker").each(function (index, element) {
					var id = statusIds[index];
					data.append('projectScheduleDto['+index+'].actionStatusId', parseInt(id));
					data.append('projectScheduleDto['+index+'].scheduleDate', element.getAttribute('value'));
				});

				getDataOtherProjectTeam(data);
				getDataOtherAccount(data);
				// Append Data form
				form.find(".ng-hide").find("select, input").each(function(){
					$(this).removeAttr('name');
				});
				$.each(form.serializeArray(), function(index, obj) {
					data.append(obj.name, obj.value);
				});
				$.each(form.find('input[type=checkbox][name]:not(:checked)'), function (index, element) {
	            	data.append($(element).attr('name'), false);
	            });
				getListDataForRequest(data);

				// get list project file
				$.each(form.find('div[objectName="projectMediaDocumentDto"][class="media-list-item has-trash new-item"]'), function(index, obj) {
					data.append("files", $(this).prop("data"));
				});

				// get list opinion file
				if($("#project-edit-branch-opinion-has-file").val() === "true"){
					data.append("opinionFiles", $("#project-edit-branch-opinion-file").prop("files")[0]);
				}
				if($("#project-edit-view-opinion-has-file").val() === "true"){
					data.append("opinionFiles", $("#project-edit-view-opinion-file").prop("files")[0]);
				}
				if($("#project-edit-bu-opinion-has-file").val() === "true"){
					data.append("opinionFiles", $("#project-edit-bu-opinion-file").prop("files")[0]);
				}

				projectPlanDto = projectPlanDto.map(function (pl) {
					return { "projectId": pl.projectId, "category": pl.category, "startDate": pl.startDate, "endDate": pl.endDate, "numberOfYear": pl.numberOfYear, "salesAgencyTargetId": pl.salesAgencyTargetId, "participatingStoreCorporationId": pl.participatingStoreCorporationId };
				});
				if(projectPlanDto.length > 0){
					projectPlanDto.forEach(function (element, index) {
						for (var key in element) {
					        if (element[key] !== null && element[key] != "" && element[key] != undefined){
					        	data.append('projectPlanDto['+index+'].' + key, element[key]);
					        }
					    }
					})
				}
				if(implementationScheduleDatetimeYear != '' && implementationScheduleDatetimeMonth != '' && implementationScheduleDatetimeDay != ''){
	                data.append('implementationScheduleDatetime' , implementationScheduleDatetimeYear + "-" + ("0" + implementationScheduleDatetimeMonth).slice(-2) + "-" + implementationScheduleDatetimeDay);
	            }else{
					data.append('implementationScheduleDatetime' , '');
				}

				data.append("shopNameTemporary", $("#project_edit_shop_name_temporary").val());

				jQuery.ajax({
					url : createUrl("/pc/project/update"),
					data : data,
					cache : false,
					contentType : false,
					processData : false,
					method : 'POST',
					type : 'POST', // For jQuery < 1.9
					success : function(response) {
						window.location.replace(createUrl("/pc/project/detail/"
								+ response));
					},
					error : function(e) {
						alert("更新に失敗しました。");
						$($this).removeClass("disabled");
					}
				});
			});

	// Add Task
	$('#addProjectTask')
			.click(
					function() {
						indexTask++;
						let htmltext = '<div class="media-list-item has-trash new-item" objectName="projectTaskDtos"><br>';
						htmltext += '<div class="row">';
						htmltext += '<input class="object-property" type="hidden" propertyName="id" value="0">';
						htmltext += '<div class="col-md-12">';
						htmltext += '<div class="form-group">';
						htmltext += '<div class="row">';
						htmltext += '<div class="col-md-2">';
						htmltext += '<label>内容</label>';
						htmltext += '</div>';
						htmltext += '<div class="col-md-9">';
						htmltext += '<textarea id="j-form-task-comment-add'+indexTask+'" class="form-control" propertyName="comment" rows="3" j-active="comment" placeholder="内容を入力してください"></textarea>';
						htmltext += '</div>';
						htmltext += '</div>';
						htmltext += '</div>';
						htmltext += '<div class="form-group">';
						htmltext += '<div class="row">';
						htmltext += '<div class="col-md-2">';
						htmltext += '<label>重要度</label>';
						htmltext += '</div>';
						htmltext += '<div class="col-md-9">';
							htmltext += '<select propertyName="important">';
								htmltext += '<option value="低">低</option>';
								htmltext += '<option value="中" selected>中</option>';
								htmltext += '<option value="高">高</option>';
							htmltext += '</select>';
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
						htmltext += '<label>期限日</label>';
						htmltext += '</div>';
						htmltext += '<div class="col-md-9 j-implementation-date">';
						htmltext += '<input id="j-form-task-period-add'+indexTask+'" name="j-form-task-period-add'+indexTask+'" class="form-control" type="text" placeholder="期限日を入力してください" propertyName="period">';
						htmltext += '</div>';
						htmltext += '</div>';
						htmltext += '</div>';
						htmltext += '<div class="form-group">';
						htmltext += '<div class="row">';
						htmltext += '<div class="col-md-2">';
						htmltext += '<label>担当者</label>';
						htmltext += '</div>';
						htmltext += '<div class="col-md-9">';
						htmltext += '<input class="form-control" placeholder="担当者を入力してください" id="j-form-task-account-add'+indexTask+'">';
						htmltext += '<div id="j-form-id-task-account-add'+indexTask+'">';
						htmltext += '</div>';
						htmltext += '</div>';
						htmltext += '</div>';
						htmltext += '</div>';
						htmltext += '</div>';
						htmltext += '</div>';
						htmltext += '</div>';
						$('#task > .tab-content-body > .media-list').prepend(htmltext);

						if ($('.j-implementation-date').length) {
							datepickerUtils.createDatePicker($('.j-implementation-date').find('input'));
						}

						$('#j-form-task-period-add' + indexTask).rules("add", {
							required: true,
							dateISO : true,
							messages : {
								required: "期限を選択してください。",
								dateISO : "日付の形式を正しく入力してください。",
							}
						});
						let suggestTaskAccount = $(
								'#j-form-task-account-add' + indexTask)
								.magicSuggest(
										{
											maxSelectionRenderer: function () {
												return "";
											},
											allowFreeEntries : false,
										});
						createSuggest(suggestTaskAccount, "/sp/project/find/account");
						$(suggestTaskAccount).on(
								'selectionchange',
								function() {
									let selection = this.getSelection();
									let html = "";

									$.each(selection, function(index){
										html += '<input type="hidden" propertyName="account['+index+'].accountId" value="'+this.id+'">';
									});
									$('#j-form-id-task-account-add' + indexTask).html(html);
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

	$("#add-new-file").on("change", function() {
		var file = this.files[0];
	    var reader = new FileReader();
	    var ret = 0;

	    permitType.forEach(function (element) {
	    	if (element.type == file.type) {
		    	reader.readAsDataURL(file);

	            reader.onload = function() {
	            	$('#related-document > .tab-content-body > .media-list').prepend(htmlAddFile(0, file.name, this.result, element.division, "", "", false));
	            	$('#project-file-add'+indexFile).prop("data", file);
	            	$('#project-file-add-output-flag-' + indexFile).on("change", function(){
	            		let outputNumberTargetId = $(this).attr("id").replace("output-flag", "output-number");
	            		if($(this).val() === 'T'){
	            			$("#" + outputNumberTargetId).show();
	            		} else{
	            			$("#" + outputNumberTargetId).hide();
	            		}
	            	});
	            };
	            ret = 1;
	    	}
	    });

	    if (ret === 0) {
	    	alert("ファイルの拡張子はjpg、png、gif、docx、xlsx、pdf、pptx、mp4またはmovでアップロードしてください");
	        return;
		}
	});

	$("#addReferenceBuildingFileModal").on('show.bs.modal', function (eventModel) {
		 let buildingId = 0;

		 if(refShop){
			 buildingId = refShopData.buildingId;
		 }else{
			 buildingId = suggestBuilding.getValue()[0];
		 }

		 if(buildingId !== undefined && buildingId !== null){
			 makePostRequest("/sp/project/find/buildingfile/" + buildingId, {}).then(function (data) {
				 let files = data;
				 let target = $('#addReferenceBuildingFileModal').find('.media-list');

				 target.html("");
				 if(files.length > 0){

					 $.each(files, function() {
						 let file = this;
						 let html = '<div class="media-list-item">';
						 html += '<div class="row">';
						 html += '<div class="col-md-1">';
						 html += '<input class="ref-building-file-choose" add-id="'+file.id+'" add-name="'+file.originFileName+'" add-src="/wsd/pc/project/'+file.urlPath+'" add-path="'+file.path+'" add-url-path="'+file.urlPath+'" add-type="'+file.type+'" type="checkbox">';
						 html += '</div>';
						 html += '<div class="col-md-2">';
						 html += '<div class="img">';

						 if(file.path !== null){
							 html += '<a target="_blank" href="/wsd/pc/project/'+file.path+'">';
							 html += '<img class="media-object img-rounded" src="/wsd/pc/project/'+file.path+'" alt="">';
							 html += '</a>';
						 }else{
							 html += '<a target="_blank" href="/wsd/pc/project/img/no_img.jpg">';
							 html += '<img class="media-object img-rounded" src="/wsd/pc/project/img/no_img.jpg" alt="no_img">';
							 html += '</a>';
						 }

						 html += '</div>';
						 html += '</div>';
						 html += '<div class="col-md-9">';
						 html += '<h4 class="media-heading">'+file.displayName + ' (' + file.size + ')</h4>';
						 html += '<p class="comment">'+file.comment+'</p>';
						 html += '<p class="created-date">'+file.updateDatetimeValue+'</p>';
						 html += '</div>';
						 html += '</div>';
						 html += '</div>';

						 target.append(html);
					 });
				 }else{
					 target.html('<div>ファイルがありません</div>');
				 }
			 });
		 }else{
			 alert("館を選んでください");
			 eventModel.preventDefault();
		 }
	 });

	$('#add-reference-building-file').on('click', function(){
		$('#addReferenceBuildingFileModal').find('input[class="ref-building-file-choose"][type="checkbox"]:checked').each(function(){
			var addId = $(this).attr("add-id");
			var addName = $(this).attr("add-name");
			var addSource = $(this).attr("add-src");
			var addPath = $(this).attr("add-path");
			var addUrlPath = $(this).attr("add-url-path");
			var addType = $(this).attr("add-type");
			$(this).prop("checked", false);

			if(addType === "IMAGE"){
				$('#related-document > .tab-content-body > .media-list').prepend(htmlAddFile(addId, addName, addSource, "image", addPath, addUrlPath, true));
			}else{
				$('#related-document > .tab-content-body > .media-list').prepend(htmlAddFile(addId, addName, addSource, "/wsd/" + addPath, addPath, addUrlPath, true));
			}
			$('#project-file-add-output-flag-' + indexFile).on("change", function(){
        		let outputNumberTargetId = $(this).attr("id").replace("output-flag", "output-number");
        		if($(this).val() === 'T'){
        			$("#" + outputNumberTargetId).show();
        		} else{
        			$("#" + outputNumberTargetId).hide();
        		}
        	});
		});
	});

	$("#addReferenceShopFileModal").on('show.bs.modal', function (eventModel) {
		 let shopId = suggestShopName.getValue()[0];

		 if(shopId !== undefined && shopId !== null){
			 makePostRequest("/sp/project/find/shopfile/" + shopId, {}).then(function (data) {
				 let files = data;
				 let target = $('#addReferenceShopFileModal').find('.media-list');

				 target.html("");
				 if(files.length > 0){

					 $.each(files, function() {
						 let file = this;
						 let html = '<div class="media-list-item">';
						 html += '<div class="row">';
						 html += '<div class="col-md-1">';
						 html += '<input class="ref-shop-file-choose" add-id="'+file.id+'" add-name="'+file.originFileName+'" add-src="/wsd/pc/project/'+file.urlPath+'" add-path="'+file.path+'" add-url-path="'+file.urlPath+'" add-type="'+file.type+'" type="checkbox">';
						 html += '</div>';
						 html += '<div class="col-md-2">';
						 html += '<div class="img">';

						 if(file.path !== null){
							 html += '<a target="_blank" href="/wsd/pc/project/'+file.path+'">';
							 html += '<img class="media-object img-rounded" src="/wsd/pc/project/'+file.path+'" alt="">';
							 html += '</a>';
						 }else{
							 html += '<a target="_blank" href="/wsd/pc/project/img/no_img.jpg">';
							 html += '<img class="media-object img-rounded" src="/wsd/pc/project/img/no_img.jpg" alt="no_img">';
							 html += '</a>';
						 }

						 html += '</div>';
						 html += '</div>';
						 html += '<div class="col-md-9">';
						 html += '<h4 class="media-heading">'+file.displayName + ' (' + file.size + ')</h4>';
						 html += '<p class="comment">'+file.comment+'</p>';
						 html += '<p class="created-date">'+file.updateDatetimeValue+'</p>';
						 html += '</div>';
						 html += '</div>';
						 html += '</div>';

						 target.append(html);
					 });
				 }else{
					 target.html('<div>ファイルがありません</div>');
				 }
			 });
		 }else{
			 alert("店舗を選らんでください");
			 eventModel.preventDefault();
		 }
	 });

	$('#add-reference-shop-file').on('click', function(){
		$('#addReferenceShopFileModal').find('input[class="ref-shop-file-choose"][type="checkbox"]:checked').each(function(){
			var addId = $(this).attr("add-id");
			var addName = $(this).attr("add-name");
			var addSource = $(this).attr("add-src");
			var addPath = $(this).attr("add-path");
			var addUrlPath = $(this).attr("add-url-path");
			var addType = $(this).attr("add-type");
			$(this).prop("checked", false);

			if(addType === "IMAGE"){
				$('#related-document > .tab-content-body > .media-list').prepend(htmlAddFile(addId, addName, addSource, "image", addPath, addUrlPath, true));
			}else{
				$('#related-document > .tab-content-body > .media-list').prepend(htmlAddFile(addId, addName, addSource, "/wsd/" + addPath, addPath, addUrlPath, true));
			}
			$('#project-file-add-output-flag-' + indexFile).on("change", function(){
        		let outputNumberTargetId = $(this).attr("id").replace("output-flag", "output-number");
        		if($(this).val() === 'T'){
        			$("#" + outputNumberTargetId).show();
        		} else{
        			$("#" + outputNumberTargetId).hide();
        		}
        	});
		});
	});

	$("#project-edit-branch-opinion-file").on("change", function() {
		var file = this.files[0];
		$("#project-edit-branch-opinion-file-name").val(file.name);
		$("#project-edit-branch-opinion-has-file").val(true);
	});

	$("#project-edit-view-opinion-file").on("change", function() {
		var file = this.files[0];
		$("#project-edit-view-opinion-file-name").val(file.name);
		$("#project-edit-view-opinion-has-file").val(true);
	});

	$("#project-edit-bu-opinion-file").on("change", function() {
		var file = this.files[0];
		$("#project-edit-bu-opinion-file-name").val(file.name);
		$("#project-edit-bu-opinion-has-file").val(true);
	});

	if ($("#project_edit_operation_division").val() === "中止"){
		$("#project_edit_stop_reason_area").show();
	}else{
		$("#project_edit_stop_reason_area").hide();
		$("#project_edit_stop_reason").val(null);
	}

	$("#project_edit_operation_division").change(function(){
		if ($(this).val() === "中止"){
			$("#project_edit_stop_reason_area").show();
		}else{
			$("#project_edit_stop_reason_area").hide();
			$("#project_edit_stop_reason").val(null);
		}
	});

	$("#project_edit_project_category").change(function(){
		var categoryId = $(this).val();

		if(categoryId.length > 0 && categoryId > 0){
			if(categoryId == 2){
				$("#tenancyPeriod").val("伴う"); 
			} else if(categoryId == 3){
				$("#tenancyPeriod").val("伴わない"); 
			} else {
				$("#tenancyPeriod").val(""); 
			}
			makePostRequest("/sp/project/find/project_classification/suspension/" + categoryId, {}).then(function (data) {
				var html = "";
					html += '<option value=""></option>';
				$.each(data, function(){
					html += '<option value="'+this.id+'">'+this.name+'</option>';
				});
				$("#project_edit_stop_reason").html(html);
	        });
			makePostRequest("/sp/project/find/project_classification/landing/" + categoryId, {}).then(function (data) {
				var html = "";
					html += '<option value=""></option>';
				$.each(data, function(){
					if(this.name=="出店" && $("#project_edit_project_category").val() == 1 ){
						html += '<option value="'+this.id+'" selected="selected">'+this.name+'</option>';
					}else if(this.name=="出店(POPUP)" && $("#project_edit_project_category").val() == 11 ){
						html += '<option value="'+this.id+'" selected="selected">'+this.name+'</option>';
					}else{
						html += '<option value="'+this.id+'">'+this.name+'</option>';
					}
				});
				$("#project_edit_landing_category").html(html);
				makeProjectTitle();
	        });
			makePostRequest("/sp/project/find/project_classification/landing_possibility/" + categoryId, {}).then(function (data) {
				var html = "";
					html += '<option value=""></option>';
				$.each(data, function(){
					html += '<option value="'+this.id+'">'+this.name+'</option>';
				});
				$("#project_edit_conclusion_possibility_percent").html(html);
			});
			makePostRequest("/sp/project/find/project_progress/company/" + categoryId, {}).then(function (data) {
				var html = "";
				$.each(data, function(){
					html += '<option value="'+this.id+'">'+this.name+'</option>';
				});
				$("#project_edit_office_project_progress").html(html);
	        });
			makePostRequest("/sp/project/find/project_progress/negotiation/" + categoryId, {}).then(function (data) {
				var html = "";
				$.each(data, function(){
					html += '<option value="'+this.id+'">'+this.name+'</option>';
				});
				$("#project_edit_negotiation_project_progress").html(html);
	        });
			makePostRequest("/sp/project/store/require/" + categoryId, {}).then(function (data) {
				requiredShop = data;
	        });
			makeProjectTitle();
		}
	});

	$("#project_edit_implementation_datetime").change(function(){
		if($(this).val() != null && $(this).val() != ""){
			makePostRequest("/sp/project/period/" + $(this).val(), {}).then(function (data) {
				var termData = JSON.parse(data);
				$("#project_edit_implementation_period_label").html(termData.term);
			});
		}
	});
	
	$("#project_edit_progress_contract_start_date").on("change", function(){
		let endDate = $("#project_edit_progress_contract_end_date").val();
		let numOfYear = $("#project_edit_progress_contract_number_of_year").val();
		if(endDate !== undefined && endDate){
			calculateProgressContractNumberOfYear();
		} else if(numOfYear !== undefined && numOfYear){
			calculateProgressContractEndDate();
		}
	});

	$("#project_edit_progress_contract_end_date").on("change", function(){
		calculateProgressContractNumberOfYear();

//		if($(this).val() !== ''){
//			makePostRequest("/sp/project/period/" + $(this).val(), {}).then(function (data) {
//				var termData = JSON.parse(data);
//				$("#project_edit_progress_contract_term").html(termData.term);
//			});
//		}
	});
	
	$("#project_edit_progress_contract_number_of_year").on("keyup", function(){
		calculateProgressContractEndDate();
	});
	
	$("#project_edit_rent_contract_startdate_progress").on("change", function () {
		let endDate = $("#project_edit_rent_contract_enddate_progress").val();
		let numOfYear = $("#project_edit_rent_contract_number_of_year_progress").val();
		if(endDate !== undefined && endDate){
			calculateProgressContractRentNumberOfYear();
		} else if(numOfYear !== undefined && numOfYear){
			calculateProgressContractRentEndDate();
		}
	});
	
	$("#project_edit_rent_contract_enddate_progress").on("change", function () {
		calculateProgressContractRentNumberOfYear();
	});
	
	$("#project_edit_rent_contract_number_of_year_progress").on("keyup", function() {
		calculateProgressContractRentEndDate();
	});

	// Start Caculate for 販売代行 契約年数
	$("#project-edit-i-sales-agency-contract-startdate-progress").on("change", function() {
		let endDate = $("#project-edit-i-sales-agency-contract-enddate-progress").val();
		let numOfYear = $("#project_edit_i_sales_agency_contract_number_of_year_progress").val();
		if(endDate !== undefined && endDate){
			calculateProgressPlanNumberOfYear();
		} else if(numOfYear !== undefined && numOfYear){
			calculateProgressPlanEndDate();
		}
	});
	
	$("#project-edit-i-sales-agency-contract-enddate-progress").on("change", function() {
		calculateProgressPlanNumberOfYear();
	});
	
	$("#project_edit_i_sales_agency_contract_number_of_year_progress").on("keyup", function () {
		calculateProgressPlanEndDate();
	});
	// End Caculate for 販売代行 契約年数
});

function calculateProgressContractEndDate(){
	let startDate = $("#project_edit_progress_contract_start_date").val();
	let numberOfYear = $("#project_edit_progress_contract_number_of_year").val();
	let endDate = calcEndDateProgressContract(startDate, numberOfYear);
	$("#project_edit_progress_contract_end_date").val(endDate);
	$("#project_edit_progress_contract_number_of_year_hidden").val(numberOfYear);
}

function calculateProgressPlanEndDate(){
	let startDate = $("#project-edit-i-sales-agency-contract-startdate-progress").val();
	let numberOfYear = $("#project_edit_i_sales_agency_contract_number_of_year_progress").val();
	let endDate = calcEndDateProgressContract(startDate, numberOfYear);
	$("#project-edit-i-sales-agency-contract-enddate-progress").val(endDate);
	$("#project_edit_i_sales_agency_contract_number_of_year_progress_hidden").val(numberOfYear);
}

function calculateProgressContractRentEndDate(){
	let startDate = $("#project_edit_rent_contract_startdate_progress").val();
	let numberOfYear = $("#project_edit_rent_contract_number_of_year_progress").val();
	let endDate = calcEndDateProgressContract(startDate, numberOfYear);
	$("#project_edit_rent_contract_enddate_progress").val(endDate);
	$("#project_edit_rent_contract_number_of_year_progress_hidden").val(numberOfYear);
}

function calculateProgressContractNumberOfYear(){
	let numOfYear = calcYear($("#project_edit_progress_contract_start_date").val(),$("#project_edit_progress_contract_end_date").val());
	if(numOfYear === +numOfYear && numOfYear !== (numOfYear|0)){
		$("#project_edit_progress_contract_number_of_year").val(roundNumber(numOfYear, 2));
		$("#project_edit_progress_contract_number_of_year_hidden").val(numOfYear);
	} else{
		$("#project_edit_progress_contract_number_of_year").val('');
		$("#project_edit_progress_contract_number_of_year_hidden").val('');
	}
}

function calculateProgressPlanNumberOfYear(){
	let numOfYear = calcYear($("#project-edit-i-sales-agency-contract-startdate-progress").val(),$("#project-edit-i-sales-agency-contract-enddate-progress").val());
	if(numOfYear === +numOfYear && numOfYear !== (numOfYear|0)){
		$("#project_edit_i_sales_agency_contract_number_of_year_progress").val(roundNumber(numOfYear, 2));
		$("#project_edit_i_sales_agency_contract_number_of_year_progress_hidden").val(numOfYear);
	} else{
		$("#project_edit_i_sales_agency_contract_number_of_year_progress").val('');
		$("#project_edit_i_sales_agency_contract_number_of_year_progress_hidden").val('');
	}
}

function calculateProgressContractRentNumberOfYear(){
	let numOfYear = calcYear($("#project_edit_rent_contract_startdate_progress").val(),$("#project_edit_rent_contract_enddate_progress").val());
	if(numOfYear === +numOfYear && numOfYear !== (numOfYear|0)){
		$("#project_edit_rent_contract_number_of_year_progress").val(roundNumber(numOfYear, 2));
		$("#project_edit_rent_contract_number_of_year_progress_hidden").val(numOfYear);
	} else{
		$("#project_edit_rent_contract_number_of_year_progress").val('');
		$("#project_edit_rent_contract_number_of_year_progress_hidden").val('');
	}
}

function calcYear(startDate, endDate) {  
    var result = 0;
    if(endDate === "9999-12-31"){
        result = "";
    } else {
        var diff = new Date(endDate) - new Date(startDate);
        if (isNaN(diff)) {
            result = "";
        } else if (diff > 0) {
            let diffDay = Math.ceil(diff / (1000 * 3600 * 24));
            result = diffDay / 365;
        }
    }
    return result;
}

function calcEndDateProgressContract(startDate, numOfYear) {
    if (!startDate) {
      return "";
    }
    if (!numOfYear || !$.isNumeric(numOfYear) || numOfYear < 0) {
      return "";
    }
    
    let diffDate = numOfYear * 365 + getLeapYear(startDate, numOfYear);
    let endDate = new Date(startDate);
    endDate.setDate(endDate.getDate() + diffDate - 1);
    return endDate.toISOString().slice(0, 10);
}

function getLeapYear(startDate, numOfYear){
    let year = new Date(startDate).getFullYear();
    let parseIntYear = parseInt(numOfYear);
    let cntLeapYear = 0;
    for (let index = 1; index <= parseIntYear; index++) {
        if (leapYear(year + index)) {
            cntLeapYear = cntLeapYear + 1;
        }
    }
    return cntLeapYear;
};

function leapYear(year) {
    return ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
};

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
								if($(this).prop('type') === 'checkbox'){
									if($(this).prop('checked') === true){
										data.append(obj + '[' + index + '].'
												+ $(this).attr('propertyName'), $(
												this).val());
									}
								}else{
									data.append(obj + '[' + index + '].'
											+ $(this).attr('propertyName'), $(
											this).val());
								}
							});
				});
	});
}

function getDataForSectionProgressNegotiation() {
	projectSectionProgressNegotiation.section = $("#project_edit_progress_negotiation_section").val();
	projectSectionProgressNegotiation.frontage = $("#project_edit_progress_negotiation_frontage").val();
	projectSectionProgressNegotiation.floor = $("#project_edit_progress_negotiation_floor").val();
	projectSectionProgressNegotiation.contractTsubo = $("#edit_project_section_progress_negotiation_contracttsubo").val();
	projectSectionProgressNegotiation.businessHours = $("#project_edit_progress_negotiation_business_hours").val();
	projectSectionProgressNegotiation.memo = $("#project_edit_progress_negotiation_memo").val();
	projectSectionProgressNegotiation.buildingExpectedValue = $("#project_edit_progress_negotiation_building_expected_value").val();
}

function checkValidSuggest() {
	let result = true;
	let value = suggestShopName.getValue();
	if (requiredShop && value.length <= 0) {
		$('#project_edit_shop_name')
				.after(
						'<label id="project_edit_shop_name-error" class="error" for="project_edit_shop_name">必須項目です。</label>');
		result = false;
	}
	return result;
}

function createValidMessage() {
	var message = [];
	$(".tab-pane").each(
			function() {
				if ($(this).find('label.error').length > 0) {
					message.push($(
							'ul > li > a[href="#' + $(this).attr('id') + '"]')
							.html()
							+ 'タブの項目に誤りがあります');
				}
			});

	if (message.length > 0) {
		alert(message.join('\n'));
	}
}

function getDataSuggestBlock(data) {
	let value = suggestBlock.getValue();
	if (value.length > 0) {
		data.append("iBlockId", value[0]);
	}
}

function getDataSuggestArea(data) {
	let value = suggestArea.getValue();
	if (value.length > 0) {
		data.append("iAreaId", value[0]);
	}
}

function getDataSuggestShop(data) {
	let value = suggestShopName.getValue();
	if (value.length > 0 && value[0] > 0) {
		data.append("iShopId", value[0]);
	} else {
		if(project.iShopId != null){
        	data.append("iShopId", project.iShopId);
        }
	}
}

function getDataSuggestBuilding(data) {
	let value = suggestBuilding.getValue();
	if (value.length > 0) {
		data.append("buildingId", value[0]);
	}
}

function getDataSuggestBrand(data) {
	let value = suggestBrand.getValue();
	if (value.length > 0) {
		data.append("brandId", value[0]);
	}
}

function getDataSuggestCorporationGroup(data) {
	let value = suggestCorporationGroup.getValue();
	if (value.length > 0 ) {
		data.append("corporationGpId", value[0]);
	}
}

function getDataSuggestCorporation(data) {
	let value = suggestCorporation.getValue();
	if (value.length > 0) {
		data.append("corporationId", value[0]);
	}
}

function getDataSuggestProgressContractTargetName(data) {
	if (suggestProgressContractTargetName.getSelection().length > 0) {
		data.append("projectContractProgressDto[1].contractTargetName", suggestProgressContractTargetName.getSelection()[0].name);
	}
}

function getDataSuggestPrefectures(data) {
	let value = suggestPrefectures.getValue();
	if (value.length > 0) {
		data.append("iPrefecturesId", value[0]);
	}
}

function getDataOtherProjectTeam(data) {
	if ($("#project_edit_other_project_team").val() != null) {
		data.append('otherProjectTeamDto[0].deptCd', $("#project_edit_other_project_team").val());
	}
}

function getDataOtherAccount(data) {
	let indexCount = 0;
	// Store Accounts
	$.each(suggestAccountStore.getSelection(), function(index, obj) {
		data.append('otherProjectAccountDtos[' + indexCount + '].iAccountId',
				obj.id);
		data.append('otherProjectAccountDtos[' + indexCount + '].category',
				'STORE');
		indexCount++;
	});
	// Sales Accounts
	$.each(suggestAccountSales.getSelection(), function(index, obj) {
		data.append('otherProjectAccountDtos[' + indexCount + '].iAccountId',
				obj.id);
		data.append('otherProjectAccountDtos[' + indexCount + '].category',
				'SALES');
		indexCount++;
	});
	// Section Accounts
	$.each(suggestAccountSection.getSelection(), function(index, obj) {
		data.append('otherProjectAccountDtos[' + indexCount + '].iAccountId',
				obj.id);
		data.append('otherProjectAccountDtos[' + indexCount + '].category',
				'SECTION');
		indexCount++;
	});
	// Fc Accounts
	$.each(suggestAccountFc.getSelection(), function(index, obj) {
		data.append('otherProjectAccountDtos[' + indexCount + '].iAccountId',
				obj.id);
		data.append('otherProjectAccountDtos[' + indexCount + '].category',
				'FC');
		indexCount++;
	});
	// Other Accounts
	$.each(suggestAccountOther.getSelection(), function(index, obj) {
		data.append('otherProjectAccountDtos[' + indexCount + '].iAccountId',
				obj.id);
		data.append('otherProjectAccountDtos[' + indexCount + '].category',
				'OTHER');
		indexCount++;
	});
	// Leader Accounts
	$.each(suggestAccountLeader.getSelection(), function(index, obj) {
		data.append('otherProjectAccountDtos[' + indexCount + '].iAccountId',
				obj.id);
		data.append('otherProjectAccountDtos[' + indexCount + '].category',
				'humanResourceLeader');
		indexCount++;
	});
}
function checkProperties (obj) {
    if(jQuery.isEmptyObject(obj)){
        return true;
    }
    for (var key in obj) {
        if (obj[key] !== null && obj[key] != "" && obj[key] != undefined)
            return false;
    }
    return true;
};
function htmlAddFile(id, displayName, source, division, path, urlPath, isRef){
	indexFile++;
	var html = '<div class="media-list-item has-trash new-item" objectName="projectMediaDocumentDto"  id="project-file-add'+indexFile+'">';
	html += '<div class="row">';
	html += '<input class="object-property" type="hidden" propertyName="id" value="'+id+'">';
	html += '<input class="object-property" type="hidden" propertyName="originFileName" value="'+displayName+'">';
	html += '<input class="object-property" type="hidden" propertyName="path" value="'+path+'">';
	html += '<input class="object-property" type="hidden" propertyName="urlPath" value="'+urlPath+'">';
	html += '<input class="object-property" type="hidden" propertyName="addFileReference" value="'+isRef+'">';
	html += '<div class="col-md-2">';
	html += '<div class="img">';
	html += '<a target="_blank">';
	if(division === "image"){
		html += '<img class="media-object img-rounded" src="'+source+'" alt="">';
	}else{
		html += '<img class="media-object img-rounded" src="'+division+'" alt="">';
	}
	html += '</a>';
	html += '</div>';
	html += '</div>';
	html += '<div class="col-md-10">';
	html += '<div class="form-group">';
	html += '<div class="row">';
	html += '<div class="col-md-2">';
	html += '<label>ファイル名</label>';
	html += '</div>';
	html += '<div class="col-md-9">';
	html += '<input class="form-control" propertyName="displayName" value="'+displayName+'">';
	html += '</div>';
	html += '</div>';
	html += '</div>';
	html += '<div class="form-group">';
	html += '<div class="row">';
	html += '<div class="col-md-2">';
	html += '<label>コメント</label>';
	html += '</div>';
	html += '<div class="col-md-9">';
	html += '<input class="form-control" propertyName="comment" value="">';
	html += '</div>';
	html += '</div>';
	html += '</div>';
	html += '<div class="form-group">';
	html += '<div class="row">';
	html += '<div class="col-md-2">';
	html += '<label>ﾌｧｲﾙ区分</label>';
	html += '</div>';
	html += '<div class="col-md-9">';
	html += '<select class="form-control" propertyName="division">';
	$.each(fileDivision, function(obj){
		html += '<option value="'+this.fileDivisionCode+'">'+this.displayName+'</option>';
	});
	html += '</select>';
	html += '</div>';
	html += '<div class="col-md-1">';
	html += '<div class="trash j-delete-item">';
	html += '<i class="fa fa-trash"></i>';
	html += '</div>';
	html += '</div>';
	html += '</div>';
	html += '</div>';
	html += '<div class="form-group">';
	html += '<div class="row">';
	html += '<div class="col-md-2">';
	html += '<label>出力有無</label>';
	html += '</div>';
	html += '<div class="col-md-9">';
	html += '<select class="form-control" propertyName="outputFlag" id="project-file-add-output-flag-'+indexFile+'">';
	html += '<option value="T">出力有り</option>';
	html += '<option value="F">出力無し</option>';
	html += '</select>';
	html += '</div>';
	html += '</div>';
	html += '</div>';
	html += '<div class="form-group" id="project-file-add-output-number-'+indexFile+'">';
	html += '<div class="row">';
	html += '<div class="col-md-2">';
	html += '<label>出力順序</label>';
	html += '</div>';
	html += '<div class="col-md-9">';
	html += '<input class="form-control" value="10" propertyName="outputNumber">';
	html += '</div>';
	html += '</div>';
	html += '</div>';
	html += '</div>';
	html += '</div>';
	html += '</div>';

	return html;
}
function displayIDContractTsubo (){
    let pcc = null;
    let pnt = null;
    let changeValue = "";
    
    if(refShop){
    	pcc = refShopData.contractTsubo;
    }
    
    if($('#edit_project_section_progress_negotiation_contracttsubo').length > 0){
    	pnt = $('#edit_project_section_progress_negotiation_contracttsubo').val();
    }
    
    var regNumber = /^(\d+\.?\d*|\.\d+)$/;
    if (regNumber.test(pcc) && regNumber.test(pnt)) {
        let increDcre = pnt - pcc;
        if (increDcre > 0) {
        	changeValue = "増床";
        } else if (increDcre < 0) {
        	changeValue = "縮小";
        } else {
        	changeValue = "同坪";
        }
    }
    
    $("#project_section_progress_negotiation_contracttsubo_id").text(changeValue);
}
function validateImplementationScheduleDate(year, month, day) {
	if(year == '' && month == ''  && day == '') {
        $("#project_edit_implementation_schedule_datetime_year").rules("remove", "required");
        $("#project_edit_implementation_schedule_datetime_month").rules("remove", "required");
        $("#project_edit_implementation_schedule_datetime_day").rules("remove", "required");
    } else if(year == '' || month == '' || day == '') {
    	if(year !== '' || month !== '' || day !== '') {
    		if(year == ''){
    			$("#project_edit_implementation_schedule_datetime_year").rules("add", {
                    required: true,
                    messages: {
                        required: "あいまい設定を入力してください"
                    }
                });
            }
            if(month == ''){
                $("#project_edit_implementation_schedule_datetime_month").rules("add", {
                    required: true,
                    messages: {
                        required: "あいまい設定を入力してください"
                    }
                });
            }
            if(day == ''){
                $("#project_edit_implementation_schedule_datetime_day").rules("add", {
                    required: true,
                    messages: {
                        required: "あいまい設定を入力してください"
                    }
                });
            }
        }
    }
}
function operationFormChange() {
	if(refShop){
		if ($("#j-form-operation-form-label").text() == 'FC') {
			$("#project_edit_other_account_fc").show();
		} else {
			$("#project_edit_other_account_fc").hide();
		}
	}else{
		if ($("#j-form-operation-form").val() == 'FC') {
			$("#project_edit_other_account_fc").show();
		} else {
			$("#project_edit_other_account_fc").hide();
		}
	}
}
function makeProjectTitle(){
	var selprojectCategory = $("#project_edit_project_category option:selected").val();
	let _building = createProjectTitle.building;
	let _brand = createProjectTitle.brand;
	let _category = $("#project_edit_landing_category option:selected").text();

	if (selprojectCategory == 1 || selprojectCategory == 2 || selprojectCategory == 3 || selprojectCategory == 11) {
		if (_building !== "" && _brand === "" &&  _category === "") {
			$("#project_edit_title").val(_building);
		} else if (_building !== "" && _brand !== "" && _category === ""){
			$("#project_edit_title").val(_building + "_" + _brand);
		} else if (_building !== "" && _brand === "" && _category !== ""){
			$("#project_edit_title").val(_building + "_" + _category);
		} else if (_building !== "" && _brand !== "" && _category !== ""){
			$("#project_edit_title").val(_building + "_" + _brand + "_" + _category);
		} else if (_building === "" && _brand !== "" && _category === ""){
			$("#project_edit_title").val(_brand);
		} else if (_building === "" && _brand !== "" && _category !== ""){
			$("#project_edit_title").val(_brand + "_" + _category);
		} else if (_building === "" && _brand === "" && _category !== ""){
			$("#project_edit_title").val(_category);
		}
	} else if (_building !== "" && _brand !== "") {
		$("#project_edit_title").val(_building + '_' + _brand);
	} else if (_building !== "" && _brand === ""){
		$("#project_edit_title").val(_building);
	} else if (_building === "" && _brand !== ""){
		$("#project_edit_title").val(_brand);
	}
	if (_building !== "" && _brand !== "" && $("#project_edit_shop_name_temporary").val() === "") {
		$("#project_edit_shop_name_temporary").val(_building + '_' + _brand);
	}
}

function roundNumber(value, decimalDigit) {
    let num = Math.pow(10, decimalDigit);
    return Math.round(value * num) / num;
}

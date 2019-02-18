var dateFormat = "yy-mm-dd";
var dateTimeFormat = "yy-mm-dd 00:00";
var indexFile = 0;
var indexImage = 0;
var indexTask = 0;
var isBuilding = false;
var isShop = false;
var projectData = {};
var buildingData = {};
var shopData = {};
var data = {};
var requiredShop = false;
var opinionBranchPerson = {
		category: "BRANCH",
        hasFile: false
    };
var opinionBranch = {
		category: "VIEW",
        hasFile: false
    };
var opinionBU = {
		category: "BU",
        hasFile: false
    };
var projectOpinionDto = [];
var projectSectionProgressNegotiation = {};
var projectSectionProgressCurrent = {};
var projectContractProgress = {
        category: "PROGRESS",
    };
var projectContractProgressCurrent = {
		category: "CURRENT",
	};
var projectTaskAccount = [];
var otherProjectAccountDto = [];
var accountStore = [];
var accountSales = [];
var accountSection = [];
var accountOther = [];
var accountFc = [];
var accountLeader = [];
var listBrandIncome = [];
var createProjectTitle = {"building":"", "brand":"", "landing":""};
var projectCategoryDevelopShop =[1, 2, 3, 4, 5, 6];
var projectCategoryBranch =[7, 8, 9, 10];

var permitType = [{type:"image/jpeg",division:"image",extension:"jpg"}, {type:"image/png",division:"image",extension:"png"}, {type:"image/gif",division:"image",extension:"gif"},
    {type:"application/pdf",division:"/wsd/img/pdf.jpg",extension:"pdf"}, {type:"application/vnd.openxmlformats-officedocument.wordprocessingml.document",division:"/wsd/img/docx.jpg",extension:"docx"},
    {type:"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",division:"/wsd/img/xlsx.jpg",extension:"xlsx"},
    {type:"application/vnd.openxmlformats-officedocument.presentationml.presentation",division:"/wsd/img/pptx.jpg",extension:"pptx"},
    {type:"video/mp4",division:"video",extension:"mp4"}, {type:"video/quicktime",division:"video",extension:"mov"}];

let suggestBuilding, suggestBlock, suggestArea, suggestShop, suggestBrand, suggestBusinessCard, suggestOtherAccountStore, suggestOtherAccountSales, suggestOtherAccountSection, suggestOtherAccountFc, suggestOtherAccountOther, suggestOtherAccountLeader, suggestOtherProjectTeam, suggestProgressContractTargetName;
var participatingStoreCorporation = {};
var salesAgencyContract = {};
var salesAgencyTarget = {};
var projectPlanProgress = {};
var projectPlanCurrent = {};
var projectPlanDto = [];
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
var makePostRequest = function (url, data) {
	return $.ajax({
        url: createUrl(url),
        type: 'POST',
        contentType : "application/json",
        data: data,
        timeout: 30000
    });
}
var setDataFromBuilding = function () {
	if(isBuilding){
		$("#j-form-coporation-group").hide();
		$("#j-form-coporation").hide();
		$("#j-form-area").hide();
		$("#j-form-block").hide();
		$("#j-form-prefectures").hide();
		$("#j-form-sales-channel").hide();

		$("#j-form-coporation-group-label").html(buildingData.corporationGroupName);
		$("#j-form-coporation-group-label").show();
		$("#j-form-coporation-label").html(buildingData.corporationName);
		$("#j-form-coporation-label").show();
		$("#j-form-area-label").html(buildingData.areaName);
		$("#j-form-area-label").show();
		$("#j-form-block-label").html(buildingData.blockName);
		$("#j-form-block-label").show();
		$("#j-form-prefectures-label").html(buildingData.prefecturesName);
		$("#j-form-prefectures-label").show();
		$("#j-form-sales-channel-label").html(buildingData.salesChannelName);
		$("#j-form-sales-channel-label").show();
		$("#project-create-building-adopt-difficulty").html(buildingData.building.adoptDifficulty);
		$("#project-create-building-adopt-difficulty").show();

		$("#j-form-sales-channel").change();
		if(buildingData.storeDeveloperList !== undefined && buildingData.storeDeveloperList !== null) {
			suggestOtherAccountStore.addToSelection(Array
					.from(buildingData.storeDeveloperList
							.map(function(obj) {
								return {
									id : obj.id,
									name : obj.name
								}
							})));
		}

		if(buildingData.branchsSalesList !== undefined && buildingData.branchsSalesList !== null) {
			suggestOtherAccountSales.addToSelection(Array
					.from(buildingData.branchsSalesList
							.map(function(obj) {
								return {
									id : obj.id,
									name : obj.name
								}
							})));
		}

		if(buildingData.humanResourceLeaderList !== undefined && buildingData.humanResourceLeaderList !== null) {
			suggestOtherAccountLeader.addToSelection(Array
					.from(buildingData.humanResourceLeaderList
							.map(function(obj) {
								return {
									id : obj.id,
									name : obj.name
								}
							})));
		}
		createProjectTitle.building = buildingData.buildingName;
	} else {
		$("#j-form-coporation-group-label").hide();
		$("#j-form-coporation-label").hide();
		$("#j-form-area-label").hide();
		$("#j-form-block-label").hide();
		$("#j-form-prefectures-label").hide();
		$("#j-form-sales-channel-label").hide();
		$("#project-create-building-adopt-difficulty").hide();

		$("#j-form-coporation-group").show();
		$("#j-form-coporation").show();
		$("#j-form-area").show();
		$("#j-form-block").show();
		$("#j-form-prefectures").show();
		$("#j-form-sales-channel").show();
		createProjectTitle.building = "";
	}
	makeProjectTitle();
}
var setDataFromShop = function () {
	if(isShop){
		if(shopData.buildingId !== null){
			$("#j-form-building").hide();

			$("#j-form-building-label").html(shopData.buildingName);
			$("#j-form-building-label").show();

			isBuilding = true;
			makePostRequest("/sp/project/refBuildingData/" + shopData.buildingId, {}).then(function (data) {
	    		buildingData = data;
	    		setDataFromBuilding();
	        });
		}

		if(shopData.rentContract !== undefined && shopData.rentContract !== null) {
            if(shopData.rentContract.iContractType !== undefined && shopData.rentContract.iContractType !== null){
				if(shopData.rentContract.iContractType.contractTypeName != undefined){
					$("#project-contract-progress-form").val(shopData.rentContract.iContractType.contractTypeName);
					projectContractProgressCurrent.form = shopData.rentContract.iContractType.contractTypeName;
				}
            }
            
            projectContractProgressCurrent.contractTargetName = shopData.rentContract.contractTargetName;
            suggestProgressContractTargetName.setValue([shopData.rentContract.contractTargetName]);
            
            let startDateString = shopData.rentContract.startDateString.replace(/\//g,"-");
            projectContractProgressCurrent.contractStartDate = startDateString;
            
            let endDateString = shopData.rentContract.endDateString.replace(/\//g,"-");
            projectContractProgressCurrent.contractEndDate = endDateString;
            
            let numberOfYear = calcYear(startDateString, endDateString);
            
            projectContractProgressCurrent.contractNumberOfYear = "";
            if(numberOfYear !== ""){
            	projectContractProgressCurrent.contractNumberOfYear = roundNumber(numberOfYear, 2);
            }
            
            projectContractProgressCurrent.autoUpdate = shopData.rentContract.autoUpdatingFlag == "y";
            
            let dateNumOfYear = "";
            if(startDateString !== "" || endDateString !== ""){
            	dateNumOfYear += startDateString + "～";
            	
            	if(endDateString !== "9999-12-31"){
            		dateNumOfYear += endDateString;
            	}
            	
            	if(projectContractProgressCurrent.contractNumberOfYear !== ""){
            		dateNumOfYear += "（" + projectContractProgressCurrent.contractNumberOfYear + "年）";
            	}
			}
            
            $("#project-contract-current-date-number-of-year-label").text(dateNumOfYear);
            $("#project-contract-current-date-number-of-year-area").show();
			
			if(shopData.isDisplayEconomy){
				$("#project-contract-current-display-economy-label").html('<a href="/wsd/pc/store/detail/' + projectData.shop.id + '">経済条件</a>');
				$("#project-contract-current-display-economy-area").show();
			}
        }
		
		projectContractProgressCurrent.rentStartDate = shopData.rentStartDate;
		projectContractProgressCurrent.rentEndDate = shopData.rentEndDate;
		projectContractProgressCurrent.rentYear = shopData.rentYear;
		projectContractProgressCurrent.memo = $("#project-contract-progress-current-memo").val();

//		$("#project-section-progress-current-section-label").html(shopData.section);
//		$("#project-section-progress-current-section-area").show();
//		$("#project-section-progress-current-frontage-label").html(shopData.frontage);
//		$("#project-section-progress-current-frontage-area").show();
//		$("#project-section-progress-current-floor-label").html(shopData.floorNum);
//		$("#project-section-progress-current-floor-area").show();
//		$("#project-section-progress-current-contract-tsubo-label").html(shopData.contractTsubo);
//		$("#project-section-progress-current-contract-tsubo-area").show();
//		$("#project-section-progress-current-business-hours-label").html(shopData.businessHours);
//		$("#project-section-progress-current-business-hours-area").show();

		$("#project-create-i-sales-agency-target-curent-area").show();
    	$("#project-create-participating-store-corporation-curent-area").show();
    	$("#project-create-current-plan-group-area").show();
    	
    	$("#j-form-operation-form-label").text(shopData.operationForm);
    	$("#j-form-operation-form-label").show();
		$("#j-form-contract-progress-operation-form").val(shopData.operationForm)
    	$("#j-form-operation-form").hide();

		displayIDContractTsubo();
		displayFCAccount();
	} else {
		$("#project-contract-current-date-number-of-year-area").hide();
		
		$("#project-create-i-sales-agency-target-curent-area").hide();
    	$("#project-create-participating-store-corporation-curent-area").hide();
    	$("#project-create-current-plan-group-area").hide();

//		$("#project-section-progress-current-section-area").hide();
//		$("#project-section-progress-current-frontage-area").hide();
//		$("#project-section-progress-current-floor-area").hide();
//		$("#project-section-progress-current-contract-tsubo-area").hide();
//		$("#project-section-progress-current-business-hours-area").hide();
//		$("#project-section-progress-current-memo-area").hide();

		$("#j-form-building-label").hide();

		$("#project-contract-current-form-area").hide();
		$("#project-contract-current-term-area").hide();
		$("#project-contract-current-auto-update-area").hide();
		$("#project-contract-current-display-economy-area").hide();
		$("#j-form-building").show();
		
		$("#j-form-operation-form-label").hide();
		$("#j-form-operation-form").show();

		isBuilding = false;
		setDataFromBuilding();

		displayIDContractTsubo();
		displayFCAccount();
	}
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
			if ([37, 38, 39, 40].indexOf(event.keyCode) >= 0) {
				event.preventDefault()
				return;
			}

			let value = this.getRawValue();			
			if (!value) {
				this.setData([]);
				$('#' + target.container[0].id + ' input[type=text]').val('');
				event.preventDefault()
				return; }

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
var setValueSuggestBox = function (e, m, selectedData) {

    let containerId = m.container[0].id;
    if (containerId == 'j-form-building') {
    	if (selectedData.length > 0) {
    		isBuilding = true;
        	makePostRequest("/sp/project/refBuildingData/" + selectedData[0].id, {}).then(function (data) {
        		buildingData = data;
        		setDataFromBuilding();
            });
    	} else {
    		isBuilding = false;
    		setDataFromBuilding();
    	}
    } else if (containerId == 'j-form-shop') {
    	if (selectedData.length > 0) {
    		isShop = true;
    		projectData.shop = selectedData[0];
            let id = selectedData[0].id;

            if(id){
            	makePostRequest("/sp/project/refShopData/" + selectedData[0].id, {}).then(function (data) {
                    if (data !== "") {
                    	shopData = data;
                		setDataFromShop();
                        if(data.listBrandIncome.length > 0){
                        	listBrandIncome = data.listBrandIncome;
                            if(data.listBrandIncome.length == 1) {
                            	$("#j-form-brand-label-span").html(data.listBrandIncome[0].name);
                            	$("#j-form-brand-label-span").show();
                            	$("#j-form-brand-label-option").hide();
                            	createProjectTitle.brand = data.listBrandIncome[0].name;
                            	showFormBusinessCard(data.listBrandIncome[0].id);
                            } else if(data.listBrandIncome.length > 1){
                            	$("#j-form-brand-label-option").empty();
                            	data.listBrandIncome.forEach(function (element) {
                            		$("#j-form-brand-label-option").append('<option value="'+ element.id +'"> '+element.name +'</option>');
								});
                            	$("#j-form-brand-label-option").show();
                                $("#j-form-brand-label-span").hide();

                                showFormBusinessCard(data.listBrandIncome[0].id);
                                createProjectTitle.brand = data.listBrandIncome[0].name;
                                $("#j-form-brand-label-option").on("change", function () {
                                	let idBrand = $("#j-form-brand-label-option").val();
                                	showFormBusinessCard(idBrand);
                                	createProjectTitle.brand = $("#j-form-brand-label-option option:selected").text();
                                	makeProjectTitle();
								})
                            }
                            $("#j-form-brand").hide();
                        } else {
                        	listBrandIncome = [];
                        	if(suggestBrand.getValue().length <= 0 ){
                        		$("#j-form-brand-label-option").hide();
                                $("#j-form-brand-label-span").hide();
                                $("#j-form-business-card").hide();
                        	}
                        	createProjectTitle.brand = "";
                        }
                        makeProjectTitle();

						if(data.salesAgencyTarget != undefined && data.salesAgencyTarget != null){
                            salesAgencyTarget = data.salesAgencyTarget;
                            $("#project-create-i-sales-agency-target-curent-label").html(salesAgencyTarget.name);
                            displayCurrentPlan = true;
                        } else {
                        	salesAgencyTarget = {};
                        }
                        if(data.salesAgencyContract != undefined && data.salesAgencyContract != null){
                            salesAgencyContract.startDate = data.salesAgencyContract.startDateString;
                            salesAgencyContract.endDate = data.salesAgencyContract.endDateString;
                            salesAgencyContract.salesAgencyTargetId = data.salesAgencyContract.salesAgencyTargetId;
//                            $("#project-create-i-sales-agency-contract-startdate-curent-label").val(salesAgencyContract.startDate);
//                            $("#project-create-i-sales-agency-contract-enddate-curent-label").val(salesAgencyContract.endDate);
                            
                            let numberOfYear = calcYear(salesAgencyContract.startDate, salesAgencyContract.endDate);
                            
                            salesAgencyContract.numberOfYear = "";
                            if(numberOfYear !== ""){
                            	salesAgencyContract.numberOfYear = roundNumber(numberOfYear, 2);
							}
							
                            let dateNumOfYear = "";
                            if(salesAgencyContract.startDate !== "" || salesAgencyContract.endDate !== ""){
                            	dateNumOfYear += salesAgencyContract.startDate + "～";
                            	
                            	if(salesAgencyContract.endDate !== "9999-12-31"){
                            		dateNumOfYear += salesAgencyContract.endDate;
                            	}
                            	
                            	if(salesAgencyContract.numberOfYear !== ""){
                            		dateNumOfYear += "（" + salesAgencyContract.numberOfYear + "年）";
                            	}
                			}
                            $("#project-create-i-sales-agency-contract-curent-date-number-of-year-label").html(dateNumOfYear);
                            
                            displayCurrentPlan = true;
                        } else {
                        	salesAgencyContract = {};
						}

//                        if(data.salesAgencyCondition != null && data.salesAgencyCondition.startDate != null && data.salesAgencyCondition.endDate != null){
//                            let endDate = data.salesAgencyCondition.endDateString;
//                            let startDate = data.salesAgencyCondition.startDateString;;
//                            var result = 0;
//                            var diff = new Date(endDate) -new Date(startDate);
//                            if (isNaN(diff)) {
//                                result = "";
//                            } else if (diff > 0) {
//                                var diffDate = new Date(diff);
//                                result = Math.abs(diffDate.getUTCFullYear() - 1970);
//                            }
//                            $("#number-of-year-agency-contract-current").val(result);
//                            displayCurrentPlan = true;
//                            if(salesAgencyContract.startDate != '' && salesAgencyContract.endDate != undefined && 
//                            		salesAgencyContract.endDate != '' && salesAgencyContract.endDate != undefined ){
//                            	$("#project-create-i-sales-agency-contract-curent-date-number-of-year-label").html(salesAgencyContract.startDate + "～" + salesAgencyContract.endDate + "（" + result + "年）");
//                            }
//                        }
                        if(data.participatingStoreCorporation != undefined && data.participatingStoreCorporation != null){
                            participatingStoreCorporation = data.participatingStoreCorporation;
                            $("#project-create-participating-store-corporation-curent-label").html(participatingStoreCorporation.name);
                            displayCurrentPlan = true;
							suggestParticipatingStoreProgress.setValue([participatingStoreCorporation]);
                        } else {
                        	participatingStoreCorporation = {};
                        }
						projectData.brandId = undefined;
                    }else{
						suggestBrand.clear();
						listBrandIncome = [];
					}
					setTimeout(function () {
						if (suggestSalesAgencyProgress.getValue() != undefined || suggestSalesAgencyProgress.getValue() != null) {
							suggestSalesAgencyProgress.clear();
						}
						if (data.salesAgencyTarget != null) {
					suggestSalesAgencyProgress.setValue([data.salesAgencyTarget]);
						}
					}, 1000);
                });
            }
            $("#j-form-shop-name-temporary").hide();
    	} else {
            $("#j-form-brand-label-option").hide();
            $("#j-form-brand-label-span").hide();
            $("#j-form-brand").show();
            $("#j-form-shop-name-temporary").show();
            if(suggestBrand.getValue().length <= 0){
            	$("#j-form-business-card").hide();
            } else {
            	showFormBusinessCard(suggestBrand.getValue()[0]);
            }
            isShop = false;
			if(suggestBuilding.getValue().length > 0){
				makePostRequest("/sp/project/refBuildingData/" + suggestBuilding.getValue()[0], {}).then(function (data) {
					shopData.buildingId = data.building.id;
					isBuilding = true;
		    		buildingData = data;
		    		setDataFromBuilding();
		        });
			} else {
				isBuilding = false;
			}
            setDataFromShop();

//            $("#number-of-year-agency-contract-current").val("");
            participatingStoreCorporation = {};
            salesAgencyContract = {};
            salesAgencyTarget = {};
			suggestParticipatingStoreProgress.clear();
        }
    } else if (containerId == 'j-form-brand') {
    	if(selectedData.length > 0){
            projectData.brandId = selectedData[0].id;
            showFormBusinessCard(projectData.brandId);
            createProjectTitle.brand = selectedData[0].name;
        } else {
			projectData.brandId = undefined;
            $("#j-form-business-card").hide();
            createProjectTitle.brand = "";
        }
    	makeProjectTitle();
    } else if (containerId == 'project-create-other-account-store') {
    	accountStore = [];
    	if (selectedData.length > 0){
    		selectedData.forEach(function (element, index) {
    			let store = {
    					iAccountId: element.id,
    					category: 'STORE'
    			}
				accountStore.push(store);
			});
    	}
    } else if (containerId == 'project-create-other-account-sales') {
    	accountSales = [];
    	if (selectedData.length > 0){
    		selectedData.forEach(function (element, index) {
    			let sales = {
    					iAccountId: element.id,
    					category: 'SALES'
    			}
    			accountSales.push(sales);
			});
    	}
    } else if (containerId == 'project-create-other-account-section') {
    	accountSection = [];
    	if (selectedData.length > 0){
    		selectedData.forEach(function (element, index) {
    			let section = {
    					iAccountId: element.id,
    					category: 'SECTION'
    			}
    			accountSection.push(section);
			});
    	}
    } else if (containerId == 'project-create-other-account-fc') {
    	accountFc = [];
    	if (selectedData.length > 0){
    		selectedData.forEach(function (element, index) {
    			let fc = {
    					iAccountId: element.id,
    					category: 'FC'
    			}
    			accountFc.push(fc);
			});
    	}
    } else if (containerId == 'project-create-other-account-other') {
    	accountOther = [];
    	if (selectedData.length > 0){
    		selectedData.forEach(function (element, index) {
    			let other = {
    					iAccountId: element.id,
    					category: 'OTHER'
    			}
    			accountOther.push(other);
			});
    	}
    } else if (containerId == 'project-create-other-account-leader') {
    	accountLeader = [];
    	if (selectedData.length > 0){
    		selectedData.forEach(function (element, index) {
    			let leader = {
    					iAccountId: element.id,
    					category: 'humanResourceLeader'
    			}
    			accountLeader.push(leader);
			});
    	}
    } else if (containerId == 'project-create-participating-store-corporation-progress') {
        if (selectedData.length > 0){
            projectPlanProgress.participatingStoreCorporationId = selectedData[0].id;
        } else {
            delete projectPlanProgress.participatingStoreCorporationId;
        }
    } else if (containerId == 'project-create-i-sales-agency-target-progress') {
        if (selectedData.length > 0){
            projectPlanProgress.salesAgencyTargetId = selectedData[0].id;
        } else {
            delete projectPlanProgress.salesAgencyTargetId;
        }
    }
}
var showFormBusinessCard = function (id) {
	makePostRequest("/sp/project/refBrandData/" + id, {}).then(function (data) {
        if (data !== "") {
        	$("#j-form-business-card").show();
            $("#j-form-business-card").html(data.listIBusiness.name);
        }
	});
}
var setMagicSuggestOption = function (target, maxSelection) {
	if(maxSelection == 1){
		return $(target).magicSuggest({
			maxSelection : maxSelection,
			allowFreeEntries : false,
			placeholder : "",
			noSuggestionText: "候補がありません",
			maxSelectionRenderer : function() {
				return "";
			}
		});
	} else {
		return $(target).magicSuggest({
			allowFreeEntries : false,
			noSuggestionText: "候補がありません",
			placeholder : "",
		});
	}

}
var setMagicSuggestFreeOption = function (target, maxSelection) {
	if(maxSelection == 1){
		return $(target).magicSuggest({
			maxSelection : maxSelection,
			allowFreeEntries : true,
			noSuggestionText: "候補がありません",
			maxSelectionRenderer : function() {
				return "";
			}
		});
	} else {
		return $(target).magicSuggest({
			allowFreeEntries : true,
			noSuggestionText: "候補がありません",
			placeholder : "",
		});
	}

}
var setDateSuggest = function () {
	if ($('.j-implementation-date').length) {
		datepickerUtils.createDatePicker($('.j-implementation-date').find('input'));
	}
	if ($('.j-form-external-release-start-date-time').length) {
		datepickerUtils.createDatePicker($('.j-form-external-release-start-date-time').find('input'));
	}
	if ($('.j-form-startdate-time').length) {
		datepickerUtils.createDatePicker($('.j-form-startdate-time').find('input'));
	}
	if ($('.j-form-implementation-datetime').length) {
		datepickerUtils.createDatePicker($('.j-form-implementation-datetime').find('input'));
	}
	if ($('.j-form-sales-end-date').length) {
		datepickerUtils.createDatePicker($('.j-form-sales-end-date').find('input'));
	}
	if ($('.project-create-section-suggest-date').length) {
		datepickerUtils.createDatePicker($('.project-create-section-suggest-date').find('input'));
	}
	if ($('.project-create-building-metting-date').length) {
		datepickerUtils.createDatePicker($('.project-create-building-metting-date').find('input'));
	}
	if ($('.project-create-consensus-date').length) {
		datepickerUtils.createDatePicker($('.project-create-consensus-date').find('input'));
	}
	if ($('.project-create-contract-date').length) {
		datepickerUtils.createDatePicker($('.project-create-contract-date').find('input'));
	}
	if ($('.project-create-opening-date').length) {
		createDateTimePicker($('.project-create-opening-date').find('input'));
	}
	if ($('.project-create-article-review-datetime').length) {
		datepickerUtils.createDatePicker($('.project-create-article-review-datetime').find('input'));
	}
	if ($('.project-create-article-management-datetime').length) {
		datepickerUtils.createDatePicker($('.project-create-article-management-datetime').find('input'));
	}
	if ($('.project-create-article-investment-process-datetime').length) {
		datepickerUtils.createDatePicker($('.project-create-article-investment-process-datetime').find('input'));
	}
}
var setDataSuggestBox = function () {
	suggestBlock = setMagicSuggestOption('#j-form-block', 1);
	suggestArea = setMagicSuggestOption('#j-form-area', 1);
	suggestBuilding = setMagicSuggestOption('#j-form-building', 1);
	suggestShop = setMagicSuggestOption('#j-form-shop', 1);
	suggestBrand = setMagicSuggestOption('#j-form-brand', 1);
	suggestCoporationGroup = setMagicSuggestOption('#j-form-coporation-group', 1);
	suggestCoporation = setMagicSuggestOption('#j-form-coporation', 1);
	suggestIPrefectures = setMagicSuggestOption('#j-form-prefectures', 1);
	suggestOtherAccountStore = setMagicSuggestOption('#project-create-other-account-store', '');
	suggestOtherAccountSales = setMagicSuggestOption('#project-create-other-account-sales', '');
	suggestOtherAccountSection = setMagicSuggestOption('#project-create-other-account-section', '');
	suggestOtherAccountOther = setMagicSuggestOption('#project-create-other-account-other', '');
	suggestOtherAccountLeader = setMagicSuggestOption('#project-create-other-account-leader', '');
	suggestOtherAccountFc = setMagicSuggestOption('#project-create-other-account-fc', '');
	suggestSalesAgencyProgress = setMagicSuggestOption('#project-create-i-sales-agency-target-progress', 1);
	suggestParticipatingStoreProgress = setMagicSuggestOption('#project-create-participating-store-corporation-progress', 1);
	suggestProgressContractTargetName = setMagicSuggestOption('#project-contract-progress-contract-target-name', 1);

	createSuggest(suggestBuilding, "/sp/project/find/building");
	createSuggest(suggestShop, "/sp/project/find/shop");
	createSuggest(suggestBrand, "/sp/project/find/ibrandincomeunit");
	createSuggest(suggestBlock, "/sp/project/find/iblock");
	createSuggest(suggestArea, "/sp/project/find/iarea");
	createSuggest(suggestCoporationGroup, "/sp/project/find/i_corporation_group");
	createSuggest(suggestCoporation, "/sp/project/find/icorporation");
	createSuggest(suggestIPrefectures, "/sp/project/find/iprefectures");
	createSuggest(suggestOtherAccountStore, "/sp/project/find/account");
	createSuggest(suggestOtherAccountSales, "/sp/project/find/account");
	createSuggest(suggestOtherAccountSection, "/sp/project/find/account");
	createSuggest(suggestOtherAccountOther, "/sp/project/find/account");
	createSuggest(suggestOtherAccountLeader, "/sp/project/find/account");
	createSuggest(suggestOtherAccountFc, "/sp/project/find/account");
	createSuggest(suggestSalesAgencyProgress, "/sp/project/find/salesagencytarget");
	createSuggest(suggestParticipatingStoreProgress, "/sp/project/find/participatingstorecorporation");
	createSuggest(suggestOtherProjectTeam, "/sp/project/find/m_store_develop_team");
	createSuggest(suggestProgressContractTargetName, "/sp/project/find/icorporation");

	$(suggestBuilding).on('selectionchange', setValueSuggestBox);
	$(suggestShop).on('selectionchange', setValueSuggestBox);
	$(suggestBrand).on('selectionchange', setValueSuggestBox);
	$(suggestOtherAccountStore).on('selectionchange', setValueSuggestBox);
	$(suggestOtherAccountSales).on('selectionchange', setValueSuggestBox);
	$(suggestOtherAccountSection).on('selectionchange', setValueSuggestBox);
	$(suggestOtherAccountOther).on('selectionchange', setValueSuggestBox);
	$(suggestOtherAccountLeader).on('selectionchange', setValueSuggestBox);
	$(suggestOtherAccountFc).on('selectionchange', setValueSuggestBox);
	$(suggestSalesAgencyProgress).on('selectionchange', setValueSuggestBox);
	$(suggestParticipatingStoreProgress).on('selectionchange', setValueSuggestBox);
	$(suggestOtherProjectTeam).on('selectionchange', setValueSuggestBox);
}
$(function() {
	setDataSuggestBox();
	$("#project-create-other-account-fc").hide();
	
	setDataFromBuilding();
	setDataFromShop();
	setDateSuggest();

	if ($("#project-form").length) {
		$("#project-form").validate(validation);
	}
	$("#j-form-operation-form").on("change", function () {
		displayFCAccount();
	});
	$("#j-form-external-release-confirm").on("change", function () {
		let check = this.checked;
		if(check){
			$("#j-form-external-release-confirm-label").html("外部公開日確定");
		} else {
			$("#j-form-external-release-confirm-label").html("外部公開日未確定");
		}
	});
	
	$("#addFileRelationOpinionBu").on("change", function() {
		var file = this.files[0];
		$("#j-form-file-relation-opinion-bu").html(file.name);
        opinionBU.formFile = file;
        opinionBU.hasFile = true;
	});
	$("#addFileRelationBranchPerson").on("change", function() {
		var file = this.files[0];
		$("#j-form-file-relation-branch-person").html(file.name);
        opinionBranchPerson.formFile = file;
        opinionBranchPerson.hasFile = true;
	});
	$("#addFileRelationOpinionBranch").on("change", function() {
		var file = this.files[0];
		$("#j-form-file-relation-opinion-branch").html(file.name);
        opinionBranch.formFile = file;
        opinionBranch.hasFile = true;
	});

	$(document).on('click', '#j-create', function() {
				$("label.error").remove();

				let form = $('#project-form');
				var implementationScheduleDatetimeYear = $("#project_create_implementation_schedule_datetime_year").val();
				var implementationScheduleDatetimeMonth = $("#project_create_implementation_schedule_datetime_month").val();
				var implementationScheduleDatetimeDay = $("#project_create_implementation_schedule_datetime_day").val();
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
				// Create data
				var data = new FormData();
				getDataSuggest(data, suggestBuilding, "buildingId");
				if(isBuilding){
					if(buildingData.building.iBlockId != undefined){
						data.append("iBlockId", buildingData.building.iBlockId);
					}
					if(buildingData.building.iAreaId != undefined){
						data.append("iAreaId", buildingData.building.iAreaId);
					}
					if(buildingData.building.iCorporationId != undefined){
						data.append("corporationId", buildingData.building.iCorporationId);
					}
					if(buildingData.corporation.corporationGpId != undefined){
						data.append("corporationGpId", buildingData.corporation.corporationGpId);
					}
					if(buildingData.building.iPrefecturesId != undefined){
						data.append("iPrefecturesId", buildingData.building.iPrefecturesId);
					}
					if(buildingData.building.iSalesChannelId != undefined){
						data.append("iSalesChannelId", buildingData.building.iSalesChannelId);
					}
					if(buildingData.building.adoptDifficulty != undefined){
						data.append("adoptDifficulty", buildingData.building.adoptDifficulty);
					}
				} else {
					getDataSuggest(data, suggestBlock, "iBlockId");
					getDataSuggest(data, suggestArea, "iAreaId");
					getDataSuggest(data, suggestCoporation, "corporationId");
					getDataSuggest(data, suggestCoporationGroup, "corporationGpId");
					getDataSuggest(data, suggestIPrefectures, "iPrefecturesId");
					data.append("iSalesChannelId", $("#j-form-sales-channel").val());
				}
				if(isShop){
					if (projectData.shop.shopCd !== undefined) {
						data.append("shopCd", projectData.shop.shopCd);
					}
					data.append("shopName", projectData.shop.rawName);
					let shopId = projectData.shop.id;
					if(shopId){
						data.append("iShopId", projectData.shop.id);
					}
					if(shopData.buildingId !== null && shopData.buildingId !== undefined){
						data.append("buildingId", shopData.buildingId);
					}else{
						getDataSuggest(data, suggestBuilding, "buildingId");
					}
					if(listBrandIncome.length > 1){
						let idBrand = $("#j-form-brand-label-option").val();
						data.append("brandId", idBrand);
					} else if(projectData.brandId !== undefined){
						data.append("brandId", projectData.brandId);
					} else if(listBrandIncome.length == 1){
						data.append("brandId", listBrandIncome[0].id);
					} else {
						data.append("brandId", "");
					}

            		if(shopData.section !== undefined && shopData.section !== null){
            			projectSectionProgressCurrent.section = shopData.section;
            		}else{
            			projectSectionProgressCurrent.section = "";
            		}

            		if(shopData.frontage !== undefined && shopData.frontage !== null){
            			projectSectionProgressCurrent.frontage = shopData.frontage;
            		}else{
            			projectSectionProgressCurrent.frontage = "";
            		}

            		if(shopData.floorNum !== undefined && shopData.floorNum !== null){
            			projectSectionProgressCurrent.floor = shopData.floorNum;
            		}else{
            			projectSectionProgressCurrent.floor = "";
            		}

            		if(shopData.contractTsubo !== undefined && shopData.contractTsubo !== null){
            			projectSectionProgressCurrent.contractTsubo = shopData.contractTsubo;
            		}else{
            			projectSectionProgressCurrent.contractTsubo = 0;
            		}

            		if(shopData.businessHours !== undefined && shopData.businessHours !== null){
            			projectSectionProgressCurrent.businessHours = shopData.businessHours;
            		}else{
            			projectSectionProgressCurrent.businessHours = "";
            		}

            		if(shopData.buildingExpectedValue !== undefined && shopData.buildingExpectedValue !== null){
            			projectSectionProgressCurrent.buildingExpectedValue = shopData.buildingExpectedValue;
            		}

//            		if(!checkProperties(salesAgencyTarget)){
//                        projectPlanCurrent.salesAgencyTargetId = salesAgencyTarget.id;
//                    }
                    if (!checkProperties(salesAgencyContract)){
                        let endDate = salesAgencyContract.endDate;
                        let startDate = salesAgencyContract.startDate;
                        projectPlanCurrent.startDate = (new Date(startDate) instanceof Date)  ? startDate : null;
                        projectPlanCurrent.endDate = (new Date(endDate) instanceof Date) ? endDate : null;
                        projectPlanCurrent.numberOfYear = salesAgencyContract.numberOfYear;
                        projectPlanCurrent.salesAgencyTargetId = salesAgencyContract.salesAgencyTargetId;
                    }
                    if(!checkProperties(participatingStoreCorporation)){
                        projectPlanCurrent.participatingStoreCorporationId = participatingStoreCorporation.id;
                    }
				} else {
					getDataSuggest(data, suggestBuilding, "buildingId");
					getDataSuggest(data, suggestBrand, "brandId");
//					projectPlanCurrent.startDate = $("#project-create-i-sales-agency-contract-startdate-curent-label").val();
//                    projectPlanCurrent.endDate = $("#project-create-i-sales-agency-contract-enddate-curent-label").val();

//                    projectSectionProgressCurrent.section = "";
//                    projectSectionProgressCurrent.frontage = "";
//                    projectSectionProgressCurrent.floor = "";
//                    projectSectionProgressCurrent.contractTsubo = 0;
//                    projectSectionProgressCurrent.businessHours = "";
				}

				if (!checkProperties(projectPlanCurrent)) {
	                projectPlanCurrent.category = "CURRENT";
	                projectPlanDto.push(projectPlanCurrent);
	            }
				projectPlanProgress.startDate = $("#project-create-i-sales-agency-contract-startdate-progress").val();
	            projectPlanProgress.endDate = $("#project-create-i-sales-agency-contract-enddate-progress").val();
	            projectPlanProgress.numberOfYear = $("#project_create_i_sales_agency_contract_number_of_year_progress_hidden").val();
	            if (!checkProperties(projectPlanProgress)) {
	                projectPlanProgress.category = "PROGRESS";
	                projectPlanDto.push(projectPlanProgress);
	            }

				if(opinionBranchPerson.hasFile || $("#j-form-file-relation-branch-person-comment").val().length > 0){
					opinionBranchPerson.comment = $("#j-form-file-relation-branch-person-comment").val();
	                projectOpinionDto.push(opinionBranchPerson);
	            }
	            if(opinionBranch.hasFile || $("#j-form-file-relation-opinion-branch-comment").val().length > 0){
	            	opinionBranch.comment = $("#j-form-file-relation-opinion-branch-comment").val();
	                projectOpinionDto.push(opinionBranch);
	            }
	            if(opinionBU.hasFile || $("#j-form-file-relation-opinion-bu-comment").val().length > 0){
	            	opinionBU.comment = $("#j-form-file-relation-opinion-bu-comment").val();
	                projectOpinionDto.push(opinionBU);
	            }

	            //getDataForSectionProgressCurrent();
	            getDataForSectionProgressNegotiation();
	            getDataForContractProgress();
	            getDataForContractProgressCurrent();

	            var projectSectionProgressDto = [];
	            var projectContractProgressDto = [];
	            if(!checkProperties(projectSectionProgressCurrent)){
	            	projectSectionProgressCurrent.category = "CURRENT";
	            	projectSectionProgressDto.push(projectSectionProgressCurrent);
	            }

	            if(!checkProperties(projectSectionProgressNegotiation)){
	            	projectSectionProgressNegotiation.category = "NEGOTIATION";
	            	projectSectionProgressDto.push(projectSectionProgressNegotiation);
	            }
	            projectContractProgressCurrent.memo = $("#project-contract-progress-current-memo").val()
	            projectContractProgressDto.push(projectContractProgressCurrent);
	            projectContractProgressDto.push(projectContractProgress);

				// Append Data form
				form.find(".ng-hide").find("select, input").each(function(){
					$(this).removeAttr('name');
				});
	            $.each(form.serializeArray(), function (index, obj) {
	            	if (obj.name.indexOf("j-form-task-period-date") === -1) {
	            		data.append(obj.name, obj.value);
	            	}
	            });
	            $.each(form.find('input[type=checkbox][name]:not(:checked)'), function (index, element) {
	            	data.append($(element).attr('name'), false);
	            });
				// Get list object Images / Docs
				getListDataForRequest(data);

				// get list project file
				$.each(form.find('div[objectName="projectMediaDocumentDto"][class="media-list-item has-trash new-item"]'), function(index, obj) {
					data.append("docs", $(this).prop("data"));
				});
				projectOpinionDto.forEach(function (element) {
                    if (element.formFile != null) {
                    	data.append("opinionFiles", element.formFile);
                    }
                    delete element.formFile;
                });
				if(projectOpinionDto.length > 0) {
					projectOpinionDto.forEach(function (element, index) {
						data.append('projectOpinionDto['+index+'].comment', element.comment);
						data.append('projectOpinionDto['+index+'].hasFile', element.hasFile);
						data.append('projectOpinionDto['+index+'].category', element.category);
					});
				}
				if(projectSectionProgressDto.length > 0) {
					projectSectionProgressDto.forEach(function (element, index) {
						//data.append('projectSectionProgressDto['+index+'].section', element.section);
						//data.append('projectSectionProgressDto['+index+'].frontage', element.frontage);
						data.append('projectSectionProgressDto['+index+'].floor', element.floor);
						data.append('projectSectionProgressDto['+index+'].category', element.category);
						data.append('projectSectionProgressDto['+index+'].businessHours', element.businessHours);
						data.append('projectSectionProgressDto['+index+'].contractTsubo', element.contractTsubo);
						if (element.buildingExpectedValue !== undefined) {
							data.append('projectSectionProgressDto['+index+'].buildingExpectedValue', element.buildingExpectedValue);
						}
						data.append('projectSectionProgressDto['+index+'].memo', element.memo);
					});
				}
				if(projectContractProgressDto.length > 0) {
					projectContractProgressDto.forEach(function (element, index) {
						for (var key in element) {
							if (element[key] != undefined && element[key] !== null){
								data.append('projectContractProgressDto['+index+'].' + key, element[key]);
							}
						}
					});
				}

				var statusIds = [];
				var statusIdList = $(".schedule-status-id").each(function(index, obj) {
					statusIds.push($(obj).val());
				});

				$(".schedule-datepicker").each(function (index, element) {
					var id = statusIds[index];
					data.append('projectScheduleDto['+index+'].actionStatusId', parseInt(id));
					data.append('projectScheduleDto['+index+'].scheduleDate', element.getAttribute('value'));
				});
				if (projectTaskAccount.length > 0) {
					projectTaskAccount.forEach(function (taskAccount, indexTask) {
						if(taskAccount.length > 0){
							taskAccount.forEach(function (account, index) {
								data.append('projectTaskDto['+indexTask+'].account['+index+'].accountId', account);
							});
						}
					})
				}

				if ($("#project-create-other-project-team").val() != null) {
					data.append('otherProjectTeamDto[0].deptCd', $("#project-create-other-project-team").val());
				}
				if(accountStore.length > 0){
					accountStore.forEach(function (element) {
						otherProjectAccountDto.push(element);
					});
				}
				if(accountSales.length > 0){
					accountSales.forEach(function (element) {
						otherProjectAccountDto.push(element);
					});
				}
				if(accountSection.length > 0){
					accountSection.forEach(function (element) {
						otherProjectAccountDto.push(element);
					});
				}
				if(accountOther.length > 0){
					accountOther.forEach(function (element) {
						otherProjectAccountDto.push(element);
					});
				}
				if(accountFc.length > 0){
					accountFc.forEach(function (element) {
						otherProjectAccountDto.push(element);
					});
				}
				if(accountLeader.length > 0){
					accountLeader.forEach(function (element) {
						otherProjectAccountDto.push(element);
					});
				}
				if(otherProjectAccountDto.length > 0){
					otherProjectAccountDto.forEach(function (element, index) {
						data.append('otherProjectAccountDto['+index+'].category', element.category);
						data.append('otherProjectAccountDto['+index+'].iAccountId', element.iAccountId);
					});
				}
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
	            }

				data.append('shopNameTemporary', $("#j-form-shop-name-temporary").val());;

				jQuery.ajax({
					url : createUrl("/pc/project/create"),
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
					error : function(response) {
						console.log(response)
						alert("作成に失敗しました。");
						$($this).removeClass("disabled");
					}
				});
			});

	// Add Task
	$('#addProjectTask').click( function() {
						let htmltext = '<div class="media-list-item has-trash new-item new-project-task" objectName="projectTaskDto">';
						htmltext += '<div class="row">';
						htmltext += '<input class="object-property" type="hidden" propertyName="id" value="0">';
						htmltext += '<div class="col-md-12">';

						htmltext += '<div class="right">';
							htmltext += '<div class="trash j-delete-item">';
								htmltext += '<i class="fa fa-trash"></i>';
							htmltext += '</div>';
						htmltext += '</div>';
						htmltext += '<div class="form-group">';
							htmltext += '<div class="row">';
								htmltext += '<div class="col-md-2">';
									htmltext += '<label>内容</label>';
								htmltext += '</div>';
								htmltext += '<div class="col-md-9">';
									htmltext += '<textarea class="form-control" id="j-form-task-comment-add'+indexTask+'" propertyName="comment" rows="3" placeholder="内容を入力してください"></textarea>';
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
							htmltext += '</div>';
						htmltext += '</div>';
						htmltext += '<div class="form-group">';
							htmltext += '<div class="row">';
								htmltext += '<div class="col-md-2">';
									htmltext += '<label>期限日</label>';
								htmltext += '</div>';
								htmltext += '<div class="col-md-9 project-task-period-date">';
									htmltext += '<input class="form-control" type="text" id="j-form-task-period-add'+indexTask+'" name="j-form-task-period-date'+indexTask+'" propertyName="period" placeholder="期限日を入力してください">';
								htmltext += '</div>';
							htmltext += '</div>';
						htmltext += '</div>';

						htmltext += '<div class="form-group">';
							htmltext += '<div class="row">';
								htmltext += '<div class="col-md-2">';
									htmltext += '<label>担当者</label>';
								htmltext += '</div>';
								htmltext += '<div class="col-md-9">';
									htmltext += '<input class="form-control" type="text" placeholder="担当者を入力してください" ';
										htmltext += 'id="j-form-task-account-add'+ indexTask +'">';
									htmltext += '<input class="form-control" type="hidden" ';
										htmltext += 'id="id-form-task-account-add' + indexTask + '">';
								htmltext += '</div>';
							htmltext += '</div>';
						htmltext += '</div>';
						htmltext += '</div>';
						htmltext += '</div>';
						htmltext += '</div>';
						$('#task > .tab-content-body > .media-list').prepend(htmltext);
						if ($('.project-task-period-date').length) {
							createDatePicker($('.project-task-period-date').find('input'));
						}
						$('#j-form-task-period-add' + indexTask).rules("add", {
									required: true,
									dateISO : true,
									messages : {
										required: "期限を選択してください。",
										dateISO : "日付の形式を正しく入力してください。",
									}
								});
						let suggestTaskAccount = $('#j-form-task-account-add' + indexTask).magicSuggest({
							noSuggestionText: "候補がありません",
							maxSelectionRenderer: function () {
								return "";
							},
							allowFreeEntries : false,
						});
						createSuggest(suggestTaskAccount, "/sp/project/find/account");

						$(suggestTaskAccount).on('selectionchange', function() {
									let value = this.getSelection();
									let accountTask = [];
									if (value.length > 0) {
										value.forEach(function (account) {
											accountTask.push(account.id);
										});
									}
									if(accountTask.length > 0 ){
										projectTaskAccount[indexTask-1] = accountTask;
									}
								});
						indexTask++;

					});

	// Add Image/document
	$('#add-related-document').click( function() {
					indexImage++;
					let htmltext = '<div class="media-list-item has-trash new-item" objectName="projectMediaDocumentDto">';
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
					htmltext += '<input type="text" class="form-control" propertyName="displayName" input-file="openImageFile'
							+ indexImage + '" placeholder="ファイル名">';
					htmltext += '<span class="input-group-btn">';
					htmltext += '<label class="btn btn-primary" for="openImageFile'
							+ indexImage + '" >選択</label>';
					htmltext += '<input type="file" control-name="docs" class="openImageFile" style="display:none" id="openImageFile'
							+ indexImage
							+ '" name="openImageFile'
							+ indexImage
							+ '" accept=".png, .gif, .jpg, .jpeg .pdf, .xls, .xlsx, .doc, .docx">';
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
								htmltext += '<label></label>';
							htmltext += '</div>';
							htmltext += '<div class="col-md-9">';
								htmltext += '<select class="form-control" style="width: 175px" id="project-file-division' + indexImage + '" propertyName="division">';
									projectFileDivision.forEach(function(division){
										htmltext += '<option value="'+division.fileDivisionCode+'">' + division.displayName + '';
										htmltext += '</option>';
									});
								htmltext += '</select>';
							htmltext += '</div>';
						htmltext += '</div>';
					htmltext += '</div>';
					htmltext += '<div class="form-group">';
						htmltext += '<div class="row">';
							htmltext += '<div class="col-md-2">';
								htmltext += '<label></label>';
							htmltext += '</div>';
							htmltext += '<div class="col-md-9">';
								htmltext += '<select class="form-control" style="width: 100px" id="project-file-output-flag' + indexImage + '" propertyName="outputFlag">';
									htmltext += '<option value="T">出力有り</option>';
									htmltext += '<option value="F">出力無し</option>';
								htmltext += '</select>';
							htmltext += '</div>';
						htmltext += '</div>';
					htmltext += '</div>';
					htmltext += '<div class="form-group" id="project-file-output-number'+ indexImage +'">';
						htmltext += '<div class="row">';
							htmltext += '<div class="col-md-2">';
								htmltext += '<label></label>';
							htmltext += '</div>';
							htmltext += '<div class="col-md-9">';
								htmltext += '<input class="form-control" style="width: 100px" placeholder="出力順序" value="10" propertyName="outputNumber">';
							htmltext += '</div>';
						htmltext += '</div>';
					htmltext += '</div>';

					htmltext += '<div class="right">';
						htmltext += '<div class="trash j-delete-item">';
							htmltext += '<i class="fa fa-trash"></i>';
						htmltext += '</div>';
					htmltext += '</div>';
					htmltext += '</div>';
					htmltext += '</div>';
					htmltext += '</div>';

					$('#related-document > .tab-content-body > .media-list').append(htmltext);
					displayOutputNumber(indexImage);
					$('#openImageFile' + indexImage).rules("add", {
						hasfile : true,
					});
				});
	$("#related-document").on("change", ".openImageFile", function() {
				let parent = $(this).closest(".media-list-item");
				let files = $(this).prop("files");
				let fileExtension = ['image/jpeg', 'image/png', 'image/gif', 'application/pdf', 'application/vnd.openxmlformats-officedocument.wordprocessingml.document', 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet', 'application/vnd.openxmlformats-officedocument.presentationml.presentation'];
				if (files.length > 0) {
					if ($.inArray(this.files[0].type, fileExtension) == -1) {
						alert("画像の拡張子はjpg、png、gif、pdf、xlsx、docx、またはpptxでアップロードしてください");
					} else {
						parent.find('input[input-file="' + $(this).attr("id") + '"]').val(files[0].name);
						parent.find('.sizefile').html(Math.round(files[0].size / 1024) + 'KB');
						var reader = new FileReader();
						reader.readAsDataURL(files[0]);
						reader.onload = function() {
							let dataUrl = reader.result;
							parent.find('.media-object').attr('src', dataUrl);
						};
					}
				} else {
					parent.find('input[input-file="' + $(this).attr("id") + '"]').val('');
					parent.find('.sizefile').html('');
					parent.find('.media-object').attr('src', '');
				}
			});

	$(document).on('click', '.j-delete-item', function() {
		let parent = $(this).closest(".media-list-item");
		if (parent.hasClass("present-item")) {
			$(parent).addClass("hidden");
			$.each($(parent).find("input"), function(index, obj) {
				$(obj).rules('remove');
			});
		} else if (parent.hasClass("new-item")) {
			if(parent.hasClass("new-project-task")){
				let index = $(".new-project-task").index(parent);
				projectTaskAccount.splice(index, 1);
			}
			$(parent).remove();
		}
	});

	$('a[href]:not([href*="#"],[target="_blank"])').click(function(e) {
		let searchForm = localStorage.getItem("project_search_form");
		let form = null;
		let isSubmit = false;
		if(searchForm !== undefined && searchForm !== null && searchForm.length > 0){
			let formData = JSON.parse(searchForm);
			let input = "";
			if(formData.length > 0){
				$.each(formData, function(index, obj) {
					if(this.name !== 'isDefaultSearch'){
						input += '<input type="hidden" name="' + this.name + '" value="' + this.value + '" />';
					}
				});
			}			
			form = $('<form action="/wsd/pc/project/find" method="post" accept-charset="UTF-8">'+input+'</form>');
			$('body').append(form);
			isSubmit = true;
		}
		if (!confirm('他のﾍﾟｰｼﾞに移動します。内容が消えますがよろしいでしょうか？')) {
			return false;
		} else {
			if(isSubmit){
				form.submit();
			} else {
				window.location.replace(createUrl("/pc/project/list"));
			}
		}
	});

	if ($("#project_create_operation_division").val() === "中止"){
		$("#project_create_stop_reason_area").show();
	}else{
		$("#project_create_stop_reason_area").hide();
		$("#project_create_stop_reason").val(null);
	}

	$("#project_create_operation_division").change(function(){
		if ($(this).val() === "中止"){
			$("#project_create_stop_reason_area").show();
		}else{
			$("#project_create_stop_reason_area").hide();
			$("#project_create_stop_reason").val(null);
		}
	});

	function setSamePlaceholder($targets, message) {
		$.each($targets, function (index, $target) {
			$($target).attr('placeholder', message);
		})
	}

	$("#project-create-category").change(function(){
		var categoryId = $(this).val();
		var $rentContractProgressTargets = ['#project_create_rent_contract_startdate_progress', '#project_create_rent_contract_enddate_progress', '#project_create_rent_contract_number_of_year_progress'];
		setSamePlaceholder($rentContractProgressTargets, '');  // reset placeholder

		if(categoryId.length > 0 && categoryId > 0){
			switch (categoryId) {
				case '1':  // 出店
					makeProjectTitle();
					break;
				case '2':  // 定借満了
					makeProjectTitle();
					setTimeout(function() {
						$("#project-create-tenancy-period").val("伴う");
					}, 200);
					break;
				case '3':  // 契約期中
					makeProjectTitle();
					setTimeout(function() {
						$("#project-create-tenancy-period").val("伴わない");
						setSamePlaceholder($rentContractProgressTargets, '期間限定の条件変更の場合は、ここに入力');
					}, 200);
					break;
				case '11':  // POPUP
					makeProjectTitle();
					break;
				default:
					$("#project-create-tenancy-period").val("");
					break;
			}
			makePostRequest("/sp/project/find/project_classification/suspension/" + categoryId, {}).then(function (data) {
				var html = "";
					html += '<option value=""></option>';
				$.each(data, function(){
					html += '<option value="'+this.id+'">'+this.name+'</option>';
				});
				$("#project_create_stop_reason").html(html);
	        });
			makePostRequest("/sp/project/find/project_classification/landing/" + categoryId, {}).then(function (data) {
				var html = "";
					html += '<option value=""></option>';
				$.each(data, function(){
					if($("#project-create-category").val() == 1 && this.name=="出店"){
						html += '<option value="'+this.id+'" selected="selected">'+this.name+'</option>';
					}else if($("#project-create-category").val() == 11 && this.name== "出店(POPUP)"){
						html += '<option value="'+this.id+'" selected="selected">'+this.name+'</option>';
					}else{
						html += '<option value="'+this.id+'">'+this.name+'</option>';
					}
				});
				$("#project-create-landing-category-name").html(html);
				makeProjectTitle();

	        });
			makePostRequest("/sp/project/find/project_classification/landing_possibility/" + categoryId, {}).then(function (data) {
				var html = "";
					html += '<option value=""></option>';
				$.each(data, function(){
					html += '<option value="'+this.id+'">'+this.name+'</option>';
				});
				$("#project-create-conclusion-possibility-percent").html(html);
			});
			makePostRequest("/sp/project/find/project_progress/company/" + categoryId, {}).then(function (data) {
				var html = "";
				$.each(data, function(){
					html += '<option value="'+this.id+'">'+this.name+'</option>';
				});
				$("#project-create-progress-company").html(html);
	        });
			makePostRequest("/sp/project/find/project_progress/negotiation/" + categoryId, {}).then(function (data) {
				var html = "";
				$.each(data, function(){
					html += '<option value="'+this.id+'">'+this.name+'</option>';
				});
				$("#project-create-progress-negotiation").html(html);
	        });
			makePostRequest("/sp/project/store/require/" + categoryId, {}).then(function (data) {
				requiredShop = data;
					});
			
			if( projectCategoryDevelopShop.indexOf(Number(categoryId)) > -1 ){
				suggestOtherAccountStore.setSelection([{
					id:userInfo.id,
					name:userInfo.fullName
				}]);
			}else if( projectCategoryBranch.indexOf( Number(categoryId)) > -1) {
				suggestOtherAccountSales.setSelection([{
					id:userInfo.id,
					name:userInfo.fullName
				}]);
			}
					
		}
	});

	$("#project-create-landing-category-name").change(function(){
		if ($("#project-create-landing-category-name option:selected").text() !== "") {
			createProjectTitle.landing = $("#project-create-landing-category-name option:selected").text();
		} else {
			createProjectTitle.landing = "";
		}
		makeProjectTitle();
	});

	$("#project_create_implementation_datetime").change(function(){
		makePostRequest("/sp/project/period/" + $(this).val(), {}).then(function (data) {
			var termData = JSON.parse(data);
			$("#project_create_implementation_period_label").html(termData.term);
		});
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

		 if(isShop){
			 buildingId = shopData.buildingId;
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
		 let shopId = suggestShop.getValue()[0];

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
	
	$("#project-contract-progress-start-date").on("change", function() {
		let endDate = $("#project-contract-progress-end-date").val();
		let numOfYear = $("#project-contract-progress-contract-number-of-year").val();
		if(endDate !== undefined && endDate){
			calculateProgressContractNumberOfYear();
		} else if(numOfYear !== undefined && numOfYear){
			calculateProgressContractEndDate();
		}
	});

	$("#project-contract-progress-end-date").on("change", function() {
		calculateProgressContractNumberOfYear();

//		makePostRequest("/sp/project/period/" + $(this).val(), {}).then(function (data) {
//			var termData = JSON.parse(data);
//			$("#project-contract-progress-contract-term").html(termData.term);
//		});
	});
	
	$("#project-contract-progress-contract-number-of-year").on("keyup", function() {
		calculateProgressContractEndDate();
	});
	
	$("#project_create_rent_contract_startdate_progress").on("change", function () {
		let endDate = $("#project_create_rent_contract_enddate_progress").val();
		let numOfYear = $("#project_create_rent_contract_number_of_year_progress").val();
		if(endDate !== undefined && endDate){
			calculateProgressContractRentNumberOfYear();
		} else if(numOfYear !== undefined && numOfYear){
			calculateProgressContractRentEndDate();
		}
	});
	
	$("#project_create_rent_contract_enddate_progress").on("change", function () {
		calculateProgressContractRentNumberOfYear();
	});
	
	$("#project_create_rent_contract_number_of_year_progress").on("keyup", function () {
		calculateProgressContractRentEndDate();
	});
	
	// Start Caculate for 販売代行 契約年数
	$("#project-create-i-sales-agency-contract-startdate-progress").on("change", function() {
		let endDate = $("#project-create-i-sales-agency-contract-enddate-progress").val();
		let numOfYear = $("#project_create_i_sales_agency_contract_number_of_year_progress").val();
		if(endDate !== undefined && endDate){
			calculateProgressPlanNumberOfYear();
		} else if(numOfYear !== undefined && numOfYear){
			calculateProgressPlanEndDate();
		}
	});
	
	$("#project-create-i-sales-agency-contract-enddate-progress").on("change", function() {
		calculateProgressPlanNumberOfYear();
	});
	
	$("#project_create_i_sales_agency_contract_number_of_year_progress").on("keyup", function () {
		calculateProgressPlanEndDate();
	});
	// End Caculate for 販売代行 契約年数
});

function calculateProgressContractEndDate(){
	let startDate = $("#project-contract-progress-start-date").val();
	let numberOfYear = $("#project-contract-progress-contract-number-of-year").val();
	let endDate = calcEndDateProgressContract(startDate, numberOfYear);
	$("#project-contract-progress-end-date").val(endDate);
	$("#project-contract-progress-contract-number-of-year-hidden").val(numberOfYear);
}

function calculateProgressPlanEndDate(){
	let startDate = $("#project-create-i-sales-agency-contract-startdate-progress").val();
	let numberOfYear = $("#project_create_i_sales_agency_contract_number_of_year_progress").val();
	let endDate = calcEndDateProgressContract(startDate, numberOfYear);
	$("#project-create-i-sales-agency-contract-enddate-progress").val(endDate);
	$("#project_create_i_sales_agency_contract_number_of_year_progress_hidden").val(numberOfYear);
}

function calculateProgressContractRentEndDate(){
	let startDate = $("#project_create_rent_contract_startdate_progress").val();
	let numberOfYear = $("#project_create_rent_contract_number_of_year_progress").val();
	let endDate = calcEndDateProgressContract(startDate, numberOfYear);
	$("#project_create_rent_contract_enddate_progress").val(endDate);
	$("#project_create_rent_contract_number_of_year_progress_hidden").val(numberOfYear);
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
					data.append(obj + '[' + index + '].isDeleted', $(this).hasClass("hidden"));
					if (!$(this).hasClass("hidden")) {
						$(this).find('[propertyName]').each(
								function() {
									if($(this).attr('type') === 'checkbox'){
										if(this.checked){
											data.append(obj + '[' + index + '].' + $(this).attr('propertyName'), true);
										} else {
											data.append(obj + '[' + index + '].' + $(this).attr('propertyName'), false);
										}
									} else  {
										data.append(obj + '[' + index + '].' + $(this).attr('propertyName'), $(this).val());
									}

								});
					} else {
						$(this).find('[propertyName=id]').each(
								function() {
									data.append(obj + '[' + index + '].' + $(this).attr('propertyName'), $(this).val());
								});
					}
				});
	});
}

function checkValidSuggest() {
	let result = true;
	let value = suggestShop.getValue();
	if (requiredShop && value.length <= 0) {
		$('#j-form-shop')
				.after(
						'<label id="j-form-shop-error" class="error" for="j-form-shop">必須項目です。</label>');
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

function displayOutputNumber(index) {
	$("#project-file-output-flag" + index).change(function() {
		if ($(this).val() === 'T') {
			$("#project-file-output-number" + index).show();
			$("#project-file-output-number" + index).find('input').attr('propertyName', 'outputNumber');
		} else {
			$("#project-file-output-number" + index).hide();
			$("#project-file-output-number" + index).find('input').attr('propertyName', '');
		}
	});
}

function getDataSuggest(data, suggest, field) {
	let value = suggest.getValue();
	if (value.length > 0) {
		data.append(field, value[0]);
	}
}
function getDataForSectionProgressNegotiation() {
	//projectSectionProgressNegotiation.section = $("#project-section-progress-negotiation-section").val();
	//projectSectionProgressNegotiation.frontage = $("#project-section-progress-negotiation-frontage").val();
	projectSectionProgressNegotiation.floor = $("#project-section-progress-negotiation-floor").val();
	projectSectionProgressNegotiation.contractTsubo = $("#project-section-progress-negotiation-contractTsubo").val();
	projectSectionProgressNegotiation.businessHours = $("#project-section-progress-negotiation-businessHours").val();
	//projectSectionProgressNegotiation.memo = $("#project-section-progress-negotiation-memo").val();
	let buildingExpectedValue = $("#project-section-progress-negotiation-building-expected-value").val();
	if(buildingExpectedValue !== undefined 
		&& buildingExpectedValue !== null 
		&& buildingExpectedValue.length > 0 
		&& !isNaN(buildingExpectedValue)){
		projectSectionProgressNegotiation.buildingExpectedValue = buildingExpectedValue * 1000;
	}
}
function getDataForSectionProgressCurrent() {
	//projectSectionProgressCurrent.memo = $("#project-section-progress-current-memo").val();
}
function getDataForContractProgress(){
	projectContractProgress.form = $("#project-contract-progress-form").val();
	projectContractProgress.autoUpdate = $("#project-contract-progress-auto-update").val();
	if(suggestProgressContractTargetName.getSelection().length > 0){
		projectContractProgress.contractTargetName = suggestProgressContractTargetName.getSelection()[0].name;
	} else{
		projectContractProgress.contractTargetName = null;
	}
	projectContractProgress.contractStartDate = $("#project-contract-progress-start-date").val();
	projectContractProgress.contractEndDate = $("#project-contract-progress-end-date").val();
	projectContractProgress.contractNumberOfYear = $("#project-contract-progress-contract-number-of-year-hidden").val();
	projectContractProgress.memo = $("#project-contract-progress-memo").val();
	projectContractProgress.rentStartDate = $("#project_create_rent_contract_startdate_progress").val();
    projectContractProgress.rentEndDate = $("#project_create_rent_contract_enddate_progress").val();
    projectContractProgress.rentYear = $("#project_create_rent_contract_number_of_year_progress_hidden").val();
    projectContractProgress.operationForm = $("#j-form-contract-progress-operation-form").val();
}
function getDataForContractProgressCurrent(){
	if(isShop){
		projectContractProgressCurrent.operationForm = $("#j-form-operation-form-label").text();
	} else{
		projectContractProgressCurrent.operationForm = $("#j-form-operation-form").val();
	}
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
function calculateProgressContractNumberOfYear(){	
	let numOfYear = calcYear($("#project-contract-progress-start-date").val(), $("#project-contract-progress-end-date").val());
	if(numOfYear === +numOfYear && numOfYear !== (numOfYear|0)){
		$("#project-contract-progress-contract-number-of-year").val(roundNumber(numOfYear, 2));
		$("#project-contract-progress-contract-number-of-year-hidden").val(numOfYear);
	} else{
		$("#project-contract-progress-contract-number-of-year").val('');
		$("#project-contract-progress-contract-number-of-year-hidden").val('');
	}
}
function calculateProgressPlanNumberOfYear(){
	let numOfYear = calcYear($("#project-create-i-sales-agency-contract-startdate-progress").val(), $("#project-create-i-sales-agency-contract-enddate-progress").val());
	if(numOfYear === +numOfYear && numOfYear !== (numOfYear|0)){
		$("#project_create_i_sales_agency_contract_number_of_year_progress").val(roundNumber(numOfYear, 2));
		$("#project_create_i_sales_agency_contract_number_of_year_progress_hidden").val(numOfYear);
	} else{
		$("#project_create_i_sales_agency_contract_number_of_year_progress").val('');
		$("#project_create_i_sales_agency_contract_number_of_year_progress_hidden").val('');
	}
}
function calculateProgressContractRentNumberOfYear(){	
	let numOfYear = calcYear($("#project_create_rent_contract_startdate_progress").val(), $("#project_create_rent_contract_enddate_progress").val());
	if(numOfYear === +numOfYear && numOfYear !== (numOfYear|0)){
		$("#project_create_rent_contract_number_of_year_progress").val(roundNumber(numOfYear, 2));
		$("#project_create_rent_contract_number_of_year_progress_hidden").val(numOfYear);
	} else{
		$("#project_create_rent_contract_number_of_year_progress").val('');
		$("#project_create_rent_contract_number_of_year_progress_hidden").val('');
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
function displayIDContractTsubo (){
    let pcc = null;
    let pnt = null;
    let changeValue = "";
    
    if(isShop){
    	pcc = shopData.contractTsubo;
    }
    
    if($('#project-section-progress-negotiation-contractTsubo').length > 0){
    	pnt = $('#project-section-progress-negotiation-contractTsubo').val();
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
	$.each(projectFileDivision, function(obj){
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
function validateImplementationScheduleDate(year, month, day) {
	if(year == '' && month == ''  && day == '') {
        $("#project_create_implementation_schedule_datetime_year").rules("remove", "required");
        $("#project_create_implementation_schedule_datetime_month").rules("remove", "required");
        $("#project_create_implementation_schedule_datetime_day").rules("remove", "required");
    } else if(year == '' || month == '' || day == '') {
    	if(year !== '' || month !== '' || day !== '') {
    		if(year == ''){
    			$("#project_create_implementation_schedule_datetime_year").rules("add", {
                    required: true,
                    messages: {
                        required: "あいまい設定を入力してください"
                    }
                });
            }
            if(month == ''){
                $("#project_create_implementation_schedule_datetime_month").rules("add", {
                    required: true,
                    messages: {
                        required: "あいまい設定を入力してください"
                    }
                });
            }
            if(day == ''){
                $("#project_create_implementation_schedule_datetime_day").rules("add", {
                    required: true,
                    messages: {
                        required: "あいまい設定を入力してください"
                    }
                });
            }
        }
    }
}

function makeProjectTitle(){
	var selprojectCategory = $("#project-create-category option:selected").val();
	let _building = createProjectTitle.building;
	let _brand = createProjectTitle.brand;
	let _category = $("#project-create-landing-category-name option:selected").text();

	if ($("#j-form-shop").find(".ms-close-btn").length == 0) {
		_brand = "";
	}

	if (selprojectCategory == 1 || selprojectCategory == 2 || selprojectCategory == 3 || selprojectCategory == 11) {
		if (_building !== "" && _brand === "" &&  _category === "") {
			$("#project-create-title").val(_building);
		} else if (_building !== "" && _brand !== "" && _category === ""){
			$("#project-create-title").val(_building + "_" + _brand);
		} else if (_building !== "" && _brand === "" && _category !== ""){
			$("#project-create-title").val(_building + "_" + _category);
		} else if (_building !== "" && _brand !== "" && _category !== ""){
			$("#project-create-title").val(_building + "_" + _brand + "_" + _category);
		} else if (_building === "" && _brand !== "" && _category === ""){
			$("#project-create-title").val(_brand);
		} else if (_building === "" && _brand !== "" && _category !== ""){
			$("#project-create-title").val(_brand + "_" + _category);
		} else if (_building === "" && _brand === "" && _category !== ""){
			$("#project-create-title").val(_category);
		} else {
			$("#project-create-title").val("");
		}
	} else if (_building !== "" && _brand !== "") {
		$("#project-create-title").val(_building + '_' + _brand);
	} else if (_building !== "" && _brand === ""){
		$("#project-create-title").val(_building);
	} else if (_building === "" && _brand !== ""){
		$("#project-create-title").val(_brand);
	} else {
		$("#project-create-title").val("");
	}
	if (_building !== "" && _brand !== "" && $("#j-form-shop-name-temporary").val() === "") {
		$("#j-form-shop-name-temporary").val(_building + '_' + _brand);
	}
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

function roundNumber(value, decimalDigit) {
    let num = Math.pow(10, decimalDigit);
    return Math.round(value * num) / num;
}

function displayFCAccount(){
	if(isShop){
		if ($("#j-form-operation-form-label").text() == 'FC') {
			$("#project-create-other-account-fc").show();
		} else {
			suggestOtherAccountFc.clear();
			$("#project-create-other-account-fc").hide();
		}
	}else{
		if ($("#j-form-operation-form").val() == 'FC') {
			$("#project-create-other-account-fc").show();
		} else {
			suggestOtherAccountFc.clear();
			$("#project-create-other-account-fc").hide();
		}
	}
}

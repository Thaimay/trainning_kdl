let validation = {
	rules : {
		currentAreaNumber : {
			number : true
		},
		externalRelease : "inspDateTime",
		articleReviewDatetime : "inspDateTime",
		managementDatetime : "inspDateTime",
		investmentProcessDatetime : "inspDateTime",
		againContractStartDate : "inspDateTime",
		againContractEndDate : "inspDateTime",
		transferImplementationDatetime : "inspDateTime",
		redecorationLastSalesDatetime : "inspDateTime",
		businessHoursChangeStartDate : "inspDateTime",
		needfullyManpowerDatetime : "inspDateTime",
		needfullyManpowerNumber : {
			number : true
		},
		profitRate : {
			number : true,
			range: [0,100],
		},
		project_section_progress_current_contracttsubo: {
            number: true,
            min: 0,
        },
        project_section_progress_negotiation_contracttsubo: {
            number: true,
            min: 0,
        },
        project_section_progress_negotiation_building_expected_value: {
        	digits: true,
        },
        salesPrediction: {
        	digits: true,
		},
		project_contract_progress_contract_number_of_year: {
            number: true,
            min: 0,
        },
        project_create_rent_contract_number_of_year_progress: {
            number: true,
            min: 0,
        },
        project_create_implementation_schedule_datetime_year: {
            digits: true,
            min: 1970,
        },
        project_create_implementation_schedule_datetime_month: {
            digits: true,
            range: [1,12],
        },
        profitExpectation: {
            number: true,
            min: 0,
        },
        projectCategoryId: {
        	required: true,
        },
        project_create_i_sales_agency_contract_number_of_year_progress:{
        	number: true,
            min: 0,
        },
        title:{
        	required: true,
        	maxlength: 256,
        }
	},
	messages : {
		currentAreaNumber : "数値を入力してください。",
		needfullyManpowerNumber : "数値を入力してください。",
		profitRate: {
			number: "0から100まで入力してください",
			range: "0から100まで入力してください"
		},
        project_section_progress_current_contracttsubo: {
            number: "半角数字を入力してください",
            min: "坪数を0より入力してください",
		},
		project_contract_progress_contract_number_of_year: {
            number: "0以上の数字を入力してください",
            min: "0以上の数字を入力してください",
        },
        project_create_rent_contract_number_of_year_progress: {
            number: "0以上の数字を入力してください",
            min: "0以上の数字を入力してください",
        },
        project_section_progress_negotiation_contracttsubo: {
            number: "半角数字を入力してください",
            min: "坪数を0より入力してください",
        },
        project_section_progress_negotiation_building_expected_value: {
        	digits: "半角数字を入力してください",
        },
        salesPrediction: {
        	digits: "半角数字を入力してください",
        },
        project_create_implementation_schedule_datetime_year: {
            digits: "半角数字を入力してください",
            min: "年は1970以上入力してください",
        },
        project_create_implementation_schedule_datetime_month: {
            digits: "半角数字を入力してください",
            range: "月は1～12以内で入力してください。",
        },
        profitExpectation: {
            number: "0以上の数字を入力してください",
            min: "0以上の数字を入力してください",
        },
    	projectCategoryId: {
    		required: "必須項目です。",
        },
        project_create_i_sales_agency_contract_number_of_year_progress:{
        	number: "0以上の数字を入力してください",
            min: "0以上の数字を入力してください",
        },
        title:{
        	required: "必須項目です。",
        	maxlength: "案件名を256文字以内で入力してください",
        }
	},
	errorPlacement : function(error, element) {
		if (element.attr("name").substr(0, 13) === "openImageFile"
				|| element.attr("name").substr(0, 14) === "openRelatedDoc") {
			error.insertAfter($(element).closest('.input-group'));
		} else {
			error.insertAfter(element);
		}
	},
	ignore : ""
};
$.validator.addMethod("hasfile", function(value, element) {
	return (value != '');
}, "ファイルを選択してください。");
$.validator
.addMethod(
		'inspDateTime',
		function(value, element, param) {
			return this.optional(element)
					|| value.match(/^(-?(?:[1-9][0-9]*)?[0-9]{4})-(1[0-2]|0[1-9])-(3[01]|0[1-9]|[12][0-9]) (2[0-3]|[01][0-9]):([0-5][0-9])$/);
		}, "日付の形式を正しく入力してください。");
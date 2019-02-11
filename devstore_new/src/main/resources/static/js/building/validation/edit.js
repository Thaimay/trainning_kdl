let validation = {
	rules : {
		name : "required",
		openDate : "dateISO",
		closeDate : "dateISO",
		longitude : {
			betweenvalue : {
				param : [ -180, 180 ],
			}
		},
		latitude : {
			betweenvalue : {
				param : [ -85, 85 ],
			}
		},
		tenantNumber : {
			number : true
		},
	},
	messages : {
		name : "館名を入力してください。",
		openDate : "日付の形式を正しく入力してください。",
		closeDate : "日付の形式を正しく入力してください。",
		tenantNumber : "数値を入力してください。",
	},
	errorPlacement : function(error, element) {
		if (element.attr("name").substr(0, 13) === "openImageFile"
				|| element.attr("name").substr(0, 14) === "openRelatedDoc") {
			error.insertAfter($(element).closest('.input-group'));
			console.log(element);
		} else {
			error.insertAfter(element);
		}
	},
	ignore : ""
};
let validationAddBuildingSales = {
	rules : {
		"add-f-month" : {
			required : true,
			exactlength: 4,
		},
		"add-sales" : {
			number : true
		},
		"add-area" : {
			number : true
		},
		"add-month-basis" : {
			number : true
		},
	},
	messages : {
		"add-f-month" : {
			required: "決算年度を入力してください。"
		},
		"add-sales" : {
			number: "数値を入力してください。"
		},
		"add-area" : {
			number: "数値を入力してください。"
		},
		"add-month-basis" : {
			number: "数値を入力してください。"
		},
	},
	ignore : ""
};
$.validator.addMethod("hasfile", function(value, element) {
	return (value != '');
}, "ファイルを選択してください。");
$.validator
		.addMethod(
				'betweenvalue',
				function(value, element, param) {
					return this.optional(element)
							|| (parseFloat(value) >= parseFloat(param[0]) && parseFloat(value) <= parseFloat(param[1]));
				}, "値は{0}～{1}以内に入力してください。");
$.validator
.addMethod(
		'inspDateTime',
		function(value, element, param) {
			return this.optional(element)
					|| value.match(/^(-?(?:[1-9][0-9]*)?[0-9]{4})-(1[0-2]|0[1-9])-(3[01]|0[1-9]|[12][0-9]) (2[0-3]|[01][0-9]):([0-5][0-9])$/);
		}, "日付の形式を正しく入力してください。");
$.validator.addMethod("exactlength", function(value, element, param) {
	 return this.optional(element) || value.length == param;
	}, $.validator.format("{0}桁で入力してください。"));
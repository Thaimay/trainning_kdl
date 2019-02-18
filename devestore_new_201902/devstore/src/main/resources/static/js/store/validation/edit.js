let validation = {
	rules : {
		store_edit_rent_year: {
            number: true,
            min: 0,
        }
	},
	messages : {
		store_edit_rent_year: {
            number: "0以上の数字を入力してください",
            min: "0以上の数字を入力してください",
        }
	},
	errorPlacement : function(error, element) {
		if (element.attr("name").substr(0, 13) === "openImageFile") {
			error.insertAfter($(element).closest('.input-group'));
		} else {
			error.insertAfter(element);
		}
	},
	ignore : ""
}
$.validator.addMethod("hasfile", function(value, element) {
	return (value != '');
}, "ファイルを選択してください。");
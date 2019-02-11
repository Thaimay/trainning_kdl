var checkTimeOver = function(start, end) {
    var startHour = start[0];
    var endHour = end[0];
    var startTime = start[1];
    var endTime = end[1];
    var result = startHour <= endHour;
    if (startHour == endHour) {
        result = startTime <= endTime;
    }

    return result;
}

$.validator.addMethod("schedule_time_over", function(value, element) {
    var time = $("#schedule_start_time").val();
    var start = $("#schedule_start_time").val().split(":");
    var end = $(element).val().split(":");

    if (time === "") {
        return true;
    }
    return checkTimeOver(start, end);
});

$.validator.addMethod("implementation_time_over", function(value, element) {
    var time = $("#implementation_start_time").val();
    var start = time.split(":");
    var end = $(element).val().split(":");

    if (time === "") {
        return true;
    }
    return checkTimeOver(start, end);
});

$.validator.addMethod("negotiation_important_required", function(value, element) {
    var existsBuilding = $("#j-form-important-building .ms-sel-ctn .ms-sel-item").length > 0;
    var existsCorporation =  $("#j-form-important-corporation .ms-sel-ctn .ms-sel-item").length > 0;
    var existsEnd = $("#show-end-date").val() != "";
    var existsContent = $(".j-important-content").val() != "";

    if (existsBuilding || existsCorporation || existsEnd || existsContent) {
        return $(element).val();
    } else {
        return true;
    }
});

$.validator.addMethod("schedule_date_require", function(value, element) {
    var startTime = $(".j-scheduled-time-start").val();
    return value == "" || startTime != "";
});

$.validator.addMethod("schedule_time_require", function(value, element) {
    var scheduleDate = $(".j-scheduled-date").val();
    return value == "" || scheduleDate != "";
});

$.validator.addMethod("implementation_date_require", function(value, element) {
    var startTime = $(".j-implementation-time-start").val();
    return value == "" || startTime != "";
});

$.validator.addMethod("implementation_time_require", function(value, element) {
    var scheduleDate = $(".j-implementation-date").val();
    return value == "" || scheduleDate != "";
});

$.validator.addMethod("hasfile", function(value, element) {
	return (value != '');
}, "ファイルを選択してください。");

$.validator.setDefaults({
    ignore: []
});

var negotiationCreateScheduleValidate = function() {
    return {
    	validateNonVisibleFields: true,
        focusInvalid: false,
        rules: {
            schedule_date: {
                required: true,
                date: true,
            },
            place: {
                maxlength: 64,
            },
            schedule_end_time: {
                schedule_time_over: true,
            },
            title: {
                required: true,
            },
            division: {
                required: true,
            },
            "show-end-date": {
                date: true,
                negotiation_important_required: true,
            },
            "importantInformation.content": {
                negotiation_important_required: true,
            },
        },
        messages: {
            schedule_date: {
                required: "必須項目です。",
                date: "日付の形式で入力してください",
            },
            place: {
                maxlength: "最大文字数64を超えています。",
            },
            title: {
                required: "必須項目です。",
            },
            division: {
                required: "必須項目です。",
            },
            "show-end-date": {
                date: "日付の形式で入力してください",
                negotiation_important_required: "必須項目です。",
            },
            "importantInformation.content": {
                negotiation_important_required: "必須項目です。",
            },
            schedule_end_time: {
                schedule_time_over: "終了時間は開始時間より後に設定してください",
            },
        },
        errorPlacement: function(error, element) {
        	if (element.is(':radio')) {
        		error.appendTo(element.closest(".radio").parent());
        	} else {
                error.insertAfter(element);
        	}
        },
    };
};


var negotiationCreateImplementationValidate = function() {
    return {
    	validateNonVisibleFields: true,
        focusInvalid: false,
        rules: {
        	implementation_date: {
                required: true,
                date: true,
            },
            place: {
                maxlength: 64,
            },
            implementation_end_time: {
            	implementation_time_over: true,
            },
            title: {
                required: true,
                maxlength: 64,
            },
            division: {
                required: true,
            },
            "show-end-date": {
                date: true,
                negotiation_important_required: true,
            },
            "importantInformation.content": {
                negotiation_important_required: true,
            },
        },
        messages: {
        	implementation_date: {
                required: "必須項目です。",
                date: "日付の形式で入力してください",
            },
            place: {
                maxlength: "最大文字数64を超えています。"
            },
            title: {
                required: "必須項目です。"
            },
            content: {
                maxlength: "最大文字数10000を超えています。",
            },
            important_notice_input_content: {
                maxlength: "最大文字数10000を超えています。",
            },
            division: {
                required: "必須項目です。"
            },
            implementation_end_time: {
                implementation_time_over: "終了時間は開始時間より後に設定してください",
            },
            "show-end-date": {
                date: "日付の形式で入力してください",
                negotiation_important_required: "必須項目です。",
            },
            "importantInformation.content": {
                negotiation_important_required: "必須項目です。",
            },
        },
        errorPlacement: function(error, element) {
        	if (element.is(':radio')) {
        		error.appendTo(element.closest(".radio").parent());
        	} else {
                error.insertAfter(element);
        	}
        },
    };
};


var negotiationUpdateValidate = function() {
    return {
    	validateNonVisibleFields: true,
        focusInvalid: false,
        rules: {
            schedule_date: {
                date: true,
                schedule_date_require: true,
            },
            implementation_date: {
                date: true,
                implementation_date_require: true,
            },
            schedule_end_time: {
                schedule_time_require: true,
            },
            implementation_end_time: {
                implementation_time_require: true,
            },
            place: {
                maxlength: 64,
            },
            title: {
                required: true,
            },
            division: {
                required: true,
            },
            "show-end-date": {
                date: true,
                negotiation_important_required: true,
            },
            "importantInformation.content": {
                negotiation_important_required: true,
            },
        },
        messages: {
            schedule_date: {
                required: "必須項目です。",
                date: "日付の形式で入力してください",
                schedule_date_require: "時間を入力してください",
            },
            implementation_date: {
                date: "日付の形式で入力してください",
                implementation_date_require: "時間を入力してください",
            },
            place: {
                maxlength: "最大文字数64を超えています。"
            },
            title: {
                required: "必須項目です。"
            },
            content: {
                maxlength: "最大文字数10000を超えています。",
            },
            division: {
                required: "必須項目です。"
            },
            next_action: {
                maxlength: "最大文字数10000を超えています。",
            },
            important_notice_input_content: {
                maxlength: "最大文字数10000を超えています。",
            },
            schedule_end_time: {
                schedule_time_over: "終了時間は開始時間より後に設定してください",
                schedule_time_require: "日付を入力してください",
            },
            implementation_end_time: {
                implementation_time_over: "終了時間は開始時間より後に設定してください",
                implementation_time_require: "日付を入力してください",
            },
            "show-end-date": {
                date: "日付の形式で入力してください",
                negotiation_important_required: "必須項目です。",
            },
            "importantInformation.content": {
                negotiation_important_required: "必須項目です。",
            },
        },
        errorPlacement: function(error, element) {
        	if (element.is(':radio')) {
        		error.appendTo(element.closest(".radio").parent());
        	} else {
                error.insertAfter(element);
        	}
        },
    };
};

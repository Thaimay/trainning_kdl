$(function() {
	var $target = $("#negotiation-form");

//	magicSuggest.create($target.find(".j-form-meeting-corporation"), {
//		name: "corporationIds[]",
//		request: function(value, successHandler) {
//			// ajax処理 successHandlerにajaxの結果を渡してください
//			RequestObject.getListCorporation().then(function(data) {
//				successHandler(data);
//			})
//		}
//	});

//	magicSuggest.create($target.find(".j-form-visitor"), {
//		name: "",
//		request: function(value, successHandler) {
//			// ajax処理 successHandlerにajaxの結果を渡してください
//			successHandler([
//				"訪問者A",
//			]);
//		}
//	});
//
//	magicSuggest.create($target.find(".j-form-staff-interview"), {
//		name: "",
//		request: function(value, successHandler) {
//			// ajax処理 successHandlerにajaxの結果を渡してください
//			successHandler([
//				"面談者",
//			]);
//		}
//	});
//
//	magicSuggest.create($target.find(". j-form-negotiation-building"), {
//		name: "",
//		request: function(value, successHandler) {
//			// ajax処理 successHandlerにajaxの結果を渡してください
//			successHandler([
//				"面談先館",
//			]);
//		}
//	});


	// 商談一覧の表示切り替え
	$('#j-negotiation-tabs').find("a").on("click", function (e) {
		e.preventDefault()
		$(this).tab('show')
	})

	if ($("#jScheduledDate").length) {
		var $scheduled = $("#jScheduledDate");
		datepickerUtils.createDatePicker($scheduled.find(".j-scheduled-date"));
		datepickerUtils.createDatePicker($scheduled.find(".j-scheduled-date-start"));
		datepickerUtils.createDatePicker($scheduled.find(".j-scheduled-date-end"));

		var $startTimeTarget = $scheduled.find(".j-scheduled-time-start");
		var startTimeVal = null;
		datepickerUtils.createTimePicker($startTimeTarget, startTimeVal);

		var $endTimeTarget = $scheduled.find(".j-scheduled-time-end")
		var endTimeVal = null;
		datepickerUtils.createTimePicker($endTimeTarget, endTimeVal);
	}

	if ($("#jImplementationDate").length) {
		var $implementation = $("#jImplementationDate");
		datepickerUtils.createDatePicker($implementation.find(".j-implementation-date"));
		datepickerUtils.createDatePicker($implementation.find(".j-implementation-date-start"));
		datepickerUtils.createDatePicker($implementation.find(".j-implementation-date-end"));

		var $implementationStartTime = $implementation.find(".j-implementation-time-start");
		var implementationStartTimeVal = null;
		datepickerUtils.createTimePicker($implementationStartTime, implementationStartTimeVal);

		var $implementationEndTime = $implementation.find(".j-implementation-time-end");
		var implementationEndTimeVal = null;
		datepickerUtils.createTimePicker($implementationEndTime, implementationEndTimeVal);
	}

	if ($("#jGeneralImplementationDate").length) {
		var $implementation = $("#jGeneralImplementationDate");
		datepickerUtils.createDatePicker($implementation.find(".j-implementation-date"));
	}

	if ($('.j-open-date-picker').length) {
		datepickerUtils.createDatePicker($('.j-open-date-picker').find('input'));
	}

	if ($('.j-close-date-picker').length) {
		datepickerUtils.createDatePicker($('.j-close-date-picker').find('input'));
	}

	if ($('.j-form-schedule-activation-date').length) {
		datepickerUtils.createDatePicker($('.j-form-schedule-activation-date').find('input'));
	}


});

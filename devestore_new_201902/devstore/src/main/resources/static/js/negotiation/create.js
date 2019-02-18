var negotiationCreate = {
	onSubmit: function(e) {
		console.log("submit");
		var scheduleStartDatetime = datepickerUtils.combineDateTime($(".j-scheduled-date").val(), $(".j-scheduled-time-start").val());
		var scheduleEndDatetime = datepickerUtils.combineDateTime($(".j-scheduled-date").val(), $(".j-scheduled-time-end").val());

		$("#schedule-start-datetime").val(scheduleStartDatetime);
		$("#schedule-end-datetime").val(scheduleEndDatetime);
		
		e.preventDefault();
	},
};

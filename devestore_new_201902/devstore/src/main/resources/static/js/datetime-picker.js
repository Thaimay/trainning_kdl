var datepickerUtils = {
	createDatePicker: function(target) {
		$.datepicker._gotoToday = function(id) {
		    var target = $(id);
			var inst = this._getInst(target[0]);
			var date = new Date();
			this._setDate(inst,date);
			this._hideDatepicker();
		};

		var datepicker = target.datepicker({
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

		return datepicker;
	},
	createTimePicker: function(target, defaultTime) {
		target.timepicker({
			'timeFormat': 'H:i',
		});
		var value = target.val();

		switch (defaultTime) {
			case "":
				break;
			case null:
				target.val("");
				if (value != null) {
					target.val(value);
				}
				break;
			default:
				target.val(defaultTime);
		}
	},
	combineDateTime: function(date, time) {
		return date + " " + time;
	},
};

$(function(){
    var old_fn = $.datepicker._updateDatepicker;
    $.datepicker._updateDatepicker = function(inst) {
      old_fn.call(this, inst);
      var buttonPane = $(this).datepicker("widget").find(".ui-datepicker-buttonpane");
      var buttonHtml = "<button type='button' class='ui-datepicker-clean ui-state-default ui-priority-primary ui-corner-all'>クリア</button>";
      $(buttonHtml).appendTo(buttonPane).click(
        function(ev) {
          $.datepicker._clearDate(inst.input);
			});
			$('.ui-datepicker-current').remove()  // Today ボタンを削除
    }
  });
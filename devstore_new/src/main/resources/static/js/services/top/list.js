'use strict';

/*
 * Contains a service to communicate with API
 */
angular
    .module('app.services')
    .factory('TopListService', topListService);

function topListService($http, BaseService) {

  function settingDatePicker() {
    var $target = $("#j-date-select");
    createDatePicker($target);
  };

  return {
    settingDatePicker: settingDatePicker,
  };
}


/**
 * 日付・時間入力補助
 */
var dateFormat = "yy-mm-dd";
var timeFormat = "H:i";

var createDatePicker = function ($target) {
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
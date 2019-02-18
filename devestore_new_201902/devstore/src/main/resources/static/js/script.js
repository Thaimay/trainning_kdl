$(function() {
  /**
   *  @desc サイドバーの表示/非表示を変更する処理
   */
  var $globalHeader = $("#global-header");
  var $hamburgerBtn = $("#j-sidebar-opener");
  var $sidebar = $("#j-sidebar");
  var $mainContents = $("#mainContents");
  var sidebarIsOpen = true;

  var setSidebarHeight = function() {
    var sidebarHeight = $(document).height() - $globalHeader.outerHeight();
    $sidebar.css({height: sidebarHeight});
  };
  setSidebarHeight();

  $(window).on({
    'scroll': function() {
      setSidebarHeight();
    },
    'resize': function() {
      setSidebarHeight();
    }
  })

  if(!localStorage.getItem("sidebarIsOpen")){
    localStorage.setItem("sidebarIsOpen", "true")
  }
  var allowSideBardOpen = localStorage.getItem("sidebarIsOpen");
  if(allowSideBardOpen == 'true'){
    $sidebar.addClass("is-show");
    $mainContents.addClass("is-sidebar-show");
    sidebarIsOpen = true;
  }else{
    $sidebar.removeClass("is-show");
    $mainContents.removeClass("is-sidebar-show");
    sidebarIsOpen = false;
  }
  if( window.location.href.indexOf("login")>0 ){
    $sidebar.removeClass("is-show");
    $mainContents.removeClass("is-sidebar-show");
  }
  $hamburgerBtn.on({
    'click': function(event) {
      if (sidebarIsOpen) {
        $sidebar.removeClass("is-show");
        $mainContents.removeClass("is-sidebar-show");
        sidebarIsOpen = false;
        localStorage.setItem("sidebarIsOpen", sidebarIsOpen.toString());
      } else {
        $sidebar.addClass("is-show");
        $mainContents.addClass("is-sidebar-show");
        sidebarIsOpen = true;
        localStorage.setItem("sidebarIsOpen", sidebarIsOpen.toString());
      }
    }
  });


  // 商談一覧の表示切り替え
  $('#j-negotiation-tabs').find("a").on("click", function (e) {
    e.preventDefault()
    $(this).tab('show')
  })

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

  var createTimePicker = function ($target) {
    $target.timepicker({
      timeFormat: timeFormat
    });
  }
  if ($("#jScheduledDate").length) {
    var $scheduled = $("#jScheduledDate");
    createDatePicker($scheduled.find(".j-scheduled-date"));
    createDatePicker($scheduled.find(".j-scheduled-date-start"));
    createDatePicker($scheduled.find(".j-scheduled-date-end"));

    //createTimePicker($scheduled.find(".j-scheduled-time-start"));
    //createTimePicker($scheduled.find(".j-scheduled-time-end"));
  }

  if ($("#jImplementationDate").length) {
    var $implementation = $("#jImplementationDate");
    createDatePicker($implementation.find(".j-implementation-date"));
    createDatePicker($implementation.find(".j-implementation-date-start"));
    createDatePicker($implementation.find(".j-implementation-date-end"));

    //createTimePicker($implementation.find(".j-implementation-time-start"));
    //createTimePicker($implementation.find(".j-implementation-time-end"));
  }

  if ($("#jGeneralImplementationDate").length) {
    var $implementation = $("#jGeneralImplementationDate");
    createDatePicker($implementation.find(".j-implementation-date"));
  }

  if ($('.j-open-date-start').length) {
    createDatePicker($('.j-open-date-start').find('input'));
  }

  if ($('.j-open-date-end').length) {
    createDatePicker($('.j-open-date-end').find('input'));
  }

  if ($('.j-close-date-start').length) {
    createDatePicker($('.j-close-date-start').find('input'));
  }

  if ($('.j-close-date-end').length) {
    createDatePicker($('.j-close-date-end').find('input'));
  }

  if ($('.j-form-expected-day-start').length) {
    createDatePicker($('.j-form-expected-day-start').find('input'));
  }

  if ($('.j-form-expected-day-end').length) {
    createDatePicker($('.j-form-expected-day-end').find('input'));
  }

  if ($('.j-open-date').length) {
		createDatePicker($('.j-open-date').find('input'));
	}

  if ($('.j-close-date').length) {
		createDatePicker($('.j-close-date').find('input'));
	}

  /**
   * @desc 商談登録時の流入経路によって入力項目を制限する
   */
  (function () {

    if(window.location.search === "") return;

    var lawQuerys = window.location.search.split("?")[1].split("&");
    var spliteQueries = {};
    lawQuerys.forEach(function(v, i) {
        var lawQuerys = v.split("=");
        spliteQueries[lawQuerys[0]] = lawQuerys[1];
    });

    switch (spliteQueries.division){
      case "schedule":
        $(".j-disable-form-item.implementation").addClass("isDisabled");
        break;
      case "report":
        $(".j-disable-form-item.scheduled").addClass("isDisabled");
        break;
      default:
        console.warn("defined division");
        break;
    }
  }());

  if ($('.j-dropdown').length) {
    var $dropdown = $('.j-dropdown');

    $dropdown.find('.j-dropdown-header').on({
      'click': function(event) {
        $target = $(event.currentTarget);

        if ($target.hasClass('isOpen')) {
          handleDropDownState($target, false);
        } else {
          handleDropDownState($target, true);
        }
      }
    })
    
    $dropdown.find('.j-dropdown-header').find('.j-dropdown-cancel-collapse').on({
      'click': function(event) {
    	  event.stopPropagation();
      }
    })

    /**
     * @description init dropdown state
     */
    $('.j-dropdown-header.isOpen').next('.j-dropdown-body').show();

    var handleDropDownState = function ($target, doOpen) {
      if (doOpen) {
        $target.addClass('isOpen');
        $target.next('.j-dropdown-body').slideDown();
      } else {
        $target.removeClass('isOpen');
        $target.next('.j-dropdown-body').slideUp();
      }
    }
  }
  
  // 案件検索ボタンのスクロール追従
  if ($('[data-j-sticky-area]').length && $('[data-j-sticky-item]').length) {
	  var $stickyItem = $('[data-j-sticky-item]');

	  function checkScrollPosition() {
		  var scrollPosition = $(window).scrollTop();
		  var stickyPosition = $('[data-j-sticky-area]').offset();
		  
		  if (scrollPosition >= stickyPosition.top) {
			  $stickyItem.addClass('isFixed').css({'left': stickyPosition.left});
		  } else {
			  $stickyItem.removeClass('isFixed');
		  }
	  }
	  
	  $(window).on({
		  'scroll': function() {
			  checkScrollPosition();
		  }
	  });
  }
});
function removeSearchStorage() {
	// remove local storage
	localStorage.removeItem("project_search_form");
}
function hideSpinner() {
	$('#spinner').hide();
}
if (!Array.from) {
	  Array.from = (function () {
	    var toStr = Object.prototype.toString;
	    var isCallable = function (fn) {
	      return typeof fn === 'function' || toStr.call(fn) === '[object Function]';
	    };
	    var toInteger = function (value) {
	      var number = Number(value);
	      if (isNaN(number)) { return 0; }
	      if (number === 0 || !isFinite(number)) { return number; }
	      return (number > 0 ? 1 : -1) * Math.floor(Math.abs(number));
	    };
	    var maxSafeInteger = Math.pow(2, 53) - 1;
	    var toLength = function (value) {
	      var len = toInteger(value);
	      return Math.min(Math.max(len, 0), maxSafeInteger);
	    };

	    // The length property of the from method is 1.
	    return function from(arrayLike/*, mapFn, thisArg */) {
	      // 1. Let C be the this value.
	      var C = this;

	      // 2. Let items be ToObject(arrayLike).
	      var items = Object(arrayLike);

	      // 3. ReturnIfAbrupt(items).
	      if (arrayLike == null) {
	        throw new TypeError("Array.from requires an array-like object - not null or undefined");
	      }

	      // 4. If mapfn is undefined, then let mapping be false.
	      var mapFn = arguments.length > 1 ? arguments[1] : void undefined;
	      var T;
	      if (typeof mapFn !== 'undefined') {
	        // 5. else
	        // 5. a If IsCallable(mapfn) is false, throw a TypeError exception.
	        if (!isCallable(mapFn)) {
	          throw new TypeError('Array.from: when provided, the second argument must be a function');
	        }

	        // 5. b. If thisArg was supplied, let T be thisArg; else let T be undefined.
	        if (arguments.length > 2) {
	          T = arguments[2];
	        }
	      }

	      // 10. Let lenValue be Get(items, "length").
	      // 11. Let len be ToLength(lenValue).
	      var len = toLength(items.length);

	      // 13. If IsConstructor(C) is true, then
	      // 13. a. Let A be the result of calling the [[Construct]] internal method of C with an argument list containing the single item len.
	      // 14. a. Else, Let A be ArrayCreate(len).
	      var A = isCallable(C) ? Object(new C(len)) : new Array(len);

	      // 16. Let k be 0.
	      var k = 0;
	      // 17. Repeat, while k < len… (also steps a - h)
	      var kValue;
	      while (k < len) {
	        kValue = items[k];
	        if (mapFn) {
	          A[k] = typeof T === 'undefined' ? mapFn(kValue, k) : mapFn.call(T, kValue, k);
	        } else {
	          A[k] = kValue;
	        }
	        k += 1;
	      }
	      // 18. Let putStatus be Put(A, "length", len, true).
	      A.length = len;
	      // 20. Return A.
	      return A;
	    };
	  }());
	}

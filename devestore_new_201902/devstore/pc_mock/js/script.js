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
    'resize': function() {
      setSidebarHeight();
    }
  })

  $hamburgerBtn.on({
    'click': function(event) {
      if (sidebarIsOpen) {
        $sidebar.removeClass("is-show");
        $mainContents.removeClass("is-sidebar-show");
        sidebarIsOpen = false;
      } else {
        $sidebar.addClass("is-show");
        $mainContents.addClass("is-sidebar-show");
        sidebarIsOpen = true;
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
  var timeFormat = "H:i:s";

  var createDatePicker = function ($target) {
    $target.datepicker({
      dateFormat: dateFormat
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

    createTimePicker($scheduled.find(".j-scheduled-time-start"));
    createTimePicker($scheduled.find(".j-scheduled-time-end"));
  }

  if ($("#jImplementationDate").length) {
    var $implementation = $("#jImplementationDate");
    createDatePicker($implementation.find(".j-implementation-date"));
    createDatePicker($implementation.find(".j-implementation-date-start"));
    createDatePicker($implementation.find(".j-implementation-date-end"));

    createTimePicker($implementation.find(".j-implementation-time-start"));
    createTimePicker($implementation.find(".j-implementation-time-end"));
  }

  if ($("#jGeneralImplementationDate").length) {
    var $implementation = $("#jGeneralImplementationDate");
    createDatePicker($implementation.find(".j-implementation-date"));
  }

  if ($('.j-open-date-picker').length) {
    createDatePicker($('.j-open-date-picker').find('input'));
  }

  if ($('.j-close-date-picker').length) {
    createDatePicker($('.j-close-date-picker').find('input'));
  }

  if ($('.j-form-schedule-activation-date').length) {
    createDatePicker($('.j-form-schedule-activation-date').find('input'));
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
});

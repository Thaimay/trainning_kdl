angular
.module('app.core')
.controller('ProjectEditController', function (ProjectBaseService, $scope, $filter, $timeout) {
  var vm = this;
  vm.listActionStatus = [];
  vm.mProjectActionStatusId = null;
  vm.mProjectActionStatus = {};
  vm.projectId = null;
  $scope.scheduleList = [];
  $scope.itemControl = [];

  vm.initData = function () {
    vm.listActionStatus = [{}];
    vm.projectId = $("#project_edit_project_id").val();
    vm.mProjectActionStatusId = $("#project_edit_m_project_action_status_id").val();

    $("#project_edit_project_category").change(function (e) {
      e.preventDefault();
      vm.updateSchedule();
      vm.findActionStatus();
      vm.setItemControl();

      $timeout(function () {
        vm.actionStatus();

      }, 1000);
    });

    vm.findActionStatus();

    $timeout(function () {
      vm.actionStatus();
    }, 1000);

    $("#project_edit_sales_channel").change(function (e) {
      e.preventDefault();
      vm.updateSchedule();
      vm.findActionStatus();

      $timeout(function () {
        vm.actionStatus();
      }, 1000);
    });

    $("#project_edit_implementation_datetime").change(function (e) {
      e.preventDefault();
      vm.updateSchedule();
      vm.findActionStatus();
      vm.actionStatus();
    });

    $("#start-datetime").change(function (e) {
      e.preventDefault();
      vm.actionStatus();
    });

    var categoryId = $("#project_edit_project_category").val();
    var channelId = $("#project_edit_sales_channel").val();
    ProjectBaseService.findProjectActionStatus(categoryId, channelId).then(function (data) {
      vm.listActionStatus = data;
    }).then(function (data) {
      vm.setActionStatus();
    })

    ProjectBaseService.findProjectSchedule(vm.projectId).then(function (data) {
      $scope.scheduleList = data;
      $timeout(function () {
        $(".schedule-datepicker").each(function (e, el) {
          datepickerUtils.createDatePicker($(el));
        });
      }, 1000);
    });

    $("#project_edit_office_project_progress").change(function (e) {
      e.preventDefault();
      vm.actionStatus();
    });

    $("#project_edit_negotiation_project_progress").change(function (e) {
      e.preventDefault();
      vm.actionStatus();
    });

    $("#project_edit_progress_negotiation_section").focusout(function (e) {
      e.preventDefault();
      vm.actionStatus();
    });

    $("#edit_project_section_progress_negotiation_contracttsubo").focusout(function (e) {
      e.preventDefault();
      vm.actionStatus();
    });

    $("#project_edit_article_review_result_id").change(function (e) {
      e.preventDefault();
      vm.actionStatus();
    });

    $("#project_edit_management_result_id").change(function (e) {
      e.preventDefault();
      vm.actionStatus();
    });

    $("#project_edit_investment_process_result_id").change(function (e) {
      e.preventDefault();
      vm.actionStatus();
    });
    $timeout(function () {
      $("#project_edit_implementation_schedule_datetime_year").focusout(function (e) {
        e.preventDefault();
        vm.updateSchedule();
        vm.actionStatus();
      });
      $("#project_edit_implementation_schedule_datetime_month").focusout(function (e) {
        e.preventDefault();
        vm.updateSchedule();
        vm.actionStatus();
      });
      $("#project_edit_implementation_schedule_datetime_day").change(function (e) {
        e.preventDefault();
        vm.updateSchedule();
        vm.actionStatus();
      });
    }, 1000);

    vm.setItemControl();

    ProjectBaseService.handlePageTopBtn();
  }

  vm.setItemControl = function () {
    ProjectBaseService.findProjectSwitingItemControl($("#project_edit_project_category").val()).then(function (data) {
      $scope.itemControl = data;
    })
  }

  vm.findActionStatus = function () {
	var categoryId = $("#project_edit_project_category").val();
    var channelId = null;

	if(refBuilding){
	  channelId = refBuildingData.building.iSalesChannelId;
	} else {
	  channelId = $("#project_edit_sales_channel").val();
	}

    if (categoryId && channelId) {
      ProjectBaseService.findProjectActionStatus(categoryId, channelId).then(function(data) {
        vm.listActionStatus = data;
      });
    } else {
      vm.listActionStatus = [];
    }
  }

  vm.setActionStatus = function() {
  	vm.listActionStatus.forEach(function(data) {
  		if (data.id == vm.mProjectActionStatusId) {
  			vm.mProjectActionStatus = {
  					id: data.id,
  					name: data.name
  			}
  		}
  	});
  }

  vm.actionStatus = function() {
	  var iSalesChannelId = null;
      var projectSectionProgressDto = [];
      var implementationDate = $("#project_edit_implementation_datetime").val();
      getDataForSectionProgressNegotiation();

      if (!implementationDate) {
		var implementationScheduleDatetimeYear = $("#project_edit_implementation_schedule_datetime_year").val();
		var implementationScheduleDatetimeMonth = $("#project_edit_implementation_schedule_datetime_month").val();
		var implementationScheduleDatetimeDay =  $("#project_edit_implementation_schedule_datetime_day").val();
		if(implementationScheduleDatetimeYear != '' && implementationScheduleDatetimeMonth != '' && implementationScheduleDatetimeDay != ''){
		  implementationDate = implementationScheduleDatetimeYear + "-" + ("0" + implementationScheduleDatetimeMonth).slice(-2) + "-" + implementationScheduleDatetimeDay;
	    }
      }
      
      if(!checkProperties(projectSectionProgressNegotiation) && !isNaN(projectSectionProgressNegotiation.contractTsubo)){
      	projectSectionProgressNegotiation.category = "NEGOTIATION";
      	projectSectionProgressDto.push(projectSectionProgressNegotiation);
      }

	  if(refBuilding){
		  iSalesChannelId = refBuildingData.building.iSalesChannelId;
	  } else {
		  iSalesChannelId = $("#project_edit_sales_channel").val();
	  }

    if($("#project_edit_project_category").val() === '' || iSalesChannelId== null || iSalesChannelId === ''){
      return;
    }

	  var data = {
          "startDate": $("#start-datetime").val(),
          "mProjectActionStatusId": $("#project-edit-action-status").val(),
          "articleReviewResultId": $("#project_edit_article_review_result_id").val(),
          "managementResultId": $("#project_edit_management_result_id").val(),
          "projectCategoryId": $("#project_edit_project_category").val(),
          "iSalesChannelId": iSalesChannelId,
          "implementationDatetime": implementationDate,
          "investmentProcessResultId": $("#project_edit_investment_process_result_id").val(),
          "officeProjectProgressId": $("#project_edit_office_project_progress").val(),
          "negotiationProjectProgressId": $("#project_edit_negotiation_project_progress").val(),
          "projectSectionProgressDto": projectSectionProgressDto,
          "investmentDiscussionTarget": $("#projectEditInvestmentDiscussionTarget").is(':checked'),
      };

      ProjectBaseService.actionStatus(data).then(function(response) {
    	  $("#project_edit_office_project_progress").val(response.officeProjectProgressId);
          $("#project_edit_negotiation_project_progress").val(response.negotiationProjectProgressId);
          vm.mProjectActionStatusId = response.mprojectActionStatusId;
          vm.setActionStatus();
      });
  }

  vm.updateSchedule = function() {
	  var categoryId = $("#project_edit_project_category").val();
	  var channelId = null;
	  var date = $("#project_edit_implementation_datetime").val();

      if(refBuilding){
	    channelId = refBuildingData.building.iSalesChannelId;
	  } else {
	    channelId = $("#project_edit_sales_channel").val();
	  }
      
      var implementationScheduleDatetimeYear = $("#project_edit_implementation_schedule_datetime_year").val();
      var implementationScheduleDatetimeMonth = $("#project_edit_implementation_schedule_datetime_month").val();
      var implementationScheduleDatetimeDay = $("#project_edit_implementation_schedule_datetime_day").val();
      
      if (!date) {
          if(implementationScheduleDatetimeYear != '' && implementationScheduleDatetimeYear != undefined &&
              implementationScheduleDatetimeMonth != '' && implementationScheduleDatetimeMonth != undefined &&
              implementationScheduleDatetimeDay != '' && implementationScheduleDatetimeDay != undefined)
              {
              let dateTmp = implementationScheduleDatetimeYear + "-" + ("0" + implementationScheduleDatetimeMonth).slice(-2) + "-" + implementationScheduleDatetimeDay;
              if(implementationScheduleDatetimeMonth >= 1 && implementationScheduleDatetimeMonth <= 12 &&
                  implementationScheduleDatetimeYear >= 1970 ){
                  date = dateTmp;
              }
          }
      }
      if (date && categoryId && channelId) {
    	var dates = date.split("-");
    	var year = dates[0];
    	var month = dates[1];
    	var day = dates[2];

        ProjectBaseService.findInitProjectSchedule(categoryId, channelId, year, month, day).then(function(data) {
          $scope.scheduleList = data;
        });
      } else {
    	var promise = ProjectBaseService.findInitSchedule(categoryId, channelId)

        if (promise != null) {
          promise.then(function(data) {
            $scope.scheduleList = data;

          });
        } else {
          $scope.scheduleList = [];
        }
      }

      $timeout(function() {
          $(".schedule-datepicker").each(function(e, el) {
        	  datepickerUtils.createDatePicker($(el));
          });
      }, 1000);
    }

    if($( "input[name='open']:checked").val()== true|| $( "input[name='open']:checked").val()== null){
      $("#open").html(" 公開")
    }else{
      $("#open").html("非公開")
    }
    $( "input[name='open']" ).change(function() {
      if($( "input[name='open']:checked").val()== true|| $( "input[name='open']:checked").val()== null){
        $("#open").html("公開 ")
      }else{
        $("#open").html("非公開 ")
      }
    });
    
    vm.toScrollTop = function() {
      ProjectBaseService.scrollToTop();
    }
});

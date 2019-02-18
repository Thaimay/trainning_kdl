angular
.module('app.core')
.controller('ProjectCreateController', function (ProjectBaseService, $scope, $filter, $timeout) {
  var vm = this;
  vm.year=$("#project-contract-progress-contract-number-of-year-hidden").val();
  vm.listActionStatus = [];
  vm.mProjectActionStatus = {};
    vm.projectProgress = {
      numberOfYear: "",
      endDate: ""
    };
  $scope.itemControl = {};
  $scope.scheduleList = [];

  vm.initData = function () {
    vm.listActionStatus = [{}];

    $("#project-create-category").change(function(e) {
      e.preventDefault();
      vm.updateSchedule();
      vm.findActionStatus();
      vm.actionStatus();
      vm.setItemControl();
    });

    $("#j-form-sales-channel").change(function(e) {
      e.preventDefault();
      vm.updateSchedule();
      vm.findActionStatus();
      vm.actionStatus();
    });

    $("#project_create_implementation_datetime").change(function(e) {
    	e.preventDefault();
        vm.findActionStatus();
    	vm.updateSchedule();
    });

    $timeout(function () {
    	$("#project_create_implementation_schedule_datetime_year").focusout(function(e) {
    		e.preventDefault();
    		vm.updateSchedule();
            vm.findActionStatus();
    		vm.actionStatus();
        });
        $("#project_create_implementation_schedule_datetime_month").focusout(function(e) {
        	e.preventDefault();
    		vm.updateSchedule();
            vm.findActionStatus();
    		vm.actionStatus();
        });
        $("#project_create_implementation_schedule_datetime_day").change(function (e) {
        	e.preventDefault();
    		vm.updateSchedule();
            vm.findActionStatus();
    		vm.actionStatus();
        });
    }, 1000);

	if ($('.j-form-startdate-time').length) {
		datepickerUtils.createDatePicker($('.j-form-startdate-time').find('input'));
		$("#start-datetime").datepicker("setDate", new Date());
		$('.j-form-startdate-time').find('input').change(function(e) {
		   vm.actionStatus();
		});
	}

	vm.setItemControl();
	ProjectBaseService.handlePageTopBtn();
  }

  vm.changeStartTime= function(){
    vm.changeNumberOfYear();
  }

    vm.changeEndTime = function () {
      let startDate = Date.parse($("#project-contract-progress-start-date").val(), "Y-m-d")
      let endDate = Date.parse($("#project-contract-progress-end-date").val(), "Y-m-d");
      if (!startDate || !endDate) {
        return;
      }
      diff = (endDate - startDate);
      let diffDay = Math.ceil(diff / (1000 * 3600 * 24));

      $("#project-contract-progress-contract-number-of-year").val(Math.round((diffDay / 365)*100)/100);
      vm.projectProgress.numberOfYear = diffDay / 365;
      vm.year= diffDay / 365;
      $("#project-contract-progress-contract-number-of-year-hidden").val(diffDay / 365)
    };

    vm.changeNumberOfYear = function () {
      let startDate = $("#project-contract-progress-start-date").val();
      let year = Number(vm.projectProgress.numberOfYear);
      if (!startDate) {
        return;
      }
      if (!year || !$.isNumeric(year) || year<0) {
        $("#project-contract-progress-end-date").val("");
        return;
      }
      let diffDate = year * 365;
      let endDate = new Date(startDate);
      endDate.setDate(endDate.getDate() + diffDate);
      $("#project-contract-progress-end-date").val(endDate.getFullYear() + "-" +("0" + (endDate.getMonth()+1)).slice(-2)  + "-" +("0" + endDate.getDate()).slice(-2) )
      $("#project-contract-progress-contract-number-of-year-hidden").val($("#project-contract-progress-contract-number-of-year").val());
    };

    $("#project-create-progress-company").change(function (e) {
      e.preventDefault();
      vm.actionStatus();
    });

    $("#project-create-progress-negotiation").change(function(e) {
      e.preventDefault();
      vm.actionStatus();
    });

    $("#project-section-progress-negotiation-section").focusout(function(e) {
      e.preventDefault();
      vm.actionStatus();
    });

    $("#project-section-progress-negotiation-contractTsubo").focusout(function(e) {
      e.preventDefault();
      vm.actionStatus();
    });

    $("#article-review-result-id").change(function(e) {
      e.preventDefault();
      vm.actionStatus();
    });

    $("#management-result-id").change(function(e) {
      e.preventDefault();
      vm.actionStatus();
    });

    $("#investment-process-result-id").change(function(e) {
      e.preventDefault();
      vm.actionStatus();
    });

  vm.findActionStatus = function () {
    var categoryId = $("#project-create-category").val();
    var channelId = $("#j-form-sales-channel").val();
    if (isBuilding && buildingData.building !== undefined && buildingData.building.iSalesChannelId) {
    	channelId = buildingData.building.iSalesChannelId
	}

    if (categoryId && channelId) {
      ProjectBaseService.findProjectActionStatus(categoryId, channelId).then(function(data) {
        vm.listActionStatus = data;
      }).then(function(data) {
    	  vm.actionStatus();
      });
    } else {
      vm.listActionStatus = [];
    }
  }

  vm.actionStatus = function() {
	  var iSalesChannelId = null;
      var projectSectionProgressDto = [];
      var implementationDate = $("#project_create_implementation_datetime").val();
      getDataForSectionProgressNegotiation();

      if (!implementationDate) {
		var implementationScheduleDatetimeYear = $("#project_create_implementation_schedule_datetime_year").val();
		var implementationScheduleDatetimeMonth = $("#project_create_implementation_schedule_datetime_month").val();
		var implementationScheduleDatetimeDay = $("#project_create_implementation_schedule_datetime_day").val();

		if(implementationScheduleDatetimeYear != '' && implementationScheduleDatetimeMonth != '' && implementationScheduleDatetimeDay != ''){
			implementationDate = implementationScheduleDatetimeYear + "-" + ("0" + implementationScheduleDatetimeMonth).slice(-2) + "-" + implementationScheduleDatetimeDay;
        }
      }
      
      if(!checkProperties(projectSectionProgressNegotiation) && !isNaN(projectSectionProgressNegotiation.contractTsubo)){
      	projectSectionProgressNegotiation.category = "NEGOTIATION";
      	projectSectionProgressDto.push(projectSectionProgressNegotiation);
      }

	  if(isBuilding){
		  iSalesChannelId = buildingData.building.iSalesChannelId;
	  } else {
		  iSalesChannelId = $("#j-form-sales-channel").val();
	  }

	  if (iSalesChannelId === null || iSalesChannelId === "" || $("#project-create-category").val() === "") {
		vm.mProjectActionStatus.id = "";
		vm.mProjectActionStatus.name = "";
		return;
	  }

      var data = {
          "startDate": $("#start-datetime").val(),
          "implementationDatetime": implementationDate,
          "mProjectActionStatusId": $("#project-create-action-status").val(),
          "articleReviewResultId": $("#article-review-result-id").val(),
          "managementResultId": $("#management-result-id").val(),
          "projectCategoryId": $("#project-create-category").val(),
          "iSalesChannelId": iSalesChannelId,
          "investmentProcessResultId": $("#investment-process-result-id").val(),
          "negotiationProjectProgressId": $("#investment-process-result-id").val(),
          "officeProjectProgressId": $("#project-create-progress-company").val(),
          "negotiationProjectProgressId": $("#project-create-progress-negotiation").val(),
          "investmentDiscussionTarget": $("#investmentDiscussionTarget").is(':checked'),
          "projectSectionProgressDto": projectSectionProgressDto
      };
      ProjectBaseService.actionStatus(data).then(function(response) {
    	  $("#project-create-progress-company").val(response.officeProjectProgressId);
          $("#project-create-progress-negotiation").val(response.negotiationProjectProgressId);
          vm.mProjectActionStatus.id = response.mprojectActionStatusId;
          vm.setActionStatus();
      });
  }

  vm.setActionStatus = function() {
  	vm.listActionStatus.forEach(function(data) {
  		if (data.id == vm.mProjectActionStatus.id) {
  			vm.mProjectActionStatus = {
  					id: data.id,
  					name: data.name
  			}
  		}
  	});
  }

  vm.setItemControl = function() {
	  ProjectBaseService.findProjectSwitingItemControl($("#project-create-category").val()).then(function(data) {
		  $scope.itemControl = data;
	  })
  }

  vm.updateSchedule = function() {
      var categoryId = $("#project-create-category").val();
      var channelId = $("#j-form-sales-channel").val();
      var date = $("#project_create_implementation_datetime").val();
      
      if(isBuilding){
    	  channelId = buildingData.building.iSalesChannelId;
	  } else {
		  channelId = $("#j-form-sales-channel").val();
	  }

      if (!date) {
          if(vm.implementationScheduleDatetimeYear != '' && vm.implementationScheduleDatetimeYear != undefined &&
              vm.implementationScheduleDatetimeMonth != '' && vm.implementationScheduleDatetimeMonth != undefined &&
              vm.implementationScheduleDatetimeDay != '' && vm.implementationScheduleDatetimeDay != undefined)
              {
              let dateTmp = vm.implementationScheduleDatetimeYear + "-" + ("0" + vm.implementationScheduleDatetimeMonth).slice(-2) + "-" + vm.implementationScheduleDatetimeDay;
              if(vm.implementationScheduleDatetimeMonth >= 1 && vm.implementationScheduleDatetimeMonth <= 12 &&
                  vm.implementationScheduleDatetimeYear >= 1970 ){
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

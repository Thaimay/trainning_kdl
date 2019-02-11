'use strict';

angular
  .module('app.core')
  .controller('NegotiationCreateController', function (NegotiationFormBase, NegotiationSearchService, $scope) {
    var self = this;
    var $targetForm = $('#negotiation-form');
    var curNotificationValue = [];
    var curVisitorValue = [];
    var visitorAdd = false;
    
    var curBuildingValue = [];
    var curProjectValue = [];

    this.initSuggest = function () {
      self.suggestProject = this.createSuggestProject($('.j-form-meeting-project'), 'projectIds', NegotiationSearchService.findProject);
      self.suggestCorporation = this.createSuggest($('.j-form-meeting-corporation'), 'corporationIds', NegotiationSearchService.findCorporation);
      self.suggestBuildingInterview = this.createSuggest($('.j-meeting-building'), 'buildingIds', NegotiationSearchService.findBuilding);
      let suggestVisitor = this.createSuggest($('.j-form-visitor'), 'accountIds', NegotiationSearchService.findAccount);
      this.createSuggest($('.j-form-staff-interview'), 'interviewIds', NegotiationSearchService.findPartner);
      //this.createSuggest($('.j-form-negotiation-building'), 'buildingIds', NegotiationSearchService.findBuilding);
      self.suggestBrand = this.createSuggest($('.j-form-brand'), 'brandIds', NegotiationSearchService.findBrand);
  	  this.createSuggest($(".j-form-business-card"), "bussinessCardIds", NegotiationSearchService.findPartner);
  	  let suggestNotification = this.createNoticeSuggest($(".j-form-notification"), "noticeIds", NegotiationSearchService.findAccount);
      this.createSuggest($('.j-form-important-building'), 'importantInformation.buildingIds', NegotiationSearchService.findBuilding);
      this.createSuggest($('.j-form-important-corporation'), 'importantInformation.corporationIds', NegotiationSearchService.findCorporation);
      datepickerUtils.createDatePicker($('.j-show-start-date'));
      datepickerUtils.createDatePicker($('.j-show-end-date'));

      datepickerUtils.createTimePicker($(".j-scheduled-time-start"), "09:00");
      datepickerUtils.createTimePicker($(".j-scheduled-time-end"), "10:00");

      datepickerUtils.createTimePicker($(".j-implementation-time-start"), "09:00");
      datepickerUtils.createTimePicker($(".j-implementation-time-end"), "10:00");

      NegotiationFormBase.fileDeleteEvent();

      $(self.suggestProject).on('keyup', function(e,m,selection){
			let searchText ={
				text:"",
				corporationIds:[],
				buildingIds:[],
				brandIds:[]
			}
			searchText.text= this.getRawValue()
			self.suggestCorporation.getSelection().forEach(function(item){
				searchText.corporationIds.push(item.id);
			});
			self.suggestBuildingInterview.getSelection().forEach(function(item){
				searchText.buildingIds.push(item.id);
			});
			self.suggestBrand.getSelection().forEach(function(item){
				searchText.brandIds.push(item.id);
			});
			NegotiationSearchService.findProject(searchText).then(function (data) {
				if(data){
					self.suggestProject.setData(data);
				}
			});
      });

      $(suggestVisitor).on('selectionchange', function(e,m,selection){
    	  var oldValue = curVisitorValue;
    	  var newValue = this.getValue();
    	  if(oldValue.length < newValue.length && selection.length > 0){
    		  visitorAdd = true;
    		  suggestNotification.addToSelection(selection[selection.length - 1]);
    	  }
    	  visitorAdd = false;
    	  curVisitorValue = newValue;
      });

      $(self.suggestProject).on('selectionchange', function(e,m,selection){
    	var oldValue = curProjectValue;
  		var newValue = this.getValue();
  		
  		let containerId = m.container[0].id;
  	    if (containerId == 'form-meeting-project') {
  	    	self.projectValues = [];
  	    	selection.forEach(function(element) {
                  if (element.projectCorporation != "{}" && element.projectCorporation != undefined) {
                      self.suggestCorporation.addToSelection(JSON.parse(element.projectCorporation));
                  }
                  if (element.projectBuilding != "{}" && element.projectBuilding != undefined) {
                      self.suggestBuildingInterview.addToSelection(JSON.parse(element.projectBuilding));
                  }
                  if (element.projectBrand != "{}" && element.projectBrand != undefined) {
                      self.suggestBrand.addToSelection(JSON.parse(element.projectBrand));
                  }

                  let project = {
                      id: element.id,
                      name: element.name != undefined ? element.name : element.label,
                  }
                  self.projectValues.push(project);
              });
  	    }
  	    
	  	$('div[id^=file-project-suggest-]').each(function() {
			let targetSuggest = $(this).magicSuggest();
			targetSuggest.setData(self.suggestProject.getSelection());
			if(oldValue.length > newValue.length){
				targetSuggest.clear();
	  		}
		});
	    
	    curProjectValue = newValue;
      });
      
      $(self.suggestBuildingInterview).on('selectionchange', function(e,m,selection){
  		var oldValue = curBuildingValue;
  		var newValue = this.getValue();
  		
  		$('div[id^=file-building-suggest-]').each(function() {
  			let targetSuggest = $(this).magicSuggest();
  			targetSuggest.setData(self.suggestBuildingInterview.getSelection());
  			if(oldValue.length > newValue.length){
  				targetSuggest.clear();
	    		}
  		});
  		
  		$('div[id^=file-shop-suggest-]').each(function() {
  			let targetSuggest = $(this).magicSuggest();
  			if(oldValue.length > newValue.length){
  				targetSuggest.clear();
	    		}
  		});
  		
  		curBuildingValue = newValue;
  	  });

      suggestVisitor.setSelection(visitorData);
      visitorAdd = false;

	  curVisitorValue = suggestVisitor.getValue();
	  curNotificationValue = suggestNotification.getValue();
  	
  	  $.each(curNotificationValue, function() {
  		$("#notification-type").append('<div class="radio" id="notification-type-'+this+'"><label><input type="radio" name="notification-type-'+this+'" value="TO">TO　</label><label><input type="radio" name="notification-type-'+this+'" value="CC" checked>CC</label>');
  	  });
  	  
  	  $('#notification-suggest').find('.ms-sel-ctn').addClass('notification-suggest-style');
  	  loadRadioNotification();
  		        
  	  $(suggestNotification).on('selectionchange', function(e,m,selection){
  		var oldValue = curNotificationValue;
  		var newValue = this.getValue();
  		var changeId = 0;
  		
  		if(oldValue.length > newValue.length){
  			changeId = $(oldValue).not(newValue).get()[0];
  			$('#notification-type-'+changeId).remove();
  		}else if(oldValue.length < newValue.length) {
  			changeId = $(newValue).not(oldValue).get()[0];
  			if(visitorAdd){
				$("#notification-type").append('<div class="radio" id="notification-type-'+changeId+'"><label><input type="radio" name="notification-type-'+changeId+'" value="TO">TO　</label><label><input type="radio" name="notification-type-'+changeId+'" value="CC" checked>CC</label>');
			}else{
				$("#notification-type").append('<div class="radio" id="notification-type-'+changeId+'"><label><input type="radio" name="notification-type-'+changeId+'" value="TO" checked>TO　</label><label><input type="radio" name="notification-type-'+changeId+'" value="CC">CC</label>');
			}
  		}
  		curNotificationValue = newValue;
  		loadRadioNotification();
      });
  	  
	  	$(suggestNotification).on('collapse', function(c){
	        loadRadioNotification();
	    });
	
	    $(suggestNotification).on('expand', function(c){
	        loadRadioNotification();
	    });

	    $("#form-meeting-project .ms-sel-ctn input").css("width","0px");
	    $("#form-meeting-corporation .ms-sel-ctn input").css("width","0px");
	    $("#form-meeting-building .ms-sel-ctn input").css("width","0px");
	    $("#j-form-business-card .ms-sel-ctn input").css("width","0px");
	    $("#form-visitor .ms-sel-ctn input").css("width","0px");
	    $("#form-brand .ms-sel-ctn input").css("width","0px");
	    $("#j-form-important-building .ms-sel-ctn input").css("width","0px");
	    $("#j-form-important-corporation .ms-sel-ctn input").css("width","0px");
    };

    this.createSuggest = function (target, name, method) {
      return magicSuggest.create(target, {
        name: name,
        request: function (value, successHandler) {
          method(value).then(function (data) {
            successHandler(data);
          });
        },
        allowFreeEntries: false
      });
    };
    this.createSuggestProject = function(target, name, method) {
		   return magicSuggest.create(target, {
				name: name,
				request: function(value, successHandler) {
					if(typeof(value)==='object'){
						method(value).then(function(data) {
							if(data){
								successHandler(data);
							}
						});
					}
				},
				allowFreeEntries: false,
			});
	   };
    Array.prototype.diff = function(a) {
	    return this.filter(function(i) {return a.indexOf(i) < 0;});
    };
   
    this.createNoticeSuggest = function(target, name, method) {
	   return magicSuggest.create(target, {
			name: name,
			request: function(value, successHandler) {
				method(value).then(function(data) {
					successHandler(data);
				});
			},
			allowFreeEntries: false,
			selectionPosition: 'bottom',
		});
    };

    this.create = function (isSendMail) {
    	var urls = location.href.split("/");
  	  if (urls[urls.length - 1] == "schedule") {
  		self.validator = $targetForm.validate(negotiationCreateScheduleValidate());
  	  } else {
  		self.validator = $targetForm.validate(negotiationCreateImplementationValidate());
  	  }
      if (!$targetForm.valid()) {
    	  NegotiationFormBase.createValidMessage();
    	  return;
      }
      this.setDatetime();
      $targetForm.trigger('click');
      $(this).attr("disabled", true);
      
      var data = new FormData();
      $.each($targetForm.serializeArray(), function(index, obj) {
  		data.append(obj.name, obj.value);
  	  });
      
      var fileList = [];
      $("#file-view-area .new-file").each(function(index) {
      	fileList.push($(this).prop("data"));
  	  });
      
      fileList.reverse();
      
      $.each(fileList, function(index) {
  		data.append("uploadFiles[" + index + "]", this);
  	  });
      
      $.each(curNotificationValue, function(index) {
		data.append("noticeSendTypes", $('input[name="notification-type-'+ this +'"]:checked').val());
	  });

      data.append("noSendMail", isSendMail);
      
      var type = $targetForm.attr("type");
      
      // suggest project file data
      getSuggestFileData('file-building-suggest-', 'buildingIds', data);
      getSuggestFileData('file-shop-suggest-', 'shopIds', data);
      getSuggestFileData('file-project-suggest-', 'projectIds', data);
      
      disableButton();
      
      jQuery.ajax({
  		url: createUrl("/pc/negotiation/create/"+type),
  		data : data,
  		cache : false,
  		contentType : false,
  		processData : false,
  		method : 'POST',
  		type : 'POST', // For jQuery < 1.9
  		success : function(response) {
  			window.location.replace(createUrl("/pc/negotiation/detail/" + response));
		},
  		error : function() {
  			alert("作成に失敗しました。");
  		}
 	  });
    };

    this.draft = function() {
		if (!$targetForm.valid()) {
			self.validator.destroy();
	    }
      this.setDatetime();
      $targetForm.trigger('click');
      
      var data = new FormData();
      $.each($targetForm.serializeArray(), function(index, obj) {
  		data.append(obj.name, obj.value);
  	  });
      
      var fileList = [];
      $("#file-view-area .new-file").each(function(index) {
      	fileList.push($(this).prop("data"));
  	  });
      
      fileList.reverse();
      
      $.each(fileList, function(index) {
  		data.append("uploadFiles[" + index + "]", this);
  	  });
      
      $.each(curNotificationValue, function(index) {
		data.append("noticeSendTypes", $('input[name="notification-type-'+ this +'"]:checked').val());
	  });

      data.append("noSendMail", false);
      
      // suggest project file data
      getSuggestFileData('file-building-suggest-', 'buildingIds', data);
      getSuggestFileData('file-shop-suggest-', 'shopIds', data);
      getSuggestFileData('file-project-suggest-', 'projectIds', data);
      
      disableButton();
      
      var type = $targetForm.attr("type");
      
      jQuery.ajax({
  		url: createUrl("/pc/negotiation/draft/"+type),
  		data : data,
  		cache : false,
  		contentType : false,
  		processData : false,
  		method : 'POST',
  		type : 'POST', // For jQuery < 1.9
  		success : function(response) {
  			window.location.replace(createUrl("/pc/negotiation/detail/" + response));
		},
  		error : function() {
  			alert("作成に失敗しました。");
  		}
  	  });
    }

    function disableButton() {
        $(".draft-button").attr("disabled", true);
    	$(".register-button").attr("disabled", true);
    }

    this.setDatetime = function() {
        var scheduleDate = $('.j-scheduled-date').val();
        var scheduleStartTime = $('.j-scheduled-time-start').val();
        var scheduleEndTime = $('.j-scheduled-time-end').val();
        var implementationDate = $('.j-implementation-date').val();
        var implementationStartTime = $('.j-implementation-time-start').val();
        var implementationEndTime = $('.j-implementation-time-end').val();
        var showStartDatetime = $('.j-show-start-date').val();
        var showEndDatetime = $('.j-show-end-date').val();

        if (scheduleDate) {
            $('<input>').attr({
              type: 'hidden',
              name: 'scheduleStartDatetime',
              value: scheduleDate + ' ' + scheduleStartTime
            }).appendTo($targetForm);

            $('<input>').attr({
              type: 'hidden',
              name: 'scheduleEndDatetime',
              value: scheduleDate + ' ' + scheduleEndTime
            }).appendTo($targetForm);
        }

        if (implementationDate) {
            $('<input>').attr({
              type: 'hidden',
              name: 'implementationStartDatetime',
              value: implementationDate + ' ' + implementationStartTime
            }).appendTo($targetForm);

            $('<input>').attr({
              type: 'hidden',
              name: 'implementationEndDatetime',
              value: implementationDate + ' ' + implementationEndTime
            }).appendTo($targetForm);
        }

        if (showStartDatetime) {
            $('<input>').attr({
              type: 'hidden',
              name: 'importantInformation.showStartDate',
              value: showStartDatetime + ' 00:00'
            }).appendTo($targetForm);
        }

        if (showEndDatetime) {
            $('<input>').attr({
              type: 'hidden',
              name: 'importantInformation.showEndDate',
              value: showEndDatetime + ' 00:00'
            }).appendTo($targetForm);
        }

        if (!$('.j-important-content').val()) {
            $('.j-important-content').prop('disabled', true);
        }
    }

    this.onCheck = function () {
      NegotiationSearchService.submitForm();
    };

    $('#btnAccept').click(function(){
    	self.create(true);
    });

    $('#btnNoAccept').click(function(){
    	self.create(false);
    });

    $(".j-scheduled-time-start").change(function(e) {
        $(".j-scheduled-time-end").val(plusOneHour($(this).val()));
    });

    $(".j-implementation-time-start").change(function(e) {
        $(".j-implementation-time-end").val(plusOneHour($(this).val()));
    });


    function plusOneHour(time) {
            var timeSplit = time.split(":");
            var hour = timeSplit[0];
            var minute = timeSplit[1];
            hour = parseInt(hour) + 1;
            if (hour === 24) {
                hour = "00";
            } else if (hour < 10) {
                hour = "0" + hour;
            }
            return hour + ":" + minute;
        };

    function loadRadioNotification(){        
        $("#notification-suggest").find('.ms-sel-item').each(function(idx,dom){     	
        	if ($(this).length > 0) {
        		if(idx === 0){
        			$("#notification-type-"+curNotificationValue[idx]).css({top: 30, left: 15, position:'absolute'})
        		}else{
        			$("#notification-type-"+curNotificationValue[idx]).css({top: 44 + (idx*30), left: 15, position:'absolute'})
        		}
			}
        });
    }
    
    function getSuggestFileData(partOfName, bindName){
    	$('div[id^='+partOfName+']').each(function() {
        	let target = $(this);
        	let targetId = target.attr('id');
        	
        	if(targetId !== undefined && targetId.length > 0){
        		let targetIndex = targetId.substring(targetId.length - 1, targetId.length);
        		let targetValue = target.magicSuggest().getValue();
        		
        		if(targetValue.length > 0){
        			targetValue.each(function(){
        				data.append('fileInformation[' + targetIndex + '].' + bindName, $(this).value);
        			});
        		}
        	}
		});
    }
    
    function getSuggestFileData(partOfName, bindName, data){
    	$('div[id^='+partOfName+']').each(function() {
        	let target = $(this);
        	let targetId = target.attr('id');
        	
        	if(targetId !== undefined && targetId.length > 0){
        		let targetIndex = targetId.substring(targetId.length - 1, targetId.length);
        		let targetValue = target.magicSuggest().getValue();
        		
        		if(targetValue.length > 0){
        			$.each(targetValue, function(){
        				data.append('fileInformation[' + targetIndex + '].' + bindName, this);
        			});
        		}
        	}
		});
    }
});


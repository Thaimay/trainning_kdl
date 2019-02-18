'use strict';

angular
  .module('app.core')
 .controller('NegotiationUpdateController', function(NegotiationFormBase, NegotiationSearchService, $scope) {
	   var self = this;
	   var $targetForm = $("#negotiation-form");
	   var curNotificationValue = [];
	   var curVisitorValue = [];
	   var visitorAdd = false;
	   self.isProject = false;
	   self.projectData = {};
	   
	   var curBuildingValue = [];
	   var curProjectValue = [];

	   this.initSuggest = function() {
		    self.suggestProject = this.createSuggestProject($(".j-form-meeting-project"), "projectIds", NegotiationSearchService.findProject);
		    self.suggestCorporation = this.createSuggest($(".j-form-meeting-corporation"), "corporationIds", NegotiationSearchService.findCorporation);
		    self.suggestBuildingInterview = this.createSuggest($(".j-meeting-building"), "buildingIds", NegotiationSearchService.findBuilding);
	    	let suggestVisitor = this.createSuggest($(".j-form-visitor"), "accountIds", NegotiationSearchService.findAccount);
	    	this.createSuggest($(".j-form-business-card"), "bussinessCardIds", NegotiationSearchService.findPartner);
	    	self.suggestBrand = this.createSuggest($(".j-form-brand"), "brandIds", NegotiationSearchService.findBrand);
	    	let suggestNotification = this.createNoticeSuggest($(".j-form-notification"), "noticeIds", NegotiationSearchService.findAccount);
	    	this.createSuggest($(".j-form-important-building"), "importantInformation.buildingIds", NegotiationSearchService.findBuilding);
	    	this.createSuggest($(".j-form-important-corporation"), "importantInformation.corporationIds", NegotiationSearchService.findCorporation);
	    	datepickerUtils.createDatePicker($(".j-show-start-date"));
	    	datepickerUtils.createDatePicker($(".j-show-end-date"));
	    	
	    	// init file suggest 
	    	$('input[id^=file-building-suggest-]').each(function(){
	    		$(this).magicSuggest( {
					allowFreeEntries : false,
					maxSelectionRenderer: function () {
						return "";
					},
					data : self.suggestBuildingInterview.getSelection(),
					value : []
				});
	    	});
	    	
			$('input[id^=file-shop-suggest-]').each(function(){
				let suggestShopFile = $(this).magicSuggest({
					allowFreeEntries : false,
					maxSelectionRenderer: function () {
						return "";
					},
					data : [],
					value : []
				});
				
				$(suggestShopFile).on('keyup', function(e, m, v) {
					let value = this.getRawValue();
					if (value) {
						let suggest = this;
						$.ajax({
							url: createUrl("/sp/negotiation/find/shopfile"),
							type : "POST",
							data : JSON.stringify({
								"text" : value,
								"params": self.suggestBuildingInterview.getValue()
							}),
							contentType : "application/json",
							success : function(response) {
								suggest.setData(response);
							}
						});
					}
				});    		
	    	});

			$('input[id^=file-project-suggest-]').each(function(){
				$(this).magicSuggest({
					allowFreeEntries : false,
					maxSelectionRenderer: function () {
						return "";
					},
					data : self.suggestProject.getSelection(),
					value : []
				});
			});

	    	this.initRating();
	    	NegotiationFormBase.fileDeleteEvent();
	    	self.validate = $targetForm.validate(negotiationUpdateValidate());

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
	    	
	    	curProjectValue = self.suggestProject.getValue();

			$(self.suggestProject).on('keyup', function(e,m,selection){
		      	  if (selection.keyCode == "37" || selection.keyCode == "38" || selection.keyCode == "39" || selection.keyCode == "40") {
		    		  return;
		    	  }

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
	    	
	    	curBuildingValue = self.suggestBuildingInterview.getValue();
	    	
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

	    	curVisitorValue = suggestVisitor.getValue();
	    	curNotificationValue = suggestNotification.getValue();
	    	
	    	$.each(suggestNotification.getSelection(), function() {
	    		var to = this.sendType === 'TO';
	    		if(to){
	    			$("#notification-type").append('<div class="radio" id="notification-type-'+this.id+'"><label><input type="radio" name="notification-type-'+this.id+'" value="TO" checked>TO　</label><label><input type="radio" name="notification-type-'+this.id+'" value="CC">CC</label>');
	    		}else{
	    			$("#notification-type").append('<div class="radio" id="notification-type-'+this.id+'"><label><input type="radio" name="notification-type-'+this.id+'" value="TO">TO　</label><label><input type="radio" name="notification-type-'+this.id+'" value="CC" checked>CC</label>');
	    		}
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
	    		curNotificationValue = this.getValue();
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
			$("#form-business-card .ms-sel-ctn input").css("width","0px");
			$("#form-visitor .ms-sel-ctn input").css("width","0px");
			$("#form-brand .ms-sel-ctn input").css("width","0px");
			$("#j-form-important-building .ms-sel-ctn input").css("width","0px");
			$("#j-form-important-corporation .ms-sel-ctn input").css("width","0px");
	   };

		this.draft = function() {
			self.saveDraft = true;
			self.update(false);
		  }
	   this.createSuggest = function(target, name, method) {
		   return magicSuggest.create(target, {
				name: name,
				request: function(value, successHandler) {
					method(value).then(function(data) {
						if(data){
							successHandler(data);
						}
					});
				},
				allowFreeEntries: false,
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

    this.initRating = function() {
    	$(function() {
        	var rating = $('#j-star-container').attr("rating")
        	$("#j-star-container .star").each(function(index, element) {
        		if (index < rating) {
        			$(this).addClass('fullStar');
        		}
        	});
    	});
    }

    function disableButton() {
        $(".draft-button").attr("disabled", true);
    	$(".register-button").attr("disabled", true);
    }

    this.update = function(isSendMail) {
    	var scheduleDate = $(".j-scheduled-date").val();
    	var scheduleStartTime = $(".j-scheduled-time-start").val();
    	var scheduleEndTime = $(".j-scheduled-time-end").val();
    	var implementationDate = $(".j-implementation-date").val();
    	var implementationStartTime = $(".j-implementation-time-start").val();
    	var implementationEndTime = $(".j-implementation-time-end").val();
    	var showStartDatetime = $(".j-show-start-date").val();
    	var showEndDatetime = $(".j-show-end-date").val();

    	if(self.saveDraft){
    		self.validate.destroy();
    	} else {
    		if (!$targetForm.valid()) {
            	NegotiationFormBase.createValidMessage();

          	  return;
            }
    	}
        
        if (!$(".j-important-content").val()) {
       		$(".j-important-content").prop("disabled", true);
       	}
        
        var data = new FormData();
    	$targetForm.trigger('click');

        $.each($targetForm.serializeArray(), function(index, obj) {
    		data.append(obj.name, obj.value);
    	});
        
        var current = $("#file-view-area .current-file").length;
        
        var fileList = [];
        $("#file-view-area .new-file").each(function(index) {
        	fileList.push($(this).prop("data"));
    	});
        
        fileList.reverse();
        
        $.each(fileList, function(index) {
    		data.append("uploadFiles[" + (current + index) + "]", this);
    	});
        
        if (scheduleDate) {
	    	data.append("scheduleStartDatetime", scheduleDate + " " + scheduleStartTime);
	    	data.append("scheduleEndDatetime", scheduleDate + " " + scheduleEndTime);
    	}
        
        if (implementationDate) {	    	
	    	data.append("implementationStartDatetime", implementationDate + " " + implementationStartTime);
	    	data.append("implementationEndDatetime", implementationDate + " " + implementationEndTime);
    	}

    	if (showStartDatetime) {    		
    		data.append("importantInformation.showStartDate", showStartDatetime + " 00:00");
    	}

       	if (showEndDatetime) {    		
    		data.append("importantInformation.showEndDate", showEndDatetime + " 00:00");
    	}

        $.each(curNotificationValue, function(index) {
    		data.append("noticeSendTypes", $('input[name="notification-type-'+ this +'"]:checked').val());
    	  });

       	if (!self.saveDraft) {
       		data.append("draft", false);
    	} else {
    		data.append("draft", true);
    	}

        data.append("noSendMail", isSendMail);
        
        // suggest project file data
        getSuggestFileData('file-building-suggest-', 'buildingIds', data);
        getSuggestFileData('file-shop-suggest-', 'shopIds', data);
        getSuggestFileData('file-project-suggest-', 'projectIds', data);
        
        disableButton();
        
        jQuery.ajax({
    		url: createUrl("/pc/negotiation/update"),
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
    			alert("更新に失敗しました。");
    		}
    	});
    };

    $('#btnAccept').click(function(){
    	self.update(true);
    });

    $('#btnNoAccept').click(function(){
    	self.update(false);
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
        }

    this.setImplementDate = function () {
    	$(".j-implementation-date").val($(".j-scheduled-date").val());
    	$(".j-implementation-time-start").val($(".j-scheduled-time-start").val());
    	$(".j-implementation-time-end").val($(".j-scheduled-time-end").val());
	}
    
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

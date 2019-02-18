$(function() {
	// change tab from Url
	changeTabFromUrl();

	$("#j-detail-edit").on('click', function(){
		if (project.operationDivision === '中止' && !confirm('中止案件ですが、このまま編集しますか？')) {
			return false;
		}
		url = $('.basic-detail-tabs li.active a').prop("href");
		url = url.replace('detail', 'showUpdate');
		window.location.href = url;
	});

	$(document).on('click', '.j-download-item', function() {
		window.open($(this).attr('path'), '_system');
	});
	$("#j-form-history").on('change', function(e, m) {
		var form = $('#project-revision');
		form.submit();
	});
	$('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
		 var target = $(e.target).attr("href") // activated tab
		 if(target == '#image'){
			var elem = document.querySelector('.masonry-grid');
			var msnry = new Masonry( elem, {
				itemSelector: '.masonry-item',
				columnWidth: '.grid-sizer',
				percentPosition: true
			});
		 }
	});
	
	loadedElements = 0;

	var instance = $('.project_detail_image_lazy')
			.Lazy(
					{
						afterLoad : function(element) {
							loadedElements++;
						},
						onError : function(element) {
							loadedElements++;
						},
					});
	
	$(".project-task-complete").on("change", function(){
		var taskId = $(this).val();
		var check = $(this).is(':checked');
		jQuery.ajax({
			url : createUrl("/pc/project/projectTask/complete/" + taskId),
			cache : false,
			contentType : false,
			processData : false,
			method : 'POST',
			type : 'POST', // For jQuery < 1.9success : function(response) {
			success : function(response) {
				if (check) {
					$("#alert-info").find('.alert-msg').text("タスクを完了にしました");
				      	if ($("#alert-info").css('display') === 'none') {
				      		$("#alert-info").fadeIn(1000).delay(2000).fadeOut(2000);
				    }
				} else {
					$("#alert-info").find('.alert-msg').text("タスクを未対応にしました");
			      	if ($("#alert-info").css('display') === 'none') {
			      		$("#alert-info").fadeIn(1000).delay(2000).fadeOut(2000);
			      	}
				}
			},
			error : function() {
				alert("タスクを未対応にしました。");
			}
		});
	});
	
	$("#building-shop-detail-button").on("click", function(){
		if(project.shop.building == null){
			alert("紐づく館が存在しません");
		}else{
			window.open(createUrl('/pc/building/detail/' + project.shop.building.id + '#shop-case&project/detail/' + project.id));
		}
	});
});

function backToList(){
	// back to list or project
	let url = window.location.href;
	if (url.indexOf('#') > -1 && url.indexOf('&') > -1) {
		let screen = url.substring(url.lastIndexOf('&') + 1);
		window.location.replace(createUrl('/pc/' + screen));
	} else {
		let searchForm = localStorage.getItem("project_search_form");
		if(searchForm !== undefined && searchForm !== null && searchForm.length > 0){
			let formData = JSON.parse(searchForm);
			let input = "";
			if(formData.length > 0){
				$.each(formData, function(index, obj) {
					if(this.name !== 'isDefaultSearch'){
						input += '<input type="hidden" name="' + this.name + '" value="' + this.value + '" />';
					}
			  	});
			}
			
			let form = $('<form action="/wsd/pc/project/find" method="post" accept-charset="UTF-8">'+input+'</form>');
			$('body').append(form);
					
			form.submit();
		} else{
			window.location.replace(createUrl("/pc/project/list"));
		}
	}
}
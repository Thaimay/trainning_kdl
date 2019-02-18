$(function() {
	$("#j-form-history").on('change', function(e, m) {
		var form = $('#project-revision');
		form.submit();
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
$(function() {
	// change tab from Url
	changeTabFromUrl();

	$("#j-form-history").on('change', function(e, m) {
		var form = $('#store-revision');
		form.submit();
	});
	$('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
		 var target = $(e.target).attr("href") // activated tab
		 if(target == '#image'){
				loadedElements = 0;
				
				$('.store_detail_image_lazy').Lazy({
					beforeLoad: function(element){
						console.log('image "' + element.data('src') + '" is about to be loaded');
					},
					afterLoad: function(element) {
						loadedElements++;
						
						setTimeout(function() { 
							var elem = document.querySelector('.masonry-grid');
							var ma = new Masonry( elem, {
							itemSelector: '.masonry-item',
							columnWidth: '.grid-sizer',
							percentPosition: true
							});
						}, 1000);
						
						console.log('image "' + element.data('src') + '" was loaded successfully');
					},
					onError: function(element) {
						loadedElements++;
						console.log('image "' + element.data('src') + '" could not be loaded');
					},
					onFinishedAll: function() {
						console.log('finished loading ' + loadedElements + ' elements');
						console.log('lazy instance is about to be destroyed')
					}
			  });
		 }
	});

	// click back to list
	$('.btn-back2list').on('click', function(e, m){
		let url = window.location.href;
		if (url.indexOf('#') > -1 && url.indexOf('&') > -1) {
			let screen = url.substring(url.lastIndexOf('&') + 1);
			window.location.replace(createUrl('/pc/' + screen));
		} else {
			window.location.replace(createUrl('/pc/store/list'));
		}
	});
})
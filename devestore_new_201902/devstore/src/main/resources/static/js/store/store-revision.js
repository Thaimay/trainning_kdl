$(function() {
	
	$("#j-form-history").on('change', function(e, m) {
		var form = $('#store-revision');
		form.submit();
	});
})
var createUrl = function(url) {
	return "/wsd" + url;
}

var changeTabFromUrl = function(){
	let url = window.location.href;
	let lastUrl = '#basic';
	if(url.indexOf('#') > -1 && url.indexOf('&') > -1){
		lastUrl = url.substr(url.lastIndexOf('#'), url.lastIndexOf('&') - url.lastIndexOf('#'));
	} else if (url.indexOf('#') > -1 && url.indexOf('&') == -1){
		lastUrl = url.substr(url.lastIndexOf('#'));
	}
	if ($('a[href="'+ lastUrl +'"]').length === 0) {
		lastUrl = '#basic';
	}
	$('a[href="'+ lastUrl +'"]').parent().addClass('active');
	$('#basic').removeClass('active in');
	$(''+lastUrl).addClass('active in');
}

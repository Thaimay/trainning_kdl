function _addFileView(file){
	var dom = $("#file-view-area");
	var html = $("#file-upload-template").html();
	var index = $("#file-view-area .input-area").length;
	var permitType = [{type:"image/jpeg",division:"image",extension:"jpg"}, {type:"image/png",division:"image",extension:"png"}, {type:"image/gif",division:"image",extension:"gif"},
	    {type:"application/pdf",division:"/wsd/img/pdf.jpg",extension:"pdf"}, {type:"application/vnd.openxmlformats-officedocument.wordprocessingml.document",division:"/wsd/img/docx.jpg",extension:"docx"}, 
	    {type:"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",division:"/wsd/img/xlsx.jpg",extension:"xlsx"}, 
	    {type:"application/vnd.openxmlformats-officedocument.presentationml.presentation",division:"/wsd/img/pptx.jpg",extension:"pptx"},
	    {type:"video/mp4",division:"video",extension:"mp4"}, {type:"video/quicktime",division:"video",extension:"mov"}];

    var reader = new FileReader();
    var ret = 0;            
    
    html = html.replace(/#index#/g, index);
    
    permitType.forEach(function (element) {
    	if (element.type == file.type && (element.division == "image")) {
            reader.readAsDataURL(file);

            reader.onload = function() {
            	html = html.replace(/#file_source#/g, '<div class="img"><img class="file-image-view" src="' + this.result + '"></div>');
            	dom.prepend('<div class="new-file input-area file-input-area' + index + '" index="' + index + '">' + html + '</div>');
            	var recentFiles = dom.prop("files");
            	recentFiles.push(file);
            	dom.prop("files", recentFiles);
            	
            	dom.find('.input-area[index="'+index+'"]').prop("data", file);
            	
            	initSuggest(index);
            };
            ret = 1;
    	} else if (element.type == file.type && element.division == "video") {
			reader.readAsDataURL(file);

            reader.onload = function() {
            	html = html.replace(/#file_source#/g, '<div class="video"><video controls class="video-view"><source src="' + this.result + '" type="video/mp4"></video></div>');
				dom.prepend('<div class="new-file input-area file-input-area' + index + '" index="' + index + '">' + html + '</div>');
				var recentFiles = dom.prop("files");
            	recentFiles.push(file);
            	dom.prop("files", recentFiles);
            	
            	dom.find('.input-area[index="'+index+'"]').prop("data", file);
            	
            	initSuggest(index);
            };
            ret = 1;
		} else if (element.type == file.type && (element.division != "image" || element.division != "video")) {
			reader.readAsDataURL(file);

            reader.onload = function() {
            	html = html.replace(/#file_source#/g, '<div class="img"><img class="file-image-view" src="' + element.division + '"></div>');
            	dom.prepend('<div class="new-file input-area file-input-area' + index + '" index="' + index + '">' + html + '</div>');
				var recentFiles = dom.prop("files");
            	recentFiles.push(file);
            	dom.prop("files", recentFiles);
            	
            	dom.find('.input-area[index="'+index+'"]').prop("data", file);
            	
            	initSuggest(index);
            };
            ret = 1;
		}
    });

    if (ret === 0) {
    	alert("ファイルの拡張子はjpg、png、gif、docx、xlsx、pdf、pptx、mp4またはmovでアップロードしてください");
        return;
	}
}

function initSuggest(index){
	let suggestNegotiatoinBuilding = $("#form-meeting-building").magicSuggest();
	let suggestNegotiatoinProject = $("#form-meeting-project").magicSuggest();
	
	let suggestBuilding = $('#file-building-suggest-' + index).magicSuggest(
			{
				allowFreeEntries : false,
				maxSelectionRenderer: function () {
					return "";
				},
				data : suggestNegotiatoinBuilding.getSelection(),
				value : []
			});
	
	let suggestShop = $('#file-shop-suggest-' + index).magicSuggest(
			{
				allowFreeEntries : false,
				maxSelectionRenderer: function () {
					return "";
				},
				data : [],
				value : []
			});
	
	$(suggestShop).on('keyup', function(e, m, v) {
		let value = this.getRawValue();
		if (value) {
			let suggest = this;
			$.ajax({
				url: createUrl("/sp/negotiation/find/shopfile"),
				type : "POST",
				data : JSON.stringify({
					"text" : value,
					"params": suggestNegotiatoinBuilding.getValue()
				}),
				contentType : "application/json",
				success : function(response) {
					suggest.setData(response);
				}
			});
		}
	});
	
	let suggestProject = $('#file-project-suggest-' + index).magicSuggest(
			{
				allowFreeEntries : false,
				maxSelectionRenderer: function () {
					return "";
				},
				data : suggestNegotiatoinProject.getSelection(),
				value : []
			});
}
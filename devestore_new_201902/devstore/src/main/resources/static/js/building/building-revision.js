$(function() {

	$('.media-list-item[bulding-sales-type]').hide();
	let buildingSaleType = $('input[type="radio"][name="sales-type"]').val();
	$('.media-list-item[bulding-sales-type="' + buildingSaleType + '"]').show();
	$(document).on(
			'change',
			'input[type=radio][name=sales-type]',
			function() {
				$('li.media-list-item[bulding-sales-type]').hide();
				$(
						'.media-list-item[bulding-sales-type="' + $(this).val()
								+ '"]').show();
			});

	$("#j-form-history").on('change', function(e, m) {
		var form = $('#building-revision');
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
})
function buildingSales(buildingSaless) {
	let fMonths = Array.from(Array.from(buildingSaless.map(function(s) {
		let date = new Date(s.financialMonth);
		return date.getFullYear().toString();
	})).filter(function(value, index, self) {
		return self.indexOf(value) === index;
	})).sort().reverse();
	let textBuildingSale = '';
	$
			.each(
					fMonths,
					function(index, obj) {
						let data = buildingSaless.filter(function(bds) {
							let date = new Date(bds.financialMonth);
							return date.getFullYear().toString() === obj;
						});
						var type = Array.from(Array.from(data.map(function(f) {
							return f.type;
						})).filter(function(value, index, self) {
							return self.indexOf(value) === index;
						})).sort();
						$
								.each(
										type,
										function(index, t) {
											var buildsaleAll = data
													.filter(function(bds) {
														return (bds.type == t && bds.division == 1);
													});
											textBuildingSale += '<li class="media-list-item has-trash present-item" bulding-sales-year="'
													+ obj
													+ '" bulding-sales-type="'
													+ t + '" >';
											textBuildingSale += '<div class="building-basic-info j-dropdown">';
											textBuildingSale += '<header class="building-basic-info-header j-dropdown-header isOpen">';
											textBuildingSale += '<h3 class="title">'
													+ obj + '年度</h3>';
											if (buildsaleAll.length > 0) {
												textBuildingSale += '<div class="ta-r" style="width: 150px; min-width: 150px; max-with: 150px; position: absolute; right:380px;top:10px;">'
													+ buildsaleAll[0].salesString +'</div>';
											textBuildingSale += '<div class="ta-r" style="width: 150px; min-width: 150px; max-with: 150px; position: absolute; right:230px;top:10px;">'
												+ buildsaleAll[0].areaString +'</div>';
											textBuildingSale += '<div class="ta-r" style="width: 150px; min-width: 150px; max-with: 150px; position: absolute; right:80px;top:10px;">'
												+ buildsaleAll[0].monthBasisString +'</div>';
											}
											
											textBuildingSale += '</header>';
											textBuildingSale += '<div class="building-basic-info-body j-dropdown-body" style="display: block;">';
											textBuildingSale += '<div class="j-dropdown-body-inner">';
											textBuildingSale += '<table style="width:100%">';
											textBuildingSale += '<tbody class="present-item" objectName="buildingSalesDto">';
											var buildsales = data
													.filter(function(dt) {
														return (dt.type === t && dt.division !== 1);
													});
											$
													.each(
															buildsales,
															function(index,
																	buildsale) {
																textBuildingSale += '<tr style="border-bottom: 1px solid #cccccc;">';
																textBuildingSale += '<th style="padding-top:15px;padding-left:50px">';
																textBuildingSale += buildsale.mBuildingSalesClassifications.value
																textBuildingSale += '</th>';
																textBuildingSale += '<td class="ta-r"';
																textBuildingSale += ' style="padding-top:15px;width: 150px; min-width: 150px; max-width: 150px">' + buildsale.salesString + '</th>';
																textBuildingSale += '<td class="ta-r"';
																textBuildingSale += ' style="padding-top:15px;width: 150px; min-width: 150px; max-width: 150px">' + buildsale.areaString + '</th>';
																textBuildingSale += '<td class="ta-r"';
																textBuildingSale += ' style="padding-top:15px;width: 150px; min-width: 150px; max-width: 150px">' + buildsale.monthBasisString + '</th>';
																textBuildingSale += '<td';
																textBuildingSale += ' style="padding-top:15px;width: 80px; min-width: 80px; max-width: 80px">';
																textBuildingSale += '</td>';
																textBuildingSale += '</tr>';
															});
											textBuildingSale += '</tbody>';
											textBuildingSale += '</table>';
											textBuildingSale += '</div>';
											textBuildingSale += '</div>';
											textBuildingSale += '</div>';
											textBuildingSale += '</li>';
										});
					});
	return textBuildingSale;
};
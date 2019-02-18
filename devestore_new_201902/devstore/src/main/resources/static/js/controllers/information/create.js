let suggestCorporation, suggestBuildingGroupId,content, showEndDate;
$(function () {
	var createDatePicker = function($target, dateFormat) {
		if (dateFormat == undefined) {
			dateFormat = "yy-mm-dd";
		}
		$target.datepicker({
			dateFormat : dateFormat
		});
	};

    createDatePicker($("#show-end-date-info"), "yy-mm-dd 00:00");
    
    content = $('#content').val();
    
    showEndDate = $('#show-end-date-info').val();
    
    suggestPmCorporation = $('#j-form-info-corporation').magicSuggest({
        noSuggestionText: "候補がありません",
        allowFreeEntries: false
    });

    suggestBuildings = $('#j-info-meeting-building').magicSuggest({
        noSuggestionText: "候補がありません",
        allowFreeEntries: false
    });

    var createSuggest = function (target, url) {
        var isImeMode = false;
        $(target).on({
            'keydown': function (keydownEvent, ms, event) {
                if (event.keyCode === 229) {
                    isImeMode = true
                } else {
                    isImeMode = false
                }
            },
            'keyup': function (keyupEvent, ms, event) {
                if (event.keyCode == 38 || event.keyCode == 40) { return; }

                let value = this.getRawValue();
                if (!value) { return; }

                if (!isImeMode || (isImeMode && event.keyCode === 13)) {
                    let suggest = this;
                    $('#' + target.container[0].id + ' input[type=text]').val(value);
                    $.ajax({
                        url: createUrl(url),
                        type: "POST",
                        data: JSON.stringify({
                            "text": value
                        }),
                        contentType: "application/json",
                        success: function (response) {
                            suggest.setData(response);
                        }
                    });
                }
            },
            'blur': function (e) {
                isImeMode = false
            }
        });
    }

    createSuggest(suggestPmCorporation, '/sp/building/find/corporation');
    createSuggest(suggestBuildings, '/sp/negotiation/find/building');
});


function getDataSuggestBuildings(data) {
	$.each(suggestBuildings.getSelection(), function (index, obj) {
        data.append('buildingIds[' + index + ']', obj.id);
    });
}

function getDataSuggetPmCorporation(data) {
    $.each(suggestPmCorporation.getSelection(), function (index, obj) {
        data.append('corporationIds[' + index + ']', obj.id);
    });
}

$(document).on('click', '#information-btn-create', function () {

    $(".error").find("label").remove();
    let form = $('#information-form');
    let $this = this;
    var data = new FormData();

    getDataSuggestBuildings(data);
    getDataSuggetPmCorporation(data);
    // Append Data form
    $.each(form.serializeArray(), function (index, obj) {
        data.append(obj.name, obj.value);
    });

    jQuery.ajax({
        url: createUrl("/pc/information/doCreate"),
        data: data,
        cache: false,
        contentType: false,
        processData: false,
        method: 'POST',
        type: 'POST', // For jQuery < 1.9
        success: function (response) {
            window.location.replace(createUrl("/pc/top"));
        },
        error: function () {
            alert("Create fail!");
            $($this).removeClass("disabled");
        }
    });
});





$(function() {
	$(document).on("click", "span.ms-close-btn", function() {
		var parent = $(this).parent()
		var name = parent.text();
		var classes = document.getElementsByClassName(name);
		
		$.each(classes, function(element){
			$(element).remove();
		});
	});
})

var magicSuggest = {

	create: function(target, options) {
		if(options == undefined) {
			options = {};
		}
		var suggestOptions = {
			placeholder: '',
			data: options.data,
			maxSelection: 50,
			noSuggestionText: "候補がありません",
			selectionRenderer: function(v) {
					if (v.label === undefined) {
							return v.name;
					} else {
							return v.label;
					}
			},
		};

		Object.keys(options).forEach(function(key) {
			suggestOptions[key] = options[key];
		});

		var defaultValue = target.val();

		if (defaultValue === "" || defaultValue === undefined || defaultValue == "null") {
			defaultValue = [];
		} else {
			defaultValue = JSON.parse(defaultValue).map(function(item) {
		        item.id = item.id == null ? item.label : item.id;
		        return item;
			});
		}

		suggestOptions.value = defaultValue;
		var targetForm = target.closest("form");  // magicSuggestにデフォルトのinput要素が削除されて探せなくなるためココでキャッシュ
		var suggest = target.magicSuggest(suggestOptions);

		magicSuggest.deleteDefaultHiddenInput(targetForm, options.name);

		defaultValue.forEach(function(item, index) {
			magicSuggest.addHiddenInput(targetForm, item.id, options.name, index, item.label);
		});

		var isImeMode = false;
		
		$(suggest).on({
			'keydown': function (keydownEvent, ms, event) {
				if (event.keyCode === 229) {
					isImeMode = true
				} else {
					isImeMode = false
				}
			},
			'keyup': function(keyupEvent, ms, event) {
				var value = suggest.getRawValue();

				if (event.keyCode == 38 || event.keyCode == 40) {
					return;
				}
				
				if (!isImeMode || (isImeMode && event.keyCode === 13)) {
					if (value && options.request != undefined) {
						$('#' + suggest.container[0].id + ' input[type=text]').val(value);
						options.request(value, function(data) {
							suggest.setData(data);

							if (data.length > 0) {
								suggest.fadeOutNoSuggest();
							}
						});
					}

				}
			},
			'blur': function(e) {
				magicSuggest.deleteHiddenInput(options.name);
				isImeMode = false

				var selectedItems = suggest.getValue();
				var selectedDatas = suggest.getSelection();

				for (var i = 0; i < selectedItems.length; i++) {
					var nameValue = "";
					if (options.request) {
						var id = selectedDatas[i].id;
						if (!$.inArray(id, selectedItems)) {
							continue;
						}

						nameValue = selectedDatas[i].name;
					} else {
						nameValue = selectedItems[i];
					}

					magicSuggest.addHiddenInput(targetForm, selectedItems[i], options.name, i, nameValue);
				}
				magicSuggest.deleteDefaultHiddenInput(targetForm, options.name);
				magicSuggest.screeningHideInput(targetForm, suggest, options);
			}
		});

		$(document).find("button[type=submit]").on({
			'click': function(e) {
				magicSuggest.screeningHideInput(targetForm, suggest, options);
			}
		});

		$(targetForm).find("input[type='submit']").on({
			'click': function(event) {
				magicSuggest.screeningHideInput(targetForm, suggest, options);
			}
		});

		$(".j-form-submit").on({
			'click': function() {
				magicSuggest.screeningHideInput(targetForm, suggest, options);
			}
		});
		return suggest;
	},
	deleteHiddenInput: function(name, label) {
		$('.suggest-' + name.replace(".","-")).remove();
	},
	deleteDefaultHiddenInput: function(targetForm, name) {
		targetForm.find("input[name='" + name + "[]']").each(function(i, elm) {
			if ($(elm).hasClass('suggest-' + name.replace(".","-"))) { return }
			$(elm).remove();
		});
	},
	addHiddenInput: function(targetForm, value, name, index, label) {
		var input = $("<input>").prop({
			type: "hidden",
			name: name + '[' + index + ']',
			value: value,
			class: "suggest-" + name.replace(".","-") + " " + label,
		});



		targetForm.append(input);
	},
	screeningHideInput: function(targetForm, suggest, options) {
		magicSuggest.deleteHiddenInput(options.name);

		if (suggest.getValue != undefined) {
			var selectedItems = suggest.getValue();

			for (var i = 0; i < selectedItems.length; i++) {
				magicSuggest.addHiddenInput(targetForm, selectedItems[i], options.name, i);
				magicSuggest.deleteDefaultHiddenInput(targetForm, options.name, selectedItems[i]);
			}
		}
	}
};
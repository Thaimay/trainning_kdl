$(function() {
  if (typeof magicSuggestCreate !== 'function') {
    var magicSuggestCreate = function (domName, value, data) {
      $(domName).magicSuggest({
        placeholder: '',
        value: value,
        data: data,
      })
    };
  }

  if ($("#building-form").length) {
    var $target = $("#building-form");

    magicSuggestCreate(
      $target.find(".j-form-corporation-group"),
      [],
      [
        "法人グループA",
        "法人グループB"
      ]
    );

    magicSuggestCreate(
      $target.find(".j-form-corporation"),
      [],
      [
        "法人A",
        "法人B"
      ]
    );

    magicSuggestCreate(
      $target.find(".j-form-building"),
      [],
      [
        "館A",
        "館B"
      ]
    );

    magicSuggestCreate(
      $target.find('.j-form-keyman'),
      [],
      [
        "キーマンA",
        "キーマンB"
      ]
    )
  }

  if ($('#shop-search').length) {
    var $target = $('#shop-search');
    magicSuggestCreate(
      $target.find(".j-form-shop-case"),
      [],
      [
        "店舗A",
        "店舗B"
      ]
    );
  }

  if ($('.j-tenant').length) {
    var $target = $('.j-tenant');

    magicSuggestCreate(
      $target.find(".j-form-tenant"),
      [],
      [
        "テナントA",
        "テナントB"
      ]
    );
  }
})
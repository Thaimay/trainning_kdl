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

  if ($("#negotiation-form").length) {
    var $target = $("#negotiation-form");

    magicSuggestCreate(
      $target.find(".j-form-case"),
      [
        "アピタ長津田契約延長交渉",
        "アピタ新潟西店契約延長交渉"
      ],
      [
        "アピタ長津田契約延長交渉",
        "アピタ新潟西店契約延長交渉"
      ]
    );

    magicSuggestCreate(
      $target.find(".j-form-meeting-corporation"),
      [
        "株式会社ユニー"
      ],
      [
        "株式会社ユニー"
      ]
    );

    magicSuggestCreate(
      $target.find(".j-meeting-place"),
      [
        "大市晃宏"
      ],
      [
        "大市晃宏"
      ]
    );

    magicSuggestCreate(
      $target.find(".j-form-visitor"),
      [
        "森本亜佐子"
      ],
      [
        "森本亜佐子"
      ]
    );

    magicSuggestCreate(
      $target.find(".j-form-brand"),
      [
        "OLLINKAR雑貨",
        "アダバットM海外",
        "リミテッド エディション by オンシーズン"
      ],
      [
        "OLLINKAR雑貨",
        "アダバットM海外",
        "リミテッド エディション by オンシーズン"
      ]
    );

    magicSuggestCreate(
      $target.find(".j-form-notification"),
      [
        "通知先1",
        "通知先2",
        "通知先3"
      ],
      [
        "通知先1",
        "通知先2",
        "通知先3"
      ]
    )
  }
})
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

  /**
   * @desc フォームの補完処理
   */
  if ($("#negotiation-form").length) {
    var $target = $("#negotiation-form");

    magicSuggestCreate(
      $target.find(".j-form-case"),
      [],
      [
        "アピタ長津田契約延長交渉",
        "アピタ新潟西店契約延長交渉"
      ]
    );

    magicSuggestCreate(
      $target.find(".j-form-meeting-corporation"),
      [],
      [
        "株式会社ユニー"
      ]
    );

    magicSuggestCreate(
      $target.find(".j-meeting-place"),
      [],
      [
        "大市晃宏"
      ]
    );

    magicSuggestCreate(
      $target.find(".j-form-visitor"),
      [],
      [
        "森本亜佐子"
      ]
    );

    magicSuggestCreate(
      $target.find(".j-form-brand"),
      [],
      [
        "OLLINKAR雑貨",
        "アダバットM海外",
        "リミテッド エディション by オンシーズン"
      ]
    );

    magicSuggestCreate(
      $target.find(".j-form-building"),
      [],
      [
        "館A",
        "館B",
      ]
    );

    magicSuggestCreate(
      $target.find(".j-form-shop"),
      [],
      [
        "店舗A",
        "店舗B",
      ]
    );

    magicSuggestCreate(
      $target.find(".j-form-corporation"),
      [],
      [
        "法人A",
        "法人B",
      ]
    );

    magicSuggestCreate(
      $target.find(".j-form-staff-interview"),
      [],
      [
        "スタッフA",
        "スタッフB",
      ]
    );

    magicSuggestCreate(
      $target.find(".j-form-notification"),
      [],
      [
        "通知先1",
        "通知先2",
        "通知先3"
      ]
    )

    magicSuggestCreate(
      $target.find(".j-form-negotiation-building"),
      [],
      [
        "館A",
        "館B",
      ]
    );
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
      $target.find(".j-form-shop-development"),
      [],
      [
        "店舗開発A",
        "店舗開発B"
      ]
    );

    magicSuggestCreate(
      $target.find(".j-form-branch-sales"),
      [],
      [
        "支店営業A",
        "支店営業B"
      ]
    );

    magicSuggestCreate(
      $target.find(".j-form-sales-channel"),
      [],
      [
        "SC",
        "NSC",
        "駅FB",
        "百貨店"
      ]
    );

    magicSuggestCreate(
      $target.find(".j-form-sales-channel-division-sc"),
      [],
      [
        "SC1",
        "SC2"
      ]
    );

    magicSuggestCreate(
      $target.find(".j-form-block"),
      [],
      [
        "ブロックA",
        "ブロックB"
      ]
    );

    magicSuggestCreate(
      $target.find(".j-form-area"),
      [],
      [
        "エリアA",
        "エリアB"
      ]
    );
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

  if ($('.j-form-Prefectures').length) {
    magicSuggestCreate(
      $('.j-form-Prefectures'),
      [],
      [
        "北海道",
        "青森県",
        "岩手県",
        "秋田県",
        "宮城県",
        "山形県",
        "福島県",
        "栃木県",
        "群馬県",
        "茨城県",
        "埼玉県",
        "東京都",
        "千葉県",
        "神奈川県",
        "山梨県",
        "長野県",
        "新潟県",
        "富山県",
        "石川県",
        "福井県",
        "静岡県",
        "岐阜県",
        "愛知県",
        "三重県",
        "滋賀県",
        "京都府",
        "大阪府",
        "兵庫県",
        "奈良県",
        "和歌山県",
        "徳島県",
        "香川県",
        "愛媛県",
        "高知県",
        "鳥取県",
        "島根県",
        "岡山県",
        "広島県",
        "山口県",
        "福岡県",
        "佐賀県",
        "長崎県",
        "大分県",
        "熊本県",
        "宮崎県",
        "鹿児島県",
        "沖縄県",
      ]
    );
  }
});
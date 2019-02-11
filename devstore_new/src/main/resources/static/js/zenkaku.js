
const HANKAKU_KATAKANA = [ '｡', '｢', '｣', '､', '･', 'ｦ', 'ｧ', 'ｨ', 'ｩ', 'ｪ', 'ｫ', 'ｬ', 'ｭ',
    'ｮ', 'ｯ', 'ｰ', 'ｱ', 'ｲ', 'ｳ', 'ｴ', 'ｵ', 'ｶ', 'ｷ', 'ｸ', 'ｹ', 'ｺ', 'ｻ', 'ｼ', 'ｽ', 'ｾ', 'ｿ', 'ﾀ', 'ﾁ', 'ﾂ',
    'ﾃ', 'ﾄ', 'ﾅ', 'ﾆ', 'ﾇ', 'ﾈ', 'ﾉ', 'ﾊ', 'ﾋ', 'ﾌ', 'ﾍ', 'ﾎ', 'ﾏ', 'ﾐ', 'ﾑ', 'ﾒ', 'ﾓ', 'ﾔ', 'ﾕ', 'ﾖ', 'ﾗ',
    'ﾘ', 'ﾙ', 'ﾚ', 'ﾛ', 'ﾜ', 'ﾝ', 'ﾞ', 'ﾟ' ];

const ZENKAKU_KATAKANA = [ '。', '「', '」', '、', '・', 'ヲ', 'ァ', 'ィ', 'ゥ', 'ェ', 'ォ', 'ャ', 'ュ',
    'ョ', 'ッ', 'ー', 'ア', 'イ', 'ウ', 'エ', 'オ', 'カ', 'キ', 'ク', 'ケ', 'コ', 'サ', 'シ', 'ス', 'セ', 'ソ', 'タ', 'チ', 'ツ',
    'テ', 'ト', 'ナ', 'ニ', 'ヌ', 'ネ', 'ノ', 'ハ', 'ヒ', 'フ', 'ヘ', 'ホ', 'マ', 'ミ', 'ム', 'メ', 'モ', 'ヤ', 'ユ', 'ヨ', 'ラ',
    'リ', 'ル', 'レ', 'ロ', 'ワ', 'ン', '゛', '゜' ];

const HANKAKU_KATAKANA_FIRST_CHAR = HANKAKU_KATAKANA[0];

const HANKAKU_KATAKANA_LAST_CHAR = HANKAKU_KATAKANA[HANKAKU_KATAKANA.length - 1];

/**
* 半角カタカナから全角カタカナへ変換します。
* 
* @param c
*            変換前の文字
* @return 変換後の文字
*/
function hankakuKatakanaToZenkakuKatakana(c) {
    if (c.charCodeAt(0) >= HANKAKU_KATAKANA_FIRST_CHAR.charCodeAt(0) && c.charCodeAt(0) <= HANKAKU_KATAKANA_LAST_CHAR.charCodeAt(0)) {
        return ZENKAKU_KATAKANA[c.charCodeAt(0) - HANKAKU_KATAKANA_FIRST_CHAR.charCodeAt(0)];
    } else {
        return c;
    }
}

/**
* 2文字目が濁点・半濁点で、1文字目に加えることができる場合は、合成した文字を返します。 合成ができないときは、c1を返します。
* 
* @param c1
*            変換前の1文字目
* @param c2
*            変換前の2文字目
* @return 変換後の文字
*/
function mergeChar(c1, c2) {
if (c2 == 'ﾞ') {
    if ("ｶｷｸｹｺｻｼｽｾｿﾀﾁﾂﾃﾄﾊﾋﾌﾍﾎ".indexOf(c1) > 0) {
        switch (c1) {
        case 'ｶ':
            return 'ガ';
        case 'ｷ':
            return 'ギ';
        case 'ｸ':
            return 'グ';
        case 'ｹ':
            return 'ゲ';
        case 'ｺ':
            return 'ゴ';
        case 'ｻ':
            return 'ザ';
        case 'ｼ':
            return 'ジ';
        case 'ｽ':
            return 'ズ';
        case 'ｾ':
            return 'ゼ';
        case 'ｿ':
            return 'ゾ';
        case 'ﾀ':
            return 'ダ';
        case 'ﾁ':
            return 'ヂ';
        case 'ﾂ':
            return 'ヅ';
        case 'ﾃ':
            return 'デ';
        case 'ﾄ':
            return 'ド';
        case 'ﾊ':
            return 'バ';
        case 'ﾋ':
            return 'ビ';
        case 'ﾌ':
            return 'ブ';
        case 'ﾍ':
            return 'ベ';
        case 'ﾎ':
            return 'ボ';
        }
    }
} else if (c2 == 'ﾟ') {
    if ("ﾊﾋﾌﾍﾎ".indexOf(c1) > 0) {
        switch (c1) {
        case 'ﾊ':
            return 'パ';
        case 'ﾋ':
            return 'ピ';
        case 'ﾌ':
            return 'プ';
        case 'ﾍ':
            return 'ペ';
        case 'ﾎ':
            return 'ポ';
        }
    }
}
return c1;
}

/**
* 文字列中の半角カタカナを全角カタカナに変換します。
* 
* @param s
*            変換前文字列
* @return 変換後文字列
*/
function toZenkaku(s) {
    if (s.length == 0) {
        return s;
    } else if (s.length == 1) {
        return hankakuKatakanaToZenkakuKatakana(s.charAt(0)) + "";
    } else {
        var i = 0;
        for (i = 0; i < s.length - 1; i++) {
            var originalChar1 = s.charAt(i);
            var originalChar2 = s.charAt(i + 1);
            var margedChar = mergeChar(originalChar1, originalChar2);
            if (margedChar != originalChar1) {
                s = setCharAt(s, i, margedChar);
                s.deleteCharAt(i + 1);
            } else {
                var convertedChar = hankakuKatakanaToZenkakuKatakana(originalChar1);
                if (convertedChar != originalChar1) {
                    s = setCharAt(s ,i, convertedChar);
                }
            }
        }
        if (i < s.length) {
            var originalChar1 = s.charAt(i);
            var convertedChar = hankakuKatakanaToZenkakuKatakana(originalChar1);
            if (convertedChar != originalChar1) {
                s = setCharAt(s, i, convertedChar);
            }
        }
        return s.replace("･", "・");
    }
}

function setCharAt(str, index, chr) {
    if(index > str.length-1) return str;
    return str.substr(0,index) + chr + str.substr(index+1);
}

function deleteCharAt(str, pos){
    return str = str.substr(0, pos) + str.substr(pos + 1);
}
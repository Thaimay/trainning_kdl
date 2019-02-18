package jp.co.world.storedevelopment.batch.constant;

public enum TodoContent {

    /**
     * 4:商談報告 商談 商談予定日を過ぎても、商談報告が入力されていない
     */
    NEGOTIATION_REPORT("の報告を入力してください。"),

    /**
     * 5:館未訪問 全館（自分担当分） XX日以上、商談・訪問実施の入力がない
     */
    BUILDING_UNVISITED("担当している館の商談・訪問実施がしばらく入力されておりません。"),
    
	/**
	 * 6:館期待値
	 */
	BUILDING_EXPECTED_VALUE("館期待値(月坪売上)が実際の月坪売上にしめる割合を下回っております。"),

    /**
     * 9:マスタ 館リニューアル情報 館のリニューアル情報が入っていない
     */
    BUILDING_ACTIVATION("館のリニューアル情報がしばらく作成されておりません。"),
	
	/**
	 * 10:館売上 館の売上が入っていない
	 */
	BUILDING_SALES_NOT_INPUT("館の売上がしばらくの間、入力されておりません。");
	
	private final String code;
	
	private TodoContent(String code) {
		this.code = code;
	}
	
	@Override
	public String toString() {
		return this.code;
	}
}

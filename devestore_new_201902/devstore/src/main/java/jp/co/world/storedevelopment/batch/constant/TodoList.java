package jp.co.world.storedevelopment.batch.constant;

public enum TodoList {

    /**
     * 0:ステータス未更新 案件ステータス
     */
    PROJECT_STATUS(0),
    
    /**
     * 1:ステータス未更新 外部公開日程
     */
    EXTERNAL_RELEASE_SCHEDULE(1),
    
    /**
     * 2:案件実施日 OPEN日 契約進捗：内容合意済みの状態でOPEN日が未入力
     */
    PROJECT_OPEN_DATE(2),
    
    /**
     * 3:案件実施日 実施日
     */
    PROJECT_DATE(3),
    
    /**
     * 4:商談報告 商談 商談予定日を過ぎても、商談報告が入力されていない
     */
    NEGOTIATION_REPORT(4),
    
    /**
     * 5:館未訪問 全館（自分担当分） XX日以上、商談・訪問実施の入力がない
     */
    BUILDING_UNVISITED(5),
    
    /**
     * 6:館期待値 既存全館（自分担当分） 館期待値売上のXX%未満
     */
    BUILDING_EXPECTED_VALUE(6),
    
    /**
     * 7:その他 見解系他 物件検討会予定日の○○日前に揃っていない
     */
    OTHER_TODO(7),
    
    /**
     * 8:定借満了 定借切れの2年前リスク判定
     */
    FIXED(8),
    
    /**
     * 9:マスタ 館リニューアル情報 館のリニューアル情報が入っていない
     */
    BUILDING_ACTIVATION(9),
    
    /**
     * 10:館売上 館の売上が入っていない
     */
    BUILDING_SALES_NOT_INPUT(10),
    
    /**
     * 11:タスク追加 自分にタスクが指示された 案件で自分にタスクが指定されてた
     */
    ADDED_TASK(11);
    
    private final Integer code;
    
    private TodoList(Integer code) {
        this.code = code;
    }
    
    public Integer getTodoDetailsDivisionId() {
        return this.code;
    }
}

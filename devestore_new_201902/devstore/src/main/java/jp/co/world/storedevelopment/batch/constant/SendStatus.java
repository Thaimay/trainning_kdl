package jp.co.world.storedevelopment.batch.constant;

/**
 * 配信ステータスの定数クラスです
 * 
 * @author togashi
 *
 */
public enum SendStatus {
    /**
     * 00:下書き
     */
    DRAFT("00"),
    
    /**
     * 01:予約済み
     */
    RESERVED("01"),
    
    /**
     * 02:抽出済み
     */
    EXTRACTED("02"),
    
    /**
     * 03:配信準備
     */
    DELIVERY_RESERVED("03"),
    
    /**
     * 04:配信中
     */
    DURING_DELIVERY("04"),
    
    /**
     * 05:配信完了
     */
    DELIVERY_COMPLETION("05"),
    
    /**
     * 06:抽出エラー
     */
    SELECT_ERROR("06"),
    
    /**
     * 07:配信エラー
     */
    SEND_ERROR("07"),
    
    /**
     * 09:キャンセル
     */
    CANCEL("09"),
    
    /**
     * 10:事前ファイル取り込み待ち
     */
    DELIVERY_RESERVE_WAIT("10");
    
    private final String code;
    
    private SendStatus(String code) {
        this.code = code;
    }
    
    @Override
    public String toString() {
        return this.code;
    }
}

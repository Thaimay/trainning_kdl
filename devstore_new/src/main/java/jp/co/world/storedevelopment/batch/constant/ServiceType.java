package jp.co.world.storedevelopment.batch.constant;

public enum ServiceType {

    /**
     * 01:プッシュ通知
     */
    PUSH("01"),
    
    /**
     * 02:お知らせ
     */
    NOTIFICATION("02"),
    
    /**
     * 03:TODO
     */
    TODO("03");
    
    private final String code;
    
    private ServiceType(String code) {
        this.code = code;
    }
    
    @Override
    public String toString() {
        return this.code;
    }
}

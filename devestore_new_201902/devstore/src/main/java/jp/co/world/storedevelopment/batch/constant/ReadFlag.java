package jp.co.world.storedevelopment.batch.constant;

public enum ReadFlag {

    /**
     * 0:未読
     */
    OFF("0"),
    
    /**
     * 1:既読
     */
    ON("1");
    
    private final String code;
    
    private ReadFlag(String code) {
        this.code = code;
    }
    
    @Override
    public String toString() {
        return this.code;
    }
}

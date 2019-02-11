package jp.co.world.storedevelopment.batch.constant;

public enum Flag {
    
    /**
     * 0:OFF
     */
    OFF("0"),
    
    /**
     * 1:ON
     */
    ON("1");
    
    private final String code;
    
    private Flag(String code) {
        this.code = code;
    }
    
    @Override
    public String toString() {
        return this.code;
    }
}

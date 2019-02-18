package jp.co.world.storedevelopment.batch.constant;

public enum DeviceType {

    IOS("ios"),
    
    ANDROID("android");
    
    private final String code;
    
    private DeviceType(String code) {
        this.code = code;
    }
    
    @Override
    public String toString() {
        return this.code;
    }
}

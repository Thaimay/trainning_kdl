package jp.co.world.storedevelopment.batch.constant;

/**
 * ロックフラグ定数
 * 
 * @author togashi
 *
 */
public enum LockFlag {
    /**
     * 通常
     */
    UNLOCK("0"),
    
    /**
     * ロック
     */
    LOCK("1");
    
    private final String code;
    
    private LockFlag(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return this.code;
    }
}

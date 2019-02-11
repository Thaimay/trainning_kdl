package jp.co.world.storedevelopment.model;

import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.MTodoBatchSettingModelMapper;
import jp.co.world.storedevelopment.model.mapper.repository.MTodoBatchSettingRepository;

public class MTodoBatchSetting extends ActiveModel<MTodoBatchSetting> {

    private String name    = "";
    private String value   = "";
    private String remarks = "";
    
    public static Optional<MTodoBatchSetting> externalReleaseInAdvance() {
    	return Optional.ofNullable(new MTodoBatchSettingRepository().findByName("PROJECT_EXTERNAL_RELEASE_IN_ADVANCE"));
    }
    
    public static Optional<MTodoBatchSetting> taskTermInAdvance() {
    	return Optional.ofNullable(new MTodoBatchSettingRepository().findByName("PROJECT_TASK_TERM_IN_ADVANCE"));    	
    }
    
    public static Optional<MTodoBatchSetting> opinionRecordInAdvance() {
    	return Optional.ofNullable(new MTodoBatchSettingRepository().findByName("PROJECT_OPINION_RECORD_IN_ADVANCE"));    	    	
    }
    
    public static Optional<MTodoBatchSetting> opinionTargetProjectCategoryId() {
    	return Optional.ofNullable(new MTodoBatchSettingRepository().findByName("PROJECT_OPINION_TARGET_PROJECT_CATEGORY_ID"));    	    	    	
    }
    
    public static Optional<MTodoBatchSetting> preventRetreat() {
    	return Optional.ofNullable(new MTodoBatchSettingRepository().findByName("PROJECT_PREVENT_RETREAT"));    	    	    	    	
    }
    
    public static Optional<MTodoBatchSetting> closeCategoryId() {
    	return Optional.ofNullable(new MTodoBatchSettingRepository().findByName("PROJECT_CLOSE_CATEGORY_ID"));    	    	    	    	    	
    }
    
    public static Optional<MTodoBatchSetting> startDatetimeExpired() {
    	return Optional.ofNullable(new MTodoBatchSettingRepository().findByName("PROJECT_START_DATETIME_EXPIRED"));    	    	    	    	    	    	
    }
    
    public Integer valueInteger() {
    	return Integer.parseInt(getValue());
    }
    
    /**
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return value
     */
    public String getValue() {
        return value;
    }

    /**
     * 
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 
     * @return remarks
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 
     * @param remarks
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    protected ModelMapper<MTodoBatchSetting> modelMapper(SqlSession session) {
        return session.getMapper(MTodoBatchSettingModelMapper.class);
    }

}

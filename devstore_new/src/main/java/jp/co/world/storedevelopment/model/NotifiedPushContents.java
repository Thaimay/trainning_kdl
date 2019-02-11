package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.NotifiedPushContentsModelMapper;

public class NotifiedPushContents extends ActiveModel<NotifiedPushContents> {

    private Long   userId               = (long) 0;
    private Long   reserveId            = (long) 0;
    private Long   documentId           = (long) 0;
    private String documentCategoryName = "";
    private String imagePath            = "";
    private String readFlag             = "";
    private String title                = "";
    private String serviceType          = "";
    private Long   serviceId            = (long) 0;
    private String newFlag              = "";
    
    @Override
    protected ModelMapper<NotifiedPushContents> modelMapper(SqlSession session) {
        return session.getMapper(NotifiedPushContentsModelMapper.class);
    }

    /**
     * 
     * @return
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 
     * @param userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 
     * @return
     */
    public Long getReserveId() {
        return reserveId;
    }

    /**
     * 
     * @param reserveId
     */
    public void setReserveId(Long reserveId) {
        this.reserveId = reserveId;
    }

    /**
     * 
     * @return
     */
    public Long getDocumentId() {
        return documentId;
    }

    /**
     * 
     * @param documentId
     */
    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    /**
     * 
     * @return
     */
    public String getDocumentCategoryName() {
        return documentCategoryName;
    }

    /**
     * 
     * @param documentCategoryName
     */
    public void setDocumentCategoryName(String documentCategoryName) {
        this.documentCategoryName = documentCategoryName;
    }

    /**
     * 
     * @return
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * 
     * @param imagePath
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * 
     * @return
     */
    public String getReadFlag() {
        return readFlag;
    }

    /**
     * 
     * @param readFlag
     */
    public void setReadFlag(String readFlag) {
        this.readFlag = readFlag;
    }

    /**
     * 
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     */
    public String getServiceType() {
        return serviceType;
    }

    /**
     * 
     * @param serviceType
     */
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    /**
     * 
     * @return
     */
    public Long getServiceId() {
        return serviceId;
    }

    /**
     * 
     * @param serviceId
     */
    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    /**
     * 
     * @return
     */
    public String getNewFlag() {
        return newFlag;
    }

    /**
     * 
     * @param newFlag
     */
    public void setNewFlag(String newFlag) {
        this.newFlag = newFlag;
    }

}

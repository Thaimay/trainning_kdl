package jp.co.world.storedevelopment.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.UserIdListModelMapper;

public class UserIdList extends ActiveModel<UserIdList> {

    private Long sendReserveId        = (long) 0;
    private String userIdListFileName = "";
    private List<String> userNoList;
    
    @Override
    protected ModelMapper<UserIdList> modelMapper(SqlSession session) {
        return session.getMapper(UserIdListModelMapper.class);
    }

    /**
     * 
     * @return
     */
    public Long getSendReserveId() {
        return sendReserveId;
    }

    /**
     * 
     * @param sendReserveId
     */
    public void setSendReserveId(Long sendReserveId) {
        this.sendReserveId = sendReserveId;
    }

    /**
     * 
     * @return
     */
    public String getUserIdListFileName() {
        return userIdListFileName;
    }

    /**
     * 
     * @param userIdListFileName
     */
    public void setUserIdListFileName(String userIdListFileName) {
        this.userIdListFileName = userIdListFileName;
    }

    /**
     * 
     * @return
     */
    public List<String> getUserNoList() {
        return userNoList;
    }

    /**
     * 
     * @param userNoList
     */
    public void setUserNoList(List<String> userNoList) {
        this.userNoList = userNoList;
    }

}

package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.InvalidDeviceToken;

public interface InvalidDeviceTokenRepositoryMapper extends RepositoryMapper<InvalidDeviceToken> {

    @Select("SELECT * FROM invalid_device_token WHERE screening_flg = 0 AND is_deleted IS false")
    List<InvalidDeviceToken> getScreeningTargetList();

}

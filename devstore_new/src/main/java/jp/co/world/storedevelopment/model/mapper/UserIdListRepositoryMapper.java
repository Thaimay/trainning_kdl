package jp.co.world.storedevelopment.model.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.UserIdList;

public interface UserIdListRepositoryMapper extends RepositoryMapper<UserIdList> {
    @Select("SELECT * FROM user_id_list WHERE send_reserve_id = '${id}'")
    UserIdList findByReserveId(@Param("id") Long id);

}

package jp.co.world.storedevelopment.model.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.MTodoBatchSetting;

public interface MTodoBatchSettingRepositoryMapper extends RepositoryMapper<MTodoBatchSetting> {

    @Select("select * from m_todo_batch_setting where name = '${name}'")
	MTodoBatchSetting findByName(@Param("name") String name);

}

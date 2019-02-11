package jp.co.world.storedevelopment.model.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.IYakata;

public interface IYakataRepositoryMapper extends RepositoryMapper<IYakata> {
	@Select("select * from i_yakata where is_deleted is false and building_cd = '${code}'")
	IYakata findByImportCode(@Param("code") String buildingCd);
}

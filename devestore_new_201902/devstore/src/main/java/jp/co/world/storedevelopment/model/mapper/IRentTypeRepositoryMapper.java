package jp.co.world.storedevelopment.model.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.IRentType;

public interface IRentTypeRepositoryMapper extends RepositoryMapper<IRentType> {
	@Select("select * from i_rent_type where is_deleted is false and rent_type_cd = '${code}'")
	IRentType findByImportCode(@Param("code") String rentTypeCd);
}

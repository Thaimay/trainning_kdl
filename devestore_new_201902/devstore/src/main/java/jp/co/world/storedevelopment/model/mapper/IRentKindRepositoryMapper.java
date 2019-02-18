package jp.co.world.storedevelopment.model.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.IRentKind;

public interface IRentKindRepositoryMapper extends RepositoryMapper<IRentKind> {
	@Select("select * from i_rent_kind where is_deleted is false and rent_kind_cd = '${code}'")
	IRentKind findByImportCode(@Param("code") String rentKindCd);
}

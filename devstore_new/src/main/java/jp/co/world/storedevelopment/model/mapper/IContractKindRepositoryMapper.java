package jp.co.world.storedevelopment.model.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.IContractKind;

public interface IContractKindRepositoryMapper extends RepositoryMapper<IContractKind> {
	@Select("select * from i_contract_kind where is_deleted is false and contract_kind_cd = '${code}'")
	IContractKind findByImportCode(@Param("code") String contractKindCd);
}

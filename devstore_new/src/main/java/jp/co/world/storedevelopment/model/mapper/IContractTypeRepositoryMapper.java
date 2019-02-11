package jp.co.world.storedevelopment.model.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.IContractType;

public interface IContractTypeRepositoryMapper extends RepositoryMapper<IContractType> {
	@Select("select * from i_contract_type where is_deleted is false and contract_type_id = '${contractTypeId}'")
	IContractType getByContractTypeId(@Param("contractTypeId") Long contractTypeId);

	@Select("select * from i_contract_type where is_deleted is false and contract_type_cd = '${code}'")
	IContractType findByImportCode(@Param("code") String contractTypeCd);
}

package jp.co.world.storedevelopment.model.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.IFeeTypeDivision;

public interface IFeeTypeDivisionRepositoryMapper extends RepositoryMapper<IFeeTypeDivision> {
	@Select("select * from i_fee_type_division where is_deleted is false and fee_type_division_id = '${code}'")
	IFeeTypeDivision findByImportCode(@Param("code") String feeTypeDivisionId);
}

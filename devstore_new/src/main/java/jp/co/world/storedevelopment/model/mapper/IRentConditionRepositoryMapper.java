package jp.co.world.storedevelopment.model.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.IRentCondition;

public interface IRentConditionRepositoryMapper extends RepositoryMapper<IRentCondition> {
	@Select("select * from i_rent_condition where is_deleted is false and rent_condition_id = '${code}'")
	IRentCondition findByImportCode(@Param("code") String rentConditionId);
}

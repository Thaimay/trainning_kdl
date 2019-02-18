package jp.co.world.storedevelopment.model.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.ISettlementStatus;

public interface ISettlementStatusRepositoryMapper extends RepositoryMapper<ISettlementStatus> {
	@Select("select * from i_settlement_status where is_deleted is false and settlement_status_id = '${code}'")
	ISettlementStatus findByImportCode(@Param("code") String settlementStatusId);
}

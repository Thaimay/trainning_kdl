package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import jp.co.world.storedevelopment.model.NegotiationInterviewAccount;

public interface NegotiationInterviewAccountRepositoryMapper
		extends NegotiationRelatedRepositoryMapper<NegotiationInterviewAccount> {

	@Select("SELECT * FROM negotiation_interview_account WHERE negotiation_id = ${id}")
	List<NegotiationInterviewAccount> findByNegotiationId(@Param("id") Long id);

	@Select("select * from negotiation_interview_account where is_deleted = false and account_id = #{accountId}")
	List<NegotiationInterviewAccount> findByAccountId(@Param("accountId") Long accountId);
}

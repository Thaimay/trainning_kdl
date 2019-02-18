package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import jp.co.world.storedevelopment.model.NegotiationInterviewCorporation;

public interface NegotiationInterviewCorporationRepositoryMapper
		extends NegotiationRelatedRepositoryMapper<NegotiationInterviewCorporation> {
	@Select("select * from negotiation_interview_corporation where is_deleted = false and corporation_id = #{corporationId} and negotiation_id NOT IN (SELECT id from negotiation n where n.is_deleted = true)")
	List<NegotiationInterviewCorporation> findByCorporationId(@Param("corporationId") Long corporationId);
}

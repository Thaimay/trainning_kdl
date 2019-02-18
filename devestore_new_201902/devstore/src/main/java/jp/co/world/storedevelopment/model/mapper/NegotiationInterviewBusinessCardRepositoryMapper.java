package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import jp.co.world.storedevelopment.model.NegotiationInterviewBusinessCard;

public interface NegotiationInterviewBusinessCardRepositoryMapper
		extends NegotiationRelatedRepositoryMapper<NegotiationInterviewBusinessCard> {
	@Select("select * from negotiation_interview_business_card where is_deleted = false and business_card_id = #{businessCardId} and negotiation_id NOT IN (SELECT id from negotiation n where n.is_deleted = true)")
	List<NegotiationInterviewBusinessCard> findByBusinessCardId(@Param("businessCardId") Long businessCardId);
}

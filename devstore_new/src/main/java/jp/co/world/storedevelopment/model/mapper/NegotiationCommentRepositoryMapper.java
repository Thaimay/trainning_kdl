package jp.co.world.storedevelopment.model.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import jp.co.world.storedevelopment.model.Negotiation;
import jp.co.world.storedevelopment.model.NegotiationComment;

public interface NegotiationCommentRepositoryMapper extends NegotiationRelatedRepositoryMapper<NegotiationComment> {
    @Select("select COUNT(id) from NEGOTIATION_COMMENT where negotiation_id = ${negotiation.id}")
    int countByNegotiation(@Param("negotiation") Negotiation negotiation);
}

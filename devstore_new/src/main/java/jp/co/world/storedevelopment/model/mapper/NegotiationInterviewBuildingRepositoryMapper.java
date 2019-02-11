package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import jp.co.world.storedevelopment.model.NegotiationInterviewBuilding;

public interface NegotiationInterviewBuildingRepositoryMapper
	extends NegotiationRelatedRepositoryMapper<NegotiationInterviewBuilding> {

    @Select("SELECT * FROM negotiation_interview_building WHERE negotiation_id = '${id}' ")
    NegotiationInterviewBuilding findByNegotiationId(@Param("id") Long id);

    @Select("select * from negotiation_interview_building where is_deleted = false and building_id = #{buildingId} and negotiation_id NOT IN (SELECT id from negotiation n where n.is_deleted = true)")
	List<NegotiationInterviewBuilding> findByBuildingId(@Param("buildingId") Long buildingId);

}

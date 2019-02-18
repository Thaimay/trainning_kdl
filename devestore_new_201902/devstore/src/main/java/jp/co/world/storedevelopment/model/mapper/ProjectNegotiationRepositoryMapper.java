package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import jp.co.world.storedevelopment.model.Negotiation;
import jp.co.world.storedevelopment.model.ProjectNegotiation;

public interface ProjectNegotiationRepositoryMapper extends ProjectRelatedRepositoryMapper<ProjectNegotiation> {

	@Select("select * from project_negotiation where negotiation_id = #{negotiation.id} order by update_datetime desc")
	List<ProjectNegotiation> findOfNegotiation(@Param("negotiation") Negotiation negotiation);

	@Select("select * from project_negotiation where is_deleted = false and project_id = #{projectId} and negotiation_id NOT IN (SELECT id from negotiation n where n.is_deleted = true)")
	List<ProjectNegotiation> findNegotiationByProjectId(@Param("projectId") Long projectId);

	@Delete("delete from project_negotiation where negotiation_id = #{negotiation.id}")
	int deleteByNegotiation(@Param("negotiation") Negotiation negotiation);

}

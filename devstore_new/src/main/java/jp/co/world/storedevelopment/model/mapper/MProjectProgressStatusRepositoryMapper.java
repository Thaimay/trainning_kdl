package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.MProjectProgressStatus;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationRelationDTO;

public interface MProjectProgressStatusRepositoryMapper extends RepositoryMapper<MProjectProgressStatus> {

	@Select("select * from m_project_progress_status" +
			" where is_deleted = false" +
			"  and category = '${category}'" +
			"  and project_category_id = ${projectCategoryId} order by priority")
	public List<MProjectProgressStatus> getMProjectProgressStatusList(@Param("category") String category, @Param("projectCategoryId") Long projectCategoryId);

	@Select("select * from m_project_progress_status" +
			" where is_deleted = false" +
			"  and category = '${category}'" +
			"  and project_category_id = ${projectCategoryId}" +
			"  and priority = ${priority} limit 1")
	public MProjectProgressStatus getMProjectProgressStatus(@Param("category") String category, @Param("projectCategoryId") Long projectCategoryId, @Param("priority") Integer priority);

	@Select("select x.* from m_project_progress_status x" +
			" left join m_project_progress_status y" +
			"  on x.category = y.category and (x.priority > y.priority or x.priority = y.priority and x.id > y.id)" +
			" where x.is_deleted = false and y.id is null and x.project_category_id = ${projectCategoryId}")
	public List<MProjectProgressStatus> getInitMProjectProgressStatus(@Param("projectCategoryId") Long projectCategoryId);

	@Select("select * from m_project_progress_status" +
			" where is_deleted = false" +
			"  and category = '${category}'"+
			"  and project_category_id = ${projectCategoryId}" +
			"  and code = '${code}' limit 1")
	public MProjectProgressStatus getCurrentProgressByCode(@Param("category") String category, @Param("projectCategoryId") Long projectCategoryId, @Param("code") String code);

	@Select("select * from m_project_progress_status" +
			" where is_deleted = false" +
			"  and category = '${category}'" +
			"  and project_category_id = ${projectCategoryId}" +
			"  and priority > ${priority} order by priority limit 1")
	public MProjectProgressStatus getNextProgress(@Param("category") String category, @Param("projectCategoryId") Long projectCategoryId, @Param("priority") Integer priority);

	@Select("select * from m_project_progress_status" +
			" where is_deleted = false" +
			"  and category = '${category}'" +
			"  and project_category_id = ${projectCategoryId} order by priority limit 1")
	public MProjectProgressStatus getFirstProgress(@Param("category") String category, @Param("projectCategoryId") Long projectCategoryId);

	@Select("select pc.name || '_' || mpps.name as name, mpps.project_category_id as id" 
			+ " from m_project_progress_status mpps "
			+ " left join project_category pc on pc.id = mpps.project_category_id "
			+ " where mpps.category = 'COMPANY'")
	public List<NegotiationRelationDTO> getOfficeProjectProgress();

	@Select("select pc.name || '_' || mpps.name as name, mpps.project_category_id as id"
			+ " from m_project_progress_status mpps "
			+ " left join project_category pc on pc.id = mpps.project_category_id "
			+ " where mpps.category = 'NEGOTIATION'")
	public List<NegotiationRelationDTO> getNegotiationProjectProgress();

}

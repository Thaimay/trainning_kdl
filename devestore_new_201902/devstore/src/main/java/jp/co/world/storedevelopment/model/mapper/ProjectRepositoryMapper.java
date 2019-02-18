package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.Building;
import jp.co.world.storedevelopment.model.IShop;
import jp.co.world.storedevelopment.model.Project;
import jp.co.world.storedevelopment.pc.controller.form.ProjectFindForm;
import jp.co.world.storedevelopment.sp.controller.dto.ProjectFindFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.RelatedFindFromProjectDTO;

public interface ProjectRepositoryMapper extends RepositoryMapper<Project> {

	@Select("select * from project where is_deleted = false")
	List<Project> findAllNotDeleted();
	
	List<Project> findByNameForNegotiationFile(@Param("text") String text, @Param("hankaku") String hankaku,
			@Param("params") List<Long> params, @Param("limit") int limit);

	@Select("select * from project WHERE id = ${projectId}")
	Project findForNegotiationById(@Param("projectId") Long projectId, @Param("accountCd") String accountCd,
			@Param("accountId") Long accountId);

	@Select("select * from project where is_deleted = false and building_id = #{buildingId} order by update_datetime desc")
	List<Project> getProjectListByBuildingId(@Param("buildingId") Long buildingId);

	@Select("select * from project where is_deleted = false and (building_id = #{building.id}) and operation_division = '進行中'")
	List<Project> findByBuilding(@Param("building") Building model);

	@Select("select * from project where is_deleted = false and (i_shop_id is not null OR shop_cd != '') and operation_division = '進行中'")
	List<Project> findShopCopyTarget();
	
	@Select("select * from project where is_deleted = false and (i_shop_id = #{shop.id} OR shop_cd = #{shop.shopCd}) and operation_division = '進行中'")
	List<Project> findByShop(@Param("shop") IShop model);
	
	@Select("select * from project where id in (select project_id from project_negotiation where negotiation_id = ${negotiationId})")
	List<Project> findByNegotiationId(@Param("negotiationId") Long id);

	@Select("select * from project WHERE is_deleted = false AND operation_division = '進行中' AND external_release_confirm = 'false' AND external_release_start_date is not null")
	List<Project> findAlert();

	@Select("select * from project WHERE is_deleted = false AND operation_division = '進行中' AND start_date is null AND project_category_id IN (${ids})")
	List<Project> findStartDateAlert(@Param("ids") String ids);

	@Select("select distinct project.* from project "
			+ " left join project_category on (project.project_category_id = project_category.id) "
			+ " left join project_schedule on (project.id = project_schedule.project_id) "
			+ "  where project.is_deleted = false and "
			+ "        project.implementation_datetime <= current_date and "
			+ "        project.operation_division = '進行中' and " + "        project_category.name = '出店'")
	List<Project> findOpeningStoreProject();

	List<Project> findForSP(ProjectFindFormDTO dto);

	List<Project> findForSuggest(RelatedFindFromProjectDTO dto);

	List<Project> findForPC(ProjectFindForm dto);

	int getCountFindForPC(ProjectFindForm dto);

	int getCountFindForSP(ProjectFindFormDTO dto);

}
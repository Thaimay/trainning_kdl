package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.Building;
import jp.co.world.storedevelopment.pc.controller.form.BuildingFindForm;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingDetailDTO;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingFindByTextFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingFindFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingListDTO;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingListParentDTO;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingRelationFindByTextFormDTO;

/**
 * @author hungdh
 *
 */
public interface BuildingRepositoryMapper extends RepositoryMapper<Building> {

	List<BuildingListDTO> find(BuildingFindFormDTO dto);

	List<Building> findForPC(BuildingFindForm dto);

	Integer getCountFindForPC(BuildingFindForm dto);

	BuildingDetailDTO findBuildingById(Long id);

	@Select("select * from BUILDING where building_group_id = '${buildingCd}'")
	List<Building> getListChildrenBuilding(@Param("buildingCd") String buildingCd);

	@Select("select * from BUILDING where name like '${building.text}%' ")
	List<Building> findByText(@Param("building") BuildingFindByTextFormDTO dto);

	@Select("select * from BUILDING where name like '%${name}%' or name like '%${hankaku}%' limit ${limit}")
	List<Building> findByName(@Param("name") String text, @Param("hankaku") String hankaku, @Param("limit") int limit);

	@Select("select * from building where is_deleted is false and building_cd = '${buildingCd}'")
	Building getBuildingByBuildingCd(@Param("buildingCd") String buildingCd);

	@Select("select * from building where is_deleted is false and origin_building_id = '${originBuildingId}'")
	Building getBuildingByOriginBuildingId(@Param("originBuildingId") Long originBuildingId);

	@Select("select * from building where is_deleted is false")
	List<Building> getBuildingList();

	@Select("select * from BUILDING where (name like '%${building.text}%' or name like '%${building.textHankaku}%') and is_building_group is true limit ${limit}")
	List<BuildingListParentDTO> findByParentText(@Param("building") BuildingRelationFindByTextFormDTO dto, @Param("limit") int limit);

	List<Long> getListAccountIdForFile();

	void updateBuildingHistory(@Param("building") Building building);

	Building findBuildingHistoryById(Long id);

	@Select("select * from building_history where is_deleted is false and building_cd = '${buildingCd}'")
	List<Building> getBuildingHistoryList(@Param("buildingCd") String buildingCd);

	List<Building> findByNameForNegotiationFile(@Param("text") String text, @Param("hankaku") String hankaku, @Param("params") List<Long> params, @Param("limit") int limit);
}

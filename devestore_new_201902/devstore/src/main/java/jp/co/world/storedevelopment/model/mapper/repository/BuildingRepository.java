package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;
import com.world.storedevelopment.utils.HankakuUtils;
import com.world.storedevelopment.utils.ZenkakuUtils;

import jp.co.world.storedevelopment.Application;
import jp.co.world.storedevelopment.model.Building;
import jp.co.world.storedevelopment.model.mapper.BuildingRepositoryMapper;
import jp.co.world.storedevelopment.pc.controller.form.BuildingFindForm;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingDetailDTO;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingFindFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingHistoryListDTO;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingListDTO;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingListParentDTO;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingRelationFindByTextFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationRelationDTO;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationRelationFindByParamFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationRelationFindByTextFormDTO;

/**
 * @author hungdh
 *
 */
public class BuildingRepository extends Repository<Building, BuildingRepositoryMapper> {
	@Override
	protected BuildingRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(BuildingRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(Building.class);
	}

	public List<BuildingListDTO> find(BuildingFindFormDTO dto) {
		return execute((mapper) -> {
			return mapper.find(dto);
		});
	}

	public List<Building> findForPC(BuildingFindForm dto) {
		return execute((mapper) -> {
			return mapper.findForPC(dto);
		});
	}

	public BuildingDetailDTO findBuildingById(Long id) {
		return execute((mapper) -> {
			return mapper.findBuildingById(id);
		});
	}

	public List<NegotiationRelationDTO> findByText(NegotiationRelationFindByTextFormDTO dto) {
		return execute((mapper) -> {
			List<Building> list = mapper.findByName(dto.getText(), HankakuUtils.toHankaku(dto.getText()),
					Application.SUGGEST_LIMIT_SIZE);

			return list.stream().map(b -> new NegotiationRelationDTO(b.getId(), ZenkakuUtils.toZenkaku(b.getName())))
					.collect(Collectors.toList());
		});
	}

	public List<BuildingListParentDTO> findByParentText(BuildingRelationFindByTextFormDTO dto) {
		return execute((mapper) -> {
			return mapper.findByParentText(dto, Application.SUGGEST_LIMIT_SIZE);
		});
	}

	public List<Building> getListChildrenBuilding(String buildingCd) {
		return execute((mapper) -> {
			return mapper.getListChildrenBuilding(buildingCd);
		});
	}

	public Integer getCountFindForPC(BuildingFindForm dto) {
		return execute((mapper) -> {
			return mapper.getCountFindForPC(dto);
		});
	}

	public List<Building> getBuildingList() {
		return execute((mapper) -> {
			return mapper.getBuildingList();
		});
	}

	public Building getBuildingByBuildingCd(String buildingCd) {
		return execute((mapper) -> {
			return mapper.getBuildingByBuildingCd(buildingCd);
		});
	}

	public Building getBuildingByOriginBuildingId(Long originBuildingId) {
		return execute((mapper) -> {
			return mapper.getBuildingByOriginBuildingId(originBuildingId);
		});
	}

	public List<Long> getListAccountIdForFile() {
		return execute((mapper) -> {
			return mapper.getListAccountIdForFile();
		});
	}

	public void updateBuildingHistory(Building building) {
		execute((mapper) -> {
			mapper.updateBuildingHistory(building);
		});
	}

	public Building findBuildingHistoryById(Long id) {
		return execute((mapper) -> {
			return mapper.findBuildingHistoryById(id);
		});
	}

	public List<BuildingHistoryListDTO> getBuildingHistoryList(String buildingCd) {
		return execute((mapper) -> {
			return mapper.getBuildingHistoryList(buildingCd).stream().map(x -> new BuildingHistoryListDTO(x))
					.sorted((b2, b1) -> b1.getId().compareTo(b2.getId())).collect(Collectors.toList());
		});
	}

	public List<NegotiationRelationDTO> findByNameForNegotiationFile(NegotiationRelationFindByParamFormDTO dto) {
		return execute((mapper) -> {
			List<Building> list = mapper.findByNameForNegotiationFile(dto.getText(), HankakuUtils.toHankaku(dto.getText()), dto.getParams(),
					Application.SUGGEST_LIMIT_SIZE);

			return list.stream().map(b -> new NegotiationRelationDTO(b.getId(), ZenkakuUtils.toZenkaku(b.getName())))
					.collect(Collectors.toList());
		});
	}
}
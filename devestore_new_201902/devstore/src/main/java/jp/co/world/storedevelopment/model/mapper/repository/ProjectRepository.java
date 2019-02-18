package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;
import com.world.storedevelopment.utils.HankakuUtils;
import com.world.storedevelopment.utils.ZenkakuUtils;

import jp.co.world.storedevelopment.Application;
import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Building;
import jp.co.world.storedevelopment.model.IShop;
import jp.co.world.storedevelopment.model.Project;
import jp.co.world.storedevelopment.model.mapper.ProjectRepositoryMapper;
import jp.co.world.storedevelopment.pc.controller.form.ProjectFindForm;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationRelationDTO;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationRelationFindByParamFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationRelationFindByTextFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationRelationProjectDTO;
import jp.co.world.storedevelopment.sp.controller.dto.ProjectFindFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.RelatedFindFromProjectDTO;

public class ProjectRepository extends Repository<Project, ProjectRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(Project.class);
	}
	
	@Override
	protected ProjectRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(ProjectRepositoryMapper.class);
	}
	
	public List<Project> findAllNotDeleted() {
		return execute((mapper) -> {
			return mapper.findAllNotDeleted();
		});
	}

	public List<NegotiationRelationDTO> findByNameForNegotiationFile(NegotiationRelationFindByParamFormDTO dto) {
		return execute((mapper) -> {
			List<Project> list = mapper.findByNameForNegotiationFile(dto.getText(),
					HankakuUtils.toHankaku(dto.getText()), dto.getParams(), Application.SUGGEST_LIMIT_SIZE);
			return list.stream().map(p -> new NegotiationRelationDTO(p.getId(), ZenkakuUtils.toZenkaku(p.getTitle())))
					.collect(Collectors.toList());
		});
	}

	public List<Project> findByNegotiationId(Long id) {
		return execute((mapper) -> {
			return mapper.findByNegotiationId(id);
		});
	}
	
	public List<Project> findAlert() {
		return execute((mapper) -> {
			return mapper.findAlert();
		});
	}

	public List<Project> findStartDateAlert(String categoryIds) {
		return execute((mapper) -> {
			return mapper.findStartDateAlert(categoryIds);
		});
	}

	public NegotiationRelationProjectDTO findForNegotiationById(NegotiationRelationFindByTextFormDTO dto,
			Account account) {
		return execute((mapper) -> {
			Project project = mapper.findForNegotiationById(Long.valueOf(dto.getText()), account.getEmployeCode(),
					account.getId());
			return new NegotiationRelationProjectDTO(project.getId(), project.getTitle());
		});
	}

	public List<Project> getProjectListByBuildingId(Long buildingId) {
		return execute((mapper) -> {
			return mapper.getProjectListByBuildingId(buildingId);
		});
	}

	public List<Project> findForSP(ProjectFindFormDTO dto) {
		return execute((mapper) -> {
			return mapper.findForSP(dto);
		});
	}

	public List<Project> findForSuggest(RelatedFindFromProjectDTO dto) {
		return execute((mapper) -> {
			return mapper.findForSuggest(dto);
		});
	}

	public List<Project> findForPC(ProjectFindForm dto) {
		return execute((mapper) -> {
			return mapper.findForPC(dto);
		});
	}

	public int getCountFindForPC(ProjectFindForm dto) {
		return execute((mapper) -> {
			return mapper.getCountFindForPC(dto);
		});
	}

	public List<Project> findByBuilding(Building building) {
		return execute((mapper) -> {
			return mapper.findByBuilding(building);
		});
	}

	public List<Project> findShopCopyTarget() {
		return execute((mapper) -> {
			return mapper.findShopCopyTarget();
		});
	}
	
	public List<Project> findByShop(IShop model) {
		return execute((mapper) -> {
			return mapper.findByShop(model);
		});
	}

	public List<Project> findOpeningStoreProject() {
		return execute((mapper) -> {
			return mapper.findOpeningStoreProject();
		});
	}

	public int getCountFindForSP(ProjectFindFormDTO dto) {
		return execute((mapper) -> {
			return mapper.getCountFindForSP(dto);
		});
	}
}

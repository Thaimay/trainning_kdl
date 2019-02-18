package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;
import com.world.storedevelopment.utils.HankakuUtils;
import com.world.storedevelopment.utils.ZenkakuUtils;

import jp.co.world.storedevelopment.Application;
import jp.co.world.storedevelopment.model.ProjectProgress;
import jp.co.world.storedevelopment.model.mapper.ProjectProgressRepositoryMapper;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationRelationDTO;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationRelationFindByTextFormDTO;

public class ProjectProgressRepository extends Repository<ProjectProgress, ProjectProgressRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(ProjectProgress.class);
	}

	@Override
	protected ProjectProgressRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(ProjectProgressRepositoryMapper.class);
	}

	public List<NegotiationRelationDTO> findByText(NegotiationRelationFindByTextFormDTO dto) {
		return execute((mapper) -> {
			List<ProjectProgress> list = mapper.findByText(dto.getText(), HankakuUtils.toHankaku(dto.getText()),
					Application.SUGGEST_LIMIT_SIZE);
			return list.stream().map(c -> new NegotiationRelationDTO(c.getId(), ZenkakuUtils.toZenkaku(c.getName())))
					.collect(Collectors.toList());
		});
	}

	public List<ProjectProgress> getByCategory(String category, Long projectCategoryId) {
		return execute((mapper) -> {
			return mapper.getByCategory(category, projectCategoryId);
		});
	}

	public List<ProjectProgress> getCompanyCategory(Long projectCategoryId) {
		return this.getByCategory("COMPANY", projectCategoryId);
	}

	public List<ProjectProgress> getNegotiationCategory(Long projectCategoryId) {
		return this.getByCategory("NEGOTIATION", projectCategoryId);
	}
}

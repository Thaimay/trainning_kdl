package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.MProjectProgressStatus;
import jp.co.world.storedevelopment.model.mapper.MProjectProgressStatusRepositoryMapper;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationRelationDTO;

public class MProjectProgressStatusRepository extends Repository<MProjectProgressStatus, MProjectProgressStatusRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(MProjectProgressStatus.class);
	}

	@Override
	protected MProjectProgressStatusRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(MProjectProgressStatusRepositoryMapper.class);
	}

	public List<MProjectProgressStatus> getMProjectProgressStatusList(String category, Long projectCategoryId) {
		return execute((mapper) -> {
			List<MProjectProgressStatus> projectProgressList = mapper.getMProjectProgressStatusList(category, projectCategoryId);
			return projectProgressList;
		});
	}

	public Optional<MProjectProgressStatus> getMProjectProgressStatus(String category, Long projectCategoryId, Integer priority) {
		return execute((mapper) -> {
			MProjectProgressStatus projectProgress = mapper.getMProjectProgressStatus(category, projectCategoryId, priority);
			return Optional.ofNullable(projectProgress);
		});
	}
	
	public List<MProjectProgressStatus> getInitMProjectProgressStatus(Long projectCategoryId) {
		return execute((mapper) -> {
			if (projectCategoryId == null) {
				throw new IllegalArgumentException("引数の値が null は不正です");
			}
			List<MProjectProgressStatus> projectProgressList = mapper.getInitMProjectProgressStatus(projectCategoryId);
			/*if (projectProgressList.isEmpty()) {
				throw new IllegalStateException("該当するマスタが存在しません");
			}*/
			return projectProgressList;
		});
	}

	public Optional<MProjectProgressStatus> getCurrentProgress(String category, Long projectCategoryId, String code) {
		return execute((mapper) -> {
			MProjectProgressStatus currentProgress = mapper.getCurrentProgressByCode(category, projectCategoryId, code);
			return Optional.ofNullable(currentProgress);
		});
	}

	public Optional<MProjectProgressStatus> getNextProgress(String category, Long projectCategoryId, Integer priority) {
		return execute((mapper) -> {
			MProjectProgressStatus currentProgress = mapper.getNextProgress(category, projectCategoryId, priority);
			return Optional.ofNullable(currentProgress);
		});
	}

	public Optional<MProjectProgressStatus> getFirstProgress(String category, Long projectCategoryId) {
		return execute((mapper) -> {
			MProjectProgressStatus currentProgress = mapper.getFirstProgress(category, projectCategoryId);
			return Optional.ofNullable(currentProgress);
		});
	}

	public List<NegotiationRelationDTO> getOfficeProjectProgress() {
		return execute((mapper) -> {
			List<NegotiationRelationDTO> office = mapper.getOfficeProjectProgress();
			return office;
		});
	}

	public List<NegotiationRelationDTO> getNegotiationProjectProgress() {
		return execute((mapper) -> {
			List<NegotiationRelationDTO> office = mapper.getNegotiationProjectProgress();
			return office;
		});
	}

}

package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.MProjectActionStatus;
import jp.co.world.storedevelopment.model.mapper.MProjectActionStatusRepositoryMapper;

public class MProjectActionStatusRepository extends Repository<MProjectActionStatus, MProjectActionStatusRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(MProjectActionStatus.class);
	}

	@Override
	protected MProjectActionStatusRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(MProjectActionStatusRepositoryMapper.class);
	}

	public List<MProjectActionStatus> getMProjectActionStatusList(Long projectCategoryId, Long salesChannelId) {
		return execute((mapper) -> {
			List<MProjectActionStatus> mProjectActionStatusList = mapper.getMProjectActionStatusList(projectCategoryId, salesChannelId);
			return mProjectActionStatusList;
		});
	}
	
	public List<MProjectActionStatus> findSortDescBy(Long projectCategoryId, Long salesChannelId) {
		return execute((mapper) -> {
			return mapper.findSortDescBy(projectCategoryId, salesChannelId);
		});
	}
	
	public List<MProjectActionStatus> findBy(Long projectCategoryId, Long salesChannelId) {
		return execute((mapper) -> {
			return mapper.findBy(projectCategoryId, salesChannelId);
		});
	}

	public List<MProjectActionStatus> getScheduleUseList(Long projectCategoryId, Long salesChannelId) {
		return execute((mapper) -> {
			List<MProjectActionStatus> mProjectActionStatusList = mapper.getScheduleUseList(projectCategoryId, salesChannelId);
			return mProjectActionStatusList;
		});
	}

	public Optional<MProjectActionStatus> getInitStatus(Long projectCategoryId, Long salesChannelId) {
		return execute((mapper) -> {
			Optional<MProjectActionStatus> mProjectActionStatusOption = Optional.ofNullable(mapper.getInitStatus(projectCategoryId, salesChannelId));
//			mProjectActionStatusOption.orElseThrow(() -> {
//				throw new IllegalStateException("マスタデータが不正な為初期値の取得に失敗しました");
//			});
			return mProjectActionStatusOption;
		});
	}

}

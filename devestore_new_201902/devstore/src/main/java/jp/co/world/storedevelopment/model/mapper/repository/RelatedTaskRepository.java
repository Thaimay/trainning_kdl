package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.RelatedTask;
import jp.co.world.storedevelopment.model.mapper.RelatedTaskRepositoryMapper;

public class RelatedTaskRepository extends Repository<RelatedTask, RelatedTaskRepositoryMapper> {

	@Override
	protected RelatedTaskRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(RelatedTaskRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(RelatedTask.class);
	}

	public List<RelatedTask> findByNegotiationCommentId(Long id) {
		return execute((mapper) -> {
			return mapper.findByNegotiationCommentId(id);
		});
	}

	public List<RelatedTask> findRelatedTaskList() {
		return execute((mapper) -> {
			return mapper.findRelatedTaskList();
		});
	}
}

package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.IRemodelingHistory;
import jp.co.world.storedevelopment.model.mapper.IRemodelingHistoryRepositoryMapper;

public class IRemodelingHistoryRepository extends Repository<IRemodelingHistory, IRemodelingHistoryRepositoryMapper> {

	@Override
	protected IRemodelingHistoryRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(IRemodelingHistoryRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(IRemodelingHistory.class);
	}

	public Optional<IRemodelingHistory> findByImportCode(IRemodelingHistory model) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByImportCode(model.getRemodelingLogId().toString()));
		});
	}
}

package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.IStatus;
import jp.co.world.storedevelopment.model.mapper.IStatusRepositoryMapper;

public class IStatusRepository extends Repository<IStatus, IStatusRepositoryMapper> {

	@Override
	protected IStatusRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(IStatusRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(IStatus.class);
	}

	public Optional<IStatus> findByImportCode(IStatus model) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByImportCode(model.getStatusCd()));
		});
	}
}

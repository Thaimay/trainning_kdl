package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.IYakata;
import jp.co.world.storedevelopment.model.mapper.IYakataRepositoryMapper;

public class IYakataRepository extends Repository<IYakata, IYakataRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(IYakata.class);
	}

	@Override
	protected IYakataRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(IYakataRepositoryMapper.class);
	}

	public Optional<IYakata> findByImportCode(IYakata model) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByImportCode(model.getBuildingCd()));
		});
	}
}

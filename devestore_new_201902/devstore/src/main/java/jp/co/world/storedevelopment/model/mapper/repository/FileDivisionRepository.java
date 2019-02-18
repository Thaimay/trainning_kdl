package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.FileDivision;
import jp.co.world.storedevelopment.model.mapper.FileDivisionRepositoryMapper;

public class FileDivisionRepository extends Repository<FileDivision, FileDivisionRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(FileDivision.class);
	}

	@Override
	protected FileDivisionRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(FileDivisionRepositoryMapper.class);
	}

	public Optional<FileDivision> getFileDivisionByCode(String code) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.getFileDivisionByCode(code));
		});
	}
}

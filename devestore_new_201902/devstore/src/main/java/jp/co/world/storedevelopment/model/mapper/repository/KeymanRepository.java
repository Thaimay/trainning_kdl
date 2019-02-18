package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.Keyman;
import jp.co.world.storedevelopment.model.mapper.KeymanRepositoryMapper;

public class KeymanRepository extends Repository<Keyman, KeymanRepositoryMapper> {

	@Override
	protected KeymanRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(KeymanRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(Keyman.class);
	}

	public Optional<Keyman> findByBusinessCardId(Long id) {
		return execute((mapper) -> {
			Keyman keyman = mapper.findByBusinessCardId(id);
			return Optional.ofNullable(keyman);
		});
	}

}

package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.ISalesByTimeZone;
import jp.co.world.storedevelopment.model.mapper.ISalesByTimeZoneRepositoryMapper;

public class ISalesByTimeZoneRepository extends Repository<ISalesByTimeZone, ISalesByTimeZoneRepositoryMapper> {

	@Override
	protected ISalesByTimeZoneRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(ISalesByTimeZoneRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(ISalesByTimeZone.class);
	}

	public Optional<ISalesByTimeZone> findByImportCode(ISalesByTimeZone model) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByImportCode(model.getDispOrder()));
		});
	}
}

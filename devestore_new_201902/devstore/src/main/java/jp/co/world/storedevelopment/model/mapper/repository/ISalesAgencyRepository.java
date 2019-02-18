package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.ISalesAgency;
import jp.co.world.storedevelopment.model.mapper.ISalesAgencyRepositoryMapper;

public class ISalesAgencyRepository extends Repository<ISalesAgency, ISalesAgencyRepositoryMapper> {

	@Override
	protected ISalesAgencyRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(ISalesAgencyRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(ISalesAgency.class);
	}

	public Optional<ISalesAgency> findByImportCode(ISalesAgency model) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByImportCode(model.getSalesAgencyCd()));
		});
	}

	public Optional<ISalesAgency> findByShopId(Long id) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByShopId(id));
		});
	}
}

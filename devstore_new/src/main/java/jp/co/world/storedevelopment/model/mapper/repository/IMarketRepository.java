package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.IMarket;
import jp.co.world.storedevelopment.model.mapper.IMarketRepositoryMapper;

public class IMarketRepository extends Repository<IMarket, IMarketRepositoryMapper> {

	@Override
	protected IMarketRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(IMarketRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(IMarket.class);
	}

	public Optional<IMarket> findByImportCode(IMarket model) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByImportCode(model.getMarketCd()));
		});
	}
}

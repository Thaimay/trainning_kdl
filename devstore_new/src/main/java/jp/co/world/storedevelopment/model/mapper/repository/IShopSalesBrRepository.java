package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.IShopSalesBr;
import jp.co.world.storedevelopment.model.mapper.IShopSalesBrRepositoryMapper;

public class IShopSalesBrRepository extends Repository<IShopSalesBr, IShopSalesBrRepositoryMapper> {

	@Override
	protected IShopSalesBrRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(IShopSalesBrRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(IShopSalesBr.class);
	}

	public Optional<IShopSalesBr> findByImportCode(IShopSalesBr model) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByImportCode(model.getDispOrder()));
		});
	}
}

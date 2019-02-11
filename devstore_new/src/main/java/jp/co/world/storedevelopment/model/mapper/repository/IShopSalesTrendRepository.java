package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;

import jp.co.world.storedevelopment.model.IShopSalesTrend;
import jp.co.world.storedevelopment.model.mapper.IShopSalesTrendRepositoryMapper;

public class IShopSalesTrendRepository
		extends BuildingRelatedRepository<IShopSalesTrend, IShopSalesTrendRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(IShopSalesTrend.class);
	}

	@Override
	protected IShopSalesTrendRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(IShopSalesTrendRepositoryMapper.class);
	}

	public Optional<IShopSalesTrend> findByImportCode(IShopSalesTrend model) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByImportCode(model.getDispOrder()));
		});
	}
}

package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;

import jp.co.world.storedevelopment.model.Building;
import jp.co.world.storedevelopment.model.IShopSalesLatestOneYear;
import jp.co.world.storedevelopment.model.mapper.IShopSalesLatestOneYearRepositoryMapper;

public class IShopSalesLatestOneYearRepository
		extends BuildingRelatedRepository<IShopSalesLatestOneYear, IShopSalesLatestOneYearRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(IShopSalesLatestOneYear.class);
	}

	@Override
	protected IShopSalesLatestOneYearRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(IShopSalesLatestOneYearRepositoryMapper.class);
	}

	public IShopSalesLatestOneYear findByShopCd(String shopCd) {
		return execute((mapper) -> {
			return mapper.findByShopCd(shopCd);
		});
	}

	public Optional<IShopSalesLatestOneYear> findByImportCode(IShopSalesLatestOneYear model) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByImportCode(model.getDispOrder()));
		});
	}
	
	public Optional<Building> findBy(String code) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findBy(code));
		});
	}

}

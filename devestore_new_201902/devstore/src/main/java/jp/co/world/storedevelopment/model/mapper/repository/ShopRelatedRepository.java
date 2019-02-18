package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;

import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.mapper.ShopRelatedRepositoryMapper;

public abstract class ShopRelatedRepository<T, M extends ShopRelatedRepositoryMapper<T>> extends Repository<T, M> {

	public List<T> findByShopId(Long shopId) {
		return execute(mapper -> {
			return mapper.findByShopId(tableName(), shopId);
		});
	}

	public int deleteByShopId(Long shopId) {
		return execute(mapper -> {
			return mapper.deleteByShopId(tableName(), shopId);
		});
	}

	public List<T> findByShopCd(String shopCd) {
		return execute(mapper -> {
			return mapper.findByShopCd(tableName(), shopCd);
		});
	}
}

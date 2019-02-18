package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;

import jp.co.world.storedevelopment.model.File;
import jp.co.world.storedevelopment.model.ShopImage;
import jp.co.world.storedevelopment.model.mapper.ShopImageRepositoryMapper;

public class ShopImageRepository extends ShopRelatedRepository<ShopImage, ShopImageRepositoryMapper> {
	@Override
	protected ShopImageRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(ShopImageRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(File.class);
	}

	@Override
	public List<ShopImage> findByShopId(Long shopId) {
		return execute(mapper -> {
			return mapper.findByShopId(tableName(), shopId).stream().filter(x -> x.isImage())
					.collect(Collectors.toList());
		});
	}

	public List<ShopImage> findShopImageRelatedProject(Long shopId) {
		return execute(mapper -> {
			return mapper.findShopImageRelatedProject(shopId).stream().filter(x -> x.isImage()).collect(Collectors.toList());
		});
	}
}

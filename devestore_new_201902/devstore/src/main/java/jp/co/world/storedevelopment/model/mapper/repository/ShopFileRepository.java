package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;

import jp.co.world.storedevelopment.model.File;
import jp.co.world.storedevelopment.model.ShopFile;
import jp.co.world.storedevelopment.model.mapper.ShopFileRepositoryMapper;

public class ShopFileRepository extends ShopRelatedRepository<ShopFile, ShopFileRepositoryMapper> {
	@Override
	protected ShopFileRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(ShopFileRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(File.class);
	}

	@Override
	public List<ShopFile> findByShopId(Long shopId) {
		return execute(mapper -> {
			return mapper.findByShopId(tableName(), shopId).stream().filter(x -> x.isDocument())
					.collect(Collectors.toList());
		});
	}

	public List<ShopFile> findShopFileRelatedProject(Long shopId) {
		return execute(mapper -> {
			return mapper.findShopFileRelatedProject(shopId).stream().filter(x -> x.isDocument())
					.collect(Collectors.toList());
		});
	}
}

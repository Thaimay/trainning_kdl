package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.IShopAdmin;
import jp.co.world.storedevelopment.model.mapper.IShopAdminRepositoryMapper;

public class IShopAdminRepository extends Repository<IShopAdmin, IShopAdminRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(IShopAdmin.class);
	}

	@Override
	protected IShopAdminRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(IShopAdminRepositoryMapper.class);
	}

	public Optional<IShopAdmin> findByImportCode(IShopAdmin model) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByImportCode(model.getShopAdminId()));
		});
	}

	public Optional<IShopAdmin> findByShopId(Long shopId) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByShopId(shopId));
		});
	}
}

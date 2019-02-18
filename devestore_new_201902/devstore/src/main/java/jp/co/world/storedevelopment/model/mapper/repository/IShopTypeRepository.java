package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.IShopType;
import jp.co.world.storedevelopment.model.mapper.IShopTypeRepositoryMapper;

public class IShopTypeRepository extends Repository<IShopType, IShopTypeRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(IShopType.class);
	}

	@Override
	protected IShopTypeRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(IShopTypeRepositoryMapper.class);
	}

	public Optional<IShopType> findByImportCode(IShopType model) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByImportCode(model.getShopTypeId()));
		});
	}

}

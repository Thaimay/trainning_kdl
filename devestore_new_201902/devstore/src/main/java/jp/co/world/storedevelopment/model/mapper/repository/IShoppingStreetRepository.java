package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.IShoppingStreet;
import jp.co.world.storedevelopment.model.mapper.IShoppingStreetRepositoryMapper;

public class IShoppingStreetRepository extends Repository<IShoppingStreet, IShoppingStreetRepositoryMapper>{

	@Override
	protected IShoppingStreetRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(IShoppingStreetRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(IShoppingStreet.class);
	}

	public Optional<IShoppingStreet> findByImportCode(IShoppingStreet model) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByImportCode(model.getShoppingStreetId()));
		});
	}
	
}

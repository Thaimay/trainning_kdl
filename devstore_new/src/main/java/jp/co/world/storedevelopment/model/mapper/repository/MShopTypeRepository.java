package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.MShopType;
import jp.co.world.storedevelopment.model.mapper.MShopTypeRepositoryMapper;

public class MShopTypeRepository extends Repository<MShopType, MShopTypeRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(MShopType.class);
	}

	@Override
	protected MShopTypeRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(MShopTypeRepositoryMapper.class);
	}

	public List<MShopType> getAllShopType() {
		return execute((mapper) -> {
			return mapper.getAllShopType();
		});
	}
}

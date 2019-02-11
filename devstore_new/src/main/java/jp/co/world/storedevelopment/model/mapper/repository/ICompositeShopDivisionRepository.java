package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.ICompositeShopDivision;
import jp.co.world.storedevelopment.model.mapper.ICompositeShopDivisionRepositoryMapper;

public class ICompositeShopDivisionRepository
		extends Repository<ICompositeShopDivision, ICompositeShopDivisionRepositoryMapper> {

	@Override
	protected ICompositeShopDivisionRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(ICompositeShopDivisionRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(ICompositeShopDivision.class);
	}

	public Optional<ICompositeShopDivision> findByImportCode(ICompositeShopDivision model) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByImportCode(model.getCompositeShopDivisionCd()));
		});
	}
}

package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.IIncomeUnitLayer;
import jp.co.world.storedevelopment.model.mapper.IIncomeUnitLayerRepositoryMapper;

public class IIncomeUnitLayerRepository extends Repository<IIncomeUnitLayer, IIncomeUnitLayerRepositoryMapper> {

	@Override
	protected IIncomeUnitLayerRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(IIncomeUnitLayerRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(IIncomeUnitLayer.class);
	}

	public Optional<IIncomeUnitLayer> findByImportCode(IIncomeUnitLayer model) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByImportCode(model.getIncomeUnitId()));
		});
	}
}

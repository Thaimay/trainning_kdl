package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.IFixedAssetsDepreciationDivision;
import jp.co.world.storedevelopment.model.mapper.IFixedAssetsDepreciationDivisionRepositoryMapper;

public class IFixedAssetsDepreciationDivisionRepository
		extends Repository<IFixedAssetsDepreciationDivision, IFixedAssetsDepreciationDivisionRepositoryMapper> {

	@Override
	protected IFixedAssetsDepreciationDivisionRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(IFixedAssetsDepreciationDivisionRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(IFixedAssetsDepreciationDivision.class);
	}

	public Optional<IFixedAssetsDepreciationDivision> findByImportCode(IFixedAssetsDepreciationDivision model) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByImportCode(model.getStepsRateCalculationDivisionCd()));
		});
	}
}

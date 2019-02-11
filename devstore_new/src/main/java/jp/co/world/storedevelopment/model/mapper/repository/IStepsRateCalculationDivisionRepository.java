package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.IStepsRateCalculationDivision;
import jp.co.world.storedevelopment.model.mapper.IStepsRateCalculationDivisionRepositoryMapper;

public class IStepsRateCalculationDivisionRepository
		extends Repository<IStepsRateCalculationDivision, IStepsRateCalculationDivisionRepositoryMapper> {

	@Override
	protected IStepsRateCalculationDivisionRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(IStepsRateCalculationDivisionRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(IStepsRateCalculationDivision.class);
	}

	public Optional<IStepsRateCalculationDivision> findByImportCode(IStepsRateCalculationDivision model) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByImportCode(model.getStepsRateCalculationDivisionCd()));
		});
	}
}

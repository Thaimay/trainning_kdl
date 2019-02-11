package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;

import jp.co.world.storedevelopment.model.ICompetitionSales;
import jp.co.world.storedevelopment.model.mapper.ICompetitionSalesRepositoryMapper;

public class ICompetitionSalesRepository
		extends BuildingRelatedRepository<ICompetitionSales, ICompetitionSalesRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(ICompetitionSales.class);
	}

	@Override
	protected ICompetitionSalesRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(ICompetitionSalesRepositoryMapper.class);
	}

	public List<ICompetitionSales> findByBuildingCdFromTo(String buildingCd, int yearMonthFrom, int yearMonthTo) {
		return execute((mapper) -> {
			return mapper.findByBuildingCdFromTo(buildingCd, yearMonthFrom, yearMonthTo);
		});
	}

	public Optional<ICompetitionSales> findByImportCode(ICompetitionSales model) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByImportCode(model.getDispOrder()));
		});
	}

}

package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.IIncomeUnit;
import jp.co.world.storedevelopment.model.mapper.IIncomeUnitRepositoryMapper;
import jp.co.world.storedevelopment.sp.controller.dto.IIncomeUnitListDTO;

public class IIncomeUnitRepository extends Repository<IIncomeUnit, IIncomeUnitRepositoryMapper> {

	@Override
	protected IIncomeUnitRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(IIncomeUnitRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(IIncomeUnit.class);
	}

	public List<IIncomeUnitListDTO> getIIncomeUnitList() {
		return execute((mapper) -> {
			List<IIncomeUnit> iIncomeUnits = this.findAll();
			return iIncomeUnits.stream().map(n -> new IIncomeUnitListDTO(n)).collect(Collectors.toList());
		});
	}

	public IIncomeUnit findByIncomeUnitId(Long incomeUnitId) {
		return execute((mapper) -> {
			return mapper.findByIncomeUnitId(incomeUnitId);
		});
	}

	public Optional<IIncomeUnit> findByImportCode(IIncomeUnit model) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByImportCode(model.getIncomeUnitId()));
		});
	}
	
}

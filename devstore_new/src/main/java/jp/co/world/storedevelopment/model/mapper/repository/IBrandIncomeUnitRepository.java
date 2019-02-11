package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;
import com.world.storedevelopment.utils.HankakuUtils;

import jp.co.world.storedevelopment.Application;
import jp.co.world.storedevelopment.model.IBrandIncomeUnit;
import jp.co.world.storedevelopment.model.mapper.IBrandIncomeUnitRepositoryMapper;
import jp.co.world.storedevelopment.sp.controller.dto.IBrandIncomeUnitBusinessListDTO;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationRelationDTO;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationRelationFindByTextFormDTO;

public class IBrandIncomeUnitRepository extends Repository<IBrandIncomeUnit, IBrandIncomeUnitRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(IBrandIncomeUnit.class);
	}

	@Override
	protected IBrandIncomeUnitRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(IBrandIncomeUnitRepositoryMapper.class);
	}

	public Optional<IBrandIncomeUnit> findByImportCode(IBrandIncomeUnit model) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByImportCode(model.getBrandIncomeUnitCd()));
		});
	}

	public List<NegotiationRelationDTO> findByText(NegotiationRelationFindByTextFormDTO dto) {
		return execute((mapper) -> {
			List<IBrandIncomeUnit> list = mapper.findByText(dto.getText(), HankakuUtils.toHankaku(dto.getText()),
					Application.SUGGEST_LIMIT_SIZE);
			return list.stream().map(b -> new NegotiationRelationDTO(b.getId(), b.getBrandIncomeUnitName()))
					.collect(Collectors.toList());
		});
	}

	public List<NegotiationRelationDTO> findByShopId(Long id) {
		return execute((mapper) -> {
			List<IBrandIncomeUnit> list = mapper.findByShopId(id);
			return list.stream().map(b -> new NegotiationRelationDTO(b.getId(), b.getBrandIncomeUnitName()))
					.collect(Collectors.toList());
		});
	}
	
	public List<NegotiationRelationDTO> findFromShopAdmin(Long id) {
		return execute((mapper) -> {
			List<IBrandIncomeUnit> list = mapper.findFromShopAdmin(id);
			return list.stream().map(b -> new NegotiationRelationDTO(b.getId(), b.getBrandIncomeUnitName()))
					.collect(Collectors.toList());
		});
	}

	public List<IBrandIncomeUnitBusinessListDTO> getBrandBusinessList() {
		return execute((mapper) -> {
			return mapper.getBrandBusinessList();
		});
	}
}

package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.ISalesAgencyContract;
import jp.co.world.storedevelopment.model.mapper.ISalesAgencyContractRepositoryMapper;

public class ISalesAgencyContractRepository
		extends Repository<ISalesAgencyContract, ISalesAgencyContractRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(ISalesAgencyContract.class);
	}

	@Override
	protected ISalesAgencyContractRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(ISalesAgencyContractRepositoryMapper.class);
	}

	public Optional<ISalesAgencyContract> findByImportCode(ISalesAgencyContract model) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByImportCode(model.getSalesAgencyContractId().toString()));
		});
	}

	public ISalesAgencyContract findByShopId(Long id) {
		return execute((mapper) -> {
			ISalesAgencyContract list = mapper.findByShopId(id);
			return list;
		});
	}

	public ISalesAgencyContract findSalesAgencyContractRefShop(Long shopId) {
		return execute((mapper) -> {
			List<ISalesAgencyContract> list = mapper.findSalesAgencyContractRefShop(shopId);
			if (list.size() > 0) {
				return list.get(0);
			}
			return null;
		});
	}
	
	public Optional<Long> findParentSalesAgencyTargetId(String shopTownParentId) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findParentSalesAgencyTargetId(shopTownParentId));
		});
	}
}

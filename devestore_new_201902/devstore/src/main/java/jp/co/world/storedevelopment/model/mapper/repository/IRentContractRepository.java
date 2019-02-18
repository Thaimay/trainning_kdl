package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;

import jp.co.world.storedevelopment.model.IRentContract;
import jp.co.world.storedevelopment.model.mapper.IRentContractRepositoryMapper;

/**
 * @author TaiNM
 *
 */
public class IRentContractRepository extends ShopRelatedRepository<IRentContract, IRentContractRepositoryMapper> {
	@Override
	protected IRentContractRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(IRentContractRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(IRentContract.class);
	}

	public Optional<IRentContract> findByImportCode(IRentContract model) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByImportCode(model.getRentContractId().toString()));
		});
	}
	
	public IRentContract findRentContractRefShop(Long shopId) {
		return execute((mapper) -> {
			List<IRentContract> list = mapper.findRentContractRefShop(shopId);
			if(list.size() > 0) {
				return list.get(0);
			}
			return null;
		});
	}
	
	public Optional<Long> findParentContractTypeId(String shopTownParentId) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findParentContractTypeId(shopTownParentId));
		});
	}
}

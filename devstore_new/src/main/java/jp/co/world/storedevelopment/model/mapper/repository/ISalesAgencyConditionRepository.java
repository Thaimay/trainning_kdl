package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.ISalesAgencyCondition;
import jp.co.world.storedevelopment.model.mapper.ISalesAgencyConditionRepositoryMapper;

public class ISalesAgencyConditionRepository
		extends Repository<ISalesAgencyCondition, ISalesAgencyConditionRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(ISalesAgencyCondition.class);
	}

	@Override
	protected ISalesAgencyConditionRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(ISalesAgencyConditionRepositoryMapper.class);
	}

	public Optional<ISalesAgencyCondition> findByImportCode(ISalesAgencyCondition model) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByImportCode(model.getSalesAgencyConditionId().toString()));
		});
	}

	public ISalesAgencyCondition findByShopId(Long id) {
		return execute((mapper) -> {
			ISalesAgencyCondition list = mapper.findByShopId(id);
			return list;
		});
	}

	public ISalesAgencyCondition findSalesAgencyConditionRefShop(Long shopId) {
		return execute((mapper) -> {
			List<ISalesAgencyCondition> list = mapper.findSalesAgencyConditionRefShop(shopId);
			if (list.size() > 0) {
				return list.get(0);
			}
			return null;
		});
	}
}

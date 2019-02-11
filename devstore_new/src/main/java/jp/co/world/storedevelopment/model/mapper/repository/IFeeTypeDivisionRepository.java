package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.IFeeTypeDivision;
import jp.co.world.storedevelopment.model.mapper.IFeeTypeDivisionRepositoryMapper;

public class IFeeTypeDivisionRepository extends Repository<IFeeTypeDivision, IFeeTypeDivisionRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(IFeeTypeDivision.class);
	}

	@Override
	protected IFeeTypeDivisionRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(IFeeTypeDivisionRepositoryMapper.class);
	}

	public Optional<IFeeTypeDivision> findByImportCode(IFeeTypeDivision model) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByImportCode(model.getFeeTypeDivisionId().toString()));
		});
	}
}

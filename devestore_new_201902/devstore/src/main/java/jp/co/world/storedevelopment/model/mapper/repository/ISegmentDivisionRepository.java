package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.ISegmentDivision;
import jp.co.world.storedevelopment.model.mapper.ISegmentDivisionRepositoryMapper;

public class ISegmentDivisionRepository extends Repository<ISegmentDivision, ISegmentDivisionRepositoryMapper> {

	@Override
	protected ISegmentDivisionRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(ISegmentDivisionRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(ISegmentDivision.class);
	}

	public Optional<ISegmentDivision> findByImportCode(ISegmentDivision model) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByImportCode(model.getSegmentDivisionCd()));
		});
	}

}

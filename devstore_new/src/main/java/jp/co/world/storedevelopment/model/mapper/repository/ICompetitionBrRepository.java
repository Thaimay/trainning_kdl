package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.ICompetitionBr;
import jp.co.world.storedevelopment.model.mapper.ICompetitionBrRepositoryMapper;

public class ICompetitionBrRepository extends Repository<ICompetitionBr, ICompetitionBrRepositoryMapper> {

	@Override
	protected ICompetitionBrRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(ICompetitionBrRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(ICompetitionBr.class);
	}

	public Optional<ICompetitionBr> findByImportCode(ICompetitionBr model) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByImportCode(model.getCompetitionBrCd()));
		});
	}
}

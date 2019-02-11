package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.ICompetition;
import jp.co.world.storedevelopment.model.mapper.ICompetitionRepositoryMapper;

public class ICompetitionRepository extends Repository<ICompetition, ICompetitionRepositoryMapper> {

	protected ICompetitionRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(ICompetitionRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(ICompetition.class);
	}

	public Optional<ICompetition> findByImportCode(ICompetition model) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByImportCode(model.getCompetitionShopCd()));
		});
	}

}

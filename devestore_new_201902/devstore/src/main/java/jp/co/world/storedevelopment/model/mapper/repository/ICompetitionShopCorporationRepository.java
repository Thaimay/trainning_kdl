package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;

import jp.co.world.storedevelopment.model.ICompetitionShopCorporation;
import jp.co.world.storedevelopment.model.mapper.ICompetitionShopCorporationRepositoryMapper;

public class ICompetitionShopCorporationRepository
		extends BuildingRelatedRepository<ICompetitionShopCorporation, ICompetitionShopCorporationRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(ICompetitionShopCorporation.class);
	}

	@Override
	protected ICompetitionShopCorporationRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(ICompetitionShopCorporationRepositoryMapper.class);
	}

	public Optional<ICompetitionShopCorporation> findByImportCode(ICompetitionShopCorporation model) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByImportCode(model.getCompetitionShopCorporationCd()));
		});
	}

}

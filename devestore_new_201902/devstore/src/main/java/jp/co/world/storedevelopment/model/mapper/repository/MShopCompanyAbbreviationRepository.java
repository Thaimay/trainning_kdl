package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.MShopCompanyAbbreviation;
import jp.co.world.storedevelopment.model.mapper.MShopCompanyAbbreviationRepositoryMapper;

public class MShopCompanyAbbreviationRepository
		extends Repository<MShopCompanyAbbreviation, MShopCompanyAbbreviationRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(MShopCompanyAbbreviation.class);
	}

	@Override
	protected MShopCompanyAbbreviationRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(MShopCompanyAbbreviationRepositoryMapper.class);
	}

	public Optional<MShopCompanyAbbreviation> findByName(String brandIncomeUnitName) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByName(brandIncomeUnitName));
		});
	}
}

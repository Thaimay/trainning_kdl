package jp.co.world.storedevelopment.model.mapper.repository;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;
import com.world.storedevelopment.utils.ZenkakuUtils;

import jp.co.world.storedevelopment.model.MShopCompanyAbbreviation;
import jp.co.world.storedevelopment.model.mapper.MShopCompanyAbbreviationRepositoryMapper;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationRelationDTO;

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

	public NegotiationRelationDTO findByName(String brandIncomeUnitName) {
		return execute((mapper) -> {
			MShopCompanyAbbreviation mShopCompanyAbbreviation = mapper.findByName(brandIncomeUnitName);
			return new NegotiationRelationDTO(mShopCompanyAbbreviation.getId(),
					ZenkakuUtils.toZenkaku(mShopCompanyAbbreviation.getAbbreviatedCompanyName()));
		});
	}
}

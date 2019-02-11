package jp.co.world.storedevelopment.model.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import jp.co.world.storedevelopment.model.ICompetitionShopCorporation;

public interface ICompetitionShopCorporationRepositoryMapper
		extends BuildingRelatedRepositoryMapper<ICompetitionShopCorporation> {

	@Select("select * from i_competition_shop_corporation where is_deleted is false and competition_shop_corporation_cd = '${code}'")
	ICompetitionShopCorporation findByImportCode(@Param("code") String competitionShopCorporationCd);
}

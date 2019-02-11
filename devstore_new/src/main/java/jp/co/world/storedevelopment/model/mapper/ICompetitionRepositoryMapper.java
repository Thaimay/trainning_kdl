package jp.co.world.storedevelopment.model.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.ICompetition;

public interface ICompetitionRepositoryMapper extends RepositoryMapper<ICompetition> {
	@Select("select * from i_competition where is_deleted is false and competition_shop_cd = '${code}'")
	ICompetition findByImportCode(@Param("code") String competitionShopCd);
}

package jp.co.world.storedevelopment.model.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.ICompetitionBr;

public interface ICompetitionBrRepositoryMapper extends RepositoryMapper<ICompetitionBr> {
	@Select("select * from i_competition_br where is_deleted is false and competition_br_cd = '${code}'")
	ICompetitionBr findByImportCode(@Param("code") String competitionBrCd);
}

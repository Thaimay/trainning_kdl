package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.MCreditRank;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingRelationFindByTextFormDTO;

public interface MCreditRankRepositoryMapper extends RepositoryMapper<MCreditRank> {

	@Select("select * from m_credit_rank where credit_rank like '${mCreditRank.text}%' or credit_rank like '${mCreditRank.textHankaku}%' limit ${limit}")
	List<MCreditRank> findByText(@Param("mCreditRank") BuildingRelationFindByTextFormDTO dto, @Param("limit") int limit);
	
	@Select("select * from m_credit_rank where is_deleted is false and corporation_cd = '${corporationCd}' limit 1")
	MCreditRank findByCorporationCd(@Param("corporationCd") String corporationCd);

}

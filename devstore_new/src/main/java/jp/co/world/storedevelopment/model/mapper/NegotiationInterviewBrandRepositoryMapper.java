package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.Negotiation;
import jp.co.world.storedevelopment.model.NegotiationInterviewBrand;

public interface NegotiationInterviewBrandRepositoryMapper extends RepositoryMapper<NegotiationInterviewBrand> {

	@Select("select * from NEGOTIATION_INTERVIEW_BRAND where negotiation_id = #{negotiation.id}")
	List<NegotiationInterviewBrand> findOfNegotiation(@Param("negotiation") Negotiation negotiation);

	@Delete("delete from NEGOTIATION_INTERVIEW_BRAND where negotiation_id = #{negotiation.id}")
	int deleteByNegotiation(@Param("negotiation") Negotiation negotiation);

	@Select("select * from negotiation_interview_brand where is_deleted = false and brand_id = #{brandId} and negotiation_id NOT IN (SELECT id from negotiation n where n.is_deleted = true)")
	List<NegotiationInterviewBrand> findByBrandId(@Param("brandId") Long brandId);
}

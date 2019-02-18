package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.Negotiation;

public interface NegotiationRelatedRepositoryMapper<T> extends RepositoryMapper<T> {

	@Select("select * from ${table} where negotiation_id = #{negotiation.id} order by update_datetime desc")
	List<T> findOfNegotiation(@Param("table") String table, @Param("negotiation") Negotiation negotiation);

	@Delete("delete from ${table} where negotiation_id = #{negotiation.id}")
	int deleteByNegotiation(@Param("table") String table, @Param("negotiation") Negotiation negotiation);

	@Select("select * from ${table}_history where negotiation_id = #{negotiation.id} order by update_datetime desc")
	List<T> findOfNegotiationHistory(@Param("table") String table, @Param("negotiation") Negotiation negotiation);
}

package jp.co.world.storedevelopment.model.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.ImportantInformationNegotiation;

public interface ImportantInformationNegotiationRepositoryMapper
		extends RepositoryMapper<ImportantInformationNegotiation> {

	@Delete("delete from important_information_negotiation where negotiation_id = #{id}")
	int deleteByNegotiationId(@Param("id") Long id);
}

package jp.co.world.storedevelopment.model.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.Keyman;

public interface KeymanRepositoryMapper extends RepositoryMapper<Keyman> {
	@Select("select * from KEYMAN where business_card_id = ${id}")
	public Keyman findByBusinessCardId(@Param("id") Long id);
}

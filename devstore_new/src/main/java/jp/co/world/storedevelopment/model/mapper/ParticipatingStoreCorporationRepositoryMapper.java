package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.ParticipatingStoreCorporation;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingRelationFindByTextFormDTO;

public interface ParticipatingStoreCorporationRepositoryMapper extends RepositoryMapper<ParticipatingStoreCorporation> {

	@Select("select * from participating_store_corporation where corporation_name like '%${participatingStoreCorporation.text}%' or corporation_name like '%${participatingStoreCorporation.textHankaku}%' limit ${limit}")
	List<ParticipatingStoreCorporation> findByText(
			@Param("participatingStoreCorporation") BuildingRelationFindByTextFormDTO dto, @Param("limit") int limit);

}

package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.ICorporationGroup;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingRelationFindByTextFormDTO;

public interface ICorporationGroupRepositoryMapper extends RepositoryMapper<ICorporationGroup> {
	
	@Select("select * from i_corporation_group where corporation_gp_name like '%${iCorporationGroup.text}%' or corporation_gp_name like '%${iCorporationGroup.textZenkaku}%' or corporation_gp_name like '%${iCorporationGroup.textHankaku}%' limit ${limit}")
	List<ICorporationGroup> findByText(@Param("iCorporationGroup") BuildingRelationFindByTextFormDTO dto, @Param("limit") int limit);

	@Select("select * from i_corporation_group where is_deleted is false and corporation_gp_cd = '${code}'")
	ICorporationGroup findByImportCode(@Param("code") String corporationGroupCd);

}

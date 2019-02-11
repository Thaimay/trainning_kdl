package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.ICorporation;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingRelationFindByTextFormDTO;

public interface ICorporationRepositoryMapper extends RepositoryMapper<ICorporation> {

	@Select("select * from i_corporation where is_deleted is false and corporation_cd = trim('${iCorporationCd}')")
	ICorporation getICorporationByICorporationCd(@Param("iCorporationCd") String iCorporationCd);

	@Select("select * from i_corporation where corporation_name like '%${text}%' or corporation_name like '%${hankaku}%' limit ${limit}")
	List<ICorporation> findByText(@Param("text") String text, @Param("hankaku") String hankaku,
			@Param("limit") int limit);

	@Select("select * from i_corporation where corporation_name like '%${iCorporation.text}%' or corporation_name like '%${iCorporation.textHankaku}%' limit ${limit}")
	List<ICorporation> findByString(@Param("iCorporation") BuildingRelationFindByTextFormDTO dto,
			@Param("limit") int limit);

	@Select("select * from i_corporation where is_deleted is false and corporation_gp_id = #{iCorporationGpId}")
	List<ICorporation> findByCorporationGroupId(@Param("iCorporationGpId") Long iCorporationGpId);

	@Select("select * from i_corporation where is_deleted is false and corporation_cd = '${code}'")
	ICorporation findByImportCode(@Param("code") String corporationCd);
}

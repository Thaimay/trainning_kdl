package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import jp.co.world.storedevelopment.model.IPrefectures;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingRelationFindByTextFormDTO;

public interface IPrefecturesRepositoryMapper extends BuildingRelatedRepositoryMapper<IPrefectures> {

	@Select("select * from i_prefectures where prefectures_name like '%${iPrefectures.text}%' or prefectures_name like '%${iPrefectures.textHankaku}%' limit ${limit}")
	List<IPrefectures> findByText(@Param("iPrefectures") BuildingRelationFindByTextFormDTO dto, @Param("limit") int limit);

	@Select("select * from i_prefectures where is_deleted is false and jis_prefectures_id = '${jisPrefecturesId}'")
	IPrefectures findByJisPrefecturesId(@Param("jisPrefecturesId") String jisPrefecturesId);
}

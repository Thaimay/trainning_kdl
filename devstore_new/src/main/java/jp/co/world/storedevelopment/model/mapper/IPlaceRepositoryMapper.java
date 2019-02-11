package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.IPlace;

public interface IPlaceRepositoryMapper extends RepositoryMapper<IPlace> {

	@Select("select * from i_place where origin_building_id = ${originBuildingId}")
	List<IPlace> findByBuildingId(@Param("originBuildingId") Long originBuildingId);

	@Select("select * from i_place where place_id = ${placeId}")
	IPlace findByPlaceId(@Param("placeId") Long placeId);

	@Select("select * from i_place where origin_building_id = ${originBuildingId}")
	List<IPlace> getIPlaceListByOriginBuildingId(@Param("originBuildingId") Long originBuildingId);

	@Select("select * from i_place where is_deleted is false and place_id = '${id}'")
	IPlace findByImportCode(@Param("id") Long placeId);
}

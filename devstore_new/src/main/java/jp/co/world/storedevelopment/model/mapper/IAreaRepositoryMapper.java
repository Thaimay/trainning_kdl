package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.IArea;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingRelationFindByTextFormDTO;

public interface IAreaRepositoryMapper extends RepositoryMapper<IArea> {

	@Select("select * from i_area where area_name like '%${iArea.text}%' or area_name like '%${iArea.textHankaku}%' limit ${limit}")
	List<IArea> findByText(@Param("iArea") BuildingRelationFindByTextFormDTO dto, @Param("limit") int limit);

	@Select("select * from i_area where is_deleted is false and area_cd = '${code}'")
	IArea findByImportCode(@Param("code") String areaCd);

}

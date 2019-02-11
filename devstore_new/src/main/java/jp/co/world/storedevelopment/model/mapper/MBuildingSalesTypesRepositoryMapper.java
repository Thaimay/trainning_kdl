package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.MBuildingSalesTypes;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingRelationFindByTextFormDTO;

public interface MBuildingSalesTypesRepositoryMapper extends RepositoryMapper<MBuildingSalesTypes> {

	@Select("select * from m_building_sales_types where value like '${mBuildingSalesTypes.text}%' or value like '${mBuildingSalesTypes.textHankaku}%' limit ${limit}")
	List<MBuildingSalesTypes> findByText(
			@Param("mBuildingSalesTypes") BuildingRelationFindByTextFormDTO dto, @Param("limit") int limit);

}

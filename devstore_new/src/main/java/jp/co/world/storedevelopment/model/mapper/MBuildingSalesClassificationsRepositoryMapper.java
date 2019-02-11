package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.MBuildingSalesClassifications;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingRelationFindByTextFormDTO;

public interface MBuildingSalesClassificationsRepositoryMapper extends RepositoryMapper<MBuildingSalesClassifications> {

	@Select("select * from m_building_sales_classifications where value like '${mBuildingSalesClassifications.text}%' or value like '${mBuildingSalesClassifications.textHankaku}%' limit ${limit}")
	List<MBuildingSalesClassifications> findByText(@Param("mBuildingSalesClassifications") BuildingRelationFindByTextFormDTO dto,
			@Param("limit") int limit);

}

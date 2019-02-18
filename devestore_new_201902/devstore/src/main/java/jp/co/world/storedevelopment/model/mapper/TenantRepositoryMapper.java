package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.Tenant;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingRelationFindByTextFormDTO;

/**
 * @author TaiNM
 *
 */
public interface TenantRepositoryMapper extends RepositoryMapper<Tenant> {

	@Select("select * from tenant where name like '${tenant.text}%' or name like '${tenant.textHankaku}%' limit ${limit}")
	List<Tenant> findByText(@Param("tenant") BuildingRelationFindByTextFormDTO dto, @Param("limit") int limit);

}

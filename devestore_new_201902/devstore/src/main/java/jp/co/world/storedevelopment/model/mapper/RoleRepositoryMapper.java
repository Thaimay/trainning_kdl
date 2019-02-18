package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.Role;

public interface RoleRepositoryMapper extends RepositoryMapper<Role> {
	@Select("select * from role order by id")
	public List<Role> findAllOrderById();
}

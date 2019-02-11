package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.Brand;

public interface BrandRepositoryMapper extends RepositoryMapper<Brand> {

	@Select("select * from BRAND where name like '${text}%' or name like '${hankaku}%' limit ${limit}")
	List<Brand> findByText(@Param("text") String text, @Param("hankaku") String hankaku, @Param("limit") int limit);

}

package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.IBrand;

public interface IBrandRepositoryMapper extends RepositoryMapper<IBrand> {
	@Select("select * from i_brand where is_deleted is false and brand_cd = '${code}'")
	IBrand findByImportCode(@Param("code") String brandCd);

	@Select("select * from i_brand where brand_name like '${text}%' or brand_name like '${hankaku}%' limit ${limit}")
	List<IBrand> findByText(@Param("text") String text, @Param("hankaku") String hankaku, @Param("limit") int limit);
}

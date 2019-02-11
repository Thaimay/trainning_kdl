package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

public interface ShopRelatedRepositoryMapper<T> extends RepositoryMapper<T> {

	@Select("select * from ${table} where shop_id = #{shopId} and is_deleted is false order by update_datetime desc")
	List<T> findByShopId(@Param("table") String table, @Param("shopId") Long shopId);

	@Delete("delete from ${table} where shop_id = #{shopId}")
	int deleteByShopId(@Param("table") String table, @Param("shopId") Long shopId);

	@Select("select * from ${table} where shop_cd = '${shopCd}' and is_deleted is false order by update_datetime desc")
	List<T> findByShopCd(@Param("table") String table, @Param("shopCd") String shopCd);
}

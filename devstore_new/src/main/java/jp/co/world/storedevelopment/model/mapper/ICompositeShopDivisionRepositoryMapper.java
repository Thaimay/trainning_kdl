package jp.co.world.storedevelopment.model.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.ICompositeShopDivision;

public interface ICompositeShopDivisionRepositoryMapper extends RepositoryMapper<ICompositeShopDivision> {
	@Select("select * from i_composite_shop_division where is_deleted is false and composite_shop_division_cd = '${code}'")
	ICompositeShopDivision findByImportCode(@Param("code") String compositeShopDivisionCd);
}

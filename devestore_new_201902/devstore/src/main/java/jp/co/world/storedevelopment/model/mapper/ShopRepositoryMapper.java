package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.IShop;
import jp.co.world.storedevelopment.model.Shop;
import jp.co.world.storedevelopment.model.ShopHistory;
import jp.co.world.storedevelopment.pc.controller.form.ShopFindForm;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingRelationFindByTextFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.ShopFindFormDTO;

/**
 * @author TaiNM
 *
 */
public interface ShopRepositoryMapper extends RepositoryMapper<Shop> {

	IShop findShopById(Long id);

	List<IShop> findShop(ShopFindFormDTO dto);

	Integer getCountFindForPC(ShopFindForm dto);

	List<IShop> findForPC(ShopFindForm dto);

	@Select("select * from i_shop where shop_name_zenkaku like '${iShop.text}%' or shop_name_zenkaku_hankaku like '${iShop.textHankaku}%' limit ${limit}")
	List<IShop> findByText(@Param("iShop") BuildingRelationFindByTextFormDTO dto, @Param("limit") int limit);

	@Select("select * from i_shop where shop_cd like '${iShop.text}%' limit ${limit}")
	List<IShop> findCodeByText(@Param("iShop") BuildingRelationFindByTextFormDTO dto, @Param("limit") int limit);

	void updateShopHistory(Long id);

	ShopHistory findShopHistoryById(Long id);

	@Select("select * from shop_history where is_deleted is false and shop_id = ${shopId}")
	List<ShopHistory> getShopHistoryList(@Param("shopId") String shopId);

	@Select("select * from shop where is_deleted is false and i_shop_id = ${iShopId} LIMIT 1")
	Shop findByIShopId(@Param("iShopId") Long iShopId);

	List<IShop> findByNameForNegotiationFile(@Param("text") String text, @Param("hankaku") String hankaku, @Param("params") List<Long> params,
			@Param("limit") int limit);
}

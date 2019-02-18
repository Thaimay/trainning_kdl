package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.MShopType;

public interface MShopTypeRepositoryMapper extends RepositoryMapper<MShopType> {
	@Select("select * from m_shop_type where is_deleted is false order by disp_order")
	List<MShopType> getAllShopType();
}

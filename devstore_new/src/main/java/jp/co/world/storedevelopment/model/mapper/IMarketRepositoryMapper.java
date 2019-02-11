package jp.co.world.storedevelopment.model.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.IMarket;

public interface IMarketRepositoryMapper extends RepositoryMapper<IMarket> {
	@Select("select * from i_market where is_deleted is false and market_cd = '${code}'")
	IMarket findByImportCode(@Param("code") String marketCd);
}

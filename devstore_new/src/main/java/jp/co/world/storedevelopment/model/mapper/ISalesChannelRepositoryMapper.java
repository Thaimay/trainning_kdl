package jp.co.world.storedevelopment.model.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.ISalesChannel;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingRelationFindByTextFormDTO;

public interface ISalesChannelRepositoryMapper extends RepositoryMapper<ISalesChannel> {

	@Select("select * from i_sales_channel where sales_channel_name like '${iSalesChannel.text}%' or sales_channel_name like '${iSalesChannel.textHankaku}%' limit ${limit}")
	List<ISalesChannel> findByText(@Param("iSalesChannel") BuildingRelationFindByTextFormDTO dto, @Param("limit") int limit);

	@Select("select * from i_sales_channel where is_deleted is false and sales_channel_id = '${code}'")
	ISalesChannel findByImportCode(@Param("code") BigDecimal salesChannelId);
}

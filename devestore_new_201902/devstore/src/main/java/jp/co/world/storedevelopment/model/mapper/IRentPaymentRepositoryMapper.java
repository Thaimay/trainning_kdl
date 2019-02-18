package jp.co.world.storedevelopment.model.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.IRentPayment;

public interface IRentPaymentRepositoryMapper extends RepositoryMapper<IRentPayment> {
	@Select("select * from i_rent_payment where is_deleted is false and rent_payment_id = '${code}'")
	IRentPayment findByImportCode(@Param("code") String rentPaymentId);
}

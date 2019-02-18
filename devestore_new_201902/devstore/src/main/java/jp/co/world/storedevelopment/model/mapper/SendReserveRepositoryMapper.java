package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.SendReserve;

public interface SendReserveRepositoryMapper extends RepositoryMapper<SendReserve> {
    @Select("SELECT * FROM send_reserve WHERE is_deleted IS false")
    List<SendReserve> getSendReserveList();
    
    @Select("SELECT * FROM send_reserve WHERE send_status = '01' OR send_status = '06'")
    List<SendReserve> findBySendReserve();

    @Select("SELECT * FROM send_reserve WHERE send_status = '02'")
    List<SendReserve> getExtracted();

    @Select("SELECT * FROM send_reserve WHERE send_status = '03'")
    List<SendReserve> getDeliveryReserved();
}

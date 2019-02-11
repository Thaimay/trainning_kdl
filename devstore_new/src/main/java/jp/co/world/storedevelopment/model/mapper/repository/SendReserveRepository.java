package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.SendReserve;
import jp.co.world.storedevelopment.model.mapper.SendReserveRepositoryMapper;

/**
 * 
 * @author togashi
 *
 */
public class SendReserveRepository extends Repository<SendReserve, SendReserveRepositoryMapper> {

    @Override
    protected SendReserveRepositoryMapper repositoryMapper(SqlSession session) {
        return session.getMapper(SendReserveRepositoryMapper.class);
    }

    @Override
    public String tableName() {
        return ActiveModelStringUtils.toTableName(SendReserve.class);
    }

    public List<SendReserve> getSendReserveList() {
        return execute((mapper) -> {
            return mapper.getSendReserveList();
        });
    }

    public List<SendReserve> findBySendReserve() {
        return execute((mapper) -> {
            return mapper.findBySendReserve();
        });
    }
    
    public List<SendReserve> getDeliveryReserved() {
        return execute((mapper) -> {
            return mapper.getDeliveryReserved();
        });
    }

    public List<SendReserve> getExtracted() {
        return execute((mapper) -> {
            return mapper.getExtracted();
        });
    }

}

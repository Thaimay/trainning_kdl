package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.IBusinessCardBatch;

public interface IBusinessCardBatchRepositoryMapper  extends RepositoryMapper<IBusinessCardBatch> {

	@Select("select * from i_business_card_batch where is_success is false order by id")
	List<IBusinessCardBatch> getFailList();

	@Select("select * from i_business_card_batch where target_date = '${targetDate}' and is_deleted is false")
	IBusinessCardBatch findByTargetDate(@Param("targetDate") String targetDate);

}

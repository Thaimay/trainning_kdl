package jp.co.world.storedevelopment.model.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.AccountData;

public interface AccountDataRepositoryMapper extends RepositoryMapper<AccountData> {
	@Select("select * from account_data where is_deleted is false and account_id = ${id}")
	AccountData findByAccountId(@Param("id") Long id);
}

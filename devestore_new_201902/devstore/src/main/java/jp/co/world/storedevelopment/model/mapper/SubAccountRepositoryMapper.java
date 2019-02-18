package jp.co.world.storedevelopment.model.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.SubAccount;

public interface SubAccountRepositoryMapper extends RepositoryMapper<SubAccount> {

	@Select("select * from SUB_ACCOUNT where employee_code = '${code}' ")
	SubAccount findByEmployCode(@Param("code") String code);

}

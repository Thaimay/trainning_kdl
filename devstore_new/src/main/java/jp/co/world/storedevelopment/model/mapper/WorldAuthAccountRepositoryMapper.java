package jp.co.world.storedevelopment.model.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.WorldAuthAccount;

public interface WorldAuthAccountRepositoryMapper extends RepositoryMapper<WorldAuthAccount> {

	@Select("select * from employee where email = '${email}' and approval_flg = 1 ")
	WorldAuthAccount findByMail(@Param("email") String email);
}

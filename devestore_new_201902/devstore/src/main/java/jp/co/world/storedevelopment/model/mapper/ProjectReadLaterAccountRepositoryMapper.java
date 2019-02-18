package jp.co.world.storedevelopment.model.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Project;
import jp.co.world.storedevelopment.model.ProjectReadLaterAccount;

public interface ProjectReadLaterAccountRepositoryMapper extends RepositoryMapper<ProjectReadLaterAccount> {

	@Select("select * from project_read_later_account where project_id = #{project.id} and account_id = #{account.id}  LIMIT 1 OFFSET 0")
	ProjectReadLaterAccount findByAccount(@Param("project") Project project, @Param("account") Account account);

}

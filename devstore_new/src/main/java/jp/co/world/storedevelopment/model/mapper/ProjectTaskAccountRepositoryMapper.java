package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.ProjectTaskAccount;

public interface ProjectTaskAccountRepositoryMapper extends RepositoryMapper<ProjectTaskAccount> {
	@Select("select * from project_task_account where is_deleted is false and project_task_id = '${id}'")
	List<ProjectTaskAccount> findByProjectTaskId(@Param("id") Long projectTaskId);
	
	@Select("select * from i_account WHERE is_deleted = false AND id IN (select account_id from project_task_account where is_deleted is false and project_task_id = '${id}')")
	List<Account> findBy(@Param("id") Long projectTaskId);
	
	@Delete("delete from ${table} where project_task_id = #{projectTaskId}")
	int deleteByProjectTaskId(@Param("table") String table, @Param("projectTaskId") Long projectTaskId);
}

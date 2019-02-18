package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.ProjectTaskAccount;
import jp.co.world.storedevelopment.model.mapper.ProjectTaskAccountRepositoryMapper;

public class ProjectTaskAccountRepository extends Repository<ProjectTaskAccount, ProjectTaskAccountRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(ProjectTaskAccount.class);
	}

	@Override
	protected ProjectTaskAccountRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(ProjectTaskAccountRepositoryMapper.class);
	}
	
	public List<Account> findBy(Long id) {
		return execute((mapper) -> {
			return mapper.findBy(id);
		});
	}

	public List<ProjectTaskAccount> findByProjectTaskId(Long id) {
		return execute((mapper) -> {
			return mapper.findByProjectTaskId(id);
		});
	}

	public int deleteByProjectTaskId(Long projectTaskId) {
		return execute(mapper -> {
			return mapper.deleteByProjectTaskId(tableName(), projectTaskId);
		});
	}
}

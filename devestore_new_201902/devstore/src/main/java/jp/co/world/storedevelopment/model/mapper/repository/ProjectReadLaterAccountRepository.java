package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Project;
import jp.co.world.storedevelopment.model.ProjectReadLaterAccount;
import jp.co.world.storedevelopment.model.mapper.ProjectReadLaterAccountRepositoryMapper;

public class ProjectReadLaterAccountRepository
		extends Repository<ProjectReadLaterAccount, ProjectReadLaterAccountRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(ProjectReadLaterAccount.class);
	}

	@Override
	protected ProjectReadLaterAccountRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(ProjectReadLaterAccountRepositoryMapper.class);
	}

	public void switchReadLater(Project project, Account account) {
		Optional<ProjectReadLaterAccount> opt = findByAccount(project, account);
		if (opt.isPresent()) {
			opt.get().delete();
		} else {
			new ProjectReadLaterAccount(project, account).create();
		}
	}

	public boolean isReadLater(Project project, Account account) {
		return execute((mapper) -> {
			return findByAccount(project, account).isPresent();
		});
	}

	public Optional<ProjectReadLaterAccount> findByAccount(Project project, Account account) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByAccount(project, account));
		});
	}

}

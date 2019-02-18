package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.Repository;
import com.tierline.mybatis.activemodel.SessionFactory;

import jp.co.world.storedevelopment.WoldAuthSessionFactory;
import jp.co.world.storedevelopment.model.WorldAuthAccount;
import jp.co.world.storedevelopment.model.mapper.WorldAuthAccountRepositoryMapper;

public class WorldAuthAccountRepository extends Repository<WorldAuthAccount, WorldAuthAccountRepositoryMapper> {

	@Override
	protected WorldAuthAccountRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(WorldAuthAccountRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return "employee";
	}

	@Override
	public String primaryKeyName() {
		return "uid";
	}

	@Override
	protected SessionFactory getSessionFactory() {
		return WoldAuthSessionFactory.getInstance();
	}

	public Optional<WorldAuthAccount> findByMail(String email) {
		return execute((mapper) -> {
			WorldAuthAccount account = mapper.findByMail(email);
			return Optional.ofNullable(account);
		});
	}

}

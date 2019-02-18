package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.OtherProjectAccount;
import jp.co.world.storedevelopment.model.mapper.OtherProjectAccountRepositoryMapper;

public class OtherProjectAccountRepository
		extends ProjectRelatedRepository<OtherProjectAccount, OtherProjectAccountRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(OtherProjectAccount.class);
	}

	@Override
	protected OtherProjectAccountRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(OtherProjectAccountRepositoryMapper.class);
	}
	
	public List<Account> findAccountByProjectId(Long id) {
		return execute((mapper) -> {
			return mapper.findAccountByProjectId(id);
		});
	}
	
	public List<Account> findStoreSalesByProjectId(Long id) {
		return execute((mapper) -> {
			return mapper.findStoreSalesByProjectId(id);
		});		
	}

	public List<Account> findStoreByProjectId(Long id) {
		return execute((mapper) -> {
			return mapper.findStoreByProjectId(id);
		});		
	}
}

package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.IWcpMember;
import jp.co.world.storedevelopment.model.mapper.IWcpMemberRepositoryMapper;

public class IWcpMemberRepository extends Repository<IWcpMember, IWcpMemberRepositoryMapper> {

	@Override
	protected IWcpMemberRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(IWcpMemberRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(IWcpMember.class);
	}

	public Optional<IWcpMember> findByImportCode(IWcpMember model) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByImportCode(model.getDispOrder()));
		});
	}
}

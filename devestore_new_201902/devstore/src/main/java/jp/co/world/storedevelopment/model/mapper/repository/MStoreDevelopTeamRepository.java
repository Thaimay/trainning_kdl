package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.MStoreDevelopTeam;
import jp.co.world.storedevelopment.model.mapper.MStoreDevelopTeamRepositoryMapper;

public class MStoreDevelopTeamRepository extends Repository<MStoreDevelopTeam, MStoreDevelopTeamRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(MStoreDevelopTeam.class);
	}

	@Override
	protected MStoreDevelopTeamRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(MStoreDevelopTeamRepositoryMapper.class);
	}

	public Optional<MStoreDevelopTeam> findByDeptCd(String deptCd) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByDeptCd(deptCd));
		});
	}

	public List<MStoreDevelopTeam> findByALL() {
		return execute((mapper) -> {
			return mapper.findByALL();
		});
	}
}

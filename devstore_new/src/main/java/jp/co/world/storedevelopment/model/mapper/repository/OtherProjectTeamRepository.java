package jp.co.world.storedevelopment.model.mapper.repository;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;

import jp.co.world.storedevelopment.model.OtherProjectTeam;
import jp.co.world.storedevelopment.model.mapper.OtherProjectTeamRepositoryMapper;

public class OtherProjectTeamRepository
		extends ProjectRelatedRepository<OtherProjectTeam, OtherProjectTeamRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(OtherProjectTeam.class);
	}

	@Override
	protected OtherProjectTeamRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(OtherProjectTeamRepositoryMapper.class);
	}

}

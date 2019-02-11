package jp.co.world.storedevelopment.model.mapper.repository;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;

import jp.co.world.storedevelopment.model.BuildingSales;
import jp.co.world.storedevelopment.model.mapper.BuildingSalesRepositoryMapper;

public class BuildingSalesRepository extends BuildingRelatedRepository<BuildingSales, BuildingSalesRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(BuildingSales.class);
	}

	@Override
	protected BuildingSalesRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(BuildingSalesRepositoryMapper.class);
	}

}

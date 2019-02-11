package jp.co.world.storedevelopment.model.mapper.repository;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.IAdoptDifficuly;
import jp.co.world.storedevelopment.model.mapper.IAdoptDifficulyRepositoryMapper;

public class IAdoptDifficulyRepository extends Repository<IAdoptDifficuly, IAdoptDifficulyRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(IAdoptDifficuly.class);
	}

	@Override
	protected IAdoptDifficulyRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(IAdoptDifficulyRepositoryMapper.class);
	}

}

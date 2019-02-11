package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.RecentChange;
import jp.co.world.storedevelopment.model.mapper.RecentChangeRepositoryMapper;

public class RecentChangeRepository extends Repository<RecentChange, RecentChangeRepositoryMapper> {

	@Override
	protected RecentChangeRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(RecentChangeRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(RecentChange.class);
	}

	public List<RecentChange> findRecentChangeBuilding() {
		return execute((mapper) -> {
			return mapper.findRecentChangeBuilding();
		});
	}

	public List<RecentChange> findRecentChangeBuildingFile() {
		return execute((mapper) -> {
			return mapper.findRecentChangeBuildingFile();
		});
	}

	public List<RecentChange> findRecentChangeNegotiation() {
		return execute((mapper) -> {
			return mapper.findRecentChangeNegotiation();
		});
	}

}

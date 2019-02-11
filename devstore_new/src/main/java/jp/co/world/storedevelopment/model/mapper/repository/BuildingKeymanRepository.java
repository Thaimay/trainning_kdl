package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;

import jp.co.world.storedevelopment.model.Building;
import jp.co.world.storedevelopment.model.BuildingKeyman;
import jp.co.world.storedevelopment.model.Keyman;
import jp.co.world.storedevelopment.model.mapper.BuildingKeymanRepositoryMapper;

public class BuildingKeymanRepository
		extends BuildingRelatedRepository<BuildingKeyman, BuildingKeymanRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(BuildingKeyman.class);
	}

	@Override
	protected BuildingKeymanRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(BuildingKeymanRepositoryMapper.class);
	}

	public Boolean existsBy(String buildingCode, Long businessCardId) {
		return execute((mapper) -> {
			return mapper.existsBy(buildingCode, businessCardId) != null;
		});
	}

	public Boolean existsByKeyman(Building b, Keyman k) {
		return execute((mapper) -> {
			Optional<BuildingKeyman> keyman = Optional.ofNullable(mapper.existsByKeyman(b, k));
			return keyman.isPresent();
		});
	}
}

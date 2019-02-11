package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;

import jp.co.world.storedevelopment.model.BuildingFile;
import jp.co.world.storedevelopment.model.File;
import jp.co.world.storedevelopment.model.mapper.BuildingFileRepositoryMapper;

public class BuildingFileRepository extends BuildingRelatedRepository<BuildingFile, BuildingFileRepositoryMapper> {
	@Override
	protected BuildingFileRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(BuildingFileRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(File.class);
	}

	@Override
	public List<BuildingFile> findByBuildingId(Long buildingId) {
		return execute(mapper -> {
			return mapper.findByBuildingId(tableName(), buildingId).stream().filter(x -> x.isDocument())
					.collect(Collectors.toList());
		});
	}

	public List<BuildingFile> findBuildingFileRelatedProject(Long buildingId) {
		return execute(mapper -> {
			return mapper.findBuildingFileRelatedProject(buildingId).stream().filter(x -> x.isDocument())
					.collect(Collectors.toList());
		});
	}
}

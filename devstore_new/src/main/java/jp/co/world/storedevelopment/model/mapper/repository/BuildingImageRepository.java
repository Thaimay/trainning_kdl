package jp.co.world.storedevelopment.model.mapper.repository;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;

import jp.co.world.storedevelopment.model.BuildingImage;
import jp.co.world.storedevelopment.model.File;
import jp.co.world.storedevelopment.model.mapper.BuildingImageRepositoryMapper;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingRelationFindByTextFormDTO;

public class BuildingImageRepository extends BuildingRelatedRepository<BuildingImage, BuildingImageRepositoryMapper> {
	@Override
	protected BuildingImageRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(BuildingImageRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(File.class);
	}

	@Override
	public List<BuildingImage> findByBuildingId(Long buildingId) {
		return execute(mapper -> {
			return mapper.findByBuildingId(tableName(), buildingId).stream().filter(x -> x.isImage())
					.collect(Collectors.toList());
		});
	}

	public List<BuildingImage> findBuildingImageRelatedProject(Long buildingId) {
		return execute(mapper -> {
			return mapper.findBuildingImageRelatedProject(buildingId).stream().filter(x -> x.isImage())
					.collect(Collectors.toList());
		});
	}

	public List<BuildingImage> findByProject(BuildingRelationFindByTextFormDTO dto) {
		return execute((mapper) -> {
			return mapper.findByProject(dto);
		});
	}

	public void deleteAll() {
		try {
			String path = new BuildingImage().basePath();
			java.io.File imageDir = new java.io.File(path);
			java.io.File gitKeep = new java.io.File(path + "/.gitkeep");
			if (imageDir.exists()) {
				FileUtils.cleanDirectory(imageDir);
				FileUtils.touch(gitKeep);
			}
			super.deleteAll();
			imageDir.mkdir();
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalStateException("ファイルの削除に失敗しました。");
		}
	}

}

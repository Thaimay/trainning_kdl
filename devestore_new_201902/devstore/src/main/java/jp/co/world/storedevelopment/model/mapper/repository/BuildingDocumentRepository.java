package jp.co.world.storedevelopment.model.mapper.repository;

import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.BuildingDocument;
import jp.co.world.storedevelopment.model.File;
import jp.co.world.storedevelopment.model.mapper.BuildingDocumentRepositoryMapper;

public class BuildingDocumentRepository extends Repository<BuildingDocument, BuildingDocumentRepositoryMapper> {
	@Override
	protected BuildingDocumentRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(BuildingDocumentRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(File.class);
	}

	@Override
	public void deleteAll() {
		try {
			String path = new BuildingDocument().basePath();
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

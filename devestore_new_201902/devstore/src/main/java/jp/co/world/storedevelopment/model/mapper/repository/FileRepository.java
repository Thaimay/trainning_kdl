package jp.co.world.storedevelopment.model.mapper.repository;

import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;

import jp.co.world.storedevelopment.model.File;
import jp.co.world.storedevelopment.model.mapper.FileRepositoryMapper;

abstract public class FileRepository extends NegotiationRelatedRepository<File, FileRepositoryMapper> {

	@Override
	protected FileRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(FileRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(File.class);
	}

	@Override
	public void deleteAll() {
		try {

			java.io.File imageDir = new java.io.File(File.IMAGE_FILE_PATH);
			if (imageDir.exists()) {
				FileUtils.cleanDirectory(imageDir);
			}
			super.deleteAll();
			imageDir.mkdir();
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalStateException("ファイルの削除に失敗しました。");
		}
	}

}

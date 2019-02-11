package jp.co.world.storedevelopment.model.mapper.repository;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.File;
import jp.co.world.storedevelopment.model.SalesFile;
import jp.co.world.storedevelopment.model.mapper.SalesFileRepositoryMapper;
import jp.co.world.storedevelopment.pc.controller.form.SalesDeleteForm;
import jp.co.world.storedevelopment.pc.controller.form.SalesFindForm;

public class SalesFileRepository extends Repository<SalesFile, SalesFileRepositoryMapper> {
	@Override
	protected SalesFileRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(SalesFileRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(File.class);
	}

	public List<SalesFile> findByText(String text) {
		return execute((mapper) -> {
			return mapper.findByText(text);
		});
	}

	@Override
	public List<SalesFile> findAll() {
		return execute((mapper) -> {
			return mapper.findSalesFile();
		});
	}

	public List<SalesFile> find(SalesFindForm form) {
		return execute((mapper) -> {
			return mapper.find(form);
		});
	}

	public void deleteByIds(SalesDeleteForm form) {
		execute((mapper) -> {
			mapper.deleteByIds(form);
		});
	}

	@Override
	public void deleteAll() {
		try {
			String path = new SalesFile().basePath();
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

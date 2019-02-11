package jp.co.world.storedevelopment.model.mapper.repository;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;

import jp.co.world.storedevelopment.model.File;
import jp.co.world.storedevelopment.model.Negotiation;
import jp.co.world.storedevelopment.model.NegotiationImage;
import jp.co.world.storedevelopment.model.mapper.NegotiationImageRepositoryMapper;

public class NegotiationImageRepository
		extends NegotiationRelatedRepository<NegotiationImage, NegotiationImageRepositoryMapper> {
	@Override
	protected NegotiationImageRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(NegotiationImageRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(File.class);
	}

	@Override
	public List<NegotiationImage> findByNegotiation(Negotiation n) {
		return execute(mapper -> {
			return mapper.findOfNegotiation(tableName(), n).stream().filter(x -> x.isImage())
					.collect(Collectors.toList());
		});
	}

	@Override
	public void deleteAll() {
		try {
			String path = new NegotiationImage().basePath();
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

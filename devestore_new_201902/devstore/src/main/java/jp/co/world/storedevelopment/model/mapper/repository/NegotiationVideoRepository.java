package jp.co.world.storedevelopment.model.mapper.repository;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;

import jp.co.world.storedevelopment.model.File;
import jp.co.world.storedevelopment.model.Negotiation;
import jp.co.world.storedevelopment.model.NegotiationVideo;
import jp.co.world.storedevelopment.model.mapper.NegotiationVideoRepositoryMapper;

public class NegotiationVideoRepository
		extends NegotiationRelatedRepository<NegotiationVideo, NegotiationVideoRepositoryMapper> {
	@Override
	protected NegotiationVideoRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(NegotiationVideoRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(File.class);
	}

	@Override
	public List<NegotiationVideo> findByNegotiation(Negotiation n) {
		return execute(mapper -> {
			return mapper.findOfNegotiation(tableName(), n).stream().filter(x -> x.isVideo())
					.collect(Collectors.toList());
		});
	}

	@Override
	public void deleteAll() {
		try {
			String path = new NegotiationVideo().basePath();
			java.io.File dir = new java.io.File(path);
			java.io.File gitKeep = new java.io.File(path + "/.gitkeep");
			if (dir.exists()) {
				FileUtils.cleanDirectory(dir);
				FileUtils.touch(gitKeep);
			}
			super.deleteAll();
			dir.mkdir();
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalStateException("ファイルの削除に失敗しました。");
		}
	}

}

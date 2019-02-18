package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;

import jp.co.world.storedevelopment.model.File;
import jp.co.world.storedevelopment.model.Negotiation;
import jp.co.world.storedevelopment.model.NegotiationFile;
import jp.co.world.storedevelopment.model.mapper.NegotiationFileRepositoryMapper;

public class NegotiationFileRepository
		extends NegotiationRelatedRepository<NegotiationFile, NegotiationFileRepositoryMapper> {
	@Override
	protected NegotiationFileRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(NegotiationFileRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(File.class);
	}

	@Override
	public List<NegotiationFile> findByNegotiation(Negotiation n) {
		return execute(mapper -> {
			return mapper.findOfNegotiation(tableName(), n).stream().filter(x -> x.isDocument())
					.collect(Collectors.toList());
		});
	}
}

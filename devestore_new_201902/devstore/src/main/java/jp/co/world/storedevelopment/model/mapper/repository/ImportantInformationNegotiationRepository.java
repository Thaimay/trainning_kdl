package jp.co.world.storedevelopment.model.mapper.repository;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.ImportantInformationNegotiation;
import jp.co.world.storedevelopment.model.mapper.ImportantInformationNegotiationRepositoryMapper;

public class ImportantInformationNegotiationRepository
        extends Repository<ImportantInformationNegotiation, ImportantInformationNegotiationRepositoryMapper> {

    @Override
    protected ImportantInformationNegotiationRepositoryMapper repositoryMapper(SqlSession session) {
        return session.getMapper(ImportantInformationNegotiationRepositoryMapper.class);
    }

    @Override
    public String tableName() {
        return ActiveModelStringUtils.toTableName(ImportantInformationNegotiation.class);
    }

	public int deleteByNegotiationId(Long negotiationId) {
		return execute(mapper -> {
			return mapper.deleteByNegotiationId(negotiationId);
		});
	}
}

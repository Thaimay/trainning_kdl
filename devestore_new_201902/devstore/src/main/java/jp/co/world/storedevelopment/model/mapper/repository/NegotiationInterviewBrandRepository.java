package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.Negotiation;
import jp.co.world.storedevelopment.model.NegotiationInterviewBrand;
import jp.co.world.storedevelopment.model.mapper.NegotiationInterviewBrandRepositoryMapper;

public class NegotiationInterviewBrandRepository
		extends Repository<NegotiationInterviewBrand, NegotiationInterviewBrandRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(NegotiationInterviewBrand.class);
	}

	@Override
	protected NegotiationInterviewBrandRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(NegotiationInterviewBrandRepositoryMapper.class);
	}

	public List<NegotiationInterviewBrand> findOfNegotiation(Negotiation negotiation) {
		return execute(mapper -> {
			return mapper.findOfNegotiation(negotiation);
		});
	}

	public int deleteByNegotiation(Negotiation negotiation) {
		return execute(mapper -> {
			return mapper.deleteByNegotiation(negotiation);
		});
	}

	public List<NegotiationInterviewBrand> findByBrandId(Long id) {
		return execute((mapper) -> {
			return mapper.findByBrandId(id);
		});
	}
}

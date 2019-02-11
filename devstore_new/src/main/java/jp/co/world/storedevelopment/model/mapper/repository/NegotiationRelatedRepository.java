package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;

import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.Negotiation;
import jp.co.world.storedevelopment.model.mapper.NegotiationRelatedRepositoryMapper;

public abstract class NegotiationRelatedRepository<T, M extends NegotiationRelatedRepositoryMapper<T>>
		extends Repository<T, M> {

	public List<T> findByNegotiation(Negotiation negotiation) {
		return execute(mapper -> {
			return mapper.findOfNegotiation(tableName(), negotiation);
		});
	}

	public int deleteByNegotiation(Negotiation negotiation) {
		return execute(mapper -> {
			return mapper.deleteByNegotiation(tableName(), negotiation);
		});
	}

	public List<T> findByNegotiationHistory(Negotiation negotiation) {
		return execute(mapper -> {
			return mapper.findOfNegotiationHistory(tableName(), negotiation);
		});
	}
}

package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.IBusinessCardBatch;
import jp.co.world.storedevelopment.model.mapper.IBusinessCardBatchRepositoryMapper;

public class IBusinessCardBatchRepository  extends Repository<IBusinessCardBatch, IBusinessCardBatchRepositoryMapper> {

	@Override
	protected IBusinessCardBatchRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(IBusinessCardBatchRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(IBusinessCardBatch.class);
	}

	public List<IBusinessCardBatch> getFailList() {
        return execute((mapper) -> {
            return mapper.getFailList();
        });
	}

	public Optional<IBusinessCardBatch> findByTargetDate(String targetDate) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByTargetDate(targetDate));
		});
	}

}

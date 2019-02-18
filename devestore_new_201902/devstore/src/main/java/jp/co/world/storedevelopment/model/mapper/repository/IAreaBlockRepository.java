package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.IAreaBlock;
import jp.co.world.storedevelopment.model.mapper.IAreaBlockRepositoryMapper;

public class IAreaBlockRepository extends Repository<IAreaBlock, IAreaBlockRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(IAreaBlock.class);
	}

	@Override
	protected IAreaBlockRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(IAreaBlockRepositoryMapper.class);
	}

	public Optional<IAreaBlock> findByImportCode(IAreaBlock model) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByImportCode(model.getMainBlockCd()));
		});
	}
}

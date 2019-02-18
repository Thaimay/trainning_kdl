package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.IArea;
import jp.co.world.storedevelopment.model.IBlock;
import jp.co.world.storedevelopment.model.IPrefectures;
import jp.co.world.storedevelopment.model.MLocality;
import jp.co.world.storedevelopment.model.mapper.MLocalityRepositoryMapper;

public class MLocalityRepository extends Repository<MLocality, MLocalityRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(MLocality.class);
	}

	@Override
	protected MLocalityRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(MLocalityRepositoryMapper.class);
	}

	public List<MLocality> findAllSort() {
		return execute((mapper) -> {
			return mapper.findAllSort();
		});
	}

	public List<IArea> findiAreas() {
		return execute((mapper) -> {
			return mapper.findiAreas();
		});
	}

	public List<IPrefectures> findiprefectures(Long iAreaId) {
		return execute((mapper) -> {
			return mapper.findiprefectures(iAreaId);
		});
	}

	public List<IBlock> findiblocks(Long iAreaId,Long iPrefectureId) {
		return execute((mapper) -> {
			return mapper.findiblocks(iAreaId,iPrefectureId);
		});

	}
}

package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.MPeriod;
import jp.co.world.storedevelopment.model.mapper.MPeriodRepositoryMapper;
import jp.co.world.storedevelopment.sp.controller.dto.PeriodSearchListDTO;

public class MPeriodRepository extends Repository<MPeriod, MPeriodRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(MPeriod.class);
	}

	@Override
	protected MPeriodRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(MPeriodRepositoryMapper.class);
	}

	public Optional<MPeriod> getPeriod(String date) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.getPeriod(date));
		});
	}

	public MPeriod findByPeriod(Long period) {
		return execute((mapper) -> {
			return mapper.findByPeriod(period);
		});
	}

	public List<PeriodSearchListDTO> getCurrentImplementationPeriod() {
		return execute((mapper) -> {
			return mapper.getCurrentImplementationPeriod();
		});
	}
}

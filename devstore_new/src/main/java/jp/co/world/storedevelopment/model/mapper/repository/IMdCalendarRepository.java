package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.IMdCalendar;
import jp.co.world.storedevelopment.model.mapper.IMdCalendarRepositoryMapper;

public class IMdCalendarRepository extends Repository<IMdCalendar, IMdCalendarRepositoryMapper> {

	@Override
	protected IMdCalendarRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(IMdCalendarRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(IMdCalendar.class);
	}

	public Optional<IMdCalendar> findByImportCode(IMdCalendar model) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByImportCode(model.getSeqno().toString()));
		});
	}
}

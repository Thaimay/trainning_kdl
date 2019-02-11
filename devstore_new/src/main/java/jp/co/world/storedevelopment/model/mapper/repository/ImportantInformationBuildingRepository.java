package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.ImportantInformation;
import jp.co.world.storedevelopment.model.ImportantInformationBuilding;
import jp.co.world.storedevelopment.model.mapper.ImportantInformationBuildingRepositoryMapper;

public class ImportantInformationBuildingRepository
		extends Repository<ImportantInformationBuilding, ImportantInformationBuildingRepositoryMapper> {

	@Override
	protected ImportantInformationBuildingRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(ImportantInformationBuildingRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(ImportantInformationBuilding.class);
	}

	public List<ImportantInformationBuilding> findByImportantInformationId(Long id) {
		return execute((mapper) -> {
			return mapper.findByImportantInformationId(id);
		});
	}

	public List<ImportantInformationBuilding> findByBuildingId(Long id) {
		return execute((mapper) -> {
			return mapper.findByBuildingId(id);
		});
	}

	public int deleteBy(ImportantInformation in) {
		return execute((mapper) -> {
			return mapper.deleteBy(in);
		});
	}
}

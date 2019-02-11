package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.ImportantInformation;
import jp.co.world.storedevelopment.model.ImportantInformationCorporation;
import jp.co.world.storedevelopment.model.mapper.ImportantInformationCorporationRepositoryMapper;

public class ImportantInformationCorporationRepository
		extends Repository<ImportantInformationCorporation, ImportantInformationCorporationRepositoryMapper> {

	@Override
	protected ImportantInformationCorporationRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(ImportantInformationCorporationRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(ImportantInformationCorporation.class);
	}

	public List<ImportantInformationCorporation> findByImportantInformationId(Long id) {
		return execute((mapper) -> {
			return mapper.findByImportantInformationId(id);
		});
	}

	public int deleteBy(ImportantInformation in) {
		return execute((mapper) -> {
			return mapper.deleteBy(in);
		});
	}

}

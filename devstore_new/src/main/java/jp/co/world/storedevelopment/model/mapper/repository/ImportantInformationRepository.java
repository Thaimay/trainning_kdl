package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.ImportantInformation;
import jp.co.world.storedevelopment.model.Negotiation;
import jp.co.world.storedevelopment.model.mapper.ImportantInformationRepositoryMapper;
import jp.co.world.storedevelopment.sp.controller.dto.ImportantInformationFindForm;
import jp.co.world.storedevelopment.sp.controller.dto.ImportantInformationListDTO;

public class ImportantInformationRepository
        extends Repository<ImportantInformation, ImportantInformationRepositoryMapper> {

    @Override
    protected ImportantInformationRepositoryMapper repositoryMapper(SqlSession session) {
        return session.getMapper(ImportantInformationRepositoryMapper.class);
    }

    @Override
    public String tableName() {
        return ActiveModelStringUtils.toTableName(ImportantInformation.class);
    }

    public List<ImportantInformationListDTO> find(ImportantInformationFindForm dto) {
        return execute((mapper) -> {
            List<ImportantInformation> list = mapper.find(dto);
            return list.stream().map(i -> new ImportantInformationListDTO(i, dto.getAccount()))
                    .collect(Collectors.toList());
        });
    }

    public List<ImportantInformation> findByNegotiation(Negotiation n) {
        return execute((mapper) -> {
            return mapper.findByNegotiation(n);
        });
    }

	public int deleteById(Long id) {
		return execute(mapper -> {
			return mapper.deleteById(id);
		});
	}
}

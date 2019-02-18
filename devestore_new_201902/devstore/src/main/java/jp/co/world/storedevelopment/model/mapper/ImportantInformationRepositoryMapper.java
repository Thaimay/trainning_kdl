package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.ImportantInformation;
import jp.co.world.storedevelopment.model.Negotiation;
import jp.co.world.storedevelopment.model.mapper.repository.sql.ImportantInfomationFindSQLBuilder;
import jp.co.world.storedevelopment.sp.controller.dto.ImportantInformationFindForm;

public interface ImportantInformationRepositoryMapper extends RepositoryMapper<ImportantInformation> {
    @SelectProvider(type = ImportantInfomationFindSQLBuilder.class, method = "build")
    List<ImportantInformation> find(ImportantInformationFindForm dto);

    @SelectProvider(type = ImportantInfomationFindSQLBuilder.class, method = "buildByNegotiation")
    List<ImportantInformation> findByNegotiation(Negotiation negotiation);

    @Delete("delete from important_information where id = #{id}")
	int deleteById(@Param("id") Long id);
}

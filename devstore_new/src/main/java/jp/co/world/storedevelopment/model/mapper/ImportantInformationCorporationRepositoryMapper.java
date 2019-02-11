package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.ImportantInformation;
import jp.co.world.storedevelopment.model.ImportantInformationCorporation;

public interface ImportantInformationCorporationRepositoryMapper
		extends RepositoryMapper<ImportantInformationCorporation> {

	@Select("select * from IMPORTANT_INFORMATION_CORPORATION where important_information_id = ${id}")
	List<ImportantInformationCorporation> findByImportantInformationId(@Param("id") Long id);

	@Delete("delete from IMPORTANT_INFORMATION_CORPORATION where important_information_id = ${important.id}")
	public int deleteBy(@Param("important") ImportantInformation in);

}

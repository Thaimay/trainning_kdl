package jp.co.world.storedevelopment.model.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.ImportantInformationNiceAccount;

public interface ImportantInformationNiceAccountRepositoryMapper
		extends RepositoryMapper<ImportantInformationNiceAccount> {
	@Select("select * from IMPORTANT_INFORMATION_NICE_ACCOUNT where important_information_id = ${importantInformationId} AND account_id = ${accountId}")
	public ImportantInformationNiceAccount findBy(@Param("importantInformationId") Long importantInformationId,
			@Param("accountId") Long accountId);

	@Select("select COUNT(*) from IMPORTANT_INFORMATION_NICE_ACCOUNT where important_information_id = ${importantInformationId}")
	public int countBy(@Param("importantInformationId") Long importantInformationId);
}

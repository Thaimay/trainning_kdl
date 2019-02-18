package jp.co.world.storedevelopment.model.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.ImportantInformationReadAccount;

public interface ImportantInformationReadAccountRepositoryMapper
		extends RepositoryMapper<ImportantInformationReadAccount> {

	@Select("select * from IMPORTANT_INFORMATION_READ_ACCOUNT where important_information_id = ${importantInformationId} AND account_id = ${accountId}")
	public ImportantInformationReadAccount findBy(@Param("importantInformationId") Long importantInformationId,
			@Param("accountId") Long accountId);
}

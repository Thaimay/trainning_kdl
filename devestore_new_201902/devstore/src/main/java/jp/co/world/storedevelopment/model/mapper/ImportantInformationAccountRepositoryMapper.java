package jp.co.world.storedevelopment.model.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.ImportantInformation;
import jp.co.world.storedevelopment.model.ImportantInformationAccount;

public interface ImportantInformationAccountRepositoryMapper extends RepositoryMapper<ImportantInformationAccount> {
    @Select("select * from IMPORTANT_INFORMATION_ACCOUNT where important_information_id = #{important_information.id} and account_id = #{account.id}")
    ImportantInformationAccount findByAccount(@Param("important_information") ImportantInformation importantInformation,
            @Param("account") Account account);

    @Select("select COUNT(*) from IMPORTANT_INFORMATION_ACCOUNT where important_information_id = #{important_information.id} and nice = true")
    int countByImportantInformation(@Param("important_information") ImportantInformation importantInformation);
}

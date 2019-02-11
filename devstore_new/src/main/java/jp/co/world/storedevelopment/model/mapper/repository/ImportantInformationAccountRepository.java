package jp.co.world.storedevelopment.model.mapper.repository;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.ImportantInformation;
import jp.co.world.storedevelopment.model.ImportantInformationAccount;
import jp.co.world.storedevelopment.model.mapper.ImportantInformationAccountRepositoryMapper;

public class ImportantInformationAccountRepository
        extends Repository<ImportantInformationAccount, ImportantInformationAccountRepositoryMapper> {

    @Override
    protected ImportantInformationAccountRepositoryMapper repositoryMapper(SqlSession session) {
        return session.getMapper(ImportantInformationAccountRepositoryMapper.class);
    }

    @Override
    public String tableName() {
        return ActiveModelStringUtils.toTableName(ImportantInformationAccount.class);
    }

    public ImportantInformationAccount findByAccount(ImportantInformation i, Account a) {
        return execute((mapper) -> {
            return mapper.findByAccount(i, a);
        });
    }

    public int countByImportantInformation(ImportantInformation i) {
        return execute((mapper) -> {
            return mapper.countByImportantInformation(i);
        });
    }

}

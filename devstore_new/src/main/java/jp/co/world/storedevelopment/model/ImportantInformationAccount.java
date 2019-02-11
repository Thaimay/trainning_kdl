package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.ImportantInformationAccountModelMapper;

public class ImportantInformationAccount extends ActiveModel<ImportantInformationAccount> {
    private Long importantInformationId;
    private Long accountId;
    private Boolean nice = false;
    private Boolean opened = false;

    public ImportantInformationAccount() {
    }

    public ImportantInformationAccount(ImportantInformation importantInformation, Account account) {
        setImportantInformationId(importantInformation.getId());
        accountId = account.getId();
    }

    public void close() {
        opened = true;
        update();
    }

    public void switchNice() {
        if (this.nice == true) {
            nice = false;
        } else {
            nice = true;
        }
        update();

    }

    @Override
    protected ModelMapper<ImportantInformationAccount> modelMapper(SqlSession session) {
        return session.getMapper(ImportantInformationAccountModelMapper.class);
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Boolean getOpened() {
        return opened;
    }

    public void setOpened(Boolean opened) {
        this.opened = opened;
    }

    public Boolean getNice() {
        return nice;
    }

    public void setNice(Boolean nice) {
        this.nice = nice;
    }

    public Long getImportantInformationId() {
        return importantInformationId;
    }

    public void setImportantInformationId(Long importantInformationId) {
        this.importantInformationId = importantInformationId;
    }

}

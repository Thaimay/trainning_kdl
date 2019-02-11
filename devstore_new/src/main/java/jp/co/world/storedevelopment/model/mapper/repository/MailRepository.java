package jp.co.world.storedevelopment.model.mapper.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.Mail;
import jp.co.world.storedevelopment.model.mapper.MailRepositoryMapper;

public class MailRepository extends Repository<Mail, MailRepositoryMapper> {

    @Override
    protected MailRepositoryMapper repositoryMapper(SqlSession session) {
        return session.getMapper(MailRepositoryMapper.class);
    }

    @Override
    public String tableName() {
        return ActiveModelStringUtils.toTableName(Mail.class);
    }

    public List<Mail> findByScheduledDatetime(LocalDateTime cuurentDateTime) {
        return execute((mapper) -> {
            return mapper.findByScheduledDatetime(cuurentDateTime);
        });
    }

	public List<Mail> getMailList() {
        return execute((mapper) -> {
            return mapper.getMailList();
        });
	}

}

package jp.co.world.storedevelopment.model.mapper.repository;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.NotifiedPushContents;
import jp.co.world.storedevelopment.model.mapper.NotifiedPushContentsRepositoryMapper;

public class NotifiedPushContentsRepository extends Repository<NotifiedPushContents, NotifiedPushContentsRepositoryMapper> {

    @Override
    protected NotifiedPushContentsRepositoryMapper repositoryMapper(SqlSession session) {
        return session.getMapper(NotifiedPushContentsRepositoryMapper.class);
    }

    @Override
    public String tableName() {
        return ActiveModelStringUtils.toTableName(NotifiedPushContents.class);
    }

    public NotifiedPushContents findByDocumentId(Long userId, Long documentId) {
        return execute((mapper) -> {
            return mapper.findByDocumentId(userId, documentId);
             });
    }

}

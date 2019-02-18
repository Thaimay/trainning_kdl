package jp.co.world.storedevelopment.model.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.NotifiedPushContents;

public interface NotifiedPushContentsRepositoryMapper extends RepositoryMapper<NotifiedPushContents> {

    @Select("SELECT * FROM notified_push_contents WHERE user_id = '${userId}' AND document_id = '${documentId}'")
    NotifiedPushContents findByDocumentId(@Param("userId") Long userId, @Param("documentId") Long documentId);

}

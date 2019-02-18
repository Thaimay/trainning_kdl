package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.RelatedTask;

public interface RelatedTaskRepositoryMapper extends RepositoryMapper<RelatedTask> {

	@Select("select * from RELATED_TASK where negotiation_comment_id = ${commentId} order by update_datetime DESC")
	public List<RelatedTask> findByNegotiationCommentId(@Param("commentId") Long commentId);

	@Select("select * from RELATED_TASK where is_deleted = false and update_datetime >= (select now() - interval '1 month') order by update_datetime DESC")
	public List<RelatedTask> findRelatedTaskList();
}

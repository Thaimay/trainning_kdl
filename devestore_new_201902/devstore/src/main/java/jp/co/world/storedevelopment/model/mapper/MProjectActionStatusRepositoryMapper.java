package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.MProjectActionStatus;

public interface MProjectActionStatusRepositoryMapper extends RepositoryMapper<MProjectActionStatus> {

	@Select("select * from m_project_action_status" +
			" where is_deleted = false" +
			"  and project_category_id = ${projectCategoryId}" +
			"  and sales_channel_id = ${salesChannelId}" +
			" order by sort")
	List<MProjectActionStatus> getMProjectActionStatusList(@Param("projectCategoryId") Long projectCategoryId, @Param("salesChannelId") Long salesChannelId);

	@Select("select * from m_project_action_status" +
			" where is_deleted = false" +
			"  and project_category_id = ${projectCategoryId}" +
			"  and sales_channel_id = ${salesChannelId}" +
			" order by sort DESC")
	List<MProjectActionStatus> findSortDescBy(@Param("projectCategoryId") Long projectCategoryId, @Param("salesChannelId") Long salesChannelId);
	
	@Select("select * from m_project_action_status" +
			" where is_deleted = false" +
			"  and project_category_id = ${projectCategoryId}" +
			"  and sales_channel_id = ${salesChannelId}" +
			"  and schedule_use = true" +
			" order by sort")
	List<MProjectActionStatus> getScheduleUseList(@Param("projectCategoryId") Long projectCategoryId, @Param("salesChannelId") Long salesChannelId);

	@Select("select * from m_project_action_status" +
			" where is_deleted = false" +
			"  and project_category_id = ${projectCategoryId}" +
			"  and sales_channel_id = ${salesChannelId}" +
			" order by sort")
	List<MProjectActionStatus> findBy(@Param("projectCategoryId") Long projectCategoryId, @Param("salesChannelId") Long salesChannelId);

	@Select("select * from m_project_action_status" +
			" where is_deleted = false" +
			"  and project_category_id = ${projectCategoryId}" +
			"  and sales_channel_id = ${salesChannelId}" +
			" order by sort limit 1")
	MProjectActionStatus getInitStatus(@Param("projectCategoryId") Long projectCategoryId, @Param("salesChannelId") Long salesChannelId);

}

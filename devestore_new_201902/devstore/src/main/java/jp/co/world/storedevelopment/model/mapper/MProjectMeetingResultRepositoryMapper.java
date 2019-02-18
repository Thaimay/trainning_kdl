package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.MProjectMeetingResult;

public interface MProjectMeetingResultRepositoryMapper extends RepositoryMapper<MProjectMeetingResult> {

	@Select("select * from m_project_meeting_result where is_deleted = false order by priority")
	public List<MProjectMeetingResult> getMProjectMeetingResultList();

}

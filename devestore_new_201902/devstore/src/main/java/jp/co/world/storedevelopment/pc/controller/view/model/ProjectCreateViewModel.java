package jp.co.world.storedevelopment.pc.controller.view.model;

import java.util.List;

import jp.co.world.storedevelopment.model.ISalesChannel;
import jp.co.world.storedevelopment.model.MProjectMeetingResult;
import jp.co.world.storedevelopment.model.ProjectCategory;
import jp.co.world.storedevelopment.model.mapper.repository.ISalesChannelRepository;
import jp.co.world.storedevelopment.model.mapper.repository.MProjectMeetingResultRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectCategoryRepository;
import jp.co.world.storedevelopment.sp.controller.dto.ProjectCreateDTO;

public class ProjectCreateViewModel extends ProjectCreateDTO {

	public ProjectCreateViewModel() {
		super();
	}
	public List<ProjectCategory> getProjectCategory() {
		List<ProjectCategory> listDto = new ProjectCategoryRepository().getAllBySort();
		return listDto;
	}
	public List<ISalesChannel> findISaleschannel() {
		List<ISalesChannel> listDto = new ISalesChannelRepository().findAll();
		return listDto;
	}
	public List<MProjectMeetingResult> findMProjectMeetingResult() {
		List<MProjectMeetingResult> listDto = new MProjectMeetingResultRepository().getMProjectMeetingResultList();
		return listDto;
	}

}

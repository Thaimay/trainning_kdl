package jp.co.world.storedevelopment.dev;

import org.junit.Test;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Project;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectRepository;
import jp.co.world.storedevelopment.sp.controller.dto.ProjectDetailDTO;
import jp.co.world.storedevelopment.utils.JsonParseUtils;

public class ProjectSwitingControlTest {

	@Test
	public void hello() {
		System.out.println("public void hello() test: テスト");
	}

	@Test
	public void testProjectSwitingItemControlMergeList() throws Exception {
		long id = 1;
		String preConsoleText = "public void testProjectSwitingItemControlMergeList test: ";
		Account account = new AccountRepository().findById(id).get();
		Project project = new ProjectRepository().getHead().orElseThrow(() -> {
			throw new IllegalArgumentException("存在しないIDです");
		});
		ProjectDetailDTO projectDetailDto = new ProjectDetailDTO(project, account);
		System.out.println(preConsoleText + JsonParseUtils.parse(projectDetailDto.getProjectSwitingItemControl()));

	}
}


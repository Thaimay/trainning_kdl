package jp.co.world.storedevelopment.model;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import jp.co.world.storedevelopment.ModelTest;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.MProjectActionStatusRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectScheduleRepository;

public class ProjectScheduleTest extends ModelTest {
	Account account;
	Project project;
	MProjectActionStatus mProjectActionStatus;

	@Override
	@Before
	public void before() {
		super.before();
		account = new AccountRepository().getHead().get();
		project = new ProjectRepository().getHead().get();
		mProjectActionStatus = new MProjectActionStatusRepository().getHead().get();
	}

	public ProjectSchedule createProjectSchedule() {
		ProjectSchedule projectSchedule = new ProjectSchedule();
		projectSchedule.setProjectId(project.getId());
		projectSchedule.setMProjectActionStatusId(mProjectActionStatus.getId());
		projectSchedule.setScheduleDate(LocalDate.now());
		projectSchedule.setCreateAccount(account);
		return projectSchedule.create();
	}

	@Test
	public void create() {
		ProjectSchedule projectSchedule = createProjectSchedule();

		assertNotEquals(Long.valueOf(0), projectSchedule.getId());

		Optional<ProjectSchedule> projectScheduleOption = new ProjectScheduleRepository().findById(projectSchedule.getId());

		if (projectScheduleOption.isPresent()) {
			assertNotEquals(Long.valueOf(0), projectScheduleOption.get().getId());
		} else {
			fail();
		}
	}

	@Test
	public void read() {
		createProjectSchedule();
		Optional<ProjectSchedule> projectScheduleOption = new ProjectScheduleRepository().getHead();

		if (projectScheduleOption.isPresent()) {
			assertNotEquals(Long.valueOf(0), projectScheduleOption.get().getId());
		} else {
			fail();
		}
	}
}

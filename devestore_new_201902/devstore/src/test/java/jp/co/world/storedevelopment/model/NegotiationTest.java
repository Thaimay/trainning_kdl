package jp.co.world.storedevelopment.model;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.Test;

import jp.co.world.storedevelopment.Application;
import jp.co.world.storedevelopment.ModelTest;
import jp.co.world.storedevelopment.dev.GlobalVariable;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ICorporationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationInterviewAccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationRepository;
import jp.co.world.storedevelopment.model.service.NegotiationService;
import jp.co.world.storedevelopment.pc.controller.form.NegotiationFindForm;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationCreateFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationDetailDTO;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationFindFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationRelatedFindFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationUpdateFormViewDTO;

public class NegotiationTest extends ModelTest {

	private Account account;
	private static String mockDate = "2018-03-12";

	@Override
	@Before
	public void before() {
		super.before();
		account = MAIN_ACCOUNT;
	}

	@Test
	public void create() {
		Negotiation negotiation = new Negotiation("test title");
		negotiation.create();

		assertNotEquals(Long.valueOf(0), negotiation.getId());

		Optional<Negotiation> negotiationOption = new NegotiationRepository().findById(negotiation.getId());

		if (negotiationOption.isPresent()) {
			Negotiation createdNegotiation = negotiationOption.get();
			assertNotEquals(Long.valueOf(0), negotiation.getId());
			assertEquals(createdNegotiation.getTitle(), negotiation.getTitle());
		} else {
			fail();
		}
	}

	@Test
	public void findBy() {
		Negotiation negotiation = new Negotiation("test title");
		negotiation.create();
		assertNotEquals(Long.valueOf(0), negotiation.getId());

		Optional<Negotiation> option = new NegotiationRepository().findById(negotiation.getId());
		assertTrue(option.isPresent());
	}

	@Test
	public void findByDetail() {
		Negotiation negotiation = new Negotiation("test title");

		new NegotiationService().createAll(negotiation, new NegotiationCreateFormDTO(), account);

		assertNotEquals(Long.valueOf(0), negotiation.getId());

		NegotiationFindFormDTO dto = new NegotiationFindFormDTO();
		dto.setIsScheduled(false);
		dto.setIsOwn(false);
		dto.setIsReadLater(false);
		dto.setAccount(account);

		new NegotiationRepository().find(dto);
	}

	@Test
	public void findForIsScheduled() {
		int pageSize = Application.PAGING_SIZE;
		int pageNumber = (int) Math.floor(GlobalVariable.sumNegotiationScheduleSize() / pageSize);
		NegotiationFindFormDTO dtoIsSheduled = new NegotiationFindFormDTO();
		dtoIsSheduled.setIsScheduled(true);
		dtoIsSheduled.setAccount(account);
		dtoIsSheduled.setNumberOfPage(pageNumber);
		assertEquals(4, new NegotiationRepository().find(dtoIsSheduled).size());

		NegotiationFindFormDTO dtoIsAll = new NegotiationFindFormDTO();
		dtoIsAll.setIsScheduled(false);
		dtoIsAll.setAccount(account);
		int allPageNumber = (int) Math.floor(GlobalVariable.negotiationSize / pageSize);
		int allResultSize = GlobalVariable.negotiationSize % pageSize;
		dtoIsAll.setNumberOfPage(allPageNumber);
		assertEquals(allResultSize, new NegotiationRepository().find(dtoIsAll).size());
	}

	@Test
	public void findForIsOwn() {
		before();
		NegotiationFindFormDTO dto = new NegotiationFindFormDTO();
		dto.setIsOwn(true);
		dto.setAccount(account);
		List<Negotiation> list = new NegotiationRepository().find(dto);
		assertEquals(GlobalVariable.negotiationSckeduleSize, list.size());
	}

	@Test
	public void findForIsLater() {
		NegotiationFindFormDTO dto = new NegotiationFindFormDTO();
		dto.setIsReadLater(true);
		dto.setAccount(account);
		List<Negotiation> list = new NegotiationRepository().find(dto);
		assertEquals(GlobalVariable.negotiationImplementationSize, list.size());
	}

	@Test
	public void readLaterByAccount() {
		Negotiation negotiation = new Negotiation("test title");

		new NegotiationService().createAll(negotiation, new NegotiationCreateFormDTO(), account);

		assertFalse(negotiation.getIsLater(account));
		negotiation.switchReadLater(account);
		assertTrue(negotiation.getIsLater(account));
	}

	@Test
	public void openByAccount() {
		Negotiation negotiation = new Negotiation("test title");

		new NegotiationService().createAll(negotiation, new NegotiationCreateFormDTO(), account);

		assertFalse(negotiation.getIsOpend(account));
		negotiation.open(account);
		assertTrue(negotiation.getIsOpend(account));
	}

	@Test
	public void getOpenRate() {
		Negotiation negotiation = new Negotiation("test title");

		new NegotiationService().createAll(negotiation, new NegotiationCreateFormDTO(), account);

		assertFalse(negotiation.getIsOpend(account));

		assertEquals("0%", negotiation.openRates());

		negotiation.addNoticeAccount(account);

		assertEquals("0%", negotiation.openRates());

		negotiation.open(account);
		assertTrue(negotiation.getIsOpend(account));

		assertEquals("100%", negotiation.openRates());
	}

	@Test
	public void createDetailDTO() {
		Negotiation negotiation = new NegotiationRepository().getHead().get();
		NegotiationDetailDTO dto = new NegotiationDetailDTO(negotiation, account);
		assertNotNull(dto);
	}

	@Test
	public void createUpdateDTO() {
		Negotiation negotiation = new NegotiationRepository().getHead().get();
		NegotiationUpdateFormViewDTO dto = new NegotiationUpdateFormViewDTO(negotiation);
		assertNotNull(dto);
	}

	@Test
	public void findByCorporationResultTwo() {
		NegotiationFindFormDTO dto = new NegotiationFindFormDTO();
		dto.setCorporationIds(Arrays.asList(4L, 1L));
		dto.setAccount(account);
		List<Negotiation> list = new NegotiationRepository().find(dto);
		assertEquals(2, list.size());
	}

	@Test
	public void findByCorporationResultOne() {
		Negotiation negotiation = new NegotiationRepository().getHead().get();
		ICorporation corporation = new ICorporationRepository().findById(4L).get();
		NegotiationFindFormDTO dto = new NegotiationFindFormDTO();
		dto.setCorporationIds(Arrays.asList(4L));
		dto.setAccount(account);

		new NegotiationInterviewCorporation(negotiation, corporation).create();

		List<Negotiation> list = new NegotiationRepository().find(dto);
		assertEquals(1, list.size());
	}

	@Test
	public void findForInterviewSingleStaff() {
		NegotiationFindFormDTO dto = new NegotiationFindFormDTO();
		dto.setInterviewIds(Arrays.asList(1L));
		dto.setAccount(account);

		List<Negotiation> list = new NegotiationRepository().find(dto);
		assertEquals(2, list.size());
	}

	@Test
	public void findForInterviewMultiStaff() {
		NegotiationFindFormDTO dto = new NegotiationFindFormDTO();
		dto.setInterviewIds(Arrays.asList(1L, 4L));
		dto.setAccount(account);

		NegotiationInterviewAccount ni = new NegotiationInterviewAccountRepository().findById(2L).get();
		ni.setAccountId(4L);
		ni.update();

		List<Negotiation> list = new NegotiationRepository().find(dto);
		assertEquals(2, list.size());
	}

	@Test
	public void findForimplementationDateBoth() {
		NegotiationFindFormDTO dto = new NegotiationFindFormDTO();
		Month month = Month.of(12);
		LocalDate startDate = LocalDate.of(2015, month, 15);
		LocalDate endDate = LocalDate.of(2015, month, 17);

		dto.setImplementationStartDate(startDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		dto.setImplementationEndDate(endDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		dto.setAccount(account);

		List<Negotiation> list = new NegotiationRepository().find(dto);
		assertEquals(1, list.size());
	}

//	@Test
//	public void findForScheduleDateBoth() {
//		NegotiationFindFormDTO dto = new NegotiationFindFormDTO();
//		Month month = Month.of(12);
//		LocalDate startDate = LocalDate.of(2015, month, 15);
//		LocalDate endDate = LocalDate.of(2015, month, 16);
//
//		dto.setScheduleStartDate(startDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//		dto.setScheduleEndDate(endDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//		dto.setAccount(account);
//
//		List<Negotiation> list = new NegotiationRepository().find(dto);
//		assertEquals(1, list.size());
//	}

	@Test
	public void findForDivisionNegotiation() {
		NegotiationFindFormDTO dto = new NegotiationFindFormDTO();
		dto.setDivision(Arrays.asList("NEGOTIATION"));
		dto.setAccount(account);

		List<Negotiation> list = new NegotiationRepository().find(dto);
		assertEquals(1, list.size());
	}

	@Test
	public void findForDivisionVisitor() {
		NegotiationFindFormDTO dto = new NegotiationFindFormDTO();
		dto.setDivision(Arrays.asList("VISITOR"));
		dto.setAccount(account);

		List<Negotiation> list = new NegotiationRepository().find(dto);
		assertEquals(1, list.size());
	}

	@Test
	public void findForDivisionOther() {
		NegotiationFindFormDTO dto = new NegotiationFindFormDTO();
		dto.setDivision(Arrays.asList("OTHER"));
		dto.setAccount(account);

		List<Negotiation> list = new NegotiationRepository().find(dto);
		assertEquals(1, list.size());
	}

	@Test
	public void findFullText() {
		NegotiationFindFormDTO dto = new NegotiationFindFormDTO();
		dto.setFillText("リテールマネジメント");
		dto.setAccount(account);

		List<Negotiation> list = new NegotiationRepository().find(dto);
		assertEquals(1, list.size());
	}

	@Test
	public void findRelatedNegotiation() {
		Negotiation negotiation = new NegotiationRepository().getHead().get();
		NegotiationRelatedFindFormDTO dto = new NegotiationRelatedFindFormDTO(negotiation);
		Account account = new AccountRepository().getHead().get();

		IntStream.range(0, 5).forEach(i -> {
			NegotiationInterviewAccount n = new NegotiationInterviewAccount(negotiation, account);
			n.create();
		});
		dto.setAccount(account);
		List<Negotiation> list = new NegotiationRepository().findRelatedNegotiation(dto);
		assertEquals(5, list.size());
	}

	@Test
	public void findRelatedNegotiationByBuilding() {
		Negotiation negotiation = new NegotiationRepository().getHead().get();
		NegotiationRelatedFindFormDTO dto = new NegotiationRelatedFindFormDTO(negotiation);
		Account account = new AccountRepository().getHead().get();
		dto.setAccount(account);
		Building buildingMock = new BuildingRepository().getHead().get();

		IntStream.range(0, 5).forEach(i -> {
			NegotiationInterviewBuilding negotiationIB = new NegotiationInterviewBuilding(negotiation, buildingMock);
			negotiationIB.create();
		});
		NegotiationInterviewBuilding negotiationIB = new NegotiationInterviewBuilding(negotiation, buildingMock);
		negotiationIB.create();

		List<Long> buildingIds = negotiation.getInterviewBuildings().stream().map(b -> b.getBuildingId())
				.collect(Collectors.toList());

		dto.setBuildingIds(buildingIds);

		List<Negotiation> list = new NegotiationRepository().findRelatedNegotiation(dto);
		assertEquals(5, list.size());
	}

	@Test
	public void findForPCByTitle() {
		int pageSize = Application.PAGING_SIZE;
		int lastPage = (int) Math.ceil((GlobalVariable.sumNegotiationOfTitleOnlySize()) / pageSize);
		int expect = GlobalVariable.sumNegotiationOfTitleOnlySize() - (lastPage * pageSize);

		NegotiationFindForm form = new NegotiationFindForm();
		form.setTitle("アピタ");
		form.setAccount(account);
		form.setNumberOfPage(lastPage);

		List<Negotiation> list = new NegotiationRepository().findForPC(form);
		assertEquals(expect, list.size());
	}

	@Test
	public void findForPCByTitleAndDivision() {
		int pageSize = Application.PAGING_SIZE;
		int lastPage = (int) Math.ceil((GlobalVariable.negotiationSckeduleSize) / pageSize);
		int expect = GlobalVariable.negotiationSckeduleSize - (lastPage * pageSize);

		NegotiationFindForm form = new NegotiationFindForm();
		form.setTitle("アピタ");
		form.setAccount(account);
		form.setDivision(Arrays.asList("negotiation"));
		form.setNumberOfPage(lastPage);

		List<Negotiation> list = new NegotiationRepository().findForPC(form);
		assertEquals(expect, list.size());
	}

//	@Test
//	public void findByTitleAndScheduleStartDate() {
//		int pageSize = Application.PAGING_SIZE;
//		int lastPage = (int) Math.ceil((GlobalVariable.negotiationSckeduleSize) / pageSize);
//		int expect = GlobalVariable.negotiationSckeduleSize - (lastPage * pageSize);
//
//		NegotiationFindForm form = new NegotiationFindForm();
//		form.setTitle("アピタ予定");
//		form.setAccount(account);
//		form.setScheduleStartDate(mockDate);
//		form.setNumberOfPage(lastPage);
//
//		List<Negotiation> list = new NegotiationRepository().findForPC(form);
//		assertEquals(expect, list.size());
//	}

//	@Test
//	public void findByTitleAndScheduleEndDate() {
//		int pageSize = Application.PAGING_SIZE;
//		int lastPage = (int) Math.ceil((GlobalVariable.negotiationSckeduleSize) / pageSize);
//		int expect = GlobalVariable.negotiationSckeduleSize - (lastPage * pageSize);
//
//		NegotiationFindForm form = new NegotiationFindForm();
//		form.setTitle("アピタ予定");
//		form.setAccount(account);
//		form.setScheduleEndDate(mockDate);
//		form.setNumberOfPage(lastPage);
//
//		List<Negotiation> list = new NegotiationRepository().findForPC(form);
//		assertEquals(expect, list.size());
//	}

//	@Test
//	public void findByTitleAndScheduleStartTime() {
//		NegotiationFindForm form = new NegotiationFindForm();
//		form.setTitle("予定日・実施日start");
//		form.setAccount(account);
//		form.setScheduleStartTime("23:30");
//
//		List<Negotiation> list = new NegotiationRepository().findForPC(form);
//		assertEquals(GlobalVariable.negotiationlementationDateSize + GlobalVariable.negotiationlementationDateSize,
//				list.size());
//	}

//	@Test
//	public void findByTitleAndScheduleEndTime() {
//		int pageSize = Application.PAGING_SIZE;
//		int lastPage = (int) Math.ceil(GlobalVariable.negotiationSckeduleSize / pageSize);
//		int expect = GlobalVariable.negotiationSckeduleSize - (lastPage * pageSize);
//
//		NegotiationFindForm form = new NegotiationFindForm();
//		form.setTitle("アピタ予定");
//		form.setAccount(account);
//		form.setScheduleEndTime("11:35");
//		form.setNumberOfPage(lastPage);
//
//		List<Negotiation> list = new NegotiationRepository().findForPC(form);
//		assertEquals(expect, list.size());
//	}

	@Test
	public void findByTitleAndImplementationStartDate() {
		int pageSize = Application.PAGING_SIZE;
		int lastPage = (int) Math.ceil((GlobalVariable.negotiationImplementationSize) / pageSize);
		int expect = GlobalVariable.negotiationImplementationSize - (lastPage * pageSize);

		NegotiationFindForm form = new NegotiationFindForm();
		form.setTitle("アピタ実施");
		form.setAccount(account);
		form.setImplementationStartDate(mockDate);
		form.setNumberOfPage(lastPage);

		List<Negotiation> list = new NegotiationRepository().findForPC(form);
		assertEquals(expect, list.size());
	}

	@Test
	public void findByTitleAndImplementationEndDate() {
		int pageSize = Application.PAGING_SIZE;
		int lastPage = (int) Math.ceil((GlobalVariable.negotiationImplementationSize) / pageSize);
		int expect = GlobalVariable.negotiationImplementationSize - (lastPage * pageSize);

		NegotiationFindForm form = new NegotiationFindForm();
		form.setTitle("アピタ実施");
		form.setAccount(account);
		form.setImplementationEndDate(mockDate);
		form.setNumberOfPage(lastPage);

		List<Negotiation> list = new NegotiationRepository().findForPC(form);
		assertEquals(expect, list.size());
	}

//	@Test
//	public void findByTitleAndImplementationStartTime() {
//		int pageSize = Application.PAGING_SIZE;
//		int lastPage = (int) Math
//				.ceil((GlobalVariable.negotiationlementationDateSize + GlobalVariable.negotiationlementationDateSize)
//						/ pageSize);
//		int expect = (GlobalVariable.negotiationlementationDateSize + GlobalVariable.negotiationlementationDateSize)
//				- (lastPage * pageSize);
//
//		NegotiationFindForm form = new NegotiationFindForm();
//		form.setTitle("予定日・実施日start");
//		form.setAccount(account);
//		form.setImplementationStartTime("23:30");
//		form.setNumberOfPage(lastPage);
//
//		List<Negotiation> list = new NegotiationRepository().findForPC(form);
//		assertEquals(expect, list.size());
//	}

//	@Test
//	public void findByTitleAndImplementationEndTime() {
//		int pageSize = Application.PAGING_SIZE;
//		int lastPage = (int) Math.ceil(GlobalVariable.negotiationlementationDateSize / pageSize);
//		int expect = GlobalVariable.negotiationlementationDateSize - (lastPage * pageSize);
//
//		NegotiationFindForm form = new NegotiationFindForm();
//		form.setTitle("予定日・実施日startのみ");
//		form.setAccount(account);
//		form.setImplementationEndTime("23:30");
//		form.setNumberOfPage(lastPage);
//
//		List<Negotiation> list = new NegotiationRepository().findForPC(form);
//		assertEquals(expect, list.size());
//	}

	@Test
	public void findForPCByTitleAndIsReadLater() {
		NegotiationFindForm form = new NegotiationFindForm();
		form.setTitle("アピタ");
		form.setAccount(account);
		form.setIsReadLater(true);

		List<Negotiation> list = new NegotiationRepository().findForPC(form);
		assertEquals(GlobalVariable.negotiationImplementationSize, list.size());
	}

	@Test
	public void findForPCByTitleAndInterViewCorporation() {
		NegotiationFindForm form = new NegotiationFindForm();
		form.setTitle("法人");
		form.setAccount(account);
		form.setCorporationIds(Arrays.asList(1L));

		List<Negotiation> list = new NegotiationRepository().findForPC(form);
		assertEquals(GlobalVariable.negotiationCorporationOnlySize + GlobalVariable.negotiationCorporationOnlySize,
				list.size());
	}

	@Test
	public void findForPCByTitleAndInterViewAccount() {
		NegotiationFindForm form = new NegotiationFindForm();
		form.setTitle("アピタ");
		form.setAccount(account);
		form.setAccountIds(Arrays.asList(1L));

		List<Negotiation> list = new NegotiationRepository().findForPC(form);
		assertEquals(GlobalVariable.negotiationSckeduleSize, list.size());
	}

	@Test
	public void findForPCByTitleAndInterViewBusinessCard() {
		NegotiationFindForm form = new NegotiationFindForm();
		form.setTitle("面談者");
		form.setAccount(account);
		form.setInterviewIds(Arrays.asList(1L));

		List<Negotiation> list = new NegotiationRepository().findForPC(form);
		assertEquals(GlobalVariable.sumNegotiationInterviewBusinessCardSize(), list.size());
	}

//	@Test
//	public void findForPCByTitleAndContent() {
//		int pageSize = Application.PAGING_SIZE;
//		int lastPage = (int) Math.ceil((GlobalVariable.negotiationImplementationSize) / pageSize);
//		int expect = GlobalVariable.negotiationImplementationSize - (lastPage * pageSize);
//
//		NegotiationFindForm form = new NegotiationFindForm();
//		form.setTitle("アピタ");
//		form.setAccount(account);
//		form.setContent("【商談実績】");
//		form.setNumberOfPage(lastPage);
//
//		List<Negotiation> list = new NegotiationRepository().findForPC(form);
//		assertEquals(expect, list.size());
//	}

//	@Test
//	public void findForPCByTitleAndNextActionContent() {
//		int pageSize = Application.PAGING_SIZE;
//		int lastPage = (int) Math.ceil((GlobalVariable.negotiationSckeduleSize) / pageSize);
//		int expect = GlobalVariable.negotiationSckeduleSize - (lastPage * pageSize);
//
//		NegotiationFindForm form = new NegotiationFindForm();
//		form.setTitle("アピタ");
//		form.setAccount(account);
//		form.setNextActionContent("テストネクストアクション");
//		form.setNumberOfPage(lastPage);
//
//		List<Negotiation> list = new NegotiationRepository().findForPC(form);
//		assertEquals(expect, list.size());
//	}

	@Test
	public void findForPCByTitleAndInterViewBuilding() {
		int pageSize = Application.PAGING_SIZE;
		int lastPage = (int) Math.ceil((GlobalVariable.sumNegotiationInterviewBuildingSize()) / pageSize);
		int expect = GlobalVariable.sumNegotiationInterviewBuildingSize() - (lastPage * pageSize);

		NegotiationFindForm form = new NegotiationFindForm();
		form.setTitle("アピタ");
		form.setAccount(account);
		form.setBuildingIds(Arrays.asList(1L));
		form.setNumberOfPage(lastPage);

		List<Negotiation> list = new NegotiationRepository().findForPC(form);
		assertEquals(expect, list.size());
	}

}

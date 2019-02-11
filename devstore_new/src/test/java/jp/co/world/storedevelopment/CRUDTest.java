package jp.co.world.storedevelopment;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;

import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ActivationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BrandRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingDocumentRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingFileRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingImageRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingKeymanRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingMeetingHistoryRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingPersonalDevelopRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingSalesRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingTenantRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingTradeAreaRepository;
import jp.co.world.storedevelopment.model.mapper.repository.DepartmentRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IAreaRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IBusinessCardRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ICompetitionSalesRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IContractTypeRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ICorporationGroupRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ICorporationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IIncomeUnitRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IPlaceRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IPrefecturesRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IRentContractRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ISalesAgencyTargetRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ISalesChannelRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IShopCompanyRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IShopRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IShopSalesLatestOneYearRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ImportantInformationAccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ImportantInformationBuildingRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ImportantInformationCorporationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ImportantInformationNegotiationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ImportantInformationNiceAccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ImportantInformationReadAccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ImportantInformationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.KeymanRepository;
import jp.co.world.storedevelopment.model.mapper.repository.MBuildingSalesClassificationsRepository;
import jp.co.world.storedevelopment.model.mapper.repository.MBuildingSalesTypesRepository;
import jp.co.world.storedevelopment.model.mapper.repository.MCreditRankRepository;
import jp.co.world.storedevelopment.model.mapper.repository.MProjectActionStatusRepository;
import jp.co.world.storedevelopment.model.mapper.repository.MProjectMeetingResultRepository;
import jp.co.world.storedevelopment.model.mapper.repository.MProjectProgressStatusRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationCommentRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationImageRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationInterviewAccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationInterviewBrandRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationInterviewBuildingRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationInterviewBusinessCardRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationInterviewCorporationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationNoticeAccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationOpenAccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationReadLaterAccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ParticipatingStoreCorporationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.PmCorporationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectCategoryRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectFileRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectImageRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectNegotiationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectPersonalDevelopRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectReadLaterAccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectScheduleRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectSwitingItemControlRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectTaskRepository;
import jp.co.world.storedevelopment.model.mapper.repository.RelatedTaskRepository;
import jp.co.world.storedevelopment.model.mapper.repository.RoleRepository;
import jp.co.world.storedevelopment.model.mapper.repository.SalesFileRepository;
import jp.co.world.storedevelopment.model.mapper.repository.SalesTrendRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ShopImageRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ShopRepository;
import jp.co.world.storedevelopment.model.mapper.repository.SubAccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.TenantRepository;
import jp.co.world.storedevelopment.model.mapper.repository.TodoRepository;
import jp.co.world.storedevelopment.model.mapper.repository.WorldAuthAccountRepository;

public abstract class CRUDTest {

	@BeforeAll
	public void beforeAll() {
		System.setProperty("mybatis", "");
		System.setProperty("mode", "development");
	}

	private void deleteCommon() {

		new TodoRepository().deleteAllWithResetSerial();

		new ImportantInformationBuildingRepository().deleteAllWithResetSerial();

		new ImportantInformationNegotiationRepository().deleteAllWithResetSerial();

		new ImportantInformationCorporationRepository().deleteAllWithResetSerial();

		new ImportantInformationAccountRepository().deleteAllWithResetSerial();

		new RelatedTaskRepository().deleteAllWithResetSerial();

		new ImportantInformationRepository().deleteAllWithResetSerial();

		new ImportantInformationReadAccountRepository().deleteAllWithResetSerial();

		new ImportantInformationNiceAccountRepository().deleteAllWithResetSerial();
	}

	private void deleteNegotiation() {
		new NegotiationInterviewAccountRepository().deleteAllWithResetSerial();

		new NegotiationCommentRepository().deleteAllWithResetSerial();

		new NegotiationInterviewCorporationRepository().deleteAllWithResetSerial();

		new NegotiationInterviewBusinessCardRepository().deleteAllWithResetSerial();

		new NegotiationReadLaterAccountRepository().deleteAllWithResetSerial();

		new NegotiationOpenAccountRepository().deleteAllWithResetSerial();

		new NegotiationNoticeAccountRepository().deleteAllWithResetSerial();

		new NegotiationImageRepository().deleteAllWithResetSerial();

		new NegotiationInterviewBuildingRepository().deleteAllWithResetSerial();

		new NegotiationInterviewBrandRepository().deleteAllWithResetSerial();

		new NegotiationRepository().deleteAllWithResetSerial();

	}

	@Before
	public void before() {

		deleteCommon();

		deleteProject();
		deleteNegotiation();
		deleteBuilding();
		deleteAccount();
		deleteImport();
	}

	private void deleteAccount() {

		new WorldAuthAccountRepository().deleteAllWithResetSerial();

		new DepartmentRepository().deleteAllWithResetSerial();

		new SubAccountRepository().deleteAllWithResetSerial();

		new RoleRepository().deleteAllWithResetSerial();

		new AccountRepository().deleteAllWithResetSerial();

	}

	private void deleteImport() {

		new IShopCompanyRepository().deleteAllWithResetSerial();

		new MCreditRankRepository().deleteAllWithResetSerial();

		new ICompetitionSalesRepository().deleteAllWithResetSerial();

		new IBusinessCardRepository().deleteAllWithResetSerial();

		new IShopRepository().deleteAllWithResetSerial();

		new IPrefecturesRepository().deleteAllWithResetSerial();

		new ICorporationRepository().deleteAllWithResetSerial();

		new ICorporationGroupRepository().deleteAllWithResetSerial();

		new ISalesChannelRepository().deleteAllWithResetSerial();

		new IPlaceRepository().deleteAllWithResetSerial();

		new IAreaRepository().deleteAllWithResetSerial();

		new ISalesAgencyTargetRepository().deleteAllWithResetSerial();

		new IIncomeUnitRepository().deleteAllWithResetSerial();

		new IShopSalesLatestOneYearRepository().deleteAllWithResetSerial();

	}

	private void deleteBuilding() {

		new BuildingPersonalDevelopRepository().deleteAllWithResetSerial();

		new BuildingMeetingHistoryRepository().deleteAllWithResetSerial();

		new BuildingTradeAreaRepository().deleteAllWithResetSerial();

		new PmCorporationRepository().deleteAllWithResetSerial();

		new ActivationRepository().deleteAllWithResetSerial();

		new BuildingTenantRepository().deleteAllWithResetSerial();

		new TenantRepository().deleteAllWithResetSerial();

		new BuildingSalesRepository().deleteAllWithResetSerial();

		new SalesTrendRepository().deleteAllWithResetSerial();

		new ShopRepository().deleteAllWithResetSerial();

		new ParticipatingStoreCorporationRepository().deleteAllWithResetSerial();

		new BuildingKeymanRepository().deleteAllWithResetSerial();

		new BuildingDocumentRepository().deleteAllWithResetSerial();

		new BuildingImageRepository().deleteAllWithResetSerial();

		new SalesFileRepository().deleteAllWithResetSerial();

		new BuildingFileRepository().deleteAllWithResetSerial();

		new BuildingImageRepository().deleteAllWithResetSerial();

		new ShopImageRepository().deleteAllWithResetSerial();

		new KeymanRepository().deleteAllWithResetSerial();

		new IContractTypeRepository().deleteAllWithResetSerial();

		new IRentContractRepository().deleteAllWithResetSerial();

		new MBuildingSalesClassificationsRepository().deleteAllWithResetSerial();

		new MBuildingSalesTypesRepository().deleteAllWithResetSerial();

		new BrandRepository().deleteAllWithResetSerial();

		new BuildingRepository().deleteAllWithResetSerial();
	}

	private void deleteProject() {

		new ProjectPersonalDevelopRepository().deleteAllWithResetSerial();

		new ProjectTaskRepository().deleteAllWithResetSerial();

		new ProjectNegotiationRepository().deleteAllWithResetSerial();

		new ProjectFileRepository().deleteAllWithResetSerial();

		new ProjectImageRepository().deleteAllWithResetSerial();

		new ProjectReadLaterAccountRepository().deleteAllWithResetSerial();

		new ProjectRepository().deleteAllWithResetSerial();

		new MProjectProgressStatusRepository().deleteAllWithResetSerial();

		new MProjectActionStatusRepository().deleteAllWithResetSerial();

		new MProjectMeetingResultRepository().deleteAllWithResetSerial();

		new ProjectSwitingItemControlRepository().deleteAllWithResetSerial();

		new ProjectCategoryRepository().deleteAllWithResetSerial();

		new ProjectScheduleRepository().deleteAllWithResetSerial();

		new ProjectScheduleRepository().deleteAllWithResetSerial();
	}

}

package jp.co.world.storedevelopment.dev;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jp.co.world.storedevelopment.model.Account;

public class GlobalVariable {

	public static final Account ACCOUNT = new Account(1L, "123456");

	public static int iBrandIncomeUnitSize = 10;

	public static int negotiationSckeduleSize = 5;

	public static int negotiationImplementationSize = 8;

	public static int negotiationImplementationOtherUserSize = 1;

	public static int negotiationDivisionSize = 2;

	public static int negotiationReleaseSize = 2;

	public static int negotiationCommentSize = 3;

	public static int negotiationCorporationOnlySize = 1;

	public static int negotiationOwnOnlySize = 1;

	public static int negotiationINterviewStaffSize = 1;

	public static int negotiationlementationDateSize = 1;

	public static int negotiationScheduleDateSize = 1;

	public static int negotiationlementationDivisionSize = 1;

	public static int negotiationlementationFullTextSize = 1;

	public static int negotiationImportantNoticeDateSize = 1;

	public static int iCorporationSize = 3;

	public static int businessCardSize = 3;

	public static int iSalesChannelSize = 3;

	public static int iBlockSize = 3;

	public static int iAreaSize = 3;

	public static int iPrefecturesSize = 3;

	public static int iSalesAgencyTargetSize = 3;

	public static int participatingStoreCorporationSize = 3;

	public static int tenantTargetSize = 3;

	public static int competitionTargetSize = 3;

	public static int mCreditRankSize = 3;

	public static int iIncomeUnitSize = 3;

	public static int keymanSize = 3;

	public static int iPlaceSize = 3;

	public static int importantNoticeSize = 1;

	public static int brandSize = 1;
	public static int projectSize = 1;
	public static int fileSize = 2;

	public static int negotiationMyAccountSieze = negotiationSckeduleSize;

	public static int negotiationSize = sumNegotiationScheduleSize() + sumNegotiationImplementationSize();

	public static String myAccountCode = "A12345";

	public static int noticeSize = 2;

	public static int todoSize = 1;

	public static int thirdPartyTodoSize = 1;

	public static int shopUpdateValue = 2;
	public static int shopUpdateKey = 1;

	public static int buildingSize = 3;
	public static int shopSize = 3;
	public static int buildingTradeAreaDetailSize = 1;
	public static int buildingPersonalDevelopSize = 5;
	public static int buildingSalesSize = 5;
	public static int buildingTenantSize = 5;
	public static int buildingActivationSize = 5;
	public static int buildingShopSize = 5;
	public static int buildingCompetitionSize = 5;
	public static int buildingFileSize = 5;
	public static int buildingImageSize = 5;
	public static int buildingMeetingHistorySize = 5;
	public static int buildingKeymanSize = 5;
	public static int buildingAdvancedSize = 5;
	public static int buildingTenantDetailSize = 1;
	public static int buildingPmCorporationSize = 2;
	public static int buildingPersonalDevelopDetailSize = 3;
	public static int buildingBuildingKeymanDetailSize = 1;
	public static int buildingActivationDetailSize = 3;
	public static int buildingImportantInformationDetailSize = 3;
	public static int buildingShopDetailSize = 7;
	public static int buildingCompetitionDetailSize = 6;
	public static int buildingSalesDetailSize = 5;
	public static int buildingIBuildingShopSalesLatestOneYearSize = 6;
	public static int buildingFileDetailSize = 5;
	public static int buildingImageDetailSize = 5;
	public static int buildingMeetingHistoryDetailSize = 5;
	public static int competitionSalesDetailSize = 5;

	public static int shopImageDetailSize = 6;
	public static DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH");
	public static DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	public static LocalDateTime localDateTime = LocalDateTime.now();
	public static LocalDate localDate = LocalDate.now();

	public static int sumNegotiationScheduleSize() {
		return negotiationSckeduleSize + negotiationCommentSize + negotiationINterviewStaffSize
				+ negotiationINterviewStaffSize + negotiationScheduleDateSize + negotiationCorporationOnlySize
				+ negotiationCorporationOnlySize + negotiationImportantNoticeDateSize + negotiationOwnOnlySize;
	}

	public static int sumNegotiationImplementationSize() {
		return negotiationImplementationSize + negotiationlementationDateSize + negotiationlementationDivisionSize
				+ negotiationlementationFullTextSize;

	}

	public static int sumTodoSize() {
		return todoSize + todoSize + todoSize;
	}

	public static int allIssueSize() {
		return todoSize + todoSize + todoSize + thirdPartyTodoSize;
	}

	public static int sumNegotiationOfTitleOnlySize() {
		return GlobalVariable.negotiationSckeduleSize + GlobalVariable.negotiationImplementationSize
				+ GlobalVariable.negotiationOwnOnlySize;
	}

	public static int sumNegotiationOfTitleAndContentOnlySize() {
		return GlobalVariable.negotiationSckeduleSize + GlobalVariable.negotiationImplementationSize
				+ GlobalVariable.negotiationOwnOnlySize;
	}

	public static int sumNegotiationInterviewBusinessCardSize() {
		return GlobalVariable.negotiationINterviewStaffSize + GlobalVariable.negotiationINterviewStaffSize;
	}

	public static int sumNegotiationInterviewBuildingSize() {
		return GlobalVariable.negotiationSckeduleSize + GlobalVariable.negotiationImplementationSize
				+ GlobalVariable.negotiationOwnOnlySize;
	}

	public static int sumNegotiationScheduleStartDateSize() {
		return GlobalVariable.negotiationSckeduleSize + GlobalVariable.negotiationImplementationSize
				+ GlobalVariable.negotiationOwnOnlySize;
	}

	public static int sumNegotiationScheduleTimeSize() {
		return GlobalVariable.negotiationlementationDateSize + GlobalVariable.negotiationlementationDateSize
				+ GlobalVariable.negotiationlementationDateSize;
	}
}

package jp.co.world.storedevelopment.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.ImportantInformationModelMapper;
import jp.co.world.storedevelopment.model.mapper.repository.ImportantInformationBuildingRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ImportantInformationCorporationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ImportantInformationNiceAccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ImportantInformationReadAccountRepository;

public class ImportantInformation extends ActiveModel<ImportantInformation> {
	private String content = "";
	private LocalDateTime showStartDatetime;
	private LocalDateTime showEndDatetime;
	private String division = "";

	private static DateTimeFormatter START_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
	private static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private static DateTimeFormatter DATE_PRINT_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

	private static String[] ignoreFields = new String[] { "updateDatetimeValue", "showStartDatetimeValue",
			"showEndDatetimeValue", "showStartDateValue", "showEndDateValue", "createdDate", "actionDate" };

	@Override
	protected String[] ignoreFields() {
		return ignoreFields;
	}

	public ImportantInformation() {
	}

	@Override
	protected ModelMapper<ImportantInformation> modelMapper(SqlSession session) {
		return session.getMapper(ImportantInformationModelMapper.class);
	}

	public Boolean isRead(Account account) {
		Optional<ImportantInformationReadAccount> i = new ImportantInformationReadAccountRepository().findBy(getId(),
				account.getId());
		return i.isPresent();
	}

	public void read(Account account) {
		Optional<ImportantInformationReadAccount> i = new ImportantInformationReadAccountRepository().findBy(getId(),
				account.getId());

		if (i.isPresent() == false) {
			new ImportantInformationReadAccount(getId(), account.getId()).create();
		}
	}

	public Boolean isNice(Account account) {
		Optional<ImportantInformationNiceAccount> i = new ImportantInformationNiceAccountRepository().findBy(getId(),
				account.getId());
		return i.isPresent();
	}

	public int niceNumber() {
		return new ImportantInformationNiceAccountRepository().countBy(getId());
	}

	public void switchNice(Account account) {
		Optional<ImportantInformationNiceAccount> i = new ImportantInformationNiceAccountRepository().findBy(getId(),
				account.getId());

		if (i.isPresent()) {
			i.get().delete();
		} else {
			new ImportantInformationNiceAccount(getId(), account.getId()).create();
		}
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getShowStartDatetime() {
		return showStartDatetime;
	}

	public void setShowStartDatetime(LocalDateTime showStartDatetime) {
		this.showStartDatetime = showStartDatetime;
	}

	public LocalDateTime getShowEndDatetime() {
		return showEndDatetime;
	}

	public void setShowEndDatetime(LocalDateTime showEndDatetime) {
		this.showEndDatetime = showEndDatetime;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getDivision() {
		return division;
	}

	public String getShowStartDateValue() {
		return DATE_FORMAT.format(getShowStartDatetime());
	}

	public String getShowEndDateValue() {
		return DATE_FORMAT.format(getShowEndDatetime());
	}

	public String getShowStartDatetimeValue() {
		return START_DATE_FORMAT.format(getShowStartDatetime());
	}

	public String getShowEndDatetimeValue() {
		return START_DATE_FORMAT.format(getShowEndDatetime());
	}

	public String getUpdateDatetimeValue() {
		return START_DATE_FORMAT.format(getUpdateDatetime());
	}

	public String getCreatedDate() {
		return DATE_PRINT_FORMAT.format(getCreatedDatetime());
	}

	public String getActionDate() {
		return DATE_FORMAT.format(getShowEndDatetime());
	}

	public void addAccessRecord(Account account) {
		new ImportantInformationAccount(this, account).create();
	}

	public void addRelatedNegotiation(Negotiation n) {
		new ImportantInformationNegotiation(this, n).create();
	}

	public List<ImportantInformationBuilding> importantInformationBuildings() {
		return new ImportantInformationBuildingRepository().findByImportantInformationId(getId());
	}

	public List<ImportantInformationCorporation> importantInformationCorporation() {
		return new ImportantInformationCorporationRepository().findByImportantInformationId(getId());
	}

}

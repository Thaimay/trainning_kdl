package jp.co.world.storedevelopment.pc.controller.view.model;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.ProjectHistory;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;
import jp.co.world.storedevelopment.sp.controller.dto.ProjectHistoryDetailDTO;
import jp.co.world.storedevelopment.sp.controller.dto.ScheduleDateDTO;

public class ProjectHistoryDetailViewModel extends ProjectHistoryDetailDTO {

	private static DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

	private List<ScheduleDateDTO> scheduleList = new ArrayList<>();

	public ProjectHistoryDetailViewModel(ProjectHistory p, Account a) {
		super(p, a);
		
		getScheduleList().forEach(s -> {
			Long statusId = s.getProjectStatusId();

			if (statusId.equals(p.getMProjectActionStatusId())) {
				setCurrentScheduleId(statusId);
			}

			if (s.getIsPastDay()) {
				setGoalScheduleId(statusId);
			}
		});
	}

	public String getCreatedDatetimeString() {
		if (getCreatedDatetime() != null) {
			return getCreatedDatetime().format(DATE_TIME_FORMAT);
		} else {
			return StringUtils.EMPTY;
		}
	}

	public String getUpdateDatetimeString() {
		if (getUpdateDatetime() != null) {
			return getUpdateDatetime().format(DATE_TIME_FORMAT);
		} else {
			return StringUtils.EMPTY;
		}
	}

	private Optional<Account> getCreatedAccount() {
		return new AccountRepository().findByCode(getCreatedAccountCode());
	}

	public String getCreatedAccountName() {
		Optional<Account> createdAccount = getCreatedAccount();
		if (createdAccount.isPresent()) {
			return createdAccount.get().getFullName();
		} else {
			return StringUtils.EMPTY;
		}
	}

	private Optional<Account> getUpdateAccount() {
		return new AccountRepository().findByCode(getUpdateAccountCode());
	}

	public String getUpdateAccountName() {
		Optional<Account> updateAccount = getUpdateAccount();
		if (updateAccount.isPresent()) {
			return updateAccount.get().getFullName();
		} else {
			return StringUtils.EMPTY;
		}
	}

}

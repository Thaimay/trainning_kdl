package jp.co.world.storedevelopment.pc.controller.view.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Negotiation;
import jp.co.world.storedevelopment.model.NegotiationInterviewAccount;
import jp.co.world.storedevelopment.model.NegotiationInterviewBusinessCard;
import jp.co.world.storedevelopment.model.NegotiationNoticeAccount;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationInterviewAccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationInterviewBusinessCardRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationInterviewCorporationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationNoticeAccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationOpenAccountRepository;
import jp.co.world.storedevelopment.pc.controller.dto.InterviewCorporationDTO;

public class NegotiationHistoryViewModel {

	private static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	private static DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");
	private static int trimContentIndex = 15;
	private static int trimNextActionIndex = 15;

	private Account account;
	private Negotiation negotiation;

	private List<String> noticeAccountNames;
	private List<Long> noticeAccountIds;

	public static List<NegotiationHistoryViewModel> toList(List<Negotiation> list, Account account) {
		return list.stream().filter(n -> (n.getIsDeleted() == false)).map(n -> {
			NegotiationHistoryViewModel model = new NegotiationHistoryViewModel(n, account);
			return model;
		}).collect(Collectors.toList());
	}

	public NegotiationHistoryViewModel(Negotiation negotiation, Account account) {
		List<NegotiationNoticeAccount> noticeAccounts = new NegotiationNoticeAccountRepository()
				.findByNegotiation(negotiation);

		List<String> listAccountNames = noticeAccounts.stream().map(n -> {
			return n.getAccountName();
		}).collect(Collectors.toList());

		setNoticeAccountNames(listAccountNames);

		List<Long> listAccountIds = noticeAccounts.stream().map(n -> {
			return n.getAccountId();
		}).collect(Collectors.toList());

		setNoticeAccountIds(listAccountIds);

		this.account = account;
		this.negotiation = negotiation;
	}

	public List<Long> getNoticeAccountIds() {
		return noticeAccountIds;
	}

	public void setNoticeAccountIds(List<Long> noticeAccountIds) {
		this.noticeAccountIds = noticeAccountIds;
	}

	public List<String> getNoticeAccountNames() {
		return noticeAccountNames;
	}

	public void setNoticeAccountNames(List<String> noticeAccountNames) {
		this.noticeAccountNames = noticeAccountNames;
	}

	public String getTitle() {
		return negotiation.getTitle();
	}

	public String divisionValue() {
		String division = negotiation.getDivision();
		switch (division) {
		case "NEGOTIATION":
			return "商談";
		case "INSPECTION":
			return "現調";
		case "RECEPTION":
			return "会食";
		case "GENERALMEETING":
			return "総会";
		case "OTHER":
			return "その他";
		default:
			return "";
		}
	}

	public String getScheduleDate() {
		return formatDate(negotiation.getScheduleStartDatetime());
	}

	public String getScheduleTime() {
		return getTime(negotiation.getScheduleStartDatetime(), negotiation.getScheduleEndDatetime());
	}

	private String getTime(LocalDateTime start, LocalDateTime end) {
		if (start != null && end != null) {
			String startTime = TIME_FORMAT.format(start);
			String endTime = TIME_FORMAT.format(end);
			return startTime + " - " + endTime;
		} else {
			return "";
		}
	}

	public String getImplementationDate() {
		return formatDate(negotiation.getImplementationStartDatetime());
	}

	private String formatDate(LocalDateTime date) {
		if (date != null) {
			return DATE_FORMAT.format(date);
		} else {
			return "";
		}
	}

	public String getImplementationTime() {
		return getTime(negotiation.getImplementationStartDatetime(), negotiation.getImplementationEndDatetime());
	}

	public boolean isStop() {
		return negotiation.isStop();
	}

	public String getStopState() {
		if (isStop()) {
			return "中止";
		} else {
			return "";
		}
	}

	public String IsReadLater() {
		if (negotiation.getIsLater(account)) {
			return "あとで読む";
		} else {
			return "";
		}
	}

	public List<InterviewCorporationDTO> getNegotiationInterviewCorporations() {
		return new NegotiationInterviewCorporationRepository().findByNegotiation(negotiation).stream().map(n -> {
			return new InterviewCorporationDTO(n);
		}).collect(Collectors.toList());
	}

	public List<NegotiationInterviewAccount> getInterviewAccounts() {
		return new NegotiationInterviewAccountRepository().findByNegotiation(negotiation);
	}

	public List<NegotiationInterviewBusinessCard> getInterviewBussinessCards() {
		return new NegotiationInterviewBusinessCardRepository().findByNegotiation(negotiation);
	}

	public Integer getPriority() {
		return negotiation.getPriority();
	}

	public String getPlace() {
		return negotiation.getPlace();
	}

	public String getTrimContent() {
		String content = negotiation.getContent();
		if (content.length() >= trimContentIndex) {
			return content.substring(0, trimNextActionIndex) + "...";
		} else {
			return content;
		}
	}

	public String getTrimNextActionContent() {
		String content = negotiation.getNextActionContent();
		if (content.length() >= trimNextActionIndex) {
			return content.substring(0, trimNextActionIndex) + "...";
		} else {
			return content;
		}
	}

	public String openRates() {
		int openRates = 0;

		if (noticeAccountIds.size() > 0) {
			List<Long> accountIds = noticeAccountIds;
			Long open = new NegotiationOpenAccountRepository().findByNegotiation(negotiation).stream()
					.filter(x -> accountIds.contains(x.getAccountId())).distinct().count();
			Integer all = noticeAccountIds.size();
			openRates = (int) ((open.doubleValue() / all.doubleValue()) * 100);
		}

		return String.valueOf(openRates) + "%";
	}

	public String getNotification() {
		return String.join(" ", getNoticeAccountNames());
	}
}

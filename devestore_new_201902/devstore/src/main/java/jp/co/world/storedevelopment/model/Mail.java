package jp.co.world.storedevelopment.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.MailModelMapper;

public class Mail extends ActiveModel<Mail> {
	private String mailAddress = "";
	private String mailAddressCc = "";
	private String subject = "";
	private String body = "";
	private LocalDateTime scheduledDatetime;
	private LocalDateTime sentDatetime;
	private Boolean isSent = false;

	private final String negotiationTemplate = "%sさんにより商談%sが登録されました\n\n" + "商談件名：%s\n" + "予定日時：%s\n" + "実施日時：%s\n"
			+ "中止区分：%s\n" + "商談区分：%s\n" + "公開範囲：%s\n"
			+ "---------------------------------------------------------------------------------------------------\n"
			+ "■訪問目的\n%s\n" + "■商談内容\n%s\n" + "■ネクストアクション\n%s\n"
			+ "---------------------------------------------------------------------------------------------------\n"
			+ "案件名：%s\n"
			+ "面談先法人：%s\n" 
			+ "面談先館：%s\n" + "面談者：%s\n" + "訪問者：%s\n" + "通知先：%s\n" + "商談情報URL：";

	private final String projectTaskTemplate = "%sさんによりタスクが%sされました\n\n"
			+ "■%s\n　実施日：%s\n\n"
			+ "■重要度\n%s\n\n"
			+ "■内容\n%s\n\n"
			+ "■期限\n%s";
	
	private final String projectTaskTheDayTemplate = "■%s\n"
			+ "・実施日：%s\n\n"
			+ "・重要度\n　%s\n\n"
			+ "・内容\n　%s\n\n";

	private final String projectTaskExpiredTemplate ="■%s\n"
			+ "・実施日：%s\n\n"
			+ "・期限\n　%s\n\n"
			+ "・重要度\n　%s\n\n"
			+ "・内容\n　%s\n\n";

	public void setProjectTaskTheDayTemplate(List<ProjectTask> list) {
		String message = list.stream().map(t -> {
			Project model = t.project();
			String imp = model.implemenrationDateValue();
			if (imp.isEmpty() == false) {
				imp += "予定";
			}

			return String.format(projectTaskTheDayTemplate, 
					model.mailTitle(), 
					imp,
					t.getImportant(),
					t.getComment()
			);
		}).collect(Collectors.joining());
		
		message = "本日期限のタスクがあります。\n" + message;
		
		Integer projectNumber = list.stream().map(t -> {
			return t.getProjectId();
		}).distinct().collect(Collectors.toList()).size();
		
		setBody(message);
		setSubject(String.format("【店舗開発SYS】本日のタスク %s件", projectNumber));
	}

	public void setProjectTaskExpiredTemplate(List<ProjectTask> list) {
		String message = list.stream().map(t -> {
			Project model = t.project();
			String imp = model.implemenrationDateValue();
			if (imp.isEmpty() == false) {
				imp += "予定";
			}
			
			return String.format(projectTaskExpiredTemplate, 
					model.mailTitle(), 
					imp,
					t.periodValue(),
					t.getImportant(),
					t.getComment()
			);
		}).collect(Collectors.joining());
		
		message = "期限切れのタスクがあります。\n" + message;

		Integer projectNumber = list.stream().map(t -> {
			return t.getProjectId();
		}).distinct().collect(Collectors.toList()).size();
		
		setBody(message);
		setSubject(String.format("【店舗開発SYS】期限切れのタスク %s件", projectNumber));
	}

	public void setProjectTaskCreate(ProjectTask model, Account a) {
		setProjectTaskBody(model, a, "登録");
		setSubject("【店舗開発SYS】タスクが新規登録されました");
	}

	public void setProjectTaskUpdate(ProjectTask model, Account a) {
		setProjectTaskBody(model, a, "更新");
		setSubject("【店舗開発SYS】タスクが変更されました");
	}

	private void setProjectTaskBody(ProjectTask model, Account a, String label) {
		Project p = model.project();
		String value = p.getImplementationDateValue();
		
		if (value.isEmpty() == false) {
			value += " 予定";
		}

		String text = String.format(projectTaskTemplate, a.getFullName(), label, p.mailTitle(), p.getImplementationDateValue(),
				model.getImportant(), model.getComment(), model.periodValue());
		setBody(text);
	}
	
	public void setCreateNegotiation(Negotiation n, Account a) {
		setScheduledDatetime(n.getScheduleStartDatetime());
		if (n.getImplementationStartDatetime() == null) {
			setSubject(String.format("【店舗開発Sys】%s商談予定登録: %s", subjectStop(n), n.getTitle()));
			setBody(createBody(n, a, "予定"));
		} else {
			setSubject(String.format("【店舗開発Sys】%s商談実績更新: %s", subjectStop(n), n.getTitle()));
			setBody(createBody(n, a, "報告"));
		}
	}

	public void setUpdateNegotiation(Negotiation n, Account a) {
		setScheduledDatetime(n.getScheduleStartDatetime());
		if (n.getImplementationStartDatetime() == null) {
			setSubject(String.format("【店舗開発Sys】%s商談予定登録: %s", subjectStop(n), n.getTitle()));
			setBody(createBody(n, a, "予定"));
		} else {
			setSubject(String.format("【店舗開発Sys】%s商談実績更新: %s", subjectStop(n), n.getTitle()));
			setBody(createBody(n, a, "報告"));
		}
	}

	private String subjectStop(Negotiation n) {
		if (n.isStop()) {
			return "【中止】";
		} else {
			return "";
		}
	}

	private String negotiationStopWord(Negotiation n) {
		if (n.isStop()) {
			return "中止する";
		} else {
			return "";
		}
	}

	private String createBody(Negotiation n, Account a, String category) {
		return String.format(negotiationTemplate, a.getFullName(), category, n.getTitle(), n.getScheduleDateValue(),
				n.getImplementationDateValue(), negotiationStopWord(n), n.getDivisionValue(), n.getReleaseLevelValue(),
				n.getPurpose(), n.getContent(), n.getNextActionContent(), 
				n.projects().stream().map(v -> {
					return v.getTitle();
				}).collect(Collectors.joining("/")),
				n.getInterviewCorporationsNames(),
				n.getInterviewBuildingNames(), n.getInterviewBusinessCardNames(), n.getInterviewAccountNames(),
				n.getNoticeAccountName());
	}

	/**
	 *
	 * @return mailAddress
	 */
	public String getMailAddress() {
		return mailAddress;
	}

	/**
	 *
	 * @param mailAddress
	 */
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getMailAddressCc() {
		return mailAddressCc;
	}

	public void setMailAddressCc(String mailAddressCc) {
		this.mailAddressCc = mailAddressCc;
	}

	/**
	 *
	 * @return subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 *
	 * @param subject
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 *
	 * @return body
	 */
	public String getBody() {
		return body;
	}

	/**
	 *
	 * @param body
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 *
	 * @return scheduledDatetime
	 */
	public LocalDateTime getScheduledDatetime() {
		return scheduledDatetime;
	}

	/**
	 *
	 * @param scheduledDatetime
	 */
	public void setScheduledDatetime(LocalDateTime scheduledDatetime) {
		this.scheduledDatetime = scheduledDatetime;
	}

	/**
	 *
	 * @return sentDatetime
	 */
	public LocalDateTime getSentDatetime() {
		return sentDatetime;
	}

	/**
	 *
	 * @param sentDatetime
	 */
	public void setSentDatetime(LocalDateTime sentDatetime) {
		this.sentDatetime = sentDatetime;
	}

	/**
	 *
	 * @return
	 */
	public Boolean getIsSent() {
		return isSent;
	}

	/**
	 *
	 * @param isSent
	 */
	public void setIsSent(Boolean isSent) {
		this.isSent = isSent;
	}

	@Override
	protected ModelMapper<Mail> modelMapper(SqlSession session) {
		return session.getMapper(MailModelMapper.class);
	}
}
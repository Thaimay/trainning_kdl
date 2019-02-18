package jp.co.world.storedevelopment.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.ProjectTaskModelMapper;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectTaskAccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectTaskRepository;
import jp.co.world.storedevelopment.model.mapper.repository.TodoRepository;

/**
 * @author tienpv
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class ProjectTask extends ActiveModel<ProjectTask> implements ProjectTargetAlert {

	private Long projectId;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate period;
	private String important;
	private Boolean complete = false;
	private String comment;
	private List<ProjectTaskAccount> account;

	private String[] ignoreFields = new String[] { "account" };
	private static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd");

	@Override
	protected String[] ignoreFields() {
		return ignoreFields;
	}

	public String periodValue() {
		if (getPeriod() != null) {
			return DATE_FORMAT.format(getPeriod());
		} else {
			return "";
		}
	}

	public void setIgnoreFields(String[] ignoreFields) {
		this.ignoreFields = ignoreFields;
	}

	@Override
	protected ModelMapper<ProjectTask> modelMapper(SqlSession session) {
		return session.getMapper(ProjectTaskModelMapper.class);
	}

	public List<ProjectTaskAccount> taskAccounts() {
		return new ProjectTaskAccountRepository().findByProjectTaskId(getId());
	}

	public Boolean sameOld() {
		ProjectTask old = new ProjectTaskRepository().findById(getId()).orElseGet(() -> {
			throw new IllegalStateException();
		});

		Boolean sameComment = sameComment(old);
		Boolean sameImport = sameImport(old);
		Boolean samePeriod = periodValue().equals(old.periodValue());
		Boolean taskAccounts = sameTaskAccount(old);

		return sameComment && sameImport && samePeriod && taskAccounts;
	}

	private Boolean sameComment(ProjectTask model) {
		String newComment = Optional.ofNullable(getComment()).orElseGet(() -> {
			return "";
		});
		String oldComment = Optional.ofNullable(model.getComment()).orElseGet(() -> {
			return "";
		});
		return newComment.equals(oldComment);
	}

	private Boolean sameImport(ProjectTask model) {
		String newImportant = Optional.ofNullable(getImportant()).orElseGet(() -> {
			return "";
		});
		String oldImportant = Optional.ofNullable(model.getImportant()).orElseGet(() -> {
			return "";
		});
		return newImportant.equals(oldImportant);
	}

	private Boolean sameTaskAccount(ProjectTask model) {
		List<Long> newList = Optional.ofNullable(getAccount()).orElseGet(() -> {
			return new ArrayList<>();
		}).stream().map(a -> {
			return a.getAccountId();
		}).collect(Collectors.toList());
		List<Long> oldList = taskAccounts().stream().map(a -> {
			return a.getAccountId();
		}).collect(Collectors.toList());

		Boolean sameNew = newList.stream().filter(a -> {
			return oldList.contains(a) == false;
		}).collect(Collectors.toList()).size() == 0;

		Boolean sameOld = oldList.stream().filter(a -> {
			return newList.contains(a) == false;
		}).collect(Collectors.toList()).size() == 0;
		return sameNew && sameOld;
	}

	public void complete() {
		if (getComplete()) {
			setComplete(false);
		} else {
			new TodoRepository().deleteProjectTaskBy(getProjectId());
			setComplete(true);
		}
		update();
	}

	public String prevMessage() {
		String date = DATE_FORMAT.format(period);
		return String.format("期限日： %s もうすぐ期限切れのタスクがあります\n%s (%s)", date, comment, project().getTitle());
	}

	public String theDayMessage() {		
		String date = DATE_FORMAT.format(LocalDate.now());
		return String.format("期限日： %s 本日期限のタスクがあります\n%s (%s)", date, comment, project().getTitle());
	}

	public String expiredMessage() {
		String date = DATE_FORMAT.format(period);
		return String.format("期限日： %s 期限日を過ぎたタスクがあります。\n%s (%s)", date, comment, project().getTitle());
	}

	public Project project() {
		return new ProjectRepository().findById(getProjectId()).orElseGet(() -> {
			throw new IllegalStateException("");
		});
	}

	public Boolean correctPrev(Integer prev) {
		Integer day = betweenPeriod();
		return day >= prev * -1 && day <= -1 && getComplete() == false;
	}

	public Boolean correntTheDay() {
		Integer day = betweenPeriod();
		return day == 0 && getComplete() == false;
	}

	public Boolean correntExpired() {
		Integer day = betweenPeriod();
		return day > 0 && getComplete() == false;
	}

	public List<Account> accounts() {
		return new ProjectTaskAccountRepository().findBy(getId());
	}

	public Integer betweenPeriod() {
		if (period == null) {
			return 0;
		}
		
		Temporal now = LocalDate.now().atTime(0, 0);
		Temporal target = LocalDate.of(period.getYear(), period.getMonthValue(), period.getDayOfMonth()).atTime(0, 0);
		return (int) Duration.between(target, now).toDays();
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public LocalDate getPeriod() {
		return period;
	}

	public void setPeriod(LocalDate period) {
		this.period = period;
	}

	public String getImportant() {
		return important;
	}

	public void setImportant(String important) {
		this.important = important;
	}

	public Boolean getComplete() {
		return complete;
	}

	public void setComplete(Boolean complete) {
		this.complete = complete;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<ProjectTaskAccount> getAccount() {
		return account;
	}

	public void setAccount(List<ProjectTaskAccount> account) {
		this.account = account;
	}

}

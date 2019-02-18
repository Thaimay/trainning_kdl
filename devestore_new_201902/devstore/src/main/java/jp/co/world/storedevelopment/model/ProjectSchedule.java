package jp.co.world.storedevelopment.model;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ibatis.session.SqlSession;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.ProjectScheduleModelMapper;
import jp.co.world.storedevelopment.model.mapper.repository.MProjectActionStatusRepository;

/**
 * @author tienpv
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class ProjectSchedule extends ActiveModel<ProjectSchedule> {

	private Long projectId;
	private Long mProjectActionStatusId;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate scheduleDate;
	private Boolean isChangedScheduleDate;

	private MProjectActionStatus projectActionStatus;

	private String[] ignoreFields = new String[] {
			"projectActionStatus"
	};

	@Override
	protected String[] ignoreFields() {
		return ignoreFields;
	}

	@Override
	protected ModelMapper<ProjectSchedule> modelMapper(SqlSession session) {
		return session.getMapper(ProjectScheduleModelMapper.class);
	}

	public ProjectSchedule() {
		super();
	}

	public MProjectActionStatus actionStatus() {
		MProjectActionStatus status = getProjectActionStatus();

		if (status == null) {
			status = new MProjectActionStatusRepository().findById(getMProjectActionStatusId()).orElseGet(() -> {
				throw new IllegalStateException("存在しない行動ステータスです");
			});
			setProjectActionStatus(status);
		}
		return status;
	}

	public ProjectSchedule(Project project, MProjectActionStatus mProjectActionStatus) {
		this.projectId = project.getId();
		this.mProjectActionStatusId = mProjectActionStatus.getId();
		this.scheduleDate = toLocalDate(mProjectActionStatus.getSchedule());
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public MProjectActionStatus getProjectActionStatus() {
		return projectActionStatus;
	}

	public void setProjectActionStatus(MProjectActionStatus projectActionStatus) {
		this.projectActionStatus = projectActionStatus;
	}

	public Long getMProjectActionStatusId() {
		return mProjectActionStatusId;
	}

	public void setMProjectActionStatusId(Long mProjectActionStatusId) {
		this.mProjectActionStatusId = mProjectActionStatusId;
	}

	public LocalDate getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(LocalDate scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	public Boolean getIsChangedScheduleDate() {
		return isChangedScheduleDate;
	}

	public void setIsChangedScheduleDate(Boolean isChangedScheduleDate) {
		this.isChangedScheduleDate = isChangedScheduleDate;
	}

	public Optional<LocalDate> scheduleDateOptional() {
		return Optional.ofNullable(getScheduleDate());
	}

	public LocalDate toTargetDate(String scheduleDate, LocalDate implementationDate) {
		LocalDate targetDate = implementationDate;
		String regex = "^.*?(\\d+Y)?.*?(\\d+M)?.*?(\\d+D)?$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(scheduleDate);

		matcher.find();
		Optional<String> yearOption = Optional.ofNullable(matcher.group(1));
		if (yearOption.isPresent()) {
			targetDate = targetDate.minusYears(getYearsAfter(yearOption.orElse("0Y")));
		}
		Optional<String> monthOption = Optional.ofNullable(matcher.group(2));
		if (monthOption.isPresent()) {
			targetDate = targetDate.minusMonths(getMonthsAfter(monthOption.orElse("0M")));
		}
		Optional<String> dayOption = Optional.ofNullable(matcher.group(3));
		if (dayOption.isPresent()) {
			targetDate = targetDate.minusDays(getDaysAfter(dayOption.orElse("0D")));
		}

		return targetDate;
	}

	private LocalDate toLocalDate(String scheduleDate) {
		LocalDate targetDate = LocalDate.now();
		String regex = "^.*?(\\d+Y)?.*?(\\d+M)?.*?(\\d+D)?$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(scheduleDate);

		matcher.find();
		Optional<String> yearOption = Optional.ofNullable(matcher.group(1));
		if (yearOption.isPresent()) {
			targetDate = targetDate.plusYears(getYearsAfter(yearOption.orElse("0Y")));
		}
		Optional<String> monthOption = Optional.ofNullable(matcher.group(2));
		if (monthOption.isPresent()) {
			targetDate = targetDate.plusMonths(getMonthsAfter(monthOption.orElse("0M")));
		}
		Optional<String> dayOption = Optional.ofNullable(matcher.group(3));
		if (dayOption.isPresent()) {
			targetDate = targetDate.plusDays(getDaysAfter(dayOption.orElse("0D")));
		}

		return targetDate;
	}

	private int getYearsAfter(String yearStr) {
		return Integer.parseInt(yearStr.replace("Y", ""));
	}

	private int getMonthsAfter(String monthStr) {
		return Integer.parseInt(monthStr.replace("M", ""));
	}

	private int getDaysAfter(String dayStr) {
		return Integer.parseInt(dayStr.replace("D", ""));
	}
}
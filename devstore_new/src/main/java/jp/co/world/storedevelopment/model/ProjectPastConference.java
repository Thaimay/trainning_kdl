package jp.co.world.storedevelopment.model;

import java.time.LocalDate;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.ProjectPastConferenceModelMapper;

/**
 * @author tienpv
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class ProjectPastConference extends ActiveModel<ProjectPastConference> {

	private Long projectId;
	private String name;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate scheduleDate;
	private String result;
	private String attendees;
	private String comment;

	private String[] ignoreFields = new String[] {};

	@Override
	protected String[] ignoreFields() {
		return ignoreFields;
	}

	@Override
	protected ModelMapper<ProjectPastConference> modelMapper(SqlSession session) {
		return session.getMapper(ProjectPastConferenceModelMapper.class);
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(LocalDate scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getAttendees() {
		return attendees;
	}

	public void setAttendees(String attendees) {
		this.attendees = attendees;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}

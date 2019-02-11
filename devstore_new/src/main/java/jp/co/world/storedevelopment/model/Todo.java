package jp.co.world.storedevelopment.model;

import java.time.LocalDateTime;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.TodoModelMapper;

public class Todo extends ActiveModel<Todo> {

	private Long accountId = 0L;
	private Long negotiationId = 0L;
	private Long projectId = 0L;
	private Long buildingId = 0L;
	private Integer detailsDivisionId = 0;
	private String content = "";
	private String contentSub = "";
	private LocalDateTime showStartDatetime;
	private LocalDateTime showEndDatetime;
	private LocalDateTime deadlineDatetime;

	public Todo() {
		setShowStartDatetime(LocalDateTime.now());
		setShowEndDatetime(LocalDateTime.of(2099, 12, 31, 23, 59, 59, 999999999));
	}

	public Todo(String string) {
		setContent(string);
	}

	public Todo(String string, Account account) {
		setAccountId(account.getId());
		setContent(string);

	}

	@Override
	protected ModelMapper<Todo> modelMapper(SqlSession session) {
		return session.getMapper(TodoModelMapper.class);
	}

	/**
	 * 
	 * @return accountId
	 */
	public Long getAccountId() {
		return accountId;
	}

	/**
	 * 
	 * @param accountId
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	
	/**
	 * 
	 * @return
	 */
	public Long getNegotiationId() {
		return negotiationId;
	}

	/**
	 * 
	 * @param negotiationId
	 */
	public void setNegotiationId(Long negotiationId) {
		this.negotiationId = negotiationId;
	}

	/**
	 * 
	 * @return
	 */
	public Long getProjectId() {
		return projectId;
	}

	/**
	 * 
	 * @param projectId
	 */
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	/**
	 * 
	 * @return
	 */
	public Long getBuildingId() {
		return buildingId;
	}

	/**
	 * 
	 * @param buildingId
	 */
	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}

	/**
	 * 
	 * @return detailsDivisionId
	 */
	public Integer getDetailsDivisionId() {
		return detailsDivisionId;
	}

	/**
	 * 
	 * @param detailsDivisionId
	 */
	public void setDetailsDivisionId(Integer detailsDivisionId) {
		this.detailsDivisionId = detailsDivisionId;
	}

	/**
	 * 
	 * @return content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 
	 * @return contentSub
	 */
	public String getContentSub() {
		return contentSub;
	}

	/**
	 * 
	 * @param contentSub
	 */
	public void setContentSub(String contentSub) {
		this.contentSub = contentSub;
	}

	/**
	 * 
	 * @return showStartDatetime
	 */
	public LocalDateTime getShowStartDatetime() {
		return showStartDatetime;
	}

	/**
	 * 
	 * @param showStartDatetime
	 */
	public void setShowStartDatetime(LocalDateTime showStartDatetime) {
		this.showStartDatetime = showStartDatetime;
	}

	/**
	 * 
	 * @return showEndDatetime
	 */
	public LocalDateTime getShowEndDatetime() {
		return showEndDatetime;
	}

	/**
	 * 
	 * @param showEndDatetime
	 *            showEndDatetime
	 */
	public void setShowEndDatetime(LocalDateTime showEndDatetime) {
		this.showEndDatetime = showEndDatetime;
	}

	/**
	 * 
	 * @return deadlineDatetime
	 */
	public LocalDateTime getDeadlineDatetime() {
		return deadlineDatetime;
	}

	/**
	 * 
	 * @param deadlineDatetime
	 */
	public void setDeadlineDatetime(LocalDateTime deadlineDatetime) {
		this.deadlineDatetime = deadlineDatetime;
	}

}

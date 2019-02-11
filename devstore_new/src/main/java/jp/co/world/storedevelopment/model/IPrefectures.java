package jp.co.world.storedevelopment.model;

import java.time.LocalDateTime;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.dev.RandomTestData;
import jp.co.world.storedevelopment.model.mapper.IPrefecturesModelMapper;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;

/**
 * @author tainm
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class IPrefectures extends IActiveModel<IPrefectures> {

	private String jisPrefecturesId;
	private String prefecturesName;
	private String prefecturesNameEllipsisName;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime coordinationCreatedDatetime;
	private String coordinationCreatedAccountCode;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime coordinationUpdateDatetime;
	private String coordinationUpdateAccountCode;
	private String action;

	public IPrefectures() {

	}

	public IPrefectures(String name) {
		this.setJisPrefecturesId(RandomTestData.getString(2, false, true));
		this.setPrefecturesName(name);
		this.setCreatedAccountCode(new AccountRepository().getHead().get().getEmployeeCd());
		this.setUpdateAccountCode(new AccountRepository().getHead().get().getEmployeeCd());
	}

	private String[] ignoreFields = new String[] {};

	@Override
	protected String[] ignoreFields() {
		return ignoreFields;
	}

	public void setIgnoreFields(String[] ignoreFields) {
		this.ignoreFields = ignoreFields;
	}

	@Override
	protected ModelMapper<IPrefectures> modelMapper(SqlSession session) {
		return session.getMapper(IPrefecturesModelMapper.class);
	}

	public String getJisPrefecturesId() {
		return jisPrefecturesId;
	}

	public void setJisPrefecturesId(String jisPrefecturesId) {
		this.jisPrefecturesId = jisPrefecturesId;
	}

	public String getPrefecturesName() {
		return prefecturesName;
	}

	public void setPrefecturesName(String prefecturesName) {
		this.prefecturesName = prefecturesName;
	}

	public String getPrefecturesNameEllipsisName() {
		return prefecturesNameEllipsisName;
	}

	public void setPrefecturesNameEllipsisName(String prefecturesNameEllipsisName) {
		this.prefecturesNameEllipsisName = prefecturesNameEllipsisName;
	}

	public LocalDateTime getCoordinationCreatedDatetime() {
		return coordinationCreatedDatetime;
	}

	public void setCoordinationCreatedDatetime(LocalDateTime coordinationCreatedDatetime) {
		this.coordinationCreatedDatetime = coordinationCreatedDatetime;
	}

	public String getCoordinationCreatedAccountCode() {
		return coordinationCreatedAccountCode;
	}

	public void setCoordinationCreatedAccountCode(String coordinationCreatedAccountCode) {
		this.coordinationCreatedAccountCode = coordinationCreatedAccountCode;
	}

	public LocalDateTime getCoordinationUpdateDatetime() {
		return coordinationUpdateDatetime;
	}

	public void setCoordinationUpdateDatetime(LocalDateTime coordinationUpdateDatetime) {
		this.coordinationUpdateDatetime = coordinationUpdateDatetime;
	}

	public String getCoordinationUpdateAccountCode() {
		return coordinationUpdateAccountCode;
	}

	public void setCoordinationUpdateAccountCode(String coordinationUpdateAccountCode) {
		this.coordinationUpdateAccountCode = coordinationUpdateAccountCode;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}
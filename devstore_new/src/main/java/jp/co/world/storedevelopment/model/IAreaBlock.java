package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.IAreaBlockModelMapper;

/**
 * @author tienpv
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class IAreaBlock extends IActiveModel<IAreaBlock> {

	private Long blockId;
	private String mainBlockCd;
	private String mainBlockName;
	private String prefectureCd;
	private String prefectureName;
	private Long areaId;
	private String jisPrefetureCd;
	private String unknown;
	private String blockFlg;
	private String mailBlockFlg;
	private String coordinationCreatedDatetime;
	private String coordinationCreatedAccountCode;
	private String coordinationUpdateDatetime;
	private String coordinationUpdateAccountCode;
	private String action;

	public Long getBlockId() {
		return blockId;
	}

	public void setBlockId(Long blockId) {
		this.blockId = blockId;
	}

	public String getMainBlockCd() {
		return mainBlockCd;
	}

	public void setMainBlockCd(String mainBlockCd) {
		this.mainBlockCd = mainBlockCd;
	}

	public String getMainBlockName() {
		return mainBlockName;
	}

	public void setMainBlockName(String mainBlockName) {
		this.mainBlockName = mainBlockName;
	}

	public String getPrefectureCd() {
		return prefectureCd;
	}

	public void setPrefectureCd(String prefectureCd) {
		this.prefectureCd = prefectureCd;
	}

	public String getUnknown() {
		return unknown;
	}

	public void setUnknown(String unknown) {
		this.unknown = unknown;
	}

	public String getPrefectureName() {
		return prefectureName;
	}

	public void setPrefectureName(String prefectureName) {
		this.prefectureName = prefectureName;
	}

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public String getJisPrefetureCd() {
		return jisPrefetureCd;
	}

	public void setJisPrefetureCd(String jisPrefetureCd) {
		this.jisPrefetureCd = jisPrefetureCd;
	}

	public String getBlockFlg() {
		return blockFlg;
	}

	public void setBlockFlg(String blockFlg) {
		this.blockFlg = blockFlg;
	}

	public String getMailBlockFlg() {
		return mailBlockFlg;
	}

	public void setMailBlockFlg(String mailBlockFlg) {
		this.mailBlockFlg = mailBlockFlg;
	}

	public String getCoordinationCreatedDatetime() {
		return coordinationCreatedDatetime;
	}

	public void setCoordinationCreatedDatetime(String coordinationCreatedDatetime) {
		this.coordinationCreatedDatetime = coordinationCreatedDatetime;
	}

	public String getCoordinationCreatedAccountCode() {
		return coordinationCreatedAccountCode;
	}

	public void setCoordinationCreatedAccountCode(String coordinationCreatedAccountCode) {
		this.coordinationCreatedAccountCode = coordinationCreatedAccountCode;
	}

	public String getCoordinationUpdateDatetime() {
		return coordinationUpdateDatetime;
	}

	public void setCoordinationUpdateDatetime(String coordinationUpdateDatetime) {
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

	@Override
	protected ModelMapper<IAreaBlock> modelMapper(SqlSession session) {
		return session.getMapper(IAreaBlockModelMapper.class);
	}

}

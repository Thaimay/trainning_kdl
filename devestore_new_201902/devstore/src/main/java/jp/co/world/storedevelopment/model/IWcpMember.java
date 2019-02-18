package jp.co.world.storedevelopment.model;

import java.math.BigDecimal;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.IWcpMemberModelMapper;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class IWcpMember extends IActiveModel<IWcpMember> {

	private String areaCd;
	private String areaName;
	private String blockCd;
	private String blockName;
	private String buildingCd;
	private String buildingName;
	private String shopCd;
	private String shopName;
	private String yearMonth;
	private Integer wpcMember;
	private BigDecimal memberRate;
	private Integer newsletterRegistrationNumber;
	private Long dispOrder;

	public IWcpMember() {

	}

	public String getAreaCd() {
		return areaCd;
	}

	public void setAreaCd(String areaCd) {
		this.areaCd = areaCd;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getBlockCd() {
		return blockCd;
	}

	public void setBlockCd(String blockCd) {
		this.blockCd = blockCd;
	}

	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

	public String getBuildingCd() {
		return buildingCd;
	}

	public void setBuildingCd(String buildingCd) {
		this.buildingCd = buildingCd;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getShopCd() {
		return shopCd;
	}

	public void setShopCd(String shopCd) {
		this.shopCd = shopCd;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getYearMonth() {
		return yearMonth;
	}

	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}

	public Integer getWpcMember() {
		return wpcMember;
	}

	public void setWpcMember(Integer wpcMember) {
		this.wpcMember = wpcMember;
	}

	public BigDecimal getMemberRate() {
		return memberRate;
	}

	public void setMemberRate(BigDecimal memberRate) {
		this.memberRate = memberRate;
	}

	public Integer getNewsletterRegistrationNumber() {
		return newsletterRegistrationNumber;
	}

	public void setNewsletterRegistrationNumber(Integer newsletterRegistrationNumber) {
		this.newsletterRegistrationNumber = newsletterRegistrationNumber;
	}

	public Long getDispOrder() {
		return dispOrder;
	}

	public void setDispOrder(Long dispOrder) {
		this.dispOrder = dispOrder;
	}

	@Override
	protected ModelMapper<IWcpMember> modelMapper(SqlSession session) {
		return session.getMapper(IWcpMemberModelMapper.class);
	}

}

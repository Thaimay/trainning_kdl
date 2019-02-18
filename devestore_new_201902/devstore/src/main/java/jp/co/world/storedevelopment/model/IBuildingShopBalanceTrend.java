package jp.co.world.storedevelopment.model;

import java.math.BigDecimal;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.IBuildingShopBalanceTrendModelMapper;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ShopRepository;

/**
 * @author tainm
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class IBuildingShopBalanceTrend extends ActiveModel<IBuildingShopBalanceTrend> {

	private Long buildingId;
	private String buildingName;
	private Long shopId;
	private String shopName;
	private String dataTypeCd;
	private String dataTypeName;
	private BigDecimal n5Num;
	private BigDecimal n4Num;
	private BigDecimal n3Num;
	private BigDecimal n2Num;
	private BigDecimal n1Num;
	private BigDecimal nNum;
	private Long dispOrder;

	private String[] ignoreFields = new String[] {};

	@Override
	protected String[] ignoreFields() {
		return ignoreFields;
	}

	public void setIgnoreFields(String[] ignoreFields) {
		this.ignoreFields = ignoreFields;
	}

	public Long getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getDataTypeCd() {
		return dataTypeCd;
	}

	public void setDataTypeCd(String dataTypeCd) {
		this.dataTypeCd = dataTypeCd;
	}

	public String getDataTypeName() {
		return dataTypeName;
	}

	public void setDataTypeName(String dataTypeName) {
		this.dataTypeName = dataTypeName;
	}

	public BigDecimal getN5Num() {
		return n5Num;
	}

	public void setN5Num(BigDecimal n5Num) {
		this.n5Num = n5Num;
	}

	public BigDecimal getN4Num() {
		return n4Num;
	}

	public void setN4Num(BigDecimal n4Num) {
		this.n4Num = n4Num;
	}

	public BigDecimal getN3Num() {
		return n3Num;
	}

	public void setN3Num(BigDecimal n3Num) {
		this.n3Num = n3Num;
	}

	public BigDecimal getN2Num() {
		return n2Num;
	}

	public void setN2Num(BigDecimal n2Num) {
		this.n2Num = n2Num;
	}

	public BigDecimal getN1Num() {
		return n1Num;
	}

	public void setN1Num(BigDecimal n1Num) {
		this.n1Num = n1Num;
	}

	public BigDecimal getnNum() {
		return nNum;
	}

	public void setnNum(BigDecimal nNum) {
		this.nNum = nNum;
	}

	public Long getDispOrder() {
		return dispOrder;
	}

	public void setDispOrder(Long dispOrder) {
		this.dispOrder = dispOrder;
	}

	public Building getBuilding() {
		return new BuildingRepository().findById(getBuildingId())
				.orElseThrow(() -> new IllegalStateException("存在しないアカウントIDです:" + getBuildingId()));
	}

	public Shop getShop() {
		return new ShopRepository().findById(getShopId())
				.orElseThrow(() -> new IllegalStateException("存在しないアカウントIDです:" + getShopId()));
	}

	@Override
	protected ModelMapper<IBuildingShopBalanceTrend> modelMapper(SqlSession session) {
		return session.getMapper(IBuildingShopBalanceTrendModelMapper.class);
	}
}
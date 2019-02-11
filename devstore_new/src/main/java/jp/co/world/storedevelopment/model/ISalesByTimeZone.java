package jp.co.world.storedevelopment.model;

import java.math.BigDecimal;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.ISalesByTimeZoneModelMapper;

/**
 * @author hungdh
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class ISalesByTimeZone extends IActiveModel<ISalesByTimeZone> {

	private String shopCd;
	private String dispTime;
	private BigDecimal mondaySalesAmount;
	private BigDecimal mondayPurchasingGuestsNumber;
	private BigDecimal mondayInRestaurantGuestsNumber;
	private BigDecimal tuesdaySalesAmount;
	private BigDecimal tuesdayPurchasingGuestsNumber;
	private BigDecimal tuesdayInRestaurantGuestsNumber;
	private BigDecimal wednesdaySalesAmount;
	private BigDecimal wednesdayPurchasingGuestsNumber;
	private BigDecimal wednesdayInRestaurantGuestsNumber;
	private BigDecimal thursdaySalesAmount;
	private BigDecimal thursdayPurchasingGuestsNumber;
	private BigDecimal thursdayInRestaurantGuestsNumber;
	private BigDecimal fridaySalesAmount;
	private BigDecimal fridayPurchasingGuestsNumber;
	private BigDecimal fridayInRestaurantGuestsNumber;
	private BigDecimal saturdaySalesAmount;
	private BigDecimal saturdayPurchasingGuestsNumber;
	private BigDecimal saturdayInRestaurantGuestsNumber;
	private BigDecimal sundaySalesAmount;
	private BigDecimal sundayPurchasingGuestsNumber;
	private BigDecimal sundayInRestaurantGuestsNumber;
	private Long dispOrder;

	public ISalesByTimeZone() {
	}

	public String getShopCd() {
		return shopCd;
	}

	public void setShopCd(String shopCd) {
		this.shopCd = shopCd;
	}

	public String getDispTime() {
		return dispTime;
	}

	public void setDispTime(String dispTime) {
		this.dispTime = dispTime;
	}

	public BigDecimal getMondaySalesAmount() {
		return mondaySalesAmount;
	}

	public void setMondaySalesAmount(BigDecimal mondaySalesAmount) {
		this.mondaySalesAmount = mondaySalesAmount;
	}

	public BigDecimal getMondayPurchasingGuestsNumber() {
		return mondayPurchasingGuestsNumber;
	}

	public void setMondayPurchasingGuestsNumber(BigDecimal mondayPurchasingGuestsNumber) {
		this.mondayPurchasingGuestsNumber = mondayPurchasingGuestsNumber;
	}

	public BigDecimal getMondayInRestaurantGuestsNumber() {
		return mondayInRestaurantGuestsNumber;
	}

	public void setMondayInRestaurantGuestsNumber(BigDecimal mondayInRestaurantGuestsNumber) {
		this.mondayInRestaurantGuestsNumber = mondayInRestaurantGuestsNumber;
	}

	public BigDecimal getTuesdaySalesAmount() {
		return tuesdaySalesAmount;
	}

	public void setTuesdaySalesAmount(BigDecimal tuesdaySalesAmount) {
		this.tuesdaySalesAmount = tuesdaySalesAmount;
	}

	public BigDecimal getTuesdayPurchasingGuestsNumber() {
		return tuesdayPurchasingGuestsNumber;
	}

	public void setTuesdayPurchasingGuestsNumber(BigDecimal tuesdayPurchasingGuestsNumber) {
		this.tuesdayPurchasingGuestsNumber = tuesdayPurchasingGuestsNumber;
	}

	public BigDecimal getTuesdayInRestaurantGuestsNumber() {
		return tuesdayInRestaurantGuestsNumber;
	}

	public void setTuesdayInRestaurantGuestsNumber(BigDecimal tuesdayInRestaurantGuestsNumber) {
		this.tuesdayInRestaurantGuestsNumber = tuesdayInRestaurantGuestsNumber;
	}

	public BigDecimal getWednesdaySalesAmount() {
		return wednesdaySalesAmount;
	}

	public void setWednesdaySalesAmount(BigDecimal wednesdaySalesAmount) {
		this.wednesdaySalesAmount = wednesdaySalesAmount;
	}

	public BigDecimal getWednesdayPurchasingGuestsNumber() {
		return wednesdayPurchasingGuestsNumber;
	}

	public void setWednesdayPurchasingGuestsNumber(BigDecimal wednesdayPurchasingGuestsNumber) {
		this.wednesdayPurchasingGuestsNumber = wednesdayPurchasingGuestsNumber;
	}

	public BigDecimal getWednesdayInRestaurantGuestsNumber() {
		return wednesdayInRestaurantGuestsNumber;
	}

	public void setWednesdayInRestaurantGuestsNumber(BigDecimal wednesdayInRestaurantGuestsNumber) {
		this.wednesdayInRestaurantGuestsNumber = wednesdayInRestaurantGuestsNumber;
	}

	public BigDecimal getThursdaySalesAmount() {
		return thursdaySalesAmount;
	}

	public void setThursdaySalesAmount(BigDecimal thursdaySalesAmount) {
		this.thursdaySalesAmount = thursdaySalesAmount;
	}

	public BigDecimal getThursdayPurchasingGuestsNumber() {
		return thursdayPurchasingGuestsNumber;
	}

	public void setThursdayPurchasingGuestsNumber(BigDecimal thursdayPurchasingGuestsNumber) {
		this.thursdayPurchasingGuestsNumber = thursdayPurchasingGuestsNumber;
	}

	public BigDecimal getThursdayInRestaurantGuestsNumber() {
		return thursdayInRestaurantGuestsNumber;
	}

	public void setThursdayInRestaurantGuestsNumber(BigDecimal thursdayInRestaurantGuestsNumber) {
		this.thursdayInRestaurantGuestsNumber = thursdayInRestaurantGuestsNumber;
	}

	public BigDecimal getFridaySalesAmount() {
		return fridaySalesAmount;
	}

	public void setFridaySalesAmount(BigDecimal fridaySalesAmount) {
		this.fridaySalesAmount = fridaySalesAmount;
	}

	public BigDecimal getFridayPurchasingGuestsNumber() {
		return fridayPurchasingGuestsNumber;
	}

	public void setFridayPurchasingGuestsNumber(BigDecimal fridayPurchasingGuestsNumber) {
		this.fridayPurchasingGuestsNumber = fridayPurchasingGuestsNumber;
	}

	public BigDecimal getFridayInRestaurantGuestsNumber() {
		return fridayInRestaurantGuestsNumber;
	}

	public void setFridayInRestaurantGuestsNumber(BigDecimal fridayInRestaurantGuestsNumber) {
		this.fridayInRestaurantGuestsNumber = fridayInRestaurantGuestsNumber;
	}

	public BigDecimal getSaturdaySalesAmount() {
		return saturdaySalesAmount;
	}

	public void setSaturdaySalesAmount(BigDecimal saturdaySalesAmount) {
		this.saturdaySalesAmount = saturdaySalesAmount;
	}

	public BigDecimal getSaturdayPurchasingGuestsNumber() {
		return saturdayPurchasingGuestsNumber;
	}

	public void setSaturdayPurchasingGuestsNumber(BigDecimal saturdayPurchasingGuestsNumber) {
		this.saturdayPurchasingGuestsNumber = saturdayPurchasingGuestsNumber;
	}

	public BigDecimal getSaturdayInRestaurantGuestsNumber() {
		return saturdayInRestaurantGuestsNumber;
	}

	public void setSaturdayInRestaurantGuestsNumber(BigDecimal saturdayInRestaurantGuestsNumber) {
		this.saturdayInRestaurantGuestsNumber = saturdayInRestaurantGuestsNumber;
	}

	public BigDecimal getSundaySalesAmount() {
		return sundaySalesAmount;
	}

	public void setSundaySalesAmount(BigDecimal sundaySalesAmount) {
		this.sundaySalesAmount = sundaySalesAmount;
	}

	public BigDecimal getSundayPurchasingGuestsNumber() {
		return sundayPurchasingGuestsNumber;
	}

	public void setSundayPurchasingGuestsNumber(BigDecimal sundayPurchasingGuestsNumber) {
		this.sundayPurchasingGuestsNumber = sundayPurchasingGuestsNumber;
	}

	public BigDecimal getSundayInRestaurantGuestsNumber() {
		return sundayInRestaurantGuestsNumber;
	}

	public void setSundayInRestaurantGuestsNumber(BigDecimal sundayInRestaurantGuestsNumber) {
		this.sundayInRestaurantGuestsNumber = sundayInRestaurantGuestsNumber;
	}

	public Long getDispOrder() {
		return dispOrder;
	}

	public void setDispOrder(Long dispOrder) {
		this.dispOrder = dispOrder;
	}

	@Override
	protected ModelMapper<ISalesByTimeZone> modelMapper(SqlSession session) {
		return session.getMapper(ISalesByTimeZoneModelMapper.class);
	}

}

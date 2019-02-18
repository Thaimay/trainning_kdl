package jp.co.world.storedevelopment.sp.controller.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import jp.co.world.storedevelopment.model.ISalesAgencyCondition;

public class ISalesAgencyConditionRelationShopDTO implements DTO<ISalesAgencyCondition> {
	private SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

	private Long id;
	private String startDate;
	private String endDate;

	public ISalesAgencyConditionRelationShopDTO() {

	}

	public ISalesAgencyConditionRelationShopDTO(ISalesAgencyCondition model) {
		this.copyProperties(this, model);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public String getStartDateString() {
		try {
			if (startDate != null && !startDate.isEmpty()) {
				if(startDate.equals("99999999")) {
					startDate = "99991231";
				}
				return formatter.parse(startDate).toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
						.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			}
			return "";
		} catch (ParseException e) {
			return "";
		}
	}

	public String getEndDateString() {
		try {
			if (endDate != null && !endDate.isEmpty()) {
				if(endDate.equals("99999999")) {
					endDate = "99991231";
				}
				return formatter.parse(endDate).toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
						.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			}
			return "";
		} catch (ParseException e) {
			return "";
		}
	}

	@Override
	public ISalesAgencyCondition createModel() {
		return new ISalesAgencyCondition();
	}

}

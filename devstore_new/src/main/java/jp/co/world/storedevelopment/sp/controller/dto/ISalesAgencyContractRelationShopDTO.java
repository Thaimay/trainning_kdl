package jp.co.world.storedevelopment.sp.controller.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import jp.co.world.storedevelopment.model.ISalesAgencyContract;

public class ISalesAgencyContractRelationShopDTO implements DTO<ISalesAgencyContract> {
	private SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

	private Long id;
	private Long salesAgencyTargetId;
	private String startDate;
	private String endDate;

	public ISalesAgencyContractRelationShopDTO() {

	}

	public ISalesAgencyContractRelationShopDTO(ISalesAgencyContract model) {
		this.copyProperties(this, model);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSalesAgencyTargetId() {
		return salesAgencyTargetId;
	}

	public void setSalesAgencyTargetId(Long salesAgencyTargetId) {
		this.salesAgencyTargetId = salesAgencyTargetId;
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
	public ISalesAgencyContract createModel() {
		return new ISalesAgencyContract();
	}

}

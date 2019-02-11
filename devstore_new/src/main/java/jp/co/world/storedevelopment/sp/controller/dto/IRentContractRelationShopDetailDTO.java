package jp.co.world.storedevelopment.sp.controller.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import jp.co.world.storedevelopment.model.IContractType;
import jp.co.world.storedevelopment.model.IRentContract;
import jp.co.world.storedevelopment.model.MPeriod;
import jp.co.world.storedevelopment.model.mapper.repository.IContractTypeRepository;
import jp.co.world.storedevelopment.model.mapper.repository.MPeriodRepository;

public class IRentContractRelationShopDetailDTO implements DTO<IRentContract> {
	private SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

	private Long id;
	private Long shopId;
	private Long contractTypeId;
	private String contractTargetName;
	private String startDate;
	private String endDate;
	private String autoUpdatingFlag;

	public IRentContractRelationShopDetailDTO() {

	}

	public IRentContractRelationShopDetailDTO(IRentContract iRentContract) {
		this.copyProperties(this, iRentContract);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public Long getContractTypeId() {
		return contractTypeId;
	}

	public void setContractTypeId(Long contractTypeId) {
		this.contractTypeId = contractTypeId;
	}

	public String getContractTargetName() {
		return contractTargetName;
	}

	public void setContractTargetName(String contractTargetName) {
		this.contractTargetName = contractTargetName;
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

	public String getAutoUpdatingFlag() {
		return autoUpdatingFlag;
	}

	public void setAutoUpdatingFlag(String autoUpdatingFlag) {
		this.autoUpdatingFlag = autoUpdatingFlag;
	}

	public IContractTypeRelationShopDetailDTO getiContractType() {
		if (this.getContractTypeId() == null) {
			return null;
		}

		IContractType iContractType = new IContractTypeRepository().getByContractTypeId(this.getContractTypeId());
		return iContractType != null ? new IContractTypeRelationShopDetailDTO(iContractType) : null;
	}

	public String getPeriod() {
		try {
			Calendar start = Calendar.getInstance();
			Calendar end = Calendar.getInstance();
			start.setTime(formatter.parse(this.getStartDate()));
			start.add(Calendar.DATE, -1);
			end.setTime(formatter.parse(this.getEndDate()));

			int months = 0;

			while (start.before(end)) {
				start.add(Calendar.MONTH, 1);
				if (start.before(end)) {
					months++;
				}
			}

			return months + "ヶ月";
		} catch (ParseException e) {
			return "";
		}
	}

	public String getTerm() {
		// try {
		// Calendar start = Calendar.getInstance();
		// Calendar end = Calendar.getInstance();
		// start.setTime(formatter.parse("19580331"));
		// end.setTime(formatter.parse(this.getEndDate()));
		//
		// int years = 1;
		//
		// while (start.before(end)) {
		// start.add(Calendar.YEAR, 1);
		// if (start.before(end)) {
		// years++;
		// }
		// }
		//
		// return years + "期";
		// } catch (ParseException e) {
		// return "";
		// }
		String date = "";
		try {
			date = formatter.parse(endDate).toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
					.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		} catch (ParseException e) {
			return StringUtils.EMPTY;
		}

		if (!date.isEmpty()) {
			Optional<MPeriod> opt = new MPeriodRepository().getPeriod(date);
			if (opt.isPresent()) {
				return opt.get().getPeriod() + "期";
			}
		}
		return StringUtils.EMPTY;
	}
	
	public String getStartDateString() {
		try {
			if (startDate != null && !startDate.isEmpty()) {
				if(startDate.equals("99999999")) {
					startDate = "99991231";
				}
				return formatter.parse(startDate).toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
						.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
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
						.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
			}
			return "";
		} catch (ParseException e) {
			return "";
		}
	}

	public Boolean getAutoUpdate() {
		if (autoUpdatingFlag != null && !autoUpdatingFlag.isEmpty()) {
			return autoUpdatingFlag.toLowerCase().equals("y");
		}
		return null;
	}

	@Override
	public IRentContract createModel() {
		return new IRentContract();
	}

}

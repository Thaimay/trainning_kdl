package jp.co.world.storedevelopment.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.SubAccountModelMapper;

public class SubAccount extends ActiveModel<SubAccount> {

	private Long accountId = 0L;
	private String areaCode = "";
	private String employeeCode = "";
	private String incomeUnitCode = "";
	private String companyCode = "";
	private Boolean usePhone = false;

	@Override
	public String getTableName() {
		return super.getTableName();
	}

	private static String[] ignoreFields = new String[] { "department", "employeeCd", "areaCodes" };

	@Override
	protected String[] ignoreFields() {
		return ignoreFields;
	}

	public SubAccount() {
		//
	}

	public SubAccount(String employeeCode) {
		setEmployeeCode(employeeCode);
	}

	@Override
	protected ModelMapper<SubAccount> modelMapper(SqlSession session) {
		return session.getMapper(SubAccountModelMapper.class);
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getIncomeUnitCode() {
		return incomeUnitCode;
	}

	public void setIncomeUnitCode(String incomeUnitCode) {
		this.incomeUnitCode = incomeUnitCode;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public RoleFinder createRoleFinder() {
		return new RoleFinder(getEmployeeCode(), getIncomeUnitCode(), getCompanyCode());
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	
	public String areaCodes() {
		return getAreaCodes().stream().map(c -> {
			return String.format("'%s'", c);
		}).collect(Collectors.joining(","));
	}
	
	public String incomeUnitCodes() {
		return incomeUnitCodeList().stream().map(c -> {
			return String.format("'%s'", c);
		}).collect(Collectors.joining(","));
	}
	
	public String companyCodes() {
		return companyCodeList().stream().map(c -> {
			return String.format("'%s'", c);
		}).collect(Collectors.joining(","));
	}	
	
	public List<String> companyCodeList() {
		if (StringUtils.isBlank(companyCode)) {
			return Arrays.asList();
		}
		return Arrays.asList(StringUtils.split(companyCode, ",")).stream().map(String::trim).collect(Collectors.toList());		
	}

	public List<String> incomeUnitCodeList(){
		if (StringUtils.isBlank(incomeUnitCode)) {
			return Arrays.asList();
		}
		return Arrays.asList(StringUtils.split(incomeUnitCode, ",")).stream().map(String::trim).collect(Collectors.toList());
	}

	public List<String> getAreaCodes(){
		if (StringUtils.isBlank(areaCode)) {
			return Arrays.asList();
		}
		return Arrays.asList(StringUtils.split(areaCode, ",")).stream().map(String::trim).collect(Collectors.toList());
	}

	public boolean hasAreaCode(String areaCd) {
		return getAreaCodes().contains(areaCd);
	}

	public Boolean getUsePhone() {
		return usePhone;
	}

	public void setUsePhone(Boolean usePhone) {
		this.usePhone = usePhone;
	}
}
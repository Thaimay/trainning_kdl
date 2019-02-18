package jp.co.world.storedevelopment.model;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.RoleModelMapper;

public class Role extends ActiveModel<Role> {

	public static final Supplier<? extends Role> DEFULT = () -> {
		return new Role();
	};
	public static final String SESSION = "_ROLE_";

	private String name = "";
	private String description = "";
	private String companyCode = "";
	private String incomeUnitCode = "";
	private String employeeCode = "";
	private boolean onlyTargetArea = false;
	private boolean onlyMyCompany = false;
	private boolean onlyIncomeUnit = false;
	private String permission = "";

	public Role() {
		//
	}

	private static String[] ignoreFields = new String[] { "permissions", };

	@Override
	protected String[] ignoreFields() {
		return ignoreFields;
	}

	public Role(String name, String description, String companyCode, String incomeUnitCode, String employeeCode,
			boolean onlyTargetArea, boolean onlyMyCompany, boolean onlyIncomeUnit, String permission) {
		this.name = name;
		this.description = description;
		this.companyCode = companyCode;
		this.incomeUnitCode = incomeUnitCode;
		this.employeeCode = employeeCode;
		this.onlyTargetArea = onlyTargetArea;
		this.onlyMyCompany = onlyMyCompany;
		this.onlyIncomeUnit = onlyIncomeUnit;
		this.permission = permission;
	}

	@Override
	protected ModelMapper<Role> modelMapper(SqlSession session) {
		return session.getMapper(RoleModelMapper.class);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPermission() {
		return permission;
	}

	public List<String> getPermissions() {
		if (StringUtils.isBlank(getPermission())) {
			return Arrays.asList();
		}
		return Arrays.asList(StringUtils.split(getPermission(), ",")).stream().map(String::trim).collect(Collectors.toList());
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public boolean hasPermission(String target) {
		return getPermissions().contains(target);
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String corporationCode) {
		companyCode = corporationCode;
	}

	public String getIncomeUnitCode() {
		return incomeUnitCode;
	}

	public void setIncomeUnitCode(String incomeUnitCode) {
		this.incomeUnitCode = incomeUnitCode;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public boolean isOnlyTargetArea() {
		return onlyTargetArea;
	}

	public void setOnlyTargetArea(boolean onlyTargetArea) {
		this.onlyTargetArea = onlyTargetArea;
	}

	public boolean isOnlyMyCompany() {
		return onlyMyCompany;
	}

	public void setOnlyMyCompany(boolean onlyMyCompanytarget) {
		onlyMyCompany = onlyMyCompanytarget;
	}

	public boolean isOnlyIncomeUnit() {
		return onlyIncomeUnit;
	}

	public void setOnlyIncomeUnit(boolean onlyIncomeUnit) {
		this.onlyIncomeUnit = onlyIncomeUnit;
	}

	public boolean containEmployeeCode(String employeeCode) {
		return contain(getEmployeeCode(), employeeCode);
	}

	public boolean containImcomeUnitCode(String incomeUnitCode) {
		return contain(getIncomeUnitCode(), incomeUnitCode);
	}

	public boolean containCompanyCode(String companyCode) {
		return contain(getCompanyCode(), companyCode);
	}

	private boolean contain(String left, String right) {
		String[] codeList = left.split(",");
		return Arrays.asList(codeList).contains(right);
	}

}

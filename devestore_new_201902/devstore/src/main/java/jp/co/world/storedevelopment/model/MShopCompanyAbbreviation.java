package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.MShopCompanyAbbreviationModelMapper;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class MShopCompanyAbbreviation extends ActiveModel<MShopCompanyAbbreviation> {

	private String companyCd;
	private String abbreviatedCompanyCd;
	private String abbreviatedCompanyName;
	private String companyName;

	private String[] ignoreFields = new String[] {};

	@Override
	protected String[] ignoreFields() {
		return ignoreFields;
	}

	@Override
	protected ModelMapper<MShopCompanyAbbreviation> modelMapper(SqlSession session) {
		return session.getMapper(MShopCompanyAbbreviationModelMapper.class);
	}

	public String getCompanyCd() {
		return companyCd;
	}

	public void setCompanyCd(String companyCd) {
		this.companyCd = companyCd;
	}

	public String getAbbreviatedCompanyCd() {
		return abbreviatedCompanyCd;
	}

	public void setAbbreviatedCompanyCd(String abbreviatedCompanyCd) {
		this.abbreviatedCompanyCd = abbreviatedCompanyCd;
	}

	public String getAbbreviatedCompanyName() {
		return abbreviatedCompanyName;
	}

	public void setAbbreviatedCompanyName(String abbreviatedCompanyName) {
		this.abbreviatedCompanyName = abbreviatedCompanyName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

}

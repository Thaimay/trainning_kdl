package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.BrandModelMapper;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;

/**
 * @author hungdh
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class Brand extends ActiveModel<Brand> {

	private String integrationBu;
	private String groupBu;
	private String bu;
	private String profitUnit;
	private String blmg;
	private String ssv;
	private String name;

	@Override
	protected ModelMapper<Brand> modelMapper(SqlSession session) {
		return session.getMapper(BrandModelMapper.class);
	}

	public Brand() {
	}

	public Brand(String name) {
		this.setName(name);
		this.setCreatedAccountCode(new AccountRepository().getHead().get().getEmployeeCd());
		this.setUpdateAccountCode(new AccountRepository().getHead().get().getEmployeeCd());
	}

	public String getIntegrationBu() {
		return integrationBu;
	}

	public void setIntegrationBu(String integrationBu) {
		this.integrationBu = integrationBu;
	}

	public String getGroupBu() {
		return groupBu;
	}

	public void setGroupBu(String groupBu) {
		this.groupBu = groupBu;
	}

	public String getBu() {
		return bu;
	}

	public void setBu(String bu) {
		this.bu = bu;
	}

	public String getBlmg() {
		return blmg;
	}

	public void setBlmg(String blmg) {
		this.blmg = blmg;
	}

	public String getSsv() {
		return ssv;
	}

	public void setSsv(String ssv) {
		this.ssv = ssv;
	}

	public String getProfitUnit() {
		return profitUnit;
	}

	public void setProfitUnit(String profitUnit) {
		this.profitUnit = profitUnit;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

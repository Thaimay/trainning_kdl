package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.dev.RandomTestData;
import jp.co.world.storedevelopment.model.mapper.ParticipatingStoreCorporationModelMapper;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;

/**
 * @author tainm
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class ParticipatingStoreCorporation extends ActiveModel<ParticipatingStoreCorporation> {

	private String corporationDivision;
	private String corporationCd;
	private String corporationName;
	private String corporationNameKana;
	private String postalCode;
	private String address;
	private String representativePhoneNumber;
	private String representativeFaxNumber;
	private String corporationUrl;

	public ParticipatingStoreCorporation() {

	}

	public ParticipatingStoreCorporation(String name) {
		this.setCorporationDivision(RandomTestData.getString(4, false, true));
		this.setCorporationCd(RandomTestData.getString(4, false, true));
		this.setCorporationName(name);
		this.setCreatedAccountCode(new AccountRepository().getHead().get().getEmployeeCd());
		this.setUpdateAccountCode(new AccountRepository().getHead().get().getEmployeeCd());
	}

	public String getCorporationDivision() {
		return corporationDivision;
	}

	public void setCorporationDivision(String corporationDivision) {
		this.corporationDivision = corporationDivision;
	}

	public String getCorporationCd() {
		return corporationCd;
	}

	public void setCorporationCd(String corporationCd) {
		this.corporationCd = corporationCd;
	}

	public String getCorporationName() {
		return corporationName;
	}

	public void setCorporationName(String corporationName) {
		this.corporationName = corporationName;
	}

	public String getCorporationNameKana() {
		return corporationNameKana;
	}

	public void setCorporationNameKana(String corporationNameKana) {
		this.corporationNameKana = corporationNameKana;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRepresentativePhoneNumber() {
		return representativePhoneNumber;
	}

	public void setRepresentativePhoneNumber(String representativePhoneNumber) {
		this.representativePhoneNumber = representativePhoneNumber;
	}

	public String getRepresentativeFaxNumber() {
		return representativeFaxNumber;
	}

	public void setRepresentativeFaxNumber(String representativeFaxNumber) {
		this.representativeFaxNumber = representativeFaxNumber;
	}

	public String getCorporationUrl() {
		return corporationUrl;
	}

	public void setCorporationUrl(String corporationUrl) {
		this.corporationUrl = corporationUrl;
	}

	@Override
	protected ModelMapper<ParticipatingStoreCorporation> modelMapper(SqlSession session) {
		return session.getMapper(ParticipatingStoreCorporationModelMapper.class);
	}
}
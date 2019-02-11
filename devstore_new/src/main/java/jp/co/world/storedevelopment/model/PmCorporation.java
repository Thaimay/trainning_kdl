package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.PmCorporationModelMapper;

/**
 * @author tainm
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class PmCorporation extends ActiveModel<PmCorporation> {

	private String buildingCd;
	private Long corporationId;
	private String corporationName;

	@Override
	protected ModelMapper<PmCorporation> modelMapper(SqlSession session) {
		return session.getMapper(PmCorporationModelMapper.class);
	}

	public String getBuildingCd() {
		return buildingCd;
	}

	public void setBuildingCd(String buildingCd) {
		this.buildingCd = buildingCd;
	}

	public Long getCorporationId() {
		return corporationId;
	}

	public void setCorporationId(Long corporationId) {
		this.corporationId = corporationId;
	}

	public String getCorporationName() {
		return corporationName;
	}

	public void setCorporationName(String corporationName) {
		this.corporationName = corporationName;
	}

}

package jp.co.world.storedevelopment.model;

import java.util.Arrays;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.ProjectCategoryModelMapper;

/**
 * @author tienpv
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class ProjectCategory extends ActiveModel<ProjectCategory> {

	private String name;
	private String categoryName;

	public static final String storeOpenings = "STORE_OPENINGS";
	public static final String teycaExpiration = "TEYCA_EXPIRATION";
	public static final String teycaExpirationOther = "TEYCA_EXPIRATION_OTHER";
	public static final String rentExpiration = "RENT_EXPIRATION";
	public static final String termsNegotiation = "TERMS_NEGOTIATION";
	public static final String salesAgency = "SALES_AGENCY";
	public static final String externalSalesAgency = "EXTERNAL_SALES_AGENCY";
	public static final String foreignTakeActivity = "FOREIGN_TAKE_ACTIVITY";
	public static final String event = "EVENT";
	public static final String other = "OTHER";

	private String[] ignoreFields = new String[] {};

	@Override
	protected String[] ignoreFields() {
		return ignoreFields;
	}

	@Override
	protected ModelMapper<ProjectCategory> modelMapper(SqlSession session) {
		return session.getMapper(ProjectCategoryModelMapper.class);
	}
	
	public Boolean printLanding() {
		return Arrays.asList(
				teycaExpiration,
				teycaExpirationOther
				).contains(getCategoryName());
	}
	
	public Boolean requireStoreCopy() {
		return Arrays.asList(
				storeOpenings,
				teycaExpiration,
				teycaExpirationOther,
				rentExpiration,
				termsNegotiation,
				salesAgency
				).contains(getCategoryName());		
	}

	public Boolean requireStore() {
		return Arrays.asList(
				teycaExpiration,
				teycaExpirationOther,
				rentExpiration,
				termsNegotiation,
				salesAgency
				).contains(getCategoryName());
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}

package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.ProjectClassificationModelMapper;

/**
 * @author tienpv
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class ProjectClassification extends ActiveModel<ProjectClassification> {

	public static final String SUSPENSION = "SUSPENSION";
	public static final String LANDING = "LANDING";
	public static final String LANDING_POSSIBILITY = "LANDING_POSSIBILITY";
	public static final String PROJECT_PLAN = "PROJECT_PLAN";
	public static final String PROJECT_CATEGORY_SHORT_NAME = "PROJECT_CATEGORY_SHORT_NAME";
	public static final String PROJECT_PROGRESS_COMPANY_SEARCH_LABEL = "PROJECT_PROGRESS_COMPANY_SEARCH_LABEL";
	public static final String PROJECT_PROGRESS_NEGOTIATION_SEARCH_LABEL = "PROJECT_PROGRESS_NEGOTIATION_SEARCH_LABEL";
	public static final String PROJECT_REQUESTOR_REASON = "PROJECT_REQUESTOR_REASON";

	private String name;
	private String classification;

	private String[] ignoreFields = new String[] {};

	@Override
	protected String[] ignoreFields() {
		return ignoreFields;
	}

	@Override
	protected ModelMapper<ProjectClassification> modelMapper(SqlSession session) {
		return session.getMapper(ProjectClassificationModelMapper.class);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

}

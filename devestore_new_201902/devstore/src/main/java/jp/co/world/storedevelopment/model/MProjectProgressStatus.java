package jp.co.world.storedevelopment.model;

import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.MProjectProgressStatusModelMapper;
import jp.co.world.storedevelopment.model.mapper.repository.MProjectProgressStatusRepository;

/**
 * @author tienpv
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class MProjectProgressStatus extends ActiveModel<MProjectProgressStatus> {

	// category
	public static final String COMPANY = "COMPANY";
	public static final String NEGOTIATION = "NEGOTIATION";
	public static final String OTHER = "OTHER";

	// code
	//   category = COMPANY
	public static final String BEFORE_CONSIDERATION = "BEFORE_CONSIDERATION";
	public static final String UNDER_CONSIDERATION = "UNDER_CONSIDERATION";
	public static final String PROPERTY_CONSIDERATION_APPROVAL = "PROPERTY_CONSIDERATION_APPROVAL";
	public static final String MANAGEMENT_MEETING_APPROVAL = "MANAGEMENT_MEETING_APPROVAL";
	public static final String DECIDED = "DECIDED";

	//   category = NEGOTIATION
	public static final String BEFORE_NEGOTIATION = "BEFORE_NEGOTIATION";
	public static final String IN_NEGOTIATION = "IN_NEGOTIATION";
	public static final String AGREEMENT = "AGREEMENT";
	public static final String UNSATISFIED = "UNSATISFIED";

	//   category = OTHER
	public static final String NOT_INPUT = "NOT_INPUT";
	public static final String AREA_ENTERED = "AREA_ENTERED";
	public static final String SECTION_ENTERED = "SECTION_ENTERED";
	public static final String OPENING_STORE = "OPENING_STORE";

	private String category;
	private Long projectCategoryId;
	private Integer priority;
	private String code;
	private String name;

	private String[] ignoreFields = new String[] {"COMPANY", "NEGOTIATION", "OTHER"};

	@Override
	protected String[] ignoreFields() {
		return ignoreFields;
	}
	
	public static Optional<MProjectProgressStatus> companyStatus(Long categoryId, String code) {
		return new MProjectProgressStatusRepository().getCurrentProgress(COMPANY, categoryId, code);
	}

	public static Optional<MProjectProgressStatus> negotiationStatus(Long categoryId, String code) {
		return new MProjectProgressStatusRepository().getCurrentProgress(NEGOTIATION, categoryId, code);
	}

	public static Optional<MProjectProgressStatus> otherStatus(Long categoryId, String code) {
		return new MProjectProgressStatusRepository().getCurrentProgress(OTHER, categoryId, code);
	}

	@Override
	protected ModelMapper<MProjectProgressStatus> modelMapper(SqlSession session) {
		return session.getMapper(MProjectProgressStatusModelMapper.class);
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Long getProjectCategoryId() {
		return projectCategoryId;
	}

	public void setProjectCategoryId(Long projectCategoryId) {
		this.projectCategoryId = projectCategoryId;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

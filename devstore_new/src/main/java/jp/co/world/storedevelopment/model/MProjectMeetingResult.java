package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.MProjectMeetingResultModelMapper;

/**
 * @author tienpv
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({
	"tableName", "priority", "corporationGroup", "createdAccountCode", "updateAccountCode", "createdDatetime", "updateDatetime", "isDeleted"
	})
public class MProjectMeetingResult extends ActiveModel<MProjectMeetingResult> {

	private String name;
	private Integer priority;


	private String[] ignoreFields = new String[] {"COMPANY", "NEGOTIATION", "OTHER"};

	@Override
	protected String[] ignoreFields() {
		return ignoreFields;
	}

	@Override
	protected ModelMapper<MProjectMeetingResult> modelMapper(SqlSession session) {
		return session.getMapper(MProjectMeetingResultModelMapper.class);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}
}

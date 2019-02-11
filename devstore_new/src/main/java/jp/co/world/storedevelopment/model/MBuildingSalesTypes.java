package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.MBuildingSalesTypesModelMapper;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class MBuildingSalesTypes extends ActiveModel<MBuildingSalesTypes> {

	private String value;

	public MBuildingSalesTypes() {

	}

	public MBuildingSalesTypes(String name) {
		this.setValue(name);
		this.setCreatedAccountCode(new AccountRepository().getHead().get().getEmployeeCd());
		this.setUpdateAccountCode(new AccountRepository().getHead().get().getEmployeeCd());
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	protected ModelMapper<MBuildingSalesTypes> modelMapper(SqlSession session) {
		return session.getMapper(MBuildingSalesTypesModelMapper.class);
	}

}

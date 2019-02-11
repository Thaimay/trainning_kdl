package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.CompetitionModelMapper;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;

/**
 * @author tainm
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class Competition extends ActiveModel<Competition> {

	private String name;
	
	public Competition() {

	}

	public Competition(String name) {
		this.setName(name);
		this.setCreatedAccountCode(new AccountRepository().getHead().get().getEmployeeCd());
		this.setUpdateAccountCode(new AccountRepository().getHead().get().getEmployeeCd());
	}

	private String[] ignoreFields = new String[] {};

	@Override
	protected String[] ignoreFields() {
		return ignoreFields;
	}

	public void setIgnoreFields(String[] ignoreFields) {
		this.ignoreFields = ignoreFields;
	}

	
	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	protected ModelMapper<Competition> modelMapper(SqlSession session) {
		return session.getMapper(CompetitionModelMapper.class);
	}

}

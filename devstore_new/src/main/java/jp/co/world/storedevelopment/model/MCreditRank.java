package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.MCreditRankModelMapper;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ICorporationRepository;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class MCreditRank extends ActiveModel<MCreditRank> {

	private String corporationCd;
	private String creditRank;

	public MCreditRank() {

	}

	public MCreditRank(String name) {
		this.setCreditRank(name);
		this.setCorporationCd(new ICorporationRepository().getHead().get().getCorporationCd());
		this.setCreatedAccountCode(new AccountRepository().getHead().get().getEmployeeCd());
		this.setUpdateAccountCode(new AccountRepository().getHead().get().getEmployeeCd());
	}

	public String getCorporationCd() {
		return corporationCd;
	}

	public void setCorporationCd(String corporationCd) {
		this.corporationCd = corporationCd;
	}

	public String getCreditRank() {
		return creditRank;
	}

	public void setCreditRank(String creditRank) {
		this.creditRank = creditRank;
	}

	@Override
	protected ModelMapper<MCreditRank> modelMapper(SqlSession session) {
		return session.getMapper(MCreditRankModelMapper.class);
	}

}

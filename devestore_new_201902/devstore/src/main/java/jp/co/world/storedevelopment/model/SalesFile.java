package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;
import org.springframework.web.multipart.MultipartFile;

import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.Application;
import jp.co.world.storedevelopment.model.mapper.SalesFileModelMapper;
import jp.co.world.storedevelopment.model.value.FileDivision;

public class SalesFile extends File<SalesFile> {
	private String division;

	public SalesFile() {
	}

	public SalesFile(MultipartFile file, String division, Account account) {
		super(file, account);
		setDivision(division);
	}

	@Override
	public String filePrefix() {
		return "sales_";
	}

	@Override
	public String basePath() {
		return Application.resourcePath() + "sales/";
	}

	@Override
	public String urlPath() {
		return "sales/";
	}

	@Override
	protected ModelMapper<SalesFile> modelMapper(SqlSession session) {
		return session.getMapper(SalesFileModelMapper.class);
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String divisionValue() {
		return FileDivision.toLavel(getDivision());
	}

}

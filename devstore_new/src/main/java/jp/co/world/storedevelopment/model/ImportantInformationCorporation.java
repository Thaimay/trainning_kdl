package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.ImportantInformationCorporationModelMapper;
import jp.co.world.storedevelopment.model.mapper.repository.ICorporationRepository;

public class ImportantInformationCorporation extends ActiveModel<ImportantInformationCorporation> {
	private Long importantInformationId;
	private Long corporationId;
	private String unmanagedName;

	public ImportantInformationCorporation() {
	}

	public ImportantInformationCorporation(ImportantInformation in, ICorporation c) {
		setImportantInformationId(in.getId());
		setCorporationId(c.getId());
	}

	public ImportantInformationCorporation(ImportantInformation in, String name) {
		setImportantInformationId(in.getId());
		setUnmanagedName(name);
	}

	@Override
	protected ModelMapper<ImportantInformationCorporation> modelMapper(SqlSession session) {
		return session.getMapper(ImportantInformationCorporationModelMapper.class);
	}

	public Boolean isUnmanaged() {
		return getCorporationId() == null;
	}

	public Long getImportantInformationId() {
		return importantInformationId;
	}

	public void setImportantInformationId(Long importantInformationId) {
		this.importantInformationId = importantInformationId;
	}

	public Long getCorporationId() {
		return corporationId;
	}

	public void setCorporationId(Long corporationId) {
		this.corporationId = corporationId;
	}

	public String getUnmanagedName() {
		return unmanagedName;
	}

	public void setUnmanagedName(String unmanagedName) {
		this.unmanagedName = unmanagedName;
	}

	public String corporationName() {
		if (isUnmanaged()) {
			return getUnmanagedName();
		} else {
			return findCorporation().getCorporationName();
		}
	}

	public ICorporation findCorporation() {
		return new ICorporationRepository().findById(getCorporationId()).orElseGet(() -> {
			throw new IllegalArgumentException("存在しない法人です");
		});
	}
}

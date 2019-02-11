package jp.co.world.storedevelopment.sp.controller.dto;

import java.util.Optional;

import com.world.storedevelopment.utils.ZenkakuUtils;

import jp.co.world.storedevelopment.model.IBusinessCard;
import jp.co.world.storedevelopment.model.ICorporation;
import jp.co.world.storedevelopment.model.Keyman;
import jp.co.world.storedevelopment.model.mapper.repository.ICorporationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.KeymanRepository;

public class IBusinessCardListDTO implements DTO<IBusinessCard> {

	private Long id;
	private String name;
	private String displayName;
	private String departmentName;
	private String positionName;

	private Long keymanId;

	public IBusinessCardListDTO() {

	}

	public IBusinessCardListDTO(IBusinessCard iBusinessCard) {
		this.copyProperties(this, iBusinessCard);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return ZenkakuUtils.toZenkaku(name);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public Long getKeymanId() {
		return keymanId;
	}

	public void setKeymanId(Long keymanId) {
		this.keymanId = keymanId;
	}

	private Keyman getKeyman() {
		Optional<Keyman> keyman = new KeymanRepository().findById(this.getKeymanId());
		return keyman.isPresent() ? keyman.get() : null;
	}

	public String getCorporationGroup() {
		return this.getKeyman() != null ? this.getKeyman().getCorporationGroup() : "";
	}

	public Long getCorporationId() {
		return this.getKeyman() != null ? this.getKeyman().getCorporationId() : null;
	}

	public String getCorporationName() {
		if (getCorporationId() != null && getCorporationId() > 0) {
			Optional<ICorporation> iCorporation = new ICorporationRepository().findById(getCorporationId());
			return iCorporation.isPresent() ? iCorporation.get().getCorporationName() : "";
		}
		return "";
	}

	@Override
	public IBusinessCard createModel() {
		return new IBusinessCard();
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

}

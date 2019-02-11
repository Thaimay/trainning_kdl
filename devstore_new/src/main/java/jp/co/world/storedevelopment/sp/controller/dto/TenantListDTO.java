package jp.co.world.storedevelopment.sp.controller.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.world.storedevelopment.utils.ZenkakuUtils;

import jp.co.world.storedevelopment.model.Tenant;

public class TenantListDTO implements DTO<Tenant> {

	private Long id;
	private String name;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime createdDatetime = LocalDateTime.now();

	@Override
	public Tenant createModel() {
		return new Tenant();
	}

	public TenantListDTO() {
		//
	}

	public TenantListDTO(Tenant tn) {
		this.copyProperties(this, tn);
	}

	public String getName() {
		return ZenkakuUtils.toZenkaku(name);
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getCreatedDatetime() {
		return createdDatetime;
	}

	public void setCreatedDatetime(LocalDateTime createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

}

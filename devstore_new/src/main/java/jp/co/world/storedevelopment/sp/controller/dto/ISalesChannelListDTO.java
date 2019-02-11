package jp.co.world.storedevelopment.sp.controller.dto;

import jp.co.world.storedevelopment.model.ISalesChannel;

public class ISalesChannelListDTO implements DTO<ISalesChannel> {

	private Long id;
	private String salesChannelName;

	@Override
	public ISalesChannel createModel() {
		return new ISalesChannel();
	}

	public ISalesChannelListDTO() {
		//
	}

	public ISalesChannelListDTO(ISalesChannel is) {
		this.copyProperties(this, is);
	}

	public String getSalesChannelName() {
		return salesChannelName;
	}

	public void setSalesChannelName(String salesChannelName) {
		this.salesChannelName = salesChannelName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return this.getSalesChannelName();
	}

}

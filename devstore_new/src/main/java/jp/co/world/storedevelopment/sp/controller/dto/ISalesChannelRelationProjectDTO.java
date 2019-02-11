package jp.co.world.storedevelopment.sp.controller.dto;

import jp.co.world.storedevelopment.model.ISalesChannel;

public class ISalesChannelRelationProjectDTO implements DTO<ISalesChannel> {

	private Long id;
	private String salesChannelName;

	public ISalesChannelRelationProjectDTO() {

	}

	public ISalesChannelRelationProjectDTO(ISalesChannel iSalesChannel) {
		this.copyProperties(this, iSalesChannel);
	}

	@Override
	public ISalesChannel createModel() {
		return new ISalesChannel();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSalesChannelName() {
		return salesChannelName;
	}

	public void setSalesChannelName(String salesChannelName) {
		this.salesChannelName = salesChannelName;
	}

	public String getName() {
		return this.getSalesChannelName();
	}

}

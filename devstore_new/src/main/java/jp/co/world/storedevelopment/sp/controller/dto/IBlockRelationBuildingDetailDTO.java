package jp.co.world.storedevelopment.sp.controller.dto;

import jp.co.world.storedevelopment.model.IBlock;

public class IBlockRelationBuildingDetailDTO implements DTO<IBlock> {

	private Long id;
	private String blockName;

	public IBlockRelationBuildingDetailDTO() {

	}

	public IBlockRelationBuildingDetailDTO(IBlock iBlock) {
		this.copyProperties(this, iBlock);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

	public String getName() {
		return this.getBlockName();
	}

	@Override
	public IBlock createModel() {
		return new IBlock();
	}

}

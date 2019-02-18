package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.IBusinessCardBatchModelMapper;

public class IBusinessCardBatch extends ActiveModel<IBusinessCardBatch> {

	private Boolean isSuccess;
	private String  targetDate = "";
	private String  recoveredDate = "";
	private Integer total = 0;
	private Integer createCount = 0;
	private Integer updateCount = 0;
	
	/**
	 * 
	 * @return isSuccess
	 */
	public Boolean getIsSuccess() {
		return isSuccess;
	}

	/**
	 * 
	 * @param isSuccess
	 */
	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	/**
	 * 
	 * @return recovedDate
	 */
	public String getTargetDate() {
		return targetDate;
	}

	/**
	 * 
	 * @param executionDate
	 */
	public void setTargetDate(String targetDate) {
		this.targetDate = targetDate;
	}

	/**
	 * 
	 * @return recoveredDate
	 */
	public String getRecoveredDate() {
		return recoveredDate;
	}

	/**
	 * 
	 * @param recoveredDate
	 */
	public void setRecoveredDate(String recoveredDate) {
		this.recoveredDate = recoveredDate;
	}

	/**
	 * 
	 * @return
	 */
	public Integer getTotal() {
		return total;
	}

	/**
	 * 
	 * @param total
	 */
	public void setTotal(Integer total) {
		this.total = total;
	}

	/**
	 * 
	 * @return
	 */
	public Integer getCreateCount() {
		return createCount;
	}

	/**
	 * 
	 * @param createCount
	 */
	public void setCreateCount(Integer createCount) {
		this.createCount = createCount;
	}

	/**
	 * 
	 * @return
	 */
	public Integer getUpdateCount() {
		return updateCount;
	}

	/**
	 * 
	 * @param updateCount
	 */
	public void setUpdateCount(Integer updateCount) {
		this.updateCount = updateCount;
	}

	@Override
	protected ModelMapper<IBusinessCardBatch> modelMapper(SqlSession session) {
		return session.getMapper(IBusinessCardBatchModelMapper.class);
	}

}

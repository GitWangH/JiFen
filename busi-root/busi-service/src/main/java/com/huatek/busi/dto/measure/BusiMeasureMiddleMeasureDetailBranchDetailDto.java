package com.huatek.busi.dto.measure;


/**
 * 代码自动生成model类.
 * 
 * @ClassName: BusiMeasureMiddleMeasureDetailBranchDetailDto
 * @Description: 中间计量分部分项明细
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @date: 2017-12-05 10:47:27
 * @version: 1.0
 */
public class BusiMeasureMiddleMeasureDetailBranchDetailDto {

	private Long id;
	private Long middleMeasureDetailId;// 中间计量明细ID
	private Long tendersContractDetailId;// 合同清单ID

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public Long getMiddleMeasureDetailId() {
		return middleMeasureDetailId;
	}

	public void setMiddleMeasureDetailId(Long middleMeasureDetailId) {
		this.middleMeasureDetailId = middleMeasureDetailId;
	}

	public Long getTendersContractDetailId() {
		return tendersContractDetailId;
	}

	public void setTendersContractDetailId(Long tendersContractDetailId) {
		this.tendersContractDetailId = tendersContractDetailId;
	}

}

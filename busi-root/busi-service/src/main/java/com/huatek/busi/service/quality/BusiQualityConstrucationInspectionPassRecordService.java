package com.huatek.busi.service.quality;

import java.util.List;

import com.huatek.busi.dto.quality.BusiQualityConstrucationInspectionPassRecordDto;
import com.huatek.busi.dto.quality.BusiQualityConstructionInspectionDto;


/**
 * 施工报检审批通过记录dao
 * @author rocky_wei
 *
 */
public interface BusiQualityConstrucationInspectionPassRecordService {
	/**
	 * 保存施工报检审批通过记录
	 * @param dto
	 */	
	void addConstrucationInspectionPassRecord(BusiQualityConstrucationInspectionPassRecordDto dto);
	
	/**
	 * 通过施工报检id查询施工报检审批通过记录
	 * @param constrctionId
	 * @return
	 */
	List<BusiQualityConstrucationInspectionPassRecordDto> getConstrucationInspectionPassRecords(Long constrctionId);

}

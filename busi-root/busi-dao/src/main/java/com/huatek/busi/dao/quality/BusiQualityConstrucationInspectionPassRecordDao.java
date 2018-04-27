package com.huatek.busi.dao.quality;

import java.util.List;

import com.huatek.busi.model.quality.BusiQualityConstrucationInspectionPassRecord;
import com.huatek.busi.model.quality.BusiQualityConstructionInspection;

/**
 * 施工报检审批通过记录dao
 * @author rocky_wei
 *
 */
public interface BusiQualityConstrucationInspectionPassRecordDao {
	/**
	 * 保存施工报检审批通过记录
	 * @param dto
	 */	
	void saveConstrucationInspectionPassRecord(BusiQualityConstrucationInspectionPassRecord construcationInspectionPassRecord);
	
	/**
	 * 通过施工报检id查询施工报检审批通过记录
	 * @param constrctionId
	 * @return
	 */
	List<BusiQualityConstrucationInspectionPassRecord> findConstrucationInspectionPassRecords(Long constrctionId);
}

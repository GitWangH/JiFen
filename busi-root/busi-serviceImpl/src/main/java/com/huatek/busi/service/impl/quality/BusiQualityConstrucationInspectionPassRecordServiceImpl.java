package com.huatek.busi.service.impl.quality;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.quality.BusiQualityConstrucationInspectionPassRecordDao;
import com.huatek.busi.dto.quality.BusiQualityConstrucationInspectionPassRecordDto;
import com.huatek.busi.dto.quality.BusiQualityConstructionInspectionDto;
import com.huatek.busi.model.quality.BusiQualityConstrucationInspectionPassRecord;
import com.huatek.busi.model.quality.BusiQualityConstructionInspection;
import com.huatek.busi.service.quality.BusiQualityConstrucationInspectionPassRecordService;
import com.huatek.frame.authority.util.ThreadLocalClient;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.beancopy.ConvertParam;
import com.huatek.frame.session.data.UserInfo;

/**
 * 施工报检审批通过记录service
 * @author rocky_wei
 *
 */
@Service("busiQualityConstrucationInspectionPassRecordServiceImpl")
@Transactional
public class BusiQualityConstrucationInspectionPassRecordServiceImpl implements BusiQualityConstrucationInspectionPassRecordService {

	@Autowired
	private BusiQualityConstrucationInspectionPassRecordDao construcationInspectionPassRecordDao;
	
	@Override
	public void addConstrucationInspectionPassRecord(BusiQualityConstrucationInspectionPassRecordDto dto) {
		BusiQualityConstrucationInspectionPassRecord construcationInspectionPassRecord = BeanCopy.getInstance()//
						.convert(dto, BusiQualityConstrucationInspectionPassRecord.class);
		beforeSave(construcationInspectionPassRecord, dto);
		construcationInspectionPassRecordDao.saveConstrucationInspectionPassRecord(construcationInspectionPassRecord);
	}

	@Override
	public List<BusiQualityConstrucationInspectionPassRecordDto> getConstrucationInspectionPassRecords(Long constrctionId) {
		List<BusiQualityConstrucationInspectionPassRecord> construcationInspectionPassRecords = construcationInspectionPassRecordDao//
						.findConstrucationInspectionPassRecords(constrctionId);
		return BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd")//
				.convertList(construcationInspectionPassRecords, BusiQualityConstrucationInspectionPassRecordDto.class);
	}

	private void beforeSave(BusiQualityConstrucationInspectionPassRecord entity,BusiQualityConstrucationInspectionPassRecordDto dto){
		UserInfo operator = ThreadLocalClient.get().getOperator();
		entity.setApproverId(operator.getId());
		entity.setApproverName(operator.getUserName());
		entity.setApproverTime(new Date());
	}

}

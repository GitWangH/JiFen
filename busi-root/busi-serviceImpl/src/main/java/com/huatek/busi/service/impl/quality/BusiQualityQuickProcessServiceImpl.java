package com.huatek.busi.service.impl.quality;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.quality.BusiQualityQuickProcessingDao;
import com.huatek.busi.dto.quality.BusiQualityQuickProcessingDto;
import com.huatek.busi.dto.quality.BusiQualityRectificationDto;
import com.huatek.busi.model.quality.BusiQualityQuickProcessing;
import com.huatek.busi.service.quality.BusiQualityQuickProcessService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.beancopy.ConvertParam;
import com.huatek.frame.core.exception.BusinessCheckException;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

@Service("busiQualityQuickProcessServiceImpl")
@Transactional
public class BusiQualityQuickProcessServiceImpl implements BusiQualityQuickProcessService {

	@Autowired
	private BusiQualityQuickProcessingDao busiQualityQuickProcessingDao;
	
	@Override
	public DataPage<BusiQualityQuickProcessingDto> getAllBusiQualityQuickProcessPage(
			QueryPage queryPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteBusiQualityRectification(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public BusiQualityQuickProcessingDto getBusiQualityQuickProcessingDtoById(Long id) throws BusinessCheckException {
		BusiQualityQuickProcessing quickProcess = busiQualityQuickProcessingDao.findBusiQualityQuickProcessingById(id);
		BusiQualityQuickProcessingDto quickProcessDto = BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd hh:mm:ss")//
				.addFieldMap("orgId", "org").convert(quickProcess, BusiQualityQuickProcessingDto.class);
		return quickProcessDto;
	}

	@Override
	public void updateBusiQualityRectification(Long id,
			BusiQualityQuickProcessingDto entityDto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveBusiQualityQuickProcessDto(
			BusiQualityQuickProcessingDto entityDto) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<BusiQualityRectificationDto> getAllBusiQualityQuickProcessDto() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BusiQualityQuickProcessingDto getBusiQualityQuickProcessByCode(String rectificationCode) throws BusinessCheckException {
		BusiQualityQuickProcessing quickProcess = busiQualityQuickProcessingDao.findBusiQualityQuickProcessingByCondition("quickProcessCode", rectificationCode);
		if(quickProcess!=null){
			BusiQualityQuickProcessingDto quickProcessDto = BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd hh:mm:ss")//
					.convert(quickProcess, BusiQualityQuickProcessingDto.class);
			quickProcessDto.setOrgName("");
			return quickProcessDto;
		}
		return null;
	}

}

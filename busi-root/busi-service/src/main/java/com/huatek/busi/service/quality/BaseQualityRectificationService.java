package com.huatek.busi.service.quality;

import com.huatek.busi.dto.quality.BusiQualityQuickProcessingDto;
import com.huatek.busi.dto.quality.BusiQualityRectificationDto;

/**
 * 质量快捷处理、整改单处理
 * @author rocky_wei
 *
 */
public interface BaseQualityRectificationService {

	/**
	 * 整改单处理
	 * @param rectificationDto整改单dto
	 * @return
	 */
	BusiQualityRectificationDto saveQualityRectification(BusiQualityRectificationDto rectificationDto);
	
	/**
	 * 快捷处理
	 * @param rectificationDto 整改单dto
	 * @return
	 */
	BusiQualityQuickProcessingDto saveQualityQuickProcess(BusiQualityQuickProcessingDto quickProcessingDto);
}

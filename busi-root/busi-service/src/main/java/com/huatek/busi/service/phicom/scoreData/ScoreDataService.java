package com.huatek.busi.service.phicom.scoreData;

import com.huatek.busi.dto.phicom.coupons.PhiCouponsDetailDto;
import com.huatek.busi.dto.phicom.coupons.PhiThirdPartyCouponsDetailDto;
import com.huatek.busi.dto.phicom.score.PhiScoreFlowDto;
import com.huatek.busi.dto.phicom.scoreData.ScoreCoupsDataDto;
import com.huatek.busi.dto.phicom.scoreData.ScoreDataDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface ScoreDataService {

	
	/**
	 * 数据管理--积分概括报表 饼图
	 * @param type
	 * @return
	 */
	ScoreDataDto getPhiScoreData(String type);
	
	/**
	 *数据管理--积分概括报表   列表
	 * @param queryPage
	 * @return
	 */
    DataPage<PhiScoreFlowDto> getAllPhiScoreFlowPage(QueryPage queryPage);
    
    /**
     * 数据管理--积分兑换卷报表   列表
     * @param queryPage
     * @return
     */
    DataPage<ScoreCoupsDataDto> getAllPhiScoreCoups(QueryPage queryPage);
    
    /**
     * 数据管理--积分兑换卷报表   优惠券明细列表
     * @param queryPage
     * @return
     */
    public DataPage<PhiCouponsDetailDto> getAllPhiScoreCoupsDetail(QueryPage queryPage,Long cpnsWayId);
    
    /**
     * 数据管理--积分兑换卷报表   第三方优惠券明细列表
     * @param queryPage
     * @return
     */
    public DataPage<PhiThirdPartyCouponsDetailDto> getAllPhiScoreCoupsThirdDetail(QueryPage queryPage,String cpnsWayId);
}

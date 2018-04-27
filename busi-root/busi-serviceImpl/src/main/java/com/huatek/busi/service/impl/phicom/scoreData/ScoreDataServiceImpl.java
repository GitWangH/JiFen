package com.huatek.busi.service.impl.phicom.scoreData;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.constants.Constant;
import com.huatek.busi.dao.phicom.coupons.PhiCouponsDetailDao;
import com.huatek.busi.dao.phicom.coupons.PhiThirdPartyCouponsDetailDao;
import com.huatek.busi.dao.phicom.score.PhiScoreFlowDao;
import com.huatek.busi.dao.phicom.scoreData.PhiScoreDataDao;
import com.huatek.busi.dto.phicom.coupons.PhiCouponsDetailDto;
import com.huatek.busi.dto.phicom.coupons.PhiThirdPartyCouponsDetailDto;
import com.huatek.busi.dto.phicom.score.PhiScoreFlowDto;
import com.huatek.busi.dto.phicom.scoreData.ScoreCoupsDataDto;
import com.huatek.busi.dto.phicom.scoreData.ScoreDataDto;
import com.huatek.busi.dto.phicom.scoreData.ScoreSeriesDataDto;
import com.huatek.busi.model.phicom.coupons.PhiCouponsDetail;
import com.huatek.busi.model.phicom.coupons.PhiThirdPartyCouponsDetail;
import com.huatek.busi.model.phicom.score.PhiScoreFlow;
import com.huatek.busi.service.phicom.scoreData.ScoreDataService;
import com.huatek.cmd.service.ComonRpcService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.util.DTOUtils;
@Service("scoreDataServiceImpl")
@Transactional
public class ScoreDataServiceImpl implements ScoreDataService {

	@Autowired
	private PhiScoreDataDao  phiScoreDataDao;
	
	@Autowired
	private PhiScoreFlowDao phiScoreFlowDao;
	
	@Autowired
	private PhiCouponsDetailDao phiCouponsDetailDao;
	
	@Autowired
	private PhiThirdPartyCouponsDetailDao phiThirdPartyCouponsDetailDao;

	@Autowired
	private ComonRpcService comonRpcService;
	/******************      积分概括报表       ***********************/
	/**
	 * 饼图
	 */
	@Override
	public ScoreDataDto getPhiScoreData(String type) {
		ScoreDataDto dto = new ScoreDataDto();
		//获得
		List<String> gainLegendData = new ArrayList<String>();
		List<ScoreSeriesDataDto> gainSeriesData= new ArrayList<ScoreSeriesDataDto>();
		//消耗
		List<String> consumeLegendData=new ArrayList<String>();
		List<ScoreSeriesDataDto> consumeSeriesData=new ArrayList<ScoreSeriesDataDto>();
		String gain="0";
		String consume="0";
		//获得 消耗总数
		List<Object> list=phiScoreDataDao.getPhiScoreFlowTotalByScoreType(type);
		if(list!=null&&list.size()>0){
			
			for(Object object:list){
				Map map = (Map) object;
				if(Constant.SCORE_TYPE_GAIN.equals(map.get("scoreType").toString())){
					gain=map.get("scoreTotal").toString();
				}
				if(Constant.SCORE_TYPE_CONSUME.equals(map.get("scoreType").toString())){
					consume=map.get("scoreTotal").toString();
				}
			}
	
			// 获得 消耗 明细
			List<Object> serireList = phiScoreDataDao.getPhiScoreFlowTotalByScoreTypeAndTaskId(type);
			if(serireList!=null&&serireList.size()>0){
				String taskName="其他";
				Integer otherGainScore=0;
				Integer otherConsume=0;
				for(Object object:serireList){
					if(object!=null){
						Map map = (Map) object;
						if(Constant.SCORE_TYPE_GAIN.equals(map.get("scoreType").toString())){
							double seriesTotal=Double.valueOf(map.get("scoreTotal").toString()).doubleValue();
							double gainTotal=Double.valueOf(gain).doubleValue();
							double than = divide(seriesTotal,gainTotal,2);
							BigDecimal data1 = new BigDecimal(than); 
							BigDecimal data2 = new BigDecimal("0.05"); 
							if(data1.compareTo(data2)<0){
								otherGainScore=otherGainScore+Integer.valueOf(map.get("scoreTotal").toString());
							}else{
								ScoreSeriesDataDto seriesDto = new ScoreSeriesDataDto();
								seriesDto.setName(null == map.get("taskName")? "" :map.get("taskName").toString());
								seriesDto.setValue(null == map.get("scoreTotal") ? "" :map.get("scoreTotal").toString());
								gainLegendData.add(null == map.get("taskName") ? "" :map.get("taskName").toString());
								gainSeriesData.add(seriesDto);
							}
						}
						if(Constant.SCORE_TYPE_CONSUME.equals(map.get("scoreType").toString())){
							double seriesTotal=Double.valueOf(map.get("scoreTotal").toString()).doubleValue();
							double gainTotal=Double.valueOf(gain).doubleValue();
							double than = divide(seriesTotal,gainTotal,2);
							BigDecimal data1 = new BigDecimal(than); 
							BigDecimal data2 = new BigDecimal("0.05"); 
							if(data1.compareTo(data2)<0){
								otherConsume=otherConsume+Integer.valueOf(map.get("scoreTotal").toString());
							}else{
								ScoreSeriesDataDto seriesDto = new ScoreSeriesDataDto();
								seriesDto.setName(null == map.get("taskName")? "" :map.get("taskName").toString());
								seriesDto.setValue(null == map.get("scoreTotal") ? "" :map.get("scoreTotal").toString());
								consumeLegendData.add(null == map.get("taskName") ? "" :map.get("taskName").toString());
								consumeSeriesData.add(seriesDto);
							}
						}
					}
				}
				if(otherGainScore>0){
					ScoreSeriesDataDto seriesDto = new ScoreSeriesDataDto();
					seriesDto.setName(taskName);
					seriesDto.setValue(String.valueOf(otherGainScore));
					gainLegendData.add(taskName);
					gainSeriesData.add(seriesDto);
				}
				if(otherConsume>0){
					ScoreSeriesDataDto seriesDto = new ScoreSeriesDataDto();
					seriesDto.setName(taskName);
					seriesDto.setValue(String.valueOf(otherConsume));
					consumeLegendData.add(taskName);
					consumeSeriesData.add(seriesDto);
				}
			}
		}
		dto.setConsumeTotal(consume);
		dto.setGainTotal(gain);
		if(consumeLegendData!=null&consumeLegendData.size()>0){
			String[] strings = new String[consumeLegendData.size()];
		    dto.setConsumeLegendData(consumeLegendData.toArray(strings));
		}else{
			dto.setConsumeLegendData(new String[0]);
		}
		dto.setConsumeSeriesData(consumeSeriesData);
		if(gainLegendData!=null&gainLegendData.size()>0){
			String[] strings = new String[gainLegendData.size()];
		    dto.setGainLegendData(gainLegendData.toArray(strings));
		}else{
			dto.setGainLegendData(new String[0]);
		}
		dto.setGainSeriesData(gainSeriesData);
		return dto;
	}
	
	/**
	 * 列表
	 */
	@Override
	public DataPage<PhiScoreFlowDto> getAllPhiScoreFlowPage(QueryPage queryPage) {
		DataPage<PhiScoreFlow> dataPage = phiScoreFlowDao.getAllPhiScoreFlow(queryPage);
		List<PhiScoreFlow> modelLlist= dataPage.getContent();
		List<PhiScoreFlowDto> dtoList= new ArrayList<PhiScoreFlowDto>();
		if(modelLlist!=null&modelLlist.size()>0){
			for(PhiScoreFlow model:modelLlist){
				PhiScoreFlowDto dto=BeanCopy.getInstance().convert(model, PhiScoreFlowDto.class);
				if(Constant.SCORE_TYPE_CONSUME.equals(dto.getScoreType())){
					dto.setScore("-"+dto.getScore());
				}
				dtoList.add(dto);
			}
		}
		DataPage<PhiScoreFlowDto> datePageDto = new DataPage<PhiScoreFlowDto>();
		datePageDto.setContent(dtoList);
		datePageDto.setPage(dataPage.getPage());
		datePageDto.setPageSize(dataPage.getPageSize());
		datePageDto.setTotalPage(dataPage.getTotalPage());
		datePageDto.setTotalRows(dataPage.getTotalRows());
		return datePageDto;
	}
	
	/******************      积分兑换券报表       ***********************/
	/**
	 * 列表
	 */
	@Override
	public DataPage<ScoreCoupsDataDto> getAllPhiScoreCoups(QueryPage queryPage) {
		DataPage<ScoreCoupsDataDto> dataPageDto= new DataPage<ScoreCoupsDataDto>();
		DataPage<Object> dataPage=phiScoreDataDao.getAllPhiScoreCoups(queryPage);
		List<String> strList = new ArrayList<String>();
		strList.add("coupons_third_party_type");
		Map<String,Map<String,String>> map=comonRpcService.getPropertyMapByCodeList(strList);
		List<ScoreCoupsDataDto> dtoList = new ArrayList<ScoreCoupsDataDto>();
		List<Object> modelList=dataPage.getContent();
		Map<String,String> haMap=map.get("coupons_third_party_type");
		if(modelList!=null&&modelList.size()>0){
			for(Object obj:modelList){
				Object[] objstr = (Object[]) obj;
				ScoreCoupsDataDto dto=new ScoreCoupsDataDto();
				dto.setTypeCode(objstr[0]==null?"":String.valueOf(objstr[0]));
				dto.setCpnsId(objstr[1]==null?"":String.valueOf(objstr[1]));
				dto.setCpnsName(objstr[2]==null?"":String.valueOf(objstr[2]));
				dto.setCnpsType(objstr[3]==null?"":String.valueOf(objstr[3]));
				dto.setCoupMemCount(objstr[4]==null?"":String.valueOf(objstr[4]));
				dto.setTotalCount(objstr[5]==null?"":String.valueOf(objstr[5]));
				dto.setCoupCount(objstr[6]==null?"":String.valueOf(objstr[6]));
				dto.setCoupThan(objstr[7]==null?"":String.valueOf(objstr[7]));
				dto.setUseCount(objstr[8]==null?"":String.valueOf(objstr[8]));
				dto.setUseThan(objstr[9]==null?"":String.valueOf(objstr[9]));
				dto.setCoupThan(dto.getCoupThan()+"%");
				if("-1".equals(dto.getTypeCode())){
					dto.setTypeName("优惠券");
					dto.setUseThan(dto.getUseThan()+"%");
				}else{
					dto.setTypeName(haMap.get(dto.getTypeCode()));
					dto.setUseThan("--");
					dto.setUseCount("--");
				}
				dtoList.add(dto);
			}
		}
		dataPageDto.setContent(dtoList);
		dataPageDto.setPage(dataPage.getPage());
		dataPageDto.setPageSize(dataPage.getPageSize());
		dataPageDto.setTotalPage(dataPage.getTotalPage());
		dataPageDto.setTotalRows(dataPage.getTotalRows());
		return dataPageDto;
	}
	
	/**
	 * 优惠券明细列表
	 */
	@Override
	public DataPage<PhiCouponsDetailDto> getAllPhiScoreCoupsDetail(QueryPage queryPage,Long cpnsWayId) {
		queryPage.setSqlCondition(" coup_way_id="+cpnsWayId);
        DataPage<PhiCouponsDetail> dataPage = phiCouponsDetailDao.getAllPhiCouponsDetail(queryPage);
        List<PhiCouponsDetailDto> dtoList = new ArrayList<PhiCouponsDetailDto>();
		List<PhiCouponsDetail> modelList=dataPage.getContent();
		if(modelList!=null&&modelList.size()>0){
			for(PhiCouponsDetail model:modelList){
				PhiCouponsDetailDto dto=BeanCopy.getInstance().convert(model, PhiCouponsDetailDto.class);
				if(model.getMemberId()!=null){
					dto.setMemberName(model.getMemberId().getTel());
				}
				dtoList.add(dto);
			}
		}
		DataPage<PhiCouponsDetailDto> dataPageDto = new DataPage<PhiCouponsDetailDto>();
		dataPageDto.setContent(dtoList);
		dataPageDto.setPage(dataPage.getPage());
		dataPageDto.setPageSize(dataPage.getPageSize());
		dataPageDto.setTotalPage(dataPage.getTotalPage());
		dataPageDto.setTotalRows(dataPage.getTotalRows());
		return dataPageDto;
	}
	
	/**
	 * 第三方优惠券详情
	 */
	@Override
	public DataPage<PhiThirdPartyCouponsDetailDto> getAllPhiScoreCoupsThirdDetail(QueryPage queryPage,String cpnsWayId) {
		queryPage.setSqlCondition(" coup_id='"+cpnsWayId+"'");
		DataPage<PhiThirdPartyCouponsDetail> dataPage = phiThirdPartyCouponsDetailDao.getAllPhiThirdPartyCouponsDetail(queryPage);
        List<PhiThirdPartyCouponsDetailDto> dtoList = new ArrayList<PhiThirdPartyCouponsDetailDto>();
		List<PhiThirdPartyCouponsDetail> modelList=dataPage.getContent();
		if(modelList!=null&&modelList.size()>0){
			for(PhiThirdPartyCouponsDetail model:modelList){
				PhiThirdPartyCouponsDetailDto dto=BeanCopy.getInstance().convert(model, PhiThirdPartyCouponsDetailDto.class);
				if(model.getPhiMember()!=null){
					dto.setMemberName(model.getPhiMember().getTel());
				}
				dtoList.add(dto);
			}
		}
		DataPage<PhiThirdPartyCouponsDetailDto> dataPageDto = new DataPage<PhiThirdPartyCouponsDetailDto>();
		dataPageDto.setContent(dtoList);
		dataPageDto.setPage(dataPage.getPage());
		dataPageDto.setPageSize(dataPage.getPageSize());
		dataPageDto.setTotalPage(dataPage.getTotalPage());
		dataPageDto.setTotalRows(dataPage.getTotalRows());
		return dataPageDto;
	}
	
	public  double divide(double d1,double d2,int len) {// 进行除法运算
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
       return b1.divide(b2,len,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

	
}

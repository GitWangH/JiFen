package com.huatek.busi.api.phicom.scoreData;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.huatek.busi.BusiUrlConstants;
import com.huatek.busi.dto.phicom.coupons.PhiCouponsDetailDto;
import com.huatek.busi.dto.phicom.coupons.PhiThirdPartyCouponsDetailDto;
import com.huatek.busi.dto.phicom.score.PhiScoreFlowDto;
import com.huatek.busi.dto.phicom.score.PhiScoreTaskConfigDto;
import com.huatek.busi.dto.phicom.scoreData.ScoreCoupsDataDto;
import com.huatek.busi.dto.phicom.scoreData.ScoreDataDto;
import com.huatek.busi.service.phicom.score.PhiScoreFlowService;
import com.huatek.busi.service.phicom.score.PhiScoreTaskConfigService;
import com.huatek.busi.service.phicom.scoreData.ScoreDataService;
import com.huatek.cmd.dto.FwPropertyDto;
import com.huatek.cmd.service.FwpropertyService;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

/**
 * 积分概括报表
 * @author martin_ju
 *
 */
@RestController
@RequestMapping(value = BusiUrlConstants.SCOREDATA_API)
public class ScoreDataAction {

    private static final Logger log = LoggerFactory
            .getLogger(ScoreDataAction.class);

    @Autowired
    private PhiScoreFlowService phiScoreFlowService;
    
    @Autowired
    private PhiScoreTaskConfigService phiScoreTaskConfigService;
    
    @Autowired
    private ScoreDataService scoreDataService;
    
    @Autowired
	private FwpropertyService fwpropertyService;

    
    /**
     * 积分概括报表--列表
     * */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<PhiScoreFlowDto>> getAllPhiScoreFlow(@RequestBody QueryPage queryPage)  { 	
        log.debug("get all phiScoreFlow of param " + queryPage.getQueryInfo());
        DataPage<PhiScoreFlowDto> phiScoreFlowPages = scoreDataService.getAllPhiScoreFlowPage(queryPage);
        log.debug("get phiScoreFlow size @" + phiScoreFlowPages.getContent().size());
        return new ResponseEntity<>(phiScoreFlowPages, HttpStatus.OK);
       
    }
    
    /**
     * 积分概括报表--饼图
     * */
    @RequestMapping(value = "/scoreData/{type}")
    @ResponseBody
    public ResponseEntity<ScoreDataDto> getPhiScoreData(@PathVariable("type") String type)  { 
    	ScoreDataDto scoreDataDto=scoreDataService.getPhiScoreData(type);
    	 return new ResponseEntity<>(scoreDataDto, HttpStatus.OK);
    }
   
    
    /**
     * 积分概括报表--任务项下拉列表
     * */
    @RequestMapping(value = "/scoreTaskConfig")
    @ResponseBody
    public ResponseEntity<List<PhiScoreTaskConfigDto>> getPhiScoreTaskConfig()  { 
    	List<PhiScoreTaskConfigDto> list=phiScoreTaskConfigService.getAllPhiScoreTaskConfigDto();
    	List<PhiScoreTaskConfigDto> dtolist = new ArrayList<PhiScoreTaskConfigDto>();
    	if(list!=null&&list.size()>0){
	    	for(PhiScoreTaskConfigDto dto:list ){
	    		PhiScoreTaskConfigDto dto1 = new PhiScoreTaskConfigDto();
	    		dto1.setId(dto.getId());
	    		dto1.setTaskName(dto.getTaskName());
	    	    dtolist.add(dto1);
	    	}
    	}
    	
    	return new ResponseEntity<>(removeDuplicate(dtolist), HttpStatus.OK);
    }
    
    public List<PhiScoreTaskConfigDto> removeDuplicate(List<PhiScoreTaskConfigDto> list){
    	 for  ( int  i  =   0 ; i  <  list.size()  -   1 ; i ++ )  {       
    	      for  ( int  j  =  list.size()  -   1 ; j  >  i; j -- )  {       
    	           if  (list.get(j).getId().equals(list.get(i).getId()))  {       
    	              list.remove(j);       
    	            }        
    	        }        
    	      }        
    	    return list;       
    }
    
 
    
    /**
     * 积分兑换卷报表--列表
     * */
    @RequestMapping(value = "/queryScoreCoups")
    @ResponseBody
    public ResponseEntity<DataPage<ScoreCoupsDataDto>> getAllPhiScoreCoups(@RequestBody QueryPage queryPage)  { 	
        log.debug("get all phiScoreFlow of param " + queryPage.getQueryInfo());
        DataPage<ScoreCoupsDataDto> phiScoreFlowPages = scoreDataService.getAllPhiScoreCoups(queryPage);
        log.debug("get phiScoreFlow size @" + phiScoreFlowPages.getContent().size());
        return new ResponseEntity<>(phiScoreFlowPages, HttpStatus.OK);
       
    }
    
    /**
     * 积分兑换卷报表--优惠券明细列表
     * */
    @RequestMapping(value = "/queryScoreCoups/{id}")
    @ResponseBody
    public ResponseEntity<DataPage<PhiCouponsDetailDto>> getAllPhiScoreCoupsDetail(@RequestBody QueryPage queryPage,@PathVariable("id") Long id)  { 	
        log.debug("get all phiScoreFlow of param " + queryPage.getQueryInfo());
        DataPage<PhiCouponsDetailDto> phiScoreFlowPages = scoreDataService.getAllPhiScoreCoupsDetail(queryPage,id);
        log.debug("get phiScoreFlow size @" + phiScoreFlowPages.getContent().size());
        return new ResponseEntity<>(phiScoreFlowPages, HttpStatus.OK);
       
    }
    
    /**
     * 积分兑换卷报表--第三方优惠券明细列表
     * */
    @RequestMapping(value = "/queryScoreCoupsThird/{id}")
    @ResponseBody
    public ResponseEntity<DataPage<PhiThirdPartyCouponsDetailDto>> getAllPhiScoreCoupsThirdDetail(@RequestBody QueryPage queryPage,@PathVariable("id") String id)  { 	
        log.debug("get all phiScoreFlow of param " + queryPage.getQueryInfo());
        DataPage<PhiThirdPartyCouponsDetailDto> phiScoreFlowPages = scoreDataService.getAllPhiScoreCoupsThirdDetail(queryPage,id);
        log.debug("get phiScoreFlow size @" + phiScoreFlowPages.getContent().size());
        return new ResponseEntity<>(phiScoreFlowPages, HttpStatus.OK);
       
    }
    @RequestMapping(value = "/scoreCoupsConfig")
    @ResponseBody
    public ResponseEntity<?> getPhiScoreCoupsConfig()  { 
		List<FwPropertyDto> list=fwpropertyService.getPropertyByCatKindName("coupons_third_party_type");
		FwPropertyDto dto=new FwPropertyDto();
		dto.setPropertyName("优惠券");
		dto.setPropertyValue("-1");
		list.add(dto);
    	return new ResponseEntity<>(list, HttpStatus.OK);
    }
}

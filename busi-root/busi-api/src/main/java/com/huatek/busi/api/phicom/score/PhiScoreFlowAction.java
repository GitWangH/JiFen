package com.huatek.busi.api.phicom.score;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.huatek.busi.BusiUrlConstants;
import com.huatek.busi.dto.phicom.score.PhiScoreFlowDto;
import com.huatek.busi.model.phicom.member.PhiMember;
import com.huatek.busi.service.phicom.member.PhiMemberService;
import com.huatek.busi.service.phicom.score.PhiScoreFlowService;
import com.huatek.cmd.category.util.JsonUtil;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.dto.ParamDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.page.QueryParam;
import com.huatek.frame.core.util.PhicommCloudUtil;
import com.huatek.frame.core.util.PhicommCloudUtil.CloudMember;
import com.huatek.frame.session.util.SessionKey;

@RestController
@RequestMapping(value = BusiUrlConstants.PHISCOREFLOW_API)
public class PhiScoreFlowAction {

    private static final Logger log = LoggerFactory
            .getLogger(PhiScoreFlowAction.class);

    @Autowired
    private PhiScoreFlowService phiScoreFlowService;

    @Autowired
    private PhiMemberService phiMemberService;
    /** 
    * @Title: getAllPhiScoreFlow 
    * @Description:  翻页查询PhiScoreFlow信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<PhiScoreFlowDto>>    
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<PhiScoreFlowDto>> getAllPhiScoreFlow(@RequestBody QueryPage queryPage,HttpSession session)  { 	
        log.debug("get all phiScoreFlow of param " + queryPage.getQueryInfo());
        CloudMember member = (CloudMember) session.getAttribute(SessionKey.currentMember);
        String uid=member.getUid();
        PhiMember phiMember = phiMemberService.GetMemberPhiMemberByUid(Integer.valueOf(uid));
        phiMemberService.calcEnableScore(phiMember);
        Long memberId =  phiMember.getId();
        List<QueryParam> params = queryPage.getQueryParamList();
        QueryParam queryParam = new QueryParam();
        queryParam.setField("memberId");
        queryParam.setLogic("=");
        queryParam.setValue(memberId.toString());
        params.add(queryParam);
        queryPage.setQueryParamList(params);
        DataPage<PhiScoreFlowDto> phiScoreFlowPages = phiScoreFlowService.getAllPhiScoreFlowPage(queryPage);
        log.debug("get phiScoreFlow size @" + phiScoreFlowPages.getContent().size());
        return new ResponseEntity<>(phiScoreFlowPages, HttpStatus.OK);
       
    }
    
     /** 
    * @Title: createPhiScoreFlowDto 
    * @Description: 添加PhiScoreFlow 
    * @param    phiScoreFlowDto
    * @return   ResponseEntity<ResponseMessage>    
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createPhiScoreFlowDto(@RequestBody PhiScoreFlowDto phiScoreFlowDto) {
        phiScoreFlowService.savePhiScoreFlowDto(phiScoreFlowDto);
        return new ResponseEntity<>(ResponseMessage.success("PhiScoreFlow创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getPhiScoreFlowDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<PhiScoreFlowDto>    
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<PhiScoreFlowDto> getPhiScoreFlowDto(@PathVariable("id") Long id) {
    	PhiScoreFlowDto phiScoreFlowDto = phiScoreFlowService.getPhiScoreFlowDtoById(id);
        return new ResponseEntity<>(phiScoreFlowDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editPhiScoreFlow 
    * @Description:修改PhiScoreFlow信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    phiScoreFlowDto
    * @return   ResponseEntity<ResponseMessage>    
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editPhiScoreFlow(@PathVariable("id") Long id, @RequestBody PhiScoreFlowDto phiScoreFlowDto) {
        phiScoreFlowService.updatePhiScoreFlow(id, phiScoreFlowDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deletePhiScoreFlowById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deletePhiScoreFlowById(@PathVariable("id") Long id) {
        phiScoreFlowService.deletePhiScoreFlow(id);
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
    
    /**
     * 积分到期值提醒
     * @param memberId
     * @return
     */
    @RequestMapping(value = "/getSoonFallDueScore", method = RequestMethod.GET)
    @ResponseBody

    public ResponseEntity<?> getSoonFallDueScore(HttpSession session)
	{
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH )+1;
		Param innerParam = new Param();
		//isshow:1:不显示 2：显示
		int isShow = 1;
	    int score = 0;
	   if (month == 12) {
		    PhicommCloudUtil.CloudMember member = (PhicommCloudUtil.CloudMember)session.getAttribute("_loginMember");
		    String uid = member.getUid();
		    PhiMember phiMember = this.phiMemberService.GetMemberPhiMemberByUid(Integer.valueOf(uid));
		    isShow = 2;
		    Long memberId = phiMember.getId();
		    score = this.phiScoreFlowService.getSoonFallDueScore(memberId);
		    innerParam.setScore(score);
			innerParam.setIsShow(2);
	   }
	   innerParam.setIsShow(isShow);
	  return  new ResponseEntity<Param>(innerParam, HttpStatus.OK);
    }  
    
    
    class Param{
    	private Integer score;
    	private Integer isShow;
		public Integer getScore() {
			return score;
		}
		public void setScore(Integer score) {
			this.score = score;
		}
		public Integer getIsShow() {
			return isShow;
		}
		public void setIsShow(Integer isShow) {
			this.isShow = isShow;
		}
    	
    }


}

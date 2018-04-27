package com.huatek.frame.api;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.huatek.frame.FrameUrlConstants;
import com.huatek.frame.core.beancopy.ConvertUtils;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.page.QueryParam;
import com.huatek.frame.handle.util.SpringContext;
import com.huatek.frame.service.FwOpraterLogService;
import com.huatek.frame.service.dto.FwOpraterLogDto;
import com.huatek.frame.session.data.UserInfo;
import com.huatek.frame.session.util.SessionKey;

@RestController
@RequestMapping(FrameUrlConstants.FWACTIONLOG_API)
public class FwActionLogAction {
    private static final Logger log = LoggerFactory.getLogger(FwActionLogAction.class);
    @Autowired
    private FwOpraterLogService fwOpraterLogService;

    /***
     * 获取系统操作日志信息.
     * @param acctName
     * @return 返回用户信息.
     */
    
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<FwOpraterLogDto>> getAllFwOpraterLogDto(@RequestBody QueryPage queryPage){
    	log.debug("get all ExceptionLog of param " + queryPage.getQueryInfo() + ", query start at " + System.currentTimeMillis());
	    DataPage<FwOpraterLogDto> fwOpraterLogPages = fwOpraterLogService.getFwOpraterLogDtoByPage(queryPage);
	    log.debug("get ExceptionLog size @" + fwOpraterLogPages.getContent().size() + ", query finash at " + System.currentTimeMillis());

	    return new ResponseEntity<DataPage<FwOpraterLogDto>>(fwOpraterLogPages, HttpStatus.OK);
    }
    @RequestMapping(value = "/queryOnlineCount")
    @ResponseBody
    public ResponseEntity<Map<String,Object>> queryOnlineCount(@RequestBody QueryPage queryPage){
    	DataPage<Map<String,Object>> datapage = new DataPage<Map<String,Object>>();
		datapage.setPageSize(queryPage.getPageSize());
		datapage.setPage(queryPage.getPage());
		
    	Map result=new HashMap<>();
    	Set<Long> userIds=new HashSet<Long>();
    	List<HttpSession> validateSessions=new ArrayList<HttpSession>();
    	List<HttpSession> sessions=SpringContext.findSessions();
    	for(HttpSession session : sessions){
    		if(session.getAttribute(SessionKey.currentUser)!=null){
    			UserInfo user = (UserInfo)session.getAttribute(SessionKey.currentUser);
    			String userName=null;
    			for(QueryParam param :queryPage.getQueryParamList()){
    				if("userName".equals(param.getField())&&param.getValue()!=null&&!param.getValue().trim().equals("")){
    					userName=param.getValue();
    				}
    			}
    			if(userName!=null){
    				if(user.getUserName()!=null&&user.getUserName().indexOf(userName)>=0){
            			userIds.add(user.getId());
            			validateSessions.add(session);
    				}
    			}else{
        			userIds.add(user.getId());
        			validateSessions.add(session);
    			}
    			
    		}
    	}
    	if (validateSessions.size() > 0) {
			datapage.setTotalPageByRows(validateSessions.size());
			datapage.setPage(datapage.getCurrentPage());
			datapage.setContent(new ArrayList<Map<String,Object>>());
			int start=datapage.getPageSize()*(datapage.getCurrentPage()-1);
			int end=start+datapage.getPageSize();
			validateSessions.sort(new Comparator<HttpSession>() {

				@Override
				public int compare(HttpSession o1, HttpSession o2) {
					// TODO Auto-generated method stub
					return Long.valueOf(o1.getCreationTime()-o2.getCreationTime()).intValue();
				}
			});
			for(int i=start;i<validateSessions.size()&&i<end;i++ ){
				HttpSession session=validateSessions.get(i);
				UserInfo user = (UserInfo)session.getAttribute(SessionKey.currentUser);
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("acctName", user.getAcctName());
				map.put("userName", user.getUserName());
				map.put("orgName", user.getOrgName());
				map.put("loginTime", ConvertUtils.convert(new Date(session.getCreationTime()), String.class));
				map.put("loginIp", session.getAttribute("ip"));
				map.put("userAgent", session.getAttribute("userAgent"));
				datapage.getContent().add(map);
			}
		}
		result.put("datapage", datapage);
    	result.put("onlineCount", validateSessions.size());
    	result.put("onlineUser", userIds.size());
	    return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
    }
}
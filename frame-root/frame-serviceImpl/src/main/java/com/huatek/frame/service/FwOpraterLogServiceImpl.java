package com.huatek.frame.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.beancopy.FieldValues;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.dao.FwOpraterLogDao;
import com.huatek.frame.dao.model.FwAccount;
import com.huatek.frame.dao.model.FwOpraterLog;
import com.huatek.frame.dao.model.FwSource;
import com.huatek.frame.service.dto.FwOpraterLogDto;
import com.huatek.frame.service.dto.SourceDto;

@Service
@Transactional(rollbackFor=Exception.class) 
public class FwOpraterLogServiceImpl implements FwOpraterLogService {
	
    private static final Logger log = LoggerFactory.getLogger(FwOpraterLogServiceImpl.class);

	@Autowired
	private FwOpraterLogDao fwOpraterLogDao;

	@Autowired
	private FwSourceService fwSourceService;
	private static final Map<String, String> dictionaryMap = new HashMap<String, String>();
	private static final BeanCopy beanToDto = BeanCopy.getInstance();
	static {
		dictionaryMap.put("1", "是");
		dictionaryMap.put("-1", "否");
		beanToDto
				.addFieldValuesMap(new FieldValues("isMenu", dictionaryMap),
						"isMenuStr")
				.addFieldValuesMap(new FieldValues("isShow", dictionaryMap),
						"isShowStr").addFieldMap("sourceName", "label")
				.addFieldMap("parent.sourceName", "parentName")
				.addFieldMap("parent.id", "parentid")//
				.addFieldMap("orgLevel", "level")
				.addFieldMap("sourceName", "title")
				.addFieldMap("sourceUrl", "url")
				.addFieldMap("sourceTemplate", "view")
				.addFieldMap("sourcePath", "path")
				.addFieldMap("system.id", "systemid")
				.addFieldMap("system.name", "systemName");
	}
	@Override
	public void writeOpretionLog(FwOpraterLogDto fwOpraterLogDto) {
		FwOpraterLog fwOpraterLog = new FwOpraterLog();
		fwOpraterLog.setClientIp(fwOpraterLogDto.getClientIp());
		fwOpraterLog.setClientPort(fwOpraterLogDto.getClientPort());
		FwAccount fwAccount = new FwAccount();
		fwAccount.setId(fwOpraterLogDto.getAcctId());
		fwAccount.setAcctName(fwOpraterLogDto.getAcctName());
		fwOpraterLog.setFwAccount(fwAccount);
		fwOpraterLog.setActionTime(new Date());
		fwOpraterLog.setUserAgent(fwOpraterLogDto.getUserAgent());
		fwOpraterLog.setId(fwOpraterLogDto.getActLogId());
		fwOpraterLog.setMsg(fwOpraterLogDto.getMsg());
		// 访问菜单
		SourceDto sourceDto = fwSourceService.findMenuByUrl(fwOpraterLogDto.getOpretePath());
		if(sourceDto != null ){
			FwSource source = new FwSource();
			source.setId(Long.valueOf(sourceDto.getId()));
			fwOpraterLog.setFwSource(source);
			fwOpraterLogDao.saveOpraterLog(fwOpraterLog);
		}
	}

	//获取操作日志
	@Override
	public DataPage<FwOpraterLogDto> getFwOpraterLogDtoByPage(
			QueryPage queryPage) {
		DataPage<FwOpraterLog> dataPage = fwOpraterLogDao.getFwOpraterLogByPage(queryPage);
		
		DataPage<FwOpraterLogDto> fwActiobLogDtoPage = BeanCopy.getInstance().addIgnoreField("fwSource").convertPage(dataPage, FwOpraterLogDto.class);
		//为dto设置acctName值
		int i = 0;
		List<FwOpraterLogDto> fwOpraterLogDtos = fwActiobLogDtoPage.getContent();
		for(FwOpraterLogDto fwOpraterLogDto : fwOpraterLogDtos){
			fwOpraterLogDto.setAcctId(dataPage.getContent().get(i).getFwAccount().getId());
			fwOpraterLogDto.setAcctName(dataPage.getContent().get(i).getFwAccount().getAcctName());
			fwOpraterLogDto.setUserName(dataPage.getContent().get(i).getFwAccount().getUserName());
			fwOpraterLogDto.setFwSource(beanToDto.convert(dataPage.getContent().get(i).getFwSource(), SourceDto.class));
			i++;
		}
		return fwActiobLogDtoPage;
		
	}
}
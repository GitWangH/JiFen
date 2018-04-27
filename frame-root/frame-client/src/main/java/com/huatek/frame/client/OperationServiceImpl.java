package com.huatek.frame.client;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.huatek.esb.msg.rpc.RpcProxy;
import com.huatek.frame.authority.dto.ExceptionLogDto;
import com.huatek.frame.authority.dto.OpraterLogDto;
import com.huatek.frame.authority.service.OperationService;
import com.huatek.frame.authority.util.UserUtil;
import com.huatek.frame.handle.util.HttpRequestUtil;
@Component
public class OperationServiceImpl implements OperationService {
	@Autowired
	RpcProxy rpcProxy;
	
	private OperationService getService(){
		return rpcProxy.create(OperationService.class);
	}

	@Override
	public void logOperation(String msg) {
		OpraterLogDto fwOpraterLogDto = new OpraterLogDto();
		if(RequestContextHolder.getRequestAttributes()!=null&&((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest()!=null){
			HttpServletRequest request =  ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			fwOpraterLogDto.setClientIp(HttpRequestUtil.getIpAddress(request));
			fwOpraterLogDto.setClientPort(new Long(request.getRemotePort()));
			fwOpraterLogDto.setMenuId(request.getHeader("menuId")==null?null:Long.valueOf(request.getHeader("menuId")));
			fwOpraterLogDto.setOpretePath(HttpRequestUtil.getCallPath(request));
			fwOpraterLogDto.setUserAgent(request.getHeader("user-agent"));
		}
		if(UserUtil.getUser()!=null){
			fwOpraterLogDto.setAcctId(UserUtil.getUser().getId());
			fwOpraterLogDto.setAcctName(UserUtil.getUser().getAcctName());
		}
		
		
		fwOpraterLogDto.setMsg(msg);
		
		logOperationDto(fwOpraterLogDto);getService().logOperation(msg);
	}

	@Override
	public void logOperationDto(OpraterLogDto dto) {
		getService().logOperationDto(dto);
		
	}

	@Override
	public void logException(Throwable ex) {
		
		ExceptionLogDto dto=new ExceptionLogDto();
		if(RequestContextHolder.getRequestAttributes()!=null&&((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest()!=null){
			HttpServletRequest request =  ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			dto.setSourcePath(HttpRequestUtil.getCallPath(request));
			dto.setSourceId(request.getHeader("menuId")==null?null:Long.valueOf(request.getHeader("menuId")));
		}
		if(UserUtil.getUser()!=null){
			dto.setAcctId(UserUtil.getUser().getId());
			dto.setAcctName(UserUtil.getUser().getAcctName());
			dto.setEcptCode("-1");
		}
		dto.setEcptMessage(ex.getMessage());
		
		try{
			StringWriter stringWriter = new StringWriter();
			PrintWriter pw = new PrintWriter(stringWriter); 
			ex.printStackTrace(pw);
			dto.setEcptStack(stringWriter.toString());
		}catch(Exception e){
			
		}
		logExceptionDto(dto);
	}

	@Override
	public void logExceptionDto(ExceptionLogDto dto) {
		getService().logExceptionDto(dto);
		
	}

}

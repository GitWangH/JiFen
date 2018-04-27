package com.huatek.frame.api;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.huatek.frame.authority.dto.OpraterLogDto;
import com.huatek.frame.authority.service.OperationService;
import com.huatek.frame.authority.util.UserUtil;
import com.huatek.frame.handle.LoginListener;
import com.huatek.frame.handle.util.HttpRequestUtil;
import com.huatek.frame.service.FwOpraterLogService;
import com.huatek.frame.service.FwOrgService;
import com.huatek.frame.service.UserDataAuthService;
import com.huatek.frame.service.dto.FwOrgDto;
import com.huatek.frame.session.data.UserInfo;
import com.huatek.frame.session.util.SessionKey;

@Service
@Transactional
public class FrameLoginListener implements LoginListener {
	private static final Logger log = LoggerFactory
			.getLogger(FrameLoginListener.class);
	@Autowired
	private FwOpraterLogService fwOpraterLogService;
	@Autowired
	private UserDataAuthService userDataAuthService;
	@Autowired
	OperationService operationAuthorityService;

	@Override
	public void login(UserInfo user, HttpServletRequest request) {
		log.info(user.getAcctName() + "用户登录系统.");
		user.setUserAuthorityData(userDataAuthService.getDataAuthority(user));
		user.setUserAuthorityData(userDataAuthService.getInitDataAuth(user));
		if (request.getSession().getAttribute(SessionKey.currentUser) == null) {
			request.getSession().setAttribute(SessionKey.currentUser, user);
			request.getSession().setAttribute("ip", HttpRequestUtil.getIpAddress(request));
			request.getSession().setAttribute("userAgent", request.getHeader("user-agent"));
		}
		OpraterLogDto fwOpraterLogDto = new OpraterLogDto();
			fwOpraterLogDto.setClientIp(HttpRequestUtil.getIpAddress(request));
			fwOpraterLogDto.setClientPort(new Long(request.getRemotePort()));
			fwOpraterLogDto.setOpretePath(HttpRequestUtil.getCallPath(request));
			fwOpraterLogDto.setUserAgent(request.getHeader("user-agent"));
			fwOpraterLogDto.setMsg("登录系统");
			fwOpraterLogDto.setAcctId(user.getId());
			fwOpraterLogDto.setAcctName(user.getAcctName());
		operationAuthorityService.logOperationDto(fwOpraterLogDto);
	}

	

	@Override
	public void logout(HttpServletRequest request) {
		UserInfo user = (UserInfo) request.getSession().getAttribute(SessionKey.currentUser);
		if (user != null) {
			OpraterLogDto fwOpraterLogDto = new OpraterLogDto();
			fwOpraterLogDto.setClientIp(HttpRequestUtil.getIpAddress(request));
			fwOpraterLogDto.setClientPort(new Long(request.getRemotePort()));
			fwOpraterLogDto.setOpretePath(HttpRequestUtil.getCallPath(request));
			fwOpraterLogDto.setUserAgent(request.getHeader("user-agent"));
			fwOpraterLogDto.setMsg("登录系统");
			fwOpraterLogDto.setAcctId(user.getId());
			fwOpraterLogDto.setAcctName(user.getAcctName());
			operationAuthorityService.logOperationDto(fwOpraterLogDto);
		}
	}

}

package com.huatek.frame.api;

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
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.service.ExceptionLogService;
import com.huatek.frame.service.dto.ExceptionLogDto;

@RestController
@RequestMapping(FrameUrlConstants.EXCEPTION_API)
public class ExceptionLogAction {

    private static final Logger log = LoggerFactory.getLogger(ExceptionLogAction.class);

    @Autowired
    private ExceptionLogService exceptionLogService;

    /***
     * 获取错误日志信息.
     * @param acctName
     * @return 返回用户信息.
     */
    @RequestMapping(value = "/home")
    @ResponseBody
    public ResponseEntity<DataPage<ExceptionLogDto>> getNameUserBy(@RequestBody QueryPage queryPage) {
	    DataPage<ExceptionLogDto> exceptionLogPages = exceptionLogService.getExceptionLogDtoByPage(queryPage);
	    return new ResponseEntity<DataPage<ExceptionLogDto>>(exceptionLogPages, HttpStatus.OK);
    }
}
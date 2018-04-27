package com.huatek.frame.handle;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.huatek.frame.authority.dto.ExceptionLogDto;
import com.huatek.frame.authority.service.OperationService;
import com.huatek.frame.authority.util.ThreadLocalClient;
import com.huatek.frame.core.exception.HandlerException;
import com.huatek.frame.exception.ResourceNotFoundException;
import com.huatek.frame.handle.exception.InvalidRequestException;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.session.data.UserInfo;
import com.huatek.frame.session.util.SessionKey;

/**
 * Called when an exception occurs during request processing. Transforms
 * exception message into JSON format.
 */
@ControllerAdvice(annotations = RestController.class)
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(RestExceptionHandler.class);

	@Autowired
	private MessageSource messageSource;
	@Autowired
	OperationService operationAuthorityService;
	
	
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex,
	        final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		operationAuthorityService.logException( ex);
	    if (ex.getCause() instanceof HandlerException) {
	    	ex.printStackTrace();
	    	 return new ResponseEntity<Object>(new ResponseMessage(ResponseMessage.Type.danger, ex.getCause().getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
	    } else {
	    	ResponseMessage message=ResponseMessage.danger("系统错误："+ex.getMessage());
	    	 return new ResponseEntity<Object>(message, HttpStatus.UNPROCESSABLE_ENTITY);
	    }
	}

	

	@ExceptionHandler(value = { ResourceNotFoundException.class })
	@ResponseBody
	public ResponseEntity<ResponseMessage> handleResourceNotFoundException(ResourceNotFoundException ex,
			WebRequest request) {
		ex.printStackTrace();
		operationAuthorityService.logException( ex);
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = { InvalidRequestException.class })
	@ResponseBody
	public ResponseEntity<ResponseMessage> handleInvalidRequestException(InvalidRequestException ex, WebRequest req) {
		ex.printStackTrace();
		operationAuthorityService.logException( ex);
		ResponseMessage alert = new ResponseMessage(ResponseMessage.Type.danger, ApiErrors.INVALID_REQUEST,
				messageSource.getMessage(ApiErrors.INVALID_REQUEST, new String[] {}, null));

		BindingResult result = ex.getErrors();

		List<FieldError> fieldErrors = result.getFieldErrors();

		if (!fieldErrors.isEmpty()) {
			fieldErrors.stream().forEach((e) -> {
				alert.addError(e.getField(), e.getCode(), e.getDefaultMessage());
			});
		}

		return new ResponseEntity<>(alert, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	@ExceptionHandler(value = { Exception.class, RuntimeException.class })
	@ResponseBody
	public ResponseEntity<ResponseMessage> handleGenericException(Exception ex, WebRequest request) {
		ex.printStackTrace();
		if (log.isDebugEnabled()) {
			log.debug("handling exception...");
		}
		ResponseMessage message=null;
		if (ex instanceof HandlerException) {
			message=ResponseMessage.danger( ex.getMessage());
		}else{
			message=ResponseMessage.danger( "系统错误:"+ex.getMessage());
		}
		operationAuthorityService.logException( ex);
		return new ResponseEntity<>(message,
				HttpStatus.BAD_REQUEST);
	}

	
}

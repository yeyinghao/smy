package com.luman.smy.infra.common.handler;


import cn.hutool.core.util.StrUtil;
import com.luman.smy.client.dto.Response;
import com.luman.smy.infra.common.constant.CommConstant;
import com.luman.smy.infra.common.enums.CommErrorEnum;
import com.luman.smy.infra.common.exception.BizException;
import com.luman.smy.infra.common.helper.RHelper;
import com.luman.smy.infra.common.util.LoggerUtil;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 全局异常处理程序
 *
 * @author yeyinghao
 * @date 2023/08/01
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	/**
	 * 拦截Exception异常
	 */
	@ExceptionHandler(Exception.class)
	public Response<Void> exceptionHandler(Exception e) {
		LoggerUtil.error(log, e);
		return RHelper.fail(CommErrorEnum.SYSTEM_ERROR);
	}


	@ExceptionHandler(HttpMessageNotReadableException.class)
	public Response<Void> exceptionHandler(HttpMessageNotReadableException e) {
		LoggerUtil.error(log, e);
		return RHelper.fail(CommErrorEnum.ILLEGAL_PARAMETER, e.getMessage());
	}

	/**
	 * 拦截NoHandlerFoundException异常
	 */
	@ExceptionHandler(NoHandlerFoundException.class)
	public Response<Void> noHandlerFoundExceptionHandler(NoHandlerFoundException e) {
		LoggerUtil.info(log, "message={}, requestUrl={}", e.getMessage(), e.getRequestURL());
		return RHelper.fail(CommErrorEnum.NOT_FOUND);
	}

	/**
	 * 拦截HttpRequestMethodNotSupportedException异常
	 */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public Response<Void> httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException e) {
		LoggerUtil.info(log, "message={}, exceptMethod={}, actualMethod={}", e.getMessage(), e.getSupportedMethods(), e.getMethod());
		return RHelper.fail(CommErrorEnum.BIZ_ERROR, "请求方式不支持");
	}

	/**
	 * 拦截 bizException异常
	 */
	@ExceptionHandler(BizException.class)
	public Response<Void> bizExceptionHandler(BizException e) {
		LoggerUtil.info(log, e);
		return RHelper.fail(e.getErrorEnum(), e.getMessage());
	}

	/**
	 * 绑定异常
	 *
	 * @param e e
	 * @return {@link Response}<{@link String}>
	 */
	@ExceptionHandler(BindException.class)
	public Response<Void> bindExceptionHandler(BindException e) {
		LoggerUtil.info(log, e);
		// 拿到@NotNull,@NotBlank和 @NotEmpty等注解上的message值
		String msg = Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage();
		if (StrUtil.isNotEmpty(msg)) {
			// 自定义状态返回
			return RHelper.fail(CommErrorEnum.ILLEGAL_PARAMETER, msg);
		}
		// 参数类型不匹配检验
		StringBuilder message = new StringBuilder();
		List<FieldError> fieldErrors = e.getFieldErrors();
		fieldErrors.forEach((item) -> message.append("参数:[").append(item.getObjectName()).append(".").append(item.getField()).append("]的传入值:[").append(item.getRejectedValue()).append("]与预期的字段类型不匹配."));
		return RHelper.fail(CommErrorEnum.ILLEGAL_PARAMETER, message.toString());
	}

	/**
	 * 约束违反例外
	 *
	 * @param e e
	 * @return {@link Response}<{@link String}>
	 */
	@ExceptionHandler(value = ConstraintViolationException.class)
	public Response<Void> constraintViolationExceptionHandler(ConstraintViolationException e) {
		LoggerUtil.info(log, e);
		Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
		String message = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(";"));
		return RHelper.fail(CommErrorEnum.ILLEGAL_PARAMETER, message);
	}

	/**
	 * 方法参数无效例外
	 *
	 * @param e e
	 * @return {@link Response}<{@link String}>
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Response<Void> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
		LoggerUtil.info(log, e);
		String message = e.getBindingResult().getFieldErrors().stream().map(item -> item.getField() + CommConstant.COLON + item.getDefaultMessage()).collect(Collectors.joining(CommConstant.SEMICOLON));
		return RHelper.fail(CommErrorEnum.ILLEGAL_PARAMETER, message);
	}
}

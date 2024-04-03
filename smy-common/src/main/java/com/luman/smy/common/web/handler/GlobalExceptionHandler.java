package com.luman.smy.common.web.handler;

import cn.dev33.satoken.exception.NotLoginException;
import cn.hutool.core.util.StrUtil;
import com.luman.smy.common.constant.CommConstant;
import com.luman.smy.common.enums.CommErrorEnum;
import com.luman.smy.common.exception.BizException;
import com.luman.smy.common.helper.ResultHelper;
import com.luman.smy.common.util.LoggerUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
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
@RequiredArgsConstructor
public class GlobalExceptionHandler {

	/**
	 * 请求
	 */
	private final HttpServletRequest request;

	/**
	 * 拦截Exception异常
	 */
	@ExceptionHandler(Exception.class)
	public ResultHelper<String> exceptionHandler(Exception e) {
		LoggerUtil.error(log, e);
		return ResultHelper.fail(CommErrorEnum.SYSTEM_ERROR);
	}

	/**
	 * sa-token-未登录异常
	 *
	 * @param e e
	 * @return {@link ResultHelper}<{@link String}>
	 */
	@ExceptionHandler(NotLoginException.class)
	public ResultHelper<String> notLoginExceptionHandler(NotLoginException e) {
		LoggerUtil.info(log, e.getMessage());
		return ResultHelper.fail(CommErrorEnum.FORBIDDEN,"禁止访问");
	}

	/**
	 * 拦截NoHandlerFoundException异常
	 */
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResultHelper<String> noHandlerFoundExceptionHandler(NoHandlerFoundException e) {
		LoggerUtil.info(log, e.getMessage(), request.getServletPath());
		return ResultHelper.fail(CommErrorEnum.NOT_FOUND);
	}

	/**
	 * 拦截HttpRequestMethodNotSupportedException异常
	 */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResultHelper<String> httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException e) {
		LoggerUtil.info(log, e.getMessage(), request.getServletPath());
		return ResultHelper.fail(CommErrorEnum.BIZ_PROCESS_FAIL, "请求方式不支持");
	}

	/**
	 * 拦截 bizException异常
	 */
	@ExceptionHandler(BizException.class)
	public ResultHelper<String> bizExceptionHandler(BizException e) {
		LoggerUtil.info(log, e);
		return ResultHelper.fail(e.getErrorEnum(), e.getSubMessage());
	}

	/**
	 * 绑定异常
	 *
	 * @param e e
	 * @return {@link ResultHelper}<{@link String}>
	 */
	@ExceptionHandler(BindException.class)
	public ResultHelper<String> bindExceptionHandler(BindException e) {
		LoggerUtil.info(log, e);
		// 拿到@NotNull,@NotBlank和 @NotEmpty等注解上的message值
		String msg = Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage();
		if (StrUtil.isNotEmpty(msg)) {
			// 自定义状态返回
			return ResultHelper.fail(CommErrorEnum.ILLEGAL_PARAMETER, msg);
		}
		// 参数类型不匹配检验
		StringBuilder message = new StringBuilder();
		List<FieldError> fieldErrors = e.getFieldErrors();
		fieldErrors.forEach((item) -> message.append("参数:[").append(item.getObjectName())
				.append(".").append(item.getField()).append("]的传入值:[")
				.append(item.getRejectedValue()).append("]与预期的字段类型不匹配."));
		return ResultHelper.fail(CommErrorEnum.ILLEGAL_PARAMETER, message.toString());
	}

	/**
	 * 约束违反例外
	 *
	 * @param e e
	 * @return {@link ResultHelper}<{@link String}>
	 */
	@ExceptionHandler(value = ConstraintViolationException.class)
	public ResultHelper<String> constraintViolationExceptionHandler(ConstraintViolationException e) {
		LoggerUtil.info(log, e);
		Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
		String message = violations.stream().map(ConstraintViolation::getMessage)
				.collect(Collectors.joining(";"));
		return ResultHelper.fail(CommErrorEnum.ILLEGAL_PARAMETER, message);
	}

	/**
	 * 方法参数无效例外
	 *
	 * @param e e
	 * @return {@link ResultHelper}<{@link String}>
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResultHelper<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
		LoggerUtil.info(log, e);
		String message = e.getBindingResult().getFieldErrors().stream()
				.map(item -> item.getField() + CommConstant.COLON + item.getDefaultMessage())
				.collect(Collectors.joining(CommConstant.SEMICOLON));
		return ResultHelper.fail(CommErrorEnum.ILLEGAL_PARAMETER, message);
	}
}

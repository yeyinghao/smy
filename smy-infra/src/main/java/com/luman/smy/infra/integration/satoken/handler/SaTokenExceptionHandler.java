package com.luman.smy.infra.integration.satoken.handler;


import cn.dev33.satoken.exception.NotLoginException;
import com.luman.smy.client.dto.Response;
import com.luman.smy.infra.common.enums.CommErrorEnum;
import com.luman.smy.infra.common.helper.RHelper;
import com.luman.smy.infra.common.util.LoggerUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理程序
 *
 * @author yeyinghao
 * @date 2023/08/01
 */
@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class SaTokenExceptionHandler {

	/**
	 * sa-token-未登录异常
	 *
	 * @param e e
	 * @return {@link Response}<{@link String}>
	 */
	@ExceptionHandler(NotLoginException.class)
	public Response<Void> notLoginExceptionHandler(NotLoginException e) {
		LoggerUtil.info(log, e.getMessage());
		return RHelper.fail(CommErrorEnum.FORBIDDEN, "禁止访问");
	}

}

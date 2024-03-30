/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2023.12
 */

package com.luman.code.monitor.web;

import com.luman.code.monitor.constant.LogConstant;
import com.luman.code.util.enums.ErrorEnum;
import com.luman.code.util.exception.BizException;
import com.luman.code.util.util.CommUtil;
import com.luman.code.util.util.ErrorEnumUtil;
import com.luman.code.util.util.LoggerUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * @author yeyinghao
 * @version 1.0.0
 * @description:
 * @date: 2023/2/25 21:59
 */
@Aspect
@Component
@RequiredArgsConstructor
@Slf4j(topic = LogConstant.WEB_MONITOR_LOGGER)
public class WebAspect {

	/**
	 * 请求
	 */
	private final HttpServletRequest request;

	/**
	 * 周围
	 *
	 * @param joinPoint 连接点
	 * @param web       api参数
	 * @return {@link Object}
	 */
	@SneakyThrows
	@Around("@annotation(web)")
	public Object around(ProceedingJoinPoint joinPoint, Web web) {
		long startTime = System.currentTimeMillis();
		String className = null;
		String methodName = null;
		List<Object> param = null;
		Object resp = null;
		boolean res = true;
		ErrorEnum errorEnum = null;
		String url = null;
		try {
			url = request.getServletPath();
			className = joinPoint.getSignature().getDeclaringType().getSimpleName();
			methodName = joinPoint.getSignature().getName();
			param = Arrays.asList(joinPoint.getArgs());
			resp = joinPoint.proceed();
			return resp;
		} catch (BizException e) {
			errorEnum = e.getErrorEnum();
			res = !ErrorEnumUtil.isError(errorEnum);
			throw e;
		} finally {
			LoggerUtil.info(log, className, methodName, url, param, resp, CommUtil.getStringByBoolean(res), errorEnum,
					CommUtil.getCostTime(startTime));
		}
	}
}

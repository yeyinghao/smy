/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2023.8
 */

package com.luman.code.monitor.rest;


import com.luman.code.monitor.constant.LogConstant;
import com.luman.code.util.enums.ErrorEnum;
import com.luman.code.util.exception.BizException;
import com.luman.code.util.util.CommUtil;
import com.luman.code.util.util.ErrorEnumUtil;
import com.luman.code.util.util.LoggerUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

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
@Slf4j(topic = LogConstant.REST_MONITOR_LOGGER)
public class RestAspect {

	/**
	 * 周围
	 *
	 * @param joinPoint 连接点
	 * @param rest      api
	 * @return {@link Object}
	 */
	@SneakyThrows
	@Around("@annotation(rest)")
	public Object around(ProceedingJoinPoint joinPoint, Rest rest) {
		long startTime = System.currentTimeMillis();
		List<Object> param = null;
		String className = null;
		String methodName = null;
		boolean res = true;
		Object resp = null;
		ErrorEnum errorEnum = null;
		try {
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
			LoggerUtil.info(log, className, methodName, param, resp, CommUtil.getStringByBoolean(res), errorEnum,
					CommUtil.getCostTime(startTime));
		}
	}
}

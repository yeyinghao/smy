/*
 * QQ: 1113531030 WX: missyeyh Phone: 17689397484 Copyright (c) Ye Yinghao 2022.1 - 2023.2
 */

package com.luman.code.common.annotations.dal;

import com.luman.code.common.constant.MonitorConstant;
import com.luman.code.common.util.CommUtil;
import com.luman.code.common.util.LoggerUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * @author yeyinghao
 * @version 1.0.0
 * @description:
 * @date: 2023/2/25 21:59
 */
@Aspect
@Component
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Slf4j(topic = MonitorConstant.DAL_MONITOR_LOGGER)
public class DalAspect {

	/**
	 * 周围
	 *
	 * @param joinPoint 连接点
	 * @return {@link Object}
	 */
	@SneakyThrows
	@Around("execution(* com.luman.code.domain..*.*Service.*(..))")
	public Object around(ProceedingJoinPoint joinPoint) {
		long startTime = System.currentTimeMillis();
		String className = null;
		String methodName = null;
		boolean res = false;
		try {
			className = joinPoint.getSignature().getDeclaringType().getSimpleName();
			methodName = joinPoint.getSignature().getName();
			res = true;
			return joinPoint.proceed();
		} finally {
			LoggerUtil.info(log, className, methodName, CommUtil.getStringByBoolean(res),
					CommUtil.getCostTime(startTime));
		}
	}
}

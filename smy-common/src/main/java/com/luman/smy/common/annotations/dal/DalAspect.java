/*
 * QQ: 1113531030 WX: missyeyh Phone: 17689397484 Copyright (c) Ye Yinghao 2022.1 - 2023.2
 */

package com.luman.smy.common.annotations.dal;

import com.luman.smy.common.constant.MonitorConstant;
import com.luman.smy.common.util.LoggerUtil;
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
		boolean res = true;
		try {
			return joinPoint.proceed();
		} catch (Throwable e) {
			res = false;
			throw e;
		} finally {
			LoggerUtil.info(log, joinPoint, res, startTime);
		}
	}
}

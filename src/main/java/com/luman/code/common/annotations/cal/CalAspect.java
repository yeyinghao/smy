/*
 * QQ: 1113531030 WX: missyeyh Phone: 17689397484 Copyright (c) Ye Yinghao 2022.1 - 2023.2
 */

package com.luman.code.common.annotations.cal;

import com.luman.code.common.constant.MonitorConstant;
import com.luman.code.common.util.CommUtil;
import com.luman.code.common.util.LoggerUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @author yeyinghao
 * @version 1.0.0
 * @description:
 * @date: 2023/2/25 21:59
 */
@Aspect
@Component
@Slf4j(topic = MonitorConstant.CAL_MONITOR_LOGGER)
public class CalAspect {

	/**
	 * 周围
	 *
	 * @param joinPoint 连接点
	 * @param cal       卡尔
	 * @return {@link Object}
	 */
	@SneakyThrows
	@Around("@annotation(cal)")
	public Object around(ProceedingJoinPoint joinPoint, Cal cal) {
		long startTime = System.currentTimeMillis();
		String calMethod = null;
		String calKey = null;
		boolean isGet;
		boolean getNotNull = false;
		boolean res = false;
		try {
			MethodSignature signature = (MethodSignature) joinPoint.getSignature();
			calMethod = signature.getMethod().getName();
			isGet = cal.isGet();
			calKey = String.valueOf(joinPoint.getArgs()[0]);
			Object proceed = joinPoint.proceed();
			getNotNull = isGet && proceed != null;
			res = true;
			return proceed;
		} finally {
			LoggerUtil.info(log, calMethod, calKey, CommUtil.getStringByBoolean(res),
					CommUtil.getStringByBoolean(getNotNull), CommUtil.getCostTime(startTime));
		}
	}
}

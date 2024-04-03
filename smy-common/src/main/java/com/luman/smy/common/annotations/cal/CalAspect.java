/*
 * QQ: 1113531030 WX: missyeyh Phone: 17689397484 Copyright (c) Ye Yinghao 2022.1 - 2023.2
 */

package com.luman.smy.common.annotations.cal;

import com.luman.smy.common.constant.MonitorConstant;
import com.luman.smy.common.util.CommUtil;
import com.luman.smy.common.util.LoggerUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
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
		String calKey = null;
		boolean isGet;
		boolean getNotNull = false;
		boolean res = true;
		try {
			isGet = cal.isGet();
			calKey = String.valueOf(joinPoint.getArgs()[0]);
			Object proceed = joinPoint.proceed();
			getNotNull = isGet && proceed != null;
			return proceed;
		} catch (Throwable e) {
			res = false;
			throw e;
		} finally {
			LoggerUtil.info(log, calKey, CommUtil.getStringByBoolean(getNotNull));
			LoggerUtil.info(log, joinPoint, res, startTime);
		}
	}
}

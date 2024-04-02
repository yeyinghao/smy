/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2023.8
 */

package com.luman.code.monitor.sal;

import com.luman.code.monitor.constant.MonitorConstant;
import com.luman.code.monitor.util.MonitorUtil;
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
@Slf4j(topic = MonitorConstant.SAL_MONITOR_LOGGER)
public class SalAspect {

	/**
	 * 周围
	 *
	 * @param joinPoint 连接点
	 * @param sal       api
	 * @return {@link Object}
	 */
	@SneakyThrows
	@Around("@annotation(sal)")
	public Object around(ProceedingJoinPoint joinPoint, Sal sal) {
		return MonitorUtil.monitor(joinPoint, log);
	}


}

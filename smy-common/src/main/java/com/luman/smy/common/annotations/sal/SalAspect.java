/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2023.8
 */

package com.luman.smy.common.annotations.sal;

import com.luman.smy.common.constant.MonitorConstant;
import com.luman.smy.common.util.MonitorUtil;
import com.luman.smy.common.util.SpelUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * sal切面
 *
 * @author yeyinghao
 * @date 2024/04/04
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
		String name = SpelUtil.generateKeyBySpEL(sal.name(), joinPoint);
		String desc = SpelUtil.generateKeyBySpEL(sal.desc(), joinPoint);
		return MonitorUtil.monitor(joinPoint, name, desc, log);
	}

}

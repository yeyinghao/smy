/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2023.8
 */

package com.luman.smy.common.annotations.rest;


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
 * rest切面
 *
 * @author yeyinghao
 * @date 2024/04/04
 */
@Aspect
@Component
@Slf4j(topic = MonitorConstant.REST_MONITOR_LOGGER)
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
		String name = SpelUtil.generateKeyBySpEL(rest.name(), joinPoint);
		String desc = SpelUtil.generateKeyBySpEL(rest.desc(), joinPoint);
		return MonitorUtil.monitor(joinPoint, name, desc, log);
	}
}

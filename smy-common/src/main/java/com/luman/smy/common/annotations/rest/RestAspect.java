/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2023.8
 */

package com.luman.smy.common.annotations.rest;


import com.luman.smy.common.constant.MonitorConstant;
import com.luman.smy.common.util.MonitorUtil;
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
		return MonitorUtil.monitor(joinPoint, log);
	}
}

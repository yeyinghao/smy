/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2023.12
 */

package com.luman.code.smy.annotations.web;

import com.luman.code.smy.constant.MonitorConstant;
import com.luman.code.smy.util.MonitorUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yeyinghao
 * @version 1.0.0
 * @description:
 * @date: 2023/2/25 21:59
 */
@Aspect
@Component
@RequiredArgsConstructor
@Slf4j(topic = MonitorConstant.WEB_MONITOR_LOGGER)
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
		return MonitorUtil.monitor(joinPoint, log);
	}
}

/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2024.1
 */

package com.luman.smy.infra.common.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

/**
 * Fliter配置
 *
 * @author yeyinghao
 * @date 2023/08/03
 */
@Configuration
public class FliterConfig {

	/**
	 * 跟踪id过滤器
	 *
	 * @return {@link FilterRegistrationBean}<{@link TraceIdFilter}>
	 */
	@Bean
	public FilterRegistrationBean<TraceIdFilter> traceIdFilter() {
		FilterRegistrationBean<TraceIdFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		TraceIdFilter traceIdFilter = new TraceIdFilter();
		filterRegistrationBean.setFilter(traceIdFilter);
		filterRegistrationBean.addUrlPatterns("/*");//配置过滤规则
		filterRegistrationBean.setName("traceIdFilter");//设置过滤器名称
		filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);//执行次序
		return filterRegistrationBean;
	}

}

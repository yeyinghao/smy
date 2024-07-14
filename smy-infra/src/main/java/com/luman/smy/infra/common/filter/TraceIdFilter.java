/*
 * QQ: 1113531030 WX: missyeyh Phone: 17689397484 Copyright (c) Ye Yinghao 2022.1 - 2023.2
 */

package com.luman.smy.infra.common.filter;

import com.luman.smy.infra.common.util.TraceIdUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.springframework.web.filter.OncePerRequestFilter;


/**
 * 跟踪id过滤器
 *
 * @author yeyinghao
 * @date 2023/08/01
 */
public class TraceIdFilter extends OncePerRequestFilter {

	@SneakyThrows
	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) {
		try {
			TraceIdUtil.getThreadTraceId();
			filterChain.doFilter(request, response);
		} finally {
			TraceIdUtil.clearThreadTraceId();
		}
	}

}

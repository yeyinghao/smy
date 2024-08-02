package com.luman.smy.infra.common.log;

import com.luman.smy.infra.common.exception.BizException;
import com.luman.smy.infra.common.util.LoggerUtil;
import com.luman.smy.infra.common.util.TimeUtils;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.slf4j.Logger;
import org.springframework.aop.support.AopUtils;

import java.util.List;

public abstract class LogAspect {

	public abstract Logger getLogger();

	private final Logger log = getLogger();

	private final static String LOG_TEMPLATE = "result={}, cost={}ms, className={}, methodName={}, request={}, response={}";

	@Around(value = "pointcut()")
	@SneakyThrows
	public Object proceed(ProceedingJoinPoint joinPoint) {
		long startTime = System.currentTimeMillis();
		List<Object> params = null;
		boolean res = false;
		Object resp = null;
		String className = null;
		String methodName = null;
		try {
			className = AopUtils.getTargetClass(joinPoint.getTarget()).getName();
			methodName = joinPoint.getSignature().getName();
			params = List.of(joinPoint.getArgs());
			res = true;
			resp = joinPoint.proceed();
			return resp;
		} catch (BizException e) {
			res = !e.isError();
			throw e;
		} finally {
			LoggerUtil.info(log, LOG_TEMPLATE, res, TimeUtils.getCostTime(startTime), className, methodName, params, resp);
		}
	}
}

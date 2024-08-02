package com.luman.smy.infra.common.log.web;

import com.luman.smy.infra.common.constant.CommConstant;
import com.luman.smy.infra.common.constant.LoggerConstant;
import com.luman.smy.infra.common.log.LogAspect;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebLogAspect extends LogAspect {

	@Override
	public Logger getLogger() {
		return LoggerFactory.getLogger(LoggerConstant.WEB_MONITOR_LOGGER);
	}

	/**
	 * <a href="https://blog.csdn.net/zhengchao1991/article/details/53391244">The syntax of pointcut </a>
	 */
	@Pointcut("@within(com.luman.smy.infra.common.log.web.WebLog) && execution(public * *(..))")
	public void pointcut() {
	}

	@Around(value = "pointcut()")
	public Object around(ProceedingJoinPoint joinPoint) {
		long startTime = System.currentTimeMillis();
		logRequest(joinPoint);
		Object response = null;
		String res = CommConstant.Y;
		try {
			response = joinPoint.proceed();
		} catch (Throwable e) {
			res = handleException(e);
		} finally {
			logResponse(res, startTime, response);
		}
		return response;
	}
}

package com.luman.smy.infra.common.log;

import com.luman.smy.infra.common.constant.CommConstant;
import com.luman.smy.infra.common.exception.BizException;
import com.luman.smy.infra.common.util.LoggerUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;

public abstract class LogAspect {

	public abstract Logger getLogger();

	private final Logger log = getLogger();

	public void logRequest(ProceedingJoinPoint joinPoint) {
		try {
			LoggerUtil.info(log, "START PROCESSING: {}", joinPoint.getSignature().toShortString());
			Object[] args = joinPoint.getArgs();
			for (Object arg : args) {
				LoggerUtil.info(log, "REQUEST: {}", String.valueOf(arg));
			}
		} catch (Throwable e) {
			LoggerUtil.error(log, e, "logReqeust error: {}", e.getMessage());
		}
	}

	public String handleException(Throwable e) {
		if (e instanceof BizException) {
			LoggerUtil.info(log, "BIZ EXCEPTION : {}", e.getMessage());
			if (!((BizException) e).isError()) {
				return CommConstant.Y;
			}
		}
		LoggerUtil.error(log, e, "UNKNOWN EXCEPTION: {}", e.getMessage());
		return CommConstant.N;
	}

	public void logResponse(String result, long startTime, Object response) {
		try {
			long endTime = System.currentTimeMillis();
			LoggerUtil.info(log, "RESPONSE: {}", String.valueOf(response));
			LoggerUtil.info(log, "RESULT: {}, COST: {}ms", result, (endTime - startTime));
		} catch (Throwable e) {
			LoggerUtil.error(log, e, "logResponse error: {}", e.getMessage());
		}
	}
}

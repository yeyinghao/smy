package com.luman.code.monitor.util;

import com.luman.code.util.enums.ErrorEnum;
import com.luman.code.util.exception.BizException;
import com.luman.code.util.util.CommUtil;
import com.luman.code.util.util.ErrorUtil;
import com.luman.code.util.util.LoggerUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;

import java.util.Arrays;
import java.util.List;

public class MonitorUtil {

	public static Object monitor(ProceedingJoinPoint joinPoint, Logger log) throws Throwable {
		long startTime = System.currentTimeMillis();
		List<Object> param = null;
		String className = null;
		String methodName = null;
		boolean res = true;
		Object resp = null;
		ErrorEnum errorEnum = null;
		try {
			className = joinPoint.getSignature().getDeclaringType().getSimpleName();
			methodName = joinPoint.getSignature().getName();
			param = Arrays.asList(joinPoint.getArgs());
			resp = joinPoint.proceed();
			return resp;
		} catch (BizException e) {
			errorEnum = e.getErrorEnum();
			res = !ErrorUtil.isError(errorEnum);
			throw e;
		} finally {
			LoggerUtil.info(log, className, methodName, param, resp, CommUtil.getStringByBoolean(res), errorEnum, CommUtil.getCostTime(startTime));
		}
	}
}

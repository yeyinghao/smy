package com.luman.smy.common.util;

import com.luman.smy.common.enums.ErrorEnum;
import com.luman.smy.common.exception.BizException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;

import java.util.Arrays;
import java.util.List;

public class MonitorUtil {

	public static Object monitor(ProceedingJoinPoint joinPoint, Logger log) throws Throwable {
		long startTime = System.currentTimeMillis();
		List<Object> param = null;
		boolean res = true;
		Object resp = null;
		ErrorEnum errorEnum = null;
		try {
			param = Arrays.asList(joinPoint.getArgs());
			resp = joinPoint.proceed();
			return resp;
		} catch (BizException e) {
			errorEnum = e.getErrorEnum();
			res = !ErrorUtil.isError(errorEnum);
			throw e;
		} finally {
			LoggerUtil.info(log, param, resp, errorEnum);
			LoggerUtil.info(log, joinPoint, res, startTime);
		}
	}
}

package com.luman.smy.infra.common.log.rest;

import cn.hutool.json.JSONUtil;
import com.luman.smy.infra.common.constant.LoggerConstant;
import com.luman.smy.infra.common.exception.BizException;
import com.luman.smy.infra.common.log.LogAspect;
import com.luman.smy.infra.common.log.LogInfo;
import com.luman.smy.infra.common.util.LoggerUtil;
import com.luman.smy.infra.common.util.TimeUtils;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class FacadeLogAspect extends LogAspect {

	private final static String LOG_TEMPLATE = "result={}, cost={}ms, className={}, methodName={}, request={}, response={}";

	@Override
	public Logger getLogger() {
		return LoggerFactory.getLogger(LoggerConstant.FACADE_LOG);
	}

	/**
	 * <a href="https://blog.csdn.net/zhengchao1991/article/details/53391244">The syntax of pointcut </a>
	 */
	@Pointcut("@within(FacadeLog) && execution(public * *(..))")
	public void pointcut() {
	}

	@Around(value = "pointcut()")
	@SneakyThrows
	public Object proceed(ProceedingJoinPoint joinPoint) {
		LogInfo logInfo = new LogInfo();
		try {
			logInfo = buildLogInfo(joinPoint);
			Object resp = joinPoint.proceed();
			logInfo.setResponse(resp);
			return resp;
		} catch (BizException e) {
			logInfo.setRes(!e.isError());
			throw e;
		} finally {
			printLog(logInfo);
		}
	}

	@Override
	public void printLog(LogInfo logInfo) {
		LoggerUtil.info(logInfo.getLog(), LOG_TEMPLATE, logInfo.getRes(), TimeUtils.getCostTime(logInfo.getStartTime()), logInfo.getClassName(), logInfo.getMethodName(), JSONUtil.toJsonStr(logInfo.getArgs()), JSONUtil.toJsonStr(logInfo.getResponse()));
	}
}

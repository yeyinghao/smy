package com.luman.smy.infra.common.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.springframework.aop.support.AopUtils;

import java.util.List;

public abstract class LogAspect {

	public abstract Logger getLogger();


	public LogInfo buildLogInfo(ProceedingJoinPoint joinPoint) {
		LogInfo logInfo = new LogInfo();
		logInfo.setLog(getLogger());
		logInfo.setRes(false);
		logInfo.setStartTime(System.currentTimeMillis());
		logInfo.setClassName(AopUtils.getTargetClass(joinPoint.getTarget()).getSimpleName());
		logInfo.setMethodName(joinPoint.getSignature().getName());
		logInfo.setArgs(List.of(joinPoint.getArgs()));
		return logInfo;
	}

	public abstract void printLog(LogInfo logInfo);

}

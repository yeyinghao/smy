package com.luman.smy.infra.common.log.rest;

import com.luman.smy.infra.common.constant.LoggerConstant;
import com.luman.smy.infra.common.log.LogAspect;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class RestLogAspect extends LogAspect {

	@Override
	public Logger getLogger() {
		return LoggerFactory.getLogger(LoggerConstant.REST_MONITOR_LOGGER);
	}

	/**
	 * <a href="https://blog.csdn.net/zhengchao1991/article/details/53391244">The syntax of pointcut </a>
	 */
	@Pointcut("@within(RestLog) && execution(public * *(..))")
	public void pointcut() {
	}

}

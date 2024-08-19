package com.luman.smy.infra.common.log.web;

import cn.hutool.extra.validation.BeanValidationResult;
import cn.hutool.extra.validation.ValidationUtil;
import cn.hutool.json.JSONUtil;
import com.luman.smy.client.dto.DTO;
import com.luman.smy.infra.common.constant.CommConstant;
import com.luman.smy.infra.common.enums.CommErrorEnum;
import com.luman.smy.infra.common.exception.BizException;
import com.luman.smy.infra.common.exception.Checker;
import com.luman.smy.infra.common.helper.RHelper;
import com.luman.smy.infra.common.log.LogAspect;
import com.luman.smy.infra.common.log.LogInfo;
import com.luman.smy.infra.common.util.LoggerUtil;
import com.luman.smy.infra.common.util.TimeUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Collectors;

@Component
@Aspect
@Slf4j
public class WebLogAspect extends LogAspect {

	private final static String LOG_TEMPLATE = "result={}, cost={}ms, target={}#{}, request={}, response={}";

	/**
	 * <a href="https://blog.csdn.net/zhengchao1991/article/details/53391244">The syntax of pointcut </a>
	 */
	@Pointcut("@within(WebLog) && execution(public * *(..))")
	public void pointcut() {
	}

	@Around(value = "pointcut()")
	@SneakyThrows
	public Object proceed(ProceedingJoinPoint joinPoint) {
		LogInfo logInfo = new LogInfo();
		Object resp = null;
		try {
			WebLog log = getAnnotation(joinPoint, WebLog.class);
			logInfo = buildLogInfo(joinPoint, log.topic());
			Object[] args = joinPoint.getArgs();
			for (Object arg : args) {
				if (arg instanceof DTO) {
					preCheck((DTO) arg);
				}
			}
			resp = joinPoint.proceed();
			return resp;
		} catch (BizException e) {
			LoggerUtil.info(log, e);
			logInfo.setRes(!e.isError());
			resp = RHelper.fail(e.getErrorEnum(), e.getMessage());
		} catch (Throwable e) {
			LoggerUtil.error(log, e);
			resp = RHelper.fail(CommErrorEnum.SYS_ERROR);
		} finally {
			logInfo.setResponse(resp);
			printLog(logInfo);
		}
		return resp;
	}

	@Override
	public void printLog(LogInfo logInfo) {
		LoggerUtil.info(logInfo.getLog(), LOG_TEMPLATE, logInfo.getRes(), TimeUtils.getCostTime(logInfo.getStartTime()), logInfo.getClassName(), logInfo.getMethodName(), JSONUtil.toJsonStr(logInfo.getArgs()), JSONUtil.toJsonStr(logInfo.getResponse()));
	}

	/**
	 * validate校验
	 *
	 * @param request 请求
	 */
	private static void preCheck(DTO request) {
		if (Objects.isNull(request)) {
			return;
		}
		// 获取校验结果
		BeanValidationResult result = ValidationUtil.warpValidate(request);
		// 校验失败 抛错误
		Checker.isTrue(result.isSuccess(), CommErrorEnum.ILLEGAL_PARAMETER, result.getErrorMessages().stream().map(item -> item.getPropertyName() + CommConstant.COLON + item.getMessage()).collect(Collectors.joining(CommConstant.SEMICOLON)));
	}
}

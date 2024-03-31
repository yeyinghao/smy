/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2024.1
 */

package com.luman.code.limiter.annotation;

import com.luman.code.limiter.config.LimiterConfig;
import com.luman.code.monitor.constant.LogConstant;
import com.luman.code.util.enums.CommErrorEnum;
import com.luman.code.util.enums.ErrorEnum;
import com.luman.code.util.exception.Assert;
import com.luman.code.util.exception.BizException;
import com.luman.code.util.util.CommUtil;
import com.luman.code.util.util.ErrorEnumUtil;
import com.luman.code.util.util.LoggerUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

/**
 * redis限制切面
 *
 * @author yeyinghao
 * @date 2023/12/18
 */
@Aspect
@Component
@RequiredArgsConstructor
@Slf4j(topic = LogConstant.LIMITER_MONITOR_LOGGER)
public class RedisLimitAspect {

	/**
	 * redisson客户
	 */
	private final RedissonClient redissonClient;

	/**
	 * 限流器配置
	 */
	private final LimiterConfig limiterConfig;

	/**
	 * 周围
	 *
	 * @param joinPoint  连接点
	 * @param redisLimit redis限制
	 * @return {@link Object}
	 */
	@Around("@annotation(redisLimit)")
	private Object around(ProceedingJoinPoint joinPoint, RedisLimit redisLimit) throws Throwable {
		long startTime = System.currentTimeMillis();
		boolean res = true;
		ErrorEnum errorEnum = null;
		String key = null;
		boolean tryAcquire = true;
		try {
			Object generateKey = limiterConfig.getKey(joinPoint, redisLimit);
			//redis key
			key = redisLimit.prefix() + generateKey.toString();
			//声明一个限流器
			RRateLimiter rateLimiter = redissonClient.getRateLimiter(key);
			//设置速率，time秒中产生count个令牌
			rateLimiter.trySetRate(RateType.OVERALL, redisLimit.count(), redisLimit.time(), RateIntervalUnit.SECONDS);
			// 获取令牌失败,则提示限流
			tryAcquire = rateLimiter.tryAcquire();
			Assert.isFalse(tryAcquire, CommErrorEnum.BIZ_PROCESS_FAIL, "访问过于频繁,请稍后再试");
			return joinPoint.proceed();
		} catch (BizException e) {
			errorEnum = e.getErrorEnum();
			res = !ErrorEnumUtil.isError(errorEnum);
			throw e;
		} finally {
			LoggerUtil.info(log, key, CommUtil.getStringByBoolean(tryAcquire), CommUtil.getStringByBoolean(res), errorEnum, CommUtil.getCostTime(startTime));
		}
	}
}

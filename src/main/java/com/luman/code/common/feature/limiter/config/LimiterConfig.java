/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2023.12
 */

package com.luman.code.common.feature.limiter.config;

import cn.dev33.satoken.stp.StpUtil;
import com.luman.code.common.annotations.limiter.RedisLimit;
import com.luman.code.common.feature.limiter.enums.LimitType;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * 限流器配置
 *
 * @author yeyinghao
 * @date 2023/12/18
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class LimiterConfig {
	/**
	 * 请求
	 */
	private final HttpServletRequest request;

	/**
	 * 函数图
	 */
	private final Map<LimitType, Function<ProceedingJoinPoint, String>> functionMap = new HashMap<>(9);

	/**
	 * 初始化地图
	 */
	@PostConstruct
	void initMap() {
		//初始化策略
		functionMap.put(LimitType.METHOD, this::getMethodTypeKey);
		functionMap.put(LimitType.PARAMS, this::getParamsTypeKey);
		functionMap.put(LimitType.USER, this::getUserTypeKey);
		functionMap.put(LimitType.REQUEST_URI, proceedingJoinPoint -> request.getRequestURI());
		functionMap.put(LimitType.REQUESTURI_USERID, proceedingJoinPoint -> request.getRequestURI() + getUserId());
		functionMap.put(LimitType.REQUEST_URI_PARAMS, proceedingJoinPoint -> request.getRequestURI() + getParams(proceedingJoinPoint));
		functionMap.put(LimitType.REQUEST_URI_PARAMS_USERID, proceedingJoinPoint -> request.getRequestURI() + getParams(proceedingJoinPoint) + getUserId());
		functionMap.put(LimitType.SINGLEUSER, (proceedingJoinPoint) -> String.valueOf(getUserId()));
		functionMap.put(LimitType.SINGLEMETHOD, (proceedingJoinPoint -> {
			StringBuilder sb = new StringBuilder();
			appendMthodName(proceedingJoinPoint, sb);
			return sb.toString();
		}));
	}

	/**
	 * 获取key
	 *
	 * @param joinPoint  连接点
	 * @param redisLimit redis限制
	 * @return {@link Object}
	 */
	public Object getKey(ProceedingJoinPoint joinPoint, RedisLimit redisLimit) {
		// 根据限制类型生成key
		Object generateKey;
		// 自定义
		if (redisLimit.type() != LimitType.CUSTOM) {
			generateKey = functionMap.get(redisLimit.type()).apply(joinPoint);
		} else {
			// 非自定义
			generateKey = redisLimit.key();
		}
		return generateKey;
	}

	/**
	 * 获取methodtypekey
	 * 方法级别
	 * key = ClassName+MethodName
	 *
	 * @param joinPoint 连接点
	 * @return {@link String}
	 */
	private String getMethodTypeKey(ProceedingJoinPoint joinPoint) {
		StringBuilder sb = new StringBuilder();
		appendMthodName(joinPoint, sb);
		return sb.toString();
	}

	/**
	 * 获取paramstypekey
	 * 参数级别
	 * key = ClassName+MethodName+Params
	 *
	 * @param joinPoint 连接点
	 * @return {@link String}
	 */
	private String getParamsTypeKey(ProceedingJoinPoint joinPoint) {
		StringBuilder sb = new StringBuilder();
		appendMthodName(joinPoint, sb);
		appendParams(joinPoint, sb);
		return sb.toString();
	}

	/**
	 * 获取usertypekey
	 * 用户级别
	 * key = ClassName+MethodName+Params+UserId
	 *
	 * @param joinPoint 连接点
	 * @return {@link String}
	 */
	private String getUserTypeKey(ProceedingJoinPoint joinPoint) {
		StringBuilder sb = new StringBuilder();
		appendMthodName(joinPoint, sb);
		appendParams(joinPoint, sb);
		//获取userId
		appendUserId(sb);
		return sb.toString();
	}

	/**
	 * StringBuilder添加类名和方法名
	 *
	 * @param joinPoint 连接点
	 * @param sb        某人
	 */
	private void appendMthodName(ProceedingJoinPoint joinPoint, StringBuilder sb) {
		Signature signature = joinPoint.getSignature();
		MethodSignature methodSignature = (MethodSignature) signature;
		Method method = methodSignature.getMethod();
		sb.append(joinPoint.getTarget().getClass().getName())//类名
				.append(method.getName());//方法名
	}

	/**
	 * StringBuilder添加方法参数值
	 *
	 * @param joinPoint 连接点
	 * @param sb        某人
	 */
	private void appendParams(ProceedingJoinPoint joinPoint, StringBuilder sb) {
		for (Object o : joinPoint.getArgs()) {
			sb.append(o.toString());
		}
	}

	/**
	 * 获取params
	 *
	 * @param joinPoint 连接点
	 * @return {@link String}
	 */
	@SneakyThrows
	private String getParams(ProceedingJoinPoint joinPoint) {
		StringBuilder sb = new StringBuilder();
		for (Object o : joinPoint.getArgs()) {
			if (o instanceof MultipartFile) {
				ImageIO.read(((MultipartFile) o).getInputStream());
			} else {
				sb.append(o);
			}
		}
		return sb.toString();
	}

	/**
	 * 附加用户id
	 *
	 * @param sb 某人
	 */
	private void appendUserId(StringBuilder sb) {
		sb.append(getUserId());
	}

	private Object getUserId() {
		return StpUtil.getLoginId();
	}
}

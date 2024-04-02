/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2024.1
 */

package com.luman.code.smy.annotations.limiter;

import com.luman.code.smy.feature.limiter.enums.LimitType;

import java.lang.annotation.*;

/**
 * aop限流注解
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface RedisLimit {

	String prefix() default "rateLimit:";

	//限流唯一标示
	String key() default "";

	//限流单位时间（单位为s）
	int time() default 1;

	//单位时间内限制的访问次数
	int count();

	//限流类型
	LimitType type() default LimitType.CUSTOM;

}

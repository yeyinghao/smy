/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2023.12
 */

package com.luman.code.limiter.enums;

import com.luman.code.util.constant.HttpConstant;
import com.luman.code.util.enums.ErrorEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * @author yeyinghao
 * @version 1.0.0
 * @description:
 * @date: 2022/6/5 13:55
 */
@ToString
@Getter
@RequiredArgsConstructor
public enum LimiterErrorEnum implements ErrorEnum {

	/**
	 * 访问过于频繁,请稍后再试
	 */
	TOO_MANY_REQUEST(HttpConstant.FORBIDDEN, "访问过于频繁,请稍后再试"),

	;

	/**
	 * 响应码
	 */
	private final Integer code;

	/**
	 * 响应业务码的描述
	 */
	private final String description;
}

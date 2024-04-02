/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2023.12
 */

package com.luman.code.common.feature.limiter.enums;

/**
 * 限制类型
 *
 * @author yeyinghao
 * @date 2023/12/18
 */
public enum LimitType {

	/**
	 * 自定义key
	 */
	CUSTOM,

	/**
	 * 请求者IP
	 */
	IP,

	/**
	 * 方法级别限流
	 * key = ClassName+MethodName
	 */
	METHOD,

	/**
	 * 参数级别限流
	 * key = ClassName+MethodName+Params
	 */
	PARAMS,

	/**
	 * 用户级别限流
	 * key = ClassName+MethodName+Params+UserId
	 */
	USER,

	/**
	 * 根据request的uri限流
	 * key = Request_uri
	 */
	REQUEST_URI,

	/**
	 * 对requesturi+userId限流
	 * key = Request_uri+UserId
	 */
	REQUESTURI_USERID,


	/**
	 * 对userId限流
	 * key = userId
	 */
	SINGLEUSER,

	/**
	 * 对方法限流
	 * key = ClassName+MethodName
	 */
	SINGLEMETHOD,

	/**
	 * 对uri+params限流
	 * key = uri+params
	 */
	REQUEST_URI_PARAMS,

	/**
	 * 对uri+params+userId限流
	 * key = uri+params+userId
	 */
	REQUEST_URI_PARAMS_USERID
}

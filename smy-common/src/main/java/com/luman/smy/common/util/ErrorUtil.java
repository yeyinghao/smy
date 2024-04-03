/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2023.7
 */

package com.luman.smy.common.util;

import com.luman.smy.common.constant.HttpConstant;
import com.luman.smy.common.enums.ErrorEnum;

import java.util.Arrays;
import java.util.List;

/**
 * 基础枚举工具类
 *
 * @author yeyinghao
 * @date 2023/09/16
 */
public class ErrorUtil {

	/**
	 * http状态
	 */
	private static final List<Integer> HTTP_STATUSES =
			Arrays.asList(HttpConstant.INTERNAL_SERVER_ERROR, HttpConstant.SERVICE_UNAVAILABLE);

	/**
	 * 是错误
	 *
	 * @param errorEnum 基本枚举
	 * @return {@link Boolean}
	 */
	public static Boolean isError(ErrorEnum errorEnum) {
		return HTTP_STATUSES.contains(errorEnum.getCode());
	}

}

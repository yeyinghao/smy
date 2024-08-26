package com.luman.smy.infra.common.exception;

import cn.hutool.core.util.StrUtil;
import com.luman.smy.infra.common.enums.ErrorEnum;

public class ThrowUtil {

	/**
	 * 业务异常
	 *
	 * @param errorEnum 错误枚举
	 */
	public static void throwEx(ErrorEnum errorEnum) {
		throw ExceptionFactory.buildBizException(errorEnum);
	}

	/**
	 * 业务异常
	 *
	 * @param errorEnum  错误枚举
	 * @param subMessage 错误消息
	 */
	public static void throwEx(ErrorEnum errorEnum, String subMessage) {
		throw ExceptionFactory.buildBizException(errorEnum, subMessage);
	}

	/**
	 * 业务异常
	 *
	 * @param errorEnum   错误枚举
	 * @param subMessages 错误消息
	 */
	public static void throwEx(ErrorEnum errorEnum, String template, Object... subMessages) {
		throw ExceptionFactory.buildBizException(errorEnum, StrUtil.format(template, subMessages));
	}

	/**
	 * 业务异常
	 *
	 * @param errorEnum 错误枚举
	 */
	public static void throwEx(ErrorEnum errorEnum, Throwable throwable) {
		throw ExceptionFactory.buildBizException(errorEnum, throwable);
	}

	/**
	 * 业务异常
	 *
	 * @param errorEnum  错误枚举
	 * @param subMessage 错误消息
	 */
	public static void throwEx(ErrorEnum errorEnum, Throwable throwable, String subMessage) {
		throw ExceptionFactory.buildBizException(errorEnum, throwable, subMessage);
	}

	/**
	 * 业务异常
	 *
	 * @param errorEnum   错误枚举
	 * @param subMessages 错误消息
	 */
	public static void throwEx(ErrorEnum errorEnum, Throwable throwable, String template, Object... subMessages) {
		throw ExceptionFactory.buildBizException(errorEnum, throwable, StrUtil.format(template, subMessages));
	}
}

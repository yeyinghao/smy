package com.luman.smy.infra.common.exception;

import com.luman.smy.infra.common.enums.ErrorEnum;

public class ExceptionFactory {

	/**
	 * 业务异常
	 *
	 * @param errorEnum 错误枚举
	 * @return {@link BizException }
	 */
	public static BizException buildBizException(ErrorEnum errorEnum) {
		return new BizException(errorEnum);
	}

	/**
	 * 业务异常
	 *
	 * @param errorEnum    错误枚举
	 * @param errorMessage 错误消息
	 * @return {@link BizException }
	 */
	public static BizException buildBizException(ErrorEnum errorEnum, String errorMessage) {
		return new BizException(errorEnum, errorMessage);
	}

	/**
	 * 业务异常
	 *
	 * @param errorEnum 错误枚举
	 * @param throwable throwable
	 * @return {@link BizException }
	 */
	public static BizException buildBizException(ErrorEnum errorEnum, Throwable throwable) {
		return new BizException(errorEnum, throwable);
	}

	/**
	 * 业务异常
	 *
	 * @param errorEnum    错误枚举
	 * @param throwable    throwable
	 * @param errorMessage 错误消息
	 * @return {@link BizException }
	 */
	public static BizException buildBizException(ErrorEnum errorEnum, Throwable throwable, String errorMessage) {
		return new BizException(errorEnum, errorMessage, throwable);
	}

}

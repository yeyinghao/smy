package com.luman.smy.infra.common.exception;

import com.luman.smy.infra.common.constant.HttpConstant;
import com.luman.smy.infra.common.enums.ErrorEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/**
 * 业务异常
 *
 * @author yeyinghao
 * @date 2023/08/01
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public class BizException extends RuntimeException {

	/**
	 * http状态
	 */
	private static final List<Integer> HTTP_STATUSES = Arrays.asList(HttpConstant.INTERNAL_SERVER_ERROR, HttpConstant.SERVICE_UNAVAILABLE);

	/**
	 * 基本枚举
	 */
	private final ErrorEnum errorEnum;

	public BizException(ErrorEnum errorEnum) {
		super(errorEnum.getDescription());
		this.errorEnum = errorEnum;
	}

	/**
	 * 业务异常
	 *
	 * @param errorEnum 基本枚举
	 * @param message   子消息
	 */
	public BizException(ErrorEnum errorEnum, String message) {
		super(message);
		this.errorEnum = errorEnum;
	}

	public BizException(ErrorEnum errorEnum, Throwable ex) {
		super(errorEnum.getDescription(), ex);
		this.errorEnum = errorEnum;
	}

	public BizException(ErrorEnum errorEnum, String message, Throwable ex) {
		super(message, ex);
		this.errorEnum = errorEnum;
	}

	@Override
	public String toString() {
		return "SmyBizException{" +
				"errorEnum=" + errorEnum +
				"message=" + getMessage() +
				'}';
	}

	public boolean isError() {
		return HTTP_STATUSES.contains(errorEnum.getCode());
	}
}

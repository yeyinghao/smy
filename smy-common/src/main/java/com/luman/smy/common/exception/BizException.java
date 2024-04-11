package com.luman.smy.common.exception;

import com.luman.smy.common.constant.CommConstant;
import com.luman.smy.common.constant.HttpConstant;
import com.luman.smy.common.enums.ErrorEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Arrays;
import java.util.List;

/**
 * 业务异常
 *
 * @author yeyinghao
 * @date 2023/08/01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BizException extends RuntimeException {

	/**
	 * http状态
	 */
	private static final List<Integer> HTTP_STATUSES =
			Arrays.asList(HttpConstant.INTERNAL_SERVER_ERROR, HttpConstant.SERVICE_UNAVAILABLE);


	/**
	 * 基本枚举
	 */
	private ErrorEnum errorEnum;

	/**
	 * 子消息
	 */
	private Object[] subMessage;

	/**
	 * 业务异常
	 *
	 * @param errorEnum  基本枚举
	 * @param subMessage 子消息
	 */
	public BizException(ErrorEnum errorEnum, Object... subMessage) {
		super(errorEnum.getDescription());
		this.errorEnum = errorEnum;
		this.subMessage = subMessage;
	}

	public String getResult() {
		return HTTP_STATUSES.contains(this.errorEnum.getCode()) ? CommConstant.Y : CommConstant.N;
	}
}

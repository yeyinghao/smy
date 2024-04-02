package com.luman.code.smy.exception;

import com.luman.code.smy.enums.ErrorEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
	 * 基本枚举
	 */
	private ErrorEnum errorEnum;

	/**
	 * 子消息
	 */
	private String[] subMessage;

	/**
	 * 业务异常
	 *
	 * @param errorEnum   基本枚举
	 * @param subMessage 子消息
	 */
	public BizException(ErrorEnum errorEnum, String... subMessage) {
		super(errorEnum.getDescription());
		this.errorEnum = errorEnum;
		this.subMessage = subMessage;
	}
}

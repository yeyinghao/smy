package com.luman.code.util.exception;

import com.luman.code.util.enums.ErrorEnum;
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
	private String[] subMesssage;

	/**
	 * 业务异常
	 *
	 * @param errorEnum   基本枚举
	 * @param subMesssage 子消息
	 */
	public BizException(ErrorEnum errorEnum, String... subMesssage) {
		super(errorEnum.getDescription());
		this.errorEnum = errorEnum;
		this.subMesssage = subMesssage;
	}
}

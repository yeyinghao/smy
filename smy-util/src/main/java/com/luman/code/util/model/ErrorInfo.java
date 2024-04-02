/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2024.3
 */

package com.luman.code.util.model;

import com.luman.code.util.enums.BaseEnum;
import com.luman.code.util.model.base.INFO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 错误信息
 *
 * @author yeyinghao
 * @date 2024/04/01
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class ErrorInfo extends INFO {

	/**
	 * 错误代码
	 */
	private String errorCode;

	/**
	 * 错误msg
	 */
	private String errorMsg;

	/**
	 * 错误信息
	 *
	 * @param baseEnum 基础枚举
	 */
	public ErrorInfo(BaseEnum baseEnum) {
		this.errorCode = baseEnum.name();
		this.errorMsg = baseEnum.getDescription();
	}
}

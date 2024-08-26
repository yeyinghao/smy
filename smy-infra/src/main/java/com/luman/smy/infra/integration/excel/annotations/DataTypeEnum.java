/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2024.3
 */

package com.luman.smy.infra.integration.excel.annotations;

import com.luman.smy.client.enums.ByCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * 数据类型enum
 *
 * @author yeyinghao
 * @date 2024/04/04
 */
@Getter
@RequiredArgsConstructor
@ToString
public enum DataTypeEnum implements ByCode {

	/**
	 * 字符串
	 */
	STRING("STRING", "字符串"),

	/**
	 * 日期
	 */
	DATE("DATE", "日期"),

	/**
	 * 数字
	 */
	NUMBER("NUMBER", "数字"),

	;

	/**
	 * 代码
	 */
	private final String code;

	/**
	 * 响应业务码的描述
	 */
	private final String desc;
}

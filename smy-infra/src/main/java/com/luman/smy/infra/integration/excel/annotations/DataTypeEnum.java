/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2024.3
 */

package com.luman.smy.infra.integration.excel.annotations;

import com.luman.smy.infra.common.enums.BaseEnum;
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
public enum DataTypeEnum implements BaseEnum {

	/**
	 * 字符串
	 */
	STRING("字符串"),

	/**
	 * 日期
	 */
	DATE("日期"),

	/**
	 * 数字
	 */
	NUMBER("数字"),

	;

	/**
	 * 响应业务码的描述
	 */
	private final String description;
}

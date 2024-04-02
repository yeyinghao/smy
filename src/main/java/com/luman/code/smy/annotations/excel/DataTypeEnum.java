/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2024.3
 */

package com.luman.code.smy.annotations.excel;

import com.luman.code.smy.enums.BaseEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

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

/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2024.1
 */

package com.luman.smy.cache.constant;

import com.luman.smy.common.enums.BaseEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * 基础常数
 *
 * @author yeyinghao
 * @date 2024/04/04
 */
@Getter
@RequiredArgsConstructor
@ToString
public enum CalEnum implements BaseEnum {

	/**
	 * 获取对象
	 */
	GET("获取对象"),

	;

	/**
	 * 响应业务码的描述
	 */
	private final String description;
}

/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2023.12
 */

package com.luman.code.common.feature.file.enums;

import com.luman.code.common.enums.BaseEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public enum FileTypeEnum implements BaseEnum {

	/**
	 * 其他文件
	 */
	OTHER("other/", "其他文件"),

	/**
	 * 临时文件
	 */
	TEMPORARY("temporary/", "临时文件"),

	/**
	 * 案件文件
	 */
	CASE("case/", "其他文件"),

	/**
	 * 模板文件
	 */
	TEMPLATE("template/", "模板文件"),

	;

	/**
	 * 路径
	 */
	private final String path;

	/**
	 * 响应业务码的描述
	 */
	private final String description;

}

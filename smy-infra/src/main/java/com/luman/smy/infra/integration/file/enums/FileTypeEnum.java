/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2023.12
 */

package com.luman.smy.infra.integration.file.enums;

import com.luman.smy.infra.common.enums.BaseEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * 文件类型enum
 *
 * @author yeyinghao
 * @date 2024/04/04
 */
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
	 * 业务文件
	 */
	BIZ("biz/", "业务文件"),

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

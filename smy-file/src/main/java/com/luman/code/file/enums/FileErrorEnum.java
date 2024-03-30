/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2024.1
 */

package com.luman.code.file.enums;

import com.luman.code.util.constant.HttpConstant;
import com.luman.code.util.enums.ErrorEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * @author yeyinghao
 * @version 1.0.0
 * @description:
 * @date: 2022/6/5 13:55
 */
@ToString
@Getter
@RequiredArgsConstructor
public enum FileErrorEnum implements ErrorEnum {

	/**
	 * 文件名不为空
	 */
	FILE_NAME_NOT_BLANK(HttpConstant.BAD_REQUEST, "文件名称不能为空"),

	/**
	 * 文件批量删除失败
	 */
	FILE_BATCH_DELETE_FAIL(HttpConstant.INTERNAL_SERVER_ERROR, "批量删除文件失败"),
	;

	/**
	 * 响应码
	 */
	private final Integer code;

	/**
	 * 响应业务码的描述
	 */
	private final String description;
}

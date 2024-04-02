/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2023.8
 */

package com.luman.code.common.model;

import com.luman.code.common.model.base.RES;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 页面响应
 *
 * @author yeyinghao
 * @date 2023/08/04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class PageRes<T> extends RES {

	/**
	 * 当前页
	 */
	private Integer pageIndex;

	/**
	 * 条数
	 */
	private Integer pageSize;

	/**
	 * 总条数
	 */
	private Integer totalSize;

	/**
	 * 记录
	 */
	private List<T> records;
}

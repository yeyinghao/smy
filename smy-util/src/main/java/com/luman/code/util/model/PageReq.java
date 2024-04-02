/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2023.8
 */

package com.luman.code.util.model;

import com.luman.code.util.model.base.REQ;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 页面请求
 *
 * @author yeyinghao
 * @date 2023/08/04
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PageReq extends REQ {

	/**
	 * 当前页
	 */
	@NotNull(message = "当前页不能为空")
	@Min(value = 1, message = "当前页最小值为1")
	public Integer pageIndex;

	/**
	 * 条数
	 */
	@NotNull(message = "条数不能为空")
	@Range(min = 1, max = 200, message = "条数范围在1-200之间")
	public Integer pageSize;
}

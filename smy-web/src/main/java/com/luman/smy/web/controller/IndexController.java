/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2024.1
 */

package com.luman.smy.web.controller;

import com.luman.smy.common.helper.ResultHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 首页控制器
 *
 * @author yeyinghao
 * @date 2023/12/15
 */
@RestController
public class IndexController {

	/**
	 * 首页
	 *
	 * @return {@link ResultHelper}<{@link Boolean}>
	 */
	@GetMapping
	public ResultHelper<String> index() {
		return ResultHelper.of("service is running");
	}
}

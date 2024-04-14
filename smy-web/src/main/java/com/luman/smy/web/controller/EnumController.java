/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2024.1
 */

package com.luman.smy.web.controller;

import com.luman.smy.common.enums.BaseEnum;
import com.luman.smy.common.helper.ResultHelper;
import com.luman.smy.web.enums.EnumServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 首页控制器
 *
 * @author yeyinghao
 * @date 2023/12/15
 */
@RestController("/api/enum")
public class EnumController {

	/**
	 * 首页
	 *
	 * @return {@link ResultHelper}<{@link Boolean}>
	 */
	@GetMapping("/{enumName}")
	public ResultHelper<List<BaseEnum>> getEnumByName(@PathVariable("enumName") String enumName) {
		return ResultHelper.of(EnumServiceImpl.getEnumClass(enumName));
	}
}

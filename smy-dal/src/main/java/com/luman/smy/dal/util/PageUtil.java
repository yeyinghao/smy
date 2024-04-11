/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2023.8
 */

package com.luman.smy.dal.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.luman.smy.common.model.PageRes;
import com.luman.smy.common.util.CopyUtil;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * 页转换器
 *
 * @author yeyinghao
 * @date 2023/08/04
 */
public class PageUtil {

	/**
	 * 构建页面
	 *
	 * @param page    页面
	 * @param results 结果
	 * @return {@link PageRes}<{@link T}>
	 */
	public static <S, T> PageRes<T> buildPage(IPage<S> page, List<T> results) {
		return new PageRes<>(page.getPages(), page.getSize(), page.getTotal(), results);
	}


	/**
	 * 构建页面
	 *
	 * @param page   页面
	 * @param target 目标
	 * @return {@link PageRes}<{@link T}>
	 */
	public static <S, T> PageRes<T> buildPage(PageRes<S> page, Supplier<T> target) {
		return buildPage(page, target, null);
	}

	/**
	 * 构建页面
	 *
	 * @param page     页面
	 * @param target   目标
	 * @param callBack 回调
	 * @return {@link PageRes}<{@link T}>
	 */
	public static <S, T> PageRes<T> buildPage(PageRes<S> page, Supplier<T> target, BiFunction<S, T, T> callBack) {
		return new PageRes<>(page.getPageIndex(), page.getPageSize(), page.getTotalSize(), CopyUtil.copyList(page.getRecords(), target, callBack));
	}
}

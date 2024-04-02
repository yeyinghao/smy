/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2023.9
 */

package com.luman.code.common.converter;


/**
 * Bean utils回调
 *
 * @author yeyinghao
 * @date 2023/08/01
 */
@FunctionalInterface
public interface BeanUtilsCallBack<S, T> {
	/**
	 * 回调
	 *
	 * @param source 源
	 * @param target 目标
	 */
	void callBack(S source, T target);
}

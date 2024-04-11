/*
 * QQ: 1113531030 WX: missyeyh Phone: 17689397484 Copyright (c) Ye Yinghao 2022.1 - 2023.2
 */

package com.luman.smy.common.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;


/**
 * 公共转换器
 *
 * @author yeyinghao
 * @date 2024/04/04
 */
public class CopyUtil {

	/**
	 * 构建
	 *
	 * @param source 源
	 * @param target 目标
	 * @return {@link T}
	 */
	public static <S, T> T copy(S source, Supplier<T> target) {
		return copy(source, target, null);
	}

	/**
	 * 构建
	 *
	 * @param source   源
	 * @param target   目标
	 * @param callBack 回调函数
	 * @return {@link T}
	 */
	public static <S, T> T copy(S source, Supplier<T> target, BiFunction<S, T, T> callBack) {
		if (null == source) {
			return null;
		}
		T t = target.get();
		BeanUtil.copyProperties(source, t);
		if (callBack != null) {
			// 回调
			callBack.apply(source, t);
		}
		return t;
	}

	/**
	 * 构建列表
	 *
	 * @param sources 来源
	 * @param target  目标
	 * @return {@link List}<{@link T}>
	 */
	public static <S, T> List<T> copyList(List<S> sources, Supplier<T> target) {
		return copyList(sources, target, null);
	}

	/**
	 * 构建列表
	 *
	 * @param sources  来源
	 * @param target   目标
	 * @param callBack 回调函数
	 * @return {@link List}<{@link T}>
	 */
	public static <S, T> List<T> copyList(List<S> sources, Supplier<T> target, BiFunction<S, T, T> callBack) {
		if (CollectionUtil.isEmpty(sources)) {
			return new ArrayList<>();
		}
		List<T> list = new ArrayList<>(sources.size());
		for (S source : sources) {
			T t = target.get();
			BeanUtil.copyProperties(source, t);
			if (callBack != null) {
				// 回调
				callBack.apply(source, t);
			}
			list.add(t);
		}
		return list;
	}
}

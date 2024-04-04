/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2023.12
 */

package com.luman.smy.common.exception;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.luman.smy.common.enums.ErrorEnum;

import java.util.Map;
import java.util.Objects;

/**
 * 断言
 *
 * @author yeyinghao
 * @date 2023/12/10
 */
public class Assert {

	/**
	 * 抛出异常
	 */
	private static void throwEx(boolean condition, ErrorEnum errorCode, String... subMessage) {
		if (condition) {
			throw new BizException(errorCode, subMessage);
		}
	}

	/**
	 * 条件成立则抛异常, 带子消息
	 *
	 * @param condition  表达
	 * @param errorCode  错误代码
	 * @param subMessage 子消息
	 */
	public static void isTrue(boolean condition, ErrorEnum errorCode, String... subMessage) {
		throwEx(!condition, errorCode, subMessage);
	}

	/**
	 * 条件不成立则抛异常, 带子消息
	 *
	 * @param condition  条件
	 * @param errorCode  错误代码
	 * @param subMessage 子消息
	 */
	public static void isFalse(boolean condition, ErrorEnum errorCode, String... subMessage) {
		throwEx(condition, errorCode, subMessage);
	}

	/**
	 * 对象为空则抛异常, 带子消息
	 *
	 * @param obj        obj
	 * @param errorCode  错误代码
	 * @param subMessage 子消息
	 */
	public static void notNull(Object obj, ErrorEnum errorCode, String... subMessage) {
		throwEx(Objects.isNull(obj), errorCode, subMessage);
	}

	/**
	 * 对象不为空则抛异常, 带子消息
	 *
	 * @param obj        obj
	 * @param errorCode  错误代码
	 * @param subMessage 子消息
	 */
	public static void isNull(Object obj, ErrorEnum errorCode, String... subMessage) {
		throwEx(Objects.nonNull(obj), errorCode, subMessage);
	}

	/**
	 * 字符串空则报错, 带子消息
	 *
	 * @param obj        obj
	 * @param errorCode  错误代码
	 * @param subMessage 子消息
	 */
	public static void notBlank(String obj, ErrorEnum errorCode, String... subMessage) {
		throwEx(StrUtil.isBlank(obj), errorCode, subMessage);
	}

	/**
	 * 集合为空则报错, 带子消息
	 *
	 * @param collection 集合
	 * @param errorCode  错误代码
	 * @param subMessage 子消息
	 */
	public static void notEmpty(Iterable<?> collection, ErrorEnum errorCode, String... subMessage) {
		throwEx(CollUtil.isEmpty(collection), errorCode, subMessage);
	}

	/**
	 * map为空则报错, 带子消息
	 *
	 * @param map        地图
	 * @param errorCode  错误代码
	 * @param subMessage 子消息
	 */
	public static void notEmpty(Map<?, ?> map, ErrorEnum errorCode, String... subMessage) {
		throwEx(MapUtil.isEmpty(map), errorCode, subMessage);
	}
}

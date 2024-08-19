/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2023.12
 */

package com.luman.smy.infra.common.exception;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.luman.smy.infra.common.enums.ErrorEnum;

import java.util.Map;
import java.util.Objects;

/**
 * 断言
 *
 * @author yeyinghao
 * @date 2023/12/10
 */
@SuppressWarnings("unused")
public abstract class ValidationUtils {

	/**
	 * 抛出异常
	 */
	private static void throwEx(boolean condition, ErrorEnum errorCode, String subMessage) {
		if (condition) {
			throw ExceptionFactory.bizException(errorCode, subMessage);
		}
	}

	/**
	 * 条件为false, 则抛异常
	 *
	 * @param condition  条件
	 * @param errorCode  错误代码
	 * @param subMessage 子消息
	 */
	public static void isTrue(boolean condition, ErrorEnum errorCode, String subMessage) {
		throwEx(!condition, errorCode, subMessage);
	}

	/**
	 * 条件为false, 则抛异常
	 *
	 * @param condition 条件
	 * @param errorCode 错误代码
	 */
	public static void isTrue(boolean condition, ErrorEnum errorCode) {
		isTrue(condition, errorCode, "[Assertion failed] Must be true");
	}

	/**
	 * 条件为false, 则抛异常
	 *
	 * @param condition   条件
	 * @param errorCode   错误代码
	 * @param template    模板
	 * @param subMessages 子消息
	 */
	public static void isTrue(boolean condition, ErrorEnum errorCode, String template, Object... subMessages) {
		isTrue(condition, errorCode, StrUtil.format(template, subMessages));
	}

	/**
	 * 条件为true, 则抛异常
	 *
	 * @param condition  条件
	 * @param errorCode  错误代码
	 * @param subMessage 子消息
	 */
	public static void isFalse(boolean condition, ErrorEnum errorCode, String subMessage) {
		throwEx(condition, errorCode, subMessage);
	}

	/**
	 * 条件为true, 则抛异常
	 *
	 * @param condition 条件
	 * @param errorCode 错误代码
	 */
	public static void isFalse(boolean condition, ErrorEnum errorCode) {
		isFalse(condition, errorCode, "[Assertion failed] Must be false");
	}

	/**
	 * 条件为true, 则抛异常
	 *
	 * @param condition   条件
	 * @param errorCode   错误代码
	 * @param template    模板
	 * @param subMessages 子消息
	 */
	public static void isFalse(boolean condition, ErrorEnum errorCode, String template, Object... subMessages) {
		isFalse(condition, errorCode, StrUtil.format(template, subMessages));
	}

	/**
	 * 对象为null, 则抛异常
	 *
	 * @param obj        obj
	 * @param errorCode  错误代码
	 * @param subMessage 子消息
	 */
	public static void notNull(Object obj, ErrorEnum errorCode, String subMessage) {
		throwEx(Objects.isNull(obj), errorCode, subMessage);
	}

	/**
	 * 对象为null, 则抛异常
	 *
	 * @param obj       obj
	 * @param errorCode 错误代码
	 */
	public static void notNull(Object obj, ErrorEnum errorCode) {
		notNull(obj, errorCode, "[Assertion failed] Must not null");
	}

	/**
	 * 对象为null, 则抛异常
	 *
	 * @param obj         obj
	 * @param errorCode   错误代码
	 * @param template    模板
	 * @param subMessages 子消息
	 */
	public static void notNull(Object obj, ErrorEnum errorCode, String template, Object... subMessages) {
		notNull(obj, errorCode, StrUtil.format(template, subMessages));
	}


	/**
	 * 对象非null, 则抛异常
	 *
	 * @param obj        obj
	 * @param errorCode  错误代码
	 * @param subMessage 子消息
	 */
	public static void isNull(Object obj, ErrorEnum errorCode, String subMessage) {
		throwEx(Objects.nonNull(obj), errorCode, subMessage);
	}

	/**
	 * 对象非null, 则抛异常
	 *
	 * @param obj       obj
	 * @param errorCode 错误代码
	 */
	public static void isNull(Object obj, ErrorEnum errorCode) {
		isNull(obj, errorCode, "[Assertion failed] Must be null");
	}

	/**
	 * 对象非null, 则抛异常
	 *
	 * @param obj         obj
	 * @param errorCode   错误代码
	 * @param template    模板
	 * @param subMessages 子消息
	 */
	public static void isNull(Object obj, ErrorEnum errorCode, String template, Object... subMessages) {
		isNull(obj, errorCode, StrUtil.format(template, subMessages));
	}

	/**
	 * 字符串为空, 则抛异常
	 *
	 * @param obj        obj
	 * @param errorCode  错误代码
	 * @param subMessage 子消息
	 */
	public static void notBlank(String obj, ErrorEnum errorCode, String subMessage) {
		throwEx(StrUtil.isBlank(obj), errorCode, subMessage);
	}

	/**
	 * 字符串为空, 则抛异常
	 *
	 * @param obj       obj
	 * @param errorCode 错误代码
	 */
	public static void notBlank(String obj, ErrorEnum errorCode) {
		notBlank(obj, errorCode, "[Assertion failed] Must not blank");
	}

	/**
	 * 字符串为空, 则抛异常
	 *
	 * @param obj         obj
	 * @param errorCode   错误代码
	 * @param template    模板
	 * @param subMessages 子消息
	 */
	public static void notBlank(String obj, ErrorEnum errorCode, String template, Object... subMessages) {
		notBlank(obj, errorCode, StrUtil.format(template, subMessages));
	}

	/**
	 * 集合为空, 则抛异常
	 *
	 * @param collection 集合
	 * @param errorCode  错误代码
	 * @param subMessage 子消息
	 */
	public static void notEmpty(Iterable<?> collection, ErrorEnum errorCode, String subMessage) {
		throwEx(CollUtil.isEmpty(collection), errorCode, subMessage);
	}

	/**
	 * 集合为空, 则抛异常
	 *
	 * @param collection 集合
	 * @param errorCode  错误代码
	 */
	public static void notEmpty(Iterable<?> collection, ErrorEnum errorCode) {
		notEmpty(collection, errorCode, "[Assertion failed] Collection must not be empty: it must contain at least 1 element");
	}

	/**
	 * 集合为空, 则抛异常
	 *
	 * @param collection  集合
	 * @param errorCode   错误代码
	 * @param template    模板
	 * @param subMessages 子消息
	 */
	public static void notEmpty(Iterable<?> collection, ErrorEnum errorCode, String template, Object... subMessages) {
		notEmpty(collection, errorCode, StrUtil.format(template, subMessages));
	}

	/**
	 * map为空, 则抛异常
	 *
	 * @param map        map集合
	 * @param errorCode  错误代码
	 * @param subMessage 子消息
	 */
	public static void notEmpty(Map<?, ?> map, ErrorEnum errorCode, String subMessage) {
		throwEx(MapUtil.isEmpty(map), errorCode, subMessage);
	}

	/**
	 * map为空, 则抛异常
	 *
	 * @param map       map集合
	 * @param errorCode 错误代码
	 */
	public static void notEmpty(Map<?, ?> map, ErrorEnum errorCode) {
		notEmpty(map, errorCode, "[Assertion failed] Map must not be empty: it must contain at least one entry");
	}

	/**
	 * map为空, 则抛异常
	 *
	 * @param map         map集合
	 * @param errorCode   错误代码
	 * @param template    模板
	 * @param subMessages 子消息
	 */
	public static void notEmpty(Map<?, ?> map, ErrorEnum errorCode, String template, Object... subMessages) {
		notEmpty(map, errorCode, StrUtil.format(template, subMessages));
	}
}

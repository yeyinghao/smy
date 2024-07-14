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
public abstract class SmyAssert {

	/**
	 * 抛出异常
	 */
	private static void throwEx(boolean condition, ErrorEnum errorCode, String subMessage) {
		if (condition) {
			throw SmyBizExceptionFactory.bizException(errorCode, subMessage);
		}
	}

	public static void isTrue(boolean condition, ErrorEnum errorCode, String subMessage) {
		throwEx(!condition, errorCode, subMessage);
	}

	public static void isTrue(boolean condition, ErrorEnum errorCode) {
		isTrue(condition, errorCode, "[Assertion failed] Must be true");
	}

	public static void isTrue(boolean condition, ErrorEnum errorCode, String template, Object... subMessages) {
		isTrue(condition, errorCode, StrUtil.format(template, subMessages));
	}

	public static void isFalse(boolean condition, ErrorEnum errorCode, String subMessage) {
		throwEx(condition, errorCode, subMessage);
	}

	public static void isFalse(boolean condition, ErrorEnum errorCode) {
		isFalse(condition, errorCode, "[Assertion failed] Must be false");
	}

	public static void isFalse(boolean condition, ErrorEnum errorCode, String template, Object... subMessages) {
		isFalse(condition, errorCode, StrUtil.format(template, subMessages));
	}

	public static void notNull(Object obj, ErrorEnum errorCode, String subMessage) {
		throwEx(Objects.isNull(obj), errorCode, subMessage);
	}

	public static void notNull(Object obj, ErrorEnum errorCode) {
		notNull(obj, errorCode, "[Assertion failed] Must not null");
	}

	public static void notNull(Object obj, ErrorEnum errorCode, String template, Object... subMessages) {
		notNull(obj, errorCode, StrUtil.format(template, subMessages));
	}


	public static void isNull(Object obj, ErrorEnum errorCode, String subMessage) {
		throwEx(Objects.nonNull(obj), errorCode, subMessage);
	}

	public static void isNull(Object obj, ErrorEnum errorCode) {
		isNull(obj, errorCode, "[Assertion failed] Must be null");
	}

	public static void isNull(Object obj, ErrorEnum errorCode, String template, Object... subMessages) {
		isNull(obj, errorCode, StrUtil.format(template, subMessages));
	}

	public static void notBlank(String obj, ErrorEnum errorCode, String subMessage) {
		throwEx(StrUtil.isBlank(obj), errorCode, subMessage);
	}

	public static void notBlank(String obj, ErrorEnum errorCode) {
		notBlank(obj, errorCode, "[Assertion failed] Must not blank");
	}

	public static void notBlank(String obj, ErrorEnum errorCode, String template, Object... subMessages) {
		notBlank(obj, errorCode, StrUtil.format(template, subMessages));
	}

	public static void notEmpty(Iterable<?> collection, ErrorEnum errorCode, String subMessage) {
		throwEx(CollUtil.isEmpty(collection), errorCode, subMessage);
	}

	public static void notEmpty(Iterable<?> collection, ErrorEnum errorCode) {
		notEmpty(collection, errorCode, "[Assertion failed] Collection must not be empty: it must contain at least 1 element");
	}

	public static void notEmpty(Iterable<?> collection, ErrorEnum errorCode, String template, Object... subMessages) {
		notEmpty(collection, errorCode, StrUtil.format(template, subMessages));
	}

	public static void notEmpty(Map<?, ?> map, ErrorEnum errorCode, String subMessage) {
		throwEx(MapUtil.isEmpty(map), errorCode, subMessage);
	}

	public static void notEmpty(Map<?, ?> map, ErrorEnum errorCode) {
		notEmpty(map, errorCode, "[Assertion failed] Map must not be empty: it must contain at least one entry");
	}

	public static void notEmpty(Map<?, ?> map, ErrorEnum errorCode, String template, Object... subMessages) {
		notEmpty(map, errorCode, StrUtil.format(template, subMessages));
	}
}

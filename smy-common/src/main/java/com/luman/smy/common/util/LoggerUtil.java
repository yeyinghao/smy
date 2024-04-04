/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2023.7
 */

package com.luman.smy.common.util;

import com.luman.smy.common.constant.CommConstant;
import com.luman.smy.common.exception.BizException;
import lombok.experimental.UtilityClass;
import org.slf4j.Logger;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * 日志记录器工具类
 *
 * @author yeyinghao
 * @date 2023/08/01
 */
@UtilityClass
public class LoggerUtil {

	/**
	 * 离开了分隔符
	 */
	private static final String LEFT_DELIMITER = "[";

	/**
	 * 正确分隔符
	 */
	private static final String RIGHT_DELIMITER = "]";

	/**
	 * 空字符串
	 */
	private static final String EMPTY_STRING = "-";

	/**
	 * warn日志
	 *
	 * @param logger 日志记录器
	 * @param e      e
	 * @param objs   obj
	 */
	public static void warn(Logger logger, Exception e, Object... objs) {
		logger.warn(getContentString(objs), e);
	}

	/**
	 * warn日志
	 *
	 * @param logger 日志记录器
	 * @param objs   obj
	 */
	public static void warn(Logger logger, Object... objs) {
		logger.warn(getContentString(objs));
	}

	/**
	 * debug日志
	 *
	 * @param logger 日志记录器
	 * @param objs   obj
	 */
	public static void debug(Logger logger, Object... objs) {
		logger.debug(getContentString(objs));
	}

	/**
	 * info日志
	 *
	 * @param logger 日志记录器
	 * @param objs   obj
	 */
	public static void info(Logger logger, Object... objs) {
		logger.info(getContentString(objs));
	}

	/**
	 * info日志
	 *
	 * @param logger 日志记录器
	 * @param e      e
	 * @param objs   obj
	 */
	public static void info(Logger logger, Throwable e, Object... objs) {
		logger.info(getContentString(objs), e);
	}

	/**
	 * info日志
	 *
	 * @param logger 日志记录器
	 * @param e      e
	 */
	public static void info(Logger logger, BizException e) {
		logger.info(getContentString(e.getErrorEnum(), e.getSubMessage()));
	}

	/**
	 * error日志
	 *
	 * @param logger 日志记录器
	 * @param objs   obj
	 */
	public static void error(Logger logger, Object... objs) {
		logger.error(getContentString(objs));
	}

	/**
	 * error日志
	 *
	 * @param logger 日志记录器
	 * @param e      e
	 * @param objs   obj
	 */
	public static void error(Logger logger, Throwable e, Object... objs) {
		logger.error(getContentString(objs), e);
	}

	/**
	 * 获取内容字符串
	 *
	 * @param objs obj
	 * @return {@link String}
	 */
	private static String getContentString(Object... objs) {
		return LEFT_DELIMITER + Arrays.stream(objs).map(LoggerUtil::object2String).collect(Collectors.joining(CommConstant.DELIMITER)) + RIGHT_DELIMITER;
	}

	/**
	 * object2字符串
	 *
	 * @param obj obj
	 * @return {@link String}
	 */
	private static String object2String(Object obj) {
		if (Objects.nonNull(obj)) {
			return obj.toString();
		}
		return EMPTY_STRING;
	}
}

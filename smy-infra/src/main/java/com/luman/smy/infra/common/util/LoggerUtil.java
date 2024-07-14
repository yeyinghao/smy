/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2023.7
 */

package com.luman.smy.infra.common.util;

import cn.hutool.core.util.StrUtil;
import com.luman.smy.infra.common.exception.SmyBizException;
import lombok.experimental.UtilityClass;
import org.slf4j.Logger;


/**
 * 日志记录器工具类
 *
 * @author yeyinghao
 * @date 2023/08/01
 */
@UtilityClass
@SuppressWarnings("unused")
public class LoggerUtil {

	public static void warn(Logger logger, Throwable e, String message) {
		logger.warn(message, e);
	}

	public static void warn(Logger logger, Throwable e, String template, Object... messages) {
		logger.warn(StrUtil.format(template, messages), e);
	}

	public static void warn(Logger logger, String message) {
		logger.warn(message);
	}

	public static void warn(Logger logger, String template, Object... messages) {
		logger.warn(StrUtil.format(template, messages));
	}

	public static void debug(Logger logger, String message) {
		logger.debug(message);
	}

	public static void debug(Logger logger, String template, Object... messages) {
		logger.debug(StrUtil.format(template, messages));
	}

	public static void info(Logger logger, Throwable e, String message) {
		logger.info(message, e);
	}

	public static void info(Logger logger, Throwable e) {
		logger.info(e.getMessage(), e);
	}

	public static void info(Logger logger, Throwable e, String template, Object... messages) {
		logger.info(StrUtil.format(template, messages), e);
	}

	public static void info(Logger logger, String message) {
		logger.info(message);
	}

	public static void info(Logger logger, String template, Object... messages) {
		logger.info(StrUtil.format(template, messages));
	}

	public static void error(Logger logger, Throwable e, String message) {
		logger.error(message, e);
	}

	public static void error(Logger logger, Throwable e) {
		logger.error(e.getMessage(), e);
	}

	public static void error(Logger logger, Throwable e, String template, Object... messages) {
		logger.error(StrUtil.format(template, messages), e);
	}

	public static void error(Logger logger, String message) {
		logger.error(message);
	}

	public static void error(Logger logger, String template, Object... messages) {
		logger.error(StrUtil.format(template, messages));
	}

	public static void info(Logger logger, SmyBizException e) {
		logger.info(e.toString());
	}
}

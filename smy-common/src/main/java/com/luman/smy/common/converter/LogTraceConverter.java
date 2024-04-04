/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2024.1
 */

package com.luman.smy.common.converter;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.luman.smy.common.util.TraceIdUtil;

/**
 * 日志跟踪转换器
 *
 * @author yeyinghao
 * @date 2024/04/04
 */
public class LogTraceConverter extends ClassicConverter {

	@Override
	public String convert(ILoggingEvent iLoggingEvent) {
		return TraceIdUtil.getThreadTraceId();
	}
}
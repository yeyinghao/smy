package com.luman.smy.common.template.impl;

import com.luman.smy.common.constant.LoggerConstant;
import com.luman.smy.common.enums.BaseEnum;
import com.luman.smy.common.helper.ResultHelper;
import com.luman.smy.common.template.util.ExecuteTemplateUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Supplier;

/**
 * 执行模板实现
 *
 * @author yeyinghao
 * @date 2024/04/19
 */
@Slf4j(topic = LoggerConstant.WEB_MONITOR_LOGGER)
public class WebExecuteTemplate {

	public <R> ResultHelper<R> execute(BaseEnum baseEnum, Supplier<R> supplier) {
		return ResultHelper.of(ExecuteTemplateUtil.execute(log, baseEnum, supplier));
	}

	public ResultHelper<Boolean> execute(BaseEnum baseEnum, Runnable runnable) {
		ExecuteTemplateUtil.execute(log, baseEnum, runnable);
		return ResultHelper.success();
	}
}

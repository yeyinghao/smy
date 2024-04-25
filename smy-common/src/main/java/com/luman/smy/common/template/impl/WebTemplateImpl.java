package com.luman.smy.common.template.impl;

import com.luman.smy.common.constant.LoggerConstant;
import com.luman.smy.common.enums.BaseEnum;
import com.luman.smy.common.helper.ResultHelper;
import com.luman.smy.common.template.ExecuteTemplate;
import com.luman.smy.common.template.WebTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

/**
 * 网络模板实现
 *
 * @author yeyinghao
 * @date 2024/04/11
 */
@Component
@RequiredArgsConstructor
@Slf4j(topic = LoggerConstant.WEB_MONITOR_LOGGER)
public class WebTemplateImpl implements WebTemplate {

	/**
	 * 执行模板
	 */
	private final ExecuteTemplate executeTemplate;

	@Override
	public <R> ResultHelper<R> execute(BaseEnum baseEnum, Supplier<R> supplier, Object... objs) {
		R res = executeTemplate.execute(log, baseEnum, supplier, objs);
		return ResultHelper.of(res);
	}

	@Override
	public void execute(BaseEnum baseEnum, Runnable runnable, Object... objs) {
		executeTemplate.execute(log, baseEnum, runnable, objs);
	}
}

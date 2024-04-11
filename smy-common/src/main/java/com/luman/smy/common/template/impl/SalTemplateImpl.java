package com.luman.smy.common.template.impl;

import com.luman.smy.common.constant.LoggerConstant;
import com.luman.smy.common.enums.BaseEnum;
import com.luman.smy.common.template.ExecuteTemplate;
import com.luman.smy.common.template.SalTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

/**
 * sal模板实现
 *
 * @author yeyinghao
 * @date 2024/04/11
 */
@Component
@RequiredArgsConstructor
@Slf4j(topic = LoggerConstant.SAL_MONITOR_LOGGER)
public class SalTemplateImpl implements SalTemplate {

	/**
	 * 执行模板
	 */
	private final ExecuteTemplate executeTemplate;

	@Override
	public <R> R execute(BaseEnum baseEnum, Supplier<R> supplier, Object... objs) {
		return executeTemplate.execute(log, baseEnum, supplier, objs);
	}

	@Override
	public void execute(BaseEnum baseEnum, Runnable runnable, Object... objs) {
		executeTemplate.execute(log, baseEnum, runnable, objs);
	}
}

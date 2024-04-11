package com.luman.smy.cache.impl;

import com.luman.smy.cache.CacheTmeplate;
import com.luman.smy.common.constant.LoggerConstant;
import com.luman.smy.common.enums.BaseEnum;
import com.luman.smy.common.template.ExecuteTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

/**
 * 缓存tmeplate实现
 *
 * @author yeyinghao
 * @date 2024/04/11
 */
@Component
@Slf4j(topic = LoggerConstant.CAL_MONITOR_LOGGER)
@RequiredArgsConstructor
public class CacheTmeplateImpl implements CacheTmeplate {

	/**
	 * 执行模板
	 */
	private final ExecuteTemplate executeTemplate;

	@Override
	public <R> R execute(BaseEnum baseEnum, Supplier<R> supplier, String key, Boolean isget) {
		return executeTemplate.execute(log, baseEnum, supplier, key, isget);
	}

	@Override
	public void execute(BaseEnum baseEnum, Runnable runnable, String key, Boolean isget) {
		executeTemplate.execute(log, baseEnum, runnable, key, isget);
	}
}

package com.luman.smy.common.template.impl;

import com.luman.smy.common.constant.LoggerConstant;
import com.luman.smy.common.enums.BaseEnum;
import com.luman.smy.common.template.ExecuteTemplate;
import com.luman.smy.common.template.util.TemplateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

/**
 * 执行模板实现
 *
 * @author yeyinghao
 * @date 2024/04/19
 */
@Component("cacheExecuteTemplate")
@Slf4j(topic = LoggerConstant.CAL_MONITOR_LOGGER)
public class CacheExecuteTemplateImpl implements ExecuteTemplate {

	@Override
	public <R> R execute(BaseEnum baseEnum, Supplier<R> supplier, Object... objs) {
		return TemplateUtil.execute(log, baseEnum, supplier, objs);
	}

	@Override
	public void execute(BaseEnum baseEnum, Runnable runnable, Object... objs) {
		TemplateUtil.execute(log, baseEnum, runnable, objs);
	}
}

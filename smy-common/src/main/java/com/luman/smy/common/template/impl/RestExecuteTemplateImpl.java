package com.luman.smy.common.template.impl;

import com.luman.smy.common.constant.LoggerConstant;
import com.luman.smy.common.template.ExecuteTemplate;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

/**
 * 执行模板实现
 *
 * @author yeyinghao
 * @date 2024/04/19
 */
@Component("restExecuteTemplate")
@Slf4j(topic = LoggerConstant.REST_MONITOR_LOGGER)
public class RestExecuteTemplateImpl implements ExecuteTemplate {

	@Override
	public Logger getLogger() {
		return log;
	}
}

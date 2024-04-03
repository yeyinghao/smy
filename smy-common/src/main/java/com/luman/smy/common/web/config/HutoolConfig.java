/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2024.3
 */

package com.luman.smy.common.web.config;

import cn.hutool.core.convert.Converter;
import cn.hutool.core.convert.ConverterRegistry;
import com.luman.smy.common.enums.BaseEnum;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * hutool配置
 *
 * @author yeyinghao
 * @date 2024/03/18
 */
@Component
public class HutoolConfig {

	/**
	 * 初始化
	 */
	@PostConstruct
	public void init() {
		//配置
		ConverterRegistry converterRegistry = ConverterRegistry.getInstance();
		converterRegistry.putCustom(String.class, (Converter<String>) (value, defaultValue) -> {
			if (null == value) {
				return null;
			}
			if (value instanceof BaseEnum) {
				return ((BaseEnum) value).name();
			}
			if (value instanceof String) {
				return (String) value;
			}
			return String.valueOf(value);
		});
	}

}

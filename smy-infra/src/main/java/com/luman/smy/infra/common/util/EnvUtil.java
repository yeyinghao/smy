/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2024.3
 */

package com.luman.smy.infra.common.util;

import cn.hutool.core.util.StrUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * env工具类
 *
 * @author yeyinghao
 * @date 2024/03/17
 */
@Component
@Data
public class EnvUtil {

	/**
	 * env
	 */
	@Value("${spring.profiles.active}")
	private String env;

	/**
	 * 是否开发环境
	 *
	 * @return boolean
	 */
	public boolean isLocal() {
		return StrUtil.equals("local", env);
	}

}

/*
 * QQ: 1113531030
 * WX: missyeyh
 * Phone: 17689397484
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2023.2
 */
package com.luman.code.drm.config;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * jwtdrm配置
 *
 * @author yeyinghao
 * @date 2023/09/17
 */
@Component
@Data
public class DrmConfig {

	/**
	 * jasypt密码
	 */
	@NacosValue(value = "${jasypt.encryptor.password}", autoRefreshed = true)
	private String jasyptPassword;
}

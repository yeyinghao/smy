/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2023.10
 */

package com.luman.code.drm.service;

/**
 * JWT DRM服务
 *
 * @author yeyinghao
 * @date 2023/09/17
 */
public interface DrmService {

	/**
	 * 获取Jasypt密钥
	 *
	 * @return String
	 */
	String getJasyptPassword();
}

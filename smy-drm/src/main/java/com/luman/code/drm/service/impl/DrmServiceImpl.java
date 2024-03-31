/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2023.10
 */

package com.luman.code.drm.service.impl;

import com.luman.code.drm.config.DrmConfig;
import com.luman.code.drm.service.DrmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * jwtdrm服务实现
 *
 * @author yeyinghao
 * @date 2023/09/17
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class DrmServiceImpl implements DrmService {

	/**
	 * JWT DRM配置
	 */
	private final DrmConfig drmConfig;

	@Override
	public String getJasyptPassword() {
		return drmConfig.getJasyptPassword();
	}
}

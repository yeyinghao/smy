package com.luman.smy.common.feature.secret.service.impl;

import cn.hutool.crypto.asymmetric.Sign;
import cn.hutool.crypto.symmetric.AES;
import com.luman.smy.common.feature.secret.service.SecretService;

/**
 * 秘密服务实现
 *
 * @author yeyinghao
 * @date 2024/04/04
 */
public class SecretServiceImpl implements SecretService {
	@Override
	public AES getContentAes() {
		return null;
	}

	@Override
	public Sign getGatewayRsa() {
		return null;
	}

	@Override
	public Sign getPlatformRsa() {
		return null;
	}
}

package com.luman.smy.common.feature.secret.service;

import cn.hutool.crypto.asymmetric.Sign;
import cn.hutool.crypto.symmetric.AES;

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

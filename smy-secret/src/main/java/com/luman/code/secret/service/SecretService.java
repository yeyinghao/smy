package com.luman.code.secret.service;

import cn.hutool.crypto.asymmetric.Sign;
import cn.hutool.crypto.symmetric.AES;

public interface SecretService {

	AES getContentAes();

	Sign getGatewayRsa();

	Sign getPlatformRsa();

}

package com.luman.smy.common.feature.secret.service;

import cn.hutool.crypto.asymmetric.Sign;
import cn.hutool.crypto.symmetric.AES;

/**
 * 特勤处
 *
 * @author yeyinghao
 * @date 2024/04/04
 */
public interface SecretService {

	AES getContentAes();

	Sign getGatewayRsa();

	Sign getPlatformRsa();

}

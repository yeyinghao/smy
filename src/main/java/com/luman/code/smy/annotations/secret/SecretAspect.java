/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2024.3
 */

package com.luman.code.smy.annotations.secret;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.json.JSONUtil;
import com.luman.code.smy.enums.CommErrorEnum;
import com.luman.code.smy.enums.ErrorEnum;
import com.luman.code.smy.exception.Assert;
import com.luman.code.smy.exception.BizException;
import com.luman.code.smy.helper.ResultHelper;
import com.luman.code.smy.model.SecretDTO;
import com.luman.code.smy.feature.secret.service.SecretService;
import com.luman.code.smy.util.CommUtil;
import com.luman.code.smy.util.ErrorUtil;
import com.luman.code.smy.util.LoggerUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * 秘密切面
 *
 * @author yeyinghao
 * @date 2024/01/03
 */
@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class SecretAspect {

	/**
	 * 签名
	 */
	private static final String SIGN = "sign";

	/**
	 * 业务数据
	 */
	private static final String PLAIN_TEXT = "plainText";

	/**
	 * 加密service
	 */
	private final SecretService secretService;

	/**
	 * 周围
	 *
	 * @param joinPoint 连接点
	 * @param secret    秘密注释
	 * @return {@link Object}
	 */
	@SuppressWarnings("unchecked")
	@Around("@annotation(secret)")
	@SneakyThrows
	public Object around(ProceedingJoinPoint joinPoint, Secret secret) {
		long startTime = System.currentTimeMillis();
		boolean res = true;
		ErrorEnum errorEnum = null;
		String className = null;
		String methodName = null;
		SecretDTO req = null;
		String reqPlainText = null;
		SecretDTO resp = null;
		String respPlainText = null;
		try {
			className = joinPoint.getSignature().getDeclaringType().getSimpleName();
			methodName = joinPoint.getSignature().getName();
			req = (SecretDTO) joinPoint.getArgs()[0];
			decryptBizContentByAes(req);
			verifySignByRsa(req);
			reqPlainText = req.getPlainText();
			ResultHelper<SecretDTO> resultHelper = (ResultHelper<SecretDTO>) joinPoint.proceed();
			signDataByRsa(resultHelper);
			encryptBizContentByAes(resultHelper);
			resp = resultHelper.getData();
			respPlainText = resultHelper.getData().getPlainText();
			return resultHelper;
		} catch (BizException e) {
			errorEnum = e.getErrorEnum();
			res = !ErrorUtil.isError(errorEnum);
			throw e;
		} finally {
			LoggerUtil.info(log, className, methodName, req, reqPlainText, resp, respPlainText, CommUtil.getStringByBoolean(res), errorEnum, CommUtil.getCostTime(startTime));
		}
	}

	/**
	 * 解密业务内容通过aes
	 *
	 * @param secretDTO 秘密dto
	 */
	public void decryptBizContentByAes(SecretDTO secretDTO) {
		secretDTO.setPlainText(secretService.getContentAes().decryptStr(secretDTO.getEncryptStr()));
	}

	/**
	 * 用rsa验证符号
	 *
	 * @param secretDTO 秘密dto
	 */
	public void verifySignByRsa(SecretDTO secretDTO) {
		// 明文
		String plainText = getPlainText(secretDTO);
		String signStr = secretService.getGatewayRsa().signHex(plainText);
		String signMd5 = DigestUtil.md5Hex(signStr);
		Assert.isTrue(StrUtil.equals(signMd5, secretDTO.getSign()), CommErrorEnum.BIZ_PROCESS_FAIL, "签名验证失败");
	}

	/**
	 * 使用rsa对数据进行签名
	 *
	 * @param helper 助手
	 */
	public void signDataByRsa(ResultHelper<SecretDTO> helper) {
		SecretDTO data = helper.getData();
		// 明文
		String plainText = getPlainText(data);
		String signData = secretService.getPlatformRsa().signHex(plainText.getBytes(StandardCharsets.UTF_8));
		String signMd5 = DigestUtil.md5Hex(signData);
		data.setSign(signMd5);
	}

	/**
	 * 加密业务内容通过aes
	 *
	 * @param helper 助手
	 */
	public void encryptBizContentByAes(ResultHelper<SecretDTO> helper) {
		SecretDTO data = helper.getData();
		data.setEncryptStr(secretService.getContentAes().encryptHex(data.getPlainText()));
	}

	/**
	 * 获取原文文本
	 *
	 * @param secretDTO 秘密dto
	 * @return {@link String}
	 */
	private String getPlainText(SecretDTO secretDTO) {
		// 构造map
		Map<String, Object> reqMap = BeanUtil.beanToMap(secretDTO);
		reqMap.remove(SIGN);
		reqMap.remove(PLAIN_TEXT);
		reqMap = MapUtil.sort(reqMap);
		// 明文
		return JSONUtil.toJsonStr(reqMap);
	}
}

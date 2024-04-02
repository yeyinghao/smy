/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2024.1
 */

package com.luman.code.smy.model;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.asymmetric.SignAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.luman.code.smy.constant.CommConstant;
import com.luman.code.smy.model.base.DTO;
import com.luman.code.smy.model.base.REQ;
import com.luman.code.smy.model.base.RES;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 基础司法请求
 *
 * @author yeyinghao
 * @date 2024/01/02
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SecretDTO extends DTO {

	/**
	 * 签名类型
	 */
	private String signType;

	/**
	 * 加密类型 bizContent 加密类型
	 */
	private String encryptType;

	/**
	 * 字符集
	 */
	private String charset;

	/**
	 * 时间戳
	 */
	private Long timestamp;

	/**
	 * 随机字符串
	 */
	private String nonce;

	/**
	 * 业务内容
	 */
	private String encryptStr;

	/**
	 * 业务内容 明文
	 */
	@JsonIgnore
	private String plainText;

	/**
	 * 签名
	 */
	private String sign;

	/**
	 * 秘密dto
	 *
	 * @param plainText 纯文本
	 */
	public SecretDTO(String plainText) {
		this.signType = SignAlgorithm.SHA256withRSA.name();
		this.encryptType = SymmetricAlgorithm.AES.name();
		this.charset = CommConstant.CHARSET;
		this.timestamp = DateUtil.current();
		this.nonce = IdUtil.fastSimpleUUID();
		this.plainText = plainText;
	}

	/**
	 * 解析请求
	 *
	 * @param clazz clazz
	 * @return {@link T}
	 */
	public <T extends REQ> T parseReq(Class<T> clazz) {
		return JSONUtil.toBean(plainText, clazz);
	}

	/**
	 * 构建
	 *
	 * @param t t
	 * @return {@link SecretDTO}
	 */
	public <T extends RES> SecretDTO build(T t) {
		return new SecretDTO(JSONUtil.toJsonStr(t));
	}
}

package com.luman.smy.common.template.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.asymmetric.Sign;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.json.JSONUtil;
import com.luman.smy.common.enums.BaseEnum;
import com.luman.smy.common.enums.CommErrorEnum;
import com.luman.smy.common.exception.Assert;
import com.luman.smy.common.helper.ResultHelper;
import com.luman.smy.common.model.SecretDTO;
import com.luman.smy.common.template.ExecuteTemplate;
import com.luman.smy.common.template.SecretTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
@Slf4j
public class SecretTemplateImpl implements SecretTemplate {

	/**
	 * 签名
	 */
	private static final String SIGN = "sign";

	/**
	 * 业务数据
	 */
	private static final String PLAIN_TEXT = "plainText";

	/**
	 * 执行模板
	 */
	private final ExecuteTemplate executeTemplate;

	@Override
	public ResultHelper<SecretDTO> execute(BaseEnum baseEnum, Supplier<ResultHelper<SecretDTO>> supplier,
	                                       SecretDTO secretDTO, AES aes, Sign userRsa, Sign platformRsa) {
		return executeTemplate.execute(log, baseEnum, () -> {
			secretDTO.setPlainText(aes.decryptStr(secretDTO.getEncryptStr()));
			Assert.isTrue(StrUtil.equals(DigestUtil.md5Hex(platformRsa.signHex(getPlainText(secretDTO))),
					secretDTO.getSign()), CommErrorEnum.BIZ_ERROR, "签名验证失败");
			ResultHelper<SecretDTO> res = supplier.get();
			SecretDTO data = res.getData();
			data.setSign(DigestUtil.md5Hex(userRsa.signHex(getPlainText(data).getBytes(StandardCharsets.UTF_8))));
			data.setEncryptStr(aes.encryptHex(data.getEncryptStr()));
			return res;
		}, secretDTO);
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

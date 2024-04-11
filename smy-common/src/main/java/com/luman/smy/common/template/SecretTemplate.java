package com.luman.smy.common.template;

import cn.hutool.crypto.asymmetric.Sign;
import cn.hutool.crypto.symmetric.AES;
import com.luman.smy.common.enums.BaseEnum;
import com.luman.smy.common.helper.ResultHelper;
import com.luman.smy.common.model.SecretDTO;

import java.util.function.Supplier;

public interface SecretTemplate {

	ResultHelper<SecretDTO> execute(BaseEnum baseEnum, Supplier<ResultHelper<SecretDTO>> supplier,
	                                SecretDTO secretDTO, AES aes, Sign userRsa, Sign platformRsa);
}

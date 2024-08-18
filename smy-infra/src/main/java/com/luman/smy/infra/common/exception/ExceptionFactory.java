package com.luman.smy.infra.common.exception;

import cn.hutool.core.util.StrUtil;
import com.luman.smy.infra.common.enums.ErrorEnum;

public class ExceptionFactory {

	public static BizException bizException(ErrorEnum errorEnum) {
		return new BizException(errorEnum);
	}

	public static BizException bizException(ErrorEnum errorEnum, String errorMessage) {
		return new BizException(errorEnum, errorMessage);
	}

	public static BizException bizException(ErrorEnum errorEnum, String template, Object... subMessage) {
		return new BizException(errorEnum, StrUtil.format(template, subMessage));
	}

	public static BizException bizException(ErrorEnum errorEnum, Throwable ex) {
		return new BizException(errorEnum, ex);
	}

	public static BizException bizException(ErrorEnum errorEnum, String errorMessage, Throwable ex) {
		return new BizException(errorEnum, errorMessage, ex);
	}

}

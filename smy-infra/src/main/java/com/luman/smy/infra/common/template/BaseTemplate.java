package com.luman.smy.infra.common.template;

import cn.hutool.extra.validation.BeanValidationResult;
import cn.hutool.extra.validation.ValidationUtil;
import com.luman.smy.infra.common.constant.CommConstant;
import com.luman.smy.infra.common.enums.CommErrorEnum;
import com.luman.smy.infra.common.exception.BizException;
import com.luman.smy.infra.common.exception.CheckUtils;
import com.luman.smy.infra.common.util.LoggerUtil;
import org.slf4j.Logger;

import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public abstract class BaseTemplate {

	public abstract Logger getLogger();

	private final Logger log = getLogger();

	public <T, R> R excute(Supplier<R> supplier, T request) {
		R resp = null;
		try {
			resp = supplier.get();
		} catch (BizException e) {
			LoggerUtil.info(log, e);
			throw e;
		} catch (Throwable e) {
			LoggerUtil.error(log, e);
			throw e;
		} finally {
			LoggerUtil.info(log, "request={}, response={}", request, resp);
		}
		return resp;
	}

	public <T> void excute(Runnable runnable, T request) {
		try {
			runnable.run();
		} catch (BizException e) {
			LoggerUtil.info(log, e);
			throw e;
		} catch (Throwable e) {
			LoggerUtil.error(log, e);
			throw e;
		} finally {
			LoggerUtil.info(log, "request={}, response={}", request);
		}
	}

	private static <R> void preCheck(R request) {
		if (Objects.isNull(request)) {
			return;
		}
		// 获取校验结果
		BeanValidationResult result = ValidationUtil.warpValidate(request);
		// 校验失败 抛错误
		CheckUtils.isTrue(result.isSuccess(), CommErrorEnum.ILLEGAL_PARAMETER, result.getErrorMessages().stream().map(item -> item.getPropertyName() + CommConstant.COLON + item.getMessage()).collect(Collectors.joining(";")));
	}
}

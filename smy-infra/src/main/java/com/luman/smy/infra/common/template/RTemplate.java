package com.luman.smy.infra.common.template;

import cn.hutool.extra.validation.BeanValidationResult;
import cn.hutool.extra.validation.ValidationUtil;
import com.luman.smy.client.dto.Response;
import com.luman.smy.infra.common.constant.CommConstant;
import com.luman.smy.infra.common.enums.CommErrorEnum;
import com.luman.smy.infra.common.exception.BizException;
import com.luman.smy.infra.common.exception.CheckUtils;
import com.luman.smy.infra.common.helper.RHelper;
import com.luman.smy.infra.common.util.LoggerUtil;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@UtilityClass
@Slf4j
public class RTemplate {

	public static <T, R> Response<R> excute(Supplier<R> supplier, T request) {
		Response<R> response;
		R resp = null;
		try {
			preCheck(request);
			resp = supplier.get();
			response = RHelper.of(resp);
		} catch (BizException e) {
			LoggerUtil.info(log, e);
			response = RHelper.buildFailure(e.getErrorEnum(), e.getMessage());
		} catch (Throwable e) {
			LoggerUtil.error(log, e);
			response = RHelper.buildFailure(CommErrorEnum.SYSTEM_ERROR, "请联系管理员");
		} finally {
			LoggerUtil.info(log, "request={}, response={}", request, resp);
		}
		return response;
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

package com.luman.smy.infra.common.template;

import cn.hutool.extra.validation.BeanValidationResult;
import cn.hutool.extra.validation.ValidationUtil;
import com.luman.smy.client.dto.ListModel;
import com.luman.smy.client.dto.PageModel;
import com.luman.smy.client.dto.Response;
import com.luman.smy.infra.common.constant.CommConstant;
import com.luman.smy.infra.common.enums.CommErrorEnum;
import com.luman.smy.infra.common.exception.CheckUtils;
import com.luman.smy.infra.common.helper.RHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Component
public class RTemplate extends BaseTemplate {

	@Override
	public Logger getLogger() {
		return LoggerFactory.getLogger(RTemplate.class);
	}

	public <T, R> Response<R> single(Supplier<R> supplier, T request) {
		preCheck(request);
		return RHelper.success(excute(supplier, request));
	}

	public <T, R> Response<ListModel<R>> list(Supplier<ListModel<R>> supplier, T request) {
		preCheck(request);
		return RHelper.success(excute(supplier, request));
	}

	public <T, R> Response<PageModel<R>> page(Supplier<PageModel<R>> supplier, T request) {
		preCheck(request);
		return RHelper.success(excute(supplier, request));
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

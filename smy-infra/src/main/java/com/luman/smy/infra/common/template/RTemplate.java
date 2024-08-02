package com.luman.smy.infra.common.template;

import com.luman.smy.client.dto.ListModel;
import com.luman.smy.client.dto.PageModel;
import com.luman.smy.client.dto.Response;
import com.luman.smy.infra.common.helper.RHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class RTemplate extends BaseTemplate {

	@Override
	public Logger getLogger() {
		return LoggerFactory.getLogger(RTemplate.class);
	}

	public <T, R> Response<R> single(Supplier<R> supplier, T request) {
		return RHelper.success(excute(supplier, request));
	}

	public <T, R> Response<ListModel<R>> list(Supplier<ListModel<R>> supplier, T request) {
		return RHelper.success(excute(supplier, request));
	}

	public <T, R> Response<PageModel<R>> page(Supplier<PageModel<R>> supplier, T request) {
		return RHelper.success(excute(supplier, request));
	}
}

package com.luman.smy.infra.common.template;

import cn.hutool.json.JSONUtil;
import com.luman.smy.infra.common.exception.BizException;
import com.luman.smy.infra.common.util.LoggerUtil;
import com.luman.smy.infra.common.util.TimeUtils;
import org.slf4j.Logger;

import java.util.function.Supplier;

public abstract class BaseTemplate {

	private final static String LOG_TEMPLATE = "result={}, cost={}ms, request={}, response={}";

	public abstract Logger getLogger();

	private final Logger log = getLogger();

	public <T, R> R excute(Supplier<R> supplier, T request) {
		R resp = null;
		long startTime = System.currentTimeMillis();
		boolean res = false;
		try {
			resp = supplier.get();
		} catch (BizException e) {
			LoggerUtil.info(log, e);
			throw e;
		} catch (Throwable e) {
			LoggerUtil.error(log, e);
			throw e;
		} finally {
			LoggerUtil.info(log, LOG_TEMPLATE, res, TimeUtils.getCostTime(startTime), JSONUtil.toJsonStr(request), JSONUtil.toJsonStr(resp));
		}
		return resp;
	}

	public <T> void excute(Runnable runnable, T request) {
		long startTime = System.currentTimeMillis();
		boolean res = false;
		try {
			runnable.run();
		} catch (BizException e) {
			LoggerUtil.info(log, e);
			throw e;
		} catch (Throwable e) {
			LoggerUtil.error(log, e);
			throw e;
		} finally {
			LoggerUtil.info(log, LOG_TEMPLATE, res, TimeUtils.getCostTime(startTime), JSONUtil.toJsonStr(request), null);
		}
	}
}

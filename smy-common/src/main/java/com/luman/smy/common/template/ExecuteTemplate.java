package com.luman.smy.common.template;

import cn.hutool.json.JSONUtil;
import com.luman.smy.common.constant.CommConstant;
import com.luman.smy.common.enums.BaseEnum;
import com.luman.smy.common.enums.ErrorEnum;
import com.luman.smy.common.exception.BizException;
import com.luman.smy.common.util.LoggerUtil;
import com.luman.smy.common.util.TimeUtil;
import org.slf4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * 执行模板
 *
 * @author yeyinghao
 * @date 2024/04/11
 */
public interface ExecuteTemplate {

	/**
	 * 获取日志记录器
	 *
	 * @return {@link Logger }
	 */
	Logger getLogger();

	/**
	 * 执行
	 *
	 * @param baseEnum 基础枚举
	 * @param supplier 供应商
	 * @param objs     obj
	 * @return {@link R}
	 */
	default <R> R execute(BaseEnum baseEnum, Supplier<R> supplier, Object... objs) {
		long startTime = System.currentTimeMillis();
		String result = CommConstant.N;
		ErrorEnum errorEnum = null;
		List<Object> subMsg = null;
		R res = null;
		try {
			res = supplier.get();
			result = CommConstant.Y;
			return res;
		} catch (BizException e) {
			subMsg = Arrays.asList(e.getSubMessage());
			errorEnum = e.getErrorEnum();
			result = e.getResult();
			throw e;
		} finally {
			LoggerUtil.info(getLogger(), baseEnum, JSONUtil.toJsonStr(res), result, errorEnum, subMsg, JSONUtil.toJsonStr(objs), TimeUtil.getCostTime(startTime));
		}
	}

	/**
	 * 执行
	 *
	 * @param baseEnum 基础枚举
	 * @param runnable 可运行
	 * @param objs     obj
	 */
	default void execute(BaseEnum baseEnum, Runnable runnable, Object... objs) {
		long startTime = System.currentTimeMillis();
		String result = CommConstant.N;
		ErrorEnum errorEnum = null;
		List<Object> subMsg = null;
		try {
			runnable.run();
			result = CommConstant.Y;
		} catch (BizException e) {
			subMsg = Arrays.asList(e.getSubMessage());
			errorEnum = e.getErrorEnum();
			result = e.getResult();
			throw e;
		} finally {
			LoggerUtil.info(getLogger(), baseEnum, result, errorEnum, subMsg, JSONUtil.toJsonStr(objs), TimeUtil.getCostTime(startTime));
		}
	}
}

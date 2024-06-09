package com.luman.smy.common.template;

import cn.hutool.json.JSONUtil;
import com.luman.smy.common.constant.CommConstant;
import com.luman.smy.common.enums.BaseEnum;
import com.luman.smy.common.exception.BizException;
import com.luman.smy.common.util.LoggerUtil;
import com.luman.smy.common.util.TimeUtil;
import org.slf4j.Logger;

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
	 * @param args     obj
	 * @return {@link R}
	 */
	default <R> R execute(BaseEnum baseEnum, Supplier<R> supplier, Object... args) {
		long startTime = System.currentTimeMillis();
		String result = CommConstant.N;
		R res = null;
		try {
			res = supplier.get();
			result = CommConstant.Y;
			return res;
		} catch (BizException e) {
			result = e.getResult();
			throw e;
		} finally {
			LoggerUtil.info(getLogger(), baseEnum, result, "args", JSONUtil.toJsonStr(args), "res", JSONUtil.toJsonStr(res), TimeUtil.getCostTime(startTime));
		}
	}

	/**
	 * 执行
	 *
	 * @param baseEnum 基础枚举
	 * @param runnable 可运行
	 * @param args     obj
	 */
	default void execute(BaseEnum baseEnum, Runnable runnable, Object... args) {
		long startTime = System.currentTimeMillis();
		String result = CommConstant.N;
		try {
			runnable.run();
			result = CommConstant.Y;
		} catch (BizException e) {
			result = e.getResult();
			throw e;
		} finally {
			LoggerUtil.info(getLogger(), baseEnum, result, "args", JSONUtil.toJsonStr(args), TimeUtil.getCostTime(startTime));
		}
	}
}

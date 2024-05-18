package com.luman.smy.common.template.util;

import cn.hutool.json.JSONUtil;
import com.luman.smy.common.constant.CommConstant;
import com.luman.smy.common.enums.BaseEnum;
import com.luman.smy.common.enums.ErrorEnum;
import com.luman.smy.common.exception.BizException;
import com.luman.smy.common.util.LoggerUtil;
import com.luman.smy.common.util.TimeUtil;
import org.slf4j.Logger;

import java.util.function.Supplier;

/**
 * 模板工具类
 *
 * @author yeyinghao
 * @date 2024/05/19
 */
public class TemplateUtil {

	/**
	 * 执行
	 *
	 * @param logger   日志记录器
	 * @param baseEnum 基础枚举
	 * @param supplier 供应商
	 * @param objs     obj
	 * @return {@link R }
	 */
	public static <R> R execute(Logger logger, BaseEnum baseEnum, Supplier<R> supplier, Object... objs) {
		long startTime = System.currentTimeMillis();
		String result = CommConstant.N;
		ErrorEnum errorEnum = null;
		Object[] subMsg = null;
		R res = null;
		try {
			res = supplier.get();
			result = CommConstant.Y;
			return res;
		} catch (BizException e) {
			subMsg = e.getSubMessage();
			errorEnum = e.getErrorEnum();
			result = e.getResult();
			throw e;
		} finally {
			LoggerUtil.info(logger, baseEnum, JSONUtil.toJsonStr(res), result, errorEnum, subMsg, JSONUtil.toJsonStr(objs), TimeUtil.getCostTime(startTime));
		}
	}

	/**
	 * 执行
	 *
	 * @param logger   日志记录器
	 * @param baseEnum 基础枚举
	 * @param runnable 可运行
	 * @param objs     obj
	 */
	public static void execute(Logger logger, BaseEnum baseEnum, Runnable runnable, Object... objs) {
		long startTime = System.currentTimeMillis();
		String result = CommConstant.N;
		ErrorEnum errorEnum = null;
		Object[] subMsg = null;
		try {
			runnable.run();
			result = CommConstant.Y;
		} catch (BizException e) {
			subMsg = e.getSubMessage();
			errorEnum = e.getErrorEnum();
			result = e.getResult();
			throw e;
		} finally {
			LoggerUtil.info(logger, baseEnum, result, errorEnum, subMsg, JSONUtil.toJsonStr(objs), TimeUtil.getCostTime(startTime));
		}
	}

}

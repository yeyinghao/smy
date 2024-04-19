package com.luman.smy.common.template;

import com.luman.smy.common.enums.BaseEnum;
import com.luman.smy.common.helper.ResultHelper;

import java.util.function.Supplier;

/**
 * 网页模板
 *
 * @author yeyinghao
 * @date 2024/04/11
 */
public interface WebTemplate {

	/**
	 * 执行
	 *
	 * @param baseEnum 基础枚举
	 * @param supplier 供应商
	 * @param objs     obj
	 * @return {@link R}
	 */
	<R> ResultHelper<R> execute(BaseEnum baseEnum, Supplier<R> supplier, Object... objs);

	/**
	 * 执行
	 *
	 * @param baseEnum 基础枚举
	 * @param runnable 可运行
	 * @param objs     obj
	 * @return {@link ResultHelper}<{@link Boolean}>
	 */
	ResultHelper<Boolean> executeSuccess(BaseEnum baseEnum, Runnable runnable, Object... objs);
}

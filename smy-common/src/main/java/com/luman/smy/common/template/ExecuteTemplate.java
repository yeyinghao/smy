package com.luman.smy.common.template;

import com.luman.smy.common.enums.BaseEnum;
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
	 * 执行
	 *
	 * @param logger   日志记录器
	 * @param baseEnum 基础枚举
	 * @param supplier 供应商
	 * @param objs     obj
	 * @return {@link R}
	 */
	<R> R execute(Logger logger, BaseEnum baseEnum, Supplier<R> supplier, Object... objs);

	/**
	 * 执行
	 *
	 * @param logger   日志记录器
	 * @param baseEnum 基础枚举
	 * @param runnable 可运行
	 * @param objs     obj
	 */
	void execute(Logger logger, BaseEnum baseEnum, Runnable runnable, Object... objs);
}

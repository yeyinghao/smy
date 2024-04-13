package com.luman.smy.cache;

import com.luman.smy.common.enums.BaseEnum;

import java.util.function.Supplier;

/**
 * 缓存tmeplate
 *
 * @author yeyinghao
 * @date 2024/04/11
 */
public interface CacheTemplate {

	/**
	 * 执行
	 *
	 * @param baseEnum 基础枚举
	 * @param supplier 供应商
	 * @param key      key
	 * @param isget    isget
	 * @return {@link R}
	 */
	<R> R execute(BaseEnum baseEnum, Supplier<R> supplier, String key, Boolean isget);

	/**
	 * 执行
	 *
	 * @param baseEnum 基础枚举
	 * @param runnable 可运行
	 * @param key      key
	 * @param isget    isget
	 */
	void execute(BaseEnum baseEnum, Runnable runnable, String key, Boolean isget);

}

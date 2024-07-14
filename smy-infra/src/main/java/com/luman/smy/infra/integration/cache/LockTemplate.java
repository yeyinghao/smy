package com.luman.smy.integration.cache;


import com.luman.smy.infra.common.enums.BaseEnum;

import java.util.function.Supplier;

/**
 * 锁tmeplate
 *
 * @author yeyinghao
 * @date 2024/04/11
 */
public interface LockTemplate {

	/**
	 * 锁
	 *
	 * @param baseEnum 基础枚举
	 * @param bizId    业务id
	 * @param runnable 可运行
	 */
	void lock(BaseEnum baseEnum, Object bizId, Runnable runnable);

	/**
	 * 锁
	 *
	 * @param baseEnum 基础枚举
	 * @param bizId    业务id
	 * @param supplier 供应商
	 * @return {@link R}
	 */
	<R> R lock(BaseEnum baseEnum, Object bizId, Supplier<R> supplier);

	/**
	 * 试着锁
	 *
	 * @param baseEnum 基础枚举
	 * @param bizId    业务id
	 * @param runnable 可运行
	 */
	void tryLock(BaseEnum baseEnum, Object bizId, Runnable runnable);

	/**
	 * 试着锁
	 *
	 * @param baseEnum 基础枚举
	 * @param bizId    业务id
	 * @param supplier 供应商
	 * @return {@link R}
	 */
	<R> R tryLock(BaseEnum baseEnum, Object bizId, Supplier<R> supplier);

	/**
	 * 试锁
	 *
	 * @param baseEnum 基础枚举
	 * @param bizId    业务id
	 * @param runnable 可运行
	 */
	void tryLockEx(BaseEnum baseEnum, Object bizId, Runnable runnable);

	/**
	 * 试锁
	 *
	 * @param baseEnum 基础枚举
	 * @param bizId    业务id
	 * @param supplier 供应商
	 * @return {@link R}
	 */
	<R> R tryLockEx(BaseEnum baseEnum, Object bizId, Supplier<R> supplier);

}

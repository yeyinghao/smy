package com.luman.smy.infra.domain.cache.impl;

import com.luman.smy.domain.cache.CacheService;
import com.luman.smy.domain.cache.LockTemplate;
import com.luman.smy.client.enums.ByStringCode;
import com.luman.smy.infra.common.enums.ErrorEnum;
import com.luman.smy.infra.common.exception.CheckUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.function.Supplier;

/**
 * 锁template实现
 *
 * @author yeyinghao
 * @date 2024/04/11
 */
@Component
@RequiredArgsConstructor
public class LockTemplateImpl implements LockTemplate {

	/**
	 * 缓存服务
	 */
	private final CacheService cacheService;

	@Override
	public void lock(ByStringCode byStringCode, Object bizId, Runnable runnable) {
		Lock rLock = getLock(byStringCode, bizId);
		try {
			rLock.lock();
			runnable.run();
		} finally {
			rLock.unlock();
		}
	}

	@Override
	public <R> R lock(ByStringCode byStringCode, Object bizId, Supplier<R> supplier) {
		Lock rLock = getLock(byStringCode, bizId);
		try {
			rLock.lock();
			return supplier.get();
		} finally {
			rLock.unlock();
		}
	}

	@Override
	public void tryLock(ByStringCode byStringCode, Object bizId, Runnable runnable) {
		Lock rLock = getLock(byStringCode, bizId);
		try {
			if (!rLock.tryLock()) {
				return;
			}
			runnable.run();
		} finally {
			rLock.unlock();
		}
	}

	@Override
	public <R> R tryLock(ByStringCode byStringCode, Object bizId, Supplier<R> supplier) {
		Lock rLock = getLock(byStringCode, bizId);
		try {
			if (!rLock.tryLock()) {
				return null;
			}
			return supplier.get();
		} finally {
			rLock.unlock();
		}
	}

	@Override
	public void tryLockEx(ByStringCode byStringCode, Object bizId, Runnable runnable) {
		Lock rLock = getLock(byStringCode, bizId);
		try {
			CheckUtil.isTrue(rLock.tryLock(), ErrorEnum.BIZ_ERROR, "获取分布式锁失败");
			runnable.run();
		} finally {
			rLock.unlock();
		}
	}

	@Override
	public <R> R tryLockEx(ByStringCode byStringCode, Object bizId, Supplier<R> supplier) {
		Lock rLock = getLock(byStringCode, bizId);
		try {
			CheckUtil.isTrue(rLock.tryLock(), ErrorEnum.BIZ_ERROR, "获取分布式锁失败");
			return supplier.get();
		} finally {
			rLock.unlock();
		}
	}

	/**
	 * 获取rlock
	 *
	 * @param byStringCode 基础枚举
	 * @param bizId    业务id
	 * @return {@link Lock}
	 */
	private Lock getLock(ByStringCode byStringCode, Object bizId) {
		if (Objects.isNull(bizId)) {
			return cacheService.getLock(byStringCode.getCode());
		}
		return cacheService.getLock(byStringCode.getCode() + bizId);
	}
}

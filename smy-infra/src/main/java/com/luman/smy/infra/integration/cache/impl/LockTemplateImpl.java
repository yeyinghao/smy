package com.luman.smy.infra.integration.cache.impl;

import com.luman.smy.infra.common.enums.BaseEnum;
import com.luman.smy.infra.common.enums.CommErrorEnum;
import com.luman.smy.infra.common.exception.ValidationUtils;
import com.luman.smy.infra.integration.cache.CacheService;
import com.luman.smy.infra.integration.cache.LockTemplate;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * 锁tmeplate实现
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
	public void lock(BaseEnum baseEnum, Object bizId, Runnable runnable) {
		RLock rLock = getRLock(baseEnum, bizId);
		try {
			rLock.lock();
			runnable.run();
		} finally {
			rLock.unlock();
		}
	}

	@Override
	public <R> R lock(BaseEnum baseEnum, Object bizId, Supplier<R> supplier) {
		RLock rLock = getRLock(baseEnum, bizId);
		try {
			rLock.lock();
			return supplier.get();
		} finally {
			rLock.unlock();
		}
	}

	@Override
	public void tryLock(BaseEnum baseEnum, Object bizId, Runnable runnable) {
		RLock rLock = getRLock(baseEnum, bizId);
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
	public <R> R tryLock(BaseEnum baseEnum, Object bizId, Supplier<R> supplier) {
		RLock rLock = getRLock(baseEnum, bizId);
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
	public void tryLockEx(BaseEnum baseEnum, Object bizId, Runnable runnable) {
		RLock rLock = getRLock(baseEnum, bizId);
		try {
			ValidationUtils.isTrue(rLock.tryLock(), CommErrorEnum.BIZ_ERROR, "获取分布式锁失败");
			runnable.run();
		} finally {
			rLock.unlock();
		}
	}

	@Override
	public <R> R tryLockEx(BaseEnum baseEnum, Object bizId, Supplier<R> supplier) {
		RLock rLock = getRLock(baseEnum, bizId);
		try {
			ValidationUtils.isTrue(rLock.tryLock(), CommErrorEnum.BIZ_ERROR, "获取分布式锁失败");
			return supplier.get();
		} finally {
			rLock.unlock();
		}
	}

	/**
	 * 获取rlock
	 *
	 * @param baseEnum 基础枚举
	 * @param bizId    业务id
	 * @return {@link RLock}
	 */
	private RLock getRLock(BaseEnum baseEnum, Object bizId) {
		if (Objects.isNull(bizId)) {
			return cacheService.getLock(baseEnum.name());
		}
		return cacheService.getLock(baseEnum.name() + bizId);
	}
}

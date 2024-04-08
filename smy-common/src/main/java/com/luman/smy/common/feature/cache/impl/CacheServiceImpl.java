/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2023.10
 */

package com.luman.smy.common.feature.cache.impl;

import com.luman.smy.common.feature.cache.CacheClient;
import com.luman.smy.common.feature.cache.CacheService;
import com.luman.smy.common.feature.cache.config.CacheConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.*;
import org.springframework.stereotype.Service;

/**
 * 缓存服务实现
 *
 * @author yeyinghao
 * @date 2024/04/04
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CacheServiceImpl implements CacheService {

	/**
	 * redisson client对象
	 */
	private final CacheClient cacheClient;

	/**
	 * redisson配置
	 */
	private final CacheConfig redissonConfig;

	@Override
	public <T> T get(String key) {
		return cacheClient.get(redissonConfig.getRealKey(key));
	}

	@Override
	public <T> void save(String key, T value) {
		cacheClient.save(redissonConfig.getRealKey(key), value);
	}

	@Override
	public <T> void saveExpire(String key, T value, long expired) {
		cacheClient.saveExpire(redissonConfig.getRealKey(key), value, expired);
	}

	@Override
	public <T> boolean saveIfAbsent(String key, T value) {
		return cacheClient.saveIfAbsent(redissonConfig.getRealKey(key), value);
	}

	@Override
	public <T> boolean saveIfAbsentExpire(String key, T value, long expired) {
		return cacheClient.saveIfAbsentExpire(redissonConfig.getRealKey(key), value, expired);
	}

	@Override
	public void remove(String key) {
		cacheClient.remove(redissonConfig.getRealKey(key));
	}

	@Override
	public boolean exists(String key) {
		return cacheClient.exists(redissonConfig.getRealKey(key));
	}

	@Override
	public <T> RList<T> getRedisList(String key) {
		return cacheClient.getRedisList(redissonConfig.getRealKey(key));
	}

	@Override
	public <K, V> RMapCache<K, V> getRedisMapCache(String key) {
		return cacheClient.getRedisMapCache(redissonConfig.getRealKey(key));
	}

	@Override
	public <K, V> RMap<K, V> getRedisMap(String key) {
		return cacheClient.getRedisMap(redissonConfig.getRealKey(key));
	}

	@Override
	public <T> RSet<T> getRedisSet(String key) {
		return cacheClient.getRedisSet(redissonConfig.getRealKey(key));
	}

	@Override
	public <T> RScoredSortedSet<T> getRedisScoredSortedSet(String key) {
		return cacheClient.getRedisScoredSortedSet(redissonConfig.getRealKey(key));
	}

	@Override
	public RLock getRedisLock(String key) {
		return cacheClient.getRedisLock(redissonConfig.getRealKey(key));
	}

	@Override
	public long getKeyExpired(String key) {
		return cacheClient.getKeyExpired(redissonConfig.getRealKey(key));
	}
}

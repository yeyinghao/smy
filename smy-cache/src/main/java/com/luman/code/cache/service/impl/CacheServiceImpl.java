/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2023.10
 */

package com.luman.code.cache.service.impl;

import com.luman.code.cache.client.CacheClient;
import com.luman.code.cache.service.CacheService;
import com.luman.code.cache.config.RedissonConfig;
import com.luman.code.util.constant.CommConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.*;
import org.springframework.stereotype.Service;

/**
 * @author yeyinghao
 * @version 1.0.0
 * @description:
 * @date: 2023/2/28 13:51
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CacheServiceImpl implements CacheService {

	/**
	 * RedissonProxy对象
	 */
	private final CacheClient cacheClient;

	/**
	 * 缓存配置
	 */
	private final RedissonConfig redissonConfig;

	@Override
	public <T> T getObject(String key) {
		return cacheClient.getObject(getRealKey(key));
	}

	@Override
	public long getKeyExpired(String key) {
		return cacheClient.getKeyExpired(getRealKey(key));
	}

	@Override
	public String getString(String key) {
		return cacheClient.getString(getRealKey(key));
	}

	@Override
	public <T> void saveObject(String key, T value) {
		cacheClient.saveObject(getRealKey(key), value);
	}

	@Override
	public void saveString(String key, String value) {
		cacheClient.saveString(getRealKey(key), value);
	}

	@Override
	public void saveStringExpire(String key, String value, long expired) {
		cacheClient.saveStringExpire(getRealKey(key), value, expired);
	}

	@Override
	public boolean saveStringIfAbsentExpire(String key, String value, long expired) {
		return cacheClient.saveStringIfAbsentExpire(getRealKey(key), value, expired);
	}

	@Override
	public boolean saveStringIfAbsent(String key, String value) {
		return cacheClient.saveStringIfAbsent(getRealKey(key), value);
	}

	@Override
	public <T> void saveExpire(String key, T value, long expired) {
		cacheClient.saveExpire(getRealKey(key), value, expired);
	}

	@Override
	public void remove(String key) {
		cacheClient.remove(getRealKey(key));
	}

	@Override
	public boolean exists(String key) {
		return cacheClient.exists(getRealKey(key));
	}

	@Override
	public <T> RList<T> getRedisList(String key) {
		return cacheClient.getRedisList(getRealKey(key));
	}

	@Override
	public <K, V> RMapCache<K, V> getRedisMapCache(String key) {
		return cacheClient.getRedisMapCache(getRealKey(key));
	}

	@Override
	public <K, V> RMap<K, V> getRedisMap(String key) {
		return cacheClient.getRedisMap(getRealKey(key));
	}

	@Override
	public <T> RSet<T> getRedisSet(String key) {
		return cacheClient.getRedisSet(getRealKey(key));
	}

	@Override
	public <T> RScoredSortedSet<T> getRedisScoredSortedSet(String key) {
		return cacheClient.getRedisScoredSortedSet(getRealKey(key));
	}

	@Override
	public RLock getRedisLock(String key) {
		return cacheClient.getRedisLock(getRealKey(key));
	}

	private String getRealKey(String key) {
		return redissonConfig.getProjectKeyPrefix() + CommConstant.UNDERLINE + key;
	}
}

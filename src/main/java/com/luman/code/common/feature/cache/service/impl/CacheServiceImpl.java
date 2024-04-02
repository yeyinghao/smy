/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2023.10
 */

package com.luman.code.common.feature.cache.service.impl;

import com.luman.code.common.annotations.cal.Cal;
import com.luman.code.common.feature.cache.config.RedissonConfig;
import com.luman.code.common.feature.cache.service.CacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.*;
import org.redisson.client.codec.StringCodec;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.util.concurrent.TimeUnit;

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
	 * redisson client对象
	 */
	private final RedissonClient redissonClient;

	private final RedissonConfig redissonConfig;

	@Cal(isGet = true)
	@Override
	public <T> T getObject(String key) {
		RBucket<T> bucket = redissonClient.getBucket(key);
		return bucket.get();
	}

	@Cal(isGet = true)
	@Override
	public String getString(String key) {
		RBucket<String> bucket = redissonClient.getBucket(key, StringCodec.INSTANCE);
		return bucket.get();
	}

	@Cal(isGet = false)
	@Override
	public <T> void saveObject(String key, T value) {
		RBucket<T> bucket = redissonClient.getBucket(key);
		bucket.set(value, redissonConfig.getDefaultExpiredSecond(), TimeUnit.SECONDS);
	}

	@Cal(isGet = false)
	@Override
	public void saveString(String key, String value) {
		RBucket<String> bucket = redissonClient.getBucket(key, StringCodec.INSTANCE);
		bucket.set(value, redissonConfig.getDefaultExpiredSecond(), TimeUnit.SECONDS);
	}

	@Cal(isGet = false)
	@Override
	public void saveStringExpire(String key, String value, long expired) {
		RBucket<String> bucket = redissonClient.getBucket(key, StringCodec.INSTANCE);
		bucket.set(value, expired <= 0 ? redissonConfig.getDefaultExpiredSecond() : expired, TimeUnit.SECONDS);
	}

	@Cal(isGet = false)
	@Override
	public boolean saveStringIfAbsentExpire(String key, String value, long expired) {
		RBucket<String> bucket = redissonClient.getBucket(key, StringCodec.INSTANCE);
		return bucket.trySet(value, expired <= 0 ? redissonConfig.getDefaultExpiredSecond() : expired, TimeUnit.SECONDS);
	}

	@Cal(isGet = false)
	@Override
	public boolean saveStringIfAbsent(String key, String value) {
		RBucket<String> bucket = redissonClient.getBucket(key, StringCodec.INSTANCE);
		return bucket.trySet(value);
	}

	@Cal(isGet = false)
	@Override
	public <T> void saveExpire(String key, T value, long expired) {
		RBucket<T> bucket = redissonClient.getBucket(key);
		bucket.set(value, expired <= 0 ? redissonConfig.getDefaultExpiredSecond() : expired, TimeUnit.SECONDS);
	}

	@Cal(isGet = true)
	@Override
	public void remove(String key) {
		redissonClient.getBucket(key).delete();
	}

	@Cal(isGet = true)
	@Override
	public boolean exists(String key) {
		return redissonClient.getBucket(key).isExists();
	}

	@Cal(isGet = true)
	@Override
	public <T> RList<T> getRedisList(String key) {
		return redissonClient.getList(key);
	}

	@Cal(isGet = true)
	@Override
	public <K, V> RMapCache<K, V> getRedisMapCache(String key) {
		return redissonClient.getMapCache(key);
	}

	@Cal(isGet = true)
	@Override
	public <K, V> RMap<K, V> getRedisMap(String key) {
		return redissonClient.getMap(key);
	}

	@Cal(isGet = true)
	@Override
	public <T> RSet<T> getRedisSet(String key) {
		return redissonClient.getSet(key);
	}

	@Cal(isGet = true)
	@Override
	public <T> RScoredSortedSet<T> getRedisScoredSortedSet(String key) {
		return redissonClient.getScoredSortedSet(key);
	}

	@Cal(isGet = true)
	@Override
	public RLock getRedisLock(String key) {
		return redissonClient.getLock(key);
	}

	@Override
	public long getKeyExpired(String realKey) {
		return redissonClient.getKeys().remainTimeToLive(realKey) / 1000;
	}

	@PreDestroy
	public void destroy() {
		// 在程序关闭时，添加一个钩子函数用来释放链接资源 关闭Redisson客户端
		if (!redissonClient.isShutdown()) {
			Runtime.getRuntime().addShutdownHook(new Thread(redissonClient::shutdown));
		}
	}
}

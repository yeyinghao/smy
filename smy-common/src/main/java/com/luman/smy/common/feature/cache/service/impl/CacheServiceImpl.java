/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2023.10
 */

package com.luman.smy.common.feature.cache.service.impl;

import com.luman.smy.common.annotations.cal.Cal;
import com.luman.smy.common.feature.cache.config.RedissonConfig;
import com.luman.smy.common.feature.cache.service.CacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.*;
import org.redisson.client.codec.StringCodec;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.util.concurrent.TimeUnit;

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
	private final RedissonClient redissonClient;

	/**
	 * redisson配置
	 */
	private final RedissonConfig redissonConfig;

	@Cal(isGet = true)
	@Override
	public <T> T get(String key) {
		RBucket<T> bucket = redissonClient.getBucket(redissonConfig.getRealKey(key));
		return bucket.get();
	}

	@Cal(isGet = false)
	@Override
	public <T> void save(String key, T value) {
		RBucket<T> bucket = redissonClient.getBucket(redissonConfig.getRealKey(key));
		bucket.set(value, redissonConfig.getDefaultExpiredSecond(), TimeUnit.SECONDS);
	}

	@Cal(isGet = false)
	@Override
	public <T> void saveExpire(String key, T value, long expired) {
		RBucket<T> bucket = redissonClient.getBucket(redissonConfig.getRealKey(key));
		bucket.set(value, redissonConfig.getExpired(expired), TimeUnit.SECONDS);
	}

	@Cal(isGet = false)
	@Override
	public <T> boolean saveIfAbsent(String key, T value) {
		RBucket<T> bucket = redissonClient.getBucket(redissonConfig.getRealKey(key), StringCodec.INSTANCE);
		return bucket.trySet(value);
	}

	@Cal(isGet = false)
	@Override
	public <T> boolean saveIfAbsentExpire(String key, T value, long expired) {
		RBucket<T> bucket = redissonClient.getBucket(redissonConfig.getRealKey(key), StringCodec.INSTANCE);
		return bucket.trySet(value, redissonConfig.getExpired(expired), TimeUnit.SECONDS);
	}

	@Cal(isGet = true)
	@Override
	public void remove(String key) {
		redissonClient.getBucket(redissonConfig.getRealKey(key)).delete();
	}

	@Cal(isGet = true)
	@Override
	public boolean exists(String key) {
		return redissonClient.getBucket(redissonConfig.getRealKey(key)).isExists();
	}

	@Cal(isGet = true)
	@Override
	public <T> RList<T> getRedisList(String key) {
		return redissonClient.getList(redissonConfig.getRealKey(key));
	}

	@Cal(isGet = true)
	@Override
	public <K, V> RMapCache<K, V> getRedisMapCache(String key) {
		return redissonClient.getMapCache(redissonConfig.getRealKey(key));
	}

	@Cal(isGet = true)
	@Override
	public <K, V> RMap<K, V> getRedisMap(String key) {
		return redissonClient.getMap(redissonConfig.getRealKey(key));
	}

	@Cal(isGet = true)
	@Override
	public <T> RSet<T> getRedisSet(String key) {
		return redissonClient.getSet(redissonConfig.getRealKey(key));
	}

	@Cal(isGet = true)
	@Override
	public <T> RScoredSortedSet<T> getRedisScoredSortedSet(String key) {
		return redissonClient.getScoredSortedSet(redissonConfig.getRealKey(key));
	}

	@Cal(isGet = true)
	@Override
	public RLock getRedisLock(String key) {
		return redissonClient.getLock(redissonConfig.getRealKey(key));
	}

	@Override
	public long getKeyExpired(String key) {
		return redissonClient.getKeys().remainTimeToLive(redissonConfig.getRealKey(key)) / 1000;
	}

	@PreDestroy
	public void destroy() {
		// 在程序关闭时，添加一个钩子函数用来释放链接资源 关闭Redisson客户端
		if (!redissonClient.isShutdown()) {
			Runtime.getRuntime().addShutdownHook(new Thread(redissonClient::shutdown));
		}
	}
}

/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2023.10
 */

package com.luman.smy.common.feature.cache.impl;

import com.luman.smy.common.annotations.cal.Cal;
import com.luman.smy.common.feature.cache.CacheClient;
import com.luman.smy.common.feature.cache.config.CacheConfig;
import com.luman.smy.common.feature.cache.constant.CalConstant;
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
public class CacheClientImpl implements CacheClient {

	/**
	 * redisson client对象
	 */
	private final RedissonClient redissonClient;

	/**
	 * redisson配置
	 */
	private final CacheConfig redissonConfig;

	@Cal(name = CalConstant.GET.NAME, desc = CalConstant.GET.DESC, isGet = true)
	@Override
	public <T> T get(String key) {
		RBucket<T> bucket = redissonClient.getBucket(key);
		return bucket.get();
	}

	@Cal(name = CalConstant.SAVE.NAME, desc = CalConstant.SAVE.DESC, isGet = false)
	@Override
	public <T> void save(String key, T value) {
		RBucket<T> bucket = redissonClient.getBucket(key);
		bucket.set(value, redissonConfig.getDefaultExpiredSecond(), TimeUnit.SECONDS);
	}

	@Cal(name = CalConstant.SAVE_EXPIRE.NAME, desc = CalConstant.SAVE_EXPIRE.DESC, isGet = false)
	@Override
	public <T> void saveExpire(String key, T value, long expired) {
		RBucket<T> bucket = redissonClient.getBucket(key);
		bucket.set(value, redissonConfig.getExpired(expired), TimeUnit.SECONDS);
	}

	@Cal(name = CalConstant.SAVE_IF_ABSENT.NAME, desc = CalConstant.SAVE_IF_ABSENT.DESC, isGet = false)
	@Override
	public <T> boolean saveIfAbsent(String key, T value) {
		RBucket<T> bucket = redissonClient.getBucket(key, StringCodec.INSTANCE);
		return bucket.trySet(value);
	}

	@Cal(name = CalConstant.SAVE_IF_ABSENT_EXPIRE.NAME, desc = CalConstant.SAVE_IF_ABSENT_EXPIRE.DESC, isGet = false)
	@Override
	public <T> boolean saveIfAbsentExpire(String key, T value, long expired) {
		RBucket<T> bucket = redissonClient.getBucket(key, StringCodec.INSTANCE);
		return bucket.trySet(value, redissonConfig.getExpired(expired), TimeUnit.SECONDS);
	}

	@Cal(name = CalConstant.REMOVE.NAME, desc = CalConstant.REMOVE.DESC, isGet = true)
	@Override
	public void remove(String key) {
		redissonClient.getBucket(key).delete();
	}

	@Cal(name = CalConstant.EXISTS.NAME, desc = CalConstant.EXISTS.DESC, isGet = true)
	@Override
	public boolean exists(String key) {
		return redissonClient.getBucket(key).isExists();
	}

	@Cal(name = CalConstant.GET_REDIS_LIST.NAME, desc = CalConstant.GET_REDIS_LIST.DESC, isGet = true)
	@Override
	public <T> RList<T> getRedisList(String key) {
		return redissonClient.getList(key);
	}

	@Cal(name = CalConstant.GET_REDIS_MAP_CACHE.NAME, desc = CalConstant.GET_REDIS_MAP_CACHE.DESC, isGet = true)
	@Override
	public <K, V> RMapCache<K, V> getRedisMapCache(String key) {
		return redissonClient.getMapCache(key);
	}

	@Cal(name = CalConstant.GET_REDIS_MAP.NAME, desc = CalConstant.GET_REDIS_MAP.DESC, isGet = true)
	@Override
	public <K, V> RMap<K, V> getRedisMap(String key) {
		return redissonClient.getMap(key);
	}

	@Cal(name = CalConstant.GET_REDIS_SET.NAME, desc = CalConstant.GET_REDIS_SET.DESC, isGet = true)
	@Override
	public <T> RSet<T> getRedisSet(String key) {
		return redissonClient.getSet(key);
	}

	@Cal(name = CalConstant.GET_REDIS_SCORED_SORTED_SET.NAME, desc = CalConstant.GET_REDIS_SCORED_SORTED_SET.DESC, isGet = true)
	@Override
	public <T> RScoredSortedSet<T> getRedisScoredSortedSet(String key) {
		return redissonClient.getScoredSortedSet(key);
	}

	@Cal(name = CalConstant.GET_REDIS_LOCK.NAME, desc = CalConstant.GET_REDIS_LOCK.DESC, isGet = true)
	@Override
	public RLock getRedisLock(String key) {
		return redissonClient.getLock(key);
	}

	@Cal(name = CalConstant.GET_KEY_EXPIRED.NAME, desc = CalConstant.GET_KEY_EXPIRED.DESC, isGet = false)
	@Override
	public long getKeyExpired(String key) {
		return redissonClient.getKeys().remainTimeToLive(key) / 1000;
	}

	@PreDestroy
	public void destroy() {
		// 在程序关闭时，添加一个钩子函数用来释放链接资源 关闭Redisson客户端
		if (!redissonClient.isShutdown()) {
			Runtime.getRuntime().addShutdownHook(new Thread(redissonClient::shutdown));
		}
	}
}

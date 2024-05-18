/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2023.10
 */

package com.luman.smy.cache.impl;

import com.luman.smy.cache.CacheClient;
import com.luman.smy.cache.config.CacheConfig;
import com.luman.smy.cache.enums.CalEnum;
import com.luman.smy.common.template.Template;
import lombok.RequiredArgsConstructor;
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

	/**
	 * 执行模板
	 */
	private final Template cacheTemplate;

	@Override
	public <T> T get(String key) {
		return cacheTemplate.execute(CalEnum.GET, () -> {
			RBucket<T> bucket = redissonClient.getBucket(key);
			return bucket.get();
		}, key, true);
	}

	@Override
	public <T> void save(String key, T value) {
		cacheTemplate.execute(CalEnum.SAVE, () -> {
			RBucket<T> bucket = redissonClient.getBucket(key);
			bucket.set(value, redissonConfig.getDefaultExpiredSecond(), TimeUnit.SECONDS);
		}, key, false);
	}

	@Override
	public <T> void saveExpire(String key, T value, long expired) {
		cacheTemplate.execute(CalEnum.SAVE_EXPIRE, () -> {
			RBucket<T> bucket = redissonClient.getBucket(key);
			bucket.set(value, redissonConfig.getExpired(expired), TimeUnit.SECONDS);
		}, key, false);
	}

	@Override
	public <T> boolean trySave(String key, T value) {
		return cacheTemplate.execute(CalEnum.TRY_SAVE, () -> {
			RBucket<T> bucket = redissonClient.getBucket(key, StringCodec.INSTANCE);
			return bucket.trySet(value);
		}, key, false);
	}

	@Override
	public <T> boolean trySaveExpire(String key, T value, long expired) {
		return cacheTemplate.execute(CalEnum.TRY_SAVE_EXPIRE, () -> {
			RBucket<T> bucket = redissonClient.getBucket(key, StringCodec.INSTANCE);
			return bucket.trySet(value, redissonConfig.getExpired(expired), TimeUnit.SECONDS);
		}, key, false);
	}

	@Override
	public void delete(String key) {
		cacheTemplate.execute(CalEnum.DELETE, () -> {
			redissonClient.getBucket(key).delete();
		}, key, false);
	}

	@Override
	public boolean isExists(String key) {
		return cacheTemplate.execute(CalEnum.IS_EXISTS, () -> {
			return redissonClient.getBucket(key).isExists();
		}, key, true);
	}

	@Override
	public <T> RList<T> getList(String key) {
		return cacheTemplate.execute(CalEnum.GET_LIST, () -> {
			return redissonClient.getList(key);
		}, key, true);
	}

	@Override
	public <K, V> RMapCache<K, V> getMapCache(String key) {
		return cacheTemplate.execute(CalEnum.GET_MAP_CACHE, () -> {
			return redissonClient.getMapCache(key);
		}, key, true);
	}

	@Override
	public <K, V> RMap<K, V> getMap(String key) {
		return cacheTemplate.execute(CalEnum.GET_MAP, () -> {
			return redissonClient.getMap(key);
		}, key, true);
	}

	@Override
	public <T> RSet<T> getSet(String key) {
		return cacheTemplate.execute(CalEnum.GET_SET, () -> {
			return redissonClient.getSet(key);
		}, key, true);
	}

	@Override
	public <T> RScoredSortedSet<T> getScoredSortedSet(String key) {
		return cacheTemplate.execute(CalEnum.GET_SCORED_SORTED_SET, () -> {
			return redissonClient.getScoredSortedSet(key);
		}, key, true);
	}

	@Override
	public RLock getLock(String key) {
		return cacheTemplate.execute(CalEnum.GET_LOCK, () -> {
			return redissonClient.getLock(key);
		}, key, false);
	}

	@Override
	public long remainTimeToLive(String key) {
		return cacheTemplate.execute(CalEnum.REMAIN_TIME_TO_LIVE, () -> {
			return redissonClient.getKeys().remainTimeToLive(key);
		}, key, false);
	}

	@PreDestroy
	public void destroy() {
		// 在程序关闭时，添加一个钩子函数用来释放链接资源 关闭Redisson客户端
		if (!redissonClient.isShutdown()) {
			Runtime.getRuntime().addShutdownHook(new Thread(redissonClient::shutdown));
		}
	}
}

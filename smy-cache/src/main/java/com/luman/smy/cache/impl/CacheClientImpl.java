/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2023.10
 */

package com.luman.smy.cache.impl;

import com.luman.smy.cache.CacheClient;
import com.luman.smy.cache.config.CacheConfig;
import com.luman.smy.cache.constant.CalEnum;
import com.luman.smy.common.constant.LoggerConstant;
import com.luman.smy.common.template.ExecuteTemplate;
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
@Slf4j(topic = LoggerConstant.CAL_MONITOR_LOGGER)
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
	private final ExecuteTemplate executeTemplate;

	@Override
	public <T> T get(String key) {
		return executeTemplate.execute(log, CalEnum.GET, () -> {
			RBucket<T> bucket = redissonClient.getBucket(key);
			return bucket.get();
		}, key);
	}

	@Override
	public <T> void save(String key, T value) {
		executeTemplate.execute(log, CalEnum.GET, () -> {
			RBucket<T> bucket = redissonClient.getBucket(key);
			bucket.set(value, redissonConfig.getDefaultExpiredSecond(), TimeUnit.SECONDS);
		}, key);
	}

	@Override
	public <T> void saveExpire(String key, T value, long expired) {
		executeTemplate.execute(log, CalEnum.GET, () -> {
			RBucket<T> bucket = redissonClient.getBucket(key);
			bucket.set(value, redissonConfig.getExpired(expired), TimeUnit.SECONDS);
		}, key);
	}

	@Override
	public <T> boolean saveIfAbsent(String key, T value) {
		return executeTemplate.execute(log, CalEnum.GET, () -> {
			RBucket<T> bucket = redissonClient.getBucket(key, StringCodec.INSTANCE);
			return bucket.trySet(value);
		}, key);
	}

	@Override
	public <T> boolean saveIfAbsentExpire(String key, T value, long expired) {
		return executeTemplate.execute(log, CalEnum.GET, () -> {
			RBucket<T> bucket = redissonClient.getBucket(key, StringCodec.INSTANCE);
			return bucket.trySet(value, redissonConfig.getExpired(expired), TimeUnit.SECONDS);
		}, key);
	}

	@Override
	public void remove(String key) {
		executeTemplate.execute(log, CalEnum.GET, () -> {
			redissonClient.getBucket(key).delete();
		}, key);
	}

	@Override
	public boolean exists(String key) {
		return executeTemplate.execute(log, CalEnum.GET, () -> {
			return redissonClient.getBucket(key).isExists();
		}, key);
	}

	@Override
	public <T> RList<T> getRedisList(String key) {
		return executeTemplate.execute(log, CalEnum.GET, () -> {
			return redissonClient.getList(key);
		}, key);
	}

	@Override
	public <K, V> RMapCache<K, V> getRedisMapCache(String key) {
		return executeTemplate.execute(log, CalEnum.GET, () -> {
			return redissonClient.getMapCache(key);
		}, key);
	}

	@Override
	public <K, V> RMap<K, V> getRedisMap(String key) {
		return executeTemplate.execute(log, CalEnum.GET, () -> {
			return redissonClient.getMap(key);
		}, key);
	}

	@Override
	public <T> RSet<T> getRedisSet(String key) {
		return executeTemplate.execute(log, CalEnum.GET, () -> {
			return redissonClient.getSet(key);
		}, key);
	}

	@Override
	public <T> RScoredSortedSet<T> getRedisScoredSortedSet(String key) {
		return executeTemplate.execute(log, CalEnum.GET, () -> {
			return redissonClient.getScoredSortedSet(key);
		}, key);
	}

	@Override
	public RLock getRedisLock(String key) {
		return executeTemplate.execute(log, CalEnum.GET, () -> {
			return redissonClient.getLock(key);
		}, key);
	}

	@Override
	public long remainTimeToLive(String key) {
		return executeTemplate.execute(log, CalEnum.GET, () -> {
			return redissonClient.getKeys().remainTimeToLive(key);
		}, key);
	}

	@PreDestroy
	public void destroy() {
		// 在程序关闭时，添加一个钩子函数用来释放链接资源 关闭Redisson客户端
		if (!redissonClient.isShutdown()) {
			Runtime.getRuntime().addShutdownHook(new Thread(redissonClient::shutdown));
		}
	}
}

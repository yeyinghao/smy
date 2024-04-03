/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2023.10
 */

package com.luman.smy.common.feature.cache.service;

import org.redisson.api.*;

/**
 * 缓存服务
 *
 * @author yeyinghao
 * @date 2023/09/17
 */
public interface CacheService {

	/**
	 * 得到对象
	 *
	 * @param key 缓存key
	 * @return 缓存返回值
	 */
	<T> T getObject(String key);

	/**
	 * 获取key过期时间(秒)
	 *
	 * @param key key
	 * @return long
	 */
	long getKeyExpired(String key);

	/**
	 * 得到字符串
	 *
	 * @param key 缓存key
	 * @return 缓存返回值
	 */
	String getString(String key);

	/**
	 * 保存对象
	 *
	 * @param key   关键
	 * @param value 价值
	 */
	<T> void saveObject(String key, T value);

	/**
	 * 保存字符串
	 *
	 * @param key   关键
	 * @param value 价值
	 */
	void saveString(String key, String value);

	/**
	 * 保存字符串过期
	 *
	 * @param key     缓存key
	 * @param value   缓存值
	 * @param expired 缓存过期时间
	 */
	void saveStringExpire(String key, String value, long expired);

	/**
	 * 如果没有，保存字符串过期
	 *
	 * @param key     缓存key
	 * @param value   缓存值
	 * @param expired 缓存过期时间
	 * @return boolean
	 */
	boolean saveStringIfAbsentExpire(String key, String value, long expired);

	/**
	 * 如果没有则保存字符串
	 *
	 * @param key   缓存key
	 * @param value 缓存值
	 * @return boolean
	 */
	boolean saveStringIfAbsent(String key, String value);

	/**
	 * 保存过期
	 *
	 * @param key     缓存key
	 * @param value   缓存值
	 * @param expired 缓存过期时间
	 */
	<T> void saveExpire(String key, T value, long expired);

	/**
	 * 删除
	 *
	 * @param key 关键
	 */
	void remove(String key);

	/**
	 * 存在
	 *
	 * @param key 关键
	 * @return boolean
	 */
	boolean exists(String key);

	/**
	 * 获取redis列表
	 *
	 * @param key 关键
	 * @return {@link RList}<{@link T}>
	 */
	<T> RList<T> getRedisList(String key);

	/**
	 * 获取redis映射缓存
	 *
	 * @param key 关键
	 * @return {@link RMapCache}<{@link K}, {@link V}>
	 */
	<K, V> RMapCache<K, V> getRedisMapCache(String key);

	/**
	 * 获取redis地图
	 *
	 * @param key 关键
	 * @return {@link RMap}<{@link K}, {@link V}>
	 */
	<K, V> RMap<K, V> getRedisMap(String key);

	/**
	 * 设置redis
	 *
	 * @param key 关键
	 * @return {@link RSet}<{@link T}>
	 */
	<T> RSet<T> getRedisSet(String key);

	/**
	 * 得到redis评分排序集
	 *
	 * @param key 关键
	 * @return {@link RScoredSortedSet}<{@link T}>
	 */
	<T> RScoredSortedSet<T> getRedisScoredSortedSet(String key);

	/**
	 * 获取redis锁
	 *
	 * @param key 关键
	 * @return {@link RLock}
	 */
	RLock getRedisLock(String key);
}

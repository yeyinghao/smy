package com.luman.code.cache;

import org.redisson.api.*;

/**
 * redisson代理
 *
 * @author yeyinghao
 * @date 2023/09/16
 */
public interface CacheClient {

	/**
	 * 获取对象
	 *
	 * @param key 缓存key
	 * @return 缓存返回值
	 */
	<T> T getObject(String key);

	/**
	 * 获取字符串
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
	 * 保存到期
	 *
	 * @param key     缓存key
	 * @param value   缓存值
	 * @param expired 缓存过期时间
	 */
	<T> void saveExpire(String key, T value, long expired);

	/**
	 * 删除
	 *
	 * @param key key
	 */
	void remove(String key);

	/**
	 * 存在
	 *
	 * @param key key
	 * @return boolean
	 */
	boolean exists(String key);

	/**
	 * 获取redis列表
	 *
	 * @param key key
	 * @return {@link RList}<{@link T}>
	 */
	<T> RList<T> getRedisList(String key);

	/**
	 * 获取redis地图缓存
	 *
	 * @param key key
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
	 * 获取redis集
	 *
	 * @param key 关键
	 * @return {@link RSet}<{@link T}>
	 */
	<T> RSet<T> getRedisSet(String key);

	/**
	 * 获取redis得分排序集
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

	/**
	 * 获取key过期过期时间
	 *
	 * @param realKey 真正key
	 * @return long
	 */
	long getKeyExpired(String realKey);
}

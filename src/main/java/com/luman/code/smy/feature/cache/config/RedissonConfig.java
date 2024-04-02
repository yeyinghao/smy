package com.luman.code.smy.feature.cache.config;

import cn.hutool.core.map.MapUtil;
import lombok.Data;
import org.redisson.api.RedissonClient;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Map;


/**
 * redisson配置
 *
 * @author yeyinghao
 * @date 2023/09/16
 */
@Component
@EnableCaching
@Data
@ConfigurationProperties(prefix = "smy.cache")
public class RedissonConfig {

	/**
	 * 项目key前缀
	 */
	private String projectKeyPrefix;

	/**
	 * 缓存key前缀
	 */
	private String cacheKeyPrefix;

	/**
	 * TTL毫秒
	 */
	private Long ttlMillSecond;

	/**
	 * 最大空闲时间毫秒
	 */
	private Long maxIdleTimeMillSecond;

	/**
	 * 默认过期秒
	 */
	private Long defaultExpiredSecond;

	/**
	 * 缓存管理器
	 *
	 * @param redissonClient redisson客户
	 * @return {@link CacheManager}
	 */
	@Bean
	public CacheManager cacheManager(RedissonClient redissonClient) {
		Map<String, org.redisson.spring.cache.CacheConfig> config = MapUtil.newHashMap();
		// 创建一个名称为"testMap"的缓存，过期时间ttl为24分钟，同时最长空闲时maxIdleTime为12分钟。
		org.redisson.spring.cache.CacheConfig cacheConfig = new org.redisson.spring.cache.CacheConfig(ttlMillSecond, maxIdleTimeMillSecond);
		config.put(cacheKeyPrefix, cacheConfig);
		return new RedissonSpringCacheManager(redissonClient, config);
	}
}

package com.luman.code.file.config;

import com.luman.code.util.util.LoggerUtil;
import io.minio.MinioClient;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * minio配置
 *
 * @author yeyinghao
 * @date 2023/09/16
 */
@Component
@Data
@Slf4j
public class MinioConfig {

	/**
	 * 是一个URL，域名，IPv4或者IPv6地址")
	 */
	@Value("${minio.endpoint}")
	private String endPoint;

	/**
	 * "accessKey类似于用户ID，用于唯一标识你的账户"
	 */
	@Value("${minio.accessKey}")
	private String accessKey;

	/**
	 * "secretKey是你账户的密码"
	 */
	@Value("${minio.secretKey}")
	private String secretKey;

	/**
	 * "如果是true，则用的是https而不是http,默认值是true"
	 */
	@Value("${minio.secure}")
	private Boolean secure;

	/**
	 * 图片的最大大小
	 */
	@Value("${minio.imageSize}")
	private Long imageSize;

	/**
	 * 其他文件的最大大小
	 */
	@Value("${minio.fileSize}")
	private Long fileSize;

	/**
	 * minio客户
	 *
	 * @return {@link MinioClient}
	 */
	@Bean
	public MinioClient minioClient() {
		try {
			return MinioClient.builder().credentials(accessKey, secretKey).endpoint(endPoint).build();
		} catch (Throwable e) {
			LoggerUtil.error(log, e);
		}
		return null;
	}
}

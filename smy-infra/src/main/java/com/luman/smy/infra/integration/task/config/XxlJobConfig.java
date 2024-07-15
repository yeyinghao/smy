/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2024.2
 */

package com.luman.smy.infra.integration.task.config;

import com.luman.smy.infra.common.util.LoggerUtil;
import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * XXL作业配置
 *
 * @author yeyinghao
 * @date 2023/09/24
 */
@Slf4j
@Data
@Configuration
@ConfigurationProperties(prefix = "xxl.job")
public class XxlJobConfig {

	/**
	 * admin
	 */
	private AdminProperties admin = new AdminProperties();

	/**
	 * Executor
	 */
	private ExecutorProperties executor = new ExecutorProperties();

	/**
	 * @author yeyinghao
	 * @date 2024/02/28
	 */
	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	public static class AdminProperties {
		/**
		 * 管理地址
		 */
		private String address;

		/**
		 * 访问令牌
		 */
		private String accessToken;
	}

	/**
	 * @author yeyinghao
	 * @date 2024/02/28
	 */
	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	public static class ExecutorProperties {
		/**
		 * 地址
		 */
		private String address;

		/**
		 * 浏览器名称
		 */
		private String appname;

		/**
		 * 知识产权
		 */
		private String ip;

		/**
		 * logpath
		 */
		private String logpath;

		/**
		 * logretentiondays
		 */
		private Integer logretentiondays;

		/**
		 * 端口
		 */
		private Integer port;
	}

	/**
	 * XXL作业执行器
	 *
	 * @return {@link XxlJobSpringExecutor}
	 */
	@Bean
	public XxlJobSpringExecutor xxlJobExecutor() {
		LoggerUtil.info(log, ">>>>>>>>>>> xxl-job config init.");
		XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
		xxlJobSpringExecutor.setAdminAddresses(admin.getAddress());
		xxlJobSpringExecutor.setAppname(executor.getAppname());
		xxlJobSpringExecutor.setIp(executor.getIp());
		xxlJobSpringExecutor.setPort(executor.getPort());
		xxlJobSpringExecutor.setAddress(executor.getAddress());
		xxlJobSpringExecutor.setAccessToken(admin.getAccessToken());
		xxlJobSpringExecutor.setLogPath(executor.getLogpath());
		xxlJobSpringExecutor.setLogRetentionDays(executor.getLogretentiondays());
		return xxlJobSpringExecutor;
	}
}

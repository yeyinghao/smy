package com.luman.smy.infra.integration.task;

import com.luman.smy.infra.common.enums.BaseEnum;
import org.springframework.boot.ApplicationRunner;

/**
 * 任务服务
 *
 * @author yeyinghao
 * @date 2024/04/05
 */
public interface TaskService<T> extends ApplicationRunner {

	/**
	 * tarnceId附加日志模式
	 */
	String TRANCE_ID_APPEND_LOG_PATTERN = "task start tranceId={}";

	/**
	 * 错误枚举追加日志模式
	 */
	String ERROR_ENUM_APPEND_LOG_PATTERN = "errorEnum={}, subMessage={}";

	/**
	 * doHandle
	 *
	 * @param t t
	 */
	void handle(T t);

	BaseEnum taskEnum();
}

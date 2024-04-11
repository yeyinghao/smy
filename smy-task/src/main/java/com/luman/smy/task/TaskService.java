package com.luman.smy.task;

import java.util.List;

/**
 * 任务服务
 *
 * @author yeyinghao
 * @date 2024/04/05
 */
public interface TaskService<T> {

	/**
	 * 数据处理
	 *
	 * @return {@link List}<{@link T}>
	 */
	List<T> dataHandle();

	/**
	 * doHandle
	 *
	 * @param t t
	 */
	void doHandle(T t);
}

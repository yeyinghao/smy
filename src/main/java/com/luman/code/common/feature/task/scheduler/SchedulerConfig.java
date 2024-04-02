package com.luman.code.common.feature.task.scheduler;

/**
 * 单个任务配置
 */
public abstract class SchedulerConfig {

	public abstract String getName();

	public abstract String getCron();

	public abstract String getLockKey();

	public abstract Runnable doTask();
}
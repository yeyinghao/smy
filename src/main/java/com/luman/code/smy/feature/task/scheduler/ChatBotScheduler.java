package com.luman.code.smy.feature.task.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChatBotScheduler implements SchedulingConfigurer {

	/**
	 * 调度程序配置
	 */
	private final List<SchedulerConfig> schedulerConfigs;

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		for (SchedulerConfig item : schedulerConfigs) {
			taskRegistrar.addCronTask(item.doTask(), item.getCron());
		}
	}
}

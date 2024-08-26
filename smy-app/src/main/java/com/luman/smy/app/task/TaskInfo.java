package com.luman.smy.app.task;

import cn.hutool.json.JSONUtil;
import com.luman.smy.client.enums.ByCode;
import com.luman.smy.infra.common.util.LoggerUtil;
import com.luman.smy.infra.db.user.model.User;
import com.luman.smy.infra.integration.cache.enums.CalEnum;
import com.luman.smy.infra.integration.task.template.MultiTaskTemplate;
import com.xxl.job.core.executor.XxlJobExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 任务信息
 *
 * @author yeyinghao
 * @date 2024/08/25
 */
@Component
@Slf4j
public class TaskInfo extends MultiTaskTemplate<User> {

	@Override
	public List<User> datas() {
		return List.of(new User());
	}

	@Override
	public void handle(User user) {
		LoggerUtil.info(log, JSONUtil.toJsonStr(user));
	}

	@Override
	public ByCode taskEnum() {
		return CalEnum.GET;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		XxlJobExecutor.registJobHandler("testJobHandler", new TaskInfo());
	}
}

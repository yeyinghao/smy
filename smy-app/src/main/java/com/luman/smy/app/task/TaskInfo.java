package com.luman.smy.app.task;

import cn.hutool.json.JSONUtil;
import com.luman.smy.infra.common.enums.BaseEnum;
import com.luman.smy.infra.common.util.LoggerUtil;
import com.luman.smy.infra.db.user.model.User;
import com.luman.smy.infra.integration.cache.enums.CalEnum;
import com.luman.smy.infra.integration.task.template.MultiTaskTemplate;
import com.xxl.job.core.executor.XxlJobExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.util.List;

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
	public BaseEnum taskEnum() {
		return CalEnum.GET;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		XxlJobExecutor.registJobHandler("testJobHandler", new TaskInfo());
	}
}

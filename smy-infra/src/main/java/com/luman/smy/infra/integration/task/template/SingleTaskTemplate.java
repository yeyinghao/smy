package com.luman.smy.infra.integration.task.template;

import com.luman.smy.infra.common.exception.SmyBizException;
import com.luman.smy.infra.common.util.TraceIdUtil;
import com.luman.smy.infra.integration.task.TaskService;
import com.luman.smy.infra.integration.task.model.TaskResult;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.IJobHandler;

/**
 * 任务模板
 *
 * @author yeyinghao
 * @date 2024/04/04
 */
public abstract class SingleTaskTemplate<T> extends IJobHandler implements TaskService<T> {

	/**
	 * 数据处理
	 *
	 * @return {@link T }
	 */
	protected abstract T data();

	@Override
	public void execute() throws Exception {
		TaskResult taskResultDP = TaskResult.init(taskEnum());
		try {
			XxlJobHelper.log(TRANCE_ID_APPEND_LOG_PATTERN, TraceIdUtil.getThreadTraceId());
			try {
				handle(data());
				taskResultDP.addSussNum();
				return;
			} catch (SmyBizException e) {
				XxlJobHelper.log(ERROR_ENUM_APPEND_LOG_PATTERN, e.getErrorEnum(), e.getMessage());
			} catch (Throwable e) {
				XxlJobHelper.log(e);
			}
			XxlJobHelper.log(String.valueOf(data()));
			taskResultDP.addFailNum();
		} finally {
			XxlJobHelper.log(String.valueOf(taskResultDP));
		}
	}
}

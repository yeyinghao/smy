package com.luman.smy.infra.integration.task.template;

import com.luman.smy.infra.common.constant.CommConstant;
import com.luman.smy.infra.common.enums.BaseEnum;
import com.luman.smy.infra.common.exception.SmyBizException;
import com.luman.smy.infra.common.util.TraceIdUtil;
import com.luman.smy.infra.integration.task.model.TaskResult;
import com.xxl.job.core.context.XxlJobHelper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 任务模板
 *
 * @author yeyinghao
 * @date 2024/04/04
 */
@Component
public class TaskTemplate {

	/**
	 * tarnceId附加日志模式
	 */
	private static final String TRANCE_ID_APPEND_LOG_PATTERN = "task start tranceId={}";

	/**
	 * 错误枚举追加日志模式
	 */
	private static final String ERROR_ENUM_APPEND_LOG_PATTERN = "errorEnum={}, subMessage={}";

	/**
	 * 任务处理
	 *
	 * @param consumer 消费者
	 * @param taskEnum 任务枚举
	 * @param supplier 供应商
	 * @return boolean
	 */
	public <T> boolean taskHandle(BaseEnum taskEnum, Supplier<Collection<T>> supplier, Consumer<T> consumer) {
		TaskResult taskResultDP = TaskResult.init(taskEnum);
		try {
			XxlJobHelper.log(TRANCE_ID_APPEND_LOG_PATTERN, TraceIdUtil.getThreadTraceId());
			Collection<T> objects = supplier.get();
			objects.forEach(item -> {
				try {
					consumer.accept(item);
					taskResultDP.addSussNum();
					return;
				} catch (SmyBizException e) {
					XxlJobHelper.log(ERROR_ENUM_APPEND_LOG_PATTERN, e.getErrorEnum(), e.getMessage());
				} catch (Throwable e) {
					XxlJobHelper.log(e);
				}
				XxlJobHelper.log(String.valueOf(item));
				taskResultDP.addFailNum();
			});
			return XxlJobHelper.handleSuccess(CommConstant.TASK_SUCC_MSG);
		} catch (SmyBizException e) {
			XxlJobHelper.log(ERROR_ENUM_APPEND_LOG_PATTERN, e.getErrorEnum(), e.getMessage());
		} catch (Throwable e) {
			XxlJobHelper.log(e);
		} finally {
			XxlJobHelper.log(String.valueOf(taskResultDP));
		}
		return XxlJobHelper.handleFail(CommConstant.TASK_FAIL_MSG);
	}
}

package com.luman.smy.common.feature.xxl;

import com.luman.smy.common.constant.CommConstant;
import com.luman.smy.common.constant.MonitorConstant;
import com.luman.smy.common.enums.BaseEnum;
import com.luman.smy.common.exception.BizException;
import com.luman.smy.common.model.TaskResultDP;
import com.luman.smy.common.util.LoggerUtil;
import com.xxl.job.core.context.XxlJobHelper;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j(topic = MonitorConstant.TASK_MONITOR_LOGGER)
public class TaskTemplate {

	/**
	 * 任务处理
	 *
	 * @param consumer 消费者
	 * @param taskEnum 任务枚举
	 * @param supplier 供应商
	 * @return boolean
	 */
	public <T> boolean taskHandle(BaseEnum taskEnum, Supplier<Collection<T>> supplier, Consumer<T> consumer) {
		TaskResultDP taskResultDP = TaskResultDP.init(taskEnum);
		try {
			Collection<T> objects = supplier.get();
			objects.forEach(item -> {
				try {
					consumer.accept(item);
					taskResultDP.addSussNum();
					return;
				} catch (BizException e) {
					XxlJobHelper.log("errorEnum={}, subMessage={}", e.getErrorEnum(), e.getSubMessage());
					LoggerUtil.info(log, e);
				} catch (Throwable e) {
					XxlJobHelper.log(e);
					LoggerUtil.error(log, e);
				}
				LoggerUtil.info(log, item);
				XxlJobHelper.log(item.toString());
				taskResultDP.addFailNum();
			});
			return XxlJobHelper.handleSuccess(CommConstant.TASK_SUCC_MSG);
		} catch (BizException e) {
			XxlJobHelper.log("errorEnum={}, subMessage={}", e.getErrorEnum(), e.getSubMessage());
			LoggerUtil.info(log, e);
		} catch (Throwable e) {
			XxlJobHelper.log(e);
			LoggerUtil.error(log, e);
		} finally {
			XxlJobHelper.log(taskResultDP.toString());
			LoggerUtil.info(log, taskResultDP);
		}
		return XxlJobHelper.handleFail(CommConstant.TASK_FAIL_MSG);
	}
}

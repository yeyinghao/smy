package com.luman.smy.task.template;

import com.luman.smy.common.constant.CommConstant;
import com.luman.smy.common.constant.LoggerConstant;
import com.luman.smy.common.enums.BaseEnum;
import com.luman.smy.common.exception.BizException;
import com.luman.smy.common.model.TaskResultDP;
import com.luman.smy.common.util.LoggerUtil;
import com.luman.smy.common.util.TraceIdUtil;
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
@Slf4j(topic = LoggerConstant.TASK_MONITOR_LOGGER)
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
		TaskResultDP taskResultDP = TaskResultDP.init(taskEnum);
		try {
			printLog();
			Collection<T> objects = supplier.get();
			objects.forEach(item -> {
				try {
					consumer.accept(item);
					taskResultDP.addSussNum();
					return;
				} catch (BizException e) {
					printLog(e);
				} catch (Throwable e) {
					printLog(e);
				}
				printLog(item);
				taskResultDP.addFailNum();
			});
			return XxlJobHelper.handleSuccess(CommConstant.TASK_SUCC_MSG);
		} catch (BizException e) {
			printLog(e);
		} catch (Throwable e) {
			printLog(e);
		} finally {
			printLog(taskResultDP);
		}
		return XxlJobHelper.handleFail(CommConstant.TASK_FAIL_MSG);
	}

	/**
	 * 打印日志
	 *
	 * @param item 项
	 */
	private static <T> void printLog(T item) {
		LoggerUtil.info(log, item);
		XxlJobHelper.log(String.valueOf(item));
	}

	/**
	 * 打印日志
	 */
	private static void printLog() {
		XxlJobHelper.log(TRANCE_ID_APPEND_LOG_PATTERN, TraceIdUtil.getThreadTraceId());
		LoggerUtil.info(log, "task start");
	}

	/**
	 * 打印日志
	 *
	 * @param e e
	 */
	private static void printLog(BizException e) {
		LoggerUtil.info(log, e);
		XxlJobHelper.log(ERROR_ENUM_APPEND_LOG_PATTERN, e.getErrorEnum(), e.getSubMessage());
	}

	/**
	 * 打印日志
	 *
	 * @param e e
	 */
	private static void printLog(Throwable e) {
		LoggerUtil.error(log, e);
		XxlJobHelper.log(e);
	}
}

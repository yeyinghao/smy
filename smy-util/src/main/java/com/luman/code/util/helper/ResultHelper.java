package com.luman.code.util.helper;

import com.luman.code.util.constant.CommConstant;
import com.luman.code.util.enums.CommErrorEnum;
import com.luman.code.util.enums.ErrorEnum;
import com.luman.code.util.util.TraceIdUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Arrays;
import java.util.Objects;

/**
 * 结果辅助
 *
 * @author yeyinghao
 * @date 2024/03/29
 */
@Data
@NoArgsConstructor
@ToString
public class ResultHelper<T> {

	/**
	 * http响应状态
	 */
	private Integer code;

	/**
	 * 是否处理成功
	 */
	private Boolean success;

	/**
	 * 请求响应码
	 */
	private String resCode;

	/**
	 * 请求响应描述
	 */
	private String resMsg;

	/**
	 * 请求id
	 */
	private String traceId = TraceIdUtil.getThreadTraceId();

	/**
	 * 数据
	 */
	private T data;

	/**
	 * 成功
	 *
	 * @param data 数据
	 * @return {@link ResultHelper}<{@link T}>
	 */
	public static <T> ResultHelper<T> of(T data) {
		ResultHelper<T> resultHelper = new ResultHelper<>();
		resultHelper.code = CommErrorEnum.SUCCESS.getCode();
		resultHelper.resCode = CommErrorEnum.SUCCESS.name();
		resultHelper.resMsg = CommErrorEnum.SUCCESS.getDescription();
		resultHelper.data = data;
		resultHelper.success = Boolean.TRUE;
		return resultHelper;
	}

	/**
	 * 成功
	 *
	 * @return {@link ResultHelper}<{@link Boolean}>
	 */
	public static ResultHelper<Boolean> success() {
		return of(Boolean.TRUE);
	}


	/**
	 * 失败
	 *
	 * @param errorEnum 错误枚举
	 * @param subMsg    子的错误消息
	 * @return {@link ResultHelper}<{@link T}>
	 */
	public static <T> ResultHelper<T> fail(ErrorEnum errorEnum, String... subMsg) {
		ResultHelper<T> resultHelper = new ResultHelper<>();
		resultHelper.code = errorEnum.getCode();
		resultHelper.resCode = errorEnum.name();
		StringBuilder msg = new StringBuilder(errorEnum.getDescription());
		if (Objects.nonNull(subMsg)) {
			Arrays.asList(subMsg).forEach(item -> {
				msg.append(CommConstant.DELIMITER).append(item);
			});
		}
		resultHelper.resMsg = msg.toString();
		resultHelper.success = Boolean.FALSE;
		return resultHelper;
	}
}

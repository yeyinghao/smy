package com.luman.smy.infra.common.helper;

import cn.hutool.core.util.StrUtil;
import com.luman.smy.client.dto.ListModel;
import com.luman.smy.client.dto.PageModel;
import com.luman.smy.client.dto.Response;
import com.luman.smy.infra.common.constant.CommConstant;
import com.luman.smy.infra.common.enums.CommErrorEnum;
import com.luman.smy.infra.common.enums.ErrorEnum;
import com.luman.smy.infra.common.util.TraceIdUtil;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RHelper {

	/**
	 * 成功
	 *
	 * @return {@link Response }
	 */
	public static <T> Response<T> success(T data) {
		Response<T> response = new Response<>();
		response.setCode(CommErrorEnum.SUCCESS.getCode());
		response.setSuccess(Boolean.TRUE);
		response.setData(data);
		response.setTraceId(TraceIdUtil.getThreadTraceId());
		return response;
	}

	public static <T> Response<ListModel<T>> success(ListModel<T> data) {
		Response<ListModel<T>> response = new Response<>();
		response.setCode(CommErrorEnum.SUCCESS.getCode());
		response.setSuccess(Boolean.TRUE);
		response.setData(data);
		response.setTraceId(TraceIdUtil.getThreadTraceId());
		return response;
	}

	public static <T> Response<PageModel<T>> success(PageModel<T> data) {
		Response<PageModel<T>> response = new Response<>();
		response.setCode(CommErrorEnum.SUCCESS.getCode());
		response.setSuccess(Boolean.TRUE);
		response.setData(data);
		response.setTraceId(TraceIdUtil.getThreadTraceId());
		return response;
	}

	/**
	 * 失败
	 *
	 * @param errorEnum 错误枚举
	 * @param message   子的错误消息
	 * @return {@link Response }
	 */
	public static <T> Response<T> fail(ErrorEnum errorEnum, String message) {
		Response<T> response = new Response<>();
		response.setCode(errorEnum.getCode());
		response.setSuccess(Boolean.FALSE);
		StringBuilder msg = new StringBuilder(errorEnum.getDescription());
		if (StrUtil.isNotBlank(message)) {
			msg.append(CommConstant.DELIMITER).append(message);
		}
		response.setErrCode(errorEnum.name());
		response.setErrMessage(msg.toString());
		response.setTraceId(TraceIdUtil.getThreadTraceId());
		return response;
	}

	/**
	 * 失败
	 *
	 * @param errorEnum 错误枚举
	 * @return {@link Response }
	 */
	public static <T> Response<T> fail(ErrorEnum errorEnum) {
		Response<T> response = new Response<>();
		response.setCode(errorEnum.getCode());
		response.setSuccess(Boolean.FALSE);
		response.setErrCode(errorEnum.name());
		response.setErrMessage(errorEnum.getDescription());
		response.setTraceId(TraceIdUtil.getThreadTraceId());
		return response;
	}

}

package com.luman.smy.infra.common.response;

import cn.hutool.core.util.StrUtil;
import com.alibaba.cola.dto.Response;
import com.luman.smy.infra.common.constant.CommConstant;
import com.luman.smy.infra.common.enums.CommErrorEnum;
import com.luman.smy.infra.common.enums.ErrorEnum;
import com.luman.smy.infra.common.util.TraceIdUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 结果辅助
 *
 * @author yeyinghao
 * @date 2024/03/29
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@ToString
public class SmyResponse extends Response {

	/**
	 * http响应状态
	 */
	private Integer code;

	/**
	 * 请求id
	 */
	private String traceId = TraceIdUtil.getThreadTraceId();

	/**
	 * 成功
	 *
	 * @return {@link SmyResponse }
	 */
	public static SmyResponse buildSuccess() {
		SmyResponse resultHelper = new SmyResponse();
		resultHelper.setCode(CommErrorEnum.SUCCESS.getCode());
		resultHelper.setSuccess(Boolean.TRUE);
		return resultHelper;
	}

	/**
	 * 失败
	 *
	 * @param errorEnum 错误枚举
	 * @param message   子的错误消息
	 * @return {@link SmyResponse }
	 */
	public static SmyResponse buildFailure(ErrorEnum errorEnum, String message) {
		SmyResponse resultHelper = new SmyResponse();
		resultHelper.setCode(errorEnum.getCode());
		resultHelper.setSuccess(Boolean.FALSE);
		StringBuilder msg = new StringBuilder(errorEnum.getDescription());
		if (StrUtil.isNotBlank(message)) {
			msg.append(CommConstant.DELIMITER).append(message);
		}
		resultHelper.setErrCode(errorEnum.name());
		resultHelper.setErrMessage(msg.toString());
		return resultHelper;
	}

	/**
	 * 失败
	 *
	 * @param errorEnum 错误枚举
	 * @return {@link SmyResponse }
	 */
	public static SmyResponse buildFailure(ErrorEnum errorEnum) {
		SmyResponse resultHelper = new SmyResponse();
		resultHelper.setCode(errorEnum.getCode());
		resultHelper.setSuccess(Boolean.FALSE);
		resultHelper.setErrCode(errorEnum.name());
		resultHelper.setErrMessage(errorEnum.getDescription());
		return resultHelper;
	}
}

package com.luman.smy.common.exception;

import cn.hutool.core.util.StrUtil;
import com.luman.smy.common.constant.CommConstant;
import com.luman.smy.common.constant.HttpConstant;
import com.luman.smy.common.enums.CommErrorEnum;
import com.luman.smy.common.enums.ErrorEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 业务异常
 *
 * @author yeyinghao
 * @date 2023/08/01
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public class BizException extends RuntimeException {

	/**
	 * http状态
	 */
	private static final List<Integer> HTTP_STATUSES = Arrays.asList(HttpConstant.INTERNAL_SERVER_ERROR, HttpConstant.SERVICE_UNAVAILABLE);

	/**
	 * 基本枚举
	 */
	private final ErrorEnum errorEnum;

	/**
	 * 异常描述
	 */
	private String message;

	public BizException() {
		super(CommErrorEnum.BIZ_ERROR.getDescription());
		this.errorEnum = CommErrorEnum.BIZ_ERROR;
	}

	public BizException(Throwable ex) {
		super(ex);
		this.errorEnum = CommErrorEnum.SYSTEM_ERROR;
	}

	public BizException(String message) {
		super(message);
		this.errorEnum = CommErrorEnum.BIZ_ERROR;
		this.message = message;
	}

	/**
	 * 业务异常
	 *
	 * @param errorEnum 基本枚举
	 * @param message   子消息
	 */
	public BizException(ErrorEnum errorEnum, String message) {
		super(errorEnum.getDescription());
		this.errorEnum = errorEnum;
		this.message = message;
	}

	public BizException(ErrorEnum errorEnum, String... message) {
		super(errorEnum.getDescription());
		this.errorEnum = errorEnum;
		this.message = getMessage(message);
	}

	private static String getMessage(String... message) {
		if (Objects.nonNull(message)) {
			StringBuilder msg = new StringBuilder();
			Arrays.asList(message).forEach(item -> {
				msg.append(CommConstant.DELIMITER).append(item);
			});
			return msg.toString();
		}
		return null;
	}

	public BizException(ErrorEnum errorEnum, Throwable ex) {
		super(errorEnum.getDescription(), ex);
		this.errorEnum = errorEnum;
	}

	public BizException(String messae, Throwable ex) {
		super(messae, ex);
		this.errorEnum = CommErrorEnum.SYSTEM_ERROR;
		this.message = messae;
	}

	public BizException(ErrorEnum errorEnum, String message, Throwable ex) {
		super(message, ex);
		this.errorEnum = errorEnum;
		this.message = message;
	}

	public String getResult() {
		if (this.errorEnum == null) {
			return CommConstant.Y;
		}
		return HTTP_STATUSES.contains(this.errorEnum.getCode()) ? CommConstant.Y : CommConstant.N;
	}

	/**
	 * 消息
	 *
	 * @return {@link String }
	 */
	public String message() {

		if (this.errorEnum != null && StrUtil.isNotBlank(this.message)) {
			return this.errorEnum.getDescription() + CommConstant.COLON + this.message;
		}

		if (this.errorEnum != null && StrUtil.isBlank(this.message)) {
			return this.errorEnum.getDescription();
		}

		if (this.errorEnum == null && StrUtil.isNotBlank(this.message)) {
			return this.message;
		}

		return CommErrorEnum.BIZ_ERROR.getDescription();
	}
}

package com.luman.smy.infra.common.enums;

import com.luman.smy.infra.common.constant.HttpConstant;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * 通用错误枚举
 *
 * @author yeyinghao
 * @date 2024/04/04
 */
@Getter
@RequiredArgsConstructor
@ToString
public enum CommErrorEnum implements ErrorEnum {

	/**
	 * 成功
	 */
	SUCCESS(HttpConstant.OK, "成功"),

	/**
	 * 参数非法
	 */
	ILLEGAL_PARAMETER(HttpConstant.BAD_REQUEST, "参数非法"),

	/**
	 * 业务异常
	 */
	BIZ_ERROR(HttpConstant.BAD_REQUEST, "业务异常"),

	/**
	 * 未授权
	 */
	FORBIDDEN(HttpConstant.FORBIDDEN, "未授权"),

	/**
	 * 资源未找到
	 */
	NOT_FOUND(HttpConstant.NOT_FOUND, "资源未找到"),

	/**
	 * 服务内部错误
	 */
	SYSTEM_ERROR(HttpConstant.INTERNAL_SERVER_ERROR, "系统错误"),

	/**
	 * 服务不可用
	 */
	SERVICE_UNAVAILABLE(HttpConstant.SERVICE_UNAVAILABLE, "服务不可用"),

	;

	/**
	 * 响应码
	 */
	private final Integer code;

	/**
	 * 响应业务码的描述
	 */
	private final String description;
}

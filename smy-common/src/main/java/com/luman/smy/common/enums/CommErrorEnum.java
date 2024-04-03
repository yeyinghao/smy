package com.luman.smy.common.enums;

import com.luman.smy.common.constant.HttpConstant;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * @author yeyinghao
 * @version 1.0.0
 * @description: 通用结果异常枚举
 * <p>200 成功 400 参数错误 403 无权限 500 服务器异常 501 数据获取失败
 * @date: 2022/6/5 13:52
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
	 * 业务处理失败
	 */
	BIZ_PROCESS_FAIL(HttpConstant.BAD_REQUEST, "业务处理失败"),

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

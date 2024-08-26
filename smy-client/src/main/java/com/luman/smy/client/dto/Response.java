package com.luman.smy.client.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serial;

/**
 * 响应
 *
 * @author yeyinghao
 * @date 2024/03/29
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@ToString
public class Response<T> extends DTO {

	/**
	 * 串口版本uid
	 */
	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * http响应状态
	 */
	private Integer code;

	/**
	 * 成功
	 */
	private boolean success;

	/**
	 * 错误代码
	 */
	private String errCode;

	/**
	 * 错误消息
	 */
	private String errMessage;

	/**
	 * 数据
	 */
	private T data;

	/**
	 * 请求id
	 */
	private String traceId;
}

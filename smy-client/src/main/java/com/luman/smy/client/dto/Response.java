package com.luman.smy.client.dto;

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
public class Response<T> extends DTO {

	/**
	 * http响应状态
	 */
	private Integer code;

	private boolean success;

	private String errCode;

	private String errMessage;

	private T data;

	/**
	 * 请求id
	 */
	private String traceId;
}

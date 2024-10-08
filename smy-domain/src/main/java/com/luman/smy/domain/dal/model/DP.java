package com.luman.smy.domain.dal.model;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * dto
 *
 * @author yeyinghao
 * @date 2024/08/19
 */
@Data
@ToString
public class DP {

	/**
	 * id
	 */
	private Long id;

	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;

	/**
	 * 更新时间
	 */
	private LocalDateTime updateTime;

	/**
	 * 扩展信息-map
	 */
	private String extInfo;

	/**
	 * 状态
	 */
	private Boolean status;

}

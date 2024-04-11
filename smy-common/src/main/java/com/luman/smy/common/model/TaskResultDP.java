/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2024.3
 */

package com.luman.smy.common.model;

import com.luman.smy.common.enums.BaseEnum;
import com.luman.smy.common.base.DP;
import lombok.*;

import java.util.Map;

/**
 * 任务结果vo
 *
 * @author yeyinghao
 * @date 2024/03/06
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskResultDP extends DP {

	/**
	 * 任务枚举
	 */
	private BaseEnum taskEnum;

	/**
	 * 总num
	 */
	private Integer totalNum;

	/**
	 * 成功num
	 */
	private Integer succNum;

	/**
	 * 失败num
	 */
	private Integer failNum;

	/**
	 * ext信息
	 */
	private Map<String, Object> extInfo;

	/**
	 * 成功数加1
	 */
	public void addSussNum() {
		this.succNum += 1;
		this.totalNum += 1;
	}

	/**
	 * 失败数加1
	 */
	public void addFailNum() {
		this.failNum += 1;
		this.totalNum += 1;
	}

	/**
	 * Init任务结果dp
	 *
	 * @return {@link TaskResultDP}
	 */
	public static TaskResultDP init(BaseEnum taskEnum) {
		return TaskResultDP.builder().taskEnum(taskEnum).totalNum(0).succNum(0).failNum(0).build();
	}
}

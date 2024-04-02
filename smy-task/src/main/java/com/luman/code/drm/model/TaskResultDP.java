/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2024.3
 */

package com.luman.code.drm.model;

import com.luman.code.util.enums.BaseEnum;
import com.luman.code.util.model.base.DP;
import lombok.*;

import java.util.Map;
import java.util.Objects;

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
	}

	/**
	 * 失败数加1
	 */
	public void addFailNum() {
		this.failNum += 1;
	}

	/**
	 * 任务处理是否成功
	 * 总数和成功数相等则判定成功
	 *
	 * @return boolean
	 */
	public boolean isSucc() {
		return Objects.equals(this.succNum, this.totalNum);
	}

	/**
	 * Init任务结果dp
	 *
	 * @return {@link TaskResultDP}
	 */
	public static TaskResultDP initTaskResultDP(BaseEnum taskEnum) {
		return TaskResultDP.builder().taskEnum(taskEnum).totalNum(0).succNum(0).failNum(0).build();
	}
}

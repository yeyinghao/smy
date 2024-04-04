/*
 * QQ: 1113531030
 * WX: missyeyh
 * Phone: 17689397484
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2023.2
 */

package com.luman.smy.common.annotations.cal;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 缓存监控
 *
 * @author yeyinghao
 * @date 2024/04/04
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Cal {

	/**
	 * 名称
	 *
	 * @return {@link String}
	 */
	String name();

	/**
	 * 描述
	 *
	 * @return {@link String}
	 */
	String desc();
	
	/**
	 * 是否是get调用 统计命中率
	 *
	 * @return boolean
	 */
	boolean isGet();
}

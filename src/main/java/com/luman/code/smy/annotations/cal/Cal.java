/*
 * QQ: 1113531030
 * WX: missyeyh
 * Phone: 17689397484
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2023.2
 */

package com.luman.code.smy.annotations.cal;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yeyinghao
 * @version 1.0.0
 * @description:
 * @date: 2023/2/25 21:59
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Cal {

	/**
	 * 是否是get调用 统计命中率
	 *
	 * @return boolean
	 */
	boolean isGet();
}

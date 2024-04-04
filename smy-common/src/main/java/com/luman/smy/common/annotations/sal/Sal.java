/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2023.8
 */

package com.luman.smy.common.annotations.sal;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * sal
 *
 * @author yeyinghao
 * @date 2024/01/17
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Sal {

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

}

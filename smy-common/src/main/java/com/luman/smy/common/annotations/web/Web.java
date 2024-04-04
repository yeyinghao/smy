/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2023.12
 */

package com.luman.smy.common.annotations.web;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * web
 *
 * @author yeyinghao
 * @date 2024/01/17
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Web {

	/**
	 * 名称, 支持spel
	 *
	 * @return {@link String}
	 */
	String name();

	/**
	 * 描述, 支持spel
	 *
	 * @return {@link String}
	 */
	String desc();

}

/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2024.3
 */

package com.luman.smy.common.annotations.secret;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 加解密注解
 *
 * @author yeyinghao
 * @date 2024/03/20
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Secret {

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

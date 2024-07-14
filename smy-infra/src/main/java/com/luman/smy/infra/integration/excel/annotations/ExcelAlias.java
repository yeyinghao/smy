/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2024.3
 */

package com.luman.smy.integration.excel.annotations;


import java.lang.annotation.*;

/**
 * excel别名
 *
 * @author yeyinghao
 * @date 2024/04/04
 */
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelAlias {

	/**
	 * value
	 *
	 * @return {@link String}
	 */
	String value() default "";

	/**
	 * 数据类型
	 *
	 * @return {@link DataTypeEnum}
	 */
	DataTypeEnum dataType() default DataTypeEnum.STRING;

	/**
	 * 精度
	 *
	 * @return int
	 */
	int precision() default 2;

	/**
	 * 是否必填
	 *
	 * @return boolean
	 */
	boolean required() default false;

}

/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2024.3
 */

package com.luman.code.common.annotations.excel;


import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelAlias {

	String value() default "";

	DataTypeEnum dateType() default DataTypeEnum.STRING;

	int precision() default 2;

	boolean required() default false;

}

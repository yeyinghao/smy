/*
 * QQ: 1113531030 WX: missyeyh Phone: 17689397484 Copyright (c) Ye Yinghao 2022.1 - 2023.2
 */

package com.luman.smy.infra.common.enums;

import cn.hutool.core.util.StrUtil;

import java.util.Objects;

/**
 * 基础枚举
 *
 * @author yeyinghao
 * @date 2023/08/13
 */
public interface BaseEnum {

	/**
	 * 根据名称从枚举类型type中获取枚举对象
	 */
	static <T extends BaseEnum> T getEnumByNameOrDesc(Class<T> type, String str) {
		T[] enums = type.getEnumConstants();
		for (T em : enums) {
			if (StrUtil.equals(em.name(), str) || StrUtil.equals(em.getDescription(), str)) {
				return em;
			}
		}
		return null;
	}

	/**
	 * 获取名称
	 *
	 * @param baseEnum 基础枚举
	 * @return {@link String}
	 */
	static String getName(BaseEnum baseEnum) {
		return Objects.isNull(baseEnum) ? null : baseEnum.name();
	}

	/**
	 * 获取描述
	 *
	 * @return {@link String}
	 */
	String getDescription();

	/**
	 * 名字
	 *
	 * @return {@link String}
	 */
	String name();
}

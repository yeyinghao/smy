package com.luman.smy.infra.common.helper;

import com.luman.smy.client.dto.ListModel;

import java.util.List;

/**
 * 列表
 *
 * @author yeyinghao
 * @date 2024/08/19
 */
public class ListHelper {

	/**
	 * 构建
	 *
	 * @param list 列表
	 * @return {@link ListModel }<{@link R }>
	 */
	public static <R> ListModel<R> build(List<R> list) {
		return new ListModel<>(list);
	}
}

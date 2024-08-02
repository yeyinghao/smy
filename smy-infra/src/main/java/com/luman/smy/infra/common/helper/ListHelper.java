package com.luman.smy.infra.common.helper;

import com.luman.smy.client.dto.ListModel;

import java.util.List;

public class ListHelper {

	public static <R> ListModel<R> build(List<R> list) {
		return new ListModel<>(list);
	}
}

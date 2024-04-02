/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2023.12
 */

package com.luman.code.smy.util;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.luman.code.smy.constant.CommConstant;

import java.util.Map;

public class CommUtil {

	/**
	 * 获得Cost时间
	 *
	 * @param startTime 开始时间
	 * @return {@link String}
	 */
	public static String getCostTime(long startTime) {
		return (System.currentTimeMillis() - startTime) + CommConstant.COST_TIME_MILL_SECOND;
	}

	/**
	 * bool转string
	 *
	 * @param res res
	 * @return String
	 */
	public static String getStringByBoolean(boolean res) {
		return res ? CommConstant.Y : CommConstant.N;
	}

	/**
	 * 获取map通过ext信息
	 *
	 * @param extInfo ext信息
	 * @return {@link Map}<{@link String}, {@link Object}>
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getMapByExtInfo(String extInfo) {
		return StrUtil.isBlank(extInfo) ? null : JSONUtil.toBean(extInfo, Map.class);
	}

	/**
	 * 获取ext信息通过地图
	 *
	 * @param extInfoMap 文本信息图
	 * @return {@link String}
	 */
	public static String getExtInfoByMap(Map<String, Object> extInfoMap) {
		return MapUtil.isEmpty(extInfoMap) ? null : JSONUtil.toJsonStr(extInfoMap);
	}
}

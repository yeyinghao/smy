/*
 * Copyright (c) Ye Yinghao
 * 2022.1 - 2024.1
 */

package com.luman.smy.common.annotations.cal;

import com.luman.smy.common.constant.BaseConstant;

/**
 * 基础常数
 *
 * @author yeyinghao
 * @date 2024/04/04
 */
public interface CalConstant extends BaseConstant {

	interface GET {
		String name = "GET";
		String desc = "获取对象";
	}

	interface SAVE {
		String name = "SAVE";
		String desc = "保存对象";
	}

	interface SAVE_EXPIRE {
		String name = "SAVE_EXPIRE";
		String desc = "保存对象,有过期时间";
	}

	interface SAVE_IF_ABSENT {
		String name = "SAVE_IF_ABSENT";
		String desc = "保存对象,如果存在则终止";
	}

	interface SAVE_IF_ABSENT_EXPIRE {
		String name = "SAVE_IF_AMBSENT_EXPIRE";
		String desc = "保存对象,如果存在则终止,有过期时间";
	}

	interface REMOVE {
		String name = "REMOVE";
		String desc = "删除对象";
	}

	interface EXISTS {
		String name = "EXISTS";
		String desc = "判断对象是否存在";
	}

	interface GET_REDIS_LIST {
		String name = "GET_REDIS_LIST";
		String desc = "获取List对象";
	}


	interface GET_REDIS_MAP_CACHE {
		String name = "GET_REDIS_MAP_CACHE";
		String desc = "获取MapCache对象";
	}


	interface GET_REDIS_MAP {
		String name = "GET_REDIS_MAP";
		String desc = "获取Map对象";
	}

	interface GET_REDIS_SET {
		String name = "GET_REDIS_SET";
		String desc = "获取Set对象";
	}


	interface GET_REDIS_SCORED_SORTED_SET {
		String name = "GET_REDIS_SCORED_SORTED_SET";
		String desc = "获取SortedSet对象";
	}

	interface GET_REDIS_LOCK {
		String name = "GET_REDIS_LOCK";
		String desc = "获取Lock对象";
	}

	interface GET_KEY_EXPIRED {
		String name = "GET_KEY_EXPIRED";
		String desc = "获取对象剩余过期时间";
	}

}

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
		String NAME = "GET";
		String DESC = "获取对象";
	}

	interface SAVE {
		String NAME = "SAVE";
		String DESC = "保存对象";
	}

	interface SAVE_EXPIRE {
		String NAME = "SAVE_EXPIRE";
		String DESC = "保存对象,有过期时间";
	}

	interface SAVE_IF_ABSENT {
		String NAME = "SAVE_IF_ABSENT";
		String DESC = "保存对象,如果存在则终止";
	}

	interface SAVE_IF_ABSENT_EXPIRE {
		String NAME = "SAVE_IF_AMBSENT_EXPIRE";
		String DESC = "保存对象,如果存在则终止,有过期时间";
	}

	interface REMOVE {
		String NAME = "REMOVE";
		String DESC = "删除对象";
	}

	interface EXISTS {
		String NAME = "EXISTS";
		String DESC = "判断对象是否存在";
	}

	interface GET_REDIS_LIST {
		String NAME = "GET_REDIS_LIST";
		String DESC = "获取List对象";
	}

	interface GET_REDIS_MAP_CACHE {
		String NAME = "GET_REDIS_MAP_CACHE";
		String DESC = "获取MapCache对象";
	}

	interface GET_REDIS_MAP {
		String NAME = "GET_REDIS_MAP";
		String DESC = "获取Map对象";
	}

	interface GET_REDIS_SET {
		String NAME = "GET_REDIS_SET";
		String DESC = "获取Set对象";
	}

	interface GET_REDIS_SCORED_SORTED_SET {
		String NAME = "GET_REDIS_SCORED_SORTED_SET";
		String DESC = "获取SortedSet对象";
	}

	interface GET_REDIS_LOCK {
		String NAME = "GET_REDIS_LOCK";
		String DESC = "获取Lock对象";
	}

	interface GET_KEY_EXPIRED {
		String NAME = "GET_KEY_EXPIRED";
		String DESC = "获取对象剩余过期时间";
	}

}

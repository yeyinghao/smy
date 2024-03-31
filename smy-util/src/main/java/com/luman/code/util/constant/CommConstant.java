package com.luman.code.util.constant;

/**
 * 项目常量类
 */
public interface CommConstant extends BaseConstant {

	/**
	 * 请求id
	 */
	String TRACE_ID = "traceId";

	/**
	 * 冒号
	 */
	String COLON = ":";

	/**
	 * 分号
	 */
	String SEMICOLON = ";";

	/**
	 * 短横
	 */
	String SHORT_HORIZONTAL = "-";

	/**
	 * 下划线
	 */
	String UNDERLINE = "_";

	/**
	 * 分隔符
	 */
	String DELIMITER = ",";

	/**
	 * Y
	 */
	String Y = "Y";

	/**
	 * N
	 */
	String N = "N";

	/**
	 * = 符号
	 */
	String EQUAL_SIGN = "=";

	/**
	 * & 符号
	 */
	String ESPERLUETTE = "&";

	/**
	 * 毫秒
	 */
	String COST_TIME_MILL_SECOND = "ms";

	/**
	 * 系统字符编码
	 */
	String CHARSET = "UTF-8";

	/**
	 * 线程池Executor默认
	 */
	String THREAD_POOL_EXECUTOR_DEFAULT = "threadPoolExecutor";

	/**
	 * 跨域最大时间 3600 * 24
	 */
	long CORS_MAX_AGE_SECOND = 86400;
}

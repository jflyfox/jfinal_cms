package com.jflyfox.api.constant;

public class ApiConstant {

	/**
	 * 成功
	 */
	public static final int CODE_SUCCESS = 0;
	/**
	 * 失败
	 */
	public static final int CODE_FAIL = -1;
	/**
	 * 维护
	 */
	public static final int CODE_SERVER_MAINTAIN = -9;
	/**
	 * 版本号错误
	 */
	public static final int CODE_VERSION_ERROR = -101;
	/**
	 * 方法调用错误
	 */
	public static final int CODE_METHOD_ERROR = -103;
	/**
	 * 传递参数异常
	 */
	public static final int CODE_PARAM_ERROR = -102;
	/**
	 * IP黑名单
	 */
	public static final int CODE_IP_BLACK = -201;
	
	public static final String MSG_SUCCESS = "success";
	public static final String MSG_FAIL = "fail";
	public static final String MSG_VERSION_ERROR = "版本号错误";
	public static final String MSG_METHOD_ERROR = "方法调用错误";
	public static final String MSG_PARAM_ERROR = "传递参数异常";
	public static final String MSG_IP_BLACK  = "传递参数异常";
	public static final String MSG_SERVER_MAINTAIN  = "API服务维护中";
}

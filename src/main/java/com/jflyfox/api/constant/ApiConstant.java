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
	 * 调用方法不存在
	 */
	public static final int CODE_METHOD_ERROR = -103;
	/**
	 * 调用方法异常
	 */
	public static final int CODE_METHOD_HANDLER_ERROR = -103;
	/**
	 * 传递参数异常
	 */
	public static final int CODE_PARAM_ERROR = -102;
	/**
	 * IP黑名单
	 */
	public static final int CODE_IP_BLACK = -201;

	/**
	 * 登陆验证异常
	 */
	public static final int CODE_LOGIN_VALID_ERROR = -501;

	public static final String MSG_SUCCESS = "success";
	public static final String MSG_FAIL = "fail";
	public static final String MSG_VERSION_ERROR = "版本号错误";
	public static final String MSG_METHOD_ERROR = "调用方法不存在";
	public static final String MSG_METHOD_HANDLER_ERROR = "调用方法异常";
	public static final String MSG_PARAM_ERROR = "传递参数异常";
	public static final String MSG_IP_BLACK = "IP黑名单拦截";
	public static final String MSG_SERVER_MAINTAIN = "API服务维护中";
	public static final String MSG_LOGIN_VALID_ERROR = "登陆验证失败";
}

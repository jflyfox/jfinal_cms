package com.jflyfox.api.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.jflyfox.api.constant.ApiConstant;
import com.jflyfox.api.form.ApiResp;
import com.jflyfox.api.form.ApiForm;
import com.jflyfox.api.service.IApiLogic;
import com.jflyfox.api.service.impl.ApiV100Logic;
import com.jflyfox.api.service.impl.ApiV101Logic;
import com.jflyfox.util.encrypt.Base64;
import com.jflyfox.util.encrypt.Md5Utils;

public class ApiUtils {

	private static final Map<String, IApiLogic> map = new HashMap<String, IApiLogic>();
	/**
	 * 调试日志
	 */
	public static boolean DEBUG = false;

	static {
		addApi("1.0.0", new ApiV100Logic());
		addApi("1.0.1", new ApiV101Logic());
	}

	public static void addApi(String version, IApiLogic apiLogic) {
		map.put(version, apiLogic);
	}

	public static IApiLogic getApiLogic(ApiForm form) {
		return map.get(form.getVersion());
	}

	/**
	 * api关闭resp
	 * 
	 * 2016年9月29日 上午11:44:38 flyfox 369191470@qq.com
	 * 
	 * @return
	 */
	public static ApiResp getServerMaintain(ApiForm form) {
		return new ApiResp(null).setCode(ApiConstant.CODE_SERVER_MAINTAIN).setMsg(ApiConstant.MSG_SERVER_MAINTAIN);
	}

	/**
	 * 版本错误返回resp
	 * 
	 * 2016年9月29日 上午11:44:38 flyfox 369191470@qq.com
	 * 
	 * @return
	 */
	public static ApiResp getVersionErrorResp(ApiForm form) {
		return new ApiResp(null).setCode(ApiConstant.CODE_VERSION_ERROR).setMsg(ApiConstant.MSG_VERSION_ERROR);
	}

	/**
	 * ip黑名单返回resp
	 * 
	 * 2016年9月29日 上午11:44:38 flyfox 369191470@qq.com
	 * 
	 * @return
	 */
	public static ApiResp getIpBlackResp(ApiForm form) {
		return new ApiResp(null).setCode(ApiConstant.CODE_IP_BLACK).setMsg(ApiConstant.MSG_IP_BLACK);
	}

	/**
	 * 调用方法不存在resp
	 * 
	 * 2016年9月29日 上午11:44:38 flyfox 369191470@qq.com
	 * 
	 * @return
	 */
	public static ApiResp getMethodError(ApiForm form) {
		return new ApiResp(form).setCode(ApiConstant.CODE_METHOD_ERROR).setMsg(ApiConstant.MSG_METHOD_ERROR);
	}

	/**
	 * 调用方法异常resp
	 * 
	 * 2016年9月29日 上午11:44:38 flyfox 369191470@qq.com
	 * 
	 * @return
	 */
	public static ApiResp getMethodHandlerError(ApiForm form) {
		return new ApiResp(form).setCode(ApiConstant.CODE_METHOD_HANDLER_ERROR).setMsg(
				ApiConstant.MSG_METHOD_HANDLER_ERROR);
	}

	/**
	 * 解码
	 * 
	 * 2017年3月15日 下午1:49:09
	 * flyfox 330627517@qq.com
	 * @param params
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String decode(String params) throws UnsupportedEncodingException {
		params = URLDecoder.decode(params, "utf-8");
		params = new String(Base64.decodeBase64(params.getBytes("utf-8")), "utf-8");
		return params;
	}

	/**
	 * 编码
	 * 
	 * 2017年3月15日 下午1:49:09
	 * flyfox 330627517@qq.com
	 * @param params
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String encode(String params) throws UnsupportedEncodingException {
		params = new String(Base64.encodeBase64(params.getBytes("utf-8")), "utf-8");
		params = URLEncoder.encode(params, "utf-8");
		return params;
	}
	
	/**
	 * 获取验证sign
	 * 
	 * 2017年3月15日 下午3:14:27
	 * flyfox 330627517@qq.com
	 * @param paramMaps
	 * @param key
	 * @return
	 */
	public static String getSign(TreeMap<String, String> paramMaps, String key) {
		// 原始请求串
		StringBuffer src = new StringBuffer();
		for (Map.Entry<String, String> entry : paramMaps.entrySet()) {
			src.append(entry.getKey() + "=" + entry.getValue() + "&");
		}
		// 待加密串
		src.append("key=").append(key == null ? "" : key);
		System.out.println("#####"+src.toString());
		// 生成签名
		String serverSign = new Md5Utils().getMD5(src.toString()).toUpperCase();
		return serverSign;
	}
}

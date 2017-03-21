package com.jflyfox.api.service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.jfinal.log.Log;
import com.jflyfox.api.constant.ApiConstant;
import com.jflyfox.api.form.ApiResp;
import com.jflyfox.api.form.ApiForm;
import com.jflyfox.api.util.ApiUtils;
import com.jflyfox.jfinal.base.BaseService;
import com.jflyfox.system.config.ConfigCache;
import com.jflyfox.util.ReflectionUtils;

/**
 * API实现入口
 * 
 * 2016年9月29日 上午11:44:56 flyfox 369191470@qq.com
 */
public class ApiService extends BaseService {

	private final static Log log = Log.getLog(ApiService.class);
	private static List<String> methodList;

	static {
		methodList = methodList();
	}

	public static List<String> methodList() {
		List<String> methodList = new ArrayList<String>();
		Method[] methods = IApiLogic.class.getMethods();
		for (int i = 0; i < methods.length; i++) {
			Class<?>[] params = methods[i].getParameterTypes();
			if (params.length == 1 && (params[0] == ApiForm.class)) {
				methodList.add(methods[i].getName());
			}
		}
		return methodList;
	}

	public IApiLogic getApiLogic(ApiForm form) {
		IApiLogic apiLogic = ApiUtils.getApiLogic(form);
		return apiLogic;
	}

	/**
	 * 接口方法入口
	 * 
	 * 2016年10月3日 下午1:37:58 flyfox 369191470@qq.com
	 * 
	 * @param form
	 * @return
	 */
	public ApiResp action(ApiForm form) {
		try {
			//
			if (methodList.contains(form.getMethod())) {

				// 登陆验证标识
				boolean validFlag = ConfigCache.getValueToBoolean("API.LOGIN.VALID");
				if (validFlag) {
					// 先进行登陆验证。如果验证失败，直接返回错误
					ApiResp validResp = getApiLogic(form).valid(form);
					if (validResp.getCode() != ApiConstant.CODE_SUCCESS) {
						return validResp;
					}
				}

				// 调用接口方法，利用反射更简洁
				ApiResp apiResp = (ApiResp) ReflectionUtils.invokeMethod(getApiLogic(form), form.getMethod(), //
						new Class<?>[] { ApiForm.class }, new Object[] { form });
				return apiResp;
			}

			return ApiUtils.getMethodError(form);
		} catch (Exception e) {
			log.error("action handler error", e);
			return ApiUtils.getMethodHandlerError(form);
		}
	}

}

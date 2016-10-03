package com.jflyfox.api.service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.jflyfox.api.form.ApiResp;
import com.jflyfox.api.form.BaseApiForm;
import com.jflyfox.api.util.ApiUtils;
import com.jflyfox.jfinal.base.BaseService;
import com.jflyfox.util.ReflectionUtils;

/**
 * API实现入口
 * 
 * 2016年9月29日 上午11:44:56 flyfox 369191470@qq.com
 */
public class ApiService extends BaseService {

	private static List<String> methodList;

	static {
		methodList = methodList();
	}

	public static List<String> methodList() {
		List<String> methodList = new ArrayList<String>();
		Method[] methods = IApiLogic.class.getMethods();
		for (int i = 0; i < methods.length; i++) {
			Class<?>[] params = methods[i].getParameterTypes();
			if (params.length == 1 && (params[0] == BaseApiForm.class)) {
				methodList.add(methods[i].getName());
			}
		}
		return methodList;
	}

	public IApiLogic getApiLogic(BaseApiForm form) {
		return ApiUtils.getApiLogic(form.getVersion());
	}

	/**
	 * 接口方法入口
	 * 
	 * 2016年10月3日 下午1:37:58 flyfox 369191470@qq.com
	 * 
	 * @param form
	 * @return
	 */
	public ApiResp action(BaseApiForm form) {
		// 利用反射更简洁
		if (methodList.contains(form.getMethod())) {
			return (ApiResp)ReflectionUtils.invokeMethod(getApiLogic(form), form.getMethod(), //
					new Class<?>[] { BaseApiForm.class }, new Object[] { form });
		}

		// if ("folders".equals(form.getMethod())) {
		// return getApiLogic(form).folders(form);
		// } else if ("pageArticleSite".equals(form.getMethod())) {
		// return getApiLogic(form).pageArticleSite(form);
		// } else if ("pageArticle".equals(form.getMethod())) {
		// return getApiLogic(form).pageArticle(form);
		// } else if ("article".equals(form.getMethod())) {
		// return getApiLogic(form).article(form);
		// }
		return ApiUtils.getMethodError();
	}

}

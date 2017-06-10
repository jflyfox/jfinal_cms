package com.jflyfox.jfinal.component.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jflyfox.jfinal.base.BaseController;
import com.jflyfox.jfinal.base.BaseForm;

/**
 * 公共拦截器，处理一些通用行为
 * 
 * 2016年1月16日 下午4:44:46 flyfox 330627517@qq.com
 */
public class JflyfoxInterceptor implements Interceptor {

	public void intercept(Invocation ai) {

		Controller controller = ai.getController();

		// 设置公共属性
		if (controller instanceof BaseController) {
			BaseForm form = ((BaseController) controller).getModelByForm(BaseForm.class);
			controller.setAttr("form", form);
		}

		ai.invoke();
	}
}

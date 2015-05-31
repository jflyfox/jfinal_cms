package com.flyfox.component.interceptor;

import com.flyfox.component.util.JFlyFoxCache;
import com.flyfox.component.util.JFlyFoxUtils;
import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import com.jfinal.core.Controller;

/**
 * 用户认证拦截器
 * 
 * @author flyfox 2014-2-11
 */
public class CommonInterceptor implements Interceptor {


	public void intercept(ActionInvocation ai) {

		Controller controller = ai.getController();
		
		// 设置公共属性
		controller.setAttr(JFlyFoxUtils.TITLE_ATTR, JFlyFoxCache.getHeadTitle());

		ai.invoke();
	}
}

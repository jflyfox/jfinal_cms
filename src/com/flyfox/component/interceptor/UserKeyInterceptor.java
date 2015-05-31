package com.flyfox.component.interceptor;

import java.util.UUID;

import com.flyfox.component.util.JFlyFoxUtils;
import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import com.jfinal.core.Controller;

/**
 * 用户认证拦截器
 * 
 * @author flyfox 2014-2-11
 */
public class UserKeyInterceptor implements Interceptor {


	public void intercept(ActionInvocation ai) {

		Controller controller = ai.getController();
		
		// 如果没有，就设置一个
		Object key = controller.getSessionAttr(JFlyFoxUtils.USER_KEY);
		if (key==null) {
			controller.setSessionAttr(JFlyFoxUtils.USER_KEY, UUID.randomUUID().toString());
		}

		ai.invoke();
	}
}

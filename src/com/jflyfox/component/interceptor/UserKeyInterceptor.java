package com.jflyfox.component.interceptor;

import java.util.UUID;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jflyfox.component.util.JFlyFoxUtils;

/**
 * 用户认证拦截器
 * 
 * @author flyfox 2014-2-11
 */
public class UserKeyInterceptor implements Interceptor {


	public void intercept(Invocation ai) {

		Controller controller = ai.getController();
		
		// 如果没有，就设置一个
		Object key = controller.getSessionAttr(JFlyFoxUtils.USER_KEY);
		if (key==null) {
			controller.setSessionAttr(JFlyFoxUtils.USER_KEY, UUID.randomUUID().toString());
		}

		ai.invoke();
	}
}

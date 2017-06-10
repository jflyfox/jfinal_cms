/**
 * Copyright 2015-2025 FLY的狐狸(email:jflyfox@sina.com qq:369191470).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package com.jflyfox.jfinal.component.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jflyfox.jfinal.component.util.Attr;

/**
 * 设置用户session公共属性
 * 
 * 2014年8月9日 下午11:18:02
 * flyfox 330627517@qq.com
 */
public class SessionAttrInterceptor implements Interceptor {

	public void intercept(Invocation ai) {

		Controller controller = ai.getController();

		HttpServletRequest request = controller.getRequest();
		HttpSession session = request.getSession();
		Object obj = session.getAttribute(Attr.SESSION_IS_MOILE);
		if (obj == null) {
			boolean isMoile = HttpRequestDeviceUtils.isMobileDevice(request);
			session.setAttribute(Attr.SESSION_IS_MOILE, isMoile);
		}

		ai.invoke();
	}
}

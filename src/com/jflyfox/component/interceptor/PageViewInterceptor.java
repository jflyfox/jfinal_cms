package com.jflyfox.component.interceptor;

import javax.servlet.http.HttpServletRequest;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.jflyfox.modules.admin.pageview.PageViewCache;
import com.jflyfox.util.StrUtils;

/**
 * 用户PV统计拦截器
 * 
 * @author flyfox 2014-2-11
 */
public class PageViewInterceptor implements Interceptor {

	private final static Log log = Log.getLog(PageViewInterceptor.class);

	public void intercept(Invocation ai) {

		Controller controller = ai.getController();

		String ip = getIpAddr(controller.getRequest());
		if (StrUtils.isEmpty(ip)) {
			log.warn("PageViewInterceptor中数据为Null");
		} else {
			// 添加Page View
			PageViewCache.add(ip);
		}

		ai.invoke();
	}

	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}

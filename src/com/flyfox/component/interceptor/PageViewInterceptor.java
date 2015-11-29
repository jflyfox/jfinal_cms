package com.flyfox.component.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.flyfox.modules.pageview.PageViewCache;
import com.flyfox.util.StrUtils;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

/**
 * 用户PV统计拦截器
 * 
 * @author flyfox 2014-2-11
 */
public class PageViewInterceptor implements Interceptor {

	private final static Logger log = Logger.getLogger(PageViewInterceptor.class);

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

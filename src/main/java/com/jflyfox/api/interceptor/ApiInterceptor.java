package com.jflyfox.api.interceptor;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.jflyfox.api.form.ApiForm;
import com.jflyfox.api.util.ApiUtils;
import com.jflyfox.system.config.ConfigCache;
import com.jflyfox.util.IpUtils;
import com.jflyfox.util.StrUtils;

public class ApiInterceptor implements Interceptor {

	private final static Log log = Log.getLog(ApiInterceptor.class);

	public void intercept(Invocation ai) {

		long start = System.currentTimeMillis();
		Controller controller = ai.getController();
		String path = ai.getActionKey();
		String para = controller.getPara();
		
		// 获取传递参数
		ApiForm form = controller.getBean(ApiForm.class, null);
		
		// 开关
		boolean flag = ConfigCache.getValueToBoolean("API.FLAG");
		if (!flag) {
			controller.renderJson(ApiUtils.getServerMaintain(form));
			return;
		}

		// 黑名单
		String ip = IpUtils.getClientIP(controller.getRequest());
		String blackIps = ConfigCache.getValue("API.IP.BLACK");
		if (!StrUtils.isEmpty(ip) && !StrUtils.isEmpty(blackIps)) {
			List<String> ipList = Arrays.asList(blackIps.split(","));
			// 如果黑名单包含该IP返回错误信息
			if (ipList.contains(ip)) {
				controller.renderJson(ApiUtils.getIpBlackResp(form));
				return;
			}
		} else {
			log.debug("WARN: ApiInterceptor can't get ip ...");
		}

		// 版本验证
		String version = form.getVersion();
		String versions = ConfigCache.getValue("API.VERSIONS");
		if (StrUtils.isEmpty(version)) {
			controller.renderJson(ApiUtils.getVersionErrorResp(form));
			return;
		} else if (!StrUtils.isEmpty(versions)) {
			List<String> versionList = Arrays.asList(versions.split(","));
			// 如果不支持该版本返回错误信息
			if (!versionList.contains(version)) {
				controller.renderJson(ApiUtils.getVersionErrorResp(form));
				return;
			}
		}

		ai.invoke();

		// 调试日志
		if (ApiUtils.DEBUG) {
			log.info("API DEBUG INTERCEPTOR \n[path=" + path + "/" + para + "]" //
					+ "[from:" + form.toString() + "]" //
					+ "\n[time=" + (System.currentTimeMillis() - start) + "ms]");
		}
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

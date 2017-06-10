package com.jflyfox.util;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取真实IP
 * 
 * 2016年1月14日 下午10:44:05 flyfox 330627517@qq.com
 */
public class IpUtils {

	/**
	 * 尽量获取客户端真实ip
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @return 客户端ip
	 */
	public static String getClientIP(HttpServletRequest request) {
		if (request == null) {
			return null;
		}
		String xff = request.getHeader("X-Forwarded-For");
		String ip = getClientIPFromXFF(xff);
		if (ip != null) {
			return ip;
		}
		ip = request.getHeader("Proxy-Client-IP");
		if (isValidIP(ip)) {
			return ip;
		}
		ip = request.getHeader("WL-Proxy-Client-IP");
		if (isValidIP(ip)) {
			return ip;
		}
		ip = request.getRemoteAddr();
		return ip;
	}

	private static String getClientIPFromXFF(String xff) {
		if ((xff == null) || (xff.length() == 0)) {
			return null;
		}
		String[] ss = xff.split(",");
		for (String ip : ss) {
			ip = ip.trim();
			// 取第一个合法的IP并返回
			if (isValidIP(ip)) {
				return ip;
			}
		}
		return null;
	}

	private static final Pattern ipPattern = Pattern.compile("([0-9]{1,3}\\.){3}[0-9]{1,3}");

	private static boolean isValidIP(String ip) {
		if ((ip == null) || (ip.length() == 0) || "unknown".equalsIgnoreCase(ip)) {
			return false;
		}
		return ipPattern.matcher(ip).matches();
	}

}

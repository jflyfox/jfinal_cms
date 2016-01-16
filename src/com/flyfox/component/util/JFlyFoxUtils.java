package com.flyfox.component.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.flyfox.util.encrypt.DESUtils;

public class JFlyFoxUtils {

	public static final String TITLE_ATTR = "HEAD_TITLE";

	/**
	 * 承建部门ID
	 */
	public static final int DEPART_BUILD_ID = 1;

	/**
	 * 注册用户部门ID
	 */
	public static final int DEPART_REGIST_ID = 2;

	/**
	 * 第三方Oauth2部门ID
	 */
	public static final int DEPART_THIRD_ID = 3;

	/**
	 * session唯一Key
	 */
	public static final String USER_KEY = "USER_KEY";

	public static final int MENU_HOME = 1;
	public static final int MENU_NEWS = 2;
	public static final int MENU_FOOD = 3;
	public static final int MENU_TRAVEL = 4;
	public static final int MENU_EDUCATION = 5;
	public static final int MENU_PARK = 10;
	public static final int MENU_MARKET = 11;
	public static final int MENU_HOUSE = 12;
	public static final int MENU_TOPPIC = 13;

	public static final int MENU_ABOUT = 90;

	public static final int MENU_OTHER = 99;

	/**
	 * 博文目录
	 */
	public static final int MENU_BLOG = 100;

	private static final DESUtils des = new DESUtils("flyfoxxx");

	// admin:1RHFCLt64uOOViCTzgSaww== test:qvPQPhVn96Lx80f7BIaVjA==
	public static void main(String[] args) {
		String password = "admin123";
		String tmp = passwordEncrypt(password);
		System.out.println(tmp);
		System.out.println(passwordDecrypt(tmp));
	}

	/**
	 * 编码
	 * 
	 * 2015年2月25日 下午2:22:08 flyfox 330627517@qq.com
	 * 
	 * @param password
	 * @return
	 */
	public static String passwordEncrypt(String password) {
		return des.encryptString(password);
	}

	/**
	 * 解码
	 * 
	 * 2015年2月25日 下午2:22:13 flyfox 330627517@qq.com
	 * 
	 * @param encryptPassword
	 * @return
	 */
	public static String passwordDecrypt(String encryptPassword) {
		return des.decryptString(encryptPassword);
	}

	/**
	 * 默认密码
	 * 
	 * 2015年2月25日 下午2:23:37 flyfox 330627517@qq.com
	 * 
	 * @return
	 */
	public static String getDefaultPassword() {
		String defaultPassword = "123456";
		return passwordEncrypt(defaultPassword);
	}

	/**
	 * 删除侵入脚本
	 * 
	 * 2015年6月20日 下午5:16:21 flyfox 330627517@qq.com
	 * 
	 * @param htmlStr
	 * @return
	 */
	public static String delScriptTag(String htmlStr) {
		Pattern p_script = Pattern.compile("<script[^>]*?>[\\s\\S]*?<\\/script>", 2);
		Matcher m_script = p_script.matcher(htmlStr);
		htmlStr = m_script.replaceAll("");
		Pattern p_style = Pattern.compile("<style[^>]*?>[\\s\\S]*?<\\/style>", 2);
		Matcher m_style = p_style.matcher(htmlStr);
		htmlStr = m_style.replaceAll("");
		return htmlStr.trim();
	}

}

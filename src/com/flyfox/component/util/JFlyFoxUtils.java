package com.flyfox.component.util;

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

	private static final DESUtils des = new DESUtils("xxxxxxxx");

	// admin:LHmWhtwF/dHIwArTw+HUEg== test:qvPQPhVn96Lx80f7BIaVjA==
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

}

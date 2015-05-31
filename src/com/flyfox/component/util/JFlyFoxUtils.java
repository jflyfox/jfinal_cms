package com.flyfox.component.util;

import com.flyfox.util.cache.Cache;
import com.flyfox.util.cache.CacheManager;
import com.flyfox.util.encrypt.DESUtils;

public class JFlyFoxUtils {

	public static final String TITLE_ATTR = "HEAD_TITLE";
	/**
	 * session唯一Key
	 */
	public static final String USER_KEY = "USER_KEY";

	private final static String cacheName = "JFlyFoxUtils";
	private static Cache cache = CacheManager.get(cacheName);

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
	
	static {
		cache.add("home", "1");
		cache.add("1", "home");

		cache.add("news", "2");
		cache.add("2", "news");

		cache.add("food", "3");
		cache.add("3", "food");

		cache.add("travel", "4");
		cache.add("4", "travel");

		cache.add("education", "5");
		cache.add("5", "education");
		
		cache.add("park", "10");
		cache.add("10", "park");
		
		cache.add("market", "11");
		cache.add("11", "market");
		
		cache.add("house", "12");
		cache.add("12", "house");
		
		cache.add("topPic", "13");
		cache.add("13", "topPic");
		
		cache.add("about", "90");
		cache.add("90", "about");
		
		
		cache.add("other", "99");
		cache.add("99", "other");
	}

	public static String getMenu(String key) {
		return (cache.get(key) == null) ? key : cache.get(key).toString();
	}

	private static final DESUtils des = new DESUtils("flyoffox");

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

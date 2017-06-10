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

package com.jflyfox.util.extend;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 验证类
 * 
 * 2015年3月18日 下午3:21:14
 * flyfox 330627517@qq.com
 */
public class ValidUtils {

	private static final char[] enChar = new char[]{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	private static final char[] numChar = new char[]{'0','1','2','3','4','5','6','7','8','9'};
	
	static {
		Arrays.sort(enChar);
		Arrays.sort(numChar);
	}
	
	/**
	 * 英文字母或者数字
	 * 
	 * 2015年3月18日 下午3:19:36
	 * flyfox 330627517@qq.com
	 * @param test
	 * @return
	 */
	public static boolean isEnglishOrNumber(String test) {
		for (char ch : test.toCharArray()) {
			if (Arrays.binarySearch(enChar, ch) < 0 && Arrays.binarySearch(numChar, ch) < 0) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 英文字母
	 * 
	 * 2015年3月18日 下午3:19:44
	 * flyfox 330627517@qq.com
	 * @param test
	 * @return
	 */
	public static boolean isEnglish(String test) {
		for (char ch : test.toCharArray()) {
			if (Arrays.binarySearch(enChar, ch) < 0) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 数字
	 * 
	 * 2015年3月18日 下午3:20:00
	 * flyfox 330627517@qq.com
	 * @param test
	 * @return
	 */
	public static boolean isNumber(String test) {
		for (char ch : test.toCharArray()) {
			if (Arrays.binarySearch(numChar, ch) < 0) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 手机号验证
	 * 
	 * 2015年3月18日 下午3:20:12
	 * flyfox 330627517@qq.com
	 * @param str
	 * @return 验证通过返回true
	 */
	public static boolean isMobile(String str) {
		Pattern p = null;
		Matcher m = null;
		boolean b = false;
		p = Pattern.compile("^[1][3,4,5,8][0-9]{9}$"); // 验证手机号
		m = p.matcher(str);
		b = m.matches();
		return b;
	}

	/**
	 * 电话号码验证
	 * 
	 * 2015年3月18日 下午3:20:12
	 * flyfox 330627517@qq.com
	 * @param str
	 * @return 验证通过返回true
	 */
	public static boolean isPhone(String str) {
		Pattern p1 = null, p2 = null;
		Matcher m = null;
		boolean b = false;
		p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$"); // 验证带区号的
		p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$"); // 验证没有区号的
		if (str.length() > 9) {
			m = p1.matcher(str);
			b = m.matches();
		} else {
			m = p2.matcher(str);
			b = m.matches();
		}
		return b;
	}
}

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

package com.jflyfox.util;

import java.math.BigDecimal;

/**
 * 数字处理
 * 
 * @author flyfox 2012.08.08
 * @email 330627517@qq.com
 */
public class NumberUtils {

	private NumberUtils() {
	}

	/**
	 * 如果是null返回0
	 * 
	 * @param obj
	 * @return
	 */
	public static int parseInt(Object obj) {
		int value = 0;
		if (obj != null) {
			try {
				value = Integer.parseInt(obj.toString());
			} catch (Exception e) {
				value = 0;
			}
		}
		return value;
	}

	/**
	 * 如果是null返回BigDecimal.ZERO
	 * 
	 * @param obj
	 * @return
	 */
	public static BigDecimal parseBigDecimal(Object obj) {
		BigDecimal value = BigDecimal.ZERO;
		if (obj != null) {
			try {
				value = new BigDecimal(obj.toString());
			} catch (Exception e) {
				value = BigDecimal.ZERO;
			}
		}
		return value;
	}
	
	

	
	/**
	 * 将传入的字符串转成int型数据 . 遇到任何错误返回0
	 * @param str 待解析的字符串
	 * @return 解析结果 
	 */
	public static int parseInt(String str){
		return parseInt(str ,0);
	}

	/**
	 * 将传入的字符串转成int型数据 . 遇到任何错误返回replaceWith
	 * @param str 待解析的字符串
	 * @param defaultValue 遇到错误时的替换数字 . 
	 * @return 解析结果 
	 */
	public static int parseInt(String str ,int defaultValue){
		try{
			defaultValue = Integer.parseInt(str);
		} catch(Exception e){}
		return defaultValue ;
	}
	
	/**
	 * 将传入的字符串转成double型数据 . 遇到任何错误返回0
	 * @param str 待解析的字符串
	 * @return 解析结果 
	 */
	public static double parseDbl(String str){
		return parseDbl(str ,0);
	}
	/**
	 * 将传入的字符串转成double型数据 . 遇到任何错误返回replaceWith
	 * @param str 待解析的字符串
	 * @param defaultValue 遇到错误时的替换数字 . 
	 * @return 解析结果 
	 */
	public static double parseDbl(String str ,double defaultValue){
		try{
			defaultValue = Double.parseDouble(str);
		} catch(Exception e){}
		return defaultValue ;
	}
	
	public static float parseFloat(String str) {
		return parseFloat(str, 0);
	}
	
	public static float parseFloat(String str ,float b) {
		try{
			return Float.parseFloat(str);
		}catch(Exception e){
			return b;
		}
	}

	/**
	 * 遇到错误返回0L 
	 * @author 王平
	 * @since 2009.04.30
	 * @param str
	 * @return
	 */
	public static long parseLong(String str) {
		return parseLong(str, 0l);
	}
	/**
	 * 遇到错误返回defaultValue
	 * @author 王平
	 * @since 2009.04.30
	 * @param str
	 * @return
	 */
	public static long parseLong(String str ,long defaultValue){
		try{
			defaultValue = Long.parseLong(str);
		} catch(Exception e){}
		return defaultValue ;
	}

}

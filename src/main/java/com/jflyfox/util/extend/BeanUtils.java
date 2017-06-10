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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import com.jflyfox.util.DateUtils;
import com.jflyfox.util.NumberUtils;
import com.jflyfox.util.StrUtils;

/**
 * Bean处理
 * 
 * @author flyfox 2012.08.08
 * @email 330627517@qq.com
 */
public class BeanUtils {

	private BeanUtils() {
	}

	/**
	 * 将Map注入Bean
	 * 
	 * 2015年4月3日 下午12:02:28 flyfox 330627517@qq.com
	 * 
	 * @param srcMap
	 * @param des
	 * @return
	 */
	public static Object copy(Map<?, ?> srcMap, Object des) {
		try {
			if (des == null) {
				return null;
			}
			Method[] methods = des.getClass().getMethods();
			Class<?> _class = null;
			String vTmp = null;
			for (int i = 0; i < methods.length; i++) {
				Method aMethod = methods[i];
				String name = aMethod.getName();
				if (name.indexOf("set") != 0)
					continue;
				name = StrUtils.toLowerCaseFirst(name.substring(3));
				if (!srcMap.containsKey(name)) { // Bean里有这个set方法同时request里有这个参数
					// ,那么自动注入
					continue;
				}
				_class = aMethod.getParameterTypes()[0];
				try {
					vTmp = handlerType(srcMap, des, _class, aMethod, name);
				} catch (ClassCastException e) {
					throw new RuntimeException("类型转换错误[类名：" + des.getClass().getName() + "，字段名：" + name + "]", e);
				} catch (Exception e1) {
					throw new Exception("[" + name + "][" + vTmp + "]" + e1.getMessage());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return des;
	}

	/**
	 * 调用src对象 传递给 des对象 如果是null,不注入
	 * 
	 * 2015年4月3日 下午12:06:37 flyfox 330627517@qq.com
	 * 
	 * @param src
	 *            源对象
	 * @param des
	 *            目标对象
	 * @param flag
	 *            如果是true全部注入，如果是false不注入null
	 */
	public static void copy(Object src, Object des) {
		copy(src, des, false);
	}

	/**
	 * 调用src对象 传递给 des对象
	 * 
	 * 2015年4月3日 下午12:06:37 flyfox 330627517@qq.com
	 * 
	 * @param src
	 *            源对象
	 * @param des
	 *            目标对象
	 * @param flag
	 *            如果是true全部注入，如果是false不注入null
	 */
	public static void copy(Object src, Object des, boolean flag) {
		Method[] ms = des.getClass().getMethods();
		Method m;
		Method g;
		String name;
		for (int i = 0; i < ms.length; i++) {
			m = ms[i];
			name = m.getName();
			// PO Index不处理
			if (name.startsWith("set") && !"setIndex".equals(name)) {
				try {
					g = src.getClass().getMethod(name.replaceFirst("s", "g"));
					if (g == null)
						continue;
				} catch (Exception e) {
					continue;
				}
				try {
					Object obj = g.invoke(src);
					if (obj != null || flag) {
						m.invoke(des, obj);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static String handlerType(Map<?, ?> map, Object o, Class<?> _class, Method aMethod, String name)
			throws Exception, IllegalAccessException, InvocationTargetException {
		String vTmp;
		Object obj = map.get(name);
		vTmp = obj == null ? "" : obj.toString();
		paresType(o, _class, name, vTmp, aMethod);
		return vTmp;
	}

	private static void paresType(Object o, Class<?> _class, String name, String vTmp, Method aMethod)
			throws IllegalAccessException, InvocationTargetException {
		if ((_class == Float.class || _class == Float.TYPE)) {
			aMethod.invoke(o, new Object[] { NumberUtils.parseFloat(vTmp) });
		} else if (_class == Long.class || _class == Long.TYPE) {
			aMethod.invoke(o, new Object[] { NumberUtils.parseLong(vTmp) });
		} else if (_class == Double.class || _class == Double.TYPE) {
			aMethod.invoke(o, new Object[] { NumberUtils.parseDbl(vTmp) });
		} else if (_class == BigDecimal.class) {
			aMethod.invoke(o, new Object[] { NumberUtils.parseBigDecimal(vTmp) });
		} else if (_class == Integer.class || _class == Integer.TYPE) {
			aMethod.invoke(o, new Object[] { NumberUtils.parseInt(vTmp) });
		} else if (_class == String.class) {
			aMethod.invoke(o, new Object[] { vTmp });
		} else if (_class == Date.class) {
			aMethod.invoke(o, new Object[] { DateUtils.parse(vTmp) });
		} else if (_class == Timestamp.class) {
			aMethod.invoke(o, new Object[] { DateUtils.parseByAll(vTmp) });
		} else if (_class == Boolean.class || _class == Boolean.TYPE) {
			aMethod.invoke(o, new Object[] { parseBoolean(vTmp) });
		} else {
			throw new RuntimeException("不支持的参数类型: " + _class + " | " + name + " | " + vTmp);
		}
	}

	/**
	 * @see 安全处理boolean转换
	 * @param vTmp
	 * @return Boolean
	 * @author zb 330627517@qq.com
	 * @create Mar 29, 2013 4:43:59 PM
	 */
	private static Boolean parseBoolean(String vTmp) {
		if (StrUtils.isEmpty(vTmp)) {
			return false;
		}
		try {
			return Boolean.valueOf(vTmp);
		} catch (Exception e) {
			return false;
		}
	}

}
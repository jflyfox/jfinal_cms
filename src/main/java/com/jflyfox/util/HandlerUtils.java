package com.jflyfox.util;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.ActiveRecordException;
import com.jfinal.plugin.activerecord.Table;
import com.jfinal.plugin.activerecord.TableMapping;
import com.jflyfox.jfinal.base.BaseModel;

public class HandlerUtils {

	private HandlerUtils() {
	}
	/**
	 * 新增自定义获取model方法
	 * 
	 * 2016年1月12日 下午2:38:16 flyfox 330627517@qq.com
	 * 
	 * @param model
	 */
	public static void handler(BaseModel<?> model, JSONObject jsonObject) {
		Table table = TableMapping.me().getTable(model.getClass());

		for (Entry<String, Object> e : jsonObject.entrySet()) {
			String paraKey = e.getKey();
			String paraName = paraKey;
			if (table.hasColumnLabel(paraName)) {
				Class<?> colType = table.getColumnType(paraName);
				if (colType == null)
					throw new ActiveRecordException("The model attribute " + paraKey + " is not exists.");
				Object paraValue = e.getValue();
				try {

					Object value = paraValue != null ? convert(colType, paraValue.toString()) : null;
					model.set(paraName, value);
				} catch (Exception ex) {
					throw new RuntimeException("Can not convert parameter: " + paraName, ex);
				}
			}
		}
	}

	/**
	 * 新增自定义获取model方法
	 * 
	 * 2016年1月12日 下午2:38:16 flyfox 330627517@qq.com
	 * 
	 * @param model
	 */
	@SuppressWarnings("unchecked")
	public static void handler(BaseModel<?> model, HttpServletRequest request) {
		Table table = TableMapping.me().getTable(model.getClass());

		Map<String, String[]> parasMap = request.getParameterMap();
		for (Entry<String, String[]> e : parasMap.entrySet()) {
			String paraKey = e.getKey();
			String paraName = paraKey;
			if (table.hasColumnLabel(paraName)) {
				Class<?> colType = table.getColumnType(paraName);
				if (colType == null)
					throw new ActiveRecordException("The model attribute " + paraKey + " is not exists.");
				String[] paraValue = e.getValue();
				try {

					Object value = paraValue[0] != null ? convert(colType, paraValue[0]) : null;
					model.set(paraName, value);
				} catch (Exception ex) {
					throw new RuntimeException("Can not convert parameter: " + paraName, ex);
				}
			}
		}
	}

	/**
	 * request赋值给PO
	 * 
	 * @param request
	 * @return 注入数据后的类
	 * @throws Exception
	 */
	public static Object handler(Map<?, ?> map, Object o) {
		try {
			if (o == null) {
				return null;
			}
			Method[] methods = o.getClass().getMethods();
			Class<?> _class = null;
			String vTmp = null;
			for (int i = 0; i < methods.length; i++) {
				Method aMethod = methods[i];
				String name = aMethod.getName();
				if (name.indexOf("set") != 0)
					continue;
				name = StrUtils.toLowerCaseFirst(name.substring(3));
				if (!map.containsKey(name)) { // Bean里有这个set方法同时request里有这个参数
					// ,那么自动注入
					continue;
				}
				_class = aMethod.getParameterTypes()[0];
				try {
					Object obj = map.get(name);
					vTmp = obj == null ? "" : obj.toString();
					aMethod.invoke(o, new Object[] { convert(_class, vTmp) });
				} catch (ClassCastException e) {
					throw new RuntimeException("类型转换错误[类名：" + o.getClass().getName() + "，字段名：" + name + "]", e);
				} catch (Exception e1) {
					throw new Exception("[" + name + "][" + vTmp + "]" + e1.getMessage());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public static Object handler(HttpServletRequest request, Object o) {
		try {
			if (o == null) {
				return null;
			}
			Method[] methods = o.getClass().getMethods();
			Class<?> _class = null;
			Map<?, ?> map = request.getParameterMap();
			String vTmp = null;
			
			for (int i = 0; i < methods.length; i++) {
				Method aMethod = methods[i];
				String name = aMethod.getName();
				if (name.indexOf("set") != 0)
					continue;
				name = StrUtils.toLowerCaseFirst(name.substring(3));
				try {
					Object obj = null;
					if (map.containsKey(name.toUpperCase())) { // Bean里有这个set方法同时request里有这个参数
						obj = map.get(name.toUpperCase());
					} else if (map.containsKey(name)) {
						obj = map.get(name);
					} else {
						continue;
					}
					_class = aMethod.getParameterTypes()[0];
					vTmp = obj == null ? "" : obj.toString();
					aMethod.invoke(o, new Object[] { convert(_class, vTmp) });
				} catch (ClassCastException e) {
					throw new RuntimeException("类型转换错误[类名：" + o.getClass().getName() + "，字段名：" + name + "]", e);
				} catch (Exception e1) {
					throw new Exception("[" + name + "][" + vTmp + "]" + e1.getMessage());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	/**
	 * 调用src对象 传递给 des对象
	 * 
	 * @param des
	 *            目标对象
	 * @param src
	 *            源对象
	 */
	public static void setByGetter(Object des, Object src) {
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
					m.invoke(des, g.invoke(src));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private static Object convert(Class<?> _class, String vTmp) {
		if ((_class == Float.class || _class == Float.TYPE)) {
			return NumberUtils.parseFloat(vTmp);
		} else if (_class == Long.class || _class == Long.TYPE) {
			return NumberUtils.parseLong(vTmp);
		} else if (_class == Double.class || _class == Double.TYPE) {
			return NumberUtils.parseDbl(vTmp);
		} else if (_class == BigDecimal.class) {
			return new BigDecimal(vTmp);
		} else if (_class == Integer.class || _class == Integer.TYPE) {
			return NumberUtils.parseInt(vTmp);
		} else if (_class == String.class) {
			return vTmp;
		} else if (_class == Date.class) {
			return DateUtils.parse(vTmp, "yyyy-MM-dd");
		} else if (_class == Timestamp.class) {
			return DateUtils.parseByAll(vTmp);
		} else if (_class == Boolean.class || _class == Boolean.TYPE) {
			return parseBoolean(vTmp);
		} else {
			throw new RuntimeException("不支持的参数类型: " + _class + " | " + vTmp);
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

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

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 方法类
 * 
 * @author syh
 * 
 */

public class ReflectionUtils {

	/**
	 * 循环向上转型, 获取对象的 DeclaredMethod
	 * 
	 * @param object
	 *            : 子类对象
	 * @param methodName
	 *            : 父类中的方法名
	 * @param parameterTypes
	 *            : 父类中的方法参数类型
	 * @return 父类中的方法对象
	 */
	private static Method getDeclaredMethod(Object object, String methodName, Class<?>... parameterTypes) {
		Method method = null;
		Class<?> clazz = object.getClass();
		while (clazz != Object.class) {
			try {
				method = clazz.getDeclaredMethod(methodName, parameterTypes);
				return method;
			} catch (Exception e) {
				// 未获取到就往上找
				clazz = clazz.getSuperclass();
			}
		}
		return null;
	}

	/**
	 * 直接调用对象方法, 而忽略修饰符(private, protected, default)
	 * 
	 * @param object
	 *            : 子类对象
	 * @param methodName
	 *            : 父类中的方法名
	 * @param parameterTypes
	 *            : 父类中的方法参数类型
	 * @param parameters
	 *            : 父类中的方法参数
	 * @return 父类中方法的执行结果
	 */
	public static Object invokeMethod(Object object, String methodName, Class<?>[] parameterTypes, Object[] parameters) {
		// 根据 对象、方法名和对应的方法参数 通过反射 调用上面的方法获取 Method 对象
		Method method = getDeclaredMethod(object, methodName, parameterTypes);

		if (method == null) {
			System.err.println(object.getClass() + "未获取到" + methodName + "方法!");
			return null;
		}

		// 抑制Java对方法进行检查,主要是针对私有方法而言
		method.setAccessible(true);

		try {
			if (null != method) {

				// 调用object 的 method 所代表的方法，其方法的参数是 parameters
				return method.invoke(object, parameters);
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 循环向上转型, 获取对象的 DeclaredField
	 * 
	 * @param object
	 *            : 子类对象
	 * @param fieldName
	 *            : 父类中的属性名
	 * @return 父类中的属性对象
	 */
	private static Field getDeclaredField(Object object, String fieldName) {
		Field field = null;

		Class<?> clazz = object.getClass();

		while (clazz != Object.class) {
			try {
				field = clazz.getDeclaredField(fieldName);
				return field;
			} catch (Exception e) {
				// 未获取到就往上找
				clazz = clazz.getSuperclass();
			}
		}

		return null;
	}

	/**
	 * 直接设置对象属性值, 忽略 private/protected 修饰符, 也不经过 setter
	 * 
	 * @param object
	 *            : 子类对象
	 * @param fieldName
	 *            : 父类中的属性名
	 * @param value
	 *            : 将要设置的值
	 */
	public static void setFieldValue(Object object, String fieldName, Object value) {

		// 根据 对象和属性名通过反射 调用上面的方法获取 Field对象
		Field field = getDeclaredField(object, fieldName);

		if (field == null) {
			System.err.println(object.getClass() + "未获取到" + fieldName + "属性!");
			return;
		}

		// 抑制Java对其的检查
		field.setAccessible(true);

		try {
			// 将 object 中 field 所代表的值 设置为 value
			field.set(object, value);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 直接读取对象的属性值, 忽略 private/protected 修饰符, 也不经过 getter
	 * 
	 * @param object
	 *            : 子类对象
	 * @param fieldName
	 *            : 父类中的属性名
	 * @return : 父类中的属性值
	 */

	public static Object getFieldValue(Object object, String fieldName) {

		// 根据 对象和属性名通过反射 调用上面的方法获取 Field对象
		Field field = getDeclaredField(object, fieldName);

		if (field == null) {
			System.err.println(object.getClass() + "未获取到" + fieldName + "属性!");
			return null;
		}

		// 抑制Java对其的检查
		field.setAccessible(true);

		try {
			// 获取 object 中 field 所代表的属性值
			return field.get(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

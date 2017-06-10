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

package com.jflyfox.util.serializable;

import java.util.HashMap;
import java.util.Map;

public class SerializerManage {

	private static final Map<String, Serializer> map = new HashMap<String, Serializer>();
	private static String DEFAULT_KEY = null;

	static {
		JavaSerializer javaSerializer = new JavaSerializer();
		add(javaSerializer);
		DEFAULT_KEY = javaSerializer.name();
	}

	public static void setDefaultKey(String defaultKey) {
		DEFAULT_KEY = defaultKey;
	}
	
	/**
	 * V3.1以前版本兼容
	 * 
	 * 2017年1月18日 下午2:38:00
	 * flyfox 330627517@qq.com
	 * @param key
	 * @param serializer
	 */
	public static void add(String key, Serializer serializer) {
		map.put(key, serializer);
	}
	
	public static void add(Serializer serializer) {
		map.put(serializer.name(), serializer);
	}

	public static Serializer get(String key) {
		return map.get(key);
	}
	
	public static Serializer getDefault() {
		return map.get(DEFAULT_KEY);
	}

	public static byte[] serialize(Object obj) throws Exception {
		return getDefault().serialize(obj);
	}

	public static Object deserialize(byte[] bytes) throws Exception {
		return getDefault().deserialize(bytes);
	}
}

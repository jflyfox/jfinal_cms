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

package com.jflyfox.util.cache.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.jflyfox.util.cache.Cache;
import com.jflyfox.util.serializable.Serializer;
import com.jflyfox.util.serializable.SerializerManage;

/**
 * 内存序列化
 * 
 * 2015年4月26日 下午8:24:23 flyfox 330627517@qq.com
 */
public class MemorySerializeCache implements Cache {

	protected Serializer serializer;
	protected String name;
	protected Map<String, byte[]> map = new ConcurrentHashMap<String, byte[]>();

	public MemorySerializeCache() {
		this.serializer = SerializerManage.getDefault();
	}

	public MemorySerializeCache(Serializer serializer) {
		this.serializer = serializer;
	}

	public String name() {
		return name;
	}

	public MemorySerializeCache name(String name) {
		this.name = name;
		return this;
	}

	public MemorySerializeCache add(String key, Object value) {
		try {
			map.put(key, serializer.serialize(value));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this;
	}

	@SuppressWarnings("unchecked")
	public <T> T get(String key) {
		try {
			return (T) serializer.deserialize(map.get(key));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Object remove(String key) {
		return map.remove(key);
	}

	public void clear() {
		map.clear();
	}

	public int size() {
		return map.size();
	}

	public Set<String> keys() {
		if (map.size() == 0) {
			return null;
		}
		return map.keySet();
	}

	@SuppressWarnings("unchecked")
	public <T> Collection<T> values() {
		if (map.size() == 0) {
			return null;
		}

		Collection<T> list = new ArrayList<T>();
		for (byte[] obj : map.values()) {
			try {
				list.add((T) serializer.deserialize(obj));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

}

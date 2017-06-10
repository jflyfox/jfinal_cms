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

package com.jflyfox.util.cache;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.jflyfox.util.cache.impl.MemorySerializeCache;
import com.jflyfox.util.serializable.SerializerManage;

/**
 * 缓存管理接口
 * 
 * 2015年4月26日 下午8:47:45 flyfox 330627517@qq.com
 */
public class CacheManager {

	private static ConcurrentHashMap<String, Cache> cacheManager = new ConcurrentHashMap<String, Cache>();
	static ICacheManager _CreateCache;

	protected CacheManager() {
	}

	static {
		_CreateCache = new ICacheManager() {
			public Cache getCache() {
				return new MemorySerializeCache(SerializerManage.getDefault());
			}
		};
	}

	public static void setCache(ICacheManager thisCache) {
		_CreateCache = thisCache;
	}

	public static Cache get(String name) {
		Cache cache = cacheManager.get(name);
		if (cache == null) {
			synchronized (cacheManager) {
				cache = cacheManager.get(name);
				if (cache == null) {
					cache = _CreateCache.getCache();
					cache.name(name);
					cacheManager.put(name, cache);
				}
			}
		}
		return cache;
	}

	public static int size() {
		return cacheManager.size();
	}

	public static Collection<Cache> values() {
		return cacheManager.values();
	}

	public static Set<String> keys() {
		return cacheManager.keySet();
	}

}
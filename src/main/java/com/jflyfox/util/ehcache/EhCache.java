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

package com.jflyfox.util.ehcache;

import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

/**
 * EHCache
 */
public class EhCache {

	private net.sf.ehcache.Cache cache;

	public EhCache(Cache cache) {
		this.cache = cache;
	}

	@SuppressWarnings("rawtypes")
	public List keys() {
		return this.cache.getKeys();
	}

	public Object get(Object key)  {
			if (key == null)
				return null;
			else {
				Element element = cache.get(key);
				if (element != null)
					return element.getObjectValue();
			}
			return null;
	}

	public void update(Object key, Object value) {
		put(key, value);
	}

	public void put(Object key, Object value) {
		Element element = new Element(key, value);
		cache.put(element);

	}

	public void evict(Object key) {
		cache.remove(key);
	}

	public void evict(List<?> keys) {
		cache.removeAll(keys);
	}

	public void clear() {
		cache.removeAll();
	}

	public void destroy() {
		cache.getCacheManager().removeCache(cache.getName());
	}

}
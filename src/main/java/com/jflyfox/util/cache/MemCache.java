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

import com.jflyfox.util.cache.impl.MemorySerializeCache;
import com.jflyfox.util.serializable.Serializer;

/**
 * 请使用MemorySerializeCache
 * 
 * 2015年4月26日 下午8:23:59
 * flyfox 330627517@qq.com
 */
@Deprecated
public class MemCache extends MemorySerializeCache implements Cache {

	public MemCache(Serializer serializer) {
		super(serializer);
	}

}

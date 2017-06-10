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

public interface Cache {

	/**
	 * 设置缓存名称
	 * 
	 * 2015年4月26日 下午8:31:14 flyfox 330627517@qq.com
	 * 
	 * @param name
	 * @return
	 */
	public Cache name(String name);

	/**
	 * 根据key获取缓存数据
	 * 
	 * 2015年4月26日 下午8:31:25 flyfox 330627517@qq.com
	 * 
	 * @param key
	 * @return
	 */
	public <T> T get(String key);

	/**
	 * 添加缓存获取
	 * 
	 * 2015年4月26日 下午8:31:46 flyfox 330627517@qq.com
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Cache add(String key, Object value);

	/**
	 * 移除缓存数据
	 * 
	 * 2015年4月26日 下午8:31:52 flyfox 330627517@qq.com
	 * 
	 * @param key
	 * @return
	 */
	public Object remove(String key);
	
	/**
	 * 清楚所有数据
	 * 
	 * 2015年4月26日 下午8:31:52 flyfox 330627517@qq.com
	 * 
	 * @return
	 */
	public void clear();

	/**
	 * 获取缓存数量
	 * 
	 * 2015年4月26日 下午8:31:59 flyfox 330627517@qq.com
	 * 
	 * @return
	 */
	public int size();
	
	/**
	 * 返回数据key列表
	 * 
	 * 2017年1月18日 下午4:24:01
	 * flyfox 330627517@qq.com
	 * @return
	 */
	public Set<String> keys();
	
	/**
	 * 返回数据缓存列表
	 * 
	 * 2015年4月26日 下午8:33:11 flyfox 330627517@qq.com
	 * 
	 * @return
	 */
	public <T> Collection<T> values();
}

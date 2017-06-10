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
package com.jflyfox.jfinal.base;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Table;
import com.jfinal.plugin.activerecord.TableMapping;
import com.jflyfox.util.cache.Cache;
import com.jflyfox.util.cache.CacheManager;

/**
 * Model 优化修改
 * 
 * @author flyfox 2014-2-11
 * @param <M>
 */
public class BaseModel<M extends Model<M>> extends Model<M> {

	protected static final long serialVersionUID = 1L;

	protected static final Log log = Log.getLog(BaseModel.class);

	@Override
	public Integer getInt(String attr) {
		Object obj = get(attr);
		if (obj == null) {
			return null;
		} else if (obj instanceof Integer) {
			return (Integer) obj;
		} else if (obj instanceof BigDecimal) {
			return ((BigDecimal) obj).intValue();
		} else if (obj instanceof String) {
			try {
				return Integer.parseInt((String) obj);
			} catch (Exception e) {
				throw new RuntimeException("String can not cast to Integer : " + attr.toString());
			}
		} else {
			try {
				return Integer.parseInt(obj.toString());
			} catch (Exception e) {
				throw new RuntimeException("Object can not cast to Integer : " + attr.toString());
			}
		}
	}

	public Table getTable() {
		return TableMapping.me().getTable(getClass());
	}

	/**
	 * Paginate.
	 * 
	 * @param paginator
	 *            the page
	 * @param select
	 *            the select part of the sql statement
	 * @param sqlExceptSelect
	 *            the sql statement excluded select part
	 * @param paras
	 *            the parameters of sql
	 * @return Page
	 */
	public Page<M> paginate(Paginator paginator, String select, String sqlExceptSelect, Object... paras) {
		return paginate(paginator.getPageNo(), paginator.getPageSize(), select, sqlExceptSelect, paras);
	}

	/**
	 * Find model.
	 * 
	 * @param where
	 *            an SQL statement that may contain one or more '?' IN parameter
	 *            placeholders
	 * @param paras
	 *            the parameters of sql
	 * @return the list of Model
	 */
	public List<M> findByWhere(String where, Object... paras) {
		return findByWhereAndColumns(where, "*", paras);
	}

	/**
	 * Find model.
	 * 
	 * @param where
	 *            an SQL statement that may contain one or more '?' IN parameter
	 *            placeholders
	 * @param columns
	 * @param paras
	 *            the parameters of sql
	 * @return the list of Model
	 */
	public List<M> findByWhereAndColumns(String where, String columns, Object... paras) {
		String sql = " select " + columns + " from " + getTable().getName() + " " + where;
		return find(sql, paras);
	}

	/**
	 * Find first model. I recommend add "limit 1" in your sql.
	 * 
	 * @param where
	 *            an SQL statement that may contain one or more '?' IN parameter
	 *            placeholders
	 * @param paras
	 *            the parameters of sql
	 * @return Model
	 */
	public M findFirstByWhere(String where, Object... paras) {
		return findFirstByWhereAndColumns(where, "*", paras);
	}

	/**
	 * Find first model. I recommend add "limit 1" in your sql.
	 * 
	 * @param where
	 *            an SQL statement that may contain one or more '?' IN parameter
	 *            placeholders
	 * @param columns
	 * @param paras
	 *            the parameters of sql
	 * @return Model
	 */
	public M findFirstByWhereAndColumns(String where, String columns, Object... paras) {
		String sql = " select " + columns + " from " + getTable().getName() + " " + where;
		return findFirst(sql, paras);
	}

	// ///////////////////////////////////////缓存部分/////////////////////////////////////////////////

	/**
	 * paginateCache是重构版paginateByCache，使用自己的Cache
	 * 
	 * 2015年5月7日 下午12:20:23 flyfox 330627517@qq.com
	 * 
	 * @param cacheName
	 * @param key
	 * @param paginator
	 * @param select
	 * @param sqlExceptSelect
	 * @param paras
	 * @return
	 */
	public Page<M> paginateCache(String cacheName, String key, Paginator paginator, String select,
			String sqlExceptSelect, Object... paras) {
		Cache cache = CacheManager.get(cacheName);
		Page<M> result = cache.get(key);
		if (result == null) {
			result = paginate(paginator.getPageNo(), paginator.getPageSize(), select, sqlExceptSelect, paras);
			cache.add(key, result);
		}
		return result;
	}

	/**
	 * findCache是重构版findByCache，使用自己的Cache
	 * 
	 * 2015年5月7日 下午12:12:08 flyfox 330627517@qq.com
	 * 
	 * @param cacheName
	 * @param key
	 * @param sql
	 * @param paras
	 * @return
	 */
	public List<M> findCache(String cacheName, String key, String sql, Object... paras) {
		Cache cache = CacheManager.get(cacheName);
		List<M> result = cache.get(key);
		if (result == null) {
			result = find(sql, paras);
			cache.add(key, result);
		}
		return result;
	}

	/**
	 * findFirstCache是重构版findByCache，使用自己的Cache
	 * 
	 * 2015年5月7日 下午12:12:08 flyfox 330627517@qq.com
	 * 
	 * @param cacheName
	 * @param key
	 * @param sql
	 * @param paras
	 * @return
	 */
	public M findFirstCache(String cacheName, String key, String sql, Object... paras) {
		Cache cache = CacheManager.get(cacheName);
		M result = cache.get(key);
		if (result == null) {
			result = findFirst(sql, paras);
			cache.add(key, result);
		}
		return result;
	}

	/**
	 * 根据Key删除Cache
	 * 
	 * 2015年5月7日 下午12:16:43 flyfox 330627517@qq.com
	 * 
	 * @param cacheName
	 * @param key
	 */
	public void removeCache(String cacheName, String key) {
		Cache cache = CacheManager.get(cacheName);
		cache.remove(key);
	}

	/**
	 * 清理Cache所有数据
	 * 
	 * 2015年5月7日 下午12:16:52 flyfox 330627517@qq.com
	 * 
	 * @param cacheName
	 */
	public void clearCache(String cacheName) {
		Cache cache = CacheManager.get(cacheName);
		cache.clear();
	}

	public Map<String, Object> getAttrs() {
		return super.getAttrs();
	}

	public Object[] getAttrValues() {
		return super._getAttrValues();
	}

}

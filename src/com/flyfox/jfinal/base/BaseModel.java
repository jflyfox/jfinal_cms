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
package com.flyfox.jfinal.base;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.flyfox.system.log.SysLog;
import com.flyfox.util.DateUtils;
import com.flyfox.util.NumberUtils;
import com.flyfox.util.cache.Cache;
import com.flyfox.util.cache.CacheManager;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Table;
import com.jfinal.plugin.activerecord.TableMapping;

/**
 * Model 优化修改
 * 
 * @author flyfox 2014-2-11
 * @param <M>
 * 
 *            TODO 覆盖flyfox_jfinal
 */
public class BaseModel<M extends Model<M>> extends Model<M> {

	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(BaseModel.class);

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
		}
		return null;
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

	// TODO 以下为新增
	/****************************************** 加入公共日志 ******************************************/
	@Override
	public boolean save() {
		boolean flag = super.save();
		String tableName = getTable().getName();
		String[] keys = getTable().getPrimaryKey();
		Object id = null;
		if (keys != null && keys.length >= 1) {
			id = get(keys[0]);
		}
		Integer primaryId = (id != null) ? NumberUtils.parseInt(id) : null;
		saveLog(tableName, primaryId, SysLog.MODEL_SAVE);
		return flag;
	}

	@Override
	public boolean delete() {
		boolean flag = super.delete();
		String tableName = getTable().getName();
		String[] keys = getTable().getPrimaryKey();
		Object id = null;
		if (keys != null && keys.length >= 1) {
			id = get(keys[0]);
		}
		Integer primaryId = (id != null) ? NumberUtils.parseInt(id) : null;
		saveLog(tableName, primaryId, SysLog.MODEL_DELETE);
		return flag;
	}

	@Override
	public boolean deleteById(Object id) {
		boolean flag = super.deleteById(id);
		String tableName = getTable().getName();
		Integer primaryId = (id != null) ? NumberUtils.parseInt(id) : null;
		saveLog(tableName, primaryId, SysLog.MODEL_DELETE);
		return flag;
	}

	@Override
	public boolean update() {
		boolean flag = super.update();
		String tableName = getTable().getName();
		String[] keys = getTable().getPrimaryKey();
		Object id = null;
		if (keys != null && keys.length >= 1) {
			id = get(keys[0]);
		}
		Integer primaryId = (id != null) ? NumberUtils.parseInt(id) : null;
		saveLog(tableName, primaryId, SysLog.MODEL_UPDATE);
		return flag;
	}

	protected void saveLog(String tableName, Integer primaryId, String operType) {
		try {
			Integer updateId = getAttrs().containsKey("update_id") ? getInt("update_id") : 0;
			String updateTime = getAttrs().containsKey("update_id") ? getStr("update_time") : DateUtils
					.getNow(DateUtils.DEFAULT_REGEX_YYYY_MM_DD_HH_MIN_SS);
			String sql = "INSERT INTO `sys_log` ( `log_type`, `oper_object`, `oper_table`," //
					+ " `oper_id`, `oper_type`, `oper_remark`, `create_time`, `create_id`) " //
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			Db.update(sql, SysLog.TYPE_MODEL, SysLog.getTableRemark(tableName), tableName, //
					primaryId, operType, "", updateTime, updateId);
		} catch (Exception e) {
			log.error("添加日志失败", e);
		}
	}

	public Map<String, Object> getAttrs() {
		return super.getAttrs();
	}
}

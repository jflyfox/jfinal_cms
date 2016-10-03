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
package com.jflyfox.component.base;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jflyfox.jfinal.base.BaseModel;
import com.jflyfox.system.log.SysLog;
import com.jflyfox.util.DateUtils;
import com.jflyfox.util.NumberUtils;

/**
 * Model 优化修改
 * 
 * 2016年1月16日 下午4:57:39
 * flyfox 369191470@qq.com
 */
public class BaseProjectModel<M extends Model<M>> extends BaseModel<M> {

	private static final long serialVersionUID = 1L;

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

}

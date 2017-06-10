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
package com.jflyfox.jfinal.component.db;

import com.jflyfox.util.StrUtils;

/**
 * sql拼接
 * 
 * @author flyfox 2012.08.08
 * @email 330627517@qq.com
 */
public class SQLUtils {

	private StringBuffer sqlBuffer;
	private String alias = "";

	public SQLUtils() {
		sqlBuffer = new StringBuffer();
	}

	public SQLUtils(String sql) {
		sqlBuffer = new StringBuffer(sql);
	}

	public SQLUtils(String sql, String alias) {
		sqlBuffer = new StringBuffer(sql);
		this.alias = alias;
	}

	public void whereLike(String attrName, String value) {
		if (StrUtils.isNotEmpty(value)) {
			sqlBuffer.append(" AND " + getAttrName(attrName) + " LIKE '%").append(value).append("%'");
		}
	}

	public void whereEquals(String attrName, String value) {
		if (StrUtils.isNotEmpty(value)) {
			sqlBuffer.append(" AND " + getAttrName(attrName) + " = '").append(value).append("'");
		}
	}

	public void whereEquals(String attrName, Integer value) {
		if (value != null && value > 0) {
			sqlBuffer.append(" AND " + getAttrName(attrName) + " = ").append(value);
		}
	}

	public SQLUtils append(CharSequence s) {
		sqlBuffer.append(s);
		return this;
	}

	public StringBuffer getMe() {
		return sqlBuffer;
	}

	private String getAttrName(String attrName) {
		if (StrUtils.isEmpty(alias)) {
			return attrName;
		}
		return alias + "." + attrName;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String toString() {
		return sqlBuffer.toString();
	}

	/*********** utils ****************/
	/**
	 * 生成符合条件的sql语句,解决in问题
	 * 
	 * @param sqlParam
	 * @param columnName
	 * @return
	 */
	public static String getSqlIn(String sqlParam, String columnName) {
		if (sqlParam == null || "".equals(sqlParam)) {
			return " ";
		}
		String[] str_arr = sqlParam.split(",");
		return getSqlIn(str_arr, columnName, true);
	}

	/**
	 * 生成符合条件的sql语句,解决not in问题
	 * 
	 * @param sqlParam
	 * @param columnName
	 * @return
	 */
	public static String getNotSqlIn(String sqlParam, String columnName) {
		if (sqlParam == null || "".equals(sqlParam)) {
			return " ";
		}
		String[] str_arr = sqlParam.split(",");
		return getSqlIn(str_arr, columnName, false);
	}

	/**
	 * 生成符合条件的sql语句,解决in问题
	 * 
	 * @param str_arr
	 * @param columnName
	 * @param flag
	 *            ture:IN false:NOT IN
	 * @return
	 */
	public static String getSqlIn(String[] str_arr, String columnName, boolean flag) {
		int buff_length = 0;
		int spIndex = 500;
		int width = str_arr.length;
		int arr_width = width / spIndex;
		if (width % spIndex != 0) {
			arr_width += 1;
		}
		StringBuffer buffer = new StringBuffer("");
		for (int i = 0; i < arr_width; i++) {
			if (flag) {
				buffer.append(" " + columnName + " IN(");
			} else {
				buffer.append(" " + columnName + " NOT IN(");
			}
			for (int j = i * spIndex, k = 0; j < width && k < spIndex; j++, k++) {
				buffer.append("'" + str_arr[j] + "',");
			}
			buff_length = buffer.length();
			buffer = buffer.delete(buff_length - 1, buff_length).append(") OR");
		}
		return buffer.substring(0, buffer.length() - 2);
	}
}

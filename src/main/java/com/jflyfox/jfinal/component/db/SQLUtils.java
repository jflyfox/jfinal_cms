/**
 * Copyright 2015-2025 FLY的狐狸(email:jflyfox@sina.com qq:369191470).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jflyfox.jfinal.component.db;

import com.jfinal.log.Log;
import com.jflyfox.util.StrUtils;

/**
 * sql拼接
 *
 * @author flyfox 2012.08.08
 * @email 330627517@qq.com
 */
public class SQLUtils {

    private static final Log logger = Log.getLog(SQLUtils.class);

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
        if (checkSQLInject(attrName) || checkSQLInject(value)) {
            return;
        }

        if (StrUtils.isNotEmpty(value)) {
            sqlBuffer.append(" AND " + getAttrName(attrName) + " LIKE '%").append(value).append("%'");
        }
    }

    public void whereEquals(String attrName, String value) {
        if (checkSQLInject(attrName) || checkSQLInject(value)) {
            return;
        }

        if (StrUtils.isNotEmpty(value)) {
            sqlBuffer.append(" AND " + getAttrName(attrName) + " = '").append(value).append("'");
        }
    }

    public void whereEquals(String attrName, Integer value) {
        if (checkSQLInject(attrName)) {
            return;
        }

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

    @Override
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
        if (checkSQLInject(sqlParam) || checkSQLInject(columnName)) {
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
        if (checkSQLInject(sqlParam) || checkSQLInject(columnName)) {
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
     * @param flag       ture:IN false:NOT IN
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

    /**
     * 检查是否存在非法字符，防止SQL注入
     *
     * @param str 被检查的字符串
     * @return true-字符串中存在非法字符，false-不存在非法字符
     */
    public static boolean checkSQLInject(String str) {
        // 如果传入空串则认为不存在非法字符
        if (StrUtils.isEmpty(str)) {
            return false;
        }

        // 判断黑名单
        String[] blacks = {"script", "mid", "master", "truncate", "insert", "select", "delete", "update", "declare",
                "iframe", "'", "onreadystatechange", "alert", "atestu", "xss", ";", "'", "<", ">", "(", ")",
                // ",",, "\""
                "\\", "svg", "confirm", "prompt", "onload", "onmouseover", "onfocus", "onerror"};
        // 判断白名单
        String[] whites = {"updatetime", "update_time", "\""};

        // sql不区分大小写
        str = str.toLowerCase();

        for (int i = 0; i < whites.length; i++) {
            if (whites[i].equals(str)) {
                return false;
            }
        }

        for (int i = 0; i < blacks.length; i++) {
            if (str.indexOf(blacks[i]) >= 0) {
                logger.error("SQLInject 原因：特殊字符，传入str=" + str + ",包含特殊字符：" + blacks[i]);
                return true;
            }
        }
        return false;
    }
}

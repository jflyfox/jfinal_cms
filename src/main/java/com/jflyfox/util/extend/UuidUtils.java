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

package com.jflyfox.util.extend;

import java.util.UUID;

/**
 * UUID处理
 * 
 * @author flyfox 2012.08.08
 * @email 330627517@qq.com
 */
public class UuidUtils {

	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();// 标准UUID
	}

	public static String getUUID2() {
		String str = getUUID();
		// 去掉"-"符号
		String temp = str.substring(0, 8) + str.substring(9, 13)
				+ str.substring(14, 18) + str.substring(19, 23)
				+ str.substring(24);
		return temp;
	}

	// 获得指定数量的UUID
	public static String[] getUUID2(int number) {
		if (number < 1) {
			return null;
		}
		String[] ss = new String[number];
		for (int i = 0; i < number; i++) {
			ss[i] = getUUID2();
		}
		return ss;
	}

}

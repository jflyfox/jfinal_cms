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

package com.jflyfox.util;


/**
 * 常量类
 * 
 * @author flyfox 2012.08.08
 * @email 330627517@qq.com
 */
public class Constants {

	/**
	 * 底层调试标示
	 */
	public static final boolean DEBUG = Config.getToBoolean("CONSTANTS.DEBUG");
	/**
	 * 测试标示
	 */
	public static final boolean TEST = Config.getToBoolean("CONSTANTS.TEST");
	/**
	 * 默认分页
	 */
	public static final int DEFAULT_PAGE_SIZE = Config.getToInt("CONSTANTS.DEFAULT_PAGE_SIZE");
}
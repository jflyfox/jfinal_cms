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

package com.jflyfox.jfinal.component.util;

import com.jflyfox.util.Config;


public class Attr {

	/**
	 * session user
	 */
	public static final String SESSION_NAME = Config.getStr("ATTR.SESSION_NAME");

	/**
	 * 是否是移动设备
	 */
	public static final String SESSION_IS_MOILE = Config.getStr("ATTR.SESSION_IS_MOILE");

	/**
	 * model前缀
	 */
	public static final String PAGE_MODEL_NAME = Config.getStr("ATTR.MODEL_NAME");
	
	/**
	 * attr前缀
	 */
	public static final String PAGE_ATTR_NAME = Config.getStr("ATTR.ATTR_NAME");

	/**
	 * form前缀
	 */
	public static final String PAGE_FORM_NAME = Config.getStr("ATTR.FORM_NAME");
	
	/**
	 * 手机用户路径前缀
	 */
	public static final String PATH_MOBILE = Config.getStr("ATTR.PATH_MOBILE");

	/**
	 * PC用户路径前缀
	 */
	public static final String PATH_PC = Config.getStr("ATTR.PATH_PC");
}

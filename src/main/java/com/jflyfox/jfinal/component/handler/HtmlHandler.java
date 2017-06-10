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

package com.jflyfox.jfinal.component.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.handler.Handler;
import com.jflyfox.util.Config;
import com.jflyfox.util.StrUtils;

/**
 * 路径伪静态化
 * 
 * 2015年5月5日 下午5:07:20 flyfox 330627517@qq.com
 */
public class HtmlHandler extends Handler {

	@Override
	public void handle(String s, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			boolean[] booleans) {

		/**
		 * 不是静态文件，才进行处理
		 */
		if (!isStatic(s)) {
			if (s.lastIndexOf(".html") != -1) {
				s = s.substring(0, s.indexOf(".html"));
			} else if (s.lastIndexOf(".htm") != -1) {
				s = s.substring(0, s.indexOf(".htm"));
			}
		}
		next.handle(s, httpServletRequest, httpServletResponse, booleans);
	}

	/**
	 * 判断是否是静态文件
	 * 
	 * 2015年6月6日 上午2:37:48 flyfox 330627517@qq.com
	 * 
	 * @param s
	 * @return
	 */
	private boolean isStatic(String s) {
		String suff = Config.getStr("HTMLHANDLER.SUFF");
		if (StrUtils.isEmpty(suff)) {
			return false;
		}
		String[] suffs = suff.split(",");
		for (String suffStr : suffs) {
			if (s.indexOf(suffStr) >= 0) {
				return true;
			}
		}

		return false;
	}
}

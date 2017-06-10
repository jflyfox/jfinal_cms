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
import com.jfinal.kit.StrKit;

public class CurrentPathHandler extends Handler {
	private String currentPathName;

	public CurrentPathHandler() {
		currentPathName = "CURRENT_PATH";
	}

	public CurrentPathHandler(String currentPathName) {
		if (StrKit.isBlank(currentPathName)) {
			throw new IllegalArgumentException("contextPathName can not be blank.");
		} else {
			this.currentPathName = currentPathName;
			return;
		}
	}

	public void handle(String target, HttpServletRequest request, HttpServletResponse response, boolean isHandled[]) {
		String currentPath = request.getScheme() + "://" + request.getServerName() //
				+ ":" + request.getServerPort();
		currentPath += request.getRequestURI(); // 参数
		String currentPathParam = currentPath;
		if (request.getQueryString() != null) // 判断请求参数是否为空
			currentPathParam += "?" + request.getQueryString(); // 参数
		// 无参数
		request.setAttribute(currentPathName, currentPath);
		// 有参数
		request.setAttribute(currentPathName + "_PARAM", currentPathParam);
		next.handle(target, request, response, isHandled);
	}

}

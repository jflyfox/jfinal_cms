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

public class BasePathHandler extends Handler {
	private String basePathName;

	public BasePathHandler() {
		basePathName = "BASE_PATH";
	}

	public BasePathHandler(String contextPathName) {
		if (StrKit.isBlank(contextPathName)) {
			throw new IllegalArgumentException("contextPathName can not be blank.");
		} else {
			this.basePathName = contextPathName;
			return;
		}
	}

	public void handle(String target, HttpServletRequest request, HttpServletResponse response, boolean isHandled[]) {
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() //
				+ ":" + request.getServerPort() + path + "/";
		request.setAttribute(basePathName, basePath);
		request.setAttribute("ctx", basePath);
		next.handle(target, request, response, isHandled);
	}

}

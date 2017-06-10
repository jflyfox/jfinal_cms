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

package com.jflyfox.jfinal.component.oauth;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Map;

import com.jflyfox.jfinal.component.oauth.util.HttpKit;

/**
 * Oauth 授权
 */
public class Oauth implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String clientId;
	private String clientSecret;
	private String redirectUri;

	public Oauth() {
	}

	protected String getAuthorizeUrl(String authorize, Map<String, String> params) throws UnsupportedEncodingException {
		return HttpKit.initParams(authorize, params);
	}

	protected String doPost(String url, Map<String, String> params) throws IOException, KeyManagementException,
			NoSuchAlgorithmException, NoSuchProviderException {
		return HttpKit.post(url, params);
	}

	protected String doGet(String url, Map<String, String> params) throws IOException, KeyManagementException,
			NoSuchAlgorithmException, NoSuchProviderException {
		return HttpKit.get(url, params);
	}

	protected String doGetWithHeaders(String url, Map<String, String> headers) throws IOException,
			KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException {
		return HttpKit.get(url, null, headers);
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getRedirectUri() {
		return redirectUri;
	}

	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}
}

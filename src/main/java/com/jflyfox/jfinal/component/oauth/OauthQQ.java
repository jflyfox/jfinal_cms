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
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.log.Log;
import com.jflyfox.jfinal.component.oauth.util.OathConfig;
import com.jflyfox.jfinal.component.oauth.util.TokenUtil;
import com.jflyfox.util.StrUtils;

/**
 * OauthQQ
 */
public class OauthQQ extends Oauth{ 

	private static final long serialVersionUID = 1L;
    private static final Log LOGGER = Log.getLog(OauthQQ.class);
    
    private static final String AUTH_URL = "https://graph.qq.com/oauth2.0/authorize";
    private static final String TOKEN_URL = "https://graph.qq.com/oauth2.0/token";
    private static final String TOKEN_INFO_URL = "https://graph.qq.com/oauth2.0/me";
    private static final String USER_INFO_URL = "https://graph.qq.com/user/get_user_info";

    private static OauthQQ oauthQQ = new OauthQQ();

    /**
     * 用于链式操作
     * @return
     */
    public static OauthQQ me() {
        return oauthQQ;
    }

    public OauthQQ() {
        setClientId(OathConfig.getValue("openid_qq"));
        setClientSecret(OathConfig.getValue("openkey_qq"));
        setRedirectUri(OathConfig.getValue("redirect_qq"));
    }

    /**
     * 获取授权url
     * DOC：http://wiki.connect.qq.com/%E4%BD%BF%E7%94%A8authorization_code%E8%8E%B7%E5%8F%96access_token
     * @param @return    设定文件
     * @return String    返回类型
     * @throws UnsupportedEncodingException 
     */ 
    public String getAuthorizeUrl(String state) throws UnsupportedEncodingException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("response_type", "code");
        params.put("client_id", getClientId());
        params.put("redirect_uri", getRedirectUri());
        if (StrUtils.isNotEmpty(state)) {
            params.put("state", state); //OAuth2.0标准协议建议，利用state参数来防止CSRF攻击。可存储于session或其他cache中
        }
        return super.getAuthorizeUrl(AUTH_URL, params);
    }

    /**
     * @throws NoSuchProviderException 
     * @throws NoSuchAlgorithmException 
     * @throws KeyManagementException 
     * 获取token
     * @param @param code
     * @param @return    设定文件
     * @return String    返回类型
     * @throws
     */
    public String getTokenByCode(String code) throws IOException, KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("code", code);
        params.put("client_id", getClientId());
        params.put("client_secret", getClientSecret());
        params.put("grant_type", "authorization_code");
        params.put("redirect_uri", getRedirectUri());
        // access_token=FE04************************CCE2&expires_in=7776000
        String token = TokenUtil.getAccessToken(super.doGet(TOKEN_URL, params));
        LOGGER.debug(token);
        return token;
    }

    /**
     * @throws NoSuchProviderException 
     * @throws NoSuchAlgorithmException 
     * @throws KeyManagementException 
     * @throws IOException 
     * 获取TokenInfo
     * @return    设定文件
     * @return String    返回类型
     * @throws
     */
    public String getTokenInfo(String accessToken) throws IOException, KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("access_token", accessToken);
        // callback( {"client_id":"YOUR_APPID","openid":"YOUR_OPENID"} );
        String openid = TokenUtil.getOpenId(super.doGet(TOKEN_INFO_URL, params));
        LOGGER.debug(openid);
        return openid;
    }
    
    /**
     * 获取用户信息
     * DOC：http://wiki.connect.qq.com/get_user_info
     * @param accessToken
     * @param uid
     * @return    设定文件
     * @return String    返回类型
     * @throws NoSuchProviderException 
     * @throws NoSuchAlgorithmException 
     * @throws KeyManagementException 
     * @throws IOException 
     */
    public String getUserInfo(String accessToken, String uid) throws IOException, KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("access_token", accessToken);
        params.put("oauth_consumer_key", getClientId());
        params.put("openid", uid);
        params.put("format", "json");
        // // {"ret":0,"msg":"","nickname":"YOUR_NICK_NAME",...}
        String userinfo = super.doGet(USER_INFO_URL, params);
        LOGGER.debug(userinfo);
        return userinfo;
    }
    
    /**
     * @throws NoSuchProviderException 
     * @throws NoSuchAlgorithmException 
     * @throws KeyManagementException 
     * 根据code一步获取用户信息
     * @param @param args    设定文件
     * @return void    返回类型
     * @throws
     */
    public JSONObject getUserInfoByCode(String code) throws IOException, KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException{
        String accessToken = getTokenByCode(code);
        if (StrUtils.isEmpty(accessToken)) {
            return null;
        }
        String openId = getTokenInfo(accessToken);
        if (StrUtils.isEmpty(openId)) {
            return null;
        }
        JSONObject dataMap = JSON.parseObject(getUserInfo(accessToken, openId));
        dataMap.put("openid", openId);
        dataMap.put("access_token", accessToken);
        LOGGER.debug(dataMap.toJSONString());
        return dataMap;
    }
}

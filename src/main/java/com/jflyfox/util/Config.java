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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

public class Config {
	private final static URL classPathUrl = Config.class.getResource("/");
	private final static String classPath = new File(classPathUrl.getFile()).getPath();
	private static String configPath = "/conf/";
	private static Map<String, String> configMap = new HashMap<String, String>();

	static {
		setConfigMap();
	}
	
	public static String getStr(String key) {
		if (configMap.size() < 0) {
			return null;
		}
		return configMap.get(key);
	}

	public static int getToInt(String key) {
		String val = getStr(key);
		return NumberUtils.parseInt(val);
	}

	public static long getToLong(String key) {
		String val = getStr(key);
		return NumberUtils.parseLong(val);
	}
	
	public static double getToDbl(String key) {
		String val = getStr(key);
		return NumberUtils.parseDbl(val);
	}

	public static boolean getToBoolean(String key) {
		String val = getStr(key);
		try {
			return Boolean.valueOf(val);
		} catch (Exception e) {
			return false;
		}
	}

	
	/**
	 * 修改目录，配置重构
	 * 
	 * 2016年6月2日 下午3:27:29
	 * flyfox 330627517@qq.com
	 * @param configPath
	 */
	public static void rebuild(String configPath){
		Config.configPath = configPath;
		rebuild();
	}
	
	/**
	 * 配置重构
	 * 
	 * 2016年6月2日 下午3:27:13
	 * flyfox 330627517@qq.com
	 */
	public static void rebuild(){
		setConfigMap();
	}

	public static void test() {
		for (String key : configMap.keySet()) {
			System.out.println(key + "=" + configMap.get(key));
		}
	}
	
	private static void setConfigMap() {
		String filePath = classPath + configPath;
		filePath = PathUtils.rebuild(filePath);
		List<String> list = findFiles(filePath);
		
		Map<String, String> tmpConfigMap = new HashMap<String, String>();
		for (String configName : list) {
			Properties props = getProperties(filePath + configName);
			Iterator<Entry<Object, Object>> it = props.entrySet().iterator();
			while (it.hasNext()) {
				Entry<Object, Object> entry = it.next();
				String key = String.valueOf(entry.getKey());
				String value = String.valueOf(entry.getValue());
				if (!tmpConfigMap.containsKey(key)) {
					tmpConfigMap.put(key, value);
				} else {
					System.err.println("CONFIG EEOR:key(" + key + ") is exist;");
				}
			}
		}
		
		Config.configMap = tmpConfigMap;
	}

	/**
	 * 获取Properties文件
	 * 
	 * 2014年7月5日 上午12:23:14 flyfox 330627517@qq.com
	 * 
	 * @param file
	 * @return
	 */
	private static Properties getProperties(String fileName) {
		Properties props = new Properties();
		try {
			java.io.InputStream propFile = new FileInputStream(fileName);
			props.load(propFile);
		} catch (IOException e) {
			System.err.println("file read fail:" + fileName);
			e.printStackTrace();
		}
		return props;
	}

	/**
	 * 查找当前文件下所有properties文件
	 * 
	 * @param baseDirName
	 *            查找的文件夹路径
	 */
	private static List<String> findFiles(String baseDirName) {
		List<String> configFiles = new ArrayList<String>();
		// 判断目录是否存在
		File baseDir = new File(baseDirName);
		if (!baseDir.exists() || !baseDir.isDirectory()) {
			System.err.println("search error：" + baseDirName + " is not a dir！");
		} else {
			String[] filelist = baseDir.list();
			for (String fileName : filelist) {
				if (fileName.endsWith("properties")) {
					configFiles.add(fileName);
				}
			}
		}
		return configFiles;
	}

}

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

package com.jflyfox.util.annotation;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import com.jflyfox.util.PathUtils;

/**
 * 类扫描：借鉴Jfinal ext
 * 
 * 2014年6月9日 下午12:39:43 flyfox 330627517@qq.com
 */
public class ClassSearcher {

	static URL classPathUrl = ClassSearcher.class.getResource("/");
	static String lib = new File(classPathUrl.getFile()).getParent() + "/lib/";

	public static <T> List<Class<? extends T>> findInClasspathAndJars(Class<T> clazz, List<String> includeJars) {
		String path = PathUtils.rebuild(classPathUrl.getFile());
		List<String> classFileList = findFiles(path, "*.class");
		String libPath = PathUtils.rebuild(lib);
		classFileList.addAll(findjarFiles(libPath, includeJars));
		return extraction(clazz, classFileList);
	}

	public static <T> List<Class<? extends T>> findInClasspath(Class<T> clazz) {
		String file = PathUtils.rebuild(classPathUrl.getFile());
		List<String> classFileList = findFiles(file, "*.class");
		return extraction(clazz, classFileList);
	}

	/**
	 * 递归查找文件
	 * 
	 * @param baseDirName
	 *            查找的文件夹路径
	 * @param targetFileName
	 *            需要查找的文件名
	 */
	private static List<String> findFiles(String baseDirName, String targetFileName) {
		/**
		 * 算法简述： 从某个给定的需查找的文件夹出发，搜索该文件夹的所有子文件夹及文件，
		 * 若为文件，则进行匹配，匹配成功则加入结果集，若为子文件夹，则进队列。 队列不空，重复上述操作，队列为空，程序结束，返回结果。
		 */
		List<String> classFiles = new ArrayList<String>();
		String tempName = null;
		// 判断目录是否存在
		File baseDir = new File(baseDirName);
		if (!baseDir.exists() || !baseDir.isDirectory()) {
			System.err.println("search error：" + baseDirName + " is not a dir！");
		} else {
			String[] filelist = baseDir.list();
			for (int i = 0; i < filelist.length; i++) {
				File readfile = new File(baseDirName + File.separator + filelist[i]);
				if (readfile.isDirectory()) {
					classFiles.addAll(findFiles(baseDirName + File.separator + filelist[i], targetFileName));
				} else {
					tempName = readfile.getName();
					if (ClassSearcher.wildcardMatch(targetFileName, tempName)) {
						String classname = "";
						String tem = readfile.getAbsoluteFile().toString();
						tem = PathUtils.rebuild(tem);
						String[] rootClassPath = new String[] { //
						"/classes" // java web
								, "/test-classes" // maven test
								, "/bin" // java project
						};

						// scan class，set root path
						for (int j = 0; j < rootClassPath.length; j++) {
							String tmp = rootClassPath[j];
							if (tem.indexOf(tmp) >= 0) {
								classname = tem.substring(tem.indexOf(tmp) + tmp.length(), tem.indexOf(".class"));
								break;
							}
						}

						if (classname.startsWith("/")) {
							classname = classname.substring(classname.indexOf("/") + 1);
						}
						classname = className(classname, "/classes");
						classFiles.add(classname);
					}
				}
			}
		}
		return classFiles;
	}

	/**
	 * 查找jar包中的class
	 * 
	 * @param baseDirName
	 *            jar路径
	 * @param includeJars
	 * @param jarFileURL
	 *            jar文件地址 <a href="http://my.oschina.net/u/556800"
	 *            target="_blank" rel="nofollow">@return</a>
	 */
	public static List<String> findjarFiles(String baseDirName, final List<String> includeJars) {
		List<String> classFiles = new ArrayList<String>();
		try {
			// 判断目录是否存在
			File baseDir = new File(baseDirName);
			if (!baseDir.exists() || !baseDir.isDirectory()) {
				System.out.println("####warn####file serach error：" + baseDirName + " is not a dir！");
			} else {
				String[] filelist = baseDir.list(new FilenameFilter() {
					public boolean accept(File dir, String name) {
						return includeJars.contains(name);
					}
				});
				for (int i = 0; i < filelist.length; i++) {
					JarFile localJarFile = new JarFile(new File(baseDirName + File.separator + filelist[i]));
					Enumeration<JarEntry> entries = localJarFile.entries();
					while (entries.hasMoreElements()) {
						JarEntry jarEntry = entries.nextElement();
						String entryName = jarEntry.getName();
						if (!jarEntry.isDirectory() && entryName.endsWith(".class")) {
							String className = entryName.replaceAll("/", ".").substring(0, entryName.length() - 6);
							classFiles.add(className);
						}
					}
					localJarFile.close();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return classFiles;

	}

	@SuppressWarnings("unchecked")
	private static <T> List<Class<? extends T>> extraction(Class<T> clazz, List<String> classFileList) {
		List<Class<? extends T>> classList = new ArrayList<Class<? extends T>>();
		for (String classFile : classFileList) {
			Class<?> classInFile = null;
			try {
				classInFile = Class.forName(classFile);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			if (clazz.isAssignableFrom(classInFile) && clazz != classInFile) {
				classList.add((Class<? extends T>) classInFile);
			}
		}

		return classList;
	}

	private static String className(String classFile, String pre) {
		String objStr = PathUtils.rebuild(classFile);
		return objStr.replaceAll("/", ".");
	}

	/**
	 * 通配符匹配
	 * 
	 * @param pattern
	 *            通配符模式
	 * @param str
	 *            待匹配的字符串 <a href="http://my.oschina.net/u/556800"
	 *            target="_blank" rel="nofollow">@return</a>
	 *            匹配成功则返回true，否则返回false
	 */
	private static boolean wildcardMatch(String pattern, String str) {
		int patternLength = pattern.length();
		int strLength = str.length();
		int strIndex = 0;
		char ch;
		for (int patternIndex = 0; patternIndex < patternLength; patternIndex++) {
			ch = pattern.charAt(patternIndex);
			if (ch == '*') {
				// 通配符星号*表示可以匹配任意多个字符
				while (strIndex < strLength) {
					if (wildcardMatch(pattern.substring(patternIndex + 1), str.substring(strIndex))) {
						return true;
					}
					strIndex++;
				}
			} else if (ch == '?') {
				// 通配符问号?表示匹配任意一个字符
				strIndex++;
				if (strIndex > strLength) {
					// 表示str中已经没有字符匹配?了。
					return false;
				}
			} else {
				if ((strIndex >= strLength) || (ch != str.charAt(strIndex))) {
					return false;
				}
				strIndex++;
			}
		}
		return strIndex == strLength;
	}

}

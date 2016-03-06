/**
 * Copyright 2015-2025 FLY的狐狸(email:jflyfox@sina.com qq:330627517).
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

package com.jflyfox.modules.filemanager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import com.jflyfox.util.FileUtils;

public class FileManagerUtils {

	private static final String ENCODE = "UTF-8";

	public static String readString(String path) throws IOException {
		byte[] content = FileUtils.read(path);
		return content == null ? "" : new String(content, ENCODE);
	}

	public static void writeString(String path, String content) throws IOException {
		FileUtils.write(path, content == null ? new byte[0] : content.getBytes(ENCODE));
	}

	/**
	 * 拷贝文件
	 * 
	 * @param src
	 * @param target
	 * @throws IOException
	 */
	public static void copyFile(File src, File target) throws IOException {
		FileChannel in = null;
		FileChannel out = null;
		try {
			in = new FileInputStream(src).getChannel();
			out = new FileOutputStream(target).getChannel();
			out.transferFrom(in, 0, src.length());
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (out != null)
					out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static String encodeContent(String content) {
		content = content.replaceAll("</textarea", "&lt;/textarea");
		return content;
	}

	public static String decodeContent(String content) {
		content = content.replaceAll("&lt;/textarea", "</textarea");
		return content;
	}

	public static String rebulid(String currentPath) {
		String filePath = currentPath;
		filePath = filePath.replaceAll("\\\\", "\\/");
		filePath = filePath.replaceAll("//", "/");
		return filePath;
	}
}

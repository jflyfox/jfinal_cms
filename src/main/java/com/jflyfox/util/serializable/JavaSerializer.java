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

package com.jflyfox.util.serializable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class JavaSerializer implements Serializer {

	public String name(){
		return "java";
	}
	
	public byte[] serialize(Object obj) throws IOException {
		ObjectOutputStream oos = null;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(obj);
			return baos.toByteArray();
		} finally {
			if (oos != null)
				try {
					oos.close();
				} catch (IOException e) {
				}
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T deserialize(byte[] bits) throws IOException {
		if (bits == null || bits.length == 0)
			return null;
		ObjectInputStream ois = null;
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(bits);
			ois = new ObjectInputStream(bais);
			return (T)ois.readObject();
		} catch (ClassNotFoundException e) {
			throw new IOException("没有找到类型",e);
		} finally {
			try {
				if (ois != null)
					ois.close();
			} catch (IOException e) {
				throw new IOException("deserialize关闭失败", e);
			}
		}
	}

}

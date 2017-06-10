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

package com.jflyfox.util.encrypt;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;

import javax.crypto.Cipher;

public class RSAUtils {
	/** 指定加密算法为DESede */
	private static String ALGORITHM = "RSA";
	/** 指定key的大小 */
	public static int KEYSIZE = 512;
	/** 编码 */
	public static String ENCODE = "UTF-8";
	/** 指定公钥存放文件 */
	public static String PUBLIC_KEY_FILE = "e:/1.txt";
	/** 指定私钥存放文件 */
	public static String PRIVATE_KEY_FILE = "e:/2.txt";

	/**
	 * 生成密钥对
	 */

	private static void generateKeyPair() throws Exception {
		/** RSA算法要求有一个可信任的随机数源 */
		SecureRandom sr = new SecureRandom();
		/** 为RSA算法创建一个KeyPairGenerator对象 */
		KeyPairGenerator kpg = KeyPairGenerator.getInstance(ALGORITHM);
		/** 利用上面的随机数据源初始化这个KeyPairGenerator对象 */
		kpg.initialize(KEYSIZE, sr);
		/** 生成密匙对 */
		KeyPair kp = kpg.generateKeyPair();
		/** 得到公钥 */
		Key publicKey = kp.getPublic();
		/** 得到私钥 */
		Key privateKey = kp.getPrivate();
		/** 用对象流将生成的密钥写入文件 */
		ObjectOutputStream oos1 = new ObjectOutputStream(new FileOutputStream(PUBLIC_KEY_FILE));
		ObjectOutputStream oos2 = new ObjectOutputStream(new FileOutputStream(PRIVATE_KEY_FILE));
		oos1.writeObject(publicKey);
		oos2.writeObject(privateKey);
		/** 清空缓存，关闭文件输出流 */
		oos1.close();
		oos2.close();
	}

	/**
	 * 加密方法 source： 源数据
	 */
	public static String encrypt(String source) throws Exception {
		// generateKeyPair();
		/** 将文件中的公钥对象读出 */
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PUBLIC_KEY_FILE));
		Key key = (Key) ois.readObject();
		ois.close();
		/** 得到Cipher对象来实现对源数据的RSA加密 */
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] b = source.getBytes();
		/** 执行加密操作 */
		byte[] b1 = cipher.doFinal(b);
		String result = new String(Base64.encodeBase64(b1), ENCODE);
		return result;
	}

	/**
	 * 解密算法 cryptograph:密文
	 */
	public static String decrypt(String cryptograph) throws Exception {
		/** 将文件中的私钥对象读出 */
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PRIVATE_KEY_FILE));
		Key key = (Key) ois.readObject();
		ois.close();

		/** 得到Cipher对象对已用公钥加密的数据进行RSA解密 */
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] b1 = Base64.decodeBase64(cryptograph.getBytes(ENCODE));
		/** 执行解密操作 */
		byte[] b = cipher.doFinal(b1);
		return new String(b);
	}

	public static void main(String[] args) throws Exception {
		String source = "admin123";// 要加密的字符串
		// 第一次使用
		RSAUtils.generateKeyPair();

		String cryptograph = encrypt(source);// 生成的密文
		System.out.println(cryptograph);

		String target = decrypt(cryptograph);// 解密密文
		System.out.println(target);
	}
}
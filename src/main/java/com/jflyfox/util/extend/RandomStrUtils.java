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

package com.jflyfox.util.extend;

import java.util.Random;

public class RandomStrUtils {

	private static final Random RANDOM = new Random();

	public static String random(int count) {
		return random(count, false, false);
	}

	public static String randomAscii(int count) {
		return random(count, 32, 127, false, false);
	}

	public static String randomAlphabetic(int count) {
		return random(count, true, false);
	}

	public static String randomAlphanumeric(int count) {
		return random(count, true, true);
	}

	public static String randomNumeric(int count) {
		return random(count, false, true);
	}

	public static String random(int count, boolean letters, boolean numbers) {
		return random(count, 0, 0, letters, numbers);
	}

	public static String random(int count, int start, int end, boolean letters, boolean numbers) {
		return random(count, start, end, letters, numbers, null, RANDOM);
	}

	public static String random(int count, int start, int end, boolean letters, boolean numbers, char chars[]) {
		return random(count, start, end, letters, numbers, chars, RANDOM);
	}

	public static String random(int count, int start, int end, boolean letters, boolean numbers, char chars[],
			Random random) {
		if (count == 0)
			return "";
		if (count < 0)
			throw new IllegalArgumentException((new StringBuilder()).append("Requested random string length ")
					.append(count).append(" is less than 0.").toString());
		if (chars != null && chars.length == 0)
			throw new IllegalArgumentException("The chars array must not be empty");
		if (start == 0 && end == 0) {
			if (chars != null)
				end = chars.length;
			else if (!letters && !numbers) {
				end = 2147483647;
			} else {
				end = 123;
				start = 32;
			}
		} else if (end <= start)
			throw new IllegalArgumentException((new StringBuilder()).append("Parameter end (").append(end)
					.append(") must be greater than start (").append(start).append(")").toString());
		char buffer[] = new char[count];
		int gap = end - start;
		while (count-- != 0) {
			char ch;
			if (chars == null)
				ch = (char) (random.nextInt(gap) + start);
			else
				ch = chars[random.nextInt(gap) + start];
			if (letters && Character.isLetter(ch) || numbers && Character.isDigit(ch) || !letters && !numbers) {
				if (ch >= '\uDC00' && ch <= '\uDFFF') {
					if (count == 0) {
						count++;
					} else {
						buffer[count] = ch;
						count--;
						buffer[count] = (char) (55296 + random.nextInt(128));
					}
				} else if (ch >= '\uD800' && ch <= '\uDB7F') {
					if (count == 0) {
						count++;
					} else {
						buffer[count] = (char) (56320 + random.nextInt(128));
						count--;
						buffer[count] = ch;
					}
				} else if (ch >= '\uDB80' && ch <= '\uDBFF')
					count++;
				else
					buffer[count] = ch;
			} else {
				count++;
			}
		}
		return new String(buffer);
	}

	public static String random(int count, String chars) {
		if (chars == null)
			return random(count, 0, 0, false, false, null, RANDOM);
		else
			return random(count, chars.toCharArray());
	}

	public static String random(int count, char chars[]) {
		if (chars == null)
			return random(count, 0, 0, false, false, null, RANDOM);
		else
			return random(count, 0, chars.length, false, false, chars, RANDOM);
	}

}

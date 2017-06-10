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

package com.jflyfox.jfinal.template;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class TemplateFunctions {

	// //////////////////////////////////产生随机数//////////////////////////////////
	private static AtomicInteger bgNum = new AtomicInteger(0);

	public static int bgNum(int num) {
		if (bgNum.get() > num) {
			bgNum.set(0);
		}
		bgNum.incrementAndGet();
		return bgNum.get();
	}

	public static int randomInt() {
		return new Random().nextInt();
	}

	public static int randomInt(int num) {
		return new Random().nextInt(num);
	}

}

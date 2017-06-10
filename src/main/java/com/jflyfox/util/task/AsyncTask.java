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

package com.jflyfox.util.task;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 异步任务执行
 * 
 * 2016年1月14日 下午10:48:22 flyfox 330627517@qq.com
 */
public abstract class AsyncTask implements Runnable {

	private static AtomicLong createdNum = new AtomicLong(0);
	private static AtomicLong finishedNum = new AtomicLong(0);
	private static AtomicLong errorNum = new AtomicLong(0);

	private static AtomicLong totalCostTime = new AtomicLong(0);

	public void run() {
		long startTime = System.currentTimeMillis();
		boolean withError = false;
		try {
			customRun();
		} catch (Exception e) {
			withError = true;
			errorNum.incrementAndGet();
			String threadName = Thread.currentThread().getName();
			System.err.println("[thread:" + threadName + "][handleTaskError]");
			e.printStackTrace();
		} finally {
			if (!withError)
				finishedNum.addAndGet(1);

			totalCostTime.addAndGet((System.currentTimeMillis() - startTime));
		}
	}

	public abstract void customRun();

	public static void addCreateNum() {
		createdNum.incrementAndGet();
	}

	public static void addFinishNum() {
		finishedNum.incrementAndGet();
	}

	public static long getAddNum() {
		return createdNum.get();
	}

	public static long getFinishNum() {
		return finishedNum.get();
	}

	public static long getErrorNum() {
		return errorNum.get();
	}

	public static long getTotalExecuteTime() {
		return totalCostTime.get();
	}
}

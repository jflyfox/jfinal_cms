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

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 任务工场
 * 
 * 2016年1月14日 下午10:47:12 flyfox 330627517@qq.com
 */
public class AsyncTaskWorker extends ScheduledThreadPoolExecutor {
	private static final String threadNamePrefix = "AsyncTaskWorkerThread";
	private static final AtomicInteger threadNum = new AtomicInteger(1);

	private static ThreadFactory workThreadFactory = new ThreadFactory() {

		public Thread newThread(Runnable r) {
			Thread t = new Thread(r, threadNamePrefix + "-" + threadNum.getAndIncrement());
			if (!t.isDaemon()) {
				t.setDaemon(true);
			}
			return t;
		}
	};

	public AsyncTaskWorker(int corePoolSize) {
		super(corePoolSize, workThreadFactory);
	}

	@Override
	protected void beforeExecute(Thread t, Runnable r) {
		AsyncTask.addCreateNum();
		super.beforeExecute(t, r);
	}

}

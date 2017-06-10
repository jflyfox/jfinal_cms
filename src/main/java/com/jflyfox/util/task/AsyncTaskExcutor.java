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

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * 任务执行器
 * 
 * 2016年1月14日 下午10:47:47 flyfox 330627517@qq.com
 */
public class AsyncTaskExcutor {
	private static AsyncTaskExcutor instance = new AsyncTaskExcutor();

	public static AsyncTaskExcutor getInstance() {
		return instance;
	}

	private ScheduledExecutorService schedueExecutor = null;

	private AsyncTaskExcutor() {
		int processNum = Runtime.getRuntime().availableProcessors();
		schedueExecutor = new AsyncTaskWorker(processNum + 1);
	}

	public void postTask(AsyncTask t) {
		schedueExecutor.submit(t);
	}

	public ScheduledFuture<?> postTask(AsyncTask t, long delay, long interval) {
		return schedueExecutor.scheduleWithFixedDelay(t, delay, interval, TimeUnit.MILLISECONDS);
	}

	public ScheduledFuture<?> postTask(AsyncTask t, long delay) {
		return schedueExecutor.schedule(t, delay, TimeUnit.MILLISECONDS);
	}

}

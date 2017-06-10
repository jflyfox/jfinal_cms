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
package com.jflyfox.jfinal.component.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface ModelBind {
	
	/**
	 * 表名
	 * 
	 * 2016年1月3日 下午3:29:26
	 * flyfox 330627517@qq.com
	 * @return
	 */
	String table();

	/**
	 * 主键
	 * 
	 * 2016年1月3日 下午3:29:21
	 * flyfox 330627517@qq.com
	 * @return
	 */
	String key() default "id";
	
	/**
	 * 数据库名称
	 * 
	 * 2016年1月3日 下午3:29:10
	 * flyfox 330627517@qq.com
	 * @return
	 */
	String configName()  default "main";
}

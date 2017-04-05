package com.jflyfox.system.file.service;

import com.jflyfox.system.file.model.FileUploadBean;

public interface IFileBackup {

	/**
	 * 文件备份
	 * 
	 * 2017年4月5日 上午3:04:20 flyfox 369191470@qq.com
	 * 
	 * @param fileBean
	 * @return
	 */
	boolean backup(FileUploadBean fileBean);

	/**
	 * 文件恢复
	 * 
	 * 2017年4月5日 下午4:30:15 flyfox 369191470@qq.com
	 * 
	 * @return
	 */
	boolean restore(String projectPath);
}

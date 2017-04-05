package com.jflyfox.system.file.service.impl;

import java.io.File;

import com.jflyfox.system.file.model.FileUploadBean;
import com.jflyfox.system.file.service.IFileBackup;
import com.jflyfox.system.file.util.AliyunOSSUtils;
import com.jflyfox.system.file.util.FileUploadUtils;

/**
 * 阿里云OSS备份
 * 
 * 2017年4月5日 上午3:07:28 flyfox 369191470@qq.com
 */
public class AliOssBackup implements IFileBackup {

	private AliyunOSSUtils ossUtils = new AliyunOSSUtils();

	public boolean backup(FileUploadBean fileBean) {
		String srcPath = (fileBean.getPath().startsWith("/") ? fileBean.getPath().substring(1) : fileBean.getPath());
		ossUtils.uploadFile(srcPath, fileBean.getFactpath());
		return true;
	}

	public boolean restore(String projectPath) {
		String srcPath = (projectPath.startsWith("/") ? "" : "/") + projectPath;
		String webRootPath = FileUploadUtils.getRootPath(); // 项目路径
		String projectFactPath = FileUploadUtils.rebuild(webRootPath + srcPath);
		File destFile = new File(projectFactPath);

		if (destFile.exists()) {
			return true;
		}

		ossUtils.downloadFile(projectPath, projectFactPath);

		return true;
	}
}

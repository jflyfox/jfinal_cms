package com.jflyfox.system.file.service.impl;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.jflyfox.system.config.ConfigCache;
import com.jflyfox.system.file.model.FileUploadBean;
import com.jflyfox.system.file.service.IFileBackup;
import com.jflyfox.system.file.util.FileUploadUtils;
import com.jflyfox.util.StrUtils;

/**
 * 文件系统备份
 * 
 * 2017年4月5日 上午3:06:20 flyfox 369191470@qq.com
 */
public class FileManangerBackup implements IFileBackup {

	public boolean backup(FileUploadBean fileBean) {
		String backupPath = ConfigCache.getValue("backup.filemanger.path"); // 备份路径
		String webRootPath = FileUploadUtils.getRootPath(); // 项目路径
		// 文件路径处理
		String newFilePath = FileUploadUtils.rebuild(fileBean.getFactpath()).replace(webRootPath, "");
		newFilePath = (newFilePath.startsWith("/") ? "" : "/") + newFilePath;

		File srcFile = new File(fileBean.getFactpath());
		String destPath = FileUploadUtils.rebuild(backupPath + newFilePath);
		File destFile = new File(destPath);

		try {
			if (destFile.exists()) {
				destFile.delete();
			}
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean restore(String projectPath) {
		if (StrUtils.isEmpty(projectPath)) {
			return false;
		}

		String srcPath = (projectPath.startsWith("/") ? "" : "/") + projectPath;

		String webRootPath = FileUploadUtils.getRootPath(); // 项目路径
		String projectFactPath = FileUploadUtils.rebuild(webRootPath + srcPath);
		File destFile = new File(projectFactPath);

		String backupPath = ConfigCache.getValue("backup.filemanger.path"); // 备份路径
		String srcFactPath = FileUploadUtils.rebuild(backupPath + srcPath);
		File srcFile = new File(srcFactPath);
		// 已经存在不用恢复
		if (destFile.exists()) {
			return true;
		}

		// 备份文件都不存在，还恢复个P啊
		if (!srcFile.exists()) {
			return false;
		}

		try {
			// 复制恢复
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return true;
	}
}

package com.jflyfox.system.file.service;

import java.io.File;
import java.security.SecureRandom;

import com.jfinal.log.Log;
import com.jflyfox.jfinal.base.BaseService;
import com.jflyfox.system.config.ConfigCache;
import com.jflyfox.system.file.model.FileUploadBean;
import com.jflyfox.system.file.model.SysFileUpload;
import com.jflyfox.system.file.service.impl.AliOssBackup;
import com.jflyfox.system.file.service.impl.EmptyBackUp;
import com.jflyfox.system.file.service.impl.FileManangerBackup;
import com.jflyfox.system.file.util.FileUploadConstants;
import com.jflyfox.system.file.util.FileUploadUtils;
import com.jflyfox.util.DateUtils;

/**
 * 文件上传处理
 * 
 * 2017年4月5日 上午2:57:03 flyfox 369191470@qq.com
 */
public class FileUploadService extends BaseService {

	private static final Log log = Log.getLog(FileUploadService.class);

	/**
	 * 获取备份策略
	 * 
	 * 2017年4月5日 上午3:32:10 flyfox 369191470@qq.com
	 * 
	 * @return
	 */
	public IFileBackup getFileBackup() {
		IFileBackup fileBackup = null;
		String backupName = ConfigCache.getValue("backup.name");
		if ("filemanger".equals(backupName)) {
			fileBackup = new FileManangerBackup(); // 文件系统备份
		} else if ("alioss".equals(backupName)) {
			fileBackup = new AliOssBackup(); // 阿里云OSS备份
		} else if ("empty".equals(backupName)) {
			fileBackup = new EmptyBackUp(); // 关闭备份
		} else {
			throw new RuntimeException("file backup get fail.");
		}
		return fileBackup;
	}

	/**
	 * 恢复备份文件
	 * 
	 * 2017年4月5日 下午10:01:43 flyfox 369191470@qq.com
	 * 
	 * @param projectPath
	 * @return
	 */
	public boolean restore(String projectPath) {
		return getFileBackup().restore(projectPath);
	}

	/**
	 * 上传文件处理
	 * 
	 * 2017年4月5日 上午3:43:33 flyfox 369191470@qq.com
	 * 
	 * @param projectStorePath
	 * @param uploadFile
	 * @param userid
	 * @return
	 */
	public FileUploadBean uploadHandle(String projectStorePath, File uploadFile, int userid) {
		return uploadHandle(projectStorePath, uploadFile, FileUploadConstants.BUSINESS_TYPE_RECORD, userid);
	}

	/**
	 * 上传文件处理
	 * 
	 * 2017年4月5日 上午3:43:27 flyfox 369191470@qq.com
	 * 
	 * @param projectStorePath
	 *            项目存储路径
	 * @param uploadFile
	 *            文件
	 * @param businessType
	 *            业务类型
	 * @param userid
	 *            处理人
	 * @return
	 */
	public FileUploadBean uploadHandle(String projectStorePath, File uploadFile, int businessType, int userid) {
		FileUploadBean bean = null;
		String webRootPath = FileUploadUtils.getRootPath() + "/";
		String storePath = webRootPath + projectStorePath;
		try {
			// 文件重命名，避免中文保证唯一
			bean = rename(storePath, uploadFile);
			// 保存记录
			if (!save(bean, businessType, userid)) {
				log.error("FileUploadService uploadHandle save fail.");
				return bean;
			}

			// 文件备份
			getFileBackup().backup(bean);
		} catch (Exception e) {
			log.error("FileUploadService uploadHandle fail.", e);
		}
		return bean;
	}

	/**
	 * 重命名
	 * 
	 * 2015年9月25日 下午10:37:55 flyfox 369191470@qq.com
	 * 
	 * @param newPath
	 *            重建路径
	 * @param file
	 *            文件
	 * @return
	 */
	public FileUploadBean rename(String newPath, File file) {
		if (!file.exists()) {
			throw new RuntimeException("file is not exist , filepath:" + file.getAbsolutePath());
		}

		newPath = FileUploadUtils.rebuild(newPath);
		File uploadPath = new File(newPath);
		if (!uploadPath.exists()) {
			uploadPath.mkdirs();
		}

		String originalName = file.getName();
		String originalFileName = null;
		String fileExt = null;
		int dot = originalName.lastIndexOf(".");
		if (dot != -1) {
			originalFileName = originalName.substring(0, dot);
			fileExt = originalName.substring(dot + 1);
		} else {
			originalFileName = originalName;
			fileExt = "";
		}

		// 改名,避免重复以及中文问题
		String fileName = DateUtils.getNow("yyyyMMdd_HHmmss") + "_" //
				+ new SecureRandom().nextInt(999999) + "." + fileExt;
		String newFilePath = newPath + "/" + fileName;
		File newFile = new File(newFilePath);
		if (!file.renameTo(newFile)) {
			throw new RuntimeException("file rename fail , oldfile:" + file.getAbsolutePath() //
					+ " -> newfile:" + newFile.getAbsolutePath());
		}

		// 创建对象
		FileUploadBean bean = new FileUploadBean();
		bean.setName(fileName);
		bean.setFactpath(newFilePath);
		bean.setExt(fileExt);
		bean.setOriginalName(originalFileName + "." + fileExt);
		bean.setType(FileUploadConstants.getFileType(fileExt));
		bean.setSize(newFile.length());
		return bean;
	}

	/**
	 * 保存记录
	 * 
	 * 2017年4月5日 上午2:56:10 flyfox 369191470@qq.com
	 * 
	 * @param bean
	 *            文件bean信息
	 * @param businessType
	 *            业务类型
	 * @param userid
	 *            上传用户
	 * @return
	 */
	public boolean save(FileUploadBean bean, int businessType, int userid) {
		SysFileUpload model = new SysFileUpload();
		String now = DateUtils.getNow(DateUtils.DEFAULT_REGEX_YYYY_MM_DD_HH_MIN_SS);
		model.setName(bean.getName());
		String factpath = FileUploadUtils.rebuild(bean.getFactpath());
		model.setFactpath(factpath);
		String path = factpath.replace(FileUploadUtils.getRootPath(), "");
		model.setPath(path);
		model.setExt(bean.getExt());
		model.setOriginalname(bean.getOriginalName());
		model.setType(bean.getType());
		model.setSize(bean.getSize());
		model.setBusinessType(businessType);
		model.setUpdateTime(now);
		model.setUpdateId(userid);
		model.setCreateTime(now);
		model.setCreateId(userid);
		boolean flag = model.save();
		// 设置ID
		bean.setId(model.getId());
		return flag;
	}

	/**
	 * 将File转换为FileUploadBean
	 * 
	 * 2017年4月5日 上午3:02:43 flyfox 369191470@qq.com
	 * 
	 * @param file
	 * @return
	 */
	public FileUploadBean getFileUploadBean(File file) {
		String originalName = file.getName();
		String originalFileName = null;
		String fileExt = null;
		int dot = originalName.lastIndexOf(".");
		if (dot != -1) {
			originalFileName = originalName.substring(0, dot);
			fileExt = originalName.substring(dot + 1);
		} else {
			originalFileName = originalName;
			fileExt = "";
		}

		// 创建对象
		FileUploadBean bean = new FileUploadBean();
		bean.setName(originalName);
		bean.setFactpath(file.getAbsolutePath());
		bean.setExt(fileExt);
		bean.setOriginalName(originalFileName + "." + fileExt);
		bean.setType(FileUploadConstants.getFileType(fileExt));
		bean.setSize(file.length());
		return bean;
	}

}

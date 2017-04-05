package com.jflyfox.system.file.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import org.apache.log4j.Logger;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.ObjectMetadata;
import com.jflyfox.system.config.ConfigCache;

/**
 * 阿里云存储OSS工具类
 * 
 * 2015年11月16日 上午9:55:27 flyfox 330627517@qq.com
 */
public class AliyunOSSUtils {

	private static final Logger logger = Logger.getLogger(AliyunOSSUtils.class);
	public AliyunOSS oss = new AliyunOSS();

	/**
	 * 获取OSS Client
	 * 
	 * 2015年11月16日 上午9:06:11 flyfox 330627517@qq.com
	 * 
	 * @return
	 * @throws OSSException
	 * @throws ClientException
	 */
	public OSSClient getClient() {
		return oss.getClient();
	}

	/**
	 * 上传文件
	 * 
	 * 2015年11月16日 上午9:06:40 flyfox 330627517@qq.com
	 * 
	 * @param key
	 * @param filename
	 */
	public boolean uploadFile(String key, String filename) {
		try {
			oss.uploadFile(getClient(), getBucketName(), key, filename);
			return true;
		} catch (Exception e) {
			logger.error(e);
		}
		return false;
	}

	/**
	 * 下载文件
	 * 
	 * 2015年11月16日 上午9:06:50 flyfox 330627517@qq.com
	 * 
	 * @param key
	 * @param filename
	 */
	public boolean downloadFile(String key, String filename) {
		try {
			oss.downloadFile(getClient(), getBucketName(), key, filename);
			return true;
		} catch (Exception e) {
			logger.error(e);
		}
		return false;
	}

	/**
	 * 删除一个Bucket和其中的Objects
	 * 
	 * 2015年11月16日 上午9:06:57 flyfox 330627517@qq.com
	 * 
	 * @param key
	 */
	public boolean deleteObject(String key) {
		try {
			oss.deleteObject(getClient(), getBucketName(), key);
			return true;
		} catch (Exception e) {
			logger.error(e);
		}
		return false;
	}

	public String getBucketName() {
		String BUCKET_NAME = ConfigCache.getValue("backup.oss.bucketname");
		return BUCKET_NAME;
	}

}

class AliyunOSS {

	private String ENDPOINT = ConfigCache.getValue("backup.oss.endpoint");
	private String ACCESS_ID = ConfigCache.getValue("backup.oss.id");
	private String ACCESS_KEY = ConfigCache.getValue("backup.oss.key");
	private String BUCKET_NAME = ConfigCache.getValue("backup.oss.bucketname");

	/**
	 * 获取OSS Client
	 * 
	 * 2015年11月16日 上午9:06:11 flyfox 330627517@qq.com
	 * 
	 * @return
	 * @throws OSSException
	 * @throws ClientException
	 */
	public OSSClient getClient() throws OSSException, ClientException {
		// 这里使用的北京OSS服务器
		OSSClient client = new OSSClient(ENDPOINT, ACCESS_ID, ACCESS_KEY);
		client.getBucketLocation(BUCKET_NAME);
		return client;
	}

	/**
	 * 删除一个Bucket和其中的Objects
	 * 
	 * 2015年11月16日 上午9:06:33 flyfox 330627517@qq.com
	 * 
	 * @param client
	 * @param bucketName
	 * @throws OSSException
	 * @throws ClientException
	 */
	public void deleteBucket(OSSClient client, String bucketName) throws OSSException, ClientException {
		ObjectListing ObjectListing = getClient().listObjects(bucketName);
		List<OSSObjectSummary> listDeletes = ObjectListing.getObjectSummaries();
		for (int i = 0; i < listDeletes.size(); i++) {
			String objectName = listDeletes.get(i).getKey();
			// 如果不为空，先删除bucket下的文件
			getClient().deleteObject(bucketName, objectName);
		}
		getClient().deleteBucket(bucketName);
	}

	/**
	 * 上传文件
	 * 
	 * 2015年11月16日 上午9:06:40 flyfox 330627517@qq.com
	 * 
	 * @param client
	 * @param bucketName
	 * @param key
	 * @param filename
	 * @throws OSSException
	 * @throws ClientException
	 * @throws FileNotFoundException
	 */
	public void uploadFile(OSSClient client, String bucketName, String key, String filename) throws OSSException,
			ClientException, FileNotFoundException {
		File file = new File(filename);

		ObjectMetadata objectMeta = new ObjectMetadata();
		objectMeta.setContentLength(file.length());
		// 可以在metadata中标记文件类型
		String suf = filename.substring(filename.lastIndexOf("."));
		if (".css".equals(suf)) {
			objectMeta.setContentType("text/css");
		} else if (".js".equals(suf)) {
			objectMeta.setContentType("application/x-javascript");
		} else if (".htm".equals(suf) || ".html".equals(suf)) {
			objectMeta.setContentType("text/html");
		} else if (".json".equals(suf) || ".jsonp".equals(suf)) {
			objectMeta.setContentType("text/plain");
		} else if (".bmp".equals(suf) || ".png".equals(suf) //
				|| ".jpg".equals(suf) || ".jpeg".equals(suf) || ".jfif".equals(suf)) {
			objectMeta.setContentType("image/jpeg");
		} else {
			objectMeta.setContentType("text/plain");
		}

		InputStream input = new FileInputStream(file);
		client.putObject(bucketName, key, input, objectMeta);
	}

	/**
	 * 下载文件
	 * 
	 * 2015年11月16日 上午9:06:50 flyfox 330627517@qq.com
	 * 
	 * @param client
	 * @param bucketName
	 * @param key
	 * @param filename
	 * @throws OSSException
	 * @throws ClientException
	 */
	public void downloadFile(OSSClient client, String bucketName, String key, String filename) throws OSSException,
			ClientException {
		if (client.doesObjectExist(bucketName, key)) {
			client.getObject(new GetObjectRequest(bucketName, key), new File(filename));
		}
	}

	/**
	 * 删除一个Bucket和其中的Objects
	 * 
	 * 2015年11月16日 上午9:06:57 flyfox 330627517@qq.com
	 * 
	 * @param client
	 * @param bucketName
	 * @param key
	 * @throws OSSException
	 * @throws ClientException
	 */
	public void deleteObject(OSSClient client, String bucketName, String key) throws OSSException, ClientException {
		client.deleteObject(bucketName, key);
	}
}

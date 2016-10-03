package com.jflyfox.component.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;

/**
 * 图片处理Utils
 * 
 * 2016年2月5日 下午2:07:33 flyfox 369191470@qq.com
 */
public class ImageUtils {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		long start = System.currentTimeMillis();
		// ImageModel model = getIamge("D:\Downloads\SogouWP\Net\WallPaper\387162.jpg");
		ImageModel model = getIamge("http://i11.tietuku.com/d354a14b308a1473.png");
		
		System.out.println(model.getName());
		System.out.println(model.getExt());
		System.out.println(model.getSize());
		System.out.println(model.getWidth());
		System.out.println(model.getHeight());
		System.out.println("time:" + (System.currentTimeMillis() - start));

	}

	/**
	 * 获取图片信息
	 * 
	 * 2016年2月5日 下午2:09:53 flyfox 369191470@qq.com
	 * 
	 * @param path
	 * @return
	 */
	public static ImageModel getIamge(String path) {
		ImageModel model = null;
		BufferedImage sourceImg = null;
		try {
			model = new ImageModel();
			if (path.startsWith("http")) {
				URL url = new URL(path);
				URLConnection uc = url.openConnection();
				sourceImg = ImageIO.read(uc.getInputStream()); 
				
				String file = url.getFile();
				model.setName(file.replace("/", ""));
				if (file.lastIndexOf(".") >= 0) {
					model.setExt(file.substring(file.lastIndexOf(".") + 1));
				}
				// 未设置大小
			} else {
				File picture = new File(path);
				sourceImg = ImageIO.read(new FileInputStream(picture));
				
				model.setName(picture.getName());
				if (path.lastIndexOf(".") >= 0) {
					model.setExt(path.substring(path.lastIndexOf(".") + 1));
				}
				model.setSize((picture.length() / 1024.0));
			}

			model.setWidth(sourceImg.getWidth());
			model.setHeight(sourceImg.getHeight());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return model;

	}
}

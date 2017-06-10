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

package com.jflyfox.util.extend.mp3;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 每个ID3V2.3的标签都一个标签头和若干个标签帧或一个扩展标签头组成。关于曲目的信息如标题、作者等都存放在不同的标签帧中，扩展标签头和标签帧并不是必要的，但每个标签至少要有一个标签帧。标签头和标签帧一起顺序存放在MP3文件的首部。
 * @author moon.lee
 *
 */
public class MusicInfo {
	
	private String path="";
	private boolean isAnalysis=false;
	/**
	 * 必须为"ID3"否则认为标签不存在
	 * 3个字节
	 */
	private final int HEADER_SIZE=3;
	private byte[] header;
	private String HEAHER_START="ID3";
	/**
	 * 版本号;ID3V2.3就记录03,ID3V2.4就记录04
	 * 一个字节
	 */
	private byte version;
	/**
	 * 副版本号;此版本记录为00
	 * 一个字节
	 */
	private byte reVersion;
	/**
	 * 标志字节一般为0，定义如下：
	 * 一个字节
	 * abc00000
	 * a -- 表示是否使用不同步(一般不设置)
	 * b -- 表示是否有扩展头部，一般没有(至少Winamp没有记录)，所以一般也不设置
	 * c -- 表示是否为测试标签(99.99%的标签都不是测试用的啦，所以一般也不设置) 
	 */
	private byte flag;
	/**
	 * 标签大小，包括标签帧和扩展标签头。（不包括标签头的10个字节）
	 * 一共四个字节，但每个字节只用7位，最高位不使用恒为0。所以格式如下
	 * 0xxxxxxx 0xxxxxxx 0xxxxxxx 0xxxxxxx
	 * 计算大小时要将0去掉，得到一个28位的二进制数，就是标签大小(不懂为什么要这样做)，计算公式如下：
	 * int total_size;
	 * total_size = Size[0]*0x200000
	 * +Size[1]*0x4000
	 * +Size[2]*0x80
	 * +Size[3]
	 */
	private int SIZE_SIZE=4;
	private byte[] size;

	private Map<String, FrameInfo> frameInfos;
	private int LABEL_SIZE=10;


	public MusicInfo() {
		super();
		frameInfos=new HashMap<String, FrameInfo>();
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isAnalysis() {
		return isAnalysis;
	}

	public void setAnalysis(boolean isAnalysis) {
		this.isAnalysis = isAnalysis;
	}

	public byte[] getHeader() {
		return header;
	}

	public void setHeader(byte[] header) {
		this.header = header;
	}

	public byte getVersion() {
		return version;
	}

	public void setVersion(byte version) {
		this.version = version;
	}

	public byte getReVersion() {
		return reVersion;
	}

	public void setReVersion(byte reVersion) {
		this.reVersion = reVersion;
	}

	public byte getFlag() {
		return flag;
	}

	public void setFlag(byte flag) {
		this.flag = flag;
	}

	public byte[] getSize() {
		return size;
	}

	public void setSize(byte[] size) {
		this.size = size;
	}

	public Map<String, FrameInfo> getFrameInfos() {
		return frameInfos;
	}

	/**
	 * 解析信息
	 * @return 0表示成功   1表示不是mp3文件 2表示文件不存在
	 */
	public int parseMusic(){
		return parseMusic("UTF-16");
	}
	/**
	 * 解析信息
	 * @param charset 编码方式
	 * @return 0表示成功   1表示不是mp3文件 2表示文件不存在 3表示解析时异常
	 */
	public int parseMusic(String charset) {
		File file = new File(path);
		if (!file.exists()) {
			return 2;
		}
		if (!file.getName().endsWith(".mp3")) {
			return 1;
		}
		try {
			RandomAccessFile raf = new RandomAccessFile(file, "r");
			/**
			 * 头部信息
			 */
			header = new byte[HEADER_SIZE];
			raf.read(header, 0, HEADER_SIZE);
			// System.out.println("header:"+new String(header));
			if (new String(header).equals(HEAHER_START)) {
				/**
				 * 版本
				 */
				version = raf.readByte();
				System.out.println("version:" + version);
				/**
				 * 副版本
				 */
				reVersion = raf.readByte();
				System.out.println("reVersion:" + reVersion);
				/**
				 * 标志
				 */
				flag = raf.readByte();
				System.out.println("flag:" + flag);
				/**
				 * 标签大小
				 */
				size = new byte[SIZE_SIZE];
				raf.read(size);
				for (int i = 0; i < size.length; i++) {
					System.out.println("size[" + i + "]：0x" + parseDecimalToBinary(size[i]));
				}
				// int total_size=size[0]*0x200000
				// +size[1]*0x4000
				// +size[2]*0x80
				// +size[3];
				/**
				 * 标签信息
				 */
				byte[] label = new byte[LABEL_SIZE];
				raf.read(label);
				/**
				 * 遍历标签信息
				 */
				FrameInfo frameInfo = null;
				while ((frameInfo = decodeFrame(label)) != null) {
					/**
					 * 根据标签内容大小获取标签内容
					 */
					int frameContentSize = frameInfo.getFrameContentSize();

					byte[] content = new byte[frameContentSize];

					/**
					 * 跳过一个字节 '\0'
					 */
					raf.skipBytes(1);
					/**
					 * 读取帧内容
					 */
					raf.read(content);
					frameInfo.setContent(content);
					/**
					 * 将帧内容加入到歌曲信息
					 */
					frameInfos.put(frameInfo.getFrameId(), frameInfo);
					raf.read(label);
				}
				/**
				 * 信息解析完成关闭管道
				 */
				raf.close();
			}
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 3;
		}

	}
	/**
	 * 返回图片数据信息
	 * 
	 * @return  图片map   键mime  图片类型    键data   图片数据
	 * 返回空值表示没有解析到图片
	 * 
	 */
	public Map<String, byte[]> getImage(){
		if(frameInfos==null)return null;
		FrameInfo apicInfo=frameInfos.get("APIC");
		if(apicInfo==null)return null;
		/**
		 * 图片数据
		 */
		byte[]apic=apicInfo.getContent();

		boolean isMIMEComplte=false;
		int i=0;
		/**
		 * 查找图片数据起始位置
		 */
		Map<String, byte[]>map=new HashMap<String, byte[]>();
		for(;i<apic.length;i++){
			/**
		 * 寻找MIME结束位置
		 */

			if(!isMIMEComplte&apic[i]=='\0'){
				byte[] mime=new String(apic, 0, i).getBytes();
				map.put("mime", mime);
				isMIMEComplte=!isMIMEComplte;
			}
	/**
		 *寻找图片数据存储其实位置
		 */

			if(apic[i]==((byte) 0xff)&&apic[i+1]==((byte) 0xd8)){
				byte[] data=new byte[apic.length-i];
				for(int j=0;j<data.length;j++){
					data[j]=apic[i+j];
				}
				map.put("data", data);
				return map;
			}

		}
		return null;
	}
	public String getTitle(String charset){
		if(frameInfos==null)return null;
		FrameInfo titleInfo=frameInfos.get("TIT2");
		if(titleInfo==null)return null;
		try {
			String title=new String(titleInfo.getContent(), charset);
			return title;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public String getTitle(){
		return getTitle("UTF-16");
	}
	public String getPerformer(String charset){
		if(frameInfos==null)return null;
		FrameInfo performerInfo=frameInfos.get("TPE1");
		if(performerInfo == null)
			return null;
		try {
			String performer = new String(performerInfo.getContent(), charset);
			return performer;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getPerformer() {
		return getPerformer("UTF-16");
	}

	public String getAlbum(String charset) {
		if (frameInfos == null)
			return null;
		FrameInfo albumInfo = frameInfos.get("TALB");
		if (albumInfo == null)
			return null;
		try {
			String album = new String(albumInfo.getContent(), charset);
			return album;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getAlbum() {
		return getAlbum("UTF-16");
	}

	/**
	 * 
	 * 十进制转二进制字符串
	 * 
	 * @param decimal十进制byte
	 * @return 8位二进制字符串
	 */
	private String parseDecimalToBinary(byte decimal) {
		int temp = decimal;
		String tempStr = "";
		for (int j = 0; j < 8; j++) {
			/**
			 * 
			 */
			int x = (temp >> j) & 1;
			tempStr = x + tempStr;
		}
		return tempStr;
	}

	/**
	 * 解析帧标签信息
	 * 
	 * @param frameHead帧标签
	 *            10字节
	 * @return
	 */
	private FrameInfo decodeFrame(byte[] frameHead) {

		if (frameHead.length != LABEL_SIZE) {
			return null;
		}
		try {
			/**
			 * 将读取到的开头四个字节匹配字符串[A-Z]{3}[A-Z0-9]{1} 匹配不成功就返回空标签
			 */
			String frameId = new String(frameHead, 0, 4);
			Pattern pattern = Pattern.compile("[A-Z]{3}[A-Z0-9]{1}");
			Matcher matcher = pattern.matcher(frameId);
			if (!matcher.matches()) {
				return null;
			}
			/**
			 * 匹配成功就解析帧标签
			 */
			System.out.println("frameID:" + frameId);
			/**
			 * 标签内容大小 减去 '\0'之后的标签内容大小
			 */
			int qw = frameHead[4];
			int bw = frameHead[5];
			int sw = frameHead[6];
			int gw = frameHead[7];
			if (qw < 0) {
				qw = Math.abs(qw) + 128;
			}
			if (bw < 0) {
				bw = Math.abs(bw) + 128;
			}
			if (sw < 0) {
				sw = Math.abs(sw) + 128;
			}
			if (gw < 0) {
				gw = Math.abs(gw) + 128;
			}
			// int frameContentSize=new Integer(new String(frameHead,4,4));
			int frameContentSize = qw * 0x1000000 + bw * 0x10000 + sw * 0x100 + gw - 1;
			/**
			 * 解析标志信息
			 */
			byte[] flag = new byte[2];
			flag[0] = frameHead[8];
			flag[1] = frameHead[9];
			FrameInfo frameInfo = new FrameInfo();
			frameInfo.setFrameId(frameId);
			frameInfo.setFrameContentSize(frameContentSize);
			frameInfo.setFlag(flag);
			return frameInfo;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String toString() {
		return toString("UTF-16");
	}

	public String toString(String charset) {
		return "title:" + getTitle(charset) + "\nperformer:" + getPerformer(charset) + "\nalbum:" + getAlbum(charset);
	}
}

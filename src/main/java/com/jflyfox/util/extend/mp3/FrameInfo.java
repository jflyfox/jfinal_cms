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

/**
 * AENC Audio encryption
 * APIC Attached picture
 * COMM Comments
 * COMR Commercial frame
 * ENCR Encryption method registration
 * EQUA Equalization
 * ETCO Event timing codes
 * GEOB General encapsulated object
 * GRID Group identification registration
 * IPLS Involved people list
 * LINK Linked information
 * MCDI Music CD identifier
 * MLLT MPEG location lookup table
 * OWNE Ownership frame
 * PRIV Private frame
 * PCNT Play counter
 * POPM Popularimeter
 * POSS Position synchronisation frame
 * RBUF Recommended buffer size
 * RVAD Relative volume adjustment
 * RVRB Reverb
 * SYLT Synchronized lyric/text
 * SYTC Synchronized tempo codes
 * TALB Album/Movie/Show title
 * TBPM BPM (beats per minute)
 * TCOM Composer
 * TCON Content type
 * TCOP Copyright message
 * TDAT Date
 * TDLY Playlist delay
 * TENC Encoded by
 * TEXT Lyricist/Text writer
 * TFLT File type
 * TIME Time
 * TIT1 Content group description
 * TIT2 Title/songname/content description
 * TIT3 Subtitle/Description refinement
 * TKEY Initial key
 * TLAN Language(s)
 * TLEN Length
 * TMED Media type
 * TOAL Original album/movie/show title
 * TOFN Original filename
 * TOLY Original lyricist(s)/text writer(s)
 * TOPE Original artist(s)/performer(s)
 * TORY Original release year
 * TOWN File owner/licensee
 * TPE1 Lead performer(s)/Soloist(s)
 * TPE2 Band/orchestra/accompaniment
 * TPE3 Conductor/performer refinement
 * TPE4 Interpreted, remixed, or otherwise modified by
 * TPOS Part of a set
 * TPUB Publisher
 * TRCK Track number/Position in set
 * TRDA Recording dates
 * TRSN Internet radio station name
 * TRSO Internet radio station owner
 * TSIZ Size
 * TSRC ISRC (international standard recording code)
 * TSSE Software/Hardware and settings used for encoding
 * TYER Year
 * TXXX User defined text information frame
 * UFID Unique file identifier
 * USER Terms of use
 * USLT Unsychronized lyric/text transcription
 * WCOM Commercial information
 * WCOP Copyright/Legal information
 * WOAF Official audio file webpage
 * WOAR Official artist/performer webpage
 * WOAS Official audio source webpage
 * WORS Official internet radio station homepage
 * WPAY Payment
 * WPUB Publishers official webpage
 * WXXX User defined URL link frame
 * @author moon.lee
 *帧信息
 */
public class FrameInfo {
	/**
	 * 用四个字符标识一个帧，说明其内容
	 * 用四个字符标识一个帧，说明一个帧的内容含义，常用的对照如下：
	 * TIT2=标题 表示内容为这首歌的标题，下同
	 * TPE1=作者
	 * TALB=专集
	 * TRCK=音轨 格式：N/M 其中N为专集中的第N首，M为专集中共M首，N和M为ASCII码表示的数字
	 * TYER=年代 是用ASCII码表示的数字
	 * TCON=类型 直接用字符串表示
	 * COMM=备注 格式："eng\0备注内容"，其中eng表示备注所使用的自然语言
	 */
	private String frameId;
	/**
	 * 帧内容的大小，不包括帧头，不得小于1
	 * 这个可没有标签头的算法那么麻烦，每个字节的8位全用，格式如下
	 * xxxxxxxx xxxxxxxx xxxxxxxx xxxxxxxx
	 * 算法如下：
	 * int FSize;
	 * FSize = Size[0]*0x1000000
	 * +Size[1]*0x10000
	 * +Size[2]*0x100
	 * +Size[3];
	 */
	private int frameContentSize;
	/**
	 * 存放标志，只定义了6位
	 * 只定义了6位，另外的10位为0，但大部分的情况下16位都为0就可以了。格式如下：
	 * abc00000 ijk00000
	 * a -- 标签保护标志，设置时认为此帧作废
	 * b -- 文件保护标志，设置时认为此帧作废
	 * c -- 只读标志，设置时认为此帧不能修改(但我没有找到一个软件理会这个标志)
	 * i -- 压缩标志，设置时一个字节存放两个BCD码表示数字
	 * j -- 加密标志，(没有见过哪个MP3文件的标签用了加密)
	 * k -- 组标志，设置时说明此帧和其他的某帧是一组
	 * 值得一提的是winamp在保存和读取帧内容的时候会在内容前面加个'\0'，并把这个字节计算在帧内容的大小中。
	 * 
	 */
	private byte[]flag;

	private byte[] content=null;
	
	public FrameInfo() {
		super();
	}
	
	public String getFrameId() {
		return frameId;
	}

	public void setFrameId(String frameId) {
		this.frameId = frameId;
	}

	public int getFrameContentSize() {
		return frameContentSize;
	}

	public void setFrameContentSize(int frameContentSize) {
		this.frameContentSize = frameContentSize;
	}

	public byte[] getFlag() {
		return flag;
	}
	public void setFlag(byte[] flag) {
		this.flag = flag;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

}

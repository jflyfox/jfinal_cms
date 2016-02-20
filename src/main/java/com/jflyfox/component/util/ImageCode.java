package com.jflyfox.component.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 功能描述：产生验证码图片
 * </p>
 * 
 * @author zb
 * @version 1.0
 */
public class ImageCode extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private Font mFont = new Font("Arial", Font.BOLD, 15); // 设置字体
	private int lineWidth = 2; // 干扰线的长度=1.414*lineWidth
	private int width = 60; // 定义图形大小
	private int height = 20; // 定义图形大小
	private int count = 200;

	/**
	 * 描述：
	 * 
	 * @param fc
	 *            描述：
	 * @param bc
	 *            描述：
	 * 
	 * @return 描述：
	 */
	private Color getRandColor(int fc, int bc) { // 取得给定范围随机颜色
		Random random = new Random();
		if (fc > 255) {
			fc = 255;
		}

		if (bc > 255) {
			bc = 255;
		}

		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);

		return new Color(r, g, b);
	}

	// 处理post
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

	/**
	 * 描述：
	 * 
	 * @param request
	 *            描述：
	 * @param response
	 *            描述：
	 * 
	 * @throws ServletException
	 *             描述：
	 * @throws IOException
	 *             描述：
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// response.reset();
		// 设置页面不缓存
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/gif");

		// 在内存中创建图象
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		// 获取图形上下文
		Graphics2D g = (Graphics2D) image.getGraphics();

		// 生成随机类
		Random random = new Random();

		// 设定背景色
		g.setColor(getRandColor(200, 250)); // ---1

		g.fillRect(0, 0, width, height);

		// 设定字体
		g.setFont(mFont);

		// 画边框
		g.setColor(getRandColor(0, 20)); // ---2
		g.drawRect(0, 0, width - 1, height - 1);

		// 随机产生干扰线，使图象中的认证码不易被其它程序探测到
		for (int i = 0; i < count; i++) {

			g.setColor(getRandColor(150, 200)); // ---3

			int x = random.nextInt(width - lineWidth - 1) + 1; // 保证画在边框之内
			int y = random.nextInt(height - lineWidth - 1) + 1;
			int xl = random.nextInt(lineWidth);
			int yl = random.nextInt(lineWidth);
			g.drawLine(x, y, x + xl, y + yl);
		}

		// 取随机产生的认证码(4位数字)
		String sRand = "";
		// char[] selectChar = new
		// char[]{'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		// zb 2015-1-4 去除奇异字符 去除0 O o I i L l
		char[] selectChar = new char[] { '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f',
				'g', 'h', 'j', 'k', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C',
				'D', 'E', 'F', 'G', 'H', 'J', 'K', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		for (int i = 0; i < 4; i++) {
			int charIndex = random.nextInt(selectChar.length);
			String rand = String.valueOf(selectChar[charIndex]);
			sRand += rand;
			/*
			 * String rand = String.valueOf(random.nextInt(10)); sRand += rand;
			 */

			// 将认证码显示到图象中,调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
			g.setColor(new Color(20 + random.nextInt(130), 20 + random.nextInt(130), 20 + random.nextInt(130))); // --4--50-100

			g.drawString(rand, (13 * i) + 6, 16);
		}

		// 将认证码存入SESSION
		request.getSession().setAttribute(ImageCode.class.getName(), sRand);

		// 图象生效
		g.dispose();

		// 输出图象到页面
		ImageIO.write(image, "PNG", response.getOutputStream());
	}
}

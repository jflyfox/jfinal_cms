package baidu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class PostUrl {

	public static void main(String[] args) throws InterruptedException {
		run();
	}

	public static void run() {
		String url = "http://data.zz.baidu.com/urls?site=www.jflyfox.com&token=6oagSDEkOYIzlxJr";
		String param = "";
		String[] postUrls = new String[] { "http://www.jflyfox.com/mtg" // 门头沟首页
				, "http://www.jflyfox.com/mtg/news"//
				, "http://www.jflyfox.com/mtg/food"//
				, "http://www.jflyfox.com/mtg/travel" //
				, "http://www.jflyfox.com/mtg/education" //
				, "http://www.jflyfox.com/mtg/park" //
				, "http://www.jflyfox.com/blog" // 博客首页
		};
		for (int i = 0; i < postUrls.length; i++) {
			param += postUrls[i] + "\r\n";
		}
		param = param.substring(0, param.lastIndexOf("\r\n"));
		System.out.println(param);
		sendPost(url, param);
	}

	/**
	 * 向指定URL发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是name1=value1&name2=value2的形式。
	 * @return URL所代表远程资源的响应
	 */
	public static String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		String coding = "UTF-8";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			conn.setRequestProperty("Content-Type", "text/plain");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();

			HttpURLConnection httpConnection = (HttpURLConnection) conn;
			int responseCode = httpConnection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				// System.out.println("成功");
				InputStream urlStream = httpConnection.getInputStream();
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlStream, coding));
				String sCurrentLine = "";
				String sTotalString = "";
				while ((sCurrentLine = bufferedReader.readLine()) != null) {
					sTotalString += sCurrentLine;
				}
				System.out.println(sTotalString);
				// 假设该url页面输出为"OK"
				if (sTotalString.equals("OK")) {
				} else {
				}
			} else {
				System.err.println("失败");
			}
		} catch (Exception e) {
			System.out.println("发送POST请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

}

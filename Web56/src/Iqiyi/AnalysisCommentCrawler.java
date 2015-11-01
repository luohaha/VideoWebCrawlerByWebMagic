package Iqiyi;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import Utils.GlobalVar;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class AnalysisCommentCrawler implements PageProcessor{
	private static String part1OfUrl = "http://api.t.iqiyi.com/qx_api/comment/get_video_comments?aid=";
	private static String part2OfUrl = "&categoryid=1&cb=fnsucc&escape=true&need_reply=true&need_subject=true&need_total=1&page=";
	private static String part3OfUrl = "&page_size=10&page_size_reply=3&qitan_comment_type=1&qitancallback=fnsucc&qitanid=";
	private static String part4OfUrl = "&sort=hot&t=&tvid=";
	/*
	 * 站点设置
	 * */
	private Site site = Site
			.me()
			.setDomain("http://www.iqiyi.com/")
			.setSleepTime(3000)
			.setUserAgent(
					"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) "
							+ "AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return this.site;
	}
	@Override
	public void process(Page page) {
		// TODO Auto-generated method stub
		if (!GlobalVar.isFileCommentUrlReaded) {
			GlobalVar.isFileCommentUrlReaded = true;
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		String str = null;
		try {
			fis = new FileInputStream("./commentUrl.txt");
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			while ((str = br.readLine()) != null) {
				//读取两个id号
				String ids[] = str.split(",");
				if (!ids[0].equals("null")&&!ids[0].equals("0")&&
						!ids[1].equals("null")&&!ids[1].equals("0")) {
					String url = this.part1OfUrl+ids[0]+this.part2OfUrl+"1"+
							this.part3OfUrl+ids[0]+this.part4OfUrl+ids[1];
					GlobalVar.map.put(url, ids[2]);
					page.addTargetRequest(url);
							
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		     try {
				br.close();
			     isr.close();
			     fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}

		page.putField("comments", page.getJson().regex("fnsucc=(.*?)$").toString());
		
		JSONObject object = JSON.parseObject(page.getJson().regex("fnsucc=(.*?)$").toString());
		JSONArray array = object.getJSONObject("data").getJSONArray("comments");
		if (array == null || array.size() == 0) {
			//
		} else {
			int currentPageNumber = getPageNumber(page.getUrl().toString());
			page.addTargetRequest(updateUrl(page.getUrl().toString(), ++currentPageNumber));
		}
	}

	/*
	 * url翻页
	 * */
	private String updateUrl(String url, int page) {
		return url.replaceFirst("&page=(.*?)&", "&page="+String.valueOf(page)+"&");
	}
	/*
	 * 根据url，返回页数
	 * */
	private int getPageNumber(String url) {
		return Integer.valueOf(url.split("&")[7].split("=")[1]);
	}
	/*
	 * test unit
	 * */
	public static void main(String[] args) {
		String url = part1OfUrl+"111"+part2OfUrl+"12"+
				part3OfUrl+"111"+part4OfUrl+"111";
		System.out.println();
    }
}

package Iqiyi;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import Utils.GlobalVar;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
/*
* author : 罗一鑫
* date : 2015/10/19
* 根据播放页html，从中抓取qitanid
*
* */
public class CommentCrawler implements PageProcessor{
	private static String part1OfUrl = "http://api.t.iqiyi.com/qx_api/comment/get_video_comments?aid=";
	private static String part2OfUrl = "&categoryid=1&cb=fnsucc&escape=true&need_reply=true&need_subject=true&need_total=1&page=";
	private static String part3OfUrl = "&page_size=10&page_size_reply=3&qitan_comment_type=1&qitancallback=fnsucc&qitanid=";
	private static String part4OfUrl = "&sort=hot&t=&tvid=";
	/*
	 * xpath解析
	 * */
	private static String TOTAL = "//div/div[@class='wrapper']/div[@class='wrapper-left']/"
			+ "div[@id='block-I']/div[@id='qitancommonarea']/@";
	private static String QITANID_XPATH = TOTAL + "data-qitancomment-qitanid";
	private static String TVID_XPATH = TOTAL + "data-qitancomment-tvid";
	private static String TITLE_XPATH = "//head/title";
	
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
		if (!GlobalVar.isFilePlayUrlsReaded) {
			GlobalVar.isFilePlayUrlsReaded = true;
			FileInputStream fis = null;
			InputStreamReader isr = null;
			BufferedReader br = null;
			String str = null;
			try {
				fis = new FileInputStream("./playUrls.txt");
				isr = new InputStreamReader(fis);
				br = new BufferedReader(isr);
				while ((str = br.readLine()) != null) {
					page.addTargetRequest(str);
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
		System.out.println("test");
		// TODO Auto-generated method stub
		page.putField("qitanid", page.getHtml().xpath(QITANID_XPATH).toString()
				+","+page.getHtml().xpath(TVID_XPATH).toString()+","+
				page.getHtml().xpath(TITLE_XPATH).toString().split(">")[1].split("-")[0]);

	}
}

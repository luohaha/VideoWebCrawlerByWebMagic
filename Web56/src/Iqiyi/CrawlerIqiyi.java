package Iqiyi;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;


/*
 * author : Yixin Luo
 * date : 2015/10/10
 * description : 爱奇艺网的网页解析程序,抓取四大栏目中每部影片的aid和name，以便后边
 *               得到每部影片的指数页面url.
 * */
public class CrawlerIqiyi implements PageProcessor{
	/*
	 * 用来构成构成指数页面url的name和aid两项
	 * */
	public static final String VIDEO_NAME = "video_name";
	public static final String VIDEO_ID = "video_id";
	public static final String VIDEO_URL = "video_url";
	/*
	 * 分别对应四大栏目的首页面：电影，电视剧，综艺，动画
	 * */
	public static final String MoviePage = 
			"http://list.iqiyi.com/www/1/-------------11-1-1-iqiyi--.html";
	private static final String TeleplayPage = 
			"http://list.iqiyi.com/www/2/-------------11-1-1-iqiyi--.html";
	private static final String ShowPage = 
			"http://list.iqiyi.com/www/6/-------------11-1-1-iqiyi--.html";
	private static final String CartoonPage = 
			"http://list.iqiyi.com/www/4/-------------11-1-1-iqiyi--.html";
	
	/*
	 * 页面规则定义
	 * */
	private static final String TypeRegrex = 
			"http://list.iqiyi.com/www/\\w+/-------------11-\\w+-1-iqiyi--.html";
	/*
	 * xpath的提取规则
	 * */
	private static final String TOTAL = "//div[@class='page-list']/"
			+ "div[@class='wrapper-content']/div[@class='site-main']/"
			+ "div[@class='wrapper-cols']/div[@class='wrapper-piclist']/"
			+ "ul[@class='site-piclist']/li/div[@class='site-piclist_pic']/"
			+ "a[@class='site-piclist_pic_link']/@";
	private static final String XPATHRULE_TITLE = TOTAL + "title";
	
	private static final String XPATHRULE_ID = TOTAL + "data-qipuid";
	/*
	 * 提取电影播放页面的url
	 * */
	private static final String XPATHRULE_URL = TOTAL + "href";
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
		page.addTargetRequest(MoviePage);
		page.addTargetRequest(TeleplayPage);
		page.addTargetRequest(ShowPage);
		page.addTargetRequest(CartoonPage);
		/*
		 * 提取页面中，底部的不同页面的链接
		 * */
		page.addTargetRequests(page.getHtml().xpath("//div[@class='mod-page']/a").links().all());
		
		if (page.getUrl().regex(TypeRegrex).match()) {

			page.putField(VIDEO_NAME, page.getHtml().xpath(XPATHRULE_TITLE).all());
			page.putField(VIDEO_ID, page.getHtml().xpath(XPATHRULE_ID).all());
			page.putField(VIDEO_URL, page.getHtml().xpath(XPATHRULE_URL).all());
		}
	}

}

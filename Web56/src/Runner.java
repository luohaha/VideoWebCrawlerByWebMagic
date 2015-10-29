import Iqiyi.CommentCrawler;
import Iqiyi.CommentPipleline;
import Iqiyi.CrawlerIqiyi;
import Iqiyi.CrawlerZhishu;
import Iqiyi.IqiyiPipleline;
import Tudou.CrawlerTudou;
import Tudou.TudouPipleline;
import Util.Tools;
import Web56.Crawler56;
import Web56.NumberCountPipleline;
import us.codecraft.webmagic.Spider;
/*
 * author : Yixin Luo
 * date : 2015/10/10
 * description : 爬虫程序的入口程序
 * 
 * */
public class Runner {
	/*
	 * 56网的抓取入口程序
	 * */
	public static void runner56() {
    	/*先从电视剧的页面开始*/
        Spider.create(new Crawler56()).addUrl(Crawler56.TeleplayPage).addPipeline(new NumberCountPipleline())
                .run();
	}
	
	/*
	 * 爱奇艺的抓取程序入口
	 * */
	public static void runnerIqiyi() {
        Spider.create(new CrawlerIqiyi()).addUrl(CrawlerIqiyi.MoviePage).addPipeline(new IqiyiPipleline())
        .run();
	}
	/*
	 * 爱奇艺的指数页面解析程序入口
	 * */
	public static void IqiyiZhishu() {
		Spider.create(new CrawlerZhishu()).addUrl("http://uaa.iqiyi.com/video_index/v1/get_user_profile?album_id=378519000&album_name=%E6%8B%B3%E9%9C%B8%E9%A3%8E%E4%BA%91&callback=window.Q.__callbacks__.cbgg1cdr")
		.run();
	}
	/*
	 * tudou抓取
	 * */
	public static void runnerTudou() {
		String begin = "http://www.tudou.com/s3portal/service/pianku/data.action?"
				+ "pageSize=90&app=mainsitepc&deviceType=1&tags=&tagType=3&firstTagId=5"
				+ "&areaCode=110000&initials=&hotSingerId=&pageNo=1&sortDesc=quality";
		Spider.create(new CrawlerTudou()).addUrl("http://www.tudou.com")
		.addPipeline(new TudouPipleline()).run();
	}
	/*
	 * 提取评论api的url
	 * */
	public static void runnerGetIqiyiCommentUrl() {
		String test = "http://www.iqiyi.com/v_19rrobq8l8.html#vfrm=2-4-0-1";
		Tools.clearInfoForFile("./commentUrl.txt");
		Spider.create(new CommentCrawler()).addUrl(test).addPipeline(new CommentPipleline()).run();;
	}
}

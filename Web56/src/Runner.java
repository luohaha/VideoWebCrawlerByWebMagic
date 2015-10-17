import Iqiyi.CrawlerIqiyi;
import Iqiyi.CrawlerZhishu;
import Iqiyi.IqiyiPipleline;
import Tudou.CrawlerTudou;
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
	private static void runner56() {
    	/*先从电视剧的页面开始*/
        Spider.create(new Crawler56()).addUrl(Crawler56.TeleplayPage).addPipeline(new NumberCountPipleline())
                .run();
	}
	
	/*
	 * 爱奇艺的抓取程序入口
	 * */
	private static void runnerIqiyi() {
        Spider.create(new CrawlerIqiyi()).addUrl(CrawlerIqiyi.MoviePage).addPipeline(new IqiyiPipleline())
        .run();
	}
	/*
	 * 爱奇艺的指数页面解析程序入口
	 * */
	private static void IqiyiZhishu() {
		Spider.create(new CrawlerZhishu()).addUrl("http://uaa.iqiyi.com/video_index/v1/get_user_profile?album_id=378519000&album_name=%E6%8B%B3%E9%9C%B8%E9%A3%8E%E4%BA%91&callback=window.Q.__callbacks__.cbgg1cdr")
		.run();
	}
	/*
	 * tudou抓取
	 * */
	private static void runnerTudou() {
		Spider.create(new CrawlerTudou()).addUrl(CrawlerTudou.MoviePage).run();
	}
	public static void main(String[] args) {
		//runner56();
		//runnerIqiyi();
		//IqiyiZhishu();
		runnerTudou();
    }
}

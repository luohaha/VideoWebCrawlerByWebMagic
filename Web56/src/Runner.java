import Iqiyi.CrawlerIqiyi;
import Iqiyi.IqiyiPipleline;
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
	public static void main(String[] args) {
		//runner56();
		runnerIqiyi();
    }
}

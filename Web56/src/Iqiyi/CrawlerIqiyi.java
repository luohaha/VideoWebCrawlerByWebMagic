package Iqiyi;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/*
 * author : Yixin Luo
 * date : 2015/10/10
 * description : 爱奇艺网的网页解析程序
 * */
public class CrawlerIqiyi implements PageProcessor{
	
	/*
	 * 站点设置
	 * */
	private Site site = Site
            .me()
            .setDomain("http://video.56.com")
            .setSleepTime(3000)
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) "
                    + "AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void process(Page page) {
		// TODO Auto-generated method stub
		
	}
	
}

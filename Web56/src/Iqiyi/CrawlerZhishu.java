package Iqiyi;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
/*
 * author : Yixin Luo
 * date : 2015/10/11
 * description : 抓取给定的指数页面url的页面源码
 * */
public class CrawlerZhishu implements PageProcessor{

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
		page.putField("json", page.getHtml().regex("cbgg1cdr\\((.*?)\\)").replace("&quot;", "\"").toString());
	}

}

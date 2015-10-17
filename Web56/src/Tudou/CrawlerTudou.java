package Tudou;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Json;

/*
 * author : Yixin Luo
 * date : 2015/10/17
 * 
 * */
public class CrawlerTudou implements PageProcessor{
	public final static String MoviePage = "http://www.tudou.com/s3portal/service/pianku/data.action?"
			+ "pageSize=90&app=mainsitepc&deviceType=1&tags=&tagType=3&firstTagId=5"
			+ "&areaCode=110000&initials=&hotSingerId=&pageNo=1&sortDesc=quality";
	public final static String TeleplayPage = "http://www.tudou.com/s3portal/service/pianku/data.action?"
			+ "pageSize=90&app=mainsitepc&deviceType=1&tags=&tagType=3&firstTagId=3"
			+ "&areaCode=110000&initials=&hotSingerId=&pageNo=1&sortDesc=quality";
	public final static String CatoonPage = "http://www.tudou.com/s3portal/service/pianku/data.action?"
			+ "pageSize=90&app=mainsitepc&deviceType=1&tags=&tagType=3&firstTagId=4"
			+ "&areaCode=110000&initials=&hotSingerId=&pageNo=1&sortDesc=quality";
	public final static String ShowPage = "http://www.tudou.com/s3portal/service/pianku/data.action?"
			+ "pageSize=90&app=mainsitepc&deviceType=1&tags=&tagType=3&firstTagId=6"
			+ "&areaCode=110000&initials=&hotSingerId=&pageNo=1&sortDesc=quality";
	/*
	 * 站点设置
	 * */
	private Site site = Site
            .me()
            .setDomain("http://www.tudou.com/")
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
		//page.addTargetRequest(MoviePage);
		page.addTargetRequest(TeleplayPage);
		page.addTargetRequest(CatoonPage);
		page.addTargetRequest(ShowPage);

		switch(page.getUrl().toString()) {
			case MoviePage:
				page.putField("movie", page.getJson().toString());
				break;
			case TeleplayPage:
				page.putField("tele", page.getJson().toString());
				break;
			case CatoonPage:
				page.putField("catoon", page.getJson().toString());
				break;
			case ShowPage:
				page.putField("show", page.getJson().toString());
				break;
			default:
				System.out.println("url err!");
		}
		
	}
	

}

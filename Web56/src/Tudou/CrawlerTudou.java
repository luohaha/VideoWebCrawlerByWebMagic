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
	/*
	 * 构成api的部分
	 * */
	private final static String MainUrl = "http://www.tudou.com/s3portal/service/pianku/data.action?"
			+ "pageSize=90&app=mainsitepc&deviceType=1&tags=&tagType=3"
			+ "&areaCode=110000&initials=&hotSingerId=&sortDesc=quality";
	private final static String TagId = "&firstTagId=";
	private final static String PageNo = "&pageNo=";
	/*
	 * 对应api中firstTagId的值
	 * */
	private final static String MovieTag = "5";
	private final static String TeleplayTag = "3";
	private final static String CatoonTag = "4";
	private final static String ShowTag = "6";
	/*
	 * 对应四种类型的正则匹配
	 * */
	public final static String MoviePageRegex = "http://www.tudou.com/s3portal/service/pianku/data.action\\?"
			+ "pageSize=90&app=mainsitepc&deviceType=1&tags=&tagType=3"
			+ "&areaCode=110000&initials=&hotSingerId=&sortDesc=quality&firstTagId=5&pageNo=\\w+";
	public final static String TeleplayPageRegex = "http://www.tudou.com/s3portal/service/pianku/data.action\\?"
			+ "pageSize=90&app=mainsitepc&deviceType=1&tags=&tagType=3"
			+ "&areaCode=110000&initials=&hotSingerId=&sortDesc=quality&firstTagId=3&pageNo=\\w+";
	public final static String CatoonPageRegex = "http://www.tudou.com/s3portal/service/pianku/data.action\\?"
			+ "pageSize=90&app=mainsitepc&deviceType=1&tags=&tagType=3"
			+ "&areaCode=110000&initials=&hotSingerId=&sortDesc=quality&firstTagId=4&pageNo=\\w+";
	public final static String ShowPageRegex = "http://www.tudou.com/s3portal/service/pianku/data.action\\?"
			+ "pageSize=90&app=mainsitepc&deviceType=1&tags=&tagType=3"
			+ "&areaCode=110000&initials=&hotSingerId=&sortDesc=quality&firstTagId=6&pageNo=\\w+";
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
		
		page.addTargetRequest(this.getUrlToFetch(MovieTag, 1));
		page.addTargetRequest(this.getUrlToFetch(TeleplayTag, 1));
		page.addTargetRequest(this.getUrlToFetch(CatoonTag, 1));
		page.addTargetRequest(this.getUrlToFetch(ShowTag, 1));

		if (page.getUrl().regex(MoviePageRegex).match()) {
			PutField(MovieTag, page);
		}
		if (page.getUrl().regex(TeleplayPageRegex).match()) {
			PutField(TeleplayTag, page);
		}
		if (page.getUrl().regex(CatoonPageRegex).match()) {
			PutField(CatoonTag, page);
		}
		if (page.getUrl().regex(ShowPageRegex).match()) {
			PutField(ShowTag, page);
		}
		
	}
	
	/*
	 * 合成要抓取的url
	 * */
	private String getUrlToFetch(String tag, int page) {
		return MainUrl+TagId+tag+PageNo+String.valueOf(page);
	}
	
	/*
	 * 获取当前url对应的页号
	 * */
	private int getPageNoFromUrl(String url) {
		String num = url.split(PageNo)[1];
		return Integer.valueOf(num);
	}
	
	private void PutField(String tag, Page page) {
		if (page.getJson().jsonPath("$.items") != null) {
			page.putField(tag, page.getJson());
			int currentPage = getPageNoFromUrl(page.getUrl().toString());
			page.addTargetRequest(getUrlToFetch(tag, ++currentPage));
		}
	}
}

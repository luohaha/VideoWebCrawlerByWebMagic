import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
/*
 * author : Yixin Luo
 * date : 2015/10/9
 * description : 抓取56网下三大栏目的视频，并且判断出各自视频的来源，统计
 * 
 * */
public class Crawler56 implements PageProcessor{
	/*
	 * 56网的电影视频类型分类：
	 * p1101 ： 电视剧
	 * p1106 ： 综艺
	 * p1100 ： 电影
	 * */
	public static final String VideoTypeRegex = 
			"http://video\\.56\\.com/wolelist_p1\\w+_p2_p3_p4_p5_p6_p7_p8_p9_p10_p11_p12_p13\\.html";
    public static final String MoviePage = 
    		"http://video.56.com/wolelist_p1100_p2_p3_p4_p5_p6_p7_p8_p9_p10_p11_p12_p13.html";
    public static final String TeleplayPage = 
    		"http://video.56.com/wolelist_p1101_p2_p3_p4_p5_p6_p7_p8_p9_p10_p11_p12_p13.html";
    public static final String ShowPage = 
    		"http://video.56.com/wolelist_p1106_p2_p3_p4_p5_p6_p7_p8_p9_p10_p11_p12_p13.html";
    
    /*
     * p10后面的数字代表当前页面
     * */
	public static final String PageCountRegex = 
			"http://video\\.56\\.com/wolelist_p1\\w+_p2_p3_p4_p5_p6_p7_p8_p9_p10\\w+_p11_p12_p13.html";
	/*
	 * 设置站点
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
		return this.site;
	}

	@Override
	public void process(Page page) {
		// TODO Auto-generated method stub
		page.addTargetRequest(MoviePage);
		page.addTargetRequest(ShowPage);
		/*
		 * 添加后续每一页的url，因为调度过程中会过滤重复的页面，所以不考虑页面重复的问题
		 * */
		page.addTargetRequests(page.getHtml().xpath("//div[@class='page_hook_wrap']/div[@class='wrap']/"
				+ "div[@class='sort-column']/div[@class='column-bd']/div[@class='ssPages']/a").links().all());
		
		/*
		 * 符合要求的页面,
		 * */
		if (page.getUrl().regex(PageCountRegex).match()) {
			page.putField("type", page.getHtml().xpath("//div[@class='page_hook_wrap']/div[@class='wrap']/"
					+ "div[@class='sort-column']/div[@class='column-bd']/ul[@class='st-list']/li/"
					+ "div[@class='st-pic']/a/@href").all());
		}
	}
	
}

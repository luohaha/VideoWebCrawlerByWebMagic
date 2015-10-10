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
	public static void main(String[] args) {
		runner56();
    }
}

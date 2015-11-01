package Iqiyi;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import Utils.GlobalVar;
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
            .setSleepTime(6000)
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
		if (!GlobalVar.isFileReaded) {
			GlobalVar.isFileReaded = true;
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		String str = null;
		try {
			fis = new FileInputStream("./urls.txt");
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			while ((str = br.readLine()) != null) {
				page.addTargetRequest(str);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		     try {
				br.close();
			     isr.close();
			     fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
		// TODO Auto-generated method stub
		page.putField("json", page.getHtml().regex("cbgg1cdr\\((.*?)\\)").replace("&quot;", "\"").toString());
	}

}

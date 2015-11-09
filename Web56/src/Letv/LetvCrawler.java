package Letv;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import Utils.GlobalVar;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class LetvCrawler implements PageProcessor{
	/*
	 * 获取电影列表的api
	 * */
	private String movieList = "http://list.letv.com/apin/chandata.json?c=1&d=1&md=&o=&s=1";
	/*
	 * 站点设置
	 * */
	private Site site = Site
            .me()
            .setDomain("http://www.letv.com/")
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
		if (page.getUrl().toString().split("&p=")[0].equals(this.movieList)) {
			JSONObject object = JSONObject.parseObject(page.getJson().toString());
			GlobalVar.TotalMovie = object.getInteger("album_count");
			JSONArray albumList = object.getJSONArray("album_list");

			/*
			 * 获取vid和name
			 * */
			List<String> vidAndNameList = new ArrayList<String>();
			for (int i = 0; i < albumList.size(); i++) {
				JSONObject each = albumList.getJSONObject(i);
				vidAndNameList.add(each.getString("vids").split(",")[0]+",,,"+each.getString("name"));

				GlobalVar.currentCatchMoive += 1;
			}
			page.putField("vid", vidAndNameList);
			int currentPage = getUrlCurrentPage(page.getUrl().toString());
			if (GlobalVar.TotalMovie > GlobalVar.currentCatchMoive) {
				//还未抓取完成
				System.out.println("当前页面:"+currentPage+" 占比:"+GlobalVar.TotalMovie+"/"+GlobalVar.currentCatchMoive);
				page.addTargetRequest(this.movieList+"&p="+String.valueOf(++currentPage));

			}
		}
	}

	/*
	 * 返回当前的页数
	 * */
	private int getUrlCurrentPage(String url) {
		return Integer.valueOf(url.split("&p=")[1]);
	}
}

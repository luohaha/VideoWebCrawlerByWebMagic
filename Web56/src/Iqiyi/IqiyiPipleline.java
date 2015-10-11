package Iqiyi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class IqiyiPipleline implements Pipeline{

	@Override
	public void process(ResultItems items, Task task) {
		// TODO Auto-generated method stub
		System.out.println("对应的页面: " + items.getRequest().getUrl());
		/*
		 *  抓取到的name和id
		 * */
		List<String> name = new ArrayList<String>();
		List<String> aid = new ArrayList<String>();
		
		for (Entry<String, Object> entry : items.getAll().entrySet()) {
			String key = (String)entry.getKey();
			if (key.equals(CrawlerIqiyi.VIDEO_NAME)) {
				name = (List<String>) entry.getValue();
			} else if (key.equals(CrawlerIqiyi.VIDEO_ID)) {
				aid = (List<String>) entry.getValue();
			}
			
		}
		for (int i = 0; i < name.size(); i++) {
			System.out.println(name.get(i)+" 指数页面为: "+
					"http://index.iqiyi.com/q/?aid="+aid.get(i)+"&name="
							+ name.get(i));
		}
	}

}

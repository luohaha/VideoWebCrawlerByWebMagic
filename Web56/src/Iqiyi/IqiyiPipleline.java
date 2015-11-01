package Iqiyi;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/*
 * author : Yixin Luo
 * date : 2015/10/11
 * description : 根据得到的aid和name，组合成为每部影片的指数页面url
 * */
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
		List<String> url = new ArrayList<String>();
		FileWriter fw = null;
		FileWriter fwPlayUrl = null;
		
		for (Map.Entry<String, Object> entry : items.getAll().entrySet()) {
			String key = (String)entry.getKey();
			if (key.equals(CrawlerIqiyi.VIDEO_NAME)) {
				name = (List<String>) entry.getValue();
			} else if (key.equals(CrawlerIqiyi.VIDEO_ID)) {
				aid = (List<String>) entry.getValue();
			} else if (key.equals(CrawlerIqiyi.VIDEO_URL)) {
				url = (List<String>) entry.getValue();
			}
			
		}
		try {
			fw = new FileWriter("./urls.txt", true);
			fwPlayUrl = new FileWriter("./playUrls.txt", true);
			for (int i = 0; i < name.size(); i++) {
			/*
			System.out.println(name.get(i)+" 指数页面为: "+
					"http://index.iqiyi.com/q/?aid="+aid.get(i)+"&name="
							+ name.get(i));*/
				fw.write("http://uaa.iqiyi.com/video_index/v1/get_user_profile?album_id="+aid.get(i)+"&album_name="
							+ name.get(i)+"&callback=window.Q.__callbacks__.cbgg1cdr");
				fw.write("\n");
				fwPlayUrl.write(url.get(i));
				fwPlayUrl.write("\n");
			}
			fw.close();
			fwPlayUrl.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}

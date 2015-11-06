package Letv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import Utils.GlobalVar;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/*
 * 	author : Yixin Luo
 *  letv的评论抓取，根据文件中的id号
 * */
public class CommentCrawler implements PageProcessor{
	
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
		if (GlobalVar.LETV_MARK == false) {
			/*还未读取文件*/
			File file = new File("./letvVid.txt");
			if (!file.exists())
				System.err.println("文件未存在!");
			addTargetsFromFile(page, file);
		}
		if (page.getUrl().toString().split("?")[0].equals(LetvApi.returnApiRegex())) {
			/*符合api的规则*/
			JSONObject object = JSONObject.parseObject(page.getJson().toString());
			JSONArray array = object.getJSONArray("data");
			List<String> datas = new ArrayList<String>();
			for (int i = 0; i < datas.size(); i++) {
				datas.add(array.getJSONObject(i).getString("content"));
			}
			page.putField("comments", datas);
		}
		
	}
	
	/*
	 * 将文件中的ip号提取,添加到target中
	 * */
	private void addTargetsFromFile(Page page, File file) {
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line;
			while ((line = br.readLine()) != null) {
				if (line == null || line.equals(""))
					continue;
				page.addTargetRequest(LetvApi.create().setRows(20).setPage(1).setXid(line).toString());
			}
			br.close();
			fr.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

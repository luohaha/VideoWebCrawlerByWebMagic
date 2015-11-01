package Iqiyi;

import java.io.File;
import java.io.FileWriter;
import java.util.Map;

import Utils.GlobalVar;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class AnalysisCommentPipleLine implements Pipeline{

	@Override
	public void process(ResultItems items, Task arg1) {
		File file = new File("./"+GlobalVar.map.get(items.getRequest().getUrl())+".txt");
		try {
			if (!file.exists())
				file.createNewFile();
			FileWriter fw = new FileWriter(file, true);
			// TODO Auto-generated method stub
			for (Map.Entry<String, Object> entry : items.getAll().entrySet()) {
				System.out.println(entry.getValue());
				
				JSONObject object = JSON.parseObject(entry.getValue().toString());
				JSONArray array = object.getJSONObject("data").getJSONArray("comments");
				
				for (int i = 0; i < array.size(); i++) {
					JSONObject each = array.getJSONObject(i);
					fw.write(each.getString("content"));
					fw.write("\n");
				}
				
			}
			fw.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}

package Letv;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import Utils.GlobalVar;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class CommentPipleline implements Pipeline{

	@Override
	public void process(ResultItems items, Task arg1) {
		String url = items.getRequest().getUrl();
		String xid = String.valueOf(LetvApi.getLetvApi(url).xid);
		String name = GlobalVar.letvMap.get(xid);
		File file = new File("./letv/"+name+".txt");
		try {
			if (!file.exists())
				file.createNewFile();
			FileWriter fw = new FileWriter(file, true);
			// TODO Auto-generated method stub
			for (Map.Entry<String, Object> each: items.getAll().entrySet()) {
				List<JSONObject> coms = (List<JSONObject>) each.getValue();
				for (int i = 0; i < coms.size(); i++) {
					String content = coms.get(i).getString("content");
					fw.write(content);
					fw.write("\n");
				}
			}
			fw.close();
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

}

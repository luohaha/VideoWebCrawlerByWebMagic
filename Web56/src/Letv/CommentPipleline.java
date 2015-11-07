package Letv;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class CommentPipleline implements Pipeline{

	@Override
	public void process(ResultItems items, Task arg1) {
		// TODO Auto-generated method stub
		for (Map.Entry<String, Object> each: items.getAll().entrySet()) {
			List<JSONObject> coms = (List<JSONObject>) each.getValue();
			for (int i = 0; i < coms.size(); i++) {
				String title = coms.get(i).getString("title");
				String content = coms.get(i).getString("content");
				System.out.println("题目："+title+" 内容："+content);
			}
		}
	}

}

package Letv;

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
		// TODO Auto-generated method stub
		for (Map.Entry<String, Object> each: items.getAll().entrySet()) {
			List<JSONObject> coms = (List<JSONObject>) each.getValue();
			for (int i = 0; i < coms.size(); i++) {
				String content = coms.get(i).getString("content");
				String id = coms.get(i).getString("xid");
				System.out.println("题目："+GlobalVar.letvMap.get(id)+" 内容："+content);
			}
		}
	}

}

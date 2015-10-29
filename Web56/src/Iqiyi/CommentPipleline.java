package Iqiyi;

import java.util.Map;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class CommentPipleline implements Pipeline{

	@Override
	public void process(ResultItems items, Task task) {
		// TODO Auto-generated method stub
		for (Map.Entry<String, Object> entry : items.getAll().entrySet()) {
			System.out.println("key : "+entry.getKey()+"   "+" value : "+entry.getValue());
		}
	}

}

package Letv;

import java.util.List;
import java.util.Map;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class CommentPipleline implements Pipeline{

	@Override
	public void process(ResultItems items, Task arg1) {
		// TODO Auto-generated method stub
		for (Map.Entry<String, Object> each: items.getAll().entrySet()) {
			List<String> coms = (List<String>) each.getValue();
			for (int i = 0; i < coms.size(); i++) {
				System.out.println(coms.get(i));
			}
		}
	}

}

package Web56;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import Util.GlobalVar;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/*
 * author : Yixin Luo
 * date : 2015/10/9
 * description : 统计视频的来源
 * 	
 * */
public class NumberCountPipleline implements Pipeline{

	@Override
	public void process(ResultItems items, Task task) {
		// TODO Auto-generated method stub
		System.out.println("对应的页面: " + items.getRequest().getUrl());
        //遍历所有结果，输出到控制台，上面例子中的"author"、"name"、"readme"都是一个key，其结果则是对应的value
        for (Map.Entry<String, Object> entry : items.getAll().entrySet()) {
        	List<String> item = (List<String>)entry.getValue();
        	for(String one : item) {
        		
        		if (one.equals("") || one == null || one.length() == 0) {
        			
        		} else {
        			Pattern pattern = Pattern.compile("/");
        			String[] res = pattern.split(one);
        			if (GlobalVar.count.get(res[2]) == null) {
        				GlobalVar.count.put(res[2], 1.0);
        			} else {
        				double previousNumber = GlobalVar.count.get(res[2]);
        				GlobalVar.count.put(res[2], ++previousNumber);
        			}
        			for (Map.Entry<String, Double> map : GlobalVar.count.entrySet()) {
        				System.out.println(map.getKey()+"的个数为 ："+map.getValue().toString());
        			}
        		}
        	}
            //System.out.println(entry.getKey() + ":\t" + entry.getValue());
        }
	}

}

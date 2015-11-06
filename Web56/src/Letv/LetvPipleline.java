package Letv;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class LetvPipleline implements Pipeline{

	@Override
	public void process(ResultItems items, Task arg1) {
		// TODO Auto-generated method stub
		try {
			File file = new File("./letvVid.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file, true);
			for (Map.Entry<String, Object> each: items.getAll().entrySet()) {
				List<String> vidList = (List<String>)each.getValue();
				for(int i = 0; i < vidList.size(); i++) {
					fw.write(vidList.get(i));
					fw.write("\n");
				}
				fw.flush();
				//System.out.println(each.getValue().toString());
			}
			fw.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}

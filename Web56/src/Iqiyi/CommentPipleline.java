package Iqiyi;

import java.io.File;
import java.io.FileWriter;
import java.util.Map;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class CommentPipleline implements Pipeline{

	@Override
	public void process(ResultItems items, Task arg1) {
		// TODO Auto-generated method stub
		File file = new File("./commentUrl.txt");
		try {
			if (!file.exists())
				file.createNewFile();
			FileWriter fw = new FileWriter(file,true);
			for (Map.Entry<String, Object> entry : items.getAll().entrySet()) {
				fw.write(entry.getValue().toString());
				fw.write("\n");
				fw.flush();
			}
			fw.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}

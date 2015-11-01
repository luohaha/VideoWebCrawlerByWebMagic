package Tudou;

import java.util.List;
import java.util.Map;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.selector.Json;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * author : Yixin Luo
 * date : 2015/10/17
 * description : 对抓取到的土豆json进行解析
 *       可用的字段为：播放页url，是否付费，是否为电视剧，播放量，演员，图片url，是否付费等
 * */
public class TudouPipleline implements Pipeline{

	@Override
	public void process(ResultItems items, Task task) {
		// TODO Auto-generated method stub
		System.out.println("对应的页面: " + items.getRequest().getUrl());
		for (Map.Entry<String, Object> each: items.getAll().entrySet()) {
			System.out.println(each.getKey()+":\n");
			Json get = (Json)each.getValue();
			List<String> title = get.jsonPath("$.items[*].title").all();
			List<String> playUrl = get.jsonPath("$.items[*].playUrl").all();
			List<String> albumId = get.jsonPath("$.items[*].albumId").all();
			List<String> alias = get.jsonPath("$.items[*].alias").all();
			List<String> actors = get.jsonPath("$.items[*].actors").all();

			for (int i = 0; i < title.size(); i++) {
			 	System.out.println(each.getValue());
			}
		}
	}

}

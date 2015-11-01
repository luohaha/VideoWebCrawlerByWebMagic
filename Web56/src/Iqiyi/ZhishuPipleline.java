package Iqiyi;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import Utils.GlobalVar;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.selector.Json;

public class ZhishuPipleline implements Pipeline{

	@Override
	public void process(ResultItems items, Task task) {
		// TODO Auto-generated method stub
		//System.out.println("对应的页面: " + items.getRequest().getUrl());
		String name = items.getRequest().getUrl().toString().split("&")[1].split("=")[1];
		System.out.println("name->"+name);
		for (Map.Entry<String, Object> each: items.getAll().entrySet()) {
			JSONObject object = JSON.parseObject(each.getValue().toString());
			//Json get = (Json)each.getValue();
			//String code = get.jsonPath("$.code").toString();
			//if (code.equals("A00003")) {
				//GlobalVar.bad++;
			//}
			//GlobalVar.total++;
			//System.out.println("json -> "+ object.toString());
			JSONObject details = object.getJSONObject("data").getJSONObject("details");
			JSONArray ageLabels = object.getJSONObject("data").getJSONArray("ageLabels");
			JSONArray constellationLabels = object.getJSONObject("data").getJSONArray("constellationLabels");
			JSONArray educationLabels = object.getJSONObject("data").getJSONArray("educationLabels");
			JSONArray genderLabels = object.getJSONObject("data").getJSONArray("genderLabels");
			JSONArray interestLabels = object.getJSONObject("data").getJSONArray("interestLabels");
			JSONObject res = details.getJSONObject(name);
			JSONArray age = res.getJSONArray("age");
			JSONArray constellation = res.getJSONArray("constellation");
			JSONArray education = res.getJSONArray("education");
			JSONArray gender = res.getJSONArray("gender");
			JSONArray interest = res.getJSONArray("interest");

			for (int i = 0; i < age.size(); i++) {
				System.out.println(ageLabels.get(i)+" -> "+age.get(i));
			}
			for (int i = 0; i < constellation.size(); i++) {
				System.out.println(constellationLabels.get(i)+" -> "+constellation.get(i));
			}
			for (int i = 0; i < education.size(); i++) {
				System.out.println(educationLabels.get(i)+" -> "+education.get(i));
			}
			for (int i = 0; i < gender.size(); i++) {
				System.out.println(genderLabels.get(i)+" -> "+gender.get(i));
			}
			for (int i = 0; i < interest.size(); i++) {
				System.out.println(interestLabels.get(i)+" -> "+interest.get(i));
			}
			//System.out.println("错误:"+String.valueOf(GlobalVar.bad)+"/"+String.valueOf(GlobalVar.total));
		}
	}

}

package com.gome.baidublackfriday.controller.action;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coo8.topic.controller.action.BaseAction;


/**
 * @author JIANGCHENG QRX
 * @version 1.0
 * @since 1.0
 */

@Namespace("/ProductWords")
@ParentPackage(value="json-default")
public class ProductWordsAction2 extends BaseAction  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3178837712758769828L;
	private static Logger logger = LoggerFactory.getLogger(ProductWordsAction2.class);
	private List<Map<String, String>> data;


	@Action(value = "friendlink", results = { @Result(name = "success", type = "json",params = {"callbackParameter","callback"} ) } )
	public String list() {
		logger.info("ProductWords list start!");
		try {
			data = new ArrayList<Map<String, String>>();
			addTest(data);
			logger.info("列表为"+data.size());
			//获取数据
			String callback = getRequest().getParameter("jsonp");
			logger.info("callback为"+callback);
            //将数据存储在map里，再转换成json类型数据，也可以自己手动构造json类型数据
            Map<String,Object> map = new HashMap<String,Object>();
//            map.put("aa", "小苹果");
//            map.put("bb", "小桃子");
//            map.put("cc", "小西瓜");
//            map.put("data", paginatedArrayList);
//            JSONObject json = JSONObject.fromObject(map);//将map对象转换成json类型数据
//            result = json.toString();//给result赋值，传递给页面
        }  catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
	}
	
	private void addTest(List<Map<String, String>> p){
		for (int i = 1; i < 22; i++) {
			HashMap<String, String> map = new HashMap<>();
			map.put("id",i+"");
			map.put("name","友情链接"+1);
			map.put("url","https://www.gome.com.cn");
			p.add(map);
		}
	}


	public List<Map<String, String>> getData() {
		return data;
	}

	public void setData(List<Map<String, String>> data) {
		this.data = data;
	}
	
	
}


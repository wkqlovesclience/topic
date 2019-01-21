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

import com.coo8.btoc.util.pages.PaginatedArrayList;
import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.topic.controller.action.BaseAction;
import com.gome.baidublackfriday.model.BaiDuBlackFridayCard;
import com.gome.baidublackfriday.model.ProductWord;

import net.sf.json.JSONObject;


/**
 * @author JIANGCHENG QRX
 * @version 1.0
 * @since 1.0
 */

@Namespace("/ProductWords")
@ParentPackage(value="json-default")
public class ProductWordsAction3 extends BaseAction  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3178837712758769828L;
	private static Logger logger = LoggerFactory.getLogger(ProductWordsAction3.class);
	private List<ProductWord> data;
	private Integer productWordId=1;


	@Action(value = "like", results = { @Result(name = "success", type = "json",params = {"callbackParameter","callback"} ) } )
	public String list() {
		logger.info("ProductWords list start!");
		try {
			data = new ArrayList<ProductWord>();
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
	
	private void addTest(List<ProductWord> p){
		for (int i = 0; i < 20; i++) {
			p.add(new ProductWord(i, "苹果手机"+i));
		}
	}

	public List<ProductWord> getData() {
		return data;
	}

	public void setData(List<ProductWord> data) {
		this.data = data;
	}

	public Integer getProductWordId() {
		return productWordId;
	}

	public void setProductWordId(Integer productWordId) {
		this.productWordId = productWordId;
	}
	
	
	
}


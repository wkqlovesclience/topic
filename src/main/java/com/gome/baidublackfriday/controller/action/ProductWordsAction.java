package com.gome.baidublackfriday.controller.action;


import java.util.HashMap;
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
public class ProductWordsAction extends BaseAction  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3178837712758769828L;
	private static Logger logger = LoggerFactory.getLogger(ProductWordsAction.class);
	private String result;
	private PaginatedList<ProductWord> data;
	private Integer page_index=1;
	private Integer page_size=140;
	private Integer data_num=300;
	private Integer page_num=1;
	private String search="A";


	@Action(value = "list", results = { @Result(name = "success", type = "json",params = {"callbackParameter","callback"} ) } )
	public String list() {
		logger.info("ProductWords list start!");
		try {
			data = new PaginatedArrayList<ProductWord>(300, page_index, page_size);
			addTest(data);
			if(data_num%page_size !=0)
				page_num = data_num/page_size+1;
			else
				page_num = data_num/page_size;
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
	
	private void addTest(PaginatedList<ProductWord> p){
		int num = p.getStartPos()+p.getPageSize();
		for (int i = p.getStartPos(); i < (num < p.getTotalRec()? num :p.getTotalRec()); i++) {
			logger.info("添加手机为"+"苹果手机"+i);
			p.add(new ProductWord(i,"苹果手机"+i));
		}
	}
	
	
	
//	public String getResult() {
//		return result;
//	}
//
//
//
//	public void setResult(String result) {
//		this.result = result;
//	}



	public Integer getPage_size() {
		return page_size;
	}

	public PaginatedList<ProductWord> getData() {
		return data;
	}

	public void setData(PaginatedList<ProductWord> data) {
		this.data = data;
	}

	public void setPage_size(Integer page_size) {
		this.page_size = page_size;
	}

	public Integer getPage_index() {
		return page_index;
	}

	public Integer getData_num() {
		return data_num;
	}

	public void setData_num(Integer data_num) {
		this.data_num = data_num;
	}

	public void setPage_index(Integer page_index) {
		this.page_index = page_index;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public Integer getPage_num() {
		return page_num;
	}

	public void setPage_num(Integer page_num) {
		this.page_num = page_num;
	}
	
	
	
}


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
			logger.info("�б�Ϊ"+data.size());
			//��ȡ����
			String callback = getRequest().getParameter("jsonp");
			logger.info("callbackΪ"+callback);
            //�����ݴ洢��map���ת����json�������ݣ�Ҳ�����Լ��ֶ�����json��������
            Map<String,Object> map = new HashMap<String,Object>();
//            map.put("aa", "Сƻ��");
//            map.put("bb", "С����");
//            map.put("cc", "С����");
//            map.put("data", paginatedArrayList);
//            JSONObject json = JSONObject.fromObject(map);//��map����ת����json��������
//            result = json.toString();//��result��ֵ�����ݸ�ҳ��
        }  catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
	}
	
	private void addTest(List<Map<String, String>> p){
		for (int i = 1; i < 22; i++) {
			HashMap<String, String> map = new HashMap<>();
			map.put("id",i+"");
			map.put("name","��������"+1);
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


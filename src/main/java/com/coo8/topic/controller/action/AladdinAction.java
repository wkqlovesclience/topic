package com.coo8.topic.controller.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coo8.btoc.model.catalog.Category;
import com.coo8.btoc.util.ReloadableConfig;
import com.coo8.common.utils.SocketUtil;
import com.coo8.topic.model.AladdinKeywords;
import com.coo8.topic.model.AladdinKeywordsRef;

@Namespace("/Aladdin")
public class AladdinAction extends AladdinBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5955271619849439543L;

	private static Logger logger = LoggerFactory.getLogger(AladdinAction.class);

	private List<Category> catalogList;
	private String tags = "";

	private static String catalogIds = null; // 设置泛需求品牌范围
												// 100:大家电,102:生活电器,103:厨卫电器

	static {
		try {
			catalogIds = ReloadableConfig.getProperty("firstCategoryId", "cat18000007");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			catalogIds = "cat18000007";
		}
	}

	/**
	 * 阿拉丁生成列表页面 列表页面
	 * 
	 * @return
	 */
	@Action(value = "list", results = { @Result(name = "success", location = "/jsp/aladdin/list.jsp") })
	public String list() {

		logger.info("Aladdin list start!");
		Map<String, Object> search = new HashMap<String, Object>();
		if (null != names && !"".equals(names)) {
			search.put("names", names);
		}
		if (null != related && !"".equals(related)) {
			search.put("related", related);
		}
		search.put("pageNumber", page_index);
		search.put("pageSize", page_size);
		keywordsList = aladdinKeywordsManager.findLikeByMap(search);
		catalogList = aladdinKeywordsManager.getATGCatalogListByIds(catalogIds); // 获得商品列表
		if (!keywordsList.isEmpty() && !catalogList.isEmpty()) {
			for (AladdinKeywords obj : keywordsList) {
				for (Category cobj : catalogList) {
					if (obj.getCatalogId().equals(cobj.getCataid())) {
						obj.setCatalogName(cobj.getCataname());
						break;
					}
				}
			}
		}

		logger.info("Aladdin list end!");
		return SUCCESS;
	}

	@Action(value = "edit", results = { @Result(name = "success", location = "/jsp/aladdin/create.jsp") })
	public String edit() {

		logger.info("Aladdin edit start!");

		catalogList = aladdinKeywordsManager.getATGCatalogListByIds(catalogIds); // 获得商品列表
		if (null == catalogList) {
			catalogList = new ArrayList<Category>();
		}
		Category category = new Category();
		category.setCataid("0");
		category.setCataname("无");
		catalogList.add(0, category);
		if (null != id) {
			akeywords = aladdinKeywordsManager.getById(id);
		}

		logger.info("Aladdin edit end!");
		return SUCCESS;
	}

	@Action(value = "save", results = {
			@Result(name = "success", type = "redirect", location = "/Aladdin/list.action") })
	public String save() {
		logger.info("Aladdin save start!");
		if (null != akeywords) {
			akeywords.setStatus("Y");
			if (null != akeywords.getId()) {// 修改操作
				// 删除泛需求词之前关联的品牌信息
				Map<String, Object> search = new HashMap<String, Object>();
				search.put("akeywords", akeywords.getId());
				List<AladdinKeywordsRef> lkr = aladdinKeywordsManager.findAllKeywordsRef(search);
				if (!lkr.isEmpty()) {
					for (AladdinKeywordsRef kr : lkr) {
						aladdinKeywordsManager.deleteKeywordsRefById(kr.getId()); // 删除泛需求关联的商品品牌信息
					}
				}
				aladdinKeywordsManager.update(akeywords);
				logger.info("Aladdin save 更新成功！");

			} else { // 新增操作
				aladdinKeywordsManager.save(akeywords);

				logger.info("Aladdin save 新增成功！");
			}
		}
		logger.info("Aladdin save end!");
		return SUCCESS;
	}

	/**
	 * 删除 泛需求词
	 * 
	 * @return
	 */
	@Action(value = "delete", results = {
			@Result(name = "success", type = "redirect", location = "/Aladdin/list.action") })
	public String delete() {
		logger.info("Aladdin delete start!");

		logger.info("Aladdin delete tags为：" + tags);
		if (tags != null && !"".equals(tags)) {
			Set<Integer> hs = this.spli(tags, ";");
			for (Integer tid : hs) {
				Map<String, Object> search = new HashMap<String, Object>();
				search.put("akeywords", tid);
				List<AladdinKeywordsRef> lkr = aladdinKeywordsManager.findAllKeywordsRef(search);
				if (!lkr.isEmpty()) {
					for (AladdinKeywordsRef kr : lkr) {
						aladdinKeywordsManager.deleteKeywordsRefById(kr.getId()); // 删除泛需求关联的商品品牌信息
					}
				}
				aladdinKeywordsManager.deleteById(tid); // 删除泛需求词
				logger.info("Aladdin delete 删除泛需求词id" + tid);
			}
		}
		logger.info("Aladdin delete end!");
		return SUCCESS;
	}

	@Action(value = "sendMessage")
	public void sendMessage() {
		logger.info("Aladdin sendMessage start!");

		ResourceBundle dbConfig = ResourceBundle.getBundle("config");
		String serverIp = dbConfig.getString("serverIp");
		String port = dbConfig.getString("serverPort");

		logger.info("Aladdin sendMessage serverIp：" + serverIp + ",port：" + port);
		Integer serverPort = 0;
		if (null != port && !"".equals(port)) {
			serverPort = Integer.valueOf(port);
		}
		boolean flag = SocketUtil.request(serverIp, serverPort);
		if (flag) {
			writeAjaxStr("yes");
		} else {
			writeAjaxStr("no");
		}

		logger.info("Aladdin sendMessage end!");
	}

	public Set<Integer> spli(String str, String sign) {
		String s[] = str.split(sign);
		List<Integer> ids = new ArrayList<Integer>();
		for (int i = 0; i < s.length; i++) {
			if (!"".equals(s[i])) {
				try {
					ids.add(Integer.parseInt(s[i]));
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
		HashSet<Integer> hs = new HashSet<Integer>();
		if (!ids.isEmpty()) {
			hs.addAll(ids);
		}
		return hs;
	}

	public List<Category> getCatalogList() {
		return catalogList;
	}

	public void setCatalogList(List<Category> catalogList) {
		this.catalogList = catalogList;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	protected void writeAjaxStr(String str) {
		HttpServletResponse response = this.getResponse();
		response.setContentType("text/html");
		response.setCharacterEncoding("GBK");
		try {
			PrintWriter out = response.getWriter();
			out.write(str);
			out.flush();
			out.close();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

}

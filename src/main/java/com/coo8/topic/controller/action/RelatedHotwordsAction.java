package com.coo8.topic.controller.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coo8.common.diamond.PropertiesUtils;
import com.coo8.topic.business.interfaces.IHotSearchwordManager;
import com.coo8.topic.model.HotSearchword;

import gcache.clients.gedis.RoundRobinJedisPool;
import net.sf.json.JSONArray;
import redis.clients.jedis.Jedis;

/**
 * 相关热词接口
 * 
 * @author fanqingxia
 *
 */
@Namespace("/relatedHotwords")
public class RelatedHotwordsAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3916604997564669412L;

	private IHotSearchwordManager hotSearchwordManager;

	@Action(value = "findReatedHotWords")
	public void findReatedHotWords() {

		String requestParam = getStringParam("requestParam");
		Jedis jedis = RoundRobinJedisPool.autoGetAndReturnJedis(
				"10.58.22.191:2181,10.58.22.192:2181,10.58.22.193:2181,10.58.50.149:2181,10.58.50.150:2181",
				"RELATED_HOTWORDS");
		String relatedHotwords = jedis.get(requestParam);

		if (StringUtils.isBlank(relatedHotwords)) {
			Map<String, Object> search = new HashMap<String, Object>();
			search.put("title", requestParam);

			/**
			 * 相关搜索词
			 */
			JSONArray jsonArray = getHotkeyRelatedKeywords(search);

			jedis.set(requestParam, jsonArray.toString());
			jedis.close();
		}

	}

	// 热搜详情页——相关搜索词
	private JSONArray getHotkeyRelatedKeywords(Map<String, Object> search) {
		JSONArray jsonArray = null;

		search.put("site", "gome");

		List<HotSearchword> hWords = hotSearchwordManager.getHotkeyRelatedKeywords(search);
		String hotWordsBaseUrl = PropertiesUtils.getStringValueByKey("hotWordsBaseUrl");

		if (hWords != null && !hWords.isEmpty()) {

			for (HotSearchword hotSearchword : hWords) {
				String createTime = hotSearchword.getCreateTime();
				Integer id = hotSearchword.getId();

				String hotwordsUrl = hotWordsBaseUrl + createTime + "/" + id + "/";
				hotSearchword.setHotwordsUrl(hotwordsUrl);

			}
			jsonArray = JSONArray.fromObject(hWords);
		}

		return jsonArray;
	}

}

package com.coo8.topic.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.coo8.btoc.business.interfaces.queue.QueueManager;
import com.coo8.common.diamond.PropertiesUtils;
import com.coo8.common.utils.HotWordsUtil;
import com.coo8.topic.business.interfaces.IHotSearchwordManager;
import com.coo8.topic.model.HotSearchword;
import org.apache.commons.lang3.StringUtils;

import gcache.clients.gedis.RoundRobinJedisPool;
import redis.clients.jedis.Jedis;

public class taskPublish{
	
	@Autowired
	private IHotSearchwordManager hotSearchwordManager;
	
	@Autowired
	private QueueManager queueManager;
	
	// 发布需要的配置信息
	private static final int hotsearch_rtype = PropertiesUtils.getIntValueByKey("hotsearch_rtype");// 商品排行榜详情页引用类型
	private static final int hotsearch_templateId = PropertiesUtils.getIntValueByKey("hotsearch_templateId");// 排行榜详情页模板Id
	
	//标识ID cache用 目前未启动
	private static final String seo_id = "getSeoHotPublishStatus";
	
	private static Logger log = LoggerFactory.getLogger(taskPublish.class);
	
	//作业1 
	public void publishJobForUncreateMethod(){
		log.info("Task作业publishJobForUncreateMethod 部分发布");
		//setp.1 查询2000 未发布 热词
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("qchecked", 0);
		paramMap.put("numLimitStart", "1");
		paramMap.put("numLimitEnd", "2000");
		List<Integer> hotSearchList = this.hotSearchwordManager.listLimit(paramMap);
		if(hotSearchList.isEmpty()){
			log.info("本次更新未发现 未发布的热词");
		}
		//setp.2 批量更新
		for (Integer hoketId : hotSearchList) {
			HotSearchword hWord = hotSearchwordManager.getById(hoketId);
			hotSearchwordManager.publish(hWord);
			queueManager.publish(hoketId, hotsearch_templateId, 0, hotsearch_rtype, 2);
			log.info("Task publishJobForUncreateMethod 热词id!" + hoketId);
		}
	}
	
	//作业2 
	public void publishJobForRebuildAllMethod(){
		log.info("Task作业publishJobForRebuildAllMethod 全量发布");
		//setp.1 查询参数
		Jedis j = null;
		try{
			j = RoundRobinJedisPool.autoGetAndReturnJedis(HotWordsUtil.REATED_HOTWORDS_DUBBO_IP,
					HotWordsUtil.REATED_HOTWORDS_DUBBO_NODE);
		}catch(Exception e) {
			log.error(e.getMessage(), e);
			return;
		}finally{
			if(null != j){
				j.close();
			}		
		}
		String numSat = j.get("cutedId");
		String numEnd = j.get("cutNum");
		if(StringUtils.isBlank(numSat) || StringUtils.isBlank(numEnd)){
			log.info("publishJobForRebuildAllMethod 重置内存数字 初始化默认 1,5000");
			j.set("cutedId", "0");
			j.set("cutNum", "5000");
			numSat = "0";
			numEnd = "5000";
		}
		log.info("全量发布 从" + numSat + "开始 共更新" + numEnd + "条数据");
		//setp.2 查询已发布数据 Limit numSat,numEnd
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("qchecked", 1);
		paramMap.put("numLimitStart", numSat);
		paramMap.put("numLimitEnd", numEnd);
		List<Integer> hotSearchList = this.hotSearchwordManager.listLimit(paramMap);
		//setp.3 批量更新
		for (Integer hoketId : hotSearchList) {
			HotSearchword hWord = hotSearchwordManager.getById(hoketId);
			hotSearchwordManager.publish(hWord);
			queueManager.publish(hoketId, hotsearch_templateId, 0, hotsearch_rtype, 2);
			log.info("Task publishJobForRebuildAllMethod 热词id!" + hoketId);
		}
		//setp.4 截取最后一个id   if 已更新数据 < 应更新数据:重置更新1开始
		if(hotSearchList.size() < Integer.valueOf(numEnd)){
			j.set("cutedId", "0");
		}else{
			j.set("cutedId", hotSearchList.get(hotSearchList.size()-1) + "");
		}
	}

}

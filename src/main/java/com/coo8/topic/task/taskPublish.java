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
	
	// ������Ҫ��������Ϣ
	private static final int hotsearch_rtype = PropertiesUtils.getIntValueByKey("hotsearch_rtype");// ��Ʒ���а�����ҳ��������
	private static final int hotsearch_templateId = PropertiesUtils.getIntValueByKey("hotsearch_templateId");// ���а�����ҳģ��Id
	
	//��ʶID cache�� Ŀǰδ����
	private static final String seo_id = "getSeoHotPublishStatus";
	
	private static Logger log = LoggerFactory.getLogger(taskPublish.class);
	
	//��ҵ1 
	public void publishJobForUncreateMethod(){
		log.info("Task��ҵpublishJobForUncreateMethod ���ַ���");
		//setp.1 ��ѯ2000 δ���� �ȴ�
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("qchecked", 0);
		paramMap.put("numLimitStart", "1");
		paramMap.put("numLimitEnd", "2000");
		List<Integer> hotSearchList = this.hotSearchwordManager.listLimit(paramMap);
		if(hotSearchList.isEmpty()){
			log.info("���θ���δ���� δ�������ȴ�");
		}
		//setp.2 ��������
		for (Integer hoketId : hotSearchList) {
			HotSearchword hWord = hotSearchwordManager.getById(hoketId);
			hotSearchwordManager.publish(hWord);
			queueManager.publish(hoketId, hotsearch_templateId, 0, hotsearch_rtype, 2);
			log.info("Task publishJobForUncreateMethod �ȴ�id!" + hoketId);
		}
	}
	
	//��ҵ2 
	public void publishJobForRebuildAllMethod(){
		log.info("Task��ҵpublishJobForRebuildAllMethod ȫ������");
		//setp.1 ��ѯ����
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
			log.info("publishJobForRebuildAllMethod �����ڴ����� ��ʼ��Ĭ�� 1,5000");
			j.set("cutedId", "0");
			j.set("cutNum", "5000");
			numSat = "0";
			numEnd = "5000";
		}
		log.info("ȫ������ ��" + numSat + "��ʼ ������" + numEnd + "������");
		//setp.2 ��ѯ�ѷ������� Limit numSat,numEnd
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("qchecked", 1);
		paramMap.put("numLimitStart", numSat);
		paramMap.put("numLimitEnd", numEnd);
		List<Integer> hotSearchList = this.hotSearchwordManager.listLimit(paramMap);
		//setp.3 ��������
		for (Integer hoketId : hotSearchList) {
			HotSearchword hWord = hotSearchwordManager.getById(hoketId);
			hotSearchwordManager.publish(hWord);
			queueManager.publish(hoketId, hotsearch_templateId, 0, hotsearch_rtype, 2);
			log.info("Task publishJobForRebuildAllMethod �ȴ�id!" + hoketId);
		}
		//setp.4 ��ȡ���һ��id   if �Ѹ������� < Ӧ��������:���ø���1��ʼ
		if(hotSearchList.size() < Integer.valueOf(numEnd)){
			j.set("cutedId", "0");
		}else{
			j.set("cutedId", hotSearchList.get(hotSearchList.size()-1) + "");
		}
	}

}

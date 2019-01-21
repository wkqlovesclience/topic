package com.coo8.btoc.business.interfaces.html;

import java.util.List;

import com.coo8.btoc.model.html.StaticHtml;

public interface StaticHtmlManager {
	
	void createHtml(StaticHtml html);

	void updateHtml(StaticHtml html);
	int updateStaticPublish(int id);
	int updateCatalogPublish(int id, int staticId);
	
	void deleteStaticHtml(StaticHtml html);
	
	StaticHtml selectById(Integer id);
	List<StaticHtml> queryHtmls(StaticHtml html);
	void deleteHtmlandRedis(StaticHtml html);//ɾ����̬ҳ�����������̬ҳ�еĲ�Ʒλ�ͻ���
	void updateHtmlandRedis(StaticHtml html);//���ľ�̬ҳ�����������̬ҳ�еĲ�Ʒλ�ͻ���

}
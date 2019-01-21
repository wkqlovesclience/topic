package com.coo8.topic.business.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.coo8.btoc.util.ReloadableConfig;
import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.common.utils.ConstantUtil;
import com.coo8.topic.business.interfaces.IAnchorKeywordsManager;
import com.coo8.topic.business.interfaces.INewsManager;
import com.coo8.topic.model.AnchorKeywords;
import com.coo8.topic.model.LabelArtRel;
import com.coo8.topic.model.News;
import com.coo8.topic.model.NewsAssess;
import com.coo8.topic.persistence.interfaces.INewsDAO;

/**
 * @author JIANGCHENG
 * @version 1.0
 * @since 1.0
 */

public class NewsManagerImpl implements INewsManager {
	
	private final int OPERATION_ASSESS_CREATE =0; //创建文章评价记录
	private final int OPERATION_ASSESS_GOOD = 1;  //文章有用评价
	private final int OPERATION_ASSESS_BAD  = 2;  //文章无用评价
	private final int BASE_COUNT = 2000; //评论文件评论数限制
	 private  static Logger logger = LoggerFactory.getLogger(NewsManagerImpl.class);
	private IAnchorKeywordsManager anchorKeyManager;
	
	
	private INewsDAO newsDAO;

	public void setNewsDAO(INewsDAO dao) {
		this.newsDAO = dao;
	}
	/**
	 * @return the anchorKeyManager
	 */
	public IAnchorKeywordsManager getAnchorKeyManager() {
		return anchorKeyManager;
	}
	/**
	 * @param anchorKeyManager the anchorKeyManager to set
	 */
	public void setAnchorKeyManager(IAnchorKeywordsManager anchorKeyManager) {
		this.anchorKeyManager = anchorKeyManager;
	}

	@Transactional(readOnly = true)
	@Override
	public News getById(java.lang.Integer id) {
		return newsDAO.getById(id);
	}

	@Transactional
	@Override
	public void deleteById(java.lang.Integer id) {
		newsDAO.deleteById(id);
	}

	@Transactional
	@Override
	public String save(News entity) {
		if(entity.getId()!=null&&!"".equals(String.valueOf(entity.getId()))){
			newsDAO.update(entity);
			return entity.getId().toString();
		}else{
			return newsDAO.save(entity);
		}
		
	}

	@Transactional
	public void update(News entity) {
		newsDAO.update(entity);
	}

	@Transactional(readOnly = true)
	public List<News> findListByMap(Map<String, Object> search) {
		return newsDAO.findListByMap(search);
	}

	@Transactional(readOnly = true)
	public PaginatedList<News> findAll(Map<String, Object> search) {
		return newsDAO.findByMap(search);
	}
	@Override
	public NewsAssess newsAssess(int id, int operation) {
		int temp = 0;
		NewsAssess assessObj = null;
		if(operation == OPERATION_ASSESS_CREATE){
			int ret = newsDAO.newsAssessCreate(id);
			if(ret != -1){
				return newsDAO.getAssessById(id);
			}
		}else if(operation == OPERATION_ASSESS_GOOD){
			temp = newsDAO.newsAssessGood(id);
			assessObj = newsDAO.getAssessById(id);
			if(temp != -1 && null != assessObj){
				return assessObj;
			}
		}else if(operation == OPERATION_ASSESS_BAD){
			temp = newsDAO.newsAssessBad(id);
			assessObj = newsDAO.getAssessById(id);
			if(temp != -1 && null != assessObj){
				return assessObj;
			}
		}
		return assessObj;
	}

	@Override
	public NewsAssess getAssessById(int id) {
		return newsDAO.getAssessById(id);
	}
	@Override
	public String getAssessValues(NewsAssess assessObj){
		JSONArray jsonArray = JSONArray.fromObject(assessObj);
		if(null != jsonArray ){
			return jsonArray.toString();
		}
		return "";
	}

	@Override
	public void deleteAssessById(Integer id) {
		newsDAO.deleteAssessById(id);
	}

	/**
	 * 检查文章ID所在的评论文件是否存在，不存在则创建
	 * @param 文章编号
	 * @reutn 文件名
	 */
	@Override
	public synchronized String checkCreateNewsAssessXml(Integer newsId) {
		String fileName = "newsAssess";
		
		if(null != newsId && newsId > BASE_COUNT){
			int count = newsId / BASE_COUNT ;
			
			fileName += "-" + count ;
		}
		//文件名
		fileName = fileName + ".xml";
		//获得文件存放目录
		String fileDir = ReloadableConfig.getProperty(
				"NewsAssessAdress", "/app/publish/catalog/newassess/");

		if(null != fileDir && !"".equals(fileDir)){
			char filechar = fileDir.charAt(fileDir.length()-1);
			
			if("/".equals(String.valueOf(filechar))){
				fileDir += "/" ;
			}
		}else{
			fileDir = "/app/publish/catalog/newassess/";
		}
		//文件URL
		String fileUrl = fileDir + fileName;
		File file = new File(fileUrl);
		File dir  = new File(fileDir);
		try {
			if(!dir.exists()){
				dir.mkdirs();
			}
			if(!file.exists()){
				file.createNewFile();
				PrintWriter pw = new PrintWriter(file);
				pw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
				pw.write("<newsassess>");
				pw.write("<assess newsid=\"" + newsId + "\" good=\"0\" bad=\"0\"/>");
				pw.write("</newsassess>");
				pw.flush();
				pw.close();
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return fileUrl;
	}
	
	/**
	 * 根据文章ID，xml文件所在文章评论信息
	 * @param newsId
	 * @param xmlFileURL
	 * @return NewsAssess
	 */
	@Override
	public synchronized NewsAssess parseXml2NewsAssessById(Integer newsId, String xmlFileURL) {
		if(null == newsId ){
			return null;
		}
		NewsAssess  assessObj = new NewsAssess(newsId,0,0);
		File file = new File(xmlFileURL);
		if( null != xmlFileURL && !"".equals(xmlFileURL) && file.exists()){
			//xml 文件解析
			try {
				SAXReader reader = new SAXReader();
				Document  document  = reader.read(file);
				Element rootElt = document.getRootElement(); // 获取根节点
				@SuppressWarnings("unchecked")
				Iterator<Element> iter = rootElt.elementIterator("assess");
				boolean flag = false; //评价内容存在不存在 ；false在该文件中不存在
				while(iter.hasNext()){
					Element ele = (Element)iter.next();
					String id = ele.attributeValue("newsid");
					if(null != id && id.equals(newsId.toString())){
						flag = true;
						String good = ele.attributeValue("good");
						String bad  = ele.attributeValue("bad");
						if(null != good && !"".equals(good)){
							assessObj.setGood(Integer.valueOf(good.trim()));
						}
						if(null != bad && !"".equals(bad)){
							assessObj.setBad(Integer.valueOf(bad.trim()));
						}
						break;
					}
				}
				//遍历整个XML文件未发现改文章评论则添加
				if(!flag){
					XMLWriter writer = new XMLWriter(new FileWriter(file));
					Element assess = rootElt.addElement("assess");
					assess.addAttribute("newsid", newsId.toString());
					assess.addAttribute("good", "0");
					assess.addAttribute("bad", "0");
					writer.write(document);
					writer.close();
				}
			} catch (DocumentException e) {
				logger.error(e.getMessage(), e);
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
		return assessObj;
	}

	/**
	 * 改变评论信息并保存到xml文件中
	 * @param newsId    
	 * @param xmlFileURL
	 * @param type 1:有用 2:无用
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@Override
	public synchronized NewsAssess changeNewsAssess2XmlById(Integer newsId,
			String xmlFileURL, int type) {
		if(newsId == null){
			return null;
		}
		NewsAssess  assessObj = new NewsAssess(newsId,0,0);
		File file = new File(xmlFileURL);
		if( null != xmlFileURL && !"".equals(xmlFileURL) && file.exists()){
			try {
				SAXReader reader = new SAXReader();
				Document  document  = reader.read(file);
				Element rootElt = document.getRootElement(); // 获取根节点
				@SuppressWarnings("unchecked")
				Iterator<Element> iter = rootElt.elementIterator("assess");
				while(iter.hasNext()){
					Element ele = (Element)iter.next();
					String id = ele.attributeValue("newsid");
					if(null != id && id.equals(newsId.toString())){
						String good = ele.attributeValue("good");
						String bad  = ele.attributeValue("bad");
						int good_ = 0;
						int bad_  = 0;
						if(null != good && !"".equals(good)){
							good_ = Integer.parseInt(good);
						}
						if(null != bad && !"".equals(bad)){
							bad_ = Integer.parseInt(bad);
						}
						if(type == 1){
							good_ ++ ;
							ele.setAttributeValue("good", "" + good_);
						}else if(type == 2){
							bad_  ++;
							ele.setAttributeValue("bad", "" + bad_);
						}
						assessObj.setGood(good_);
						assessObj.setBad(bad_);
						break;
					}
				}
				XMLWriter  writer = new XMLWriter(new FileWriter(file));
				writer.write(document);	
				writer.close();
			}  catch (DocumentException e) {
				logger.error(e.getMessage(), e);
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
		
		return assessObj;
	}
	/**
	 * 文章发布到线上
	 */
	@Override
	public int publishNews(int id, String site) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("newsId", id);
		map.put("type", ConstantUtil.PUBLISH_FILE_TYPE_ONLINE);
		map.put("site", site);
		return newsDAO.publishCoo8GomeNews(map);
	}
	/**
	 * 文章发布到预览
	 */
	@Override
	public int publishNewsTest(int id, String site) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("newsId", id);
		map.put("type", ConstantUtil.PUBLISH_FILE_TYPE_FORTEST);
		map.put("site", site);
		return newsDAO.publishCoo8GomeNews(map);
	}
	/* (non-Javadoc)
	 * @see com.coo8.topic.business.interfaces.INewsManager#getContainAnchor(java.lang.String)
	 */
	@Override
	public List<AnchorKeywords> getContainAnchor(String content) {
		List<AnchorKeywords> allAnchorKeywords= anchorKeyManager.findAllAnchorKeywordsList();
		List<AnchorKeywords>  anchorContains = new ArrayList<AnchorKeywords>();
		
		if(!allAnchorKeywords.isEmpty()){
			for(AnchorKeywords anchorKey :allAnchorKeywords){
				String keyName = anchorKey.getKeyName();
				if(content.contains(keyName)){
					anchorContains.add(anchorKey);
				}
			}
		}
		return anchorContains;
	}
	/* (non-Javadoc)
	 * @see com.coo8.topic.business.interfaces.INewsManager#getAnchorById(java.lang.Integer)
	 */
	@Override
	public AnchorKeywords getAnchorById(Integer anchorId) {
		return anchorKeyManager.getById(anchorId);
	}
	
	/**
	 * 根据新闻ID获取
	 */
	@Override
	public List<LabelArtRel> getNewsTagsById(Integer newsId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", newsId);
		return newsDAO.getNewsTagsById(map);
	}
	

  
}

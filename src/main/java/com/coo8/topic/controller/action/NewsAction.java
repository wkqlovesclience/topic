package com.coo8.topic.controller.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coo8.btoc.util.ReloadImgUtils;
import com.coo8.btoc.util.StringUtils;
import com.coo8.btoc.util.UploadImg;
import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.common.utils.ConstantUtil;
import com.coo8.topic.business.interfaces.IKeywordsManager;
import com.coo8.topic.business.interfaces.IKeywordsRelManager;
import com.coo8.topic.labelart.modal.LabelRel;
import com.coo8.topic.labelart.service.inter.LabelManager;
import com.coo8.topic.labelart.service.inter.LabelRelManager;
import com.coo8.topic.model.AnchorKeywords;
import com.coo8.topic.model.ComparatorAnchor;
import com.coo8.topic.model.Keywords;
import com.coo8.topic.model.KeywordsRel;
import com.coo8.topic.model.News;
import com.coo8.topic.model.NewsAssess;
import com.coo8.topic.model.Titles;
import com.gome.bg.gfs.constant.GFSConstant;
import com.gome.bg.gfs.dto.UploadResultDto;
import com.gome.bg.gfs.utils.GFSUtil;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.PageOrientation;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import net.sf.json.JSONArray;

/**
 * @author JIANGCHENG
 * @version 1.0
 * @since 1.0
 */

@Namespace("/News")
public class NewsAction extends NewsBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3474280511806496703L;
	private static Logger logger = LoggerFactory.getLogger(NewsAction.class);
	private static final int COMMAND_LISTALL = 1;
	protected IKeywordsManager keywordsManager;
	protected IKeywordsRelManager keywordsRelManager;
	protected LabelManager labelManager;
	protected LabelRelManager labelRelManager;
	private String tags = "";
	protected static final String DEFAULT_SORT_COLUMNS = null;
	private News news;
	private int newsid = 0;
	private Titles title_key;
	private int command = 0;
	private String newsTestUrl2;
	private String keys;
	private String[] keyss;
	private File pic;
	private String picFileName;
	
	
	public String getPicFileName() {
		return picFileName;
	}

	public void setPicFileName(String picFileName) {
		this.picFileName = picFileName;
	}

	public File getPic() {
		return pic;
	}

	public void setPic(File pic) {
		this.pic = pic;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}
	
	public IKeywordsManager getKeywordsManager() {
		return keywordsManager;
	}

	public void setKeywordsManager(IKeywordsManager keywordsManager) {
		this.keywordsManager = keywordsManager;
	}
	
	
	public LabelManager getLabelManager() {
		return labelManager;
	}

	public void setLabelManager(LabelManager labelManager) {
		this.labelManager = labelManager;
	}
   
	
	public LabelRelManager getLabelRelManager() {
		return labelRelManager;
	}

	public void setLabelRelManager(LabelRelManager labelRelManager) {
		this.labelRelManager = labelRelManager;
	}

	/**
	 * 设置专题标签
	 * 
	 * @param titleList
	 */
	private void setTitleTag(PaginatedList<News> newss) {
		// 显示标签
		if (newss != null && !newss.isEmpty()) {
			Map<String, Object> searchKey = new HashMap<String, Object>();
			List<Integer> aids = getNewsIdsByList(newss);
			if (null != aids) {
				searchKey.put("aids", aids);
			}
			searchKey.put("site", getTopicSite());
			searchKey.put("types", TYPES_TAGS);
			List<Keywords> keyList = keywordsManager.findAllTab(searchKey);
			Map<Integer, String> mapkey = new HashMap<Integer, String>();
			if (keyList != null && !keyList.isEmpty()) {
				for (Keywords k : keyList) {
					Integer newsId = k.getArticleId();
					String tag = k.getNames();
					if (mapkey.containsKey(newsId)) {
						tag += "," + mapkey.get(newsId);
					}
					mapkey.put(newsId, tag);
				}
			}
			for (int i = 0; i < newss.size(); i++) {
				News t = (News) newss.get(i);
				if (mapkey.get(t.getId()) != null) {
					newss.get(i).setTags(mapkey.get(t.getId()));
				}
			}

		}
	}
	
	private List<Integer> getNewsIdsByList(List<News> newss) {
		List<Integer> newids = new ArrayList<Integer>();
		if (null != newss && !newss.isEmpty()) {
			for (News news : newss) {
				newids.add(news.getId());
			}
		}
		return newids;
	}
	
	
	@Action(value = "checkPaths")
	public void checkPaths() {
		logger.info("News checkPaths start!");
		String str = "";
		if (news != null) {
			Map<String, Object> search = new HashMap<String, Object>();
			search.put("paths", news.getPaths());
			search.put("site", getTopicSite());
			logger.info("Titles checkPaths 查询数据参数：" + search);
			List l = titlesManager.findListByMap(search);
			if (l.isEmpty()) {
				str = "yes";
			}
		}
		writeAjaxStr(str);
		logger.info("Titles checkPaths end!");
	}
	
	@Action(value = "checkKeywords")
	public void checkKeywords() {
		logger.info("News checkKeywords start!");
		if (keys != null && !"".equals(keys)) {
			Map<String, Object> search = new HashMap<String, Object>();
			search.put("types", TYPES_KEYWORDS);
			search.put("names", keys.trim());
			search.put("site", getTopicSite());
			List l = keywordsManager.findAll(search);
			if (l.isEmpty()) {
				writeAjaxStr("yes");
			}
		}
		if (tags != null && !"".equals(tags)) {
			String s[] = tags.split(",");
			for (int i = 0; i < s.length; i++) {
				Map<String, Object> search = new HashMap<String, Object>();
				search.put("types", TYPES_TAGS);
				search.put("names", s[i].trim());
				search.put("site", getTopicSite());
				List l = keywordsManager.findAll(search);
				if (!l.isEmpty()) {
					writeAjaxStr("no");
				}
			}

		}
		if (keyss != null || !"undefined".equals(keyss[0])) {
			Map<String, Object> search = new HashMap<String, Object>();
			search.put("types", TYPES_LABEL);
			search.put("names", keyss[0].trim());
			search.put("site", getTopicSite());
			List l = keywordsManager.findAll(search);
			if (l.isEmpty()) {
				writeAjaxStr("yes");
			}
		}
		writeAjaxStr("no");
		logger.info("Titles checkKeywords end!");
	}


	@Action(value = "list", results = { @Result(name = "success", location = "/jsp/news/list.jsp") })
	public String list() {
		logger.info("News list start!");

		Map<String, Object> search = new HashMap<String, Object>();

		if (topic != null && !"".equals(topic)) {
			search.put("topic", topic);
		}
		if (titleId != null && titleId > 0) {
			search.put("titleId", titleId);
		} else if (getRequest().getParameter("titleId") != null) {
			titleId = StringUtils.parseInt(this.getRequest().getParameter("titleId"), 0);
			if (titleId > 0) {
				search.put("titleId", titleId);
			}
		}
		String paths = "";
		String titlename = "";
		if (null != titleId && titleId > 0) {
			Titles title = titlesManager.getById(titleId);
			if (title != null && title.getPaths() != null && !"".equals(title.getPaths())) {
				paths = title.getPaths();
				titlename = title.getTitle();
			} else if (title != null && title.getPaths() != null) {
				paths = title.getId().toString();
			}
		}
		search.put("site", getTopicSite());
		search.put("pageNumber", pageNumber);
		search.put("pageSize", page_size);
		search.put("sortColumns", "create_Time desc");
		logger.info("News list 查询参数：" + search);

		listNews = newsManager.findAll(search);
		newsTestUrl2 = setNewsTestUrl(paths);
		getRequest().setAttribute("titlename", titlename);
		setTitleTag(listNews);
		logger.info("News list end!");
		return "success";
	}

	/**
	 * 列举出所有的文章
	 * 
	 */
	@Action(value = "listAllNews", results = { @Result(name = "success", location = "/jsp/news/listAllNews.jsp") })
	public String listAllNews() {

		logger.info("News listAllNews start!");
		Map<String, Object> search = new HashMap<String, Object>();
		String param = getRequest().getParameter("param");
		if (null != param && !"".equals(param)) {
			try {
				topic = java.net.URLDecoder.decode(new String(param.getBytes("GBK"), "UTF-8"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error(e.getMessage(), e);
			}
		}
		if (null != news) {
			if (null != news.getTopic() && !"".equals(news.getTopic())) {
				topic = decode(news.getTopic());
			}
		}
		if (null != creator && !"".equals(creator)) {
			search.put("creator", creator);
		}
		if (null != createTime) {
			search.put("createTime", createTime);
		}
		if (topic != null && !"".equals(topic)) {
			topic = decode(topic);
			search.put("topic", topic);
		}
		search.put("site", getTopicSite());
		search.put("pageNumber", pageNumber);
		search.put("pageSize", page_size);
		search.put("sortColumns", "create_Time desc");

		logger.info("News listAllNews 查询数据参数：" + search);
		listNews = newsManager.findAll(search);

		if (null != listNews && !listNews.isEmpty()) {
			int btitleId = 0;
			Titles title = null;
			String paths = ""; // 文章所在专题路径
			for (News news : listNews) {
				btitleId = news.getTitleId();
				title = titlesManager.getById(btitleId);
				if (title != null && title.getPaths() != null && !"".equals(title.getPaths())) {
					paths = title.getPaths();
				} else if (title != null && title.getId() != null) {
					paths = title.getId().toString();
				}
				news.setPaths(paths);
				String newsTestUrl = setNewsTestUrl(paths);
				news.setNewsTestUrl(newsTestUrl);
			}
		}
		logger.info("News listAllNews end!");
		setTitleTag(listNews);
		return "success";
	}
	
	@Action(value = "toExcel")
	public void toExcel() {
		logger.info("News toExcel start!");
		List<News> list = new ArrayList<News>();
		Map<String, Object> search = new HashMap<String, Object>();

		String idBegin = this.getRequest().getParameter("idBegin");
		String idEnd = this.getRequest().getParameter("idEnd");
		if (null != idBegin && !"".equals(idBegin)) {
			search.put("idBegin", Integer.valueOf(idBegin));
		}
		if (null != idEnd && !"".equals(idEnd)) {
			search.put("idEnd", Integer.valueOf(idEnd));
		}
		String site = getTopicSite();
		search.put("site", site);
		search.put("sortColumns", "id desc");
		list = newsManager.findListByMap(search);

		String[] titlename = { "编号", "文章标题", "关键词", "创建人", "对应链接"};

		WritableWorkbook workbook = null;
		OutputStream os = null;
		// 获取结果集
		try {
			// 定义生成excel文件的类型和名称
			String fileName = "文章列表";
			fileName = new String(fileName.getBytes("gbk"), "ISO-8859-1");
			HttpServletResponse response = getResponse();
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");
			os = response.getOutputStream();

			workbook = Workbook.createWorkbook(os);

			WritableSheet sheet = workbook.createSheet("文章列表", 0);

			sheet.setPageSetup(PageOrientation.LANDSCAPE);

			sheet.setColumnView(0, 10);// 第一列，宽度
			sheet.setColumnView(1, 50);
			sheet.setColumnView(2, 50);
			sheet.setColumnView(3, 12);
			sheet.setColumnView(4, 50);
			// 设置行高
			sheet.setRowView(0, 600, false);

			// 设置字体
			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 12, WritableFont.BOLD);
			titleFont.setColour(jxl.format.Colour.RED);
			WritableFont normalFont = new WritableFont(WritableFont.createFont("宋体"), 11);

			// 用于标题
			WritableCellFormat wcf_title = new WritableCellFormat(titleFont);
			wcf_title.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_title.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直对齐
			wcf_title.setAlignment(Alignment.CENTRE); // 水平对齐
			wcf_title.setWrap(true); // 是否换行
			wcf_title.setBackground(jxl.format.Colour.YELLOW);// 设置背景颜色

			// 用于正文中文
			WritableCellFormat wcf_main = new WritableCellFormat(normalFont);
			wcf_main.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_main.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直对齐
			wcf_main.setAlignment(Alignment.CENTRE); // 水平对齐
			wcf_main.setWrap(true); // 是否换行

			for (int i = 0; i < titlename.length; i++) {
				jxl.write.Label labelCFC = new jxl.write.Label(i, 0, titlename[i], wcf_title);
				sheet.addCell(labelCFC);
			}
			for (int i = 0; i < list.size(); i++) {
				News news1 = (News) list.get(i);
				int j = 0;
				Label label = null;

				if(news1 != null){
					label = new jxl.write.Label(j++, i + 1, String.valueOf(news1.getId()), wcf_main);
					sheet.addCell(label);
	
					label = new jxl.write.Label(j++, i + 1, news1.getTopic(), wcf_main);
					sheet.addCell(label);
					
					label = new jxl.write.Label(j++, i + 1, news1.getKeywords(), wcf_main);
					sheet.addCell(label);
	
					label = new jxl.write.Label(j++, i + 1, news1.getCreator(), wcf_main);
					sheet.addCell(label);
					String paths = "";
					Integer titleIds = news1.getTitleId();
					Titles title = titlesManager.getById(titleIds);
					if (title != null && title.getPaths() != null && !"".equals(title.getPaths())) {
						paths = title.getPaths();
					} else if (title != null && title.getId() != null) {
						paths = title.getId().toString();
					}
					String newsUrl = setNewsTestUrl(paths)+news1.getId()+".html";
					label = new jxl.write.Label(j++, i + 1,newsUrl, wcf_main);
					sheet.addCell(label);
				}
			}
			workbook.write();
			response.flushBuffer();
			workbook.close();
			os.flush();
			os.close();
		} catch (FileNotFoundException e) {
			logger.error("News toExcel FileNotFoundException异常：" + e.getMessage(),e);
		} catch (IOException e) {
			logger.error("News toExcel IOException异常：" + e.getMessage(),e);
		} catch (RowsExceededException e) {
			logger.error("News toExcel RowsExceededException异常：" + e.getMessage(),e);
		} catch (WriteException e) {
			logger.error("News toExcel WriteException异常：" + e.getMessage(),e);
		} finally {
			if (null != workbook) {
				try {
					workbook.close();
				} catch (WriteException e) {
					logger.error("News toExcel WriteException异常：" + e.getMessage(),e);
				} catch (IOException e) {
					logger.error("News toExcel IOException异常：" + e.getMessage(),e);
				}
			}
			if (null != os) {
				try {
					os.close();
				} catch (IOException e) {
					logger.error("News toExcel IOException异常：" + e.getMessage(),e);
				}
			}
		}
		logger.info("News toExcel end!");
	}
	

	/**
	 * 设置文章访问ＵＲＬ
	 * 
	 * @param paths
	 * @param newsId
	 */
	private String setNewsTestUrl(String paths) {

		String site = getTopicSite();
		String newsTestUrl = "";
		if (site.equals(ConstantUtil.SITE_FLAG_COO8)) {
			newsTestUrl = ConstantUtil.DOMAIN_COO8_BASEURL + ConstantUtil.DOMAIN_COO8_TITLE_BASEURL
					+ ConstantUtil.DOMAIN_TEST_BASEURL + "/" + paths + ConstantUtil.DOMAIN_COO8_NEWS_BASEURL + "/";
		} else {
			if (paths != null && !"".equals(paths)) {
				// 预览按钮显示真正生成的url
				newsTestUrl = ConstantUtil.DOMAIN_GOME_BASEURL + "/"
				// 新的paths 是topic/xinnianlipin 截取topic/之后的内容
						+ paths + ConstantUtil.DOMAIN_GOME_NEWS_BASEURL + "/";

			}

		}
		return newsTestUrl;
	}

	@Action(value = "show", results = { @Result(name = "success", type = "redirect", location = "/jsp/news/list.jsp") })
	public String show() {
		logger.info("News show start!");
		logger.info("News show end!");
		return "success";
	}

	@Action(value = "create", results = { @Result(name = "success", location = "/jsp/news/create.jsp") })
	public String create() {
		logger.info("News create start!");
		logger.info("News create end!");
		return "success";
	}
	/**
	 * 比较两个字符串互相包含
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static boolean compareString(String s1,String s2){
		boolean flag = false;
		if(s1.contains(s2) || s2.contains(s1)){
			flag = true;
		}
		return flag;
	}

	//筛选要匹配的关键词
	public  List<AnchorKeywords>   getResultAnchorKeywords(List<AnchorKeywords> matchAnchor){
	
		//将关键词排序按照优先级
		TreeSet<Integer>  set = new TreeSet<Integer>();
		for(AnchorKeywords anchor:matchAnchor){
			set.add(anchor.getYouxianji());
		}
		
		//按照优先级分组放到一个list中
		List<List<AnchorKeywords>> fenzuList = new ArrayList<List<AnchorKeywords>>();
		for(Integer s:set){
			List<AnchorKeywords> keywords = new ArrayList<AnchorKeywords>();
			for(AnchorKeywords anchor:matchAnchor){
				if(s.intValue() == anchor.getYouxianji().intValue()){
					keywords.add(anchor);
				}
			}
			fenzuList.add(keywords);
		}
		
		List<AnchorKeywords> result = new ArrayList<AnchorKeywords>(); 
		for(int i=fenzuList.size()-1;i>=0;i--){
			List<AnchorKeywords> list = fenzuList.get(i);
			//如果只有一条直接加入结果
			if(list.size()==1){
				result.add(list.get(0));
			}else{
				//对长度进行排序
				ComparatorAnchor comparator=new ComparatorAnchor();
			    Collections.sort(list, comparator);
			    for(int z=list.size()-1;z>0;z--){
			    	if(result.isEmpty()){
			    		result.add(list.get(z));
			    	}else{
			    		boolean ifContains = true;
			    		for(int m=0;m<result.size();m++){  
			    			AnchorKeywords end = result.get(m);
			    			if(compareString(end.getKeyName(),list.get(z).getKeyName())){
			    				ifContains=false;
			    			}
			    		}
			    		if(ifContains){
			    			result.add(list.get(z));
			    		}
			    	}
			    }
			}
		}
		return result;
	}
	
	@SuppressWarnings("deprecation")
	@Action(value = "save", results = {
			@Result(name = "success", type = "redirect", location = "/News/list.action?titleId=${titleId}"),
			@Result(name = "listAllNews", type = "redirect", location = "/News/listAllNews.action") })
	public String save() {
		logger.info("News save start!");
		if (null != checkExistSite()) {
			return checkExistSite();
		}

		// 设置水印路径；
		String mask = getRequest().getRealPath("/commons/mark") + "gome_mark.png";
		ReloadImgUtils.setMaskUrl(mask);
		contents = getRequest().getParameter("editorValue");
		contents = ReloadImgUtils.reloadImgForUeditorSource(contents);
		
		
		
		//匹配和内容相关的关键词
		List<AnchorKeywords> containAnchor = newsManager.getContainAnchor(contents);
		
		//放入map中把关键词名称作为key,去掉重复的关键词
		Map<String,AnchorKeywords> anchorKeywordsMap = new HashMap<String,AnchorKeywords>();
		
		if(!containAnchor.isEmpty()){
			for(AnchorKeywords ankeywords : containAnchor){
				anchorKeywordsMap.put(ankeywords.getKeyName(), ankeywords);
			}
		}
		
		//存放匹配出的所有的关键词
		List<AnchorKeywords> matchAnchor = new ArrayList<AnchorKeywords>();
		
		for(AnchorKeywords anchor:anchorKeywordsMap.values()){
			matchAnchor.add(anchor);
		}
		
		List<AnchorKeywords> resultAnchor = new ArrayList<AnchorKeywords>();
		
		//对关键词进行筛选  优先级高的优先 对于有包含的关键词，优先级别高的优先，如果优先级相同，取长度长的
		if(!matchAnchor.isEmpty()){
		 resultAnchor = getResultAnchorKeywords(matchAnchor);
		}
		
		//一篇文章最多匹配五次
		if(!resultAnchor.isEmpty()){
			if(resultAnchor.size()>5){
				resultAnchor = resultAnchor.subList(0, 5);
			}
		}
		
		//给文章中添加关键词的超链
		for(AnchorKeywords anchorKeyword : resultAnchor){
			contents = addLink(contents, anchorKeyword.getPcUrl(), anchorKeyword.getKeyName(),
					anchorKeyword.getRate());
		}

		String username = getLoginUserName();
		// 设置文章站点
		String site = getTopicSite();
		news.setSite(site);
		/*
		 * 文章来源未填写时设置
		 */
		String sourceUrl = news.getSourceUrl();
		if (null == sourceUrl || "".equals(sourceUrl.trim())) {
			if ("coo8".equals(site)) {
				news.setSourceUrl("库巴网");
			} else {
				news.setSourceUrl("国美在线");
			}
		}
	//	UploadImg uploadImg = new UploadImg();
		  Date date=new Date();
		  DateFormat format=new SimpleDateFormat("yyyy-MM-ddHHmmss");
		  if(null != pic){
		    pic.setReadable(true, false);
			pic.setWritable(true, false);
			pic.setExecutable(true, false);
		  }
		if (news != null && news.getId() != null) {		
			news.setContents(contents);
			news.setUpdateTime(new Date());
			News temp = newsManager.getById(news.getId());		
			if (temp != null) {
				news.setCreateTime(temp.getCreateTime());
				news.setStates(temp.getStates());
				news.setCreator(temp.getCreator());
				news.setModifier(username);
				news.setPicUrl(temp.getPicUrl());
				/*
				 * 文章头图连接
				 */
				if(null != pic){
				UploadResultDto uploadResultDto = GFSUtil.uploadPromotion(pic);
				if(uploadResultDto.getResult().equals(GFSConstant.UPLOAD_FAIL))
				{
				}else
				{
					news.setPicUrl(uploadResultDto.getUrl());
				}
				}
				
				newsManager.update(news);
				// 预览生成去掉
				changeNewsRelateTitles(temp.getTitleId(), username); // 文章修改时修改专题状态，专题预览生成
			} else {
				titleId = news.getTitleId();
				return "success";// 未找到相应的文章，即id无效
			}

		} else {			
			news.setContents(contents);
			news.setCreator(username);
			news.setCreateTime(new Date());
			news.setStates("N");
			if (null != pubTime && !"".equals(pubTime)) {
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					news.setPublicTime(sf.parse(pubTime));
				} catch (ParseException e) {
					logger.error(e.getMessage(), e);
				}

			}	
			/*
			 * 文章头图连接
			 */	
			if(null != pic){		
			UploadResultDto uploadResultDto = GFSUtil.uploadPromotion(pic);
			if(uploadResultDto.getResult().equals(GFSConstant.UPLOAD_FAIL))
			{
				//doing
			}else
			{
				news.setPicUrl(uploadResultDto.getUrl());
			}
			}
			
			int id = StringUtils.parseInt(newsManager.save(news));
			if (id > 0) {
				// 预览生成去掉
				changeNewsRelateTitles(news.getTitleId(), username); // 文章修改时修改专题状态，专题预览生成
				newsManager.newsAssess(id, 0);// 创建文章评价信息
			}
		}
		titleId = news.getTitleId();
		
		Map<String, Object> search = new HashMap<String, Object>();
		search.put("articleId", news.getId());
		List<LabelRel> lkr = labelRelManager.findAll(search);
		if(!lkr.isEmpty() && null != lkr){
			for (LabelRel kr : lkr) {
				labelRelManager.deleteById(kr.getId());
			}
		}
		/*
		 * 标签保存操作
		 */
		int labelId;
		if (tags != null && !"".equals(tags.trim())) {
			String s[] = tags.trim().replace("，", ",").split(",");
			for (int j = 0; j < s.length; j++) {
				if (!"".equals(s[j])) {
					Map<String, Object> searchtag = new HashMap<String, Object>();
					searchtag.put("site", site);
					searchtag.put("types", TYPES_TAGS);
					searchtag.put("names", s[j]);
					List<?> l = labelManager.findAll(searchtag);
					if (l.isEmpty()) {
						com.coo8.topic.labelart.modal.Label k = new com.coo8.topic.labelart.modal.Label();					
						k.setSite(site);// 设置关键字所在站点
						k.setTypes(TYPES_TAGS);
						k.setNames(s[j]);
						k.setCreateTime(new java.util.Date()); 
						k.setCreator(getLoginUserName());
						labelId = this.labelManager.save(k);
					} else {
						com.coo8.topic.labelart.modal.Label k = (com.coo8.topic.labelart.modal.Label) l.get(0);
						labelId = k.getId();
					}
					LabelRel kr = new LabelRel();
					kr.setKeywords(labelId);
					kr.setArticleId(news.getId());
					labelRelManager.save(kr);
				}
			}
		}	 
		if (command == COMMAND_LISTALL) {
			return "listAllNews";
		}
		logger.info("News save end!");		
		return "success";
	}

	public static String addLink(String content, String href, String keyWord, int rate) {
		String filterReg = keyWord;
		Pattern pattern = Pattern.compile(filterReg, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(content);
		int i = 0;
		while (matcher.find()) {
			String startContent = content.substring(0, matcher.start() + i * (44 + href.length()));
			String endContent = content.substring(matcher.end() + i * (44 + href.length()), content.length());
			content = startContent + "<a class='blue' target=\"_blank\" href=\"" + href + "\">" + keyWord + "</a>"
					+ endContent;
			i++;
			if (i == rate) {
				break;
			}
		}
		return content;
	}

	/**
	 * 根据指定关键字替换为超链接
	 * @param content
	 * @param href
	 * @param keyWord
	 * @return
	 */
	public static String addHrefLink(String content, String href, String keyWord){
		if (!StringUtils.isEmpity(content)) {
			Document doc = Jsoup.parse(content);
			Elements bodyLinkList = doc.getElementsByTag("body");
			StringBuffer contentBuffer =new StringBuffer();
			//body元素只有一个
			Element bodyLinkTmp = bodyLinkList.get(0);
			//查询所有a标签
			Elements bodyAdoc = bodyLinkTmp.select("a");
			for(Element firstNodeTmp: bodyAdoc){
				String aDocHtml = firstNodeTmp.html();
				//一篇文章一个关键字只打一个锚点
				if(keyWord.equals(aDocHtml)){
					return content;
				}
			}
	    	List<Node> firstNodeList = bodyLinkTmp.childNodes();
	    	List<String> hrefList = new ArrayList<String>();
	    	for(Node firstNodeTmp: firstNodeList){
	    		List<Node> secondNodeList = firstNodeTmp.childNodes();
	    		contentBuffer.append("<"+firstNodeTmp.nodeName()+firstNodeTmp.attributes()+" >");
	    		for(Node secondNodeTmp: secondNodeList){
	    			String secondNodeText = secondNodeTmp.outerHtml();
		    		Document nodeTextDocTmp = Jsoup.parse(secondNodeText);
		    		Elements aDoc = nodeTextDocTmp.select("a");
		    		Elements imgDoc = nodeTextDocTmp.select("img");
		    		//跳过文本内容中的a和img标签，不处理
		    		if(!aDoc.isEmpty() || !imgDoc.isEmpty()){
		    			contentBuffer.append(secondNodeText);
		    			continue;
		    		}
		    		//一篇文章中，同一个关键字只替换一个超链接
		    		if(hrefList.size()>0){
		    			contentBuffer.append(secondNodeText);
		    			continue;
		    		}
		    		if(secondNodeText.contains(keyWord)){
		    			hrefList.add(keyWord);
		    			contentBuffer.append(secondNodeText.replaceFirst(keyWord, "<a class='blue' target=\"_blank\" href=\"" + href + "\">" + keyWord + "</a>"));
		    		}else{
		    			contentBuffer.append(secondNodeText);
		    		}
	    		}
	    		contentBuffer.append("</"+firstNodeTmp.nodeName()+">");
	    	}
			return contentBuffer.toString();
		}
		return "";
	}
	/**
	 * 
	 * 文章修改时修改专题状态
	 * 
	 */
	private void changeNewsRelateTitles(Integer titleId, String modifier) {
		if (null != titleId) {
			Titles titles = titlesManager.getById(titleId);
			if (null != titles) {
				Date updateTime = new Date();
				titles.setUpdateTime(updateTime);
				titles.setModifier(modifier);
				titles.setEditStat("Y");
				titlesManager.update(titles);
			}
		}
	}

	@Action(value = "edit", results = { @Result(name = "success", location = "/jsp/news/create.jsp") })
	public String edit() {
		logger.info("News edit start!");
		if (newsid != 0) {
			news = newsManager.getById(newsid);
		}
		logger.info("News edit newsid:" + newsid);
		titleId = news.getTitleId();
		Map<String, Object> search = new HashMap<String, Object>();
		search.put("articleId", news.getId());
		List<LabelRel> lkr = labelRelManager.findAll(search);
		List<com.coo8.topic.labelart.modal.Label> lk = new ArrayList<com.coo8.topic.labelart.modal.Label>();
		if (null != lkr && !lkr.isEmpty()) {
			for (LabelRel kr : lkr) {
				lk.add(labelManager.getById(kr.getKeywords()));
			}
			if (!lk.isEmpty()) {
				for (int i = 0; i < lk.size(); i++) {
					com.coo8.topic.labelart.modal.Label k = lk.get(i);
					if (k != null) {
							this.tags = this.tags + k.getNames() + ",";
					}
				}
			}
		}
		
		logger.info("News edit titleId:" + titleId);
		logger.info("News edit end!");
		return "success";
	}

	@Action(value = "update", results = {
			@Result(name = "success", type = "redirect", location = "/News/list.action") })
	public String update() {
		logger.info("News update start!");
		if (null != checkExistSite()) {
			return checkExistSite();
		}
		newsManager.update(this.news);
		logger.info("News update end!");
		return "success";
	}

	@Action(value = "delete", results = {
			@Result(name = "success", type = "redirect", location = "/News/list.action?titleId=${titleId}"),
			@Result(name = "listAllNews", type = "redirect", location = "/News/listAllNews.action") })
	public String delete() {
		logger.info("News delete start!");
		String username = getLoginUserName();
		String idString = this.getRequest().getParameter("idsString");
		String ids[] = idString.split(";");
		News n = new News();
		for (int i = 0; i < ids.length; i++) {
			newsManager.deleteAssessById(Integer.valueOf(ids[i]));
			n = this.newsManager.getById(StringUtils.parseInt(ids[i]));
			if (null != n) {
				newsManager.deleteById(Integer.valueOf(ids[i]));
				changeNewsRelateTitles(n.getTitleId(), username);
				Map<String, Object> search = new HashMap<String, Object>();
				search.put("articleId", Integer.valueOf(ids[i]));
				List<LabelRel> lkr = labelRelManager.findAll(search);
				if( lkr != null && !lkr.isEmpty()  ){
					for (LabelRel kr : lkr) {
						labelRelManager.deleteById(kr.getId());
					}
				}
			}
		}
		logger.info("News delete command:" + command);
		if (command == 1) {
			return "listAllNews";
		}
		logger.info("News delete end!");
		return "success";
	}

	@Action(value = "preReview", results = { @Result(name = "success", location = "/jsp/news/news.jsp") })
	public String preReview() {
		logger.info("News preReview start!");

		logger.info("News preReview newsid:" + newsid);
		if (newsid > 0) {
			news = newsManager.getById(newsid);
			news.setCreateTimeString(news.getCreateTime());
			title_key = titlesManager.getById(news.getTitleId());
			logger.info("News preReview title_key:" + title_key);
		}
		logger.info("News preReview end!");
		return SUCCESS;
	}

	/**
	 * 文章有用评价
	 */
	@Action(value = "assessGood")
	public void assessGood() {
		logger.info("News assessGood start!");
		String callbackparam = getRequest().getParameter("jsoncallback");
		logger.info("News assessGood jsoncallback:" + callbackparam);

		if (null != news && news.getId() != 0) {
			NewsAssess assessObj = newsManager.newsAssess(news.getId(), 1);// 评论采用读数据库方式
			/*
			 * 评论采用读文件方式
			 */
			
			if (null != assessObj) {
				if (null != callbackparam && !"".equals(callbackparam)) {
					writeAjaxStr(callbackparam + "(" + newsManager.getAssessValues(assessObj) + ")");
				} else {
					writeAjaxStr(newsManager.getAssessValues(assessObj));
				}
			}
		}

		logger.info("News assessGood end!");
	}

	/**
	 * 文章无用评价
	 */
	@Action(value = "assessBad")
	public void assessBad() {
		logger.info("News assessBad start!");
		String callbackparam = getRequest().getParameter("jsoncallback");
		logger.info("News assessBad jsoncallback:" + callbackparam);

		if (null != news && news.getId() != 0) {
			NewsAssess assessObj = newsManager.newsAssess(news.getId(), 2);// 评论采用读数据库方式
			/*
			 * 评论采用读文件方式
			 */
			
			if (null != assessObj) {
				if (null != callbackparam && !"".equals(callbackparam)) {
					writeAjaxStr(callbackparam + "(" + newsManager.getAssessValues(assessObj) + ")");
				} else {
					writeAjaxStr(newsManager.getAssessValues(assessObj));
				}
			}
		}
		logger.info("News assessBad end!");
	}

	/**
	 * 获得文章评论
	 */
	@Action(value = "getNewsAssess")
	public void getNewsAssess() {
		logger.info("News getNewsAssess start!");
		String callbackparam = getRequest().getParameter("jsoncallback");
		logger.info("News getNewsAssess jsoncallback:" + callbackparam);

		if (null != news && news.getId() != 0) {
			NewsAssess assessObj = newsManager.getAssessById(news.getId()); // 评论采用读数据库方式
			/*
			 * 评论采用读文件方式
			 */
			
			if (null != assessObj) {
				if (null != callbackparam && !"".equals(callbackparam)) {
					writeAjaxStr(callbackparam + "(" + newsManager.getAssessValues(assessObj) + ")");
				} else {
					writeAjaxStr(newsManager.getAssessValues(assessObj));
				}
			}
		}
		logger.info("News getNewsAssess end!");
	}

	/**
	 * 获得关键词
	 */
	@Action(value = "getContainAnchorKeywords")
	public void getContainAnchorKeywords() {
		logger.info("News getContainAnchorKeywords start!");
		contents = getRequest().getParameter("anchorContent");
		contents = ReloadImgUtils.reloadImgForUeditorSource(contents);
		logger.info("News getContainAnchorKeywords contents:" + contents);
		List<AnchorKeywords> containAnchor = newsManager.getContainAnchor(contents);
		JSONArray jsonArry = JSONArray.fromObject(containAnchor);
		writeAjaxStr(jsonArry.toString());
		logger.info("News getContainAnchorKeywords end!");
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public int getNewsid() {
		return newsid;
	}

	public void setNewsid(int newsid) {
		this.newsid = newsid;
	}

	public int getCommand() {
		return command;
	}

	public void setCommand(int command) {
		this.command = command;
	}

	public String getNewsTestUrl2() {
		return newsTestUrl2;
	}

	public void setNewsTestUrl2(String newsTestUrl2) {
		this.newsTestUrl2 = newsTestUrl2;
	}

	
	public IKeywordsRelManager getKeywordsRelManager() {
		return keywordsRelManager;
	}

	public void setKeywordsRelManager(IKeywordsRelManager keywordsRelManager) {
		this.keywordsRelManager = keywordsRelManager;
	}

	public static void main(String[] args) {			
		String contentString = "<img src=\"http://img3.gomein.net.cn/image/bbcimg/topic_img/publish/catalog/-4002381491854848101.png\" style=\"float:none;\" alt=\"清洁\">";
		
		contentString=contentString.replaceAll("<img[^<>]*?>", "sss");  
	}
}

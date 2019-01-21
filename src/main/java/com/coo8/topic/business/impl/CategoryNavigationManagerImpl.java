package com.coo8.topic.business.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.util.DateUtil;
import org.apache.commons.imaging.ImageInfo;
import org.apache.commons.imaging.Imaging;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coo8.btoc.model.queue.ProductQueue;
import com.coo8.btoc.persistence.interfaces.queue.QueueDao;
import com.coo8.btoc.util.StringUtil;
import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.common.diamond.PropertiesUtils;
import com.coo8.topic.business.interfaces.ICategoryNavigationManager;
import com.coo8.topic.model.CategoryNavigation;
import com.coo8.topic.model.CategoryNavigationFirst;
import com.coo8.topic.model.CategoryNavigationThird;
import com.coo8.topic.persistence.interfaces.ICategoryNavigationDAO;
import com.gome.bg.gfs.constant.GFSConstant;
import com.gome.bg.gfs.dto.UploadResultDto;
import com.gome.bg.gfs.utils.GFSUtil;
import com.gome.bg.gfs.utils.GisClientWrap;
import com.gome.bg.gfs.utils.PropertiesUtil;
import com.gome.common.util.StringUtils;
import com.gome.stage.bean.category.Category;
import com.gome.stage.bean.category.CategoryNode;
import com.gome.stage.interfaces.whale.ICategoryDBService;

public class CategoryNavigationManagerImpl implements ICategoryNavigationManager{
	
	private static Logger logger = LoggerFactory.getLogger(CategoryNavigationManagerImpl.class);
	
	// 分类信息service(查询一级、二级、三级分类信息)
	private ICategoryDBService categoryDBService;

	private ICategoryNavigationDAO categoryNavigationDAO;
	
	private QueueDao queueDao;
	
	private static final int STATUS = 0;
	private static final int PRIORITY = 0;
	private static final String SITE_GOME = "gome";
	// 品类导航页模板Id
	private static final int category_navigation_templateId = PropertiesUtils.getIntValueByKey("category_navigation_templateId");
	// 品类导航页引用类型
	private static final int category_navigation_rtype = PropertiesUtils.getIntValueByKey("category_navigation_rtype");

	public ICategoryDBService getCategoryDBService() {
		return categoryDBService;
	}

	public void setCategoryDBService(ICategoryDBService categoryDBService) {
		this.categoryDBService = categoryDBService;
	}

	public ICategoryNavigationDAO getCategoryNavigationDAO() {
		return categoryNavigationDAO;
	}

	public void setCategoryNavigationDAO(ICategoryNavigationDAO categoryNavigationDAO) {
		this.categoryNavigationDAO = categoryNavigationDAO;
	}
	
	public QueueDao getQueueDao() {
		return queueDao;
	}

	public void setQueueDao(QueueDao queueDao) {
		this.queueDao = queueDao;
	}

	@Override
	public CategoryNavigation getById(Integer id) {
		return categoryNavigationDAO.getById(id);
	}
	
	@Override
	public CategoryNavigation getByName(String names, Integer id) {
		return categoryNavigationDAO.getByName(names, id);
	}

	@Override
	public int save(CategoryNavigation entity) {
		int ret = -1;
		if(null != entity){
			ret = categoryNavigationDAO.save(entity);
		}
		return ret;
	}

	@Override
	public void deleteById(Integer id) {
		categoryNavigationDAO.deleteById(id);
		categoryNavigationDAO.deleteFirstByGroupId(id);
		categoryNavigationDAO.deleteThirdByGroupId(id);
	}

	@Override
	public int update(CategoryNavigation entity) {
		if(null != entity){
			return categoryNavigationDAO.update(entity);
		}
		return -1;
	}

	@Override
	public PaginatedList<CategoryNavigation> findLikeByMap(Map<String, Object> search) {
		return categoryNavigationDAO.findLikeByMap(search);
	}
	
	@Override
	public PaginatedList<CategoryNavigationFirst> findLikeFirstByMap(Map<String, Object> search) {
		return categoryNavigationDAO.findLikeFirstByMap(search);
	}
	
	@Override
	public PaginatedList<CategoryNavigationThird> findLikeThirdByMap(Map<String, Object> search) {
		return categoryNavigationDAO.findLikeThirdByMap(search);
	}
	
	@Override
	public CategoryNavigationFirst getFirstById(Integer id) {
		return categoryNavigationDAO.getFirstById(id);
	}
	
	@Override
	public CategoryNavigationThird getThirdById(Integer id) {
		return categoryNavigationDAO.getThirdById(id);
	}
	
	@Override
	public List<Category> getFirstCategories(){
		String siteId = "homeStoreRootCategory";
		return categoryDBService.getFirstCategoriesBySiteId(siteId);
	}
	
	@Override
	public List<Category> getFirstCategoryByGroupId(Integer groupId){
		List<CategoryNavigationFirst> firstCategoryList = categoryNavigationDAO.getFirstCategoryByGroupId(groupId);
		List<Category> categoryList = new ArrayList<Category>();
		for(CategoryNavigationFirst cnf : firstCategoryList){
			categoryList.add(categoryDBService.getCategoryByCatId(cnf.getCatId()));
		}
		return categoryList;
	}
	
	@Override
	public List<Category> getSecondCategories(String firstId){
		return categoryDBService.getSecCategoryIdByFirstCategoryId(firstId);
	}
	
	@Override
	public void getCategoryNames(List<CategoryNavigationFirst> categorys) {
		CategoryNode node;
		String names;
		for(CategoryNavigationFirst category : categorys){
			node = categoryDBService.getCategoryByCategoryId(category.getCatId());
			category.setCategoryName(node.getCategoryName());
			names = "";
			for(CategoryNode cn : node.getSub()){
				names += cn.getCategoryName() + "、";
			}
			if(StringUtil.isBlank(names)){
				category.setSubCategoryNames(names);
			}else{
				category.setSubCategoryNames(names.substring(0, names.length() - 1));
			}
		}
	}
	
	@Override
	public void getSecondCategoryName(List<CategoryNavigationThird> categorys) {
		Category c;
		for(CategoryNavigationThird category : categorys){
			c = categoryDBService.getCategoryByCatId(category.getSecondId());
			category.setCategoryName(c.getCategoryName());
		}
	}
	
	@Override
	public int saveFirst(CategoryNavigationFirst entity) {
		int ret = -1;
		if(null != entity){
			ret = categoryNavigationDAO.saveFirst(entity);
		}
		return ret;
	}
	
	@Override
	public void deleteFirstById(Integer id, Integer groupId, String firstId) {
		categoryNavigationDAO.deleteFirstById(id);
		categoryNavigationDAO.deleteThirdByFirstId(groupId, firstId);
	}

	@Override
	public int updateFirst(CategoryNavigationFirst entity) {
		if(null != entity){
			return categoryNavigationDAO.updateFirst(entity);
		}
		return -1;
	}
	
	@Override
	public CategoryNavigationFirst getByFirstCatId(Integer groupId, String catId, Integer firstId) {
		return categoryNavigationDAO.getByFirstCatId(groupId, catId, firstId);
	}
	
	@Override
	public CategoryNavigationThird getByThirdCatId(Integer groupId, String catId, String subCatId, String extendName, Integer thirdId) {
		return categoryNavigationDAO.getByThirdCatId(groupId, catId, subCatId, extendName, thirdId);
	}
	
	@Override
	public int saveThird(CategoryNavigationThird entity) {
		int ret = -1;
		if(null != entity){
			ret = categoryNavigationDAO.saveThird(entity);
		}
		return ret;
	}
	
	@Override
	public void deleteThirdById(Integer id) {
		categoryNavigationDAO.deleteThirdById(id);
	}

	@Override
	public int updateThird(CategoryNavigationThird entity) {
		if(null != entity){
			return categoryNavigationDAO.updateThird(entity);
		}
		return -1;
	}
	
	@Override
	public Map<String, String> upload(File file, String fileName, String fileType, String userName) {
		Map<String, String> map = new HashMap<String, String>();
		String result = "";
		String extName = "";
    	try {
    		String types[] = {".jpg",".jpeg",".png"};
    		List<String> typeList = new ArrayList<String>();
    		for (int i = 0; i < types.length; i++) {
    			typeList.add(types[i]);
			}
		
    		InputStream inputStream = new FileInputStream(file);
			ImageInfo img = Imaging.getImageInfo(inputStream, null);
			long size = file.length();
			GisClientWrap.initDiamond();
			String promotionImgSize = PropertiesUtil.getStringValueByKey("gis.promotionImgSize");
			if(StringUtils.isNotBlank(promotionImgSize)){
				int maxSize = GFSUtil.getImgMaxSize(img.getWidth(), img.getHeight(), Integer.parseInt(promotionImgSize));
				if(size > 1024*maxSize && size > 1024*20)  //只有当图片大于20KB时才做大小校验
				{
					result += fileName + "文件超出大小(当前规格图片最大为"+ maxSize + "KB)";
					map.put("status", "fail");
					map.put("result", result);
					return map;
				}
			}
			// 扩展名格式：
			if (fileName.lastIndexOf(".") >= 0) {
				extName = fileName.substring(fileName.lastIndexOf("."));
			}
			if(!typeList.contains(extName)){
				result += "图片"+fileName+"文件类型错误";
				map.put("status", "fail");
				map.put("result", result);
				return map;
			}
			
			String savePath = PropertiesUtils.getStringValueByKey("upload.prodimg.rootpath")+"/topics";
    		String datePath = DateUtil.formatDate(new Date(), "yyyyMM")+"/"+Integer.valueOf(DateUtil.formatDate(new Date(), "dd"));
    		String PATH = "/"+userName+"/"+ datePath +"/";
    		savePath = savePath + PATH;
    		File dirFile = new File(savePath);
    		//这里接收了name的值
    		if (!dirFile.exists()) {
    			dirFile.setReadable(true, false);
    			dirFile.setWritable(true, false);
    			dirFile.setExecutable(true, false);
    			
    			dirFile.mkdirs();
    		}
			
			File saveFile = new File(savePath + fileName);
			logger.info("上传文件:" + savePath + fileName);
			//添加读写权限
			saveFile.setReadable(true, false);
			saveFile.setWritable(true, false);
			saveFile.setExecutable(true, false);
			//inputStream = new FileInputStream(file);
			OutputStream outputStream = new FileOutputStream(saveFile);
			int byteCount = 0;
			byte[] bytes = new byte[1024];
			while((byteCount = inputStream.read(bytes)) != -1){
				outputStream.write(bytes, 0, byteCount);
			}
			outputStream.close();
			inputStream.close();
			//上传到GFS
			UploadResultDto uploadResultDto = GFSUtil.uploadPromotion(saveFile);
			if(uploadResultDto.getResult().equals(GFSConstant.UPLOAD_FAIL)){
				result += "上传图片" + fileName + "到GFS:"+uploadResultDto.getMsg();
				map.put("status", "fail");
				map.put("result", result);
				return map;
			}else{
				map.put("status", "success");
				map.put("result", uploadResultDto.getUrl());
				return map;
			}
    	}  catch (Exception e) {
			logger.error("上传图片异常:" + e.getMessage(), e);
			map.put("status", "fail");
			map.put("result", "上传图片异常");
			return map;
    	}
	}
	
	@Override
	public void publish() {
		ProductQueue proQueue = new ProductQueue();
		proQueue.setStatus(STATUS);
		proQueue.setType(0);
		proQueue.setPriority(PRIORITY);
		proQueue.setInputDate(new Date());
		proQueue.setRtype(category_navigation_rtype);
		proQueue.setTemplateId(category_navigation_templateId);
		proQueue.setSite(SITE_GOME);
		
		queueDao.insertProductQueue(proQueue);
	}
}

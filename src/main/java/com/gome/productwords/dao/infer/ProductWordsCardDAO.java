package com.gome.productwords.dao.infer;


import com.coo8.btoc.util.pages.PaginatedList;
import com.gome.productwords.model.ProductWordsCard;
import com.gome.productwords.model.ProductWordsCardImportLog;
import com.gome.productwords.model.ProductWordsErrorCard;

import java.util.List;
import java.util.Map;


public interface ProductWordsCardDAO {

	public ProductWordsCard getByProductWordsName(String productWordsName);

	public ProductWordsCard getById(Integer id);

	public ProductWordsCard getByIdForDubbo(Integer id);

	public int deleteById(Integer id);

	public String save(ProductWordsCard entity);

	public int update(ProductWordsCard entity);

	public PaginatedList<ProductWordsCard> findByMap(Map<String, Object> search);

	public List<ProductWordsCard> findListByMap(Map<String, Object> search);

	public PaginatedList<ProductWordsCardImportLog> listLog(Map<String, Object> paramMap);

	public List<ProductWordsErrorCard> listDownLog(Map<String, Object> paramMap);

	public void addError(List<ProductWordsErrorCard> errorCard, Integer logID);
	
	public Integer addLog(ProductWordsCardImportLog importLog);
	
	public PaginatedList<ProductWordsCard> findLikeByMap(Map<String, Object> search);

	public List<ProductWordsCard> getByRangeId(Map<String, Object> paramMap);

	public List<ProductWordsCard> getByRandomSize(Integer randomSize);

	public List<ProductWordsCard> getByProductWordsBase(String productWordsBase);

	public Integer getSumByBeginLetter(Map<String, Object> paramMap);

	public List<ProductWordsCard> getFreshProductWords(Integer num);

	public List<ProductWordsCard> getFriendProductWordByID(Integer id);

	public Integer getCountIsEnable();

	public Integer getCountNotEnable();







}

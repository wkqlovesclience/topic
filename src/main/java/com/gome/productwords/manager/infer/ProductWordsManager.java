package com.gome.productwords.manager.infer;


import com.coo8.btoc.util.pages.PaginatedList;
import com.gome.productwords.model.ProductWordsCard;
import com.gome.productwords.model.ProductWordsCardImportLog;
import com.gome.productwords.model.ProductWordsErrorCard;
import com.gome.stage.item.PriceInfo;

import java.util.List;
import java.util.Map;


public interface ProductWordsManager {

    public ProductWordsCard getById(Integer id);

    public ProductWordsCard  getByProductWordsName(String productWordsName);

	public String save(ProductWordsCard entity);

	public void update(ProductWordsCard entity);

	public PaginatedList<ProductWordsCard> findByMap(Map<String, Object> search);

	public List<ProductWordsCard> findListByMap(Map<String, Object> search);

	public PaginatedList<ProductWordsCardImportLog> listLog(Map<String, Object> paramMap);

	public List<ProductWordsErrorCard> listDownLog(Map<String, Object> paramMap);

	public void addError(List<ProductWordsErrorCard> productWordsErrorCard, Integer logID);

	public Integer addLog(ProductWordsCardImportLog importLog);
	
	public PaginatedList<ProductWordsCard> findLikeByMap(Map<String, Object> search);

	public List<ProductWordsCard> getByRangeId(Map<String, Object> paramMap);

	public Integer getCountIsEnable();

	public Integer getCountNotEnable();



}

package com.coo8.btoc.business.interfaces.black;

import java.util.List;
import java.util.Map;

import com.coo8.btoc.model.black.ProductBlack;

public interface ProductBlackManager {

	public ProductBlack getByProId(String id);

	public void add(ProductBlack model);

	public void delete(String id);

	public List<ProductBlack> getAllList(Map paramMap);

}

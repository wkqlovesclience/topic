package com.coo8.btoc.business.impl.black;

import java.util.List;
import java.util.Map;

import com.coo8.btoc.business.interfaces.black.ProductBlackManager;
import com.coo8.btoc.model.black.ProductBlack;
import com.coo8.btoc.persistence.interfaces.black.ProductBlackDao;

public class ProductBlackManagerImpl implements ProductBlackManager {

	private ProductBlackDao productBlackDao;
	
	
	public ProductBlackDao getProductBlackDao() {
		return productBlackDao;
	}

	public void setProductBlackDao(ProductBlackDao productBlackDao) {
		this.productBlackDao = productBlackDao;
	}

	@Override
	public ProductBlack getByProId(String id) {
		
		return productBlackDao.getByProId(id);
	}

	@Override
	public void add(ProductBlack model) {
		
		productBlackDao.add(model);
	}

	@Override
	public void delete(String id) {
		productBlackDao.delete(id);
		
	}

	@Override
	public List<ProductBlack> getAllList(Map paramMap) {
		
		return productBlackDao.getAllList(paramMap);
	}

}

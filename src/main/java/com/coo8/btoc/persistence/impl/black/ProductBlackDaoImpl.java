package com.coo8.btoc.persistence.impl.black;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.coo8.btoc.model.black.ProductBlack;
import com.coo8.btoc.persistence.interfaces.black.ProductBlackDao;

public class ProductBlackDaoImpl extends SqlMapClientDaoSupport implements ProductBlackDao {

	@Override
	public ProductBlack getByProId(String id) {
		return (ProductBlack) getSqlMapClientTemplate().queryForObject("ProductBlack.getByProId", id);
	}

	@Override
	public void add(ProductBlack model) {
		getSqlMapClientTemplate().insert("ProductBlack.add",model);
	}

	@Override
	public void delete(String id) {
		getSqlMapClientTemplate().delete("ProductBlack.delete",id);
	}

	@Override
	public List<ProductBlack> getAllList(Map paramMap) {
		List<ProductBlack> arrayList = new ArrayList<ProductBlack>();
		
		Object o =this.getSqlMapClientTemplate().queryForObject(
				"ProductBlack.count");
		if(o==null) return arrayList;
		

		List<ProductBlack> list =this.getSqlMapClientTemplate().queryForList(
				"ProductBlack.getAllList",paramMap);
		
		if(list !=null) arrayList.addAll(list);
		
		return arrayList;
	}

	@Override
	public int count() {
		return 0;
	}

}

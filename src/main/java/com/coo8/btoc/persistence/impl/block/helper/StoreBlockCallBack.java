package com.coo8.btoc.persistence.impl.block.helper;

import com.coo8.btoc.model.template.Template;

public interface StoreBlockCallBack {
	
	void store(Template template, String blockName, int type);
}

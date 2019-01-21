package com.coo8.btoc.util.file;

import java.io.File;

import com.coo8.btoc.config.ReloadableConfig;
import com.coo8.btoc.model.block.Block;
import com.mysql.jdbc.StringUtils;

public final class PathUtil {
	
	private PathUtil(){}
	public static String getManualBlockPath(Block block) {
		String path = ReloadableConfig.getProperty("manual_block", "");
		
		return path + File.separator + block.getId()/1000 + 
			File.separator + block.getId() + File.separator
			+ "b_" + block.getName() + ".html";
	}
	
	public static String getIncludeOrder(String path) {
		if(StringUtils.isNullOrEmpty(path))
			return "";
		
		return "<!--# include virtual=\"" + path +"\" --> ";
	}
	
	
	public static void main(String ar[]) {
	}
}

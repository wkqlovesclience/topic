package com.coo8.topic.model;

import java.util.Comparator;

public class ComparatorAnchor implements Comparator{
	@Override
	public int compare(Object obj0, Object obj1) {
		AnchorKeywords user0=(AnchorKeywords)obj0;
		AnchorKeywords user1=(AnchorKeywords)obj1;

          String user0Length =String.valueOf(user0.getKeyName().length());
          String user1Length  =String.valueOf(user1.getKeyName().length());
		  int flag=user0Length.compareTo(user1Length);
		  return flag;
		 }

}

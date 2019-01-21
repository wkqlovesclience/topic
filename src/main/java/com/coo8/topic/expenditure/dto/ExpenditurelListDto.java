package com.coo8.topic.expenditure.dto;

import lombok.Data;

@Data
public class ExpenditurelListDto {
	private String[] noi;
	private String[] portIds;
	private String[] stairChanIds;
	private String[] secondChanIds;
	private String[] thirdChanIds;
	private String[] createTime;
	private String[] uvs;
	private String[] orderNums;
	private String[] sales;
	private String[] expenditures;
	
	private String creator;
}

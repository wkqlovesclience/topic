package com.coo8.topic;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.coo8.topic.expenditure.dto.ChannelDetailDto;
import com.coo8.topic.expenditure.dto.ExpenditurelListDto;
import com.coo8.topic.expenditure.dto.ExpenditureDto;
import com.coo8.topic.expenditure.pojo.ChannelDetailStair;
import com.coo8.topic.expenditure.pojo.Expenditure;
import com.coo8.topic.expenditure.pojo.PortType;
import com.coo8.topic.expenditure.service.ExpenditureService;
import com.gome.architect.idgnrt.IDGenerator;
import com.gome.utils.IDGeneratorUtils;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"classpath*:/**/applicationContext*.xml"})
@Slf4j
public class ExpenditureTest {

	@Autowired
	private ExpenditureService expenditureService;
	@Test
	public void getTest(){
		expenditureService.findExpenditure(3);
	}
	
	@Test
	public void addTest(){
		Expenditure entity = new Expenditure();
		entity.setPortId(1);
		entity.setCreateDate(new Date());
		entity.setCreator("–°”„");
		entity.setExpenditure(new BigDecimal(20000));
		entity.setMender("–°”„");
		entity.setOrderNum(1400);
		entity.setSale(new BigDecimal(520.2));
		entity.setStairChanId(1501);
		entity.setUV(55533);
		int i = expenditureService.addExpenditure(entity);
		System.out.println(i);
	}
	
	@Test
	public void searchByKey(){
		Expenditure entity = new Expenditure();
		List<ExpenditureDto> list = expenditureService.findExpenditureList(entity);
		if(list ==null){
			System.out.println("Œ¥’“µΩœ‡πÿ–≈œ¢");
		}else{
			System.out.println(list.get(0).getTimestamp());
		}
		
	}
	
	@Test
	public void addChannelDetails(){
	
		ArrayList<ChannelDetailDto> list = new ArrayList<ChannelDetailDto>();
		ChannelDetailDto dto1 = new ChannelDetailDto();
		ChannelDetailDto dto2 = new ChannelDetailDto();
		ChannelDetailDto dto3 = new ChannelDetailDto();
		ChannelDetailDto dto4 = new ChannelDetailDto();
		dto1.setStairName("≤‚ ‘StairName1");
		dto1.setSecondName("≤‚ ‘SecondName1");
		dto1.setThirdName("≤‚ ‘ThirdName1");
		list.add(dto1);
		dto2.setStairName("≤‚ ‘StairName2");
		list.add(dto2);
		dto3.setStairName("≤‚ ‘StairName3");
		dto3.setSecondName("≤‚ ‘SecondName3");
		list.add(dto3);
		dto4.setStairName("≤‚ ‘StairName4");
		dto4.setThirdName("≤‚ ‘ThirdName4");
		list.add(dto4);

			try {
			//	this.expenditureService.addChannelDetailList(list);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	
	}
	
	@Test
	public void idGeneratorTest(){
		IDGenerator instance = IDGeneratorUtils.getInstance();
		 for(int i=1; i< 10;i++){
			   System.out.println(instance.next());
			  }
	}
	
	@Test
	public void addPort(){
		List<PortType> port = new ArrayList<PortType>();
		PortType type1 = new PortType();
		PortType type2 = new PortType();
		type1.setPortName("APP");
		type2.setPortName("–°≥Ã–Ú");
		port.add(type1);
		port.add(type2);
	/*	int i = expenditureService.addPortTypeList(port);
		System.out.println("∂Àø⁄≤Â»Î:"+i);*/
		
	}
}

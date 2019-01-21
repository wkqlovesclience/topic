package com.coo8.topic.expenditure.service.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.coo8.topic.expenditure.pojo.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coo8.topic.expenditure.dao.ExpenditureMapper;
import com.coo8.topic.expenditure.dto.ChannelDetailDto;
import com.coo8.topic.expenditure.dto.ExpenditureDto;
import com.coo8.topic.expenditure.dto.ExpenditurelListDto;
import com.coo8.topic.expenditure.dto.ExportExpenditureDto;
import com.coo8.topic.expenditure.service.ExpenditureService;
import com.gome.architect.idgnrt.IDGenerator;
import com.gome.common.util.date.DateUtils;
import com.gome.utils.IDGeneratorUtils;

import lombok.extern.slf4j.Slf4j;	

@Service
@Slf4j
public class ExpenditureServiceImpl implements ExpenditureService{
	@Resource
	private ExpenditureMapper expenditureMapper;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	@Override
	@Transactional(readOnly=true)
	public ExpenditureDto findExpenditure(int id) {
		log.info("findExpenditure is start!");
		Expenditure expd = expenditureMapper.getExpenditureById(id);
		ExpenditureDto dto = new ExpenditureDto();
		dto.setId(expd.getId());
		ChannelDetailStair stair = new ChannelDetailStair();
		ChannelDetailSecond second = new ChannelDetailSecond();
		ChannelDetailThird third = new ChannelDetailThird();
		if(expd.getPortId() >0){
			List<PortType> portTypeList = expenditureMapper.getPortTypeList(expd.getPortId());
			if(portTypeList.size()>0){
				PortType portType = portTypeList.get(0);
				dto.setPortId(expd.getPortId());
				dto.setPort(portType.getPortName());
			}
		}else{
			dto.setPort("-");
		}
		if(expd.getStairChanId() >0){
			 List<ChannelDetailStair> stairList = expenditureMapper.getChannelStairList(expd.getStairChanId(),0,-1);
			 if(stairList.size()>0) stair = stairList.get(0);
		}else{
			stair.setStairName("-");
		}
		if(expd.getStairChanId() >0 && expd.getSecondChanId() >0){
					List<ChannelDetailSecond> secondList = expenditureMapper.getChannelSecondList(expd.getSecondChanId(),0,-1);
					if(secondList.size()>0) second = secondList.get(0);
					
		}else{
			second.setSecondName("-");
		}
		if(expd.getSecondChanId() >0 && expd.getThirdChanId() >0){
			 List<ChannelDetailThird> thirdList = expenditureMapper.getChannelThirdList(expd.getThirdChanId(),0,0,-1);
			 if(thirdList.size()>0)  third = thirdList.get(0);
		}else{
			third.setThirdName("-");
		}
		dto.setChanStair(stair);
		dto.setChanSecond(second);
		dto.setChanThird(third);
		dto.setExpenditure(expd.getExpenditure());
		dto.setSale(expd.getSale());
		dto.setUV(expd.getUV());
		dto.setOrderNum(expd.getOrderNum());
		return dto;
	}

	@Override
	@Transactional
	public int addExpenditure(Expenditure entity) {
		log.info("addExpenditure is start!");
		 return expenditureMapper.insertExpenditureDetail(entity);
	}

	@Override
	@Transactional(readOnly=true)
	public List<ExpenditureDto> findExpenditureList(Expenditure serach) {
		 List<Expenditure> expenditureList = expenditureMapper.getExpenditureList(serach);
		 if(!expenditureList.isEmpty()){
			 List<ExpenditureDto> dtoList = new ArrayList<ExpenditureDto>();
			 for (Expenditure expenditure : expenditureList) {
				ExpenditureDto dto = new ExpenditureDto();
				dto.setId(expenditure.getId());
				if(expenditure.getPortId() >0){
					List<PortType> portTypeList = expenditureMapper.getPortTypeList(expenditure.getPortId());
					if(portTypeList.size()>0){
						PortType portType = portTypeList.get(0);
						dto.setPort(portType.getPortName());
					}
				}else{
					dto.setPort("-");
				}
				ChannelDetailStair stair = new ChannelDetailStair();
				ChannelDetailSecond second = new ChannelDetailSecond();
				ChannelDetailThird third = new ChannelDetailThird();
				if(expenditure.getStairChanId() >0){
					 List<ChannelDetailStair> stairList = expenditureMapper.getChannelStairList(expenditure.getStairChanId(),0,-1);
					 if(stairList.size()>0) stair = stairList.get(0);
				}else{
					stair.setStairName("-");
				}
				if(expenditure.getStairChanId() >0 && expenditure.getSecondChanId() >0){
							List<ChannelDetailSecond> secondList = expenditureMapper.getChannelSecondList(expenditure.getSecondChanId(),0,-1);
							if(secondList.size()>0) second = secondList.get(0);
							
				}else{
					second.setSecondName("-");
				}
				if(expenditure.getSecondChanId() >0 && expenditure.getThirdChanId() >0){
					 List<ChannelDetailThird> thirdList = expenditureMapper.getChannelThirdList(expenditure.getThirdChanId(),0,0,-1);
					 if(thirdList.size()>0)  third = thirdList.get(0);
				}else{
					third.setThirdName("-");
				}
				dto.setChanStair(stair);
				dto.setChanSecond(second);
				dto.setChanThird(third);
				dto.setTimestamp(DateUtils.DateToString(expenditure.getCreateDate(), "yyyy年MM月dd日"));
				dto.setExpenditure(expenditure.getExpenditure());
				dto.setSale(expenditure.getSale());
				dto.setUV(expenditure.getUV());
				dto.setMender(expenditure.getMender());
				dto.setOrderNum(expenditure.getOrderNum());
				dtoList.add(dto);
			}
			 return dtoList;
		 }
		return null;
	}
	
	
	@Override
	@Transactional(readOnly=true)
	public List<ExportExpenditureDto> exportExpenditureListByDate(String startDate, String endDate) {
		log.info("exportExpenditureListByDate Param # startDate:{} ,endDate:{}",startDate,endDate);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		 List<Expenditure> list = expenditureMapper.getExpenditureListByDate(startDate, endDate);
		 if(list !=null && list.size()>0){
			 List<ExportExpenditureDto> dtoList = new ArrayList<ExportExpenditureDto>();
			 for (Expenditure expenditure : list) {
				 ExportExpenditureDto dto = new ExportExpenditureDto();
				if(expenditure.getPortId() >0){//端口
					List<PortType> portList = expenditureMapper.getPortTypeList(expenditure.getPortId());
					dto.setPortName(portList.size()>0 ? portList.get(0).getPortName() : "-");
				}
				if(expenditure.getStairChanId() >0){//一级渠道
					 List<ChannelDetailStair> stairList = expenditureMapper.getChannelStairList(expenditure.getStairChanId(),0,-1);
					dto.setStairName(stairList.size()>0 ? stairList.get(0).getStairName() : "-");
				}
				if(expenditure.getStairChanId() >0 && expenditure.getSecondChanId() >0){//二级渠道
					List<ChannelDetailSecond> secondList = expenditureMapper.getChannelSecondList(expenditure.getSecondChanId(),0,-1);
					dto.setSecondName(secondList.size()>0 ? secondList.get(0).getSecondName() : "-");
				}
				if(expenditure.getSecondChanId() >0 && expenditure.getThirdChanId() >0){//三级渠道
					 List<ChannelDetailThird> thirdList = expenditureMapper.getChannelThirdList(expenditure.getThirdChanId(),0,0,-1);
					 dto.setThirdName(thirdList.size()>0 ? thirdList.get(0).getThirdName() : "-");
				}
				String date = dateFormat.format(expenditure.getCreateDate());
				dto.setDateTime(date);//时间
				dto.setUvNum(expenditure.getUV()+"");//uv
				dto.setOrderNum(expenditure.getOrderNum()+"");//订单量
				dto.setSaleNum(expenditure.getSale().toString());//销售额
				dto.setExpNum(expenditure.getExpenditure().toString());//花费
				dtoList.add(dto);
			}
			 return dtoList;
		 }
		return null;
	}

	@Override
	@Transactional
	public int addExpenditureList(ExpenditurelListDto arrays) {
		String[] portIds = arrays.getPortIds();
		int addCount = 0;
		if(portIds.length>0){
			String[] secondChanIds = arrays.getSecondChanIds();
			String[] stairChanIds = arrays.getStairChanIds();
			String[] thirdChanIds = arrays.getThirdChanIds();
			String[] createTime = arrays.getCreateTime();
			String[] uvs = arrays.getUvs();
			String[] sales = arrays.getSales();
			String[] orderNums = arrays.getOrderNums();
			String[] expenditures = arrays.getExpenditures();
			List<Expenditure> list = new ArrayList<Expenditure>();
			
			for (int i = 0; i < portIds.length; i++) {
				Expenditure expd = new Expenditure();
				if(StringUtils.isNotBlank(portIds[i])) expd.setPortId(Integer.parseInt(portIds[i]));
				if(StringUtils.isNotBlank(stairChanIds[i])) expd.setStairChanId(Long.valueOf(stairChanIds[i]));
				if(StringUtils.isNotBlank(secondChanIds[i])) expd.setSecondChanId(Long.valueOf(secondChanIds[i]));
				if(StringUtils.isNotBlank(thirdChanIds[i])) expd.setThirdChanId(Integer.parseInt(thirdChanIds[i]));
				Date cerateDate = new Date();
				if(StringUtils.isBlank(createTime[i])){
					expd.setCreateDate(cerateDate);
				} else{
					try {
						cerateDate= sdf.parse(createTime[i]);
					}catch (ParseException e) {
						log.info("时间转换出错");
					}
					expd.setCreateDate(cerateDate);
				}
				if(StringUtils.isNotBlank(uvs[i])) expd.setUV(Integer.parseInt(uvs[i]));
				if(StringUtils.isNotBlank(orderNums[i])) expd.setOrderNum(Integer.parseInt(orderNums[i]));
				if(StringUtils.isNotBlank(sales[i])) expd.setSale(new BigDecimal(sales[i]));
				if(StringUtils.isNotBlank(expenditures[i])) expd.setExpenditure(new BigDecimal(expenditures[i]));
				list.add(expd);
			}
//				Date date = new Date();
				String creator = arrays.getCreator();
			for (Expenditure expenditure : list) {
//				expenditure.setCreateDate(date);
				expenditure.setCreator(creator);
				expenditure.setMender(creator);
				expenditure.setDelFlag(false);
				expenditure.setUpdateDate(new Date());
				addCount+= this.expenditureMapper.insertExpenditureDetail(expenditure);
			}
		}
		return addCount;
	}

	@Override
	@Transactional
	public void addChannelDetailList(ChannelDetailDto dto) throws Exception{
		ChannelDetailStair chanStair = null;
		ChannelDetailSecond chanSecond = null;
		ChannelDetailThird chanThird = null;
		Date now = new Date();
			//创建id自动生成器
		if(dto.getPortId()!=0){
			IDGenerator idGenerator = IDGeneratorUtils.getInstance();
			
					if(StringUtils.isNotBlank(dto.getStairName())){
						dto.setStairId(idGenerator.next());
						chanStair = new ChannelDetailStair();
						chanStair.setPortId(dto.getPortId());
						chanStair.setId(dto.getStairId());
						chanStair.setStairName(dto.getStairName());
						chanStair.setCreateDate(now);
						int stairNum = this.expenditureMapper.insertChannelDetail_Stair(chanStair);
						log.info("插入成功 chanStair :{} 条数据",stairNum);
					}
						if(StringUtils.isNotBlank(dto.getSecondName())){
							dto.setSecondId(idGenerator.next());
							chanSecond = new ChannelDetailSecond();
							chanSecond.setId(dto.getSecondId());
							chanSecond.setStairId(dto.getStairId());
							chanSecond.setSecondName(dto.getSecondName());
							chanSecond.setCreateDate(now);
							int secondSum = this.expenditureMapper.insertChannelDetail_Second(chanSecond);
							log.info("插入成功 chanSecond :{} 条数据",secondSum);
						}
						if(StringUtils.isNotBlank(dto.getSecondName()) || dto.getSecondId()>0){
							if(StringUtils.isNotBlank(dto.getThirdName())){
								chanThird = new ChannelDetailThird();
								chanThird.setStairId(dto.getStairId());
								chanThird.setSecondId(dto.getSecondId());
								chanThird.setThirdName(dto.getThirdName());
								chanThird.setCreateDate(now);
								int thirdSum = this.expenditureMapper.insertChannelDetail_Third(chanThird);
								log.info("插入成功 chanThird :{} 条数据",thirdSum);
							}
						}
					
			idGenerator.destroy();
		}
	}

	@Override
	@Transactional
	public int addPortType(PortType type) {
		int insertNum = expenditureMapper.insertPortType(type);
		return insertNum;
	}

	@Override
	public List<PortType> findPortTypeList() {
		List<PortType> typeList = expenditureMapper.getPortTypeList(0);
		return typeList;
	}

	@Override
	public List<ChannelDetailStair> findChannelStairList(int portId) {
		List<ChannelDetailStair> stairList = expenditureMapper.getChannelStairList(0,portId,0);
		return stairList;
	}

	@Override
	public List<ChannelDetailSecond> findChannelSecondList(long stairId) {
		List<ChannelDetailSecond> secondList = expenditureMapper.getChannelSecondList(0,stairId,0);
		return secondList;
	}

	@Override
	public List<ChannelDetailThird> findChannelThirdList(long stairId,long secondId) {
		List<ChannelDetailThird> thirdList = expenditureMapper.getChannelThirdList(0,stairId,secondId,0);
		return thirdList;
	}

	@Override
	@Transactional(readOnly=true)
	public ChannelDetailStair findChannelStair(String name,int portId) {
		return expenditureMapper.getChannelStairByName(name.trim(),portId);
	}

	@Override
	@Transactional(readOnly=true)
	public ChannelDetailSecond findChannelSecond(String name,long stairId) {
		return expenditureMapper.getChannelSecondByName(name.trim(),stairId);
		
	}

	@Override
	@Transactional(readOnly=true)
	public ChannelDetailThird findChannelThird(String name,long secondId) {
		return expenditureMapper.getChannelThirdByName(name.trim(),secondId);
	}

	@Override
	@Transactional
	public int modifyExpenditure(ExpenditureDto dto) {
		Expenditure entity = new Expenditure();
		if(dto.getId() != 0){
			entity.setId(dto.getId());
			if(dto.getPortId() != 0){
				entity.setPortId(dto.getPortId());
			}
			if(dto.getChanStair() != null){
				entity.setStairChanId(dto.getChanStair().getId());
			}
			if(dto.getChanSecond() != null){
				entity.setSecondChanId(dto.getChanSecond().getId());
			}
			if(dto.getChanThird() != null){
				entity.setThirdChanId(dto.getChanThird().getId());
			}
			if(dto.getUV() != 0){
				entity.setUV(dto.getUV());
			}
			if(dto.getOrderNum() != 0){
				entity.setOrderNum(dto.getOrderNum());
			}
			if(dto.getSale() != null){
				entity.setSale(dto.getSale());
			}
			if(dto.getExpenditure() != null){
				entity.setExpenditure(dto.getExpenditure());
			}
			entity.setMender(dto.getMender());
			entity.setUpdateDate(new Date());
		}
		expenditureMapper.updateExpenditure(entity);
		return 0;
	}

	@Override
	public List<ExpendAnalyseBase> getExpenditureAnalyseListByDate(String startDate, String endDate) {
		return expenditureMapper.getExpenditureAnalyseListByDate(startDate,endDate);
	}

	@Override
	public List<ExpendAnalyseCharge> selectExpendDataInMonth(String yearNum, Integer stairId) {
		return expenditureMapper.selectExpendDataInMonth(yearNum,stairId);
	}

	@Override
	public List<ExpendPortAnalyseCharge> selectPortExpendDataInMonth(String yearNum, Integer portId) {
		return expenditureMapper.selectPortExpendDataInMonth(yearNum,portId);
	}

	@Override
	public List<WholeExpendAnalyseCharge> selectWholeExpendDataInMonth(String yearNum) {
		return expenditureMapper.selectWholeExpendDataInMonth(yearNum);
	}

	@Override
	public BigDecimal selectPortExpendAmount(String startDate, String endDate, Integer portId) {
		return expenditureMapper.selectPortExpendAmount(startDate,endDate,portId);
	}

	@Override
	public BigDecimal selectWholeExpendAmount(String startDate, String endDate) {
		return expenditureMapper.selectWholeExpendAmount(startDate,endDate);
	}

	@Override
	public int findPortByName(String name) {
		return this.expenditureMapper.getPortByName(name.trim());
		 
	}

}
